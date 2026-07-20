package io.github.aspshijiu.avaritia26.registry;

import java.util.List;
import java.util.function.Function;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.item.EndestPearlItem;
import io.github.aspshijiu.avaritia26.item.EternalSingularityItem;
import io.github.aspshijiu.avaritia26.item.InfinityAxeItem;
import io.github.aspshijiu.avaritia26.item.InfinityArmorItem;
import io.github.aspshijiu.avaritia26.item.InfinityBowItem;
import io.github.aspshijiu.avaritia26.item.InfinityHoeItem;
import io.github.aspshijiu.avaritia26.item.InfinityPickaxeItem;
import io.github.aspshijiu.avaritia26.item.InfinityShovelItem;
import io.github.aspshijiu.avaritia26.item.InfinitySwordItem;
import io.github.aspshijiu.avaritia26.item.MatterClusterItem;
import io.github.aspshijiu.avaritia26.item.SingularityItem;
import io.github.aspshijiu.avaritia26.item.SkullFireSwordItem;
import io.github.aspshijiu.avaritia26.item.TooltipItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;

public final class ModItems {
	public static final ResourceKey<Item> INFINITY_HELMET_KEY = key("infinity_helmet");
	public static final Item INFINITY_HELMET = register(
			INFINITY_HELMET_KEY,
			properties -> new InfinityArmorItem(properties, ArmorType.HELMET),
			infinityArmorProperties(ArmorType.HELMET)
	);
	public static final ResourceKey<Item> INFINITY_CHESTPLATE_KEY = key("infinity_chestplate");
	public static final Item INFINITY_CHESTPLATE = register(
			INFINITY_CHESTPLATE_KEY,
			properties -> new InfinityArmorItem(properties, ArmorType.CHESTPLATE),
			infinityArmorProperties(ArmorType.CHESTPLATE)
	);
	public static final ResourceKey<Item> INFINITY_PANTS_KEY = key("infinity_pants");
	public static final Item INFINITY_PANTS = register(
			INFINITY_PANTS_KEY,
			properties -> new InfinityArmorItem(properties, ArmorType.LEGGINGS),
			infinityArmorProperties(ArmorType.LEGGINGS)
	);
	public static final ResourceKey<Item> INFINITY_BOOTS_KEY = key("infinity_boots");
	public static final Item INFINITY_BOOTS = register(
			INFINITY_BOOTS_KEY,
			properties -> new InfinityArmorItem(properties, ArmorType.BOOTS),
			infinityArmorProperties(ArmorType.BOOTS)
	);
	public static final ResourceKey<Item> INFINITY_BOW_KEY = key("infinity_bow");
	public static final Item INFINITY_BOW = register(
			INFINITY_BOW_KEY,
			InfinityBowItem::new,
			new Item.Properties()
					.rarity(Rarity.EPIC)
					.stacksTo(1)
					.fireResistant()
					.enchantable(1)
	);
	public static final ResourceKey<Item> INFINITY_AXE_KEY = key("infinity_axe");
	public static final Item INFINITY_AXE = register(
			INFINITY_AXE_KEY,
			InfinityAxeItem::new,
			new Item.Properties()
					.rarity(Rarity.EPIC)
					.stacksTo(1)
					.fireResistant()
					.enchantable(200)
					.attributes(infinityToolAttributes(39.0, -3.0))
					.component(DataComponents.WEAPON, new Weapon(0))
	);
	public static final ResourceKey<Item> INFINITY_SHOVEL_KEY = key("infinity_shovel");
	public static final Item INFINITY_SHOVEL = register(
			INFINITY_SHOVEL_KEY,
			InfinityShovelItem::new,
			new Item.Properties()
					.rarity(Rarity.EPIC)
					.stacksTo(1)
					.fireResistant()
					.enchantable(200)
					.attributes(infinityToolAttributes(6.0, -3.0))
					.component(DataComponents.WEAPON, new Weapon(0))
	);
	public static final ResourceKey<Item> INFINITY_PICKAXE_KEY = key("infinity_pickaxe");
	public static final Item INFINITY_PICKAXE = register(
			INFINITY_PICKAXE_KEY,
			InfinityPickaxeItem::new,
			new Item.Properties()
					.rarity(Rarity.EPIC)
					.stacksTo(1)
					.fireResistant()
					.enchantable(200)
					.attributes(infinityToolAttributes(5.0, -2.8))
					.component(DataComponents.WEAPON, new Weapon(0))
	);
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
	public static final ResourceKey<Item> INFINITY_NUGGET_KEY = key("infinity_nugget");
	public static final Item INFINITY_NUGGET = register(
			INFINITY_NUGGET_KEY,
			properties -> new TooltipItem(properties, "tooltip.avaritia26.infinity_nugget"),
			new Item.Properties().rarity(Rarity.EPIC).fireResistant()
	);
	public static final ResourceKey<Item> ENHANCEMENT_CORE_KEY = key("enhancement_core");
	public static final Item ENHANCEMENT_CORE = register(
			ENHANCEMENT_CORE_KEY,
			properties -> new TooltipItem(properties, "tooltip.avaritia26.enhancement_core"),
			new Item.Properties().rarity(Rarity.EPIC).fireResistant()
	);
	public static final ResourceKey<Item> UPGRADE_SMITHING_TEMPLATE_KEY = key("upgrade_smithing_template");
	public static final Item UPGRADE_SMITHING_TEMPLATE = register(
			UPGRADE_SMITHING_TEMPLATE_KEY,
			ModItems::upgradeSmithingTemplate,
			new Item.Properties()
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
	public static final ResourceKey<Item> NEUTRON_GEAR_KEY = key("neutron_gear");
	public static final Item NEUTRON_GEAR = register(
			NEUTRON_GEAR_KEY,
			Item::new,
			new Item.Properties().rarity(Rarity.RARE).fireResistant()
	);
	public static final ResourceKey<Item> RECORD_FRAGMENT_KEY = key("record_fragment");
	public static final Item RECORD_FRAGMENT = register(
			RECORD_FRAGMENT_KEY,
			Item::new,
			new Item.Properties().rarity(Rarity.RARE)
	);
	public static final ResourceKey<Item> STAR_FUEL_KEY = key("star_fuel");
	public static final Item STAR_FUEL = register(
			STAR_FUEL_KEY,
			properties -> new TooltipItem(properties, "tooltip.avaritia26.star_fuel"),
			new Item.Properties().rarity(Rarity.RARE).stacksTo(16)
	);
	public static final ResourceKey<Item> REFINED_COAL_KEY = key("refined_coal");
	public static final Item REFINED_COAL = register(
			REFINED_COAL_KEY,
			properties -> new TooltipItem(properties, "tooltip.avaritia26.refined_coal"),
			new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(32)
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

	private static Item.Properties infinityArmorProperties(ArmorType type) {
		ItemAttributeModifiers attributes = ModArmorMaterials.INFINITY.createAttributes(type);
		if (type == ArmorType.BOOTS) {
			attributes = attributes
					.withModifierAdded(
							Attributes.MOVEMENT_SPEED,
							new AttributeModifier(Avaritia26.id("infinity_boots_speed"), 0.15, AttributeModifier.Operation.ADD_VALUE),
							EquipmentSlotGroup.FEET
					)
					.withModifierAdded(
							Attributes.JUMP_STRENGTH,
							new AttributeModifier(Avaritia26.id("infinity_boots_jump"), 0.4, AttributeModifier.Operation.ADD_VALUE),
							EquipmentSlotGroup.FEET
					)
					.withModifierAdded(
							Attributes.STEP_HEIGHT,
							new AttributeModifier(Avaritia26.id("infinity_boots_step"), 0.4625, AttributeModifier.Operation.ADD_VALUE),
							EquipmentSlotGroup.FEET
					);
		}
		Equippable equippable = Equippable.builder(type.getSlot())
				.setEquipSound(ModArmorMaterials.INFINITY.equipSound())
				.setAsset(ModArmorMaterials.INFINITY_ASSET)
				.setDamageOnHurt(false)
				.build();
		return new Item.Properties()
				.rarity(Rarity.EPIC)
				.stacksTo(1)
				.fireResistant()
				.enchantable(1000)
				.attributes(attributes)
				.component(DataComponents.EQUIPPABLE, equippable);
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

	private static SmithingTemplateItem upgradeSmithingTemplate(Item.Properties properties) {
		Component name = Component.translatable("item.avaritia26.upgrade_smithing_template");
		return new SmithingTemplateItem(
				name.copy().withStyle(ChatFormatting.BLUE),
				name.copy().withStyle(ChatFormatting.GRAY),
				name,
				name,
				List.of(
						Identifier.withDefaultNamespace("container/slot/sword"),
						Identifier.withDefaultNamespace("container/slot/pickaxe"),
						Identifier.withDefaultNamespace("container/slot/axe"),
						Identifier.withDefaultNamespace("container/slot/hoe"),
						Identifier.withDefaultNamespace("container/slot/shovel")
				),
				List.of(Identifier.withDefaultNamespace("container/slot/ingot")),
				properties
		);
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
