package io.github.aspshijiu.avaritia26.component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;

public final class MatterClusterContents {
	public static final int MAX_SLOTS = 4096;
	public static final MatterClusterContents EMPTY = new MatterClusterContents(List.of());
	public static final Codec<MatterClusterContents> CODEC = ItemStack.OPTIONAL_CODEC
			.sizeLimitedListOf(MAX_SLOTS)
			.xmap(MatterClusterContents::new, MatterClusterContents::copyItems);
	public static final StreamCodec<RegistryFriendlyByteBuf, MatterClusterContents> STREAM_CODEC =
			ItemStack.OPTIONAL_STREAM_CODEC
					.apply(ByteBufCodecs.list(MAX_SLOTS))
					.map(MatterClusterContents::new, MatterClusterContents::copyItems);

	private final List<ItemStack> items;
	private final int hashCode;

	public MatterClusterContents(List<ItemStack> items) {
		if (items.size() > MAX_SLOTS) {
			throw new IllegalArgumentException("物质团槽位不能超过 " + MAX_SLOTS);
		}
		this.items = items.stream().map(ItemStack::copy).toList();
		this.hashCode = ItemStack.hashStackList(this.items);
	}

	public List<ItemStack> copyItems() {
		return items.stream().map(ItemStack::copy).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
	}

	public Stream<ItemStack> nonEmptyItems() {
		return items.stream().filter(stack -> !stack.isEmpty()).map(ItemStack::copy);
	}

	@Override
	public boolean equals(Object other) {
		return this == other
				|| other instanceof MatterClusterContents contents && ItemStack.listMatches(items, contents.items);
	}

	@Override
	public int hashCode() {
		return hashCode;
	}
}
