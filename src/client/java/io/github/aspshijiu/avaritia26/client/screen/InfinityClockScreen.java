package io.github.aspshijiu.avaritia26.client.screen;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.inventory.InfinityClockMenu;
import io.github.aspshijiu.avaritia26.network.SetTimePayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import org.lwjgl.glfw.GLFW;

public final class InfinityClockScreen extends AbstractContainerScreen<InfinityClockMenu> {
	private static final Identifier BACKGROUND = Avaritia26.id("textures/gui/infinity_clock.png");
	private static final int[] TIMES = {0, 6000, 12000, 14000, 18000, 22000};
	private EditBox timeInput;

	public InfinityClockScreen(InfinityClockMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title, 176, 166);
		inventoryLabelY = 72;
	}

	@Override
	protected void init() {
		super.init();
		for (int index = 0; index < TIMES.length; index++) {
			int time = TIMES[index];
			String label = time == 0 ? "0" : Integer.toString(time / 1000);
			addRenderableWidget(Button.builder(Component.literal(label), button -> setTime(time))
					.bounds(leftPos + 16 + index * 24, topPos + 22, 22, 20)
					.build());
		}
		timeInput = new EditBox(font, leftPos + 38, topPos + 52, 113, 16, Component.empty());
		timeInput.setMaxLength(10);
		timeInput.setHint(Component.translatable("screen.avaritia26.infinity_clock.custom_time"));
		addRenderableWidget(timeInput);
	}

	@Override
	public boolean keyPressed(KeyEvent event) {
		if (event.key() == GLFW.GLFW_KEY_ENTER && timeInput.isFocused()) {
			try {
				setTime(Integer.parseInt(timeInput.getValue()));
				return true;
			} catch (NumberFormatException ignored) {
				return true;
			}
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
				256
		);
	}

	private static void setTime(int time) {
		ClientPlayNetworking.send(new SetTimePayload(time));
	}
}
