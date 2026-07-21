package io.github.aspshijiu.avaritia26.item;

import java.util.function.Consumer;

import io.github.aspshijiu.avaritia26.entity.BladeSlashEntity;
import io.github.aspshijiu.avaritia26.registry.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

public final class CrystalSwordItem extends Item {
	public CrystalSwordItem(Properties properties) {
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
						? "tooltip.avaritia26.crystal_sword.blade_slash_enabled"
						: "tooltip.avaritia26.crystal_sword.blade_slash_disabled"), true);
			}
			return InteractionResult.SUCCESS;
		}
		if (!isBladeSlashEnabled(stack)) {
			return InteractionResult.PASS;
		}
		if (level instanceof ServerLevel serverLevel) {
			BladeSlashEntity slash = new BladeSlashEntity(serverLevel, player);
			serverLevel.addFreshEntity(slash);
			serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(),
					SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 1.0F, 1.0F);
			player.swing(hand);
		}
		return InteractionResult.SUCCESS;
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
		tooltip.accept(Component.translatable("tooltip.avaritia26.crystal_sword.desc")
				.withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
		tooltip.accept(Component.translatable(isBladeSlashEnabled(stack)
				? "tooltip.avaritia26.crystal_sword.blade_slash_enabled"
				: "tooltip.avaritia26.crystal_sword.blade_slash_disabled").withStyle(ChatFormatting.GRAY));
	}
}
