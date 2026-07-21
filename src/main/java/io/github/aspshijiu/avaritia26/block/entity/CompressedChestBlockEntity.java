package io.github.aspshijiu.avaritia26.block.entity;

import io.github.aspshijiu.avaritia26.inventory.CompressedChestMenu;
import io.github.aspshijiu.avaritia26.registry.ModBlockEntities;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public final class CompressedChestBlockEntity extends BaseContainerBlockEntity implements ExtendedMenuProvider<BlockPos> {
	public static final int SIZE = 243;
	private NonNullList<ItemStack> items = NonNullList.withSize(SIZE, ItemStack.EMPTY);

	public CompressedChestBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.COMPRESSED_CHEST, pos, state);
	}

	@Override
	protected Component getDefaultName() {
		return Component.translatable("block.avaritia26.compressed_chest");
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> items) {
		this.items = items;
	}

	@Override
	public int getContainerSize() {
		return SIZE;
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		items = NonNullList.withSize(SIZE, ItemStack.EMPTY);
		ContainerHelper.loadAllItems(input, items);
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		ContainerHelper.saveAllItems(output, items);
	}

	@Override
	public void preRemoveSideEffects(BlockPos pos, BlockState state) {
		// 内容由方块物品组件携带，不能像普通容器一样拆散掉落。
	}

	@Override
	protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
		return new CompressedChestMenu(containerId, inventory, this);
	}

	@Override
	public BlockPos getScreenOpeningData(ServerPlayer player) {
		return worldPosition;
	}
}
