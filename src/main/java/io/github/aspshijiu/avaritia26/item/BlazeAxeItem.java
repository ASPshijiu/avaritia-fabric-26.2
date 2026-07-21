package io.github.aspshijiu.avaritia26.item;

import java.util.function.Consumer;

import io.github.aspshijiu.avaritia26.registry.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

public final class BlazeAxeItem extends AxeItem {
	public BlazeAxeItem(ToolMaterial material, Properties properties) {
		super(material, 0.0F, 25.0F, properties);
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
					? "tooltip.avaritia26.blaze_axe.smelt_enabled"
					: "tooltip.avaritia26.blaze_axe.smelt_disabled"), true);
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
		tooltip.accept(Component.translatable("tooltip.avaritia26.blaze_axe.desc")
				.withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
		tooltip.accept(Component.translatable(isSmeltingEnabled(stack)
				? "tooltip.avaritia26.blaze_axe.smelt_enabled"
				: "tooltip.avaritia26.blaze_axe.smelt_disabled").withStyle(ChatFormatting.GRAY));
	}
}
