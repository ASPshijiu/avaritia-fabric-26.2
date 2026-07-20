package io.github.aspshijiu.avaritia26.inventory;

import java.util.List;

import io.github.aspshijiu.avaritia26.crafting.ExtremeSmithingInput;
import io.github.aspshijiu.avaritia26.crafting.ExtremeSmithingRecipe;
import io.github.aspshijiu.avaritia26.crafting.ModRecipes;
import io.github.aspshijiu.avaritia26.registry.ModBlocks;
import io.github.aspshijiu.avaritia26.registry.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

public final class ExtremeSmithingMenu extends AbstractContainerMenu {
	public static final int TEMPLATE_SLOT = 0;
	public static final int BASE_SLOT = 1;
	public static final int ADDITION_SLOT_START = 2;
	public static final int ADDITION_SLOT_END = 5;
	public static final int RESULT_SLOT = 5;
	public static final int PLAYER_SLOT_START = 6;
	public static final int PLAYER_SLOT_END = 42;

	private final Player player;
	private final ContainerLevelAccess access;
	private final SimpleContainer inputSlots;
	private final ResultContainer resultSlots = new ResultContainer();
	private RecipeHolder<ExtremeSmithingRecipe> selectedRecipe;
	private boolean updating;

	public ExtremeSmithingMenu(int containerId, Inventory inventory, BlockPos pos) {
		this(containerId, inventory, ContainerLevelAccess.create(inventory.player.level(), pos));
	}

	public ExtremeSmithingMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
		super(ModMenus.EXTREME_SMITHING, containerId);
		this.player = inventory.player;
		this.access = access;
		this.inputSlots = new SimpleContainer(5) {
			@Override
			public void setChanged() {
				super.setChanged();
				ExtremeSmithingMenu.this.slotsChanged(this);
			}
		};

		addSlot(inputSlot(TEMPLATE_SLOT, 31, 35));
		addSlot(inputSlot(BASE_SLOT, 49, 35));
		addSlot(inputSlot(ADDITION_SLOT_START, 67, 35));
		addSlot(inputSlot(ADDITION_SLOT_START + 1, 49, 17));
		addSlot(inputSlot(ADDITION_SLOT_START + 2, 49, 53));
		addSlot(new SmithingResultSlot(resultSlots, 0, 121, 35));
		addStandardInventorySlots(inventory, 8, 84);
	}

	private Slot inputSlot(int slot, int x, int y) {
		return new Slot(inputSlots, slot, x, y) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return acceptsInput(slot, stack);
			}
		};
	}

	private boolean acceptsInput(int slot, ItemStack stack) {
		List<ExtremeSmithingRecipe> recipes = recipes();
		return recipes.isEmpty() || recipes.stream().anyMatch(recipe -> switch (slot) {
			case TEMPLATE_SLOT -> recipe.acceptsTemplate(stack);
			case BASE_SLOT -> recipe.acceptsBase(stack);
			default -> recipe.acceptsAddition(stack);
		});
	}

	private List<ExtremeSmithingRecipe> recipes() {
		if (player.level() instanceof ServerLevel serverLevel) {
			return serverLevel.getServer().getRecipeManager().getRecipes().stream()
					.map(RecipeHolder::value)
					.filter(ExtremeSmithingRecipe.class::isInstance)
					.map(ExtremeSmithingRecipe.class::cast)
					.toList();
		}
		return List.of();
	}

	@Override
	public void slotsChanged(Container container) {
		if (updating || !(player.level() instanceof ServerLevel serverLevel)) {
			return;
		}
		ExtremeSmithingInput input = currentInput();
		selectedRecipe = serverLevel.getServer().getRecipeManager()
				.getRecipeFor(ModRecipes.EXTREME_SMITHING, input, serverLevel)
				.orElse(null);
		ItemStack result = selectedRecipe == null ? ItemStack.EMPTY : selectedRecipe.value().assemble(input);
		if (!result.isItemEnabled(serverLevel.enabledFeatures())) {
			selectedRecipe = null;
			result = ItemStack.EMPTY;
		}
		resultSlots.setRecipeUsed(selectedRecipe);
		resultSlots.setItem(0, result);
		broadcastChanges();
	}

	private ExtremeSmithingInput currentInput() {
		return new ExtremeSmithingInput(
				inputSlots.getItem(0),
				inputSlots.getItem(1),
				inputSlots.getItem(2),
				inputSlots.getItem(3),
				inputSlots.getItem(4)
		);
	}

	private boolean resultAvailable() {
		slotsChanged(inputSlots);
		return selectedRecipe != null && selectedRecipe.value().matches(currentInput(), player.level());
	}

	private void takeResult(Player player, ItemStack result, int craftedCount) {
		if (!resultAvailable()) {
			return;
		}
		result.onCraftedBy(player, craftedCount);
		resultSlots.awardUsedRecipes(player, inputSlots.getItems());
		updating = true;
		try {
			for (int slot = 0; slot < inputSlots.getContainerSize(); slot++) {
				inputSlots.removeItem(slot, 1);
			}
		} finally {
			updating = false;
		}
		access.execute((level, pos) -> level.levelEvent(1044, pos, 0));
		slotsChanged(inputSlots);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int slotIndex) {
		Slot slot = slots.get(slotIndex);
		if (slotIndex == RESULT_SLOT && !resultAvailable()) {
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
			int target = firstInputSlot(stack);
			if (target < 0 || !moveItemStackTo(stack, target, target + 1, false)) {
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
		if (slotIndex == RESULT_SLOT && !stack.isEmpty()) {
			player.drop(stack, false);
		}
		return original;
	}

	private int firstInputSlot(ItemStack stack) {
		for (int slot = TEMPLATE_SLOT; slot < ADDITION_SLOT_END; slot++) {
			if (slots.get(slot).mayPlace(stack) && !slots.get(slot).hasItem()) {
				return slot;
			}
		}
		return -1;
	}

	@Override
	public void removed(Player player) {
		super.removed(player);
		clearContainer(player, inputSlots);
	}

	@Override
	public boolean stillValid(Player player) {
		return stillValid(access, player, ModBlocks.EXTREME_SMITHING_TABLE);
	}

	@Override
	public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
		return slot.container != resultSlots && super.canTakeItemForPickAll(stack, slot);
	}

	private final class SmithingResultSlot extends Slot {
		private int removeCount;
		private ItemStack craftedStack = ItemStack.EMPTY;

		private SmithingResultSlot(Container container, int slot, int x, int y) {
			super(container, slot, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}

		@Override
		public boolean mayPickup(Player player) {
			return resultAvailable();
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
		public void onTake(Player player, ItemStack stack) {
			ItemStack crafted = stack.isEmpty() ? craftedStack : stack;
			takeResult(player, crafted, removeCount);
			removeCount = 0;
			craftedStack = ItemStack.EMPTY;
			super.onTake(player, stack);
		}
	}
}
