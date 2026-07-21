package io.github.aspshijiu.avaritia26.inventory;

import io.github.aspshijiu.avaritia26.registry.ModBlocks;
import io.github.aspshijiu.avaritia26.registry.ModMenus;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringUtil;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.ItemCombinerMenuSlotDefinition;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.block.state.BlockState;

public final class ExtremeAnvilMenu extends ItemCombinerMenu {
	private static final ItemCombinerMenuSlotDefinition SLOTS = ItemCombinerMenuSlotDefinition.create()
			.withSlot(0, 27, 47, stack -> true)
			.withSlot(1, 76, 47, stack -> true)
			.withResultSlot(2, 134, 47)
			.build();
	private String itemName;
	private int repairItemCountCost;

	public ExtremeAnvilMenu(int containerId, Inventory inventory, net.minecraft.core.BlockPos pos) {
		this(containerId, inventory, ContainerLevelAccess.create(inventory.player.level(), pos));
	}

	public ExtremeAnvilMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
		super(ModMenus.EXTREME_ANVIL, containerId, inventory, access, SLOTS);
	}

	@Override
	protected boolean isValidBlock(BlockState state) {
		return state.is(ModBlocks.EXTREME_ANVIL);
	}

	@Override
	protected boolean mayPickup(Player player, boolean hasStack) {
		return hasStack;
	}

	@Override
	protected void onTake(Player player, ItemStack stack) {
		inputSlots.setItem(0, ItemStack.EMPTY);
		ItemStack addition = inputSlots.getItem(1);
		if (repairItemCountCost > 0 && addition.getCount() > repairItemCountCost) {
			addition.shrink(repairItemCountCost);
			inputSlots.setItem(1, addition);
		} else {
			inputSlots.setItem(1, ItemStack.EMPTY);
		}
		access.execute((level, pos) -> level.levelEvent(1044, pos, 0));
	}

	@Override
	public void createResult() {
		ItemStack input = inputSlots.getItem(0);
		if (input.isEmpty() || !EnchantmentHelper.canStoreEnchantments(input)) {
			resultSlots.setItem(0, ItemStack.EMPTY);
			return;
		}

		ItemStack result = input.copy();
		ItemStack addition = inputSlots.getItem(1);
		ItemEnchantments.Mutable enchantments = new ItemEnchantments.Mutable(
				EnchantmentHelper.getEnchantmentsForCrafting(result));
		repairItemCountCost = 0;
		int changes = 0;
		boolean enchantedBook = false;

		if (!addition.isEmpty()) {
			enchantedBook = addition.has(DataComponents.STORED_ENCHANTMENTS);
			if (result.isDamageableItem() && input.isValidRepairItem(addition)) {
				int repairedPerItem = Math.min(result.getDamageValue(), result.getMaxDamage() / 4);
				if (repairedPerItem <= 0) {
					resultSlots.setItem(0, ItemStack.EMPTY);
					return;
				}
				while (repairedPerItem > 0 && repairItemCountCost < addition.getCount()) {
					result.setDamageValue(result.getDamageValue() - repairedPerItem);
					repairItemCountCost++;
					changes++;
					repairedPerItem = Math.min(result.getDamageValue(), result.getMaxDamage() / 4);
				}
			} else {
				if (!enchantedBook && (!result.is(addition.getItem()) || !result.isDamageableItem())) {
					resultSlots.setItem(0, ItemStack.EMPTY);
					return;
				}

				if (result.isDamageableItem() && !enchantedBook) {
					int remainingInput = input.getMaxDamage() - input.getDamageValue();
					int remainingAddition = addition.getMaxDamage() - addition.getDamageValue();
					int repaired = remainingAddition + result.getMaxDamage() * 12 / 100;
					int newDamage = Math.max(0, result.getMaxDamage() - (remainingInput + repaired));
					if (newDamage < result.getDamageValue()) {
						result.setDamageValue(newDamage);
						changes += 2;
					}
				}

				for (Object2IntMap.Entry<Holder<Enchantment>> entry
						: EnchantmentHelper.getEnchantmentsForCrafting(addition).entrySet()) {
					int current = enchantments.getLevel(entry.getKey());
					int supplied = entry.getIntValue();
					int combined = current == supplied ? supplied + 1 : Math.max(current, supplied);
					enchantments.set(entry.getKey(), combined);
					changes += combined;
				}
			}
		}

		if (itemName != null && !StringUtil.isBlank(itemName)) {
			if (!itemName.equals(input.getHoverName().getString())) {
				result.set(DataComponents.CUSTOM_NAME, Component.literal(itemName));
				changes++;
			}
		} else if (input.has(DataComponents.CUSTOM_NAME)) {
			result.remove(DataComponents.CUSTOM_NAME);
			changes++;
		}

		if (changes <= 0) {
			result = ItemStack.EMPTY;
		}
		if (!result.isEmpty()) {
			EnchantmentHelper.setEnchantments(result, enchantments.toImmutable());
		}
		resultSlots.setItem(0, result);
		broadcastChanges();
	}

	public boolean setItemName(String name) {
		String validated = validateName(name);
		if (validated == null || validated.equals(itemName)) {
			return false;
		}
		itemName = validated;
		if (getSlot(2).hasItem()) {
			if (StringUtil.isBlank(validated)) {
				getSlot(2).getItem().remove(DataComponents.CUSTOM_NAME);
			} else {
				getSlot(2).getItem().set(DataComponents.CUSTOM_NAME, Component.literal(validated));
			}
		}
		createResult();
		return true;
	}

	private static String validateName(String name) {
		String filtered = StringUtil.filterText(name);
		return filtered.length() <= 50 ? filtered : null;
	}
}
