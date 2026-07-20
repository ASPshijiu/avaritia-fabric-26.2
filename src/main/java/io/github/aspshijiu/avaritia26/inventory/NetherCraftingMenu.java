package io.github.aspshijiu.avaritia26.inventory;

import io.github.aspshijiu.avaritia26.block.entity.NetherCraftingTableBlockEntity;
import io.github.aspshijiu.avaritia26.registry.ModBlocks;
import io.github.aspshijiu.avaritia26.registry.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerLevelAccess;

public final class NetherCraftingMenu extends SculkCraftingMenu {
	public NetherCraftingMenu(int containerId, Inventory inventory, BlockPos pos) {
		this(
				containerId,
				inventory,
				findContainer(
						inventory,
						pos,
						NetherCraftingTableBlockEntity.class,
						NetherCraftingTableBlockEntity.GRID_SIZE
				),
				ContainerLevelAccess.create(inventory.player.level(), pos)
		);
	}

	public NetherCraftingMenu(
			int containerId,
			Inventory inventory,
			Container storage,
			ContainerLevelAccess access
	) {
		super(
				ModMenus.NETHER_CRAFTING,
				containerId,
				inventory,
				storage,
				access,
				NetherCraftingTableBlockEntity.GRID_WIDTH,
				ModBlocks.NETHER_CRAFTING_TABLE,
				14,
				18,
				142,
				53,
				8,
				124,
				8,
				182
		);
	}
}
