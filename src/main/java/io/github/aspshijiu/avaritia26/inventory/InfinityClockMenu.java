package io.github.aspshijiu.avaritia26.inventory;

import io.github.aspshijiu.avaritia26.registry.ModItems;
import io.github.aspshijiu.avaritia26.registry.ModMenus;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public final class InfinityClockMenu extends AbstractContainerMenu {
	private final Inventory inventory;
	private final int clockSlot;

	public InfinityClockMenu(int containerId, Inventory inventory, Integer slot) {
		super(ModMenus.INFINITY_CLOCK, containerId);
		this.inventory = inventory;
		this.clockSlot = slot;
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 9; column++) {
				int inventorySlot = column + row * 9 + 9;
				addSlot(new ClockSlot(inventory, inventorySlot, 8 + column * 18, 84 + row * 18,
						inventorySlot == clockSlot));
			}
		}
		for (int column = 0; column < 9; column++) {
			addSlot(new ClockSlot(inventory, column, 8 + column * 18, 142, column == clockSlot));
		}
	}

	@Override
	public ItemStack quickMoveStack(Player player, int slotIndex) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean stillValid(Player player) {
		return inventory.getItem(clockSlot).is(ModItems.INFINITY_CLOCK);
	}

	private static final class ClockSlot extends Slot {
		private final boolean locked;

		private ClockSlot(Inventory inventory, int slot, int x, int y, boolean locked) {
			super(inventory, slot, x, y);
			this.locked = locked;
		}

		@Override
		public boolean mayPickup(Player player) {
			return !locked;
		}
	}
}
