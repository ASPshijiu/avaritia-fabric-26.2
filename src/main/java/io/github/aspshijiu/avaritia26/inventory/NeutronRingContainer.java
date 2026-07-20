package io.github.aspshijiu.avaritia26.inventory;

import io.github.aspshijiu.avaritia26.registry.ModDataComponents;
import io.github.aspshijiu.avaritia26.registry.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;

public final class NeutronRingContainer implements Container {
	public static final int SIZE = 81;

	private final ItemStack ring;
	private final NonNullList<ItemStack> items = NonNullList.withSize(SIZE, ItemStack.EMPTY);

	public NeutronRingContainer(ItemStack ring) {
		this.ring = ring;
		ring.getOrDefault(ModDataComponents.NEUTRON_RING_INVENTORY, ItemContainerContents.EMPTY).copyInto(items);
	}

	@Override
	public int getContainerSize() {
		return SIZE;
	}

	@Override
	public boolean isEmpty() {
		return items.stream().allMatch(ItemStack::isEmpty);
	}

	@Override
	public ItemStack getItem(int slot) {
		return items.get(slot);
	}

	@Override
	public ItemStack removeItem(int slot, int amount) {
		ItemStack removed = ContainerHelper.removeItem(items, slot, amount);
		if (!removed.isEmpty()) {
			setChanged();
		}
		return removed;
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return ContainerHelper.takeItem(items, slot);
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		items.set(slot, stack);
		stack.limitSize(getMaxStackSize(stack));
		setChanged();
	}

	@Override
	public void setChanged() {
		ring.set(ModDataComponents.NEUTRON_RING_INVENTORY, ItemContainerContents.fromItems(items));
	}

	@Override
	public boolean stillValid(Player player) {
		return ring.is(ModItems.NEUTRON_RING);
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		return !stack.is(ModItems.NEUTRON_RING);
	}

	@Override
	public void clearContent() {
		items.clear();
		setChanged();
	}
}
