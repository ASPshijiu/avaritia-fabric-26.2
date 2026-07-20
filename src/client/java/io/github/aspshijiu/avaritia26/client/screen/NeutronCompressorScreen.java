package io.github.aspshijiu.avaritia26.client.screen;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.inventory.NeutronCompressorMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

public final class NeutronCompressorScreen extends AbstractContainerScreen<NeutronCompressorMenu> {
	private static final Identifier BACKGROUND = Avaritia26.id("textures/gui/neutron_compressor.png");

	public NeutronCompressorScreen(NeutronCompressorMenu menu, Inventory inventory, Component title) {
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
		int materialWidth = scaled(menu.materialCount(), menu.materialsRequired(), 16);
		if (materialWidth > 0) {
			graphics.blit(
					RenderPipelines.GUI_TEXTURED,
					BACKGROUND,
					leftPos + 63,
					topPos + 35,
					176.0F,
					18.0F,
					materialWidth,
					16,
					256,
					256
			);
		}
		int progressWidth = scaled(menu.progress(), menu.timeRequired(), 22);
		if (progressWidth > 0) {
			graphics.blit(
					RenderPipelines.GUI_TEXTURED,
					BACKGROUND,
					leftPos + 89,
					topPos + 35,
					176.0F,
					0.0F,
					progressWidth,
					16,
					256,
					256
			);
		}
	}

	private static int scaled(int value, int maximum, int pixels) {
		return maximum <= 0 ? 0 : Mth.clamp(value, 0, maximum) * pixels / maximum;
	}
}
