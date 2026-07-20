package io.github.aspshijiu.avaritia26.block.entity;

import io.github.aspshijiu.avaritia26.inventory.EndCraftingMenu;
import io.github.aspshijiu.avaritia26.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.block.state.BlockState;

public final class EndCraftingTableBlockEntity extends SculkCraftingTableBlockEntity {
	public static final int GRID_WIDTH = 7;
	public static final int GRID_SIZE = GRID_WIDTH * GRID_WIDTH;

	public EndCraftingTableBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.END_CRAFTING_TABLE, GRID_SIZE, pos, state);
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("container.avaritia26.end_crafting_table");
	}

	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
		return new EndCraftingMenu(
				containerId,
				inventory,
				this,
				ContainerLevelAccess.create(level, worldPosition)
		);
	}
}
