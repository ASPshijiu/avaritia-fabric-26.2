package io.github.aspshijiu.avaritia26.inventory;

import io.github.aspshijiu.avaritia26.block.entity.EndCraftingTableBlockEntity;
import io.github.aspshijiu.avaritia26.registry.ModBlocks;
import io.github.aspshijiu.avaritia26.registry.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerLevelAccess;

public final class EndCraftingMenu extends SculkCraftingMenu {
	public EndCraftingMenu(int containerId, Inventory inventory, BlockPos pos) {
		this(
				containerId,
				inventory,
				findContainer(
						inventory,
						pos,
						EndCraftingTableBlockEntity.class,
						EndCraftingTableBlockEntity.GRID_SIZE
				),
				ContainerLevelAccess.create(inventory.player.level(), pos)
		);
	}

	public EndCraftingMenu(
			int containerId,
			Inventory inventory,
			Container storage,
			ContainerLevelAccess access
	) {
		super(
				ModMenus.END_CRAFTING,
				containerId,
				inventory,
				storage,
				access,
				EndCraftingTableBlockEntity.GRID_WIDTH,
				ModBlocks.END_CRAFTING_TABLE,
				8,
				18,
				172,
				71,
				20,
				160,
				20,
				218
		);
	}
}
