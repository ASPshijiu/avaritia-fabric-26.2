package io.github.aspshijiu.avaritia26.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import io.github.aspshijiu.avaritia26.component.MatterClusterContents;
import io.github.aspshijiu.avaritia26.registry.ModDataComponents;
import io.github.aspshijiu.avaritia26.registry.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

public final class MatterClusterItem extends Item {
	public static final int CAPACITY = 64 * 64;

	public MatterClusterItem(Properties properties) {
		super(properties);
	}

	public static List<ItemStack> getItems(ItemStack cluster) {
		return cluster.getOrDefault(ModDataComponents.MATTER_CLUSTER_CONTENTS, MatterClusterContents.EMPTY)
				.copyItems();
	}

	public static int getSize(ItemStack cluster) {
		return getItems(cluster).stream().mapToInt(ItemStack::getCount).sum();
	}

	public static List<ItemStack> createClusters(Collection<ItemStack> input) {
		List<ItemStack> clusters = new ArrayList<>();
		List<ItemStack> currentItems = new ArrayList<>();
		int currentSize = 0;
		for (ItemStack source : input) {
			ItemStack remaining = source.copy();
			while (!remaining.isEmpty()) {
				if (currentSize == CAPACITY) {
					clusters.add(createCluster(currentItems));
					currentItems = new ArrayList<>();
					currentSize = 0;
				}
				int moved = insert(currentItems, remaining, CAPACITY - currentSize);
				if (moved == 0) {
					throw new IllegalStateException("物质团无法插入物品 " + remaining);
				}
				currentSize += moved;
			}
		}
		if (currentSize > 0) {
			clusters.add(createCluster(currentItems));
		}
		return clusters;
	}

	public static boolean merge(ItemStack from, ItemStack to) {
		if (!from.is(ModItems.MATTER_CLUSTER) || !to.is(ModItems.MATTER_CLUSTER)) {
			return false;
		}
		List<ItemStack> targetItems = getItems(to);
		List<ItemStack> sourceItems = getItems(from);
		int targetSize = targetItems.stream().mapToInt(ItemStack::getCount).sum();
		boolean movedAny = false;
		for (ItemStack source : sourceItems) {
			if (targetSize >= CAPACITY) {
				break;
			}
			int moved = insert(targetItems, source, CAPACITY - targetSize);
			targetSize += moved;
			movedAny |= moved > 0;
		}
		if (!movedAny) {
			return false;
		}
		to.set(ModDataComponents.MATTER_CLUSTER_CONTENTS, new MatterClusterContents(targetItems));
		sourceItems.removeIf(ItemStack::isEmpty);
		if (sourceItems.isEmpty()) {
			from.shrink(1);
		} else {
			from.set(ModDataComponents.MATTER_CLUSTER_CONTENTS, new MatterClusterContents(sourceItems));
		}
		return true;
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack cluster = player.getItemInHand(hand);
		if (!level.isClientSide()) {
			for (ItemStack item : getItems(cluster)) {
				Containers.dropItemStack(level, player.getX(), player.getY() + 0.5, player.getZ(), item);
			}
			cluster.shrink(1);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public void appendHoverText(
			ItemStack stack,
			TooltipContext context,
			TooltipDisplay display,
			Consumer<Component> tooltip,
			TooltipFlag flag
	) {
		List<ItemStack> items = getItems(stack);
		tooltip.accept(Component.translatable("tooltip.avaritia26.matter_cluster.counter", getSize(stack), CAPACITY));
		for (int index = 0; index < Math.min(items.size(), 5); index++) {
			ItemStack item = items.get(index);
			tooltip.accept(
					item.getHoverName().copy()
							.withStyle(ChatFormatting.WHITE)
							.append(Component.literal(" x " + item.getCount()).withStyle(ChatFormatting.GRAY))
			);
		}
		if (items.size() > 5) {
			tooltip.accept(
					Component.translatable("tooltip.avaritia26.matter_cluster.more", items.size() - 5)
							.withStyle(ChatFormatting.DARK_GRAY)
			);
		}
	}

	@Override
	public boolean isBarVisible(ItemStack stack) {
		return getSize(stack) > 0;
	}

	@Override
	public int getBarWidth(ItemStack stack) {
		return Math.clamp(Math.round(13.0F * getSize(stack) / CAPACITY), 0, 13);
	}

	@Override
	public int getBarColor(ItemStack stack) {
		return 0x7F3FFF;
	}

	private static ItemStack createCluster(List<ItemStack> items) {
		ItemStack cluster = new ItemStack(ModItems.MATTER_CLUSTER);
		cluster.set(ModDataComponents.MATTER_CLUSTER_CONTENTS, new MatterClusterContents(items));
		return cluster;
	}

	private static int insert(List<ItemStack> target, ItemStack source, int capacity) {
		int moved = 0;
		for (ItemStack existing : target) {
			if (source.isEmpty() || moved == capacity) {
				break;
			}
			if (!ItemStack.isSameItemSameComponents(existing, source)) {
				continue;
			}
			int count = Math.min(Math.min(source.getCount(), existing.getMaxStackSize() - existing.getCount()), capacity - moved);
			if (count > 0) {
				existing.grow(count);
				source.shrink(count);
				moved += count;
			}
		}
		while (!source.isEmpty() && moved < capacity) {
			int count = Math.min(Math.min(source.getCount(), source.getMaxStackSize()), capacity - moved);
			target.add(source.copyWithCount(count));
			source.shrink(count);
			moved += count;
		}
		return moved;
	}
}
