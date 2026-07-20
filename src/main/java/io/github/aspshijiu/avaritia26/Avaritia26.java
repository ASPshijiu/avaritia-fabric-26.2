package io.github.aspshijiu.avaritia26;

import io.github.aspshijiu.avaritia26.crafting.ModRecipes;
import io.github.aspshijiu.avaritia26.event.ModCombatEvents;
import io.github.aspshijiu.avaritia26.event.ModClockEvents;
import io.github.aspshijiu.avaritia26.event.ModArmorEvents;
import io.github.aspshijiu.avaritia26.event.ModToolEvents;
import io.github.aspshijiu.avaritia26.network.OpenNeutronRingPayload;
import io.github.aspshijiu.avaritia26.network.SetTimePayload;
import io.github.aspshijiu.avaritia26.registry.ModBlockEntities;
import io.github.aspshijiu.avaritia26.registry.ModBlocks;
import io.github.aspshijiu.avaritia26.registry.ModCreativeTabs;
import io.github.aspshijiu.avaritia26.registry.ModDataComponents;
import io.github.aspshijiu.avaritia26.registry.ModEntityTypes;
import io.github.aspshijiu.avaritia26.registry.ModItems;
import io.github.aspshijiu.avaritia26.registry.ModMenus;
import io.github.aspshijiu.avaritia26.registry.ModSounds;
import io.github.aspshijiu.avaritia26.singularity.SingularityManager;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Avaritia26 implements ModInitializer {
	public static final String MOD_ID = "avaritia26";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModRecipes.initialize();
		ModDataComponents.initialize();
		ModItems.initialize();
		ModArmorEvents.initialize();
		ModCombatEvents.initialize();
		ModClockEvents.initialize();
		ModToolEvents.initialize();
		OpenNeutronRingPayload.initialize();
		SetTimePayload.initialize();
		ModEntityTypes.initialize();
		ModSounds.initialize();
		ModBlocks.initialize();
		ModBlockEntities.initialize();
		ModMenus.initialize();
		ModCreativeTabs.initialize();
		SingularityManager.initialize();
		LOGGER.info("Avaritia 26 已初始化");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
