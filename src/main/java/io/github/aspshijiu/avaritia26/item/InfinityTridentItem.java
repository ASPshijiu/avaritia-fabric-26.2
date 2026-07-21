package io.github.aspshijiu.avaritia26.item;

import java.util.function.Consumer;

import io.github.aspshijiu.avaritia26.entity.InfinityThrownTridentEntity;
import io.github.aspshijiu.avaritia26.registry.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public final class InfinityTridentItem extends TridentItem {
	public static final int LOYALTY = 0;
	public static final int RIPTIDE = 1;
	private static final int CHARGE_TICKS = 10;

	public InfinityTridentItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (player.isShiftKeyDown()) {
			int mode = getMode(stack) == LOYALTY ? RIPTIDE : LOYALTY;
			stack.set(ModDataComponents.INFINITY_TRIDENT_MODE, mode);
			if (player instanceof ServerPlayer serverPlayer && serverPlayer.connection != null) {
				serverPlayer.sendSystemMessage(Component.translatable(mode == LOYALTY
						? "tooltip.avaritia26.infinity_trident.loyalty"
						: "tooltip.avaritia26.infinity_trident.riptide"), true);
			}
			return InteractionResult.SUCCESS;
		}
		player.startUsingItem(hand);
		return InteractionResult.CONSUME;
	}

	@Override
	public boolean releaseUsing(ItemStack stack, Level level, LivingEntity user, int remainingUseDuration) {
		if (!(user instanceof Player player) || getUseDuration(stack, user) - remainingUseDuration < CHARGE_TICKS) {
			return false;
		}
		player.awardStat(Stats.ITEM_USED.get(this));
		if (getMode(stack) == RIPTIDE) {
			riptide(level, player, stack);
		} else if (level instanceof ServerLevel serverLevel) {
			throwTrident(serverLevel, player, stack);
		}
		return true;
	}

	public static int getMode(ItemStack stack) {
		return stack.getOrDefault(ModDataComponents.INFINITY_TRIDENT_MODE, LOYALTY);
	}

	@Override
	public void appendHoverText(
			ItemStack stack,
			TooltipContext context,
			TooltipDisplay display,
			Consumer<Component> tooltip,
			TooltipFlag flag
	) {
		tooltip.accept(Component.translatable(getMode(stack) == LOYALTY
				? "tooltip.avaritia26.infinity_trident.loyalty"
				: "tooltip.avaritia26.infinity_trident.riptide").withStyle(ChatFormatting.GRAY));
	}

	private static void throwTrident(ServerLevel level, Player player, ItemStack stack) {
		InfinityThrownTridentEntity trident = new InfinityThrownTridentEntity(level, player, stack);
		trident.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);
		trident.pickup = player.getAbilities().instabuild
				? AbstractArrow.Pickup.CREATIVE_ONLY : AbstractArrow.Pickup.ALLOWED;
		level.addFreshEntity(trident);
		level.playSeededSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.TRIDENT_THROW,
				SoundSource.PLAYERS, 1.0F, 1.0F, level.getRandom().nextLong());
		if (!player.getAbilities().instabuild) {
			player.setItemInHand(player.getUsedItemHand(), ItemStack.EMPTY);
		}
	}

	private static void riptide(Level level, Player player, ItemStack stack) {
		float yaw = player.getYRot() * Mth.DEG_TO_RAD;
		float pitch = player.getXRot() * Mth.DEG_TO_RAD;
		float x = -Mth.sin(yaw) * Mth.cos(pitch);
		float y = -Mth.sin(pitch);
		float z = Mth.cos(yaw) * Mth.cos(pitch);
		float length = Mth.sqrt(x * x + y * y + z * z);
		float speed = 4.5F / length;
		player.push(x * speed, y * speed, z * speed);
		player.startAutoSpinAttack(20, 100.0F, stack);
		if (player.onGround()) {
			player.move(MoverType.SELF, new Vec3(0.0, 1.2, 0.0));
		}
		level.playSound(null, player, SoundEvents.TRIDENT_RIPTIDE_3.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
	}
}
