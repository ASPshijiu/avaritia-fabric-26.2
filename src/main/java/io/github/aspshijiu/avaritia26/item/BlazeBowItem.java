package io.github.aspshijiu.avaritia26.item;

import java.util.function.Consumer;

import io.github.aspshijiu.avaritia26.entity.BurningArrowEntity;
import io.github.aspshijiu.avaritia26.entity.BurningBallEntity;
import io.github.aspshijiu.avaritia26.registry.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
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
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public final class BlazeBowItem extends BowItem {
	private static final float VELOCITY_MULTIPLIER = 1.2F;
	private static final double BASE_DAMAGE = 4.0;

	public BlazeBowItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (player.isShiftKeyDown()) {
			boolean enabled = !isBurningMode(stack);
			stack.set(ModDataComponents.BLAZE_BOW_BURNING, enabled);
			if (player instanceof ServerPlayer serverPlayer && serverPlayer.connection != null) {
				serverPlayer.sendSystemMessage(Component.translatable(enabled
						? "tooltip.avaritia26.blaze_bow.burning_enabled"
						: "tooltip.avaritia26.blaze_bow.burning_disabled"), true);
			}
			player.swing(hand);
			return InteractionResult.SUCCESS;
		}
		player.startUsingItem(hand);
		return InteractionResult.SUCCESS;
	}

	@Override
	public boolean releaseUsing(ItemStack stack, Level level, LivingEntity user, int remainingUseDuration) {
		if (!(user instanceof Player player)) {
			return false;
		}
		int chargeTime = getUseDuration(stack, user) - remainingUseDuration;
		float draw = getPowerForTime(chargeTime);
		float speed = draw * VELOCITY_MULTIPLIER * 3.0F;
		if (speed < 0.3F) {
			return false;
		}
		if (level instanceof ServerLevel serverLevel) {
			Projectile projectile = createProjectile(serverLevel, player, stack, draw);
			serverLevel.addFreshEntity(projectile);
			if (projectile instanceof BurningBallEntity) {
				player.getCooldowns().addCooldown(stack, 20);
			}
			serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(),
					projectile instanceof BurningBallEntity ? SoundEvents.SNOWBALL_THROW : SoundEvents.ARROW_SHOOT,
					SoundSource.PLAYERS, 1.0F,
					1.0F / (serverLevel.getRandom().nextFloat() * 0.4F + 1.2F)
							+ draw * VELOCITY_MULTIPLIER * 0.5F);
			player.awardStat(Stats.ITEM_USED.get(this));
		}
		return true;
	}

	public Projectile createProjectile(ServerLevel level, Player player, ItemStack bow, float draw) {
		float speed = draw * VELOCITY_MULTIPLIER * 3.0F;
		if (isBurningMode(bow)) {
			BurningBallEntity ball = new BurningBallEntity(level, player);
			ball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, speed, 0.5F);
			return ball;
		}

		var enchantments = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
		int powerLevel = EnchantmentHelper.getItemEnchantmentLevel(
				enchantments.getOrThrow(Enchantments.POWER), bow);
		int flameLevel = EnchantmentHelper.getItemEnchantmentLevel(
				enchantments.getOrThrow(Enchantments.FLAME), bow);
		double damage = BASE_DAMAGE + (powerLevel > 0 ? powerLevel * 0.5 + 0.5 : 0.0);
		BurningArrowEntity arrow = new BurningArrowEntity(level, player, bow);
		arrow.configure(damage, draw == 1.0F, flameLevel > 0 ? 100 : 0);
		arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, speed, 0.5F);
		return arrow;
	}

	public static boolean isBurningMode(ItemStack stack) {
		return stack.getOrDefault(ModDataComponents.BLAZE_BOW_BURNING, false);
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return false;
	}

	@Override
	public void appendHoverText(
			ItemStack stack,
			TooltipContext context,
			TooltipDisplay display,
			Consumer<Component> tooltip,
			TooltipFlag flag
	) {
		tooltip.accept(Component.translatable("tooltip.avaritia26.blaze_bow.desc")
				.withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
		tooltip.accept(Component.translatable(isBurningMode(stack)
				? "tooltip.avaritia26.blaze_bow.burning_enabled"
				: "tooltip.avaritia26.blaze_bow.burning_disabled").withStyle(ChatFormatting.GRAY));
	}
}
