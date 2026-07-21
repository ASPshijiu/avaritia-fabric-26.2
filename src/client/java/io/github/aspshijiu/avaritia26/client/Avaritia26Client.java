package io.github.aspshijiu.avaritia26.client;

import com.mojang.blaze3d.platform.InputConstants;
import io.github.aspshijiu.avaritia26.client.render.BladeSlashRenderer;
import io.github.aspshijiu.avaritia26.client.render.BurningArrowRenderer;
import io.github.aspshijiu.avaritia26.client.render.HeavenArrowRenderer;
import io.github.aspshijiu.avaritia26.client.render.NeutronArrowRenderer;
import io.github.aspshijiu.avaritia26.client.screen.CompressedChestScreen;
import io.github.aspshijiu.avaritia26.client.screen.EndCraftingScreen;
import io.github.aspshijiu.avaritia26.client.screen.ExtremeCraftingScreen;
import io.github.aspshijiu.avaritia26.client.screen.ExtremeSmithingScreen;
import io.github.aspshijiu.avaritia26.client.screen.InfinityChestScreen;
import io.github.aspshijiu.avaritia26.client.screen.InfinityClockScreen;
import io.github.aspshijiu.avaritia26.client.screen.NetherCraftingScreen;
import io.github.aspshijiu.avaritia26.client.screen.NeutronCollectorScreen;
import io.github.aspshijiu.avaritia26.client.screen.NeutronCompressorScreen;
import io.github.aspshijiu.avaritia26.client.screen.NeutronRingScreen;
import io.github.aspshijiu.avaritia26.client.screen.SculkCraftingScreen;
import io.github.aspshijiu.avaritia26.entity.BlazeFireballEntity;
import io.github.aspshijiu.avaritia26.entity.BurningBallEntity;
import io.github.aspshijiu.avaritia26.entity.EndestPearlEntity;
import io.github.aspshijiu.avaritia26.entity.GapingVoidEntity;
import io.github.aspshijiu.avaritia26.entity.UmbrellaProjectileEntity;
import io.github.aspshijiu.avaritia26.network.OpenNeutronRingPayload;
import io.github.aspshijiu.avaritia26.registry.ModEntityTypes;
import io.github.aspshijiu.avaritia26.registry.ModMenus;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.ThrownTridentRenderer;
import org.lwjgl.glfw.GLFW;

public final class Avaritia26Client implements ClientModInitializer {
	public static final KeyMapping OPEN_NEUTRON_RING = KeyMappingHelper.registerKeyMapping(new KeyMapping(
			"key.avaritia26.neutron_ring",
			InputConstants.Type.KEYSYM,
			GLFW.GLFW_KEY_N,
			KeyMapping.Category.INVENTORY
	));

	@Override
	public void onInitializeClient() {
		MenuScreens.register(ModMenus.COMPRESSED_CHEST, CompressedChestScreen::new);
		MenuScreens.register(ModMenus.EXTREME_CRAFTING, ExtremeCraftingScreen::new);
		MenuScreens.register(ModMenus.SCULK_CRAFTING, SculkCraftingScreen::new);
		MenuScreens.register(ModMenus.NETHER_CRAFTING, NetherCraftingScreen::new);
		MenuScreens.register(ModMenus.END_CRAFTING, EndCraftingScreen::new);
		MenuScreens.register(ModMenus.EXTREME_SMITHING, ExtremeSmithingScreen::new);
		MenuScreens.register(ModMenus.INFINITY_CHEST, InfinityChestScreen::new);
		MenuScreens.register(ModMenus.INFINITY_CLOCK, InfinityClockScreen::new);
		MenuScreens.register(ModMenus.NEUTRON_COLLECTOR, NeutronCollectorScreen::new);
		MenuScreens.register(ModMenus.NEUTRON_COMPRESSOR, NeutronCompressorScreen::new);
		MenuScreens.register(ModMenus.NEUTRON_RING, NeutronRingScreen::new);
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (OPEN_NEUTRON_RING.consumeClick() && client.player != null) {
				ClientPlayNetworking.send(OpenNeutronRingPayload.INSTANCE);
			}
		});
		EntityRendererRegistry.register(ModEntityTypes.ENDEST_PEARL, ThrownItemRenderer<EndestPearlEntity>::new);
		EntityRendererRegistry.register(ModEntityTypes.BLAZE_FIREBALL,
				ThrownItemRenderer<BlazeFireballEntity>::new);
		EntityRendererRegistry.register(ModEntityTypes.BURNING_ARROW, BurningArrowRenderer::new);
		EntityRendererRegistry.register(ModEntityTypes.BURNING_BALL,
				context -> new ThrownItemRenderer<BurningBallEntity>(context, 2.0F, true));
		EntityRendererRegistry.register(ModEntityTypes.BLADE_SLASH, BladeSlashRenderer::new);
		EntityRendererRegistry.register(ModEntityTypes.GAPING_VOID, NoopRenderer<GapingVoidEntity>::new);
		EntityRendererRegistry.register(ModEntityTypes.HEAVEN_ARROW, context -> new HeavenArrowRenderer<>(context));
		EntityRendererRegistry.register(ModEntityTypes.HEAVEN_SUB_ARROW, context -> new HeavenArrowRenderer<>(context));
		EntityRendererRegistry.register(ModEntityTypes.NEUTRON_ARROW, NeutronArrowRenderer::new);
		EntityRendererRegistry.register(ModEntityTypes.INFINITY_THROWN_TRIDENT, ThrownTridentRenderer::new);
		EntityRendererRegistry.register(ModEntityTypes.UMBRELLA_SUN_PROJECTILE,
				ThrownItemRenderer<UmbrellaProjectileEntity>::new);
		EntityRendererRegistry.register(ModEntityTypes.UMBRELLA_RAIN_PROJECTILE,
				ThrownItemRenderer<UmbrellaProjectileEntity>::new);
		EntityRendererRegistry.register(ModEntityTypes.UMBRELLA_STORM_PROJECTILE,
				ThrownItemRenderer<UmbrellaProjectileEntity>::new);
	}
}
