package io.github.aspshijiu.avaritia26.block.entity;

import io.github.aspshijiu.avaritia26.inventory.NeutronCollectorMenu;
import io.github.aspshijiu.avaritia26.registry.ModBlockEntities;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public final class NeutronCollectorBlockEntity extends BlockEntity
		implements WorldlyContainer, ExtendedMenuProvider<BlockPos> {
	public static final int OUTPUT_SLOT = 0;
	public static final int PRODUCTION_TICKS = 3600;
	private static final int[] OUTPUT_SLOTS = {OUTPUT_SLOT};

	private final NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);
	private final ContainerData data = new ContainerData() {
		@Override
		public int get(int index) {
			return switch (index) {
				case 0 -> progress;
				case 1 -> productionTicks();
				default -> 0;
			};
		}

		@Override
		public void set(int index, int value) {
			if (index == 0) {
				progress = Math.clamp(value, 0, productionTicks() - 1);
			}
		}

		@Override
		public int getCount() {
			return 2;
		}
	};

	private int progress;

	public NeutronCollectorBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.NEUTRON_COLLECTOR, pos, state);
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, NeutronCollectorBlockEntity collector) {
		NeutronCollectorTier tier = NeutronCollectorTier.from(state);
		ItemStack output = collector.getItem(OUTPUT_SLOT);
		if (!output.isEmpty() && (!output.is(tier.output()) || output.getCount() >= output.getMaxStackSize())) {
			return;
		}
		collector.progress++;
		if (collector.progress >= tier.productionTicks()) {
			if (output.isEmpty()) {
				collector.setItem(OUTPUT_SLOT, new ItemStack(tier.output()));
			} else {
				output.grow(1);
			}
			collector.progress = 0;
		}
		collector.setChanged();
	}

	public int getProgress() {
		return progress;
	}

	public NeutronCollectorTier getTier() {
		return NeutronCollectorTier.from(getBlockState());
	}

	public int productionTicks() {
		return getTier().productionTicks();
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		items.clear();
		ContainerHelper.loadAllItems(input, items);
		progress = Math.clamp(input.getIntOr("progress", 0), 0, productionTicks() - 1);
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		ContainerHelper.saveAllItems(output, items);
		output.putInt("progress", progress);
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
		return items.getFirst().isEmpty();
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
	public boolean canPlaceItem(int slot, ItemStack stack) {
		return false;
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
	public int[] getSlotsForFace(Direction side) {
		return OUTPUT_SLOTS;
	}

	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, Direction side) {
		return false;
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction side) {
		return slot == OUTPUT_SLOT && stack.is(getTier().output());
	}

	@Override
	public Component getDisplayName() {
		return getBlockState().getBlock().getName();
	}

	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
		return new NeutronCollectorMenu(containerId, inventory, this, data);
	}

	@Override
	public BlockPos getScreenOpeningData(ServerPlayer player) {
		return worldPosition;
	}
}
