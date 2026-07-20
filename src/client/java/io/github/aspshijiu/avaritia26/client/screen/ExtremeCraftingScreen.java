package io.github.aspshijiu.avaritia26.client.screen;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.inventory.ExtremeCraftingMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public final class ExtremeCraftingScreen extends AbstractContainerScreen<ExtremeCraftingMenu> {
	private static final Identifier BACKGROUND = Avaritia26.id("textures/gui/extreme_crafting_table.png");

	public ExtremeCraftingScreen(ExtremeCraftingMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title, 234, 278);
		titleLabelX = 8;
		titleLabelY = 6;
		inventoryLabelX = 39;
		inventoryLabelY = 184;
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
				512,
				512
		);
	}
}
