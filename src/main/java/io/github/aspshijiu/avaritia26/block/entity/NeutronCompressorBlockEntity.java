package io.github.aspshijiu.avaritia26.block.entity;

import io.github.aspshijiu.avaritia26.crafting.CompressorRecipe;
import io.github.aspshijiu.avaritia26.crafting.ModRecipes;
import io.github.aspshijiu.avaritia26.inventory.NeutronCompressorMenu;
import io.github.aspshijiu.avaritia26.item.SingularityItem;
import io.github.aspshijiu.avaritia26.registry.ModBlockEntities;
import io.github.aspshijiu.avaritia26.singularity.SingularityDefinition;
import io.github.aspshijiu.avaritia26.singularity.SingularityManager;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public final class NeutronCompressorBlockEntity extends BlockEntity
		implements WorldlyContainer, ExtendedMenuProvider<BlockPos> {
	public static final int INPUT_SLOT = 0;
	public static final int OUTPUT_SLOT = 1;
	private static final int[] INPUT_SLOTS = {INPUT_SLOT};
	private static final int[] OUTPUT_SLOTS = {OUTPUT_SLOT};

	private final NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);
	private final ContainerData data = new ContainerData() {
		@Override
		public int get(int index) {
			return switch (index) {
				case 0 -> progress;
				case 1 -> getTimeRequired();
				case 2 -> materialCount;
				case 3 -> getMaterialsRequired();
				default -> 0;
			};
		}

		@Override
		public void set(int index, int value) {
			if (index == 0) {
				progress = value;
			}
		}

		@Override
		public int getCount() {
			return 4;
		}
	};

	private ItemStack materialStack = ItemStack.EMPTY;
	private int materialCount;
	private int progress;

	public NeutronCompressorBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.NEUTRON_COMPRESSOR, pos, state);
	}

	public static void serverTick(Level level, BlockPos pos, BlockState state, NeutronCompressorBlockEntity compressor) {
		Target target = compressor.findTarget(compressor.materialStack.isEmpty()
				? compressor.getItem(INPUT_SLOT)
				: compressor.materialStack);
		if (target == null) {
			compressor.progress = 0;
			return;
		}

		ItemStack input = compressor.getItem(INPUT_SLOT);
		if (!input.isEmpty() && compressor.materialCount < target.inputCount() && target.ingredient().test(input)) {
			if (compressor.materialStack.isEmpty()) {
				compressor.materialStack = input.copyWithCount(1);
			}
			int consumed = Math.min(input.getCount(), target.inputCount() - compressor.materialCount);
			input.shrink(consumed);
			compressor.materialCount += consumed;
			compressor.setChanged();
		}

		if (compressor.materialCount < target.inputCount() || !compressor.canOutput(target.result())) {
			return;
		}
		compressor.progress++;
		if (compressor.progress >= target.timeCost()) {
			compressor.insertOutput(target.result());
			compressor.materialCount -= target.inputCount();
			compressor.progress = 0;
			if (compressor.materialCount == 0) {
				compressor.materialStack = ItemStack.EMPTY;
			}
		}
		compressor.setChanged();
	}

	public boolean canAccept(ItemStack stack) {
		if (stack.isEmpty()) {
			return false;
		}
		Target target = findTarget(materialStack.isEmpty() ? stack : materialStack);
		return target != null && target.ingredient().test(stack) && materialCount < target.inputCount();
	}

	public int getMaterialCount() {
		return materialCount;
	}

	public int getProgress() {
		return progress;
	}

	public int getMaterialsRequired() {
		Target target = findTarget(materialStack.isEmpty() ? getItem(INPUT_SLOT) : materialStack);
		return target == null ? 0 : target.inputCount();
	}

	public int getTimeRequired() {
		Target target = findTarget(materialStack.isEmpty() ? getItem(INPUT_SLOT) : materialStack);
		return target == null ? 0 : target.timeCost();
	}

	public void ejectBufferedMaterial(Player player) {
		if (materialStack.isEmpty() || materialCount == 0) {
			return;
		}
		int remaining = materialCount;
		while (remaining > 0) {
			int count = Math.min(remaining, materialStack.getMaxStackSize());
			ItemStack stack = materialStack.copyWithCount(count);
			if (player == null || !player.getInventory().add(stack)) {
				Containers.dropItemStack(level, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), stack);
			}
			remaining -= count;
		}
		materialStack = ItemStack.EMPTY;
		materialCount = 0;
		progress = 0;
		setChanged();
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		items.clear();
		ContainerHelper.loadAllItems(input, items);
		materialStack = input.read("material_stack", ItemStack.CODEC).orElse(ItemStack.EMPTY);
		materialCount = Math.clamp(input.getIntOr("material_count", 0), 0, 1_000_000);
		progress = Math.clamp(input.getIntOr("progress", 0), 0, 72_000);
		if (materialStack.isEmpty()) {
			materialCount = 0;
			progress = 0;
		}
	}

	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		ContainerHelper.saveAllItems(output, items);
		if (!materialStack.isEmpty()) {
			output.store("material_stack", ItemStack.CODEC, materialStack);
		}
		output.putInt("material_count", materialCount);
		output.putInt("progress", progress);
	}

	@Override
	public void preRemoveSideEffects(BlockPos pos, BlockState state) {
		if (level != null && !level.isClientSide()) {
			Containers.dropContents(level, pos, this);
			ejectBufferedMaterial(null);
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
	public boolean canPlaceItem(int slot, ItemStack stack) {
		return slot == INPUT_SLOT && canAccept(stack);
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
		return side == Direction.DOWN ? OUTPUT_SLOTS : INPUT_SLOTS;
	}

	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, Direction side) {
		return slot == INPUT_SLOT && side != Direction.DOWN && canAccept(stack);
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction side) {
		return slot == OUTPUT_SLOT && side == Direction.DOWN;
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("container.avaritia26.neutron_compressor");
	}

	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
		return new NeutronCompressorMenu(containerId, inventory, this, data);
	}

	@Override
	public BlockPos getScreenOpeningData(ServerPlayer player) {
		return worldPosition;
	}

	private Target findTarget(ItemStack candidate) {
		if (level == null || candidate.isEmpty()) {
			return null;
		}
		for (SingularityDefinition definition : SingularityManager.values()) {
			if (definition.recipeEnabled() && definition.ingredient().test(candidate)) {
				return new Target(
						definition.ingredient(),
						definition.count(),
						definition.timeCost(),
						SingularityItem.createStack(definition)
				);
			}
		}
		if (level instanceof ServerLevel serverLevel) {
			CompressorRecipe recipe = serverLevel.getServer().getRecipeManager()
					.getRecipeFor(ModRecipes.COMPRESSOR, new SingleRecipeInput(candidate), serverLevel)
					.map(holder -> holder.value())
					.orElse(null);
			if (recipe != null) {
				return new Target(
						recipe.ingredient(),
						recipe.inputCount(),
						recipe.timeCost(),
						recipe.assemble(new SingleRecipeInput(candidate))
				);
			}
		}
		return null;
	}

	private boolean canOutput(ItemStack result) {
		ItemStack output = getItem(OUTPUT_SLOT);
		return output.isEmpty() || ItemStack.isSameItemSameComponents(output, result)
				&& output.getCount() + result.getCount() <= output.getMaxStackSize();
	}

	private void insertOutput(ItemStack result) {
		ItemStack output = getItem(OUTPUT_SLOT);
		if (output.isEmpty()) {
			setItem(OUTPUT_SLOT, result.copy());
		} else {
			output.grow(result.getCount());
		}
	}

	private record Target(Ingredient ingredient, int inputCount, int timeCost, ItemStack result) {
	}
}
