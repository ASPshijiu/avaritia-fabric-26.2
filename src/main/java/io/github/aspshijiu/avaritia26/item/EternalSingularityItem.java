package io.github.aspshijiu.avaritia26.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public final class EternalSingularityItem extends Item {
	public EternalSingularityItem(Properties properties) {
		super(properties);
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return true;
	}
}
