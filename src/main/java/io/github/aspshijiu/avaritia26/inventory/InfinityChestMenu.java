package io.github.aspshijiu.avaritia26.inventory;

import io.github.aspshijiu.avaritia26.block.entity.InfinityChestBlockEntity;
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
import net.minecraft.world.level.block.entity.BlockEntity;

public final class InfinityChestMenu extends AbstractContainerMenu {
	public static final int COLUMNS = 30;
	public static final int ROWS = 10;
	public static final int CHEST_SLOT_END = InfinityChestBlockEntity.SIZE;
	public static final int PLAYER_SLOT_START = CHEST_SLOT_END;
	public static final int PLAYER_SLOT_END = PLAYER_SLOT_START + 36;

	private final Container container;
	private final ContainerLevelAccess access;

	public InfinityChestMenu(int containerId, Inventory inventory, BlockPos pos) {
		this(
				containerId,
				inventory,
				findContainer(inventory, pos),
				ContainerLevelAccess.create(inventory.player.level(), pos)
		);
	}

	public InfinityChestMenu(int containerId, Inventory inventory, Container container) {
		this(
				containerId,
				inventory,
				container,
				container instanceof InfinityChestBlockEntity chest
						? ContainerLevelAccess.create(chest.getLevel(), chest.getBlockPos())
						: ContainerLevelAccess.NULL
		);
	}

	private InfinityChestMenu(
			int containerId,
			Inventory inventory,
			Container container,
			ContainerLevelAccess access
	) {
		super(ModMenus.INFINITY_CHEST, containerId);
		checkContainerSize(container, InfinityChestBlockEntity.SIZE);
		this.container = container;
		this.access = access;
		container.startOpen(inventory.player);
		for (int row = 0; row < ROWS; row++) {
			for (int column = 0; column < COLUMNS; column++) {
				addSlot(new InfinitySlot(container, column + row * COLUMNS, 8 + column * 18, 17 + row * 18));
			}
		}
		addStandardInventorySlots(inventory, 197, 211);
	}

	private static Container findContainer(Inventory inventory, BlockPos pos) {
		BlockEntity blockEntity = inventory.player.level().getBlockEntity(pos);
		if (blockEntity instanceof InfinityChestBlockEntity chest) {
			return chest;
		}
		return new SimpleContainer(InfinityChestBlockEntity.SIZE);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int slotIndex) {
		Slot slot = slots.get(slotIndex);
		if (!slot.hasItem()) {
			return ItemStack.EMPTY;
		}
		ItemStack stack = slot.getItem();
		ItemStack original = stack.copy();
		if (slotIndex < CHEST_SLOT_END) {
			if (!moveItemStackTo(stack, PLAYER_SLOT_START, PLAYER_SLOT_END, true)) {
				return ItemStack.EMPTY;
			}
		} else if (!moveItemStackTo(stack, 0, CHEST_SLOT_END, false)) {
			return ItemStack.EMPTY;
		}
		if (stack.isEmpty()) {
			slot.setByPlayer(ItemStack.EMPTY);
		} else {
			slot.setChanged();
		}
		if (stack.getCount() == original.getCount()) {
			return ItemStack.EMPTY;
		}
		slot.onTake(player, stack);
		return original;
	}

	@Override
	public void removed(Player player) {
		super.removed(player);
		container.stopOpen(player);
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(access, player, ModBlocks.INFINITY_CHEST);
	}

	private static final class InfinitySlot extends Slot {
		private InfinitySlot(Container container, int slot, int x, int y) {
			super(container, slot, x, y);
		}

		@Override
		public int getMaxStackSize() {
			return InfinityChestBlockEntity.MAX_STACK_SIZE;
		}

		@Override
		public int getMaxStackSize(ItemStack stack) {
			return InfinityChestBlockEntity.MAX_STACK_SIZE;
		}
	}
}
