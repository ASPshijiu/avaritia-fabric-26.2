package io.github.aspshijiu.avaritia26.inventory;

import java.util.ArrayList;
import java.util.List;

import io.github.aspshijiu.avaritia26.block.entity.ExtremeCraftingTableBlockEntity;
import io.github.aspshijiu.avaritia26.crafting.ModRecipes;
import io.github.aspshijiu.avaritia26.registry.ModBlocks;
import io.github.aspshijiu.avaritia26.registry.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntity;

public final class ExtremeCraftingMenu extends AbstractContainerMenu {
	public static final int RESULT_SLOT = 0;
	public static final int INPUT_SLOT_START = 1;
	public static final int INPUT_SLOT_END = 82;
	public static final int PLAYER_SLOT_START = 82;
	public static final int PLAYER_SLOT_END = 118;

	private final Player player;
	private final ContainerLevelAccess access;
	private final CraftingContainer craftSlots;
	private final ResultContainer resultSlots = new ResultContainer();
	private RecipeHolder<Recipe<CraftingInput>> currentRecipe;
	private CraftingInput.Positioned currentInput;
	private boolean consuming;

	public ExtremeCraftingMenu(int containerId, Inventory inventory, BlockPos pos) {
		this(
				containerId,
				inventory,
				findContainer(inventory, pos),
				ContainerLevelAccess.create(inventory.player.level(), pos)
		);
	}

