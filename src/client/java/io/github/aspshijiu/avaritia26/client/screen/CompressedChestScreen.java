package io.github.aspshijiu.avaritia26.client.screen;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.inventory.CompressedChestMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public final class CompressedChestScreen extends AbstractContainerScreen<CompressedChestMenu> {
	private static final Identifier BACKGROUND = Avaritia26.id("textures/gui/compressed_chest.png");

	public CompressedChestScreen(CompressedChestMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title, 500, 275);
		inventoryLabelX = 170;
		inventoryLabelY = imageHeight - 94;
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(graphics, mouseX, mouseY, delta);
		graphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND, leftPos, topPos, 0, 0, imageWidth, imageHeight, 500, 275);
	}
}
