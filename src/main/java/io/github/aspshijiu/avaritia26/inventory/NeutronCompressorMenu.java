package io.github.aspshijiu.avaritia26.inventory;

import io.github.aspshijiu.avaritia26.block.entity.NeutronCompressorBlockEntity;
import io.github.aspshijiu.avaritia26.registry.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public final class NeutronCompressorMenu extends AbstractContainerMenu {
	public static final int INPUT_SLOT = 0;
	public static final int OUTPUT_SLOT = 1;
	public static final int PLAYER_SLOT_START = 2;
	public static final int PLAYER_SLOT_END = 38;

	private final Container container;
	private final ContainerData data;

	public NeutronCompressorMenu(int containerId, Inventory inventory, BlockPos pos) {
		this(
				containerId,
				inventory,
				new SimpleContainer(2),
				new SimpleContainerData(4)
		);
	}

	public NeutronCompressorMenu(
			int containerId,
			Inventory inventory,
			Container container,
			ContainerData data
	) {
		super(ModMenus.NEUTRON_COMPRESSOR, containerId);
		checkContainerSize(container, 2);
		checkContainerDataCount(data, 4);
		this.container = container;
		this.data = data;
		container.startOpen(inventory.player);
		addSlot(new Slot(container, NeutronCompressorBlockEntity.INPUT_SLOT, 39, 35) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return container.canPlaceItem(NeutronCompressorBlockEntity.INPUT_SLOT, stack);
			}
		});
		addSlot(new Slot(container, NeutronCompressorBlockEntity.OUTPUT_SLOT, 120, 35) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		});
		addStandardInventorySlots(inventory, 8, 84);
		addDataSlots(data);
	}

	public int progress() {
		return data.get(0);
	}

	public int timeRequired() {
		return data.get(1);
	}

	public int materialCount() {
		return data.get(2);
	}

	public int materialsRequired() {
		return data.get(3);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int slotIndex) {
		Slot slot = slots.get(slotIndex);
		if (!slot.hasItem()) {
			return ItemStack.EMPTY;
		}
		ItemStack stack = slot.getItem();
		ItemStack original = stack.copy();
		if (slotIndex == OUTPUT_SLOT) {
			if (!moveItemStackTo(stack, PLAYER_SLOT_START, PLAYER_SLOT_END, true)) {
				return ItemStack.EMPTY;
			}
		} else if (slotIndex >= PLAYER_SLOT_START) {
			if (!slots.get(INPUT_SLOT).mayPlace(stack)
					|| !moveItemStackTo(stack, INPUT_SLOT, INPUT_SLOT + 1, false)) {
				return ItemStack.EMPTY;
			}
		} else if (!moveItemStackTo(stack, PLAYER_SLOT_START, PLAYER_SLOT_END, false)) {
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
		return container.stillValid(player);
	}
}
