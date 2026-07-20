package io.github.aspshijiu.avaritia26.client.screen;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.inventory.NeutronCollectorMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

public final class NeutronCollectorScreen extends AbstractContainerScreen<NeutronCollectorMenu> {
	private static final Identifier BACKGROUND = Avaritia26.id("textures/gui/neutron_collector.png");

	public NeutronCollectorScreen(NeutronCollectorMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title);
		inventoryLabelY = imageHeight - 94;
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(graphics, mouseX, mouseY, delta);
		graphics.blit(
				RenderPipelines.GUI_TEXTURED,
				BACKGROUND,
				leftPos,
				topPos,
				0.0F,
				0.0F,
				imageWidth,
				imageHeight,
				256,
				256
		);
		int height = scaled(menu.progress(), menu.timeRequired(), 18);
		if (height > 0) {
			graphics.blit(
					RenderPipelines.GUI_TEXTURED,
					BACKGROUND,
					leftPos + 99,
					topPos + 49 - height,
					176.0F,
					18.0F - height,
					4,
					height,
					256,
					256
			);
		}
	}

	private static int scaled(int value, int maximum, int pixels) {
		return maximum <= 0 ? 0 : Mth.clamp(value, 0, maximum) * pixels / maximum;
	}
}
