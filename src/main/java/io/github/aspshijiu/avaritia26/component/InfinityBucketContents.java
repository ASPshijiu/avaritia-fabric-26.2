package io.github.aspshijiu.avaritia26.component;

import java.util.ArrayList;
import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public record InfinityBucketContents(List<Entry> entries) {
	public static final int MAX_VARIANTS = 1024;
	public static final long CAPACITY_PER_FLUID = (long) Integer.MAX_VALUE * FluidConstants.BUCKET / 1000L;
	public static final InfinityBucketContents EMPTY = new InfinityBucketContents(List.of());
	public static final Codec<InfinityBucketContents> CODEC = Entry.CODEC.listOf()
			.xmap(InfinityBucketContents::new, InfinityBucketContents::entries);
	public static final StreamCodec<RegistryFriendlyByteBuf, InfinityBucketContents> STREAM_CODEC =
			StreamCodec.of(InfinityBucketContents::write, InfinityBucketContents::read);

	public InfinityBucketContents {
		List<Entry> normalized = new ArrayList<>();
		for (Entry entry : entries) {
			if (entry.fluid().isBlank() || entry.amount() <= 0) {
				continue;
			}
			int existingIndex = indexOf(normalized, entry.fluid());
			if (existingIndex >= 0) {
				Entry existing = normalized.get(existingIndex);
				long amount = existing.amount()
						+ Math.min(entry.amount(), CAPACITY_PER_FLUID - existing.amount());
				normalized.set(existingIndex, new Entry(existing.fluid(), amount));
			} else if (normalized.size() < MAX_VARIANTS) {
				normalized.add(new Entry(entry.fluid(), Math.min(CAPACITY_PER_FLUID, entry.amount())));
			}
		}
		entries = List.copyOf(normalized);
	}

	public Entry selected() {
		return entries.isEmpty() ? null : entries.getFirst();
	}

	public long amount(FluidVariant fluid) {
		return entries.stream()
				.filter(entry -> entry.fluid().equals(fluid))
				.mapToLong(Entry::amount)
				.findFirst()
				.orElse(0L);
	}

	public Change fill(FluidVariant fluid, long requested) {
		if (fluid.isBlank() || requested <= 0) {
			return new Change(this, 0);
		}
		List<Entry> updated = new ArrayList<>(entries);
		int index = indexOf(updated, fluid);
		long current = index < 0 ? 0 : updated.remove(index).amount();
		if (index < 0 && updated.size() >= MAX_VARIANTS) {
			return new Change(this, 0);
		}
		long accepted = Math.min(requested, CAPACITY_PER_FLUID - current);
		if (accepted <= 0) {
			return new Change(this, 0);
		}
		updated.addFirst(new Entry(fluid, current + accepted));
		return new Change(new InfinityBucketContents(updated), accepted);
	}

	public Change drainSelected(FluidVariant fluid, long requested) {
		Entry selected = selected();
		if (selected == null || !selected.fluid().equals(fluid) || requested <= 0) {
			return new Change(this, 0);
		}
		long extracted = Math.min(requested, selected.amount());
		List<Entry> updated = new ArrayList<>(entries);
		if (extracted == selected.amount()) {
			updated.removeFirst();
		} else {
			updated.set(0, new Entry(fluid, selected.amount() - extracted));
		}
		return new Change(new InfinityBucketContents(updated), extracted);
	}

	public InfinityBucketContents rotate() {
		if (entries.size() < 2) {
			return this;
		}
		List<Entry> updated = new ArrayList<>(entries);
		updated.addLast(updated.removeFirst());
		return new InfinityBucketContents(updated);
	}

	private static int indexOf(List<Entry> entries, FluidVariant fluid) {
		for (int index = 0; index < entries.size(); index++) {
			if (entries.get(index).fluid().equals(fluid)) {
				return index;
			}
		}
		return -1;
	}

	private static void write(RegistryFriendlyByteBuf buffer, InfinityBucketContents contents) {
		buffer.writeVarInt(contents.entries.size());
		for (Entry entry : contents.entries) {
			FluidVariant.PACKET_CODEC.encode(buffer, entry.fluid());
			buffer.writeVarLong(entry.amount());
		}
	}

	private static InfinityBucketContents read(RegistryFriendlyByteBuf buffer) {
		int size = buffer.readVarInt();
		if (size < 0 || size > MAX_VARIANTS) {
			throw new IllegalArgumentException("无尽桶流体种类数量越界: " + size);
		}
		List<Entry> entries = new ArrayList<>(size);
		for (int index = 0; index < size; index++) {
			entries.add(new Entry(FluidVariant.PACKET_CODEC.decode(buffer), buffer.readVarLong()));
		}
		return new InfinityBucketContents(entries);
	}

	public record Entry(FluidVariant fluid, long amount) {
		private static final Codec<Entry> CODEC = RecordCodecBuilder.create(instance -> instance.group(
				FluidVariant.CODEC.fieldOf("fluid").forGetter(Entry::fluid),
				Codec.LONG.fieldOf("amount").forGetter(Entry::amount)
		).apply(instance, Entry::new));
	}

	public record Change(InfinityBucketContents contents, long amount) {
	}
}
