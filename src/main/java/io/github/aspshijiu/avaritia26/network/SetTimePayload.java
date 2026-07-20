package io.github.aspshijiu.avaritia26.network;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.inventory.InfinityClockMenu;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;

public record SetTimePayload(int time) implements CustomPacketPayload {
	public static final Type<SetTimePayload> TYPE = new Type<>(Avaritia26.id("set_time"));
	public static final StreamCodec<RegistryFriendlyByteBuf, SetTimePayload> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.INT,
			SetTimePayload::time,
			SetTimePayload::new
	);

	@Override
	public Type<SetTimePayload> type() {
		return TYPE;
	}

	public static void initialize() {
		PayloadTypeRegistry.serverboundPlay().register(TYPE, STREAM_CODEC);
		ServerPlayNetworking.registerGlobalReceiver(TYPE, (payload, context) ->
				context.server().execute(() -> {
					if (!(context.player().containerMenu instanceof InfinityClockMenu)) {
						return;
					}
					setTime(context.server(), payload.time());
				})
		);
	}

	public static void setTime(MinecraftServer server, int time) {
		server.getAllLevels().forEach(level ->
				level.dimensionType().defaultClock().ifPresent(clock -> {
					long day = level.clockManager().getTotalTicks(clock) / 24000L;
					level.clockManager().setTotalTicks(clock, day * 24000L + time);
				})
		);
	}
}
