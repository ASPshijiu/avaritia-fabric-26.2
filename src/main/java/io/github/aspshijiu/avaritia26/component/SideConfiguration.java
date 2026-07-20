package io.github.aspshijiu.avaritia26.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Direction;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public record SideConfiguration(
		Mode north,
		Mode south,
		Mode east,
		Mode west,
		Mode up,
		Mode down
) {
	public static final Codec<SideConfiguration> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Mode.CODEC.fieldOf("north").forGetter(SideConfiguration::north),
			Mode.CODEC.fieldOf("south").forGetter(SideConfiguration::south),
			Mode.CODEC.fieldOf("east").forGetter(SideConfiguration::east),
			Mode.CODEC.fieldOf("west").forGetter(SideConfiguration::west),
			Mode.CODEC.fieldOf("up").forGetter(SideConfiguration::up),
			Mode.CODEC.fieldOf("down").forGetter(SideConfiguration::down)
	).apply(instance, SideConfiguration::new));
	public static final StreamCodec<RegistryFriendlyByteBuf, SideConfiguration> STREAM_CODEC =
			StreamCodec.of(SideConfiguration::write, SideConfiguration::read);
	public static final SideConfiguration COLLECTOR_DEFAULT = all(Mode.PASSIVE_OUTPUT);
	public static final SideConfiguration COMPRESSOR_DEFAULT = new SideConfiguration(
			Mode.PASSIVE_INPUT,
			Mode.PASSIVE_INPUT,
			Mode.PASSIVE_INPUT,
			Mode.PASSIVE_INPUT,
			Mode.PASSIVE_INPUT,
			Mode.PASSIVE_OUTPUT
	);

	public Mode mode(Direction side) {
		return switch (side) {
			case NORTH -> north;
			case SOUTH -> south;
			case EAST -> east;
			case WEST -> west;
			case UP -> up;
			case DOWN -> down;
		};
	}

	public SideConfiguration with(Direction side, Mode mode) {
		return new SideConfiguration(
				side == Direction.NORTH ? mode : north,
				side == Direction.SOUTH ? mode : south,
				side == Direction.EAST ? mode : east,
				side == Direction.WEST ? mode : west,
				side == Direction.UP ? mode : up,
				side == Direction.DOWN ? mode : down
		);
	}

	public SideConfiguration outputsOnly() {
		SideConfiguration result = this;
		for (Direction side : Direction.values()) {
			Mode mode = mode(side);
			if (mode != Mode.PASSIVE_OUTPUT && mode != Mode.ACTIVE_OUTPUT) {
				result = result.with(side, Mode.OFF);
			}
		}
		return result;
	}

	public static Direction relativeSide(Direction absoluteSide, Direction facing) {
		if (absoluteSide.getAxis() == Direction.Axis.Y) {
			return absoluteSide;
		}
		if (absoluteSide == facing) {
			return Direction.NORTH;
		}
		if (absoluteSide == facing.getOpposite()) {
			return Direction.SOUTH;
		}
		return absoluteSide == facing.getCounterClockWise() ? Direction.EAST : Direction.WEST;
	}

	private static SideConfiguration all(Mode mode) {
		return new SideConfiguration(mode, mode, mode, mode, mode, mode);
	}

	private static void write(RegistryFriendlyByteBuf buffer, SideConfiguration configuration) {
		for (Direction side : Direction.values()) {
			buffer.writeByte(configuration.mode(side).ordinal());
		}
	}

	private static SideConfiguration read(RegistryFriendlyByteBuf buffer) {
		SideConfiguration configuration = all(Mode.OFF);
		for (Direction side : Direction.values()) {
			int ordinal = buffer.readUnsignedByte();
			Mode mode = ordinal < Mode.values().length ? Mode.values()[ordinal] : Mode.OFF;
			configuration = configuration.with(side, mode);
		}
		return configuration;
	}

	public enum Mode {
		OFF("off", false, false),
		PASSIVE_INPUT("passive_input", true, false),
		PASSIVE_OUTPUT("passive_output", false, true),
		PASSIVE_MIXIN("passive_mixin", true, true),
		ACTIVE_INPUT("active_input", true, false),
		ACTIVE_OUTPUT("active_output", false, true),
		ACTIVE_MIXIN("active_mixin", true, true);

		private static final Codec<Mode> CODEC = Codec.STRING.xmap(Mode::fromName, Mode::serializedName);
		private final String serializedName;
		private final boolean input;
		private final boolean output;

		Mode(String serializedName, boolean input, boolean output) {
			this.serializedName = serializedName;
			this.input = input;
			this.output = output;
		}

		public boolean canInput() {
			return input;
		}

		public boolean canOutput() {
			return output;
		}

		private String serializedName() {
			return serializedName;
		}

		private static Mode fromName(String name) {
			for (Mode mode : values()) {
				if (mode.serializedName.equals(name)) {
					return mode;
				}
			}
			return OFF;
		}
	}
}
