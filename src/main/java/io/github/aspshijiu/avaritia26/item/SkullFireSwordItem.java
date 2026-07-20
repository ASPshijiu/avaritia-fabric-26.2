package io.github.aspshijiu.avaritia26.item;

import java.util.function.Consumer;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

public final class SkullFireSwordItem extends Item {
	public SkullFireSwordItem(Properties properties) {
		super(properties);
	}

	@Override
	public void appendHoverText(
			ItemStack stack,
			TooltipContext context,
			TooltipDisplay display,
			Consumer<Component> tooltipAdder,
			TooltipFlag flag
	) {
		tooltipAdder.accept(
				Component.translatable("tooltip.avaritia26.skull_fire_sword")
						.withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC)
		);
	}
}
