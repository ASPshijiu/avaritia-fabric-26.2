package io.github.aspshijiu.avaritia26.network;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.item.NeutronRingItem;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record OpenNeutronRingPayload() implements CustomPacketPayload {
	public static final OpenNeutronRingPayload INSTANCE = new OpenNeutronRingPayload();
	public static final Type<OpenNeutronRingPayload> TYPE = new Type<>(Avaritia26.id("open_neutron_ring"));
	public static final StreamCodec<net.minecraft.network.RegistryFriendlyByteBuf, OpenNeutronRingPayload> STREAM_CODEC =
			StreamCodec.unit(INSTANCE);

	@Override
	public Type<OpenNeutronRingPayload> type() {
		return TYPE;
	}

	public static void initialize() {
		PayloadTypeRegistry.serverboundPlay().register(TYPE, STREAM_CODEC);
		ServerPlayNetworking.registerGlobalReceiver(TYPE, (payload, context) ->
				context.server().execute(() -> {
					int slot = NeutronRingItem.findRingSlot(context.player());
					NeutronRingItem.open(context.player(), slot);
				})
		);
	}
}
