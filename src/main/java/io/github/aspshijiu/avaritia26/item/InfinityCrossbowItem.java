package io.github.aspshijiu.avaritia26.item;

import java.util.List;
import java.util.function.Consumer;

import io.github.aspshijiu.avaritia26.entity.EndestPearlEntity;
import io.github.aspshijiu.avaritia26.entity.HeavenArrowEntity;
import io.github.aspshijiu.avaritia26.registry.ModDataComponents;
import io.github.aspshijiu.avaritia26.registry.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.entity.projectile.arrow.SpectralArrow;
import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;
import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.WindCharge;
import net.minecraft.world.entity.projectile.throwableitemprojectile.Snowball;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEgg;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEnderpearl;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

public final class InfinityCrossbowItem extends CrossbowItem {
	private static final int CHARGE_TICKS = 10;
	private static final float[] MULTI_ANGLES = {-20.0F, -10.0F, 0.0F, 10.0F, 20.0F};

	public InfinityCrossbowItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack crossbow = player.getItemInHand(hand);
		if (player.isShiftKeyDown()) {
			boolean multi = !isMulti(crossbow);
			crossbow.set(ModDataComponents.INFINITY_CROSSBOW_MULTI, multi);
			message(player, multi
					? "tooltip.avaritia26.infinity_crossbow.multi_enabled"
					: "tooltip.avaritia26.infinity_crossbow.multi_disabled");
			return InteractionResult.SUCCESS;
		}
		ChargedProjectiles charged = crossbow.getOrDefault(
				DataComponents.CHARGED_PROJECTILES,
				ChargedProjectiles.EMPTY
		);
		if (!charged.isEmpty()) {
			if (level instanceof ServerLevel serverLevel) {
				fire(serverLevel, player, crossbow, charged.itemCopies().getFirst());
				crossbow.remove(DataComponents.CHARGED_PROJECTILES);
			}
			return InteractionResult.SUCCESS;
		}
		player.startUsingItem(hand);
		return InteractionResult.CONSUME;
	}

	@Override
	public void onUseTick(Level level, LivingEntity user, ItemStack crossbow, int remainingUseDuration) {
		if (remainingUseDuration != 1 || level.isClientSide()
				|| !crossbow.getOrDefault(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY).isEmpty()) {
			return;
		}
		ItemStack ammo = user.getOffhandItem();
		if (!isAmmo(ammo)) {
			// ChargedProjectiles cannot contain an empty stack; the catalyst is an internal marker for the default heaven arrow.
			ammo = new ItemStack(ModItems.INFINITY_CATALYST);
		}
		crossbow.set(DataComponents.CHARGED_PROJECTILES,
				ChargedProjectiles.ofNonEmpty(List.of(ammo.copyWithCount(1))));
		level.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.CROSSBOW_LOADING_END,
				SoundSource.PLAYERS, 1.0F, 1.0F);
		user.stopUsingItem();
	}

	@Override
	public boolean releaseUsing(ItemStack stack, Level level, LivingEntity user, int remainingUseDuration) {
		return false;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return CHARGE_TICKS;
	}

	@Override
	public void appendHoverText(
			ItemStack stack,
			TooltipContext context,
			TooltipDisplay display,
			Consumer<Component> tooltip,
			TooltipFlag flag
	) {
		tooltip.accept(Component.translatable("tooltip.avaritia26.infinity_crossbow.infinity")
				.withStyle(ChatFormatting.LIGHT_PURPLE));
		tooltip.accept(Component.translatable(isMulti(stack)
				? "tooltip.avaritia26.infinity_crossbow.multi_enabled"
				: "tooltip.avaritia26.infinity_crossbow.multi_disabled").withStyle(ChatFormatting.GRAY));
	}

	public static boolean isMulti(ItemStack stack) {
		return stack.getOrDefault(ModDataComponents.INFINITY_CROSSBOW_MULTI, false);
	}

	public static boolean isAmmo(ItemStack stack) {
		return stack.is(Items.ARROW)
				|| stack.is(Items.ENDER_PEARL)
				|| stack.is(Items.FIRE_CHARGE)
				|| stack.is(Items.SPECTRAL_ARROW)
				|| stack.is(Items.TIPPED_ARROW)
				|| stack.is(Items.FIREWORK_ROCKET)
				|| stack.is(Items.TRIDENT)
				|| stack.is(Items.SNOWBALL)
				|| stack.is(Items.EGG)
				|| stack.is(ModItems.ENDEST_PEARL)
				|| stack.is(Items.TNT)
				|| stack.is(Items.WIND_CHARGE);
	}

	public static Projectile createProjectile(ServerLevel level, Player player, ItemStack ammo, ItemStack crossbow) {
		if (ammo.is(Items.ARROW) || ammo.is(Items.TIPPED_ARROW)) {
			return new Arrow(level, player, ammo.copyWithCount(1), crossbow.copy());
		}
		if (ammo.is(Items.SPECTRAL_ARROW)) {
			return new SpectralArrow(level, player, ammo.copyWithCount(1), crossbow.copy());
		}
		if (ammo.is(Items.ENDER_PEARL)) {
			return new ThrownEnderpearl(level, player, ammo.copyWithCount(1));
		}
		if (ammo.is(Items.FIRE_CHARGE)) {
			return new SmallFireball(level, player, player.getLookAngle());
		}
		if (ammo.is(Items.FIREWORK_ROCKET)) {
			return new FireworkRocketEntity(level, ammo.copyWithCount(1), player,
					player.getX(), player.getEyeY(), player.getZ(), true);
		}
		if (ammo.is(Items.TRIDENT)) {
			return new ThrownTrident(level, player, ammo.copyWithCount(1));
		}
		if (ammo.is(Items.SNOWBALL)) {
			return new Snowball(level, player, ammo.copyWithCount(1));
		}
		if (ammo.is(Items.EGG)) {
			return new ThrownEgg(level, player, ammo.copyWithCount(1));
		}
		if (ammo.is(ModItems.ENDEST_PEARL)) {
			return new EndestPearlEntity(level, player, ammo.copyWithCount(1));
		}
		if (ammo.is(Items.WIND_CHARGE)) {
			return new WindCharge(player, level, player.getX(), player.getEyeY(), player.getZ());
		}
		HeavenArrowEntity arrow = new HeavenArrowEntity(level, player, crossbow);
		arrow.setBaseDamage(20.0);
		arrow.setCritArrow(true);
		return arrow;
	}

	private void fire(ServerLevel level, Player player, ItemStack crossbow, ItemStack ammo) {
		float[] angles = isMulti(crossbow) ? MULTI_ANGLES : new float[]{0.0F};
		for (float angle : angles) {
			Projectile projectile = createProjectile(level, player, ammo, crossbow);
			float velocity = ammo.is(Items.FIRE_CHARGE) ? 1.0F
					: ammo.is(Items.ENDER_PEARL) || ammo.is(Items.SNOWBALL) || ammo.is(Items.EGG)
					|| ammo.is(ModItems.ENDEST_PEARL) || ammo.is(Items.WIND_CHARGE) ? 1.5F : 3.0F;
			projectile.shootFromRotation(player, player.getXRot(), player.getYRot() + angle, 0.0F, velocity, 1.0F);
			if (projectile instanceof AbstractArrow arrow) {
				arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
			}
			level.addFreshEntity(projectile);
		}
		playSound(level, player, ammo);
		player.getCooldowns().addCooldown(crossbow, isMulti(crossbow) ? 200 : 20);
		player.awardStat(Stats.ITEM_USED.get(this));
	}

	private static void playSound(ServerLevel level, Player player, ItemStack ammo) {
		if (ammo.is(Items.TRIDENT)) {
			level.playSeededSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.TRIDENT_THROW,
					SoundSource.PLAYERS, 1.0F, 1.0F, level.getRandom().nextLong());
			return;
		}
		level.playSound(null, player.getX(), player.getY(), player.getZ(), sound(ammo),
				SoundSource.PLAYERS, 1.0F, 1.0F);
	}

	private static net.minecraft.sounds.SoundEvent sound(ItemStack ammo) {
		if (ammo.is(Items.ENDER_PEARL) || ammo.is(ModItems.ENDEST_PEARL)) {
			return SoundEvents.ENDER_PEARL_THROW;
		}
		if (ammo.is(Items.FIRE_CHARGE)) {
			return SoundEvents.BLAZE_SHOOT;
		}
		if (ammo.is(Items.FIREWORK_ROCKET)) {
			return SoundEvents.FIREWORK_ROCKET_SHOOT;
		}
		if (ammo.is(Items.SNOWBALL)) {
			return SoundEvents.SNOWBALL_THROW;
		}
		if (ammo.is(Items.EGG)) {
			return SoundEvents.EGG_THROW;
		}
		if (ammo.is(Items.WIND_CHARGE)) {
			return SoundEvents.WIND_CHARGE_THROW;
		}
		return SoundEvents.CROSSBOW_SHOOT;
	}

	private static void message(Player player, String key) {
		if (player instanceof ServerPlayer serverPlayer && serverPlayer.connection != null) {
			serverPlayer.sendSystemMessage(Component.translatable(key), true);
		}
	}
}
