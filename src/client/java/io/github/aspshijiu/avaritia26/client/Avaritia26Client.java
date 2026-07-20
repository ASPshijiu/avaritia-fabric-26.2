package io.github.aspshijiu.avaritia26.client;

import io.github.aspshijiu.avaritia26.client.screen.ExtremeCraftingScreen;
import io.github.aspshijiu.avaritia26.registry.ModMenus;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;

public final class Avaritia26Client implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		MenuScreens.register(ModMenus.EXTREME_CRAFTING, ExtremeCraftingScreen::new);
	}
}
