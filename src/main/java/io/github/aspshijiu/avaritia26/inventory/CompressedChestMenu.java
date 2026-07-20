package io.github.aspshijiu.avaritia26.inventory;

import io.github.aspshijiu.avaritia26.block.entity.CompressedChestBlockEntity;
import io.github.aspshijiu.avaritia26.registry.ModBlocks;
import io.github.aspshijiu.avaritia26.registry.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public final class CompressedChestMenu extends AbstractContainerMenu {
	public static final int CHEST_SLOTS = 243;
	private final Container container;
	private final ContainerLevelAccess access;

	public CompressedChestMenu(int containerId, Inventory inventory, BlockPos pos) {
		this(containerId, inventory, new SimpleContainer(CHEST_SLOTS), ContainerLevelAccess.create(inventory.player.level(), pos));
	}

	public CompressedChestMenu(int containerId, Inventory inventory, Container container) {
		this(containerId, inventory, container, container instanceof CompressedChestBlockEntity chest
				? ContainerLevelAccess.create(chest.getLevel(), chest.getBlockPos()) : ContainerLevelAccess.NULL);
	}

	private CompressedChestMenu(int containerId, Inventory inventory, Container container, ContainerLevelAccess access) {
		super(ModMenus.COMPRESSED_CHEST, containerId);
		checkContainerSize(container, CHEST_SLOTS);
		this.container = container;
		this.access = access;
		container.startOpen(inventory.player);
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 27; column++) {
				addSlot(new Slot(container, column + row * 27, 8 + column * 18, 17 + row * 18));
			}
		}
		addStandardInventorySlots(inventory, 170, 193);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		Slot slot = slots.get(index);
		if (!slot.hasItem()) return ItemStack.EMPTY;
		ItemStack stack = slot.getItem();
		ItemStack original = stack.copy();
		if (index < CHEST_SLOTS ? !moveItemStackTo(stack, CHEST_SLOTS, slots.size(), true)
				: !moveItemStackTo(stack, 0, CHEST_SLOTS, false)) return ItemStack.EMPTY;
		if (stack.isEmpty()) slot.setByPlayer(ItemStack.EMPTY); else slot.setChanged();
		return original;
	}

	@Override
	public void removed(Player player) {
		super.removed(player);
		container.stopOpen(player);
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(access, player, ModBlocks.COMPRESSED_CHEST);
	}
}
