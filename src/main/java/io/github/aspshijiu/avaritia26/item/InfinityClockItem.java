package io.github.aspshijiu.avaritia26.item;

import java.util.List;

import io.github.aspshijiu.avaritia26.component.ClockAccelerationData;
import io.github.aspshijiu.avaritia26.inventory.InfinityClockMenu;
import io.github.aspshijiu.avaritia26.registry.ModDataComponents;
import io.github.aspshijiu.avaritia26.registry.ModItems;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public final class InfinityClockItem extends Item {
	private static final List<Integer> MULTIPLIERS = List.of(1, 4, 16, 64, 256, 512);

	public InfinityClockItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (player.isShiftKeyDown()) {
			boolean overclock = !isOverclock(stack);
			setOverclock(stack, overclock);
			message(player, overclock
					? "tooltip.avaritia26.infinity_clock.overclock_enabled"
					: "tooltip.avaritia26.infinity_clock.overclock_disabled");
			player.swing(hand, true);
			return InteractionResult.SUCCESS;
		}
		if (isOverclock(stack)) {
			int next = nextMultiplier(getMultiplier(stack));
			stack.set(ModDataComponents.INFINITY_CLOCK_MULTIPLIER, next);
			if (player instanceof ServerPlayer serverPlayer && serverPlayer.connection != null) {
				serverPlayer.sendSystemMessage(Component.literal(next + "x"), true);
			}
			return InteractionResult.SUCCESS;
		}
		if (!level.isClientSide()) {
			int slot = hand == InteractionHand.MAIN_HAND
					? player.getInventory().getSelectedSlot()
					: Inventory.SLOT_OFFHAND;
			open(player, slot);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		if (!(context.getLevel() instanceof ServerLevel level)) {
			return InteractionResult.SUCCESS;
		}
		ItemStack stack = context.getItemInHand();
		ClockAccelerationData data = level.getDataStorage().computeIfAbsent(ClockAccelerationData.TYPE);
		if (!isOverclock(stack) || getMultiplier(stack) <= 1) {
			data.remove(context.getClickedPos());
		} else {
			data.set(context.getClickedPos(), getMultiplier(stack));
		}
		return InteractionResult.CONSUME;
	}

	public static boolean isOverclock(ItemStack stack) {
		return stack.getOrDefault(ModDataComponents.INFINITY_CLOCK_OVERCLOCK, false);
	}

	public static void setOverclock(ItemStack stack, boolean overclock) {
		stack.set(ModDataComponents.INFINITY_CLOCK_OVERCLOCK, overclock);
		stack.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(
				List.of(overclock ? 1.0F : 0.0F),
				List.of(),
				List.of(),
				List.of()
		));
	}

	public static int getMultiplier(ItemStack stack) {
		int multiplier = stack.getOrDefault(ModDataComponents.INFINITY_CLOCK_MULTIPLIER, 1);
		return MULTIPLIERS.contains(multiplier) ? multiplier : 1;
	}

	private static int nextMultiplier(int current) {
		int index = MULTIPLIERS.indexOf(current);
		return MULTIPLIERS.get((index + 1) % MULTIPLIERS.size());
	}

	private static void open(Player player, int slot) {
		if (slot < 0 || !player.getInventory().getItem(slot).is(ModItems.INFINITY_CLOCK)) {
			return;
		}
		player.openMenu(new ExtendedMenuProvider<Integer>() {
			@Override
			public Integer getScreenOpeningData(ServerPlayer player) {
				return slot;
			}

			@Override
			public Component getDisplayName() {
				return Component.translatable("item.avaritia26.infinity_clock");
			}

			@Override
			public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
				return new InfinityClockMenu(containerId, inventory, slot);
			}
		});
	}

	private static void message(Player player, String key) {
		if (player instanceof ServerPlayer serverPlayer && serverPlayer.connection != null) {
			serverPlayer.sendSystemMessage(Component.translatable(key), true);
		}
	}
}
