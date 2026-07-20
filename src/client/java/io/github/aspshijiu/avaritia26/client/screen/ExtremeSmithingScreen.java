package io.github.aspshijiu.avaritia26.client.screen;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.inventory.ExtremeSmithingMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public final class ExtremeSmithingScreen extends AbstractContainerScreen<ExtremeSmithingMenu> {
	private static final Identifier BACKGROUND = Avaritia26.id("textures/gui/extreme_smithing_table.png");

	public ExtremeSmithingScreen(ExtremeSmithingMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title);
		titleLabelX = 78;
		titleLabelY = 6;
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
	}
}
