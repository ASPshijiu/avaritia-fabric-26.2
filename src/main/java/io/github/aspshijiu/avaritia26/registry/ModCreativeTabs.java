package io.github.aspshijiu.avaritia26.registry;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.item.SingularityItem;
import io.github.aspshijiu.avaritia26.singularity.SingularityManager;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public final class ModCreativeTabs {
	public static final ResourceKey<CreativeModeTab> MAIN_KEY = ResourceKey.create(
			BuiltInRegistries.CREATIVE_MODE_TAB.key(),
			Avaritia26.id("main")
	);

	private static final CreativeModeTab MAIN = FabricCreativeModeTab.builder()
			.icon(() -> new ItemStack(ModItems.DIAMOND_LATTICE))
			.title(Component.translatable("itemGroup.avaritia26.main"))
			.displayItems((parameters, output) -> {
				output.accept(ModItems.NEUTRON_RING);
				output.accept(ModItems.INFINITY_TOTEM);
				output.accept(ModItems.INFINITY_RING);
				output.accept(ModItems.INFINITY_UMBRELLA);
				output.accept(ModItems.INFINITY_CLOCK);
				output.accept(ModItems.SIDE_CONFIG_CARD);
				output.accept(ModItems.INFINITY_BUCKET);
				output.accept(ModItems.INFINITY_CROSSBOW);
				output.accept(ModItems.INFINITY_SHIELD);
				output.accept(ModItems.INFINITY_TRIDENT);
				output.accept(ModItems.INFINITY_MACE);
				output.accept(ModItems.CRYSTAL_SWORD);
				output.accept(ModItems.CRYSTAL_HOE);
				output.accept(ModItems.CRYSTAL_PICKAXE);
				output.accept(ModItems.CRYSTAL_SHOVEL);
				output.accept(ModItems.INFINITY_HELMET);
				output.accept(ModItems.INFINITY_CHESTPLATE);
				output.accept(ModItems.INFINITY_PANTS);
				output.accept(ModItems.INFINITY_BOOTS);
				output.accept(ModItems.INFINITY_BOW);
				output.accept(ModItems.INFINITY_AXE);
				output.accept(ModItems.INFINITY_SHOVEL);
				output.accept(ModItems.INFINITY_PICKAXE);
				output.accept(ModItems.INFINITY_HOE);
				output.accept(ModItems.INFINITY_SWORD);
				output.accept(ModItems.SKULL_FIRE_SWORD);
				output.accept(ModItems.BLAZE_CUBE);
				output.accept(ModBlocks.BLAZE_CUBE_BLOCK_ITEM);
				SingularityManager.values().forEach(definition -> output.accept(SingularityItem.createStack(definition)));
				output.accept(ModItems.ETERNAL_SINGULARITY);
				output.accept(ModItems.INFINITY_CATALYST);
				output.accept(ModItems.INFINITY_INGOT);
				output.accept(ModItems.INFINITY_NUGGET);
				output.accept(ModItems.ENHANCEMENT_CORE);
				output.accept(ModItems.UPGRADE_SMITHING_TEMPLATE);
				output.accept(ModBlocks.INFINITY_ITEM);
				output.accept(ModItems.DIAMOND_LATTICE);
				output.accept(ModBlocks.DIAMOND_LATTICE_BLOCK_ITEM);
				output.accept(ModItems.CRYSTAL_MATRIX_INGOT);
				output.accept(ModItems.NEUTRON_PILE);
				output.accept(ModItems.NEUTRON_NUGGET);
				output.accept(ModItems.NEUTRON_INGOT);
				output.accept(ModItems.NEUTRON_GEAR);
				output.accept(ModItems.RECORD_FRAGMENT);
				output.accept(ModItems.STAR_FUEL);
				output.accept(ModItems.INFINITY_UPGRADE);
				output.accept(ModBlocks.STAR_FUEL_BLOCK_ITEM);
				output.accept(ModItems.REFINED_COAL);
				output.accept(ModBlocks.REFINED_COAL_BLOCK_ITEM);
				output.accept(ModItems.ENDEST_PEARL);
				output.accept(ModItems.MATTER_CLUSTER);
				output.accept(ModItems.FULL_MATTER_CLUSTER);
				output.accept(ModItems.ULTIMATE_STEW);
				output.accept(ModItems.COSMIC_MEATBALLS);
				output.accept(ModBlocks.NEUTRON_COLLECTOR_ITEM);
				output.accept(ModBlocks.DENSE_NEUTRON_COLLECTOR_ITEM);
				output.accept(ModBlocks.DENSER_NEUTRON_COLLECTOR_ITEM);
				output.accept(ModBlocks.DENSEST_NEUTRON_COLLECTOR_ITEM);
				output.accept(ModBlocks.NEUTRON_COMPRESSOR_ITEM);
				output.accept(ModBlocks.DENSE_NEUTRON_COMPRESSOR_ITEM);
				output.accept(ModBlocks.DENSER_NEUTRON_COMPRESSOR_ITEM);
				output.accept(ModBlocks.DENSEST_NEUTRON_COMPRESSOR_ITEM);
				output.accept(ModBlocks.CRYSTAL_MATRIX_ITEM);
				output.accept(ModBlocks.NEUTRON_ITEM);
				output.accept(ModBlocks.COMPRESSED_CRAFTING_TABLE_ITEM);
				output.accept(ModBlocks.DOUBLE_COMPRESSED_CRAFTING_TABLE_ITEM);
				output.accept(ModBlocks.SCULK_CRAFTING_TABLE_ITEM);
				output.accept(ModBlocks.NETHER_CRAFTING_TABLE_ITEM);
				output.accept(ModBlocks.END_CRAFTING_TABLE_ITEM);
				output.accept(ModBlocks.EXTREME_CRAFTING_TABLE_ITEM);
				output.accept(ModBlocks.EXTREME_SMITHING_TABLE_ITEM);
				output.accept(ModBlocks.INFINITY_CHEST_ITEM);
				output.accept(ModBlocks.COMPRESSED_CHEST_ITEM);
				output.accept(ModBlocks.SOUL_FARMLAND_ITEM);
			})
			.build();

	private ModCreativeTabs() {
	}

	public static void initialize() {
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MAIN_KEY, MAIN);
	}
}
