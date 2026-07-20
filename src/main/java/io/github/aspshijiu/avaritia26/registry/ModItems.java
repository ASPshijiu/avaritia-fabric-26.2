package io.github.aspshijiu.avaritia26.registry;

import java.util.function.Function;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.item.EndestPearlItem;
import io.github.aspshijiu.avaritia26.item.EternalSingularityItem;
import io.github.aspshijiu.avaritia26.item.InfinityHoeItem;
import io.github.aspshijiu.avaritia26.item.InfinitySwordItem;
import io.github.aspshijiu.avaritia26.item.MatterClusterItem;
import io.github.aspshijiu.avaritia26.item.SingularityItem;
import io.github.aspshijiu.avaritia26.item.SkullFireSwordItem;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Weapon;

public final class ModItems {
	public static final ResourceKey<Item> INFINITY_HOE_KEY = key("infinity_hoe");
	public static final Item INFINITY_HOE = register(
			INFINITY_HOE_KEY,
			InfinityHoeItem::new,
			new Item.Properties()
					.rarity(Rarity.EPIC)
					.stacksTo(1)
					.fireResistant()
					.enchantable(200)
					.attributes(infinityToolAttributes(19.0, -3.0))
					.component(DataComponents.WEAPON, new Weapon(0))
	);
	public static final ResourceKey<Item> INFINITY_SWORD_KEY = key("infinity_sword");
	public static final Item INFINITY_SWORD = register(
			INFINITY_SWORD_KEY,
			InfinitySwordItem::new,
			new Item.Properties()
					.rarity(Rarity.EPIC)
					.stacksTo(1)
					.fireResistant()
					.enchantable(200)
					.attributes(infinityToolAttributes(99.0, -2.4))
					.component(DataComponents.WEAPON, new Weapon(0))
	);
	public static final ResourceKey<Item> SKULL_FIRE_SWORD_KEY = key("skull_fire_sword");
	public static final Item SKULL_FIRE_SWORD = register(
			SKULL_FIRE_SWORD_KEY,
			SkullFireSwordItem::new,
			new Item.Properties()
					.rarity(Rarity.EPIC)
					.sword(ToolMaterial.DIAMOND, 3.0F, -2.4F)
	);
	public static final ResourceKey<Item> BLAZE_CUBE_KEY = key("blaze_cube");
	public static final Item BLAZE_CUBE = register(
			BLAZE_CUBE_KEY,
			Item::new,
			new Item.Properties().rarity(Rarity.UNCOMMON).fireResistant()
	);
	public static final ResourceKey<Item> ETERNAL_SINGULARITY_KEY = key("eternal_singularity");
	public static final Item ETERNAL_SINGULARITY = register(
			ETERNAL_SINGULARITY_KEY,
			EternalSingularityItem::new,
			new Item.Properties().rarity(Rarity.RARE).stacksTo(8).fireResistant()
	);
	public static final ResourceKey<Item> INFINITY_CATALYST_KEY = key("infinity_catalyst");
	public static final Item INFINITY_CATALYST = register(
			INFINITY_CATALYST_KEY,
			Item::new,
			new Item.Properties().rarity(Rarity.EPIC).fireResistant()
	);
	public static final ResourceKey<Item> INFINITY_INGOT_KEY = key("infinity_ingot");
	public static final Item INFINITY_INGOT = register(
			INFINITY_INGOT_KEY,
			Item::new,
			new Item.Properties().rarity(Rarity.EPIC).fireResistant()
	);
	public static final ResourceKey<Item> SINGULARITY_KEY = key("singularity");
	public static final Item SINGULARITY = register(
			SINGULARITY_KEY,
			SingularityItem::new,
			new Item.Properties().rarity(Rarity.UNCOMMON)
	);
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
	public static final ResourceKey<Item> ENDEST_PEARL_KEY = key("endest_pearl");
	public static final Item ENDEST_PEARL = register(
			ENDEST_PEARL_KEY,
			EndestPearlItem::new,
			new Item.Properties().rarity(Rarity.EPIC).stacksTo(16).useCooldown(1.5F)
	);
	public static final ResourceKey<Item> MATTER_CLUSTER_KEY = key("matter_cluster");
	public static final Item MATTER_CLUSTER = register(
			MATTER_CLUSTER_KEY,
			MatterClusterItem::new,
			new Item.Properties().rarity(Rarity.RARE).stacksTo(1)
	);
	public static final ResourceKey<Item> FULL_MATTER_CLUSTER_KEY = key("full_matter_cluster");
	public static final Item FULL_MATTER_CLUSTER = register(
			FULL_MATTER_CLUSTER_KEY,
			Item::new,
			new Item.Properties().rarity(Rarity.RARE).stacksTo(1)
	);
	public static final ResourceKey<Item> ULTIMATE_STEW_KEY = key("ultimate_stew");
	public static final Item ULTIMATE_STEW = register(
			ULTIMATE_STEW_KEY,
			Item::new,
			new Item.Properties()
					.rarity(Rarity.EPIC)
					.food(ModFoods.ULTIMATE_STEW, ModFoods.ULTIMATE_STEW_CONSUMABLE)
	);
	public static final ResourceKey<Item> COSMIC_MEATBALLS_KEY = key("cosmic_meatballs");
	public static final Item COSMIC_MEATBALLS = register(
			COSMIC_MEATBALLS_KEY,
			Item::new,
			new Item.Properties()
					.rarity(Rarity.EPIC)
					.food(ModFoods.COSMIC_MEATBALLS, ModFoods.COSMIC_MEATBALLS_CONSUMABLE)
	);

	private ModItems() {
	}

	public static void initialize() {
	}

	private static ItemAttributeModifiers infinityToolAttributes(double attackDamage, double attackSpeed) {
		return ItemAttributeModifiers.builder()
				.add(
						Attributes.ATTACK_DAMAGE,
						new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, attackDamage, AttributeModifier.Operation.ADD_VALUE),
						EquipmentSlotGroup.MAINHAND
				)
				.add(
						Attributes.ATTACK_SPEED,
						new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, attackSpeed, AttributeModifier.Operation.ADD_VALUE),
						EquipmentSlotGroup.MAINHAND
				)
				.build();
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
