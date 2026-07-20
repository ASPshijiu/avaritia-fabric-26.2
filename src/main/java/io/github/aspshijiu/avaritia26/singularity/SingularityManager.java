package io.github.aspshijiu.avaritia26.singularity;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import io.github.aspshijiu.avaritia26.Avaritia26;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.RegistryOps;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

public final class SingularityManager extends SimplePreparableReloadListener<Map<Identifier, SingularityDefinition>>
		implements IdentifiableResourceReloadListener {
	private static volatile Map<Identifier, SingularityDefinition> definitions = Map.of();
	private final RegistryOps<JsonElement> ops;

	private SingularityManager(HolderLookup.Provider registries) {
		ops = registries.createSerializationContext(JsonOps.INSTANCE);
	}

	public static void initialize() {
		ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(
				Avaritia26.id("singularities"),
				SingularityManager::new
		);
	}

	public static SingularityDefinition get(Identifier id) {
		return definitions.get(id);
	}

	public static List<SingularityDefinition> values() {
		return definitions.values().stream()
				.filter(SingularityDefinition::enabled)
				.sorted(Comparator.comparing(definition -> definition.name().toString()))
				.toList();
	}

	@Override
	protected Map<Identifier, SingularityDefinition> prepare(ResourceManager resourceManager, ProfilerFiller profiler) {
		Map<Identifier, SingularityDefinition> loaded = new HashMap<>();
		SimpleJsonResourceReloadListener.scanDirectory(
				resourceManager,
				FileToIdConverter.json("singularities"),
				ops,
				SingularityDefinition.CODEC,
				loaded
		);
		return loaded;
	}

	@Override
	protected void apply(
			Map<Identifier, SingularityDefinition> loaded,
			ResourceManager resourceManager,
			ProfilerFiller profiler
	) {
		loaded.forEach((fileId, definition) -> {
			if (!fileId.equals(definition.name())) {
				throw new IllegalStateException("奇点文件 " + fileId + " 声明了不一致的名称 " + definition.name());
			}
		});
		definitions = Map.copyOf(loaded);
		Avaritia26.LOGGER.info("已加载 {} 个奇点定义", definitions.size());
	}

	@Override
	public Identifier getFabricId() {
		return Avaritia26.id("singularities");
	}
}
