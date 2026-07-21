package io.github.aspshijiu.avaritia26.network;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.inventory.ExtremeAnvilMenu;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record RenameExtremeAnvilPayload(String name) implements CustomPacketPayload {
	public static final Type<RenameExtremeAnvilPayload> TYPE = new Type<>(Avaritia26.id("rename_extreme_anvil"));
	public static final StreamCodec<RegistryFriendlyByteBuf, RenameExtremeAnvilPayload> STREAM_CODEC =
			StreamCodec.composite(ByteBufCodecs.stringUtf8(200), RenameExtremeAnvilPayload::name,
					RenameExtremeAnvilPayload::new);

	@Override
	public Type<RenameExtremeAnvilPayload> type() {
		return TYPE;
	}

	public static void initialize() {
		PayloadTypeRegistry.serverboundPlay().register(TYPE, STREAM_CODEC);
		ServerPlayNetworking.registerGlobalReceiver(TYPE, (payload, context) ->
				context.server().execute(() -> {
					if (context.player().containerMenu instanceof ExtremeAnvilMenu menu) {
						menu.setItemName(payload.name());
					}
				})
		);
	}
}
