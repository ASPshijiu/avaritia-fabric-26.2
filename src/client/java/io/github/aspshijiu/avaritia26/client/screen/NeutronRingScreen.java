package io.github.aspshijiu.avaritia26.client.screen;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.client.Avaritia26Client;
import io.github.aspshijiu.avaritia26.inventory.NeutronRingMenu;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public final class NeutronRingScreen extends AbstractContainerScreen<NeutronRingMenu> {
	private static final Identifier BACKGROUND = Avaritia26.id("textures/gui/neutron_ring.png");

	public NeutronRingScreen(NeutronRingMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title, 256, 276);
		inventoryLabelY = 182;
	}

	@Override
	public boolean keyPressed(KeyEvent event) {
		if (Avaritia26Client.OPEN_NEUTRON_RING.matches(event)) {
			Avaritia26Client.OPEN_NEUTRON_RING.consumeClick();
			onClose();
			return true;
		}
		return super.keyPressed(event);
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
				276
		);
	}
}
