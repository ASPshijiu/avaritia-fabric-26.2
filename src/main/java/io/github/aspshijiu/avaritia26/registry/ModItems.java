package io.github.aspshijiu.avaritia26.registry;

import java.util.function.Function;

import io.github.aspshijiu.avaritia26.Avaritia26;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public final class ModItems {
	public static final ResourceKey<Item> DIAMOND_LATTICE_KEY = key("diamond_lattice");
	public static final Item DIAMOND_LATTICE = register(
			DIAMOND_LATTICE_KEY,
			Item::new,
			new Item.Properties().rarity(Rarity.UNCOMMON)
	);
	public static final ResourceKey<Item> CRYSTAL_MATRIX_INGOT_KEY = key("crystal_matrix_ingot");
	public static final Item CRYSTAL_MATRIX_INGOT = register(
			CRYSTAL_MATRIX_INGOT_KEY,
			Item::new,
			new Item.Properties().rarity(Rarity.RARE).fireResistant()
	);

	private ModItems() {
	}

	public static void initialize() {
	}

	private static ResourceKey<Item> key(String path) {
		return ResourceKey.create(BuiltInRegistries.ITEM.key(), Avaritia26.id(path));
	}

	private static <T extends Item> T register(
			ResourceKey<Item> key,
			Function<Item.Properties, T> factory,
			Item.Properties properties
	) {
		T item = factory.apply(properties.setId(key));
		return Registry.register(BuiltInRegistries.ITEM, key, item);
	}
}
