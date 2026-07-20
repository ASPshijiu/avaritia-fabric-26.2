package io.github.aspshijiu.avaritia26.inventory;

import io.github.aspshijiu.avaritia26.item.NeutronRingItem;
import io.github.aspshijiu.avaritia26.registry.ModItems;
import io.github.aspshijiu.avaritia26.registry.ModMenus;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public final class NeutronRingMenu extends AbstractContainerMenu {
	public static final int STORAGE_SLOT_END = NeutronRingContainer.SIZE;
	public static final int PLAYER_SLOT_START = STORAGE_SLOT_END;
	public static final int PLAYER_SLOT_END = PLAYER_SLOT_START + 36;

	private final ItemStack ring;
	private final Container storage;

	public NeutronRingMenu(int containerId, Inventory inventory, Integer slot) {
		super(ModMenus.NEUTRON_RING, containerId);
		int ringSlot = slot;
		ItemStack candidate = ringSlot >= 0 ? inventory.getItem(ringSlot) : ItemStack.EMPTY;
		if (!candidate.is(ModItems.NEUTRON_RING)) {
			ringSlot = NeutronRingItem.findRingSlot(inventory.player);
			candidate = ringSlot >= 0 ? inventory.getItem(ringSlot) : ItemStack.EMPTY;
		}
		this.ring = candidate;
		this.storage = new NeutronRingContainer(ring);

		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				addSlot(new RingStorageSlot(storage, column + row * 9, 8 + column * 18, 18 + row * 18));
			}
		}
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 9; column++) {
				int inventorySlot = column + row * 9 + 9;
				addSlot(new RingLockedSlot(inventory, inventorySlot, 8 + column * 18, 193 + row * 18, inventorySlot == ringSlot));
			}
		}
		for (int column = 0; column < 9; column++) {
			addSlot(new RingLockedSlot(inventory, column, 8 + column * 18, 251, column == ringSlot));
		}
	}

	@Override
	public ItemStack quickMoveStack(Player player, int slotIndex) {
		Slot slot = slots.get(slotIndex);
		if (!slot.hasItem()) {
			return ItemStack.EMPTY;
		}
		ItemStack stack = slot.getItem();
		ItemStack original = stack.copy();
		if (slotIndex < STORAGE_SLOT_END) {
			if (!moveItemStackTo(stack, PLAYER_SLOT_START, PLAYER_SLOT_END, true)) {
				return ItemStack.EMPTY;
			}
		} else if (!moveItemStackTo(stack, 0, STORAGE_SLOT_END, false)) {
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
	public boolean stillValid(Player player) {
		return ring.is(ModItems.NEUTRON_RING) && storage.stillValid(player);
	}

	private static final class RingStorageSlot extends Slot {
		private RingStorageSlot(Container container, int slot, int x, int y) {
			super(container, slot, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return container.canPlaceItem(getContainerSlot(), stack);
		}
	}

	private static final class RingLockedSlot extends Slot {
		private final boolean locked;

		private RingLockedSlot(Container container, int slot, int x, int y, boolean locked) {
			super(container, slot, x, y);
			this.locked = locked;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return !locked;
		}

		@Override
		public boolean mayPickup(Player player) {
			return !locked;
		}
	}
}
