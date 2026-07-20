package io.github.aspshijiu.avaritia26.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.aspshijiu.avaritia26.Avaritia26;
import net.minecraft.core.BlockPos;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;

public final class ClockAccelerationData extends SavedData {
	private static final Codec<Entry> ENTRY_CODEC = RecordCodecBuilder.create(instance -> instance.group(
			BlockPos.CODEC.fieldOf("pos").forGetter(Entry::pos),
			Codec.INT.fieldOf("multiplier").forGetter(Entry::multiplier)
	).apply(instance, Entry::new));
	private static final Codec<ClockAccelerationData> CODEC = ENTRY_CODEC.listOf().xmap(
			ClockAccelerationData::new,
			data -> data.accelerations.entrySet().stream()
					.map(entry -> new Entry(entry.getKey(), entry.getValue()))
					.toList()
	);
	public static final SavedDataType<ClockAccelerationData> TYPE = new SavedDataType<>(
			Avaritia26.id("infinity_clock_accelerations"),
			ClockAccelerationData::new,
			CODEC,
			DataFixTypes.SAVED_DATA_COMMAND_STORAGE
	);

	private final Map<BlockPos, Integer> accelerations = new HashMap<>();

	public ClockAccelerationData() {
	}

	private ClockAccelerationData(List<Entry> entries) {
		entries.stream()
				.filter(entry -> entry.multiplier() > 1)
				.forEach(entry -> accelerations.put(entry.pos().immutable(), Math.min(entry.multiplier(), 512)));
	}

	public Map<BlockPos, Integer> entries() {
		return Map.copyOf(accelerations);
	}

	public int get(BlockPos pos) {
		return accelerations.getOrDefault(pos, 1);
	}

	public void set(BlockPos pos, int multiplier) {
		if (multiplier <= 1) {
			remove(pos);
			return;
		}
		accelerations.put(pos.immutable(), Math.min(multiplier, 512));
		setDirty();
	}

	public void remove(BlockPos pos) {
		if (accelerations.remove(pos) != null) {
			setDirty();
		}
	}

	private record Entry(BlockPos pos, int multiplier) {
	}
}
