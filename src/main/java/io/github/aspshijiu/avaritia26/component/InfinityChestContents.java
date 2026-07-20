package io.github.aspshijiu.avaritia26.component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public final class InfinityChestContents {
	public static final int SLOTS = 300;
	public static final InfinityChestContents EMPTY = new InfinityChestContents(List.of());
	private static final Codec<ItemStack> HUGE_STACK_CODEC = RecordCodecBuilder.create(instance -> instance.group(
			BuiltInRegistries.ITEM.holderByNameCodec().fieldOf("id").forGetter(ItemStack::typeHolder),
			Codec.intRange(1, Integer.MAX_VALUE).fieldOf("count").forGetter(ItemStack::getCount),
			DataComponentPatch.CODEC.optionalFieldOf("components", DataComponentPatch.EMPTY)
					.forGetter(ItemStack::getComponentsPatch)
	).apply(instance, ItemStack::new));
	private static final StreamCodec<RegistryFriendlyByteBuf, ItemStack> HUGE_STACK_STREAM_CODEC = StreamCodec.of(
			InfinityChestContents::writeStack,
			InfinityChestContents::readStack
	);
	private static final Codec<Entry> ENTRY_CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Codec.intRange(0, SLOTS - 1).fieldOf("slot").forGetter(Entry::slot),
			HUGE_STACK_CODEC.fieldOf("stack").forGetter(Entry::stack)
	).apply(instance, Entry::new));
	private static final StreamCodec<RegistryFriendlyByteBuf, Entry> ENTRY_STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.VAR_INT,
			Entry::slot,
			HUGE_STACK_STREAM_CODEC,
			Entry::stack,
			Entry::new
	);
	public static final Codec<InfinityChestContents> CODEC = ENTRY_CODEC.sizeLimitedListOf(SLOTS)
			.xmap(InfinityChestContents::new, InfinityChestContents::copyEntries);
	public static final StreamCodec<RegistryFriendlyByteBuf, InfinityChestContents> STREAM_CODEC =
			ENTRY_STREAM_CODEC.apply(ByteBufCodecs.list(SLOTS))
					.map(InfinityChestContents::new, InfinityChestContents::copyEntries);

	private final List<Entry> entries;
	private final int hashCode;

	public InfinityChestContents(List<Entry> entries) {
		boolean[] occupied = new boolean[SLOTS];
		List<Entry> copies = new ArrayList<>(entries.size());
		for (Entry entry : entries) {
			if (entry.slot() < 0 || entry.slot() >= SLOTS || entry.stack().isEmpty() || occupied[entry.slot()]) {
				throw new IllegalArgumentException("无尽箱内容包含无效或重复槽位 " + entry.slot());
			}
			occupied[entry.slot()] = true;
			copies.add(new Entry(entry.slot(), entry.stack().copy()));
		}
		copies.sort(Comparator.comparingInt(Entry::slot));
		this.entries = List.copyOf(copies);
		int hash = 1;
		for (Entry entry : this.entries) {
			hash = 31 * hash + entry.slot();
			hash = 31 * hash + ItemStack.hashItemAndComponents(entry.stack());
			hash = 31 * hash + entry.stack().getCount();
		}
		this.hashCode = hash;
	}

	public static InfinityChestContents fromItems(List<ItemStack> items) {
		List<Entry> entries = new ArrayList<>();
		for (int slot = 0; slot < Math.min(items.size(), SLOTS); slot++) {
			ItemStack stack = items.get(slot);
			if (!stack.isEmpty()) {
				entries.add(new Entry(slot, stack.copy()));
			}
		}
		return entries.isEmpty() ? EMPTY : new InfinityChestContents(entries);
	}

	public void copyInto(NonNullList<ItemStack> items) {
		for (int slot = 0; slot < items.size(); slot++) {
			items.set(slot, ItemStack.EMPTY);
		}
		for (Entry entry : entries) {
			if (entry.slot() < items.size()) {
				items.set(entry.slot(), entry.stack().copy());
			}
		}
	}

	public boolean isEmpty() {
		return entries.isEmpty();
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof InfinityChestContents contents) || entries.size() != contents.entries.size()) {
			return false;
		}
		for (int index = 0; index < entries.size(); index++) {
			Entry first = entries.get(index);
			Entry second = contents.entries.get(index);
			if (first.slot() != second.slot() || !ItemStack.matches(first.stack(), second.stack())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	private List<Entry> copyEntries() {
		return entries.stream().map(entry -> new Entry(entry.slot(), entry.stack().copy())).toList();
	}

	private static void writeStack(RegistryFriendlyByteBuf buffer, ItemStack stack) {
		ByteBufCodecs.holderRegistry(Registries.ITEM).encode(buffer, stack.typeHolder());
		buffer.writeVarInt(stack.getCount());
		DataComponentPatch.STREAM_CODEC.encode(buffer, stack.getComponentsPatch());
	}

	private static ItemStack readStack(RegistryFriendlyByteBuf buffer) {
		Holder<Item> item = ByteBufCodecs.holderRegistry(Registries.ITEM).decode(buffer);
		int count = buffer.readVarInt();
		if (count < 1) {
			throw new IllegalArgumentException("无尽箱同步了无效物品数量 " + count);
		}
		return new ItemStack(item, count, DataComponentPatch.STREAM_CODEC.decode(buffer));
	}

	public record Entry(int slot, ItemStack stack) {
	}
}
