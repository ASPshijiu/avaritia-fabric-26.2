package io.github.aspshijiu.avaritia26.item;

import java.util.function.Consumer;

import io.github.aspshijiu.avaritia26.registry.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

public final class BlazePickaxeItem extends Item {
	public BlazePickaxeItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		if (!player.isShiftKeyDown()) {
			return InteractionResult.PASS;
		}
		ItemStack stack = player.getItemInHand(hand);
		boolean enabled = !isSmeltingEnabled(stack);
		stack.set(ModDataComponents.BLAZE_TOOL_MODE, enabled);
		if (player instanceof ServerPlayer serverPlayer && serverPlayer.connection != null) {
			serverPlayer.sendSystemMessage(Component.translatable(enabled
					? "tooltip.avaritia26.blaze_pickaxe.smelt_enabled"
					: "tooltip.avaritia26.blaze_pickaxe.smelt_disabled"), true);
		}
		return InteractionResult.SUCCESS;
	}

	public static boolean isSmeltingEnabled(ItemStack stack) {
		return stack.getOrDefault(ModDataComponents.BLAZE_TOOL_MODE, false);
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
		tooltip.accept(Component.translatable("tooltip.avaritia26.blaze_pickaxe.desc")
				.withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
		tooltip.accept(Component.translatable(isSmeltingEnabled(stack)
				? "tooltip.avaritia26.blaze_pickaxe.smelt_enabled"
				: "tooltip.avaritia26.blaze_pickaxe.smelt_disabled").withStyle(ChatFormatting.GRAY));
	}
}
