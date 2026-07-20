package io.github.aspshijiu.avaritia26.item;

import java.util.function.Consumer;

import io.github.aspshijiu.avaritia26.block.entity.SideConfigurable;
import io.github.aspshijiu.avaritia26.component.SideConfiguration;
import io.github.aspshijiu.avaritia26.registry.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;

public final class SideConfigurationCardItem extends Item {
	public SideConfigurationCardItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Player player = context.getPlayer();
		if (player == null) {
			return InteractionResult.PASS;
		}
		SideConfigurable machine = context.getLevel().getBlockEntity(context.getClickedPos()) instanceof SideConfigurable configurable
				? configurable
				: null;
		if (context.getLevel().isClientSide()) {
			return machine != null || player.isShiftKeyDown() ? InteractionResult.SUCCESS : InteractionResult.PASS;
		}
		ItemStack stack = context.getItemInHand();
		if (machine != null && player.isShiftKeyDown()) {
			stack.set(ModDataComponents.SIDE_CONFIGURATION, machine.getSideConfiguration());
			message(player, "tooltip.avaritia26.side_config_card.read_success");
			return InteractionResult.SUCCESS_SERVER;
		}
		if (machine != null) {
			SideConfiguration configuration = stack.get(ModDataComponents.SIDE_CONFIGURATION);
			if (configuration == null) {
				message(player, "tooltip.avaritia26.side_config_card.no_config_to_apply");
				return InteractionResult.FAIL;
			}
			machine.setSideConfiguration(configuration);
			message(player, "tooltip.avaritia26.side_config_card.apply_success");
			return InteractionResult.SUCCESS_SERVER;
		}
		if (player.isShiftKeyDown()) {
			if (stack.remove(ModDataComponents.SIDE_CONFIGURATION) != null) {
				message(player, "tooltip.avaritia26.side_config_card.cleared");
			} else {
				message(player, "tooltip.avaritia26.side_config_card.already_empty");
			}
			return InteractionResult.SUCCESS_SERVER;
		}
		return InteractionResult.PASS;
	}

	@Override
	public void appendHoverText(
			ItemStack stack,
			TooltipContext context,
			TooltipDisplay display,
			Consumer<Component> tooltip,
			TooltipFlag flag
	) {
		boolean configured = stack.has(ModDataComponents.SIDE_CONFIGURATION);
		tooltip.accept(Component.translatable(configured
				? "tooltip.avaritia26.side_config_card.has_config"
				: "tooltip.avaritia26.side_config_card.no_config").withStyle(ChatFormatting.GRAY));
		tooltip.accept(Component.translatable(configured
				? "tooltip.avaritia26.side_config_card.instruction_right_click"
				: "tooltip.avaritia26.side_config_card.instruction_shift_right_click").withStyle(ChatFormatting.GRAY));
		tooltip.accept(Component.translatable("tooltip.avaritia26.side_config_card.instruction_shift_air")
				.withStyle(ChatFormatting.GRAY));
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return stack.has(ModDataComponents.SIDE_CONFIGURATION);
	}

	private static void message(Player player, String key) {
		if (player instanceof ServerPlayer serverPlayer && serverPlayer.connection != null) {
			serverPlayer.sendSystemMessage(Component.translatable(key), true);
		}
	}
}
