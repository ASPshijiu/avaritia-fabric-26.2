package io.github.aspshijiu.avaritia26.registry;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.block.CompressedChestBlock;
import io.github.aspshijiu.avaritia26.block.CompressedCraftingTableBlock;
import io.github.aspshijiu.avaritia26.block.EndCraftingTableBlock;
import io.github.aspshijiu.avaritia26.block.EndlessCakeBlock;
import io.github.aspshijiu.avaritia26.block.ExtremeCraftingTableBlock;
import io.github.aspshijiu.avaritia26.block.ExtremeAnvilBlock;
import io.github.aspshijiu.avaritia26.block.ExtremeSmithingTableBlock;
import io.github.aspshijiu.avaritia26.block.InfinityChestBlock;
import io.github.aspshijiu.avaritia26.block.NetherCraftingTableBlock;
import io.github.aspshijiu.avaritia26.block.NeutronCollectorBlock;
import io.github.aspshijiu.avaritia26.block.NeutronCompressorBlock;
import io.github.aspshijiu.avaritia26.block.SculkCraftingTableBlock;
import io.github.aspshijiu.avaritia26.block.SoulFarmlandBlock;
import io.github.aspshijiu.avaritia26.item.EndlessCakeBlockItem;
import net.fabricmc.fabric.api.registry.FuelValueEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.MagmaBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public final class ModBlocks {
	private static final SoundType END_PORTAL_SOUND = new SoundType(
			1.0F,
			1.0F,
			SoundEvents.END_PORTAL_FRAME_FILL,
			SoundEvents.END_PORTAL_FRAME_FILL,
			SoundEvents.END_PORTAL_FRAME_FILL,
			SoundEvents.END_PORTAL_FRAME_FILL,
			SoundEvents.END_PORTAL_FRAME_FILL
	);

	public static final ResourceKey<Block> COMPRESSED_CHEST_KEY = blockKey("compressed_chest");
	public static final Block COMPRESSED_CHEST = Registry.register(
			BuiltInRegistries.BLOCK,
			COMPRESSED_CHEST_KEY,
			new CompressedChestBlock(BlockBehaviour.Properties.of()
					.setId(COMPRESSED_CHEST_KEY).mapColor(MapColor.WOOD).sound(SoundType.WOOD).strength(2.5F))
	);
	public static final ResourceKey<Item> COMPRESSED_CHEST_ITEM_KEY = itemKey("compressed_chest");
	public static final BlockItem COMPRESSED_CHEST_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			COMPRESSED_CHEST_ITEM_KEY,
			new BlockItem(COMPRESSED_CHEST, new Item.Properties().setId(COMPRESSED_CHEST_ITEM_KEY).rarity(Rarity.RARE))
	);
	public static final ResourceKey<Block> BLAZE_CUBE_BLOCK_KEY = blockKey("blaze_cube_block");
	public static final Block BLAZE_CUBE_BLOCK = Registry.register(
			BuiltInRegistries.BLOCK,
			BLAZE_CUBE_BLOCK_KEY,
			new MagmaBlock(BlockBehaviour.Properties.of()
					.setId(BLAZE_CUBE_BLOCK_KEY)
					.mapColor(MapColor.METAL)
					.sound(SoundType.METAL)
					.strength(1000.0F, 50.0F)
					.lightLevel(state -> 9))
	);
	public static final ResourceKey<Item> BLAZE_CUBE_BLOCK_ITEM_KEY = itemKey("blaze_cube_block");
	public static final BlockItem BLAZE_CUBE_BLOCK_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			BLAZE_CUBE_BLOCK_ITEM_KEY,
			new BlockItem(
					BLAZE_CUBE_BLOCK,
					new Item.Properties().setId(BLAZE_CUBE_BLOCK_ITEM_KEY).rarity(Rarity.RARE).fireResistant()
			)
	);
	public static final ResourceKey<Block> DIAMOND_LATTICE_BLOCK_KEY = blockKey("diamond_lattice_block");
	public static final Block DIAMOND_LATTICE_BLOCK = Registry.register(
			BuiltInRegistries.BLOCK,
			DIAMOND_LATTICE_BLOCK_KEY,
			new Block(BlockBehaviour.Properties.of()
					.setId(DIAMOND_LATTICE_BLOCK_KEY)
					.sound(SoundType.GLASS)
					.strength(100.0F, 100.0F))
	);
	public static final ResourceKey<Item> DIAMOND_LATTICE_BLOCK_ITEM_KEY = itemKey("diamond_lattice_block");
	public static final BlockItem DIAMOND_LATTICE_BLOCK_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			DIAMOND_LATTICE_BLOCK_ITEM_KEY,
			new BlockItem(
					DIAMOND_LATTICE_BLOCK,
					new Item.Properties().setId(DIAMOND_LATTICE_BLOCK_ITEM_KEY).rarity(Rarity.UNCOMMON)
			)
	);
	public static final ResourceKey<Block> STAR_FUEL_BLOCK_KEY = blockKey("star_fuel_block");
	public static final Block STAR_FUEL_BLOCK = Registry.register(
			BuiltInRegistries.BLOCK,
			STAR_FUEL_BLOCK_KEY,
			new Block(BlockBehaviour.Properties.of()
					.setId(STAR_FUEL_BLOCK_KEY)
					.sound(SoundType.STONE)
					.strength(100.0F, 200.0F))
	);
	public static final ResourceKey<Item> STAR_FUEL_BLOCK_ITEM_KEY = itemKey("star_fuel_block");
	public static final BlockItem STAR_FUEL_BLOCK_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			STAR_FUEL_BLOCK_ITEM_KEY,
			new BlockItem(
					STAR_FUEL_BLOCK,
					new Item.Properties().setId(STAR_FUEL_BLOCK_ITEM_KEY).rarity(Rarity.RARE)
			)
	);
	public static final ResourceKey<Block> REFINED_COAL_BLOCK_KEY = blockKey("refined_coal_block");
	public static final Block REFINED_COAL_BLOCK = Registry.register(
			BuiltInRegistries.BLOCK,
			REFINED_COAL_BLOCK_KEY,
			new Block(BlockBehaviour.Properties.of()
					.setId(REFINED_COAL_BLOCK_KEY)
					.sound(SoundType.STONE)
					.strength(50.0F, 50.0F))
	);
	public static final ResourceKey<Item> REFINED_COAL_BLOCK_ITEM_KEY = itemKey("refined_coal_block");
	public static final BlockItem REFINED_COAL_BLOCK_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			REFINED_COAL_BLOCK_ITEM_KEY,
			new BlockItem(
					REFINED_COAL_BLOCK,
					new Item.Properties().setId(REFINED_COAL_BLOCK_ITEM_KEY).rarity(Rarity.UNCOMMON)
			)
	);
	public static final ResourceKey<Block> SOUL_FARMLAND_KEY = blockKey("soul_farmland");
	public static final Block SOUL_FARMLAND = Registry.register(
			BuiltInRegistries.BLOCK,
			SOUL_FARMLAND_KEY,
			new SoulFarmlandBlock(BlockBehaviour.Properties.of()
					.setId(SOUL_FARMLAND_KEY)
					.mapColor(MapColor.COLOR_BROWN)
					.sound(SoundType.SOUL_SOIL)
					.strength(0.6F)
					.randomTicks())
	);
	public static final ResourceKey<Item> SOUL_FARMLAND_ITEM_KEY = itemKey("soul_farmland");
	public static final BlockItem SOUL_FARMLAND_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			SOUL_FARMLAND_ITEM_KEY,
			new BlockItem(
					SOUL_FARMLAND,
					new Item.Properties().setId(SOUL_FARMLAND_ITEM_KEY).rarity(Rarity.RARE)
			)
	);
	public static final ResourceKey<Block> NEUTRON_COLLECTOR_KEY = blockKey("neutron_collector");
	public static final Block NEUTRON_COLLECTOR = Registry.register(
			BuiltInRegistries.BLOCK,
			NEUTRON_COLLECTOR_KEY,
			new NeutronCollectorBlock(BlockBehaviour.Properties.of()
					.setId(NEUTRON_COLLECTOR_KEY)
					.mapColor(MapColor.METAL)
					.sound(SoundType.METAL)
					.strength(50.0F, 2000.0F)
					.requiresCorrectToolForDrops())
	);
	public static final ResourceKey<Item> NEUTRON_COLLECTOR_ITEM_KEY = itemKey("neutron_collector");
	public static final BlockItem NEUTRON_COLLECTOR_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			NEUTRON_COLLECTOR_ITEM_KEY,
			new BlockItem(
					NEUTRON_COLLECTOR,
					new Item.Properties().setId(NEUTRON_COLLECTOR_ITEM_KEY).rarity(Rarity.RARE).fireResistant()
			)
	);
	public static final ResourceKey<Block> DENSE_NEUTRON_COLLECTOR_KEY = blockKey("dense_neutron_collector");
	public static final Block DENSE_NEUTRON_COLLECTOR = Registry.register(
			BuiltInRegistries.BLOCK,
			DENSE_NEUTRON_COLLECTOR_KEY,
			new NeutronCollectorBlock(BlockBehaviour.Properties.of()
					.setId(DENSE_NEUTRON_COLLECTOR_KEY)
					.mapColor(MapColor.METAL)
					.sound(SoundType.METAL)
					.strength(50.0F, 2000.0F)
					.requiresCorrectToolForDrops())
	);
	public static final ResourceKey<Item> DENSE_NEUTRON_COLLECTOR_ITEM_KEY = itemKey("dense_neutron_collector");
	public static final BlockItem DENSE_NEUTRON_COLLECTOR_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			DENSE_NEUTRON_COLLECTOR_ITEM_KEY,
			new BlockItem(DENSE_NEUTRON_COLLECTOR,
					new Item.Properties().setId(DENSE_NEUTRON_COLLECTOR_ITEM_KEY).rarity(Rarity.EPIC).fireResistant())
	);
	public static final ResourceKey<Block> DENSER_NEUTRON_COLLECTOR_KEY = blockKey("denser_neutron_collector");
	public static final Block DENSER_NEUTRON_COLLECTOR = Registry.register(
			BuiltInRegistries.BLOCK,
			DENSER_NEUTRON_COLLECTOR_KEY,
			new NeutronCollectorBlock(BlockBehaviour.Properties.of()
					.setId(DENSER_NEUTRON_COLLECTOR_KEY)
					.mapColor(MapColor.METAL)
					.sound(SoundType.METAL)
					.strength(50.0F, 2000.0F)
					.requiresCorrectToolForDrops())
	);
	public static final ResourceKey<Item> DENSER_NEUTRON_COLLECTOR_ITEM_KEY = itemKey("denser_neutron_collector");
	public static final BlockItem DENSER_NEUTRON_COLLECTOR_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			DENSER_NEUTRON_COLLECTOR_ITEM_KEY,
			new BlockItem(DENSER_NEUTRON_COLLECTOR,
					new Item.Properties().setId(DENSER_NEUTRON_COLLECTOR_ITEM_KEY).rarity(Rarity.EPIC).fireResistant())
	);
	public static final ResourceKey<Block> DENSEST_NEUTRON_COLLECTOR_KEY = blockKey("densest_neutron_collector");
	public static final Block DENSEST_NEUTRON_COLLECTOR = Registry.register(
			BuiltInRegistries.BLOCK,
			DENSEST_NEUTRON_COLLECTOR_KEY,
			new NeutronCollectorBlock(BlockBehaviour.Properties.of()
					.setId(DENSEST_NEUTRON_COLLECTOR_KEY)
					.mapColor(MapColor.METAL)
					.sound(SoundType.METAL)
					.strength(50.0F, 2000.0F)
					.requiresCorrectToolForDrops())
	);
	public static final ResourceKey<Item> DENSEST_NEUTRON_COLLECTOR_ITEM_KEY = itemKey("densest_neutron_collector");
	public static final BlockItem DENSEST_NEUTRON_COLLECTOR_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			DENSEST_NEUTRON_COLLECTOR_ITEM_KEY,
			new BlockItem(DENSEST_NEUTRON_COLLECTOR,
					new Item.Properties().setId(DENSEST_NEUTRON_COLLECTOR_ITEM_KEY).rarity(Rarity.EPIC).fireResistant())
	);
	public static final ResourceKey<Block> INFINITY_KEY = blockKey("infinity");
	public static final Block INFINITY = Registry.register(
			BuiltInRegistries.BLOCK,
			INFINITY_KEY,
			new Block(BlockBehaviour.Properties.of()
					.setId(INFINITY_KEY)
					.mapColor(MapColor.METAL)
					.sound(SoundType.METAL)
					.strength(9999.0F, 9999.0F)
					.lightLevel(state -> 15))
	);
	public static final ResourceKey<Item> INFINITY_ITEM_KEY = itemKey("infinity");
	public static final BlockItem INFINITY_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			INFINITY_ITEM_KEY,
			new BlockItem(
					INFINITY,
					new Item.Properties().setId(INFINITY_ITEM_KEY).rarity(Rarity.EPIC).fireResistant()
			)
	);
	public static final ResourceKey<Block> NEUTRON_COMPRESSOR_KEY = blockKey("neutron_compressor");
	public static final Block NEUTRON_COMPRESSOR = Registry.register(
			BuiltInRegistries.BLOCK,
			NEUTRON_COMPRESSOR_KEY,
			new NeutronCompressorBlock(BlockBehaviour.Properties.of()
					.setId(NEUTRON_COMPRESSOR_KEY)
					.mapColor(MapColor.METAL)
					.sound(SoundType.METAL)
					.strength(50.0F, 2000.0F)
					.requiresCorrectToolForDrops())
	);
	public static final ResourceKey<Item> NEUTRON_COMPRESSOR_ITEM_KEY = itemKey("neutron_compressor");
	public static final BlockItem NEUTRON_COMPRESSOR_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			NEUTRON_COMPRESSOR_ITEM_KEY,
			new BlockItem(
					NEUTRON_COMPRESSOR,
					new Item.Properties().setId(NEUTRON_COMPRESSOR_ITEM_KEY).rarity(Rarity.EPIC).fireResistant()
			)
	);
	public static final ResourceKey<Block> DENSE_NEUTRON_COMPRESSOR_KEY = blockKey("dense_neutron_compressor");
	public static final Block DENSE_NEUTRON_COMPRESSOR = Registry.register(
			BuiltInRegistries.BLOCK,
			DENSE_NEUTRON_COMPRESSOR_KEY,
			new NeutronCompressorBlock(BlockBehaviour.Properties.of()
					.setId(DENSE_NEUTRON_COMPRESSOR_KEY)
					.mapColor(MapColor.METAL)
					.sound(SoundType.METAL)
					.strength(50.0F, 2000.0F)
					.requiresCorrectToolForDrops())
	);
	public static final ResourceKey<Item> DENSE_NEUTRON_COMPRESSOR_ITEM_KEY = itemKey("dense_neutron_compressor");
	public static final BlockItem DENSE_NEUTRON_COMPRESSOR_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			DENSE_NEUTRON_COMPRESSOR_ITEM_KEY,
			new BlockItem(DENSE_NEUTRON_COMPRESSOR,
					new Item.Properties().setId(DENSE_NEUTRON_COMPRESSOR_ITEM_KEY).rarity(Rarity.EPIC).fireResistant())
	);
	public static final ResourceKey<Block> DENSER_NEUTRON_COMPRESSOR_KEY = blockKey("denser_neutron_compressor");
	public static final Block DENSER_NEUTRON_COMPRESSOR = Registry.register(
			BuiltInRegistries.BLOCK,
			DENSER_NEUTRON_COMPRESSOR_KEY,
			new NeutronCompressorBlock(BlockBehaviour.Properties.of()
					.setId(DENSER_NEUTRON_COMPRESSOR_KEY)
					.mapColor(MapColor.METAL)
					.sound(SoundType.METAL)
					.strength(50.0F, 2000.0F)
					.requiresCorrectToolForDrops())
	);
	public static final ResourceKey<Item> DENSER_NEUTRON_COMPRESSOR_ITEM_KEY = itemKey("denser_neutron_compressor");
	public static final BlockItem DENSER_NEUTRON_COMPRESSOR_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			DENSER_NEUTRON_COMPRESSOR_ITEM_KEY,
			new BlockItem(DENSER_NEUTRON_COMPRESSOR,
					new Item.Properties().setId(DENSER_NEUTRON_COMPRESSOR_ITEM_KEY).rarity(Rarity.EPIC).fireResistant())
	);
	public static final ResourceKey<Block> DENSEST_NEUTRON_COMPRESSOR_KEY = blockKey("densest_neutron_compressor");
	public static final Block DENSEST_NEUTRON_COMPRESSOR = Registry.register(
			BuiltInRegistries.BLOCK,
			DENSEST_NEUTRON_COMPRESSOR_KEY,
			new NeutronCompressorBlock(BlockBehaviour.Properties.of()
					.setId(DENSEST_NEUTRON_COMPRESSOR_KEY)
					.mapColor(MapColor.METAL)
					.sound(SoundType.METAL)
					.strength(50.0F, 2000.0F)
					.requiresCorrectToolForDrops())
	);
	public static final ResourceKey<Item> DENSEST_NEUTRON_COMPRESSOR_ITEM_KEY = itemKey("densest_neutron_compressor");
	public static final BlockItem DENSEST_NEUTRON_COMPRESSOR_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			DENSEST_NEUTRON_COMPRESSOR_ITEM_KEY,
			new BlockItem(DENSEST_NEUTRON_COMPRESSOR,
					new Item.Properties().setId(DENSEST_NEUTRON_COMPRESSOR_ITEM_KEY).rarity(Rarity.EPIC).fireResistant())
	);
	public static final ResourceKey<Block> CRYSTAL_MATRIX_KEY = blockKey("crystal_matrix");
	public static final Block CRYSTAL_MATRIX = Registry.register(
			BuiltInRegistries.BLOCK,
			CRYSTAL_MATRIX_KEY,
			new Block(BlockBehaviour.Properties.of()
					.setId(CRYSTAL_MATRIX_KEY)
					.mapColor(MapColor.METAL)
					.sound(SoundType.METAL)
					.strength(2000.0F, 100.0F)
					.lightLevel(state -> 11))
	);
	public static final ResourceKey<Item> CRYSTAL_MATRIX_ITEM_KEY = itemKey("crystal_matrix");
	public static final BlockItem CRYSTAL_MATRIX_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			CRYSTAL_MATRIX_ITEM_KEY,
			new BlockItem(
					CRYSTAL_MATRIX,
					new Item.Properties().setId(CRYSTAL_MATRIX_ITEM_KEY).rarity(Rarity.RARE).fireResistant()
			)
	);
	public static final ResourceKey<Block> NEUTRON_KEY = blockKey("neutron");
	public static final Block NEUTRON = Registry.register(
			BuiltInRegistries.BLOCK,
			NEUTRON_KEY,
			new Block(BlockBehaviour.Properties.of()
					.setId(NEUTRON_KEY)
					.mapColor(MapColor.METAL)
					.sound(SoundType.METAL)
					.strength(8888.0F, 8888.0F)
					.lightLevel(state -> 13))
	);
	public static final ResourceKey<Item> NEUTRON_ITEM_KEY = itemKey("neutron");
	public static final BlockItem NEUTRON_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			NEUTRON_ITEM_KEY,
			new BlockItem(
					NEUTRON,
					new Item.Properties().setId(NEUTRON_ITEM_KEY).rarity(Rarity.EPIC).fireResistant()
			)
	);
	public static final ResourceKey<Block> COMPRESSED_CRAFTING_TABLE_KEY = blockKey("compressed_crafting_table");
	public static final Block COMPRESSED_CRAFTING_TABLE = Registry.register(
			BuiltInRegistries.BLOCK,
			COMPRESSED_CRAFTING_TABLE_KEY,
			new CompressedCraftingTableBlock(BlockBehaviour.Properties.of()
					.setId(COMPRESSED_CRAFTING_TABLE_KEY)
					.mapColor(MapColor.WOOD)
					.sound(SoundType.WOOD)
					.strength(5.0F, 100.0F)
					.requiresCorrectToolForDrops())
	);
	public static final ResourceKey<Item> COMPRESSED_CRAFTING_TABLE_ITEM_KEY = itemKey("compressed_crafting_table");
	public static final BlockItem COMPRESSED_CRAFTING_TABLE_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			COMPRESSED_CRAFTING_TABLE_ITEM_KEY,
			new BlockItem(
					COMPRESSED_CRAFTING_TABLE,
					new Item.Properties().setId(COMPRESSED_CRAFTING_TABLE_ITEM_KEY).rarity(Rarity.UNCOMMON)
			)
	);
	public static final ResourceKey<Block> DOUBLE_COMPRESSED_CRAFTING_TABLE_KEY = blockKey(
			"double_compressed_crafting_table"
	);
	public static final Block DOUBLE_COMPRESSED_CRAFTING_TABLE = Registry.register(
			BuiltInRegistries.BLOCK,
			DOUBLE_COMPRESSED_CRAFTING_TABLE_KEY,
			new CompressedCraftingTableBlock(BlockBehaviour.Properties.of()
					.setId(DOUBLE_COMPRESSED_CRAFTING_TABLE_KEY)
					.mapColor(MapColor.WOOD)
					.sound(SoundType.WOOD)
					.strength(20.0F, 500.0F)
					.requiresCorrectToolForDrops())
	);
	public static final ResourceKey<Item> DOUBLE_COMPRESSED_CRAFTING_TABLE_ITEM_KEY = itemKey(
			"double_compressed_crafting_table"
	);
	public static final BlockItem DOUBLE_COMPRESSED_CRAFTING_TABLE_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			DOUBLE_COMPRESSED_CRAFTING_TABLE_ITEM_KEY,
			new BlockItem(
					DOUBLE_COMPRESSED_CRAFTING_TABLE,
					new Item.Properties().setId(DOUBLE_COMPRESSED_CRAFTING_TABLE_ITEM_KEY)
							.rarity(Rarity.UNCOMMON)
			)
	);
	public static final ResourceKey<Block> SCULK_CRAFTING_TABLE_KEY = blockKey("sculk_crafting_table");
	public static final Block SCULK_CRAFTING_TABLE = Registry.register(
			BuiltInRegistries.BLOCK,
			SCULK_CRAFTING_TABLE_KEY,
			new SculkCraftingTableBlock(BlockBehaviour.Properties.of()
					.setId(SCULK_CRAFTING_TABLE_KEY)
					.mapColor(MapColor.METAL)
					.sound(SoundType.SCULK_CATALYST)
					.strength(25.0F, 500.0F)
					.lightLevel(state -> 15)
					.requiresCorrectToolForDrops())
	);
	public static final ResourceKey<Item> SCULK_CRAFTING_TABLE_ITEM_KEY = itemKey("sculk_crafting_table");
	public static final BlockItem SCULK_CRAFTING_TABLE_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			SCULK_CRAFTING_TABLE_ITEM_KEY,
			new BlockItem(
					SCULK_CRAFTING_TABLE,
					new Item.Properties().setId(SCULK_CRAFTING_TABLE_ITEM_KEY)
			)
	);
	public static final ResourceKey<Block> NETHER_CRAFTING_TABLE_KEY = blockKey("nether_crafting_table");
	public static final Block NETHER_CRAFTING_TABLE = Registry.register(
			BuiltInRegistries.BLOCK,
			NETHER_CRAFTING_TABLE_KEY,
			new NetherCraftingTableBlock(BlockBehaviour.Properties.of()
					.setId(NETHER_CRAFTING_TABLE_KEY)
					.mapColor(MapColor.METAL)
					.sound(SoundType.NETHERRACK)
					.strength(50.0F, 1000.0F)
					.lightLevel(state -> 15)
					.requiresCorrectToolForDrops())
	);
	public static final ResourceKey<Item> NETHER_CRAFTING_TABLE_ITEM_KEY = itemKey("nether_crafting_table");
	public static final BlockItem NETHER_CRAFTING_TABLE_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			NETHER_CRAFTING_TABLE_ITEM_KEY,
			new BlockItem(
					NETHER_CRAFTING_TABLE,
					new Item.Properties().setId(NETHER_CRAFTING_TABLE_ITEM_KEY).rarity(Rarity.UNCOMMON)
			)
	);
	public static final ResourceKey<Block> END_CRAFTING_TABLE_KEY = blockKey("end_crafting_table");
	public static final Block END_CRAFTING_TABLE = Registry.register(
			BuiltInRegistries.BLOCK,
			END_CRAFTING_TABLE_KEY,
			new EndCraftingTableBlock(BlockBehaviour.Properties.of()
					.setId(END_CRAFTING_TABLE_KEY)
					.mapColor(MapColor.METAL)
					.sound(END_PORTAL_SOUND)
					.strength(75.0F, 1500.0F)
					.lightLevel(state -> 15)
					.requiresCorrectToolForDrops())
	);
	public static final ResourceKey<Item> END_CRAFTING_TABLE_ITEM_KEY = itemKey("end_crafting_table");
	public static final BlockItem END_CRAFTING_TABLE_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			END_CRAFTING_TABLE_ITEM_KEY,
			new BlockItem(
					END_CRAFTING_TABLE,
					new Item.Properties().setId(END_CRAFTING_TABLE_ITEM_KEY).rarity(Rarity.RARE)
			)
	);
	public static final ResourceKey<Block> EXTREME_CRAFTING_TABLE_KEY = blockKey("extreme_crafting_table");
	public static final Block EXTREME_CRAFTING_TABLE = Registry.register(
			BuiltInRegistries.BLOCK,
			EXTREME_CRAFTING_TABLE_KEY,
			new ExtremeCraftingTableBlock(BlockBehaviour.Properties.of()
					.setId(EXTREME_CRAFTING_TABLE_KEY)
					.mapColor(MapColor.METAL)
					.sound(SoundType.GLASS)
					.strength(100.0F, 2000.0F)
					.lightLevel(state -> 11)
					.requiresCorrectToolForDrops())
	);
	public static final ResourceKey<Item> EXTREME_CRAFTING_TABLE_ITEM_KEY = itemKey("extreme_crafting_table");
	public static final BlockItem EXTREME_CRAFTING_TABLE_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			EXTREME_CRAFTING_TABLE_ITEM_KEY,
			new BlockItem(
					EXTREME_CRAFTING_TABLE,
					new Item.Properties().setId(EXTREME_CRAFTING_TABLE_ITEM_KEY).rarity(Rarity.EPIC).fireResistant()
			)
	);
	public static final ResourceKey<Block> EXTREME_SMITHING_TABLE_KEY = blockKey("extreme_smithing_table");
	public static final Block EXTREME_SMITHING_TABLE = Registry.register(
			BuiltInRegistries.BLOCK,
			EXTREME_SMITHING_TABLE_KEY,
			new ExtremeSmithingTableBlock(BlockBehaviour.Properties.of()
					.setId(EXTREME_SMITHING_TABLE_KEY)
					.mapColor(MapColor.METAL)
					.sound(SoundType.GLASS)
					.strength(100.0F, 2000.0F)
					.lightLevel(state -> 11)
					.requiresCorrectToolForDrops())
	);
	public static final ResourceKey<Item> EXTREME_SMITHING_TABLE_ITEM_KEY = itemKey("extreme_smithing_table");
	public static final BlockItem EXTREME_SMITHING_TABLE_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			EXTREME_SMITHING_TABLE_ITEM_KEY,
			new BlockItem(
					EXTREME_SMITHING_TABLE,
					new Item.Properties().setId(EXTREME_SMITHING_TABLE_ITEM_KEY).rarity(Rarity.EPIC).fireResistant()
			)
	);
	public static final ResourceKey<Block> EXTREME_ANVIL_KEY = blockKey("extreme_anvil");
	public static final ExtremeAnvilBlock EXTREME_ANVIL = Registry.register(
			BuiltInRegistries.BLOCK,
			EXTREME_ANVIL_KEY,
			new ExtremeAnvilBlock(BlockBehaviour.Properties.of()
					.setId(EXTREME_ANVIL_KEY)
					.mapColor(MapColor.METAL)
					.sound(SoundType.ANVIL)
					.strength(8888.0F, 8888.0F)
					.pushReaction(PushReaction.BLOCK)
					.requiresCorrectToolForDrops())
	);
	public static final ResourceKey<Item> EXTREME_ANVIL_ITEM_KEY = itemKey("extreme_anvil");
	public static final BlockItem EXTREME_ANVIL_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			EXTREME_ANVIL_ITEM_KEY,
			new BlockItem(
					EXTREME_ANVIL,
					new Item.Properties().setId(EXTREME_ANVIL_ITEM_KEY).rarity(Rarity.EPIC).fireResistant()
			)
	);
	public static final ResourceKey<Block> ENDLESS_CAKE_KEY = blockKey("endless_cake");
	public static final EndlessCakeBlock ENDLESS_CAKE = Registry.register(
			BuiltInRegistries.BLOCK,
			ENDLESS_CAKE_KEY,
			new EndlessCakeBlock(BlockBehaviour.Properties.of()
					.setId(ENDLESS_CAKE_KEY)
					.forceSolidOn()
					.strength(0.5F)
					.sound(SoundType.WOOL)
					.pushReaction(PushReaction.DESTROY))
	);
	public static final ResourceKey<Item> ENDLESS_CAKE_ITEM_KEY = itemKey("endless_cake");
	public static final EndlessCakeBlockItem ENDLESS_CAKE_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			ENDLESS_CAKE_ITEM_KEY,
			new EndlessCakeBlockItem(
					ENDLESS_CAKE,
					new Item.Properties().setId(ENDLESS_CAKE_ITEM_KEY).rarity(Rarity.UNCOMMON)
			)
	);
	public static final ResourceKey<Block> INFINITY_CHEST_KEY = blockKey("infinity_chest");
	public static final Block INFINITY_CHEST = Registry.register(
			BuiltInRegistries.BLOCK,
			INFINITY_CHEST_KEY,
			new InfinityChestBlock(BlockBehaviour.Properties.of()
					.setId(INFINITY_CHEST_KEY)
					.mapColor(MapColor.METAL)
					.sound(SoundType.GLASS)
					.strength(100.0F, 2000.0F)
					.lightLevel(state -> 15)
					.requiresCorrectToolForDrops())
	);
	public static final ResourceKey<Item> INFINITY_CHEST_ITEM_KEY = itemKey("infinity_chest");
	public static final BlockItem INFINITY_CHEST_ITEM = Registry.register(
			BuiltInRegistries.ITEM,
			INFINITY_CHEST_ITEM_KEY,
			new BlockItem(
					INFINITY_CHEST,
					new Item.Properties().setId(INFINITY_CHEST_ITEM_KEY).rarity(Rarity.EPIC).fireResistant()
			)
	);

	private ModBlocks() {
	}

	public static void initialize() {
		FuelValueEvents.BUILD.register((builder, context) -> {
			builder.add(ModItems.STAR_FUEL, Integer.MAX_VALUE);
			builder.add(STAR_FUEL_BLOCK_ITEM, Integer.MAX_VALUE);
			builder.add(ModItems.REFINED_COAL, 160_000);
			builder.add(REFINED_COAL_BLOCK_ITEM, 1_440_000);
		});
	}

	private static ResourceKey<Block> blockKey(String path) {
		return ResourceKey.create(BuiltInRegistries.BLOCK.key(), Avaritia26.id(path));
	}

	private static ResourceKey<Item> itemKey(String path) {
		return ResourceKey.create(BuiltInRegistries.ITEM.key(), Avaritia26.id(path));
	}
}
