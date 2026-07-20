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
	public static final ResourceKey<Item> NEUTRON_PILE_KEY = key("neutron_pile");
	public static final Item NEUTRON_PILE = register(
			NEUTRON_PILE_KEY,
			Item::new,
			new Item.Properties().rarity(Rarity.UNCOMMON)
	);
	public static final ResourceKey<Item> NEUTRON_NUGGET_KEY = key("neutron_nugget");
	public static final Item NEUTRON_NUGGET = register(
			NEUTRON_NUGGET_KEY,
			Item::new,
			new Item.Properties().rarity(Rarity.RARE)
	);
	public static final ResourceKey<Item> NEUTRON_INGOT_KEY = key("neutron_ingot");
	public static final Item NEUTRON_INGOT = register(
			NEUTRON_INGOT_KEY,
			Item::new,
			new Item.Properties().rarity(Rarity.EPIC)
	);
	public static final ResourceKey<Item> RECORD_FRAGMENT_KEY = key("record_fragment");
	public static final Item RECORD_FRAGMENT = register(
			RECORD_FRAGMENT_KEY,
			Item::new,
			new Item.Properties().rarity(Rarity.RARE)
	);
	public static final ResourceKey<Item> ULTIMATE_STEW_KEY = key("ultimate_stew");
	public static final Item ULTIMATE_STEW = register(
			ULTIMATE_STEW_KEY,
			Item::new,
			new Item.Properties()
					.rarity(Rarity.EPIC)
					.food(ModFoods.ULTIMATE_STEW, ModFoods.ULTIMATE_STEW_CONSUMABLE)
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
