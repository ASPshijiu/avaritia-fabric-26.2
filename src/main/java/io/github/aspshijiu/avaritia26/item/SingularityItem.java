package io.github.aspshijiu.avaritia26.item;

import java.util.List;
import java.util.function.Consumer;

import io.github.aspshijiu.avaritia26.registry.ModDataComponents;
import io.github.aspshijiu.avaritia26.registry.ModItems;
import io.github.aspshijiu.avaritia26.singularity.SingularityDefinition;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.item.component.TooltipDisplay;

public final class SingularityItem extends Item {
	public SingularityItem(Properties properties) {
		super(properties);
	}

	public static ItemStack createStack(SingularityDefinition definition) {
		ItemStack stack = new ItemStack(ModItems.SINGULARITY);
		stack.set(ModDataComponents.SINGULARITY, definition);
		stack.set(
				DataComponents.CUSTOM_MODEL_DATA,
				new CustomModelData(List.of(), List.of(), List.of(), List.of(
						definition.underlayColor(),
						definition.overlayColor()
				))
		);
		return stack;
	}

	@Override
	public Component getName(ItemStack stack) {
		SingularityDefinition definition = stack.get(ModDataComponents.SINGULARITY);
		return definition == null
				? Component.translatable("item.avaritia26.singularity.empty")
				: Component.translatable(definition.displayName());
	}

	@Override
	public void appendHoverText(
			ItemStack stack,
			TooltipContext context,
			TooltipDisplay display,
			Consumer<Component> tooltip,
			TooltipFlag flag
	) {
		SingularityDefinition definition = stack.get(ModDataComponents.SINGULARITY);
		if (definition != null && flag.isAdvanced()) {
			tooltip.accept(Component.literal(definition.name().toString()).withStyle(ChatFormatting.DARK_GRAY));
		}
	}
}
