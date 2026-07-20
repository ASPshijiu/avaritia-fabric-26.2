package io.github.aspshijiu.avaritia26.client.screen;

import java.util.ArrayList;
import java.util.List;

import io.github.aspshijiu.avaritia26.inventory.InfinityChestMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public final class InfinityChestScreen extends AbstractContainerScreen<InfinityChestMenu> {
	private static final int WIDTH = 556;
	private static final int HEIGHT = 293;
	private static final Identifier SLOT_BACKGROUND = Identifier.withDefaultNamespace("container/slot");

	public InfinityChestScreen(InfinityChestMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title, WIDTH, HEIGHT);
		inventoryLabelX = 197;
		inventoryLabelY = 199;
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(graphics, mouseX, mouseY, delta);
		graphics.fill(leftPos, topPos, leftPos + imageWidth, topPos + imageHeight, 0xFF17131F);
		graphics.fill(leftPos + 2, topPos + 2, leftPos + imageWidth - 2, topPos + imageHeight - 2, 0xFF2B2336);
		graphics.fill(leftPos + 5, topPos + 14, leftPos + imageWidth - 5, topPos + 200, 0xFF100D16);
		graphics.fill(leftPos + 194, topPos + 208, leftPos + 362, topPos + 289, 0xFF100D16);
		graphics.outline(leftPos, topPos, imageWidth, imageHeight, 0xFFFFA600);
		for (Slot slot : menu.slots) {
			graphics.blitSprite(
					RenderPipelines.GUI_TEXTURED,
					SLOT_BACKGROUND,
					leftPos + slot.x - 1,
					topPos + slot.y - 1,
					18,
					18
			);
		}
	}

	@Override
	protected void extractTooltip(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		if (menu.getCarried().isEmpty() && isInfinitySlot(hoveredSlot) && hoveredSlot.hasItem()) {
			ItemStack stack = hoveredSlot.getItem();
			List<Component> lines = new ArrayList<>(getTooltipFromContainerItem(stack));
			lines.add(Component.translatable(
					"container.avaritia26.infinity_chest.amount",
					stack.getCount(),
					hoveredSlot.getMaxStackSize(stack)
			));
			graphics.setTooltipForNextFrame(font, lines, stack.getTooltipImage(), mouseX, mouseY);
			return;
		}
		super.extractTooltip(graphics, mouseX, mouseY);
	}

	private boolean isInfinitySlot(Slot slot) {
		return slot != null && menu.slots.indexOf(slot) < InfinityChestMenu.CHEST_SLOT_END;
	}
}