	public ExtremeCraftingMenu(
			int containerId,
			Inventory inventory,
			Container storage,
			ContainerLevelAccess access
	) {
		super(ModMenus.EXTREME_CRAFTING, containerId);
		checkContainerSize(storage, ExtremeCraftingTableBlockEntity.GRID_SIZE);
		this.player = inventory.player;
		this.access = access;
		this.craftSlots = new PersistentCraftingContainer(this, storage);

		addSlot(new ExtremeResultSlot(this, resultSlots, 206, 89));
		for (int row = 0; row < 9; row++) {
			for (int column = 0; column < 9; column++) {
				addSlot(new Slot(craftSlots, column + row * 9, 8 + column * 18, 18 + row * 18));
			}
		}
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 9; column++) {
				addSlot(new Slot(inventory, column + row * 9 + 9, 39 + column * 18, 196 + row * 18));
			}
		}
		for (int column = 0; column < 9; column++) {
			addSlot(new Slot(inventory, column, 39 + column * 18, 254));
		}
		slotsChanged(craftSlots);
	}

	private static Container findContainer(Inventory inventory, BlockPos pos) {
		BlockEntity blockEntity = inventory.player.level().getBlockEntity(pos);
		if (blockEntity instanceof ExtremeCraftingTableBlockEntity table) {
			return table;
		}
		return new SimpleContainer(ExtremeCraftingTableBlockEntity.GRID_SIZE);
	}

	@Override
	public void slotsChanged(Container container) {
		if (consuming || !(player.level() instanceof ServerLevel serverLevel)) {
			return;
		}
		currentInput = craftSlots.asPositionedCraftInput();
		currentRecipe = serverLevel.getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_CRAFTING, currentInput.input(), serverLevel)
				.orElse(null);
		ItemStack result = currentRecipe == null
				? ItemStack.EMPTY
				: currentRecipe.value().assemble(currentInput.input());
		if (!result.isItemEnabled(serverLevel.enabledFeatures())) {
			result = ItemStack.EMPTY;
			currentRecipe = null;
		}
		resultSlots.setRecipeUsed(currentRecipe);
		resultSlots.setItem(RESULT_SLOT, result);
		broadcastChanges();
	}

	private void takeResult(Player player, ItemStack result, int craftedCount) {
		if (currentRecipe == null || currentInput == null) {
			return;
		}
		result.onCraftedBy(player, craftedCount);
		resultSlots.awardUsedRecipes(player, craftSlots.getItems());
		NonNullList<ItemStack> remaining = CraftingRecipe.defaultCraftingReminder(currentInput.input());
		consuming = true;
		try {
			for (int row = 0; row < currentInput.input().height(); row++) {
				for (int column = 0; column < currentInput.input().width(); column++) {
					int inputIndex = column + row * currentInput.input().width();
					int slot = column + currentInput.left() + (row + currentInput.top()) * 9;
					if (!craftSlots.getItem(slot).isEmpty()) {
						craftSlots.removeItem(slot, 1);
					}
					placeRemainingItem(player, slot, remaining.get(inputIndex));
				}
			}
		} finally {
			consuming = false;
		}
		slotsChanged(craftSlots);
	}

	private boolean refreshResult() {
		slotsChanged(craftSlots);
		return currentRecipe != null && !resultSlots.getItem(RESULT_SLOT).isEmpty();
	}

	private void placeRemainingItem(Player player, int slot, ItemStack remaining) {
		if (remaining.isEmpty()) {
			return;
		}
		ItemStack current = craftSlots.getItem(slot);
		if (current.isEmpty()) {
			craftSlots.setItem(slot, remaining);
		} else if (ItemStack.isSameItemSameComponents(current, remaining)) {
			remaining.grow(current.getCount());
			craftSlots.setItem(slot, remaining);
		} else if (!player.getInventory().add(remaining)) {
			player.drop(remaining, false);
		}
	}

	@Override
	public ItemStack quickMoveStack(Player player, int slotIndex) {
		Slot slot = slots.get(slotIndex);
		if (slotIndex == RESULT_SLOT && !refreshResult()) {
			return ItemStack.EMPTY;
		}
		if (!slot.hasItem()) {
			return ItemStack.EMPTY;
		}
		ItemStack stack = slot.getItem();
		ItemStack original = stack.copy();
		if (slotIndex == RESULT_SLOT) {
			if (!moveItemStackTo(stack, PLAYER_SLOT_START, PLAYER_SLOT_END, true)) {
				return ItemStack.EMPTY;
			}
			slot.onQuickCraft(stack, original);
		} else if (slotIndex >= PLAYER_SLOT_START) {
			if (!moveItemStackTo(stack, INPUT_SLOT_START, INPUT_SLOT_END, false)) {
				if (slotIndex < 109) {
					if (!moveItemStackTo(stack, 109, PLAYER_SLOT_END, false)) {
						return ItemStack.EMPTY;
					}
				} else if (!moveItemStackTo(stack, PLAYER_SLOT_START, 109, false)) {
					return ItemStack.EMPTY;
				}
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
		if (slotIndex == RESULT_SLOT && !stack.isEmpty()) {
			player.drop(stack, false);
		}
		return original;
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(access, player, ModBlocks.EXTREME_CRAFTING_TABLE);
	}

	@Override
	public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
		return slot.container != resultSlots && super.canTakeItemForPickAll(stack, slot);
	}

	private static final class ExtremeResultSlot extends Slot {
		private final ExtremeCraftingMenu menu;
		private int removeCount;
		private ItemStack craftedStack = ItemStack.EMPTY;

		private ExtremeResultSlot(ExtremeCraftingMenu menu, Container result, int x, int y) {
			super(result, RESULT_SLOT, x, y);
			this.menu = menu;
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}

		@Override
		public boolean mayPickup(Player player) {
			return menu.refreshResult();
		}

		@Override
		public ItemStack remove(int amount) {
			ItemStack removed = super.remove(amount);
			if (!removed.isEmpty()) {
				removeCount += removed.getCount();
				craftedStack = removed.copy();
			}
			return removed;
		}

		@Override
		protected void onQuickCraft(ItemStack stack, int amount) {
			removeCount += amount;
			craftedStack = stack.copy();
		}

		@Override
		protected void onSwapCraft(int amount) {
			removeCount += amount;
		}

		@Override
		public void onTake(Player player, ItemStack stack) {
			ItemStack crafted = stack.isEmpty() ? craftedStack : stack;
			menu.takeResult(player, crafted, removeCount);
			removeCount = 0;
			craftedStack = ItemStack.EMPTY;
			super.onTake(player, stack);
		}
	}

	private static final class PersistentCraftingContainer implements CraftingContainer {
		private final ExtremeCraftingMenu menu;
		private final Container storage;

		private PersistentCraftingContainer(ExtremeCraftingMenu menu, Container storage) {
			this.menu = menu;
			this.storage = storage;
		}

		@Override
		public int getWidth() {
			return 9;
		}

		@Override
		public int getHeight() {
			return 9;
		}

		@Override
		public List<ItemStack> getItems() {
			List<ItemStack> items = new ArrayList<>(storage.getContainerSize());
			storage.forEach(items::add);
			return items;
		}

		@Override
		public void fillStackedContents(StackedItemContents contents) {
			storage.forEach(contents::accountSimpleStack);
		}

		@Override
		public int getContainerSize() {
			return storage.getContainerSize();
		}

		@Override
		public boolean isEmpty() {
			return storage.isEmpty();
		}

		@Override
		public ItemStack getItem(int slot) {
			return storage.getItem(slot);
		}

		@Override
		public ItemStack removeItem(int slot, int amount) {
			ItemStack removed = storage.removeItem(slot, amount);
			if (!removed.isEmpty()) {
				menu.slotsChanged(this);
			}
			return removed;
		}

		@Override
		public ItemStack removeItemNoUpdate(int slot) {
			return storage.removeItemNoUpdate(slot);
		}

		@Override
		public void setItem(int slot, ItemStack stack) {
			storage.setItem(slot, stack);
			menu.slotsChanged(this);
		}

		@Override
		public void setChanged() {
			storage.setChanged();
			menu.slotsChanged(this);
		}

		@Override
		public boolean stillValid(Player player) {
			return storage.stillValid(player);
		}

		@Override
		public void clearContent() {
			storage.clearContent();
			menu.slotsChanged(this);
		}
	}
}
