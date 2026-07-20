package io.github.aspshijiu.avaritia26.registry;

import com.mojang.serialization.Codec;
import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.component.MatterClusterContents;
import io.github.aspshijiu.avaritia26.component.InfinityChestContents;
import io.github.aspshijiu.avaritia26.singularity.SingularityDefinition;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;

public final class ModDataComponents {
	public static final DataComponentType<Boolean> INFINITY_PICKAXE_HAMMER = Registry.register(
			BuiltInRegistries.DATA_COMPONENT_TYPE,
			Avaritia26.id("infinity_pickaxe_hammer"),
			DataComponentType.<Boolean>builder()
					.persistent(Codec.BOOL)
					.networkSynchronized(ByteBufCodecs.BOOL)
					.build()
	);
	public static final DataComponentType<Boolean> INFINITY_SHOVEL_DESTROYER = Registry.register(
			BuiltInRegistries.DATA_COMPONENT_TYPE,
			Avaritia26.id("infinity_shovel_destroyer"),
			DataComponentType.<Boolean>builder()
					.persistent(Codec.BOOL)
					.networkSynchronized(ByteBufCodecs.BOOL)
					.build()
	);
	public static final DataComponentType<SingularityDefinition> SINGULARITY = Registry.register(
			BuiltInRegistries.DATA_COMPONENT_TYPE,
			Avaritia26.id("singularity"),
			DataComponentType.<SingularityDefinition>builder()
					.persistent(SingularityDefinition.CODEC)
					.networkSynchronized(SingularityDefinition.STREAM_CODEC)
					.build()
	);
	public static final DataComponentType<MatterClusterContents> MATTER_CLUSTER_CONTENTS = Registry.register(
			BuiltInRegistries.DATA_COMPONENT_TYPE,
			Avaritia26.id("matter_cluster_contents"),
			DataComponentType.<MatterClusterContents>builder()
					.persistent(MatterClusterContents.CODEC)
					.networkSynchronized(MatterClusterContents.STREAM_CODEC)
					.build()
	);
	public static final DataComponentType<InfinityChestContents> INFINITY_CHEST_CONTENTS = Registry.register(
			BuiltInRegistries.DATA_COMPONENT_TYPE,
			Avaritia26.id("infinity_chest_contents"),
			DataComponentType.<InfinityChestContents>builder()
					.persistent(InfinityChestContents.CODEC)
					.networkSynchronized(InfinityChestContents.STREAM_CODEC)
					.build()
	);

	private ModDataComponents() {
	}

	public static void initialize() {
	}
}
