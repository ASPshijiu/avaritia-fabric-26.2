package io.github.aspshijiu.avaritia26.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorType;

public final class InfinityArmorItem extends Item {
	private final ArmorType armorType;

	public InfinityArmorItem(Properties properties, ArmorType armorType) {
		super(properties);
		this.armorType = armorType;
	}

	public ArmorType armorType() {
		return armorType;
	}
}
