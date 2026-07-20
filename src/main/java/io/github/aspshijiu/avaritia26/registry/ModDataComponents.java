package io.github.aspshijiu.avaritia26.registry;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.component.MatterClusterContents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;

public final class ModDataComponents {
	public static final DataComponentType<MatterClusterContents> MATTER_CLUSTER_CONTENTS = Registry.register(
			BuiltInRegistries.DATA_COMPONENT_TYPE,
			Avaritia26.id("matter_cluster_contents"),
			DataComponentType.<MatterClusterContents>builder()
					.persistent(MatterClusterContents.CODEC)
					.networkSynchronized(MatterClusterContents.STREAM_CODEC)
					.build()
	);

	private ModDataComponents() {
	}

	public static void initialize() {
	}
}
