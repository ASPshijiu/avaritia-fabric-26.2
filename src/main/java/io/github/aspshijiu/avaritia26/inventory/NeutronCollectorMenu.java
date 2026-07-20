package io.github.aspshijiu.avaritia26.inventory;

import io.github.aspshijiu.avaritia26.block.entity.NeutronCollectorBlockEntity;
import io.github.aspshijiu.avaritia26.registry.ModBlocks;
import io.github.aspshijiu.avaritia26.registry.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public final class NeutronCollectorMenu extends AbstractContainerMenu {
	public static final int OUTPUT_SLOT = 0;
	public static final int PLAYER_SLOT_START = 1;
	public static final int PLAYER_SLOT_END = 37;

	private final Container container;
	private final ContainerData data;
	private final ContainerLevelAccess access;

	public NeutronCollectorMenu(int containerId, Inventory inventory, BlockPos pos) {
		this(
				containerId,
				inventory,
				new SimpleContainer(1),
				new SimpleContainerData(2),
				ContainerLevelAccess.create(inventory.player.level(), pos)
		);
	}

	public NeutronCollectorMenu(int containerId, Inventory inventory, Container container, ContainerData data) {
		this(
				containerId,
				inventory,
				container,
				data,
				container instanceof NeutronCollectorBlockEntity collector
						? ContainerLevelAccess.create(collector.getLevel(), collector.getBlockPos())
						: ContainerLevelAccess.NULL
		);
	}

	private NeutronCollectorMenu(
			int containerId,
			Inventory inventory,
			Container container,
			ContainerData data,
			ContainerLevelAccess access
	) {
		super(ModMenus.NEUTRON_COLLECTOR, containerId);
		checkContainerSize(container, 1);
		checkContainerDataCount(data, 2);
		this.container = container;
		this.data = data;
		this.access = access;
		container.startOpen(inventory.player);
		addSlot(new Slot(container, NeutronCollectorBlockEntity.OUTPUT_SLOT, 80, 32) {
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

	@Override
	public ItemStack quickMoveStack(Player player, int slotIndex) {
		if (slotIndex != OUTPUT_SLOT || !slots.get(slotIndex).hasItem()) {
			return ItemStack.EMPTY;
		}
		Slot slot = slots.get(slotIndex);
		ItemStack stack = slot.getItem();
		ItemStack original = stack.copy();
		if (!moveItemStackTo(stack, PLAYER_SLOT_START, PLAYER_SLOT_END, true)) {
			return ItemStack.EMPTY;
		}
		if (stack.isEmpty()) {
			slot.setByPlayer(ItemStack.EMPTY);
		} else {
			slot.setChanged();
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
		return stillValid(access, player, ModBlocks.NEUTRON_COLLECTOR);
	}
}
