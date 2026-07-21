package io.github.aspshijiu.avaritia26.block.entity;

import io.github.aspshijiu.avaritia26.component.InfinityChestContents;
import io.github.aspshijiu.avaritia26.inventory.InfinityChestMenu;
import io.github.aspshijiu.avaritia26.registry.ModBlockEntities;
import io.github.aspshijiu.avaritia26.registry.ModDataComponents;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public final class InfinityChestBlockEntity extends BaseContainerBlockEntity implements ExtendedMenuProvider<BlockPos> {
	public static final int SIZE = InfinityChestContents.SLOTS;
	public static final int MAX_STACK_SIZE = Integer.MAX_VALUE;
	private NonNullList<ItemStack> items = NonNullList.withSize(SIZE, ItemStack.EMPTY);

	public InfinityChestBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.INFINITY_CHEST, pos, state);
	}

	@Override
	protected Component getDefaultName() {
		return Component.translatable("block.avaritia26.infinity_chest");
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
	public int getMaxStackSize() {
		return MAX_STACK_SIZE;
	}

	@Override
	public int getMaxStackSize(ItemStack stack) {
		return MAX_STACK_SIZE;
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		items = NonNullList.withSize(SIZE, ItemStack.EMPTY);
		input.read("contents", InfinityChestContents.CODEC).orElse(InfinityChestContents.EMPTY).copyInto(items);
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		InfinityChestContents contents = InfinityChestContents.fromItems(items);
		if (!contents.isEmpty()) {
			output.store("contents", InfinityChestContents.CODEC, contents);
		}
	}

	@Override
	public void preRemoveSideEffects(BlockPos pos, BlockState state) {
		// 内容由方块物品组件携带，不能像普通容器一样拆散掉落。
	}

	@Override
	protected void applyImplicitComponents(DataComponentGetter components) {
		super.applyImplicitComponents(components);
		InfinityChestContents contents = components.get(ModDataComponents.INFINITY_CHEST_CONTENTS);
		if (contents != null) {
			contents.copyInto(items);
		}
	}

	@Override
	protected void collectImplicitComponents(DataComponentMap.Builder builder) {
		if (getCustomName() != null) {
			builder.set(DataComponents.CUSTOM_NAME, getCustomName());
		}
		builder.set(ModDataComponents.INFINITY_CHEST_CONTENTS, InfinityChestContents.fromItems(items));
	}

	@Override
	protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
		return new InfinityChestMenu(containerId, inventory, this);
	}

	@Override
	public BlockPos getScreenOpeningData(ServerPlayer player) {
		return worldPosition;
	}
}
