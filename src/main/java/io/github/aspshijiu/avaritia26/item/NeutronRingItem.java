package io.github.aspshijiu.avaritia26.item;

import io.github.aspshijiu.avaritia26.inventory.NeutronRingMenu;
import io.github.aspshijiu.avaritia26.registry.ModItems;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public final class NeutronRingItem extends Item {
	public NeutronRingItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		if (player.isCrouching()) {
			return InteractionResult.PASS;
		}
		if (!level.isClientSide()) {
			int slot = hand == InteractionHand.MAIN_HAND
					? player.getInventory().getSelectedSlot()
					: Inventory.SLOT_OFFHAND;
			open(player, slot);
		}
		return InteractionResult.SUCCESS;
	}

	public static int findRingSlot(Player player) {
		Inventory inventory = player.getInventory();
		for (int slot = 0; slot < 36; slot++) {
			if (inventory.getItem(slot).is(ModItems.NEUTRON_RING)) {
				return slot;
			}
		}
		return inventory.getItem(Inventory.SLOT_OFFHAND).is(ModItems.NEUTRON_RING)
				? Inventory.SLOT_OFFHAND
				: -1;
	}

	public static void open(Player player, int slot) {
		if (slot < 0 || !player.getInventory().getItem(slot).is(ModItems.NEUTRON_RING)) {
			return;
		}
		player.openMenu(new ExtendedMenuProvider<Integer>() {
			@Override
			public Integer getScreenOpeningData(ServerPlayer player) {
				return slot;
			}

			@Override
			public Component getDisplayName() {
				return Component.translatable("item.avaritia26.neutron_ring");
			}

			@Override
			public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
				return new NeutronRingMenu(containerId, inventory, slot);
			}
		});
	}
}
