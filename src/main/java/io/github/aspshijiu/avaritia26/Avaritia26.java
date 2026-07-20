package io.github.aspshijiu.avaritia26;

import io.github.aspshijiu.avaritia26.crafting.ModRecipes;
import io.github.aspshijiu.avaritia26.registry.ModBlockEntities;
import io.github.aspshijiu.avaritia26.registry.ModBlocks;
import io.github.aspshijiu.avaritia26.registry.ModCreativeTabs;
import io.github.aspshijiu.avaritia26.registry.ModItems;
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
		ModItems.initialize();
		ModBlocks.initialize();
		ModBlockEntities.initialize();
		ModCreativeTabs.initialize();
		LOGGER.info("Avaritia 26 已初始化");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
