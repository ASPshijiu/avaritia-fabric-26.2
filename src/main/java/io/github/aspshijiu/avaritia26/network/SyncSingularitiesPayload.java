package io.github.aspshijiu.avaritia26.network;

import java.util.List;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.singularity.SingularityDefinition;
import io.github.aspshijiu.avaritia26.singularity.SingularityManager;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

/**
 * 奇点定义在服务端由数据包加载，而创造模式页签内容在客户端本地构建。
 * 该 S2C 包在玩家加入与数据包重载时把完整定义推送给客户端，使专用
 * 服务器上的创造页签、物品浏览器能够枚举奇点。
 */
public record SyncSingularitiesPayload(List<SingularityDefinition> definitions) implements CustomPacketPayload {
	public static final Type<SyncSingularitiesPayload> TYPE = new Type<>(Avaritia26.id("sync_singularities"));
	public static final StreamCodec<RegistryFriendlyByteBuf, SyncSingularitiesPayload> STREAM_CODEC =
			SingularityDefinition.STREAM_CODEC
					.apply(ByteBufCodecs.list())
					.map(SyncSingularitiesPayload::new, SyncSingularitiesPayload::definitions);

	@Override
	public Type<SyncSingularitiesPayload> type() {
		return TYPE;
	}

	public static void initialize() {
		PayloadTypeRegistry.clientboundPlay().register(TYPE, STREAM_CODEC);
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) ->
				sender.sendPacket(new SyncSingularitiesPayload(SingularityManager.allDefinitions())));
		ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, resourceManager, success) -> {
			if (!success) {
				return;
			}
			SyncSingularitiesPayload payload = new SyncSingularitiesPayload(SingularityManager.allDefinitions());
			for (ServerPlayer player : server.getPlayerList().getPlayers()) {
				ServerPlayNetworking.send(player, payload);
			}
		});
	}
}
