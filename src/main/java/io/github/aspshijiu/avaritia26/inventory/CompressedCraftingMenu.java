package io.github.aspshijiu.avaritia26.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.block.Block;

public final class CompressedCraftingMenu extends CraftingMenu {
	private final ContainerLevelAccess access;
	private final Block validBlock;

	public CompressedCraftingMenu(
			int containerId,
			Inventory inventory,
			ContainerLevelAccess access,
			Block validBlock
	) {
		super(containerId, inventory, access);
		this.access = access;
		this.validBlock = validBlock;
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(access, player, validBlock);
	}
}
