package io.github.aspshijiu.avaritia26.registry;

import java.util.Set;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.block.entity.CompressedChestBlockEntity;
import io.github.aspshijiu.avaritia26.block.entity.EndCraftingTableBlockEntity;
import io.github.aspshijiu.avaritia26.block.entity.ExtremeCraftingTableBlockEntity;
import io.github.aspshijiu.avaritia26.block.entity.InfinityChestBlockEntity;
import io.github.aspshijiu.avaritia26.block.entity.NetherCraftingTableBlockEntity;
import io.github.aspshijiu.avaritia26.block.entity.NeutronCollectorBlockEntity;
import io.github.aspshijiu.avaritia26.block.entity.NeutronCompressorBlockEntity;
import io.github.aspshijiu.avaritia26.block.entity.SculkCraftingTableBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public final class ModBlockEntities {
	public static final BlockEntityType<CompressedChestBlockEntity> COMPRESSED_CHEST = Registry.register(
			BuiltInRegistries.BLOCK_ENTITY_TYPE,
			Avaritia26.id("compressed_chest"),
			new BlockEntityType<>(CompressedChestBlockEntity::new, Set.of(ModBlocks.COMPRESSED_CHEST))
	);
	public static final BlockEntityType<NeutronCollectorBlockEntity> NEUTRON_COLLECTOR = Registry.register(
			BuiltInRegistries.BLOCK_ENTITY_TYPE,
			Avaritia26.id("neutron_collector"),
			new BlockEntityType<>(NeutronCollectorBlockEntity::new, Set.of(
					ModBlocks.NEUTRON_COLLECTOR,
					ModBlocks.DENSE_NEUTRON_COLLECTOR,
					ModBlocks.DENSER_NEUTRON_COLLECTOR,
					ModBlocks.DENSEST_NEUTRON_COLLECTOR
			))
	);
	public static final BlockEntityType<NeutronCompressorBlockEntity> NEUTRON_COMPRESSOR = Registry.register(
			BuiltInRegistries.BLOCK_ENTITY_TYPE,
			Avaritia26.id("neutron_compressor"),
			new BlockEntityType<>(NeutronCompressorBlockEntity::new, Set.of(
					ModBlocks.NEUTRON_COMPRESSOR,
					ModBlocks.DENSE_NEUTRON_COMPRESSOR,
					ModBlocks.DENSER_NEUTRON_COMPRESSOR,
					ModBlocks.DENSEST_NEUTRON_COMPRESSOR
			))
	);
	public static final BlockEntityType<ExtremeCraftingTableBlockEntity> EXTREME_CRAFTING_TABLE = Registry.register(
			BuiltInRegistries.BLOCK_ENTITY_TYPE,
			Avaritia26.id("extreme_crafting_table"),
			new BlockEntityType<>(ExtremeCraftingTableBlockEntity::new, Set.of(ModBlocks.EXTREME_CRAFTING_TABLE))
	);
	public static final BlockEntityType<SculkCraftingTableBlockEntity> SCULK_CRAFTING_TABLE = Registry.register(
			BuiltInRegistries.BLOCK_ENTITY_TYPE,
			Avaritia26.id("sculk_crafting_table"),
			new BlockEntityType<>(SculkCraftingTableBlockEntity::new, Set.of(ModBlocks.SCULK_CRAFTING_TABLE))
	);
	public static final BlockEntityType<NetherCraftingTableBlockEntity> NETHER_CRAFTING_TABLE = Registry.register(
			BuiltInRegistries.BLOCK_ENTITY_TYPE,
			Avaritia26.id("nether_crafting_table"),
			new BlockEntityType<>(NetherCraftingTableBlockEntity::new, Set.of(ModBlocks.NETHER_CRAFTING_TABLE))
	);
	public static final BlockEntityType<EndCraftingTableBlockEntity> END_CRAFTING_TABLE = Registry.register(
			BuiltInRegistries.BLOCK_ENTITY_TYPE,
			Avaritia26.id("end_crafting_table"),
			new BlockEntityType<>(EndCraftingTableBlockEntity::new, Set.of(ModBlocks.END_CRAFTING_TABLE))
	);
	public static final BlockEntityType<InfinityChestBlockEntity> INFINITY_CHEST = Registry.register(
			BuiltInRegistries.BLOCK_ENTITY_TYPE,
			Avaritia26.id("infinity_chest"),
			new BlockEntityType<>(InfinityChestBlockEntity::new, Set.of(ModBlocks.INFINITY_CHEST))
	);

	private ModBlockEntities() {
	}

	public static void initialize() {
	}
}
