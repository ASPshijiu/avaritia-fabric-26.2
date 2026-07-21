package io.github.aspshijiu.avaritia26.item;

import java.util.function.Consumer;

import io.github.aspshijiu.avaritia26.entity.BladeSlashEntity;
import io.github.aspshijiu.avaritia26.entity.NeutronArrowEntity;
import io.github.aspshijiu.avaritia26.registry.ModDataComponents;
import net.minecraft.ChatFormatting;
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
import net.minecraft.world.level.Level;

public final class CrystalBowItem extends BowItem {
	public CrystalBowItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (player.isShiftKeyDown()) {
			boolean enabled = !isBladeSlashEnabled(stack);
			stack.set(ModDataComponents.CRYSTAL_BLADE_SLASH, enabled);
			if (player instanceof ServerPlayer serverPlayer && serverPlayer.connection != null) {
				serverPlayer.sendSystemMessage(Component.translatable(enabled
						? "tooltip.avaritia26.crystal_bow.blade_slash_enabled"
						: "tooltip.avaritia26.crystal_bow.blade_slash_disabled"), true);
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
		float power = getPowerForTime(chargeTime);
		if (power < 0.1F) {
			return false;
		}
		if (level instanceof ServerLevel serverLevel) {
			Projectile projectile = createProjectile(serverLevel, player, stack, power);
			serverLevel.addFreshEntity(projectile);
			serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(),
					isBladeSlashEnabled(stack) ? SoundEvents.PLAYER_ATTACK_SWEEP : SoundEvents.ARROW_SHOOT,
					SoundSource.PLAYERS, 1.0F, 0.8F + power * 0.4F);
			stack.hurtAndBreak(1, player, player.getUsedItemHand());
			player.awardStat(Stats.ITEM_USED.get(this));
		}
		return true;
	}

	public Projectile createProjectile(ServerLevel level, Player player, ItemStack bow, float power) {
		if (isBladeSlashEnabled(bow)) {
			BladeSlashEntity slash = new BladeSlashEntity(level, player);
			slash.configureForBow(player, power);
			return slash;
		}
		NeutronArrowEntity arrow = new NeutronArrowEntity(level, player, bow);
		arrow.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
		arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 0.0F);
		return arrow;
	}

	public static boolean isBladeSlashEnabled(ItemStack stack) {
		return stack.getOrDefault(ModDataComponents.CRYSTAL_BLADE_SLASH, false);
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
		tooltip.accept(Component.translatable("tooltip.avaritia26.crystal_bow.desc")
				.withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
		tooltip.accept(Component.translatable(isBladeSlashEnabled(stack)
				? "tooltip.avaritia26.crystal_bow.blade_slash_enabled"
				: "tooltip.avaritia26.crystal_bow.blade_slash_disabled").withStyle(ChatFormatting.GRAY));
	}
}
