package io.github.aspshijiu.avaritia26.registry;

import com.mojang.serialization.Codec;
import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.component.InfinityBucketContents;
import io.github.aspshijiu.avaritia26.component.InfinityChestContents;
import io.github.aspshijiu.avaritia26.component.MatterClusterContents;
import io.github.aspshijiu.avaritia26.component.SideConfiguration;
import io.github.aspshijiu.avaritia26.singularity.SingularityDefinition;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.item.component.ItemContainerContents;

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
	public static final DataComponentType<Boolean> INFINITY_CLOCK_OVERCLOCK = Registry.register(
			BuiltInRegistries.DATA_COMPONENT_TYPE,
			Avaritia26.id("infinity_clock_overclock"),
			DataComponentType.<Boolean>builder()
					.persistent(Codec.BOOL)
					.networkSynchronized(ByteBufCodecs.BOOL)
					.build()
	);
	public static final DataComponentType<Integer> INFINITY_CLOCK_MULTIPLIER = Registry.register(
			BuiltInRegistries.DATA_COMPONENT_TYPE,
			Avaritia26.id("infinity_clock_multiplier"),
			DataComponentType.<Integer>builder()
					.persistent(Codec.INT)
					.networkSynchronized(ByteBufCodecs.INT)
					.build()
	);
	public static final DataComponentType<Boolean> INFINITY_CROSSBOW_MULTI = Registry.register(
			BuiltInRegistries.DATA_COMPONENT_TYPE,
			Avaritia26.id("infinity_crossbow_multi"),
			DataComponentType.<Boolean>builder()
					.persistent(Codec.BOOL)
					.networkSynchronized(ByteBufCodecs.BOOL)
					.build()
	);
	public static final DataComponentType<Integer> INFINITY_TRIDENT_MODE = Registry.register(
			BuiltInRegistries.DATA_COMPONENT_TYPE,
			Avaritia26.id("infinity_trident_mode"),
			DataComponentType.<Integer>builder()
					.persistent(Codec.INT)
					.networkSynchronized(ByteBufCodecs.INT)
					.build()
	);
	public static final DataComponentType<Boolean> CRYSTAL_BLADE_SLASH = Registry.register(
			BuiltInRegistries.DATA_COMPONENT_TYPE,
			Avaritia26.id("crystal_blade_slash"),
			DataComponentType.<Boolean>builder()
					.persistent(Codec.BOOL)
					.networkSynchronized(ByteBufCodecs.BOOL)
					.build()
	);
	public static final DataComponentType<Boolean> BLAZE_TOOL_MODE = Registry.register(
			BuiltInRegistries.DATA_COMPONENT_TYPE,
			Avaritia26.id("blaze_tool_mode"),
			DataComponentType.<Boolean>builder()
					.persistent(Codec.BOOL)
					.networkSynchronized(ByteBufCodecs.BOOL)
					.build()
	);
	public static final DataComponentType<Boolean> BLAZE_BOW_BURNING = Registry.register(
			BuiltInRegistries.DATA_COMPONENT_TYPE,
			Avaritia26.id("blaze_bow_burning"),
			DataComponentType.<Boolean>builder()
					.persistent(Codec.BOOL)
					.networkSynchronized(ByteBufCodecs.BOOL)
					.build()
	);
	public static final DataComponentType<SideConfiguration> SIDE_CONFIGURATION = Registry.register(
			BuiltInRegistries.DATA_COMPONENT_TYPE,
			Avaritia26.id("side_configuration"),
			DataComponentType.<SideConfiguration>builder()
					.persistent(SideConfiguration.CODEC)
					.networkSynchronized(SideConfiguration.STREAM_CODEC)
					.build()
	);
	public static final DataComponentType<InfinityBucketContents> INFINITY_BUCKET_CONTENTS = Registry.register(
			BuiltInRegistries.DATA_COMPONENT_TYPE,
			Avaritia26.id("infinity_bucket_contents"),
			DataComponentType.<InfinityBucketContents>builder()
					.persistent(InfinityBucketContents.CODEC)
					.networkSynchronized(InfinityBucketContents.STREAM_CODEC)
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
	public static final DataComponentType<ItemContainerContents> NEUTRON_RING_INVENTORY = Registry.register(
			BuiltInRegistries.DATA_COMPONENT_TYPE,
			Avaritia26.id("neutron_ring_inventory"),
			DataComponentType.<ItemContainerContents>builder()
					.persistent(ItemContainerContents.CODEC)
					.networkSynchronized(ItemContainerContents.STREAM_CODEC)
					.build()
	);

	private ModDataComponents() {
	}

	public static void initialize() {
	}
}
