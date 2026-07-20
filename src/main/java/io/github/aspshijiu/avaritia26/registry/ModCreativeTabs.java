package io.github.aspshijiu.avaritia26.registry;

import io.github.aspshijiu.avaritia26.Avaritia26;
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
				output.accept(ModItems.DIAMOND_LATTICE);
				output.accept(ModItems.CRYSTAL_MATRIX_INGOT);
				output.accept(ModItems.NEUTRON_PILE);
				output.accept(ModItems.NEUTRON_NUGGET);
				output.accept(ModItems.NEUTRON_INGOT);
				output.accept(ModItems.RECORD_FRAGMENT);
				output.accept(ModItems.ENDEST_PEARL);
				output.accept(ModItems.MATTER_CLUSTER);
				output.accept(ModItems.FULL_MATTER_CLUSTER);
				output.accept(ModItems.ULTIMATE_STEW);
				output.accept(ModItems.COSMIC_MEATBALLS);
				output.accept(ModBlocks.CRYSTAL_MATRIX_ITEM);
				output.accept(ModBlocks.NEUTRON_ITEM);
				output.accept(ModBlocks.COMPRESSED_CRAFTING_TABLE_ITEM);
				output.accept(ModBlocks.DOUBLE_COMPRESSED_CRAFTING_TABLE_ITEM);
				output.accept(ModBlocks.EXTREME_CRAFTING_TABLE_ITEM);
			})
			.build();

	private ModCreativeTabs() {
	}

	public static void initialize() {
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MAIN_KEY, MAIN);
	}
}
