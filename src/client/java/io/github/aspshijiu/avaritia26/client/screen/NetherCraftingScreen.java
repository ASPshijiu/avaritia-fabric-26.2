package io.github.aspshijiu.avaritia26.client.screen;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.inventory.NetherCraftingMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public final class NetherCraftingScreen extends AbstractContainerScreen<NetherCraftingMenu> {
	private static final Identifier BACKGROUND = Avaritia26.id("textures/gui/nether_crafting_table.png");

	public NetherCraftingScreen(NetherCraftingMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title, 176, 206);
		titleLabelY = 6;
		inventoryLabelX = 8;
		inventoryLabelY = 112;
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
