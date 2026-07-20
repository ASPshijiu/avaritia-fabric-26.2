package io.github.aspshijiu.avaritia26.crafting;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record ExtremeSmithingInput(
		ItemStack template,
		ItemStack base,
		ItemStack addition1,
		ItemStack addition2,
		ItemStack addition3
) implements RecipeInput {
	@Override
	public ItemStack getItem(int slot) {
		return switch (slot) {
			case 0 -> template;
			case 1 -> base;
			case 2 -> addition1;
			case 3 -> addition2;
			case 4 -> addition3;
			default -> throw new IllegalArgumentException("强化锻造配方没有槽位 " + slot);
		};
	}

	@Override
	public int size() {
		return 5;
	}
}
