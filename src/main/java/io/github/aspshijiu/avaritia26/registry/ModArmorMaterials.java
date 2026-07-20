package io.github.aspshijiu.avaritia26.registry;

import java.util.Map;

import io.github.aspshijiu.avaritia26.Avaritia26;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

public final class ModArmorMaterials {
	public static final ResourceKey<EquipmentAsset> INFINITY_ASSET = ResourceKey.create(
			EquipmentAssets.ROOT_ID,
			Avaritia26.id("infinity")
	);

	public static final ArmorMaterial INFINITY = new ArmorMaterial(
			9999,
			Map.of(
					ArmorType.HELMET, 6,
					ArmorType.CHESTPLATE, 16,
					ArmorType.LEGGINGS, 12,
					ArmorType.BOOTS, 6
			),
			1000,
			SoundEvents.ARMOR_EQUIP_IRON,
			1.0F,
			0.0F,
			ItemTags.REPAIRS_NETHERITE_ARMOR,
			INFINITY_ASSET
	);

	private ModArmorMaterials() {
	}
}
