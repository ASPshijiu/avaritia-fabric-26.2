package io.github.aspshijiu.avaritia26.client;

import io.github.aspshijiu.avaritia26.client.render.HeavenArrowRenderer;
import io.github.aspshijiu.avaritia26.client.screen.CompressedChestScreen;
import io.github.aspshijiu.avaritia26.client.screen.ExtremeCraftingScreen;
import io.github.aspshijiu.avaritia26.client.screen.ExtremeSmithingScreen;
import io.github.aspshijiu.avaritia26.client.screen.InfinityChestScreen;
import io.github.aspshijiu.avaritia26.client.screen.NeutronCollectorScreen;
import io.github.aspshijiu.avaritia26.client.screen.NeutronCompressorScreen;
import io.github.aspshijiu.avaritia26.client.screen.SculkCraftingScreen;
import io.github.aspshijiu.avaritia26.entity.EndestPearlEntity;
import io.github.aspshijiu.avaritia26.entity.GapingVoidEntity;
import io.github.aspshijiu.avaritia26.registry.ModEntityTypes;
import io.github.aspshijiu.avaritia26.registry.ModMenus;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public final class Avaritia26Client implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		MenuScreens.register(ModMenus.COMPRESSED_CHEST, CompressedChestScreen::new);
		MenuScreens.register(ModMenus.EXTREME_CRAFTING, ExtremeCraftingScreen::new);
		MenuScreens.register(ModMenus.SCULK_CRAFTING, SculkCraftingScreen::new);
		MenuScreens.register(ModMenus.EXTREME_SMITHING, ExtremeSmithingScreen::new);
		MenuScreens.register(ModMenus.INFINITY_CHEST, InfinityChestScreen::new);
		MenuScreens.register(ModMenus.NEUTRON_COLLECTOR, NeutronCollectorScreen::new);
		MenuScreens.register(ModMenus.NEUTRON_COMPRESSOR, NeutronCompressorScreen::new);
		EntityRendererRegistry.register(ModEntityTypes.ENDEST_PEARL, ThrownItemRenderer<EndestPearlEntity>::new);
		EntityRendererRegistry.register(ModEntityTypes.GAPING_VOID, NoopRenderer<GapingVoidEntity>::new);
		EntityRendererRegistry.register(ModEntityTypes.HEAVEN_ARROW, context -> new HeavenArrowRenderer<>(context));
		EntityRendererRegistry.register(ModEntityTypes.HEAVEN_SUB_ARROW, context -> new HeavenArrowRenderer<>(context));
	}
}
