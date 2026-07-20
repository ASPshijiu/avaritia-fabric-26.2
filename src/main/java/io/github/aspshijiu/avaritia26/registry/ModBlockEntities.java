package io.github.aspshijiu.avaritia26.registry;

import java.util.Set;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.block.entity.ExtremeCraftingTableBlockEntity;
import io.github.aspshijiu.avaritia26.block.entity.NeutronCompressorBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public final class ModBlockEntities {
	public static final BlockEntityType<NeutronCompressorBlockEntity> NEUTRON_COMPRESSOR = Registry.register(
			BuiltInRegistries.BLOCK_ENTITY_TYPE,
			Avaritia26.id("neutron_compressor"),
			new BlockEntityType<>(NeutronCompressorBlockEntity::new, Set.of(ModBlocks.NEUTRON_COMPRESSOR))
	);
	public static final BlockEntityType<ExtremeCraftingTableBlockEntity> EXTREME_CRAFTING_TABLE = Registry.register(
			BuiltInRegistries.BLOCK_ENTITY_TYPE,
			Avaritia26.id("extreme_crafting_table"),
			new BlockEntityType<>(ExtremeCraftingTableBlockEntity::new, Set.of(ModBlocks.EXTREME_CRAFTING_TABLE))
	);

	private ModBlockEntities() {
	}

	public static void initialize() {
	}
}
