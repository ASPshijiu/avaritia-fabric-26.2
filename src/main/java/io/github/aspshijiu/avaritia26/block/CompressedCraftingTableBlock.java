package io.github.aspshijiu.avaritia26.block;

import com.mojang.serialization.MapCodec;
import io.github.aspshijiu.avaritia26.inventory.CompressedCraftingMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public final class CompressedCraftingTableBlock extends CraftingTableBlock {
	public static final MapCodec<CompressedCraftingTableBlock> CODEC = simpleCodec(CompressedCraftingTableBlock::new);

	public CompressedCraftingTableBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	public MapCodec<? extends CraftingTableBlock> codec() {
		return CODEC;
	}

	@Override
	protected MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
		return new SimpleMenuProvider(
				(containerId, inventory, player) -> new CompressedCraftingMenu(
						containerId,
						inventory,
						ContainerLevelAccess.create(level, pos),
						this
				),
				Component.translatable("container.crafting")
		);
	}
}
