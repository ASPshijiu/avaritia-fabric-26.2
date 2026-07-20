package io.github.aspshijiu.avaritia26.block.entity;

import io.github.aspshijiu.avaritia26.inventory.SculkCraftingMenu;
import io.github.aspshijiu.avaritia26.registry.ModBlockEntities;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class SculkCraftingTableBlockEntity extends BlockEntity implements Container, ExtendedMenuProvider<BlockPos> {
	public static final int GRID_WIDTH = 3;
	public static final int GRID_SIZE = GRID_WIDTH * GRID_WIDTH;

	private final NonNullList<ItemStack> items;

	public SculkCraftingTableBlockEntity(BlockPos pos, BlockState state) {
		this(ModBlockEntities.SCULK_CRAFTING_TABLE, GRID_SIZE, pos, state);
	}

	protected SculkCraftingTableBlockEntity(
			BlockEntityType<?> type,
			int gridSize,
			BlockPos pos,
			BlockState state
	) {
		super(type, pos, state);
		items = NonNullList.withSize(gridSize, ItemStack.EMPTY);
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		items.clear();
		ContainerHelper.loadAllItems(input, items);
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		ContainerHelper.saveAllItems(output, items);
	}

	@Override
	public void preRemoveSideEffects(BlockPos pos, BlockState state) {
		if (level != null && !level.isClientSide()) {
			Containers.dropContents(level, pos, this);
		}
		super.preRemoveSideEffects(pos, state);
	}

	@Override
	public int getContainerSize() {
		return items.size();
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
	public boolean stillValid(Player player) {
		return Container.stillValidBlockEntity(this, player);
	}

	@Override
	public void clearContent() {
		items.clear();
		setChanged();
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("container.avaritia26.sculk_crafting_table");
	}

	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
		return new SculkCraftingMenu(
				containerId,
				inventory,
				this,
				net.minecraft.world.inventory.ContainerLevelAccess.create(level, worldPosition)
		);
	}

	@Override
	public BlockPos getScreenOpeningData(ServerPlayer player) {
		return worldPosition;
	}
}
