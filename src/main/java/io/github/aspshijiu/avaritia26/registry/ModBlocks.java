package io.github.aspshijiu.avaritia26.registry;

import io.github.aspshijiu.avaritia26.Avaritia26;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public final class ModBlocks {
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

	private ModBlocks() {
	}

	public static void initialize() {
	}

	private static ResourceKey<Block> blockKey(String path) {
		return ResourceKey.create(BuiltInRegistries.BLOCK.key(), Avaritia26.id(path));
	}

	private static ResourceKey<Item> itemKey(String path) {
		return ResourceKey.create(BuiltInRegistries.ITEM.key(), Avaritia26.id(path));
	}
}
