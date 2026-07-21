package io.github.aspshijiu.avaritia26.client.screen;

import io.github.aspshijiu.avaritia26.inventory.ExtremeAnvilMenu;
import io.github.aspshijiu.avaritia26.network.RenameExtremeAnvilPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public final class ExtremeAnvilScreen extends ItemCombinerScreen<ExtremeAnvilMenu> {
	private static final Identifier BACKGROUND = Identifier.withDefaultNamespace("textures/gui/container/anvil.png");
	private static final Identifier TEXT_FIELD = Identifier.withDefaultNamespace("container/anvil/text_field");
	private static final Identifier TEXT_FIELD_DISABLED =
			Identifier.withDefaultNamespace("container/anvil/text_field_disabled");
	private static final Identifier ERROR = Identifier.withDefaultNamespace("container/anvil/error");
	private EditBox name;

	public ExtremeAnvilScreen(ExtremeAnvilMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title, BACKGROUND);
		titleLabelX = 60;
	}

	@Override
	protected void subInit() {
		name = new EditBox(font, leftPos + 62, topPos + 24, 103, 12, Component.translatable("container.repair"));
		name.setCanLoseFocus(false);
		name.setTextColor(-1);
		name.setTextColorUneditable(-1);
		name.setInvertHighlightedTextColor(false);
		name.setBordered(false);
		name.setMaxLength(50);
		name.setResponder(this::onNameChanged);
		name.setValue("");
		addRenderableWidget(name);
		name.setEditable(menu.getSlot(0).hasItem());
	}

	@Override
	protected void setInitialFocus() {
		setInitialFocus(name);
	}

	@Override
	public void resize(int width, int height) {
		String value = name.getValue();
		init(width, height);
		name.setValue(value);
	}

	@Override
	public boolean keyPressed(KeyEvent event) {
		if (event.isEscape()) {
			minecraft.player.closeContainer();
			return true;
		}
		return name.keyPressed(event) || name.canConsumeInput() || super.keyPressed(event);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(graphics, mouseX, mouseY, delta);
		graphics.blitSprite(
				RenderPipelines.GUI_TEXTURED,
				menu.getSlot(0).hasItem() ? TEXT_FIELD : TEXT_FIELD_DISABLED,
				leftPos + 59,
				topPos + 20,
				110,
				16
		);
	}

	@Override
	protected void extractErrorIcon(GuiGraphicsExtractor graphics, int x, int y) {
		if ((menu.getSlot(0).hasItem() || menu.getSlot(1).hasItem())
				&& !menu.getSlot(menu.getResultSlot()).hasItem()) {
			graphics.blitSprite(RenderPipelines.GUI_TEXTURED, ERROR, x + 99, y + 45, 28, 21);
		}
	}

	@Override
	public void slotChanged(AbstractContainerMenu changedMenu, int slotIndex, ItemStack stack) {
		if (slotIndex == 0) {
			name.setValue(stack.isEmpty() ? "" : stack.getHoverName().getString());
			name.setEditable(!stack.isEmpty());
			setFocused(name);
		}
	}

	private void onNameChanged(String newName) {
		Slot input = menu.getSlot(0);
		if (!input.hasItem()) {
			return;
		}
		String sentName = newName;
		if (!input.getItem().has(DataComponents.CUSTOM_NAME)
				&& newName.equals(input.getItem().getHoverName().getString())) {
			sentName = "";
		}
		if (menu.setItemName(sentName)) {
			ClientPlayNetworking.send(new RenameExtremeAnvilPayload(sentName));
		}
	}
}
