package io.github.aspshijiu.avaritia26.client.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

/**
 * 面板尺寸可能超过窗口逻辑分辨率的容器屏幕基类。当窗口容不下 imageWidth/imageHeight
 * 时按比例整体缩小渲染（pose 缩放对矩形/贴图/文字/物品一致生效），并把鼠标事件坐标
 * 反向换算，使全部槽位在任意 GUI Scale 下可见可点。比例为 1 时所有路径退化为原版行为。
 */
public abstract class ScaledContainerScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
	protected float guiScaleFactor = 1.0F;

	protected ScaledContainerScreen(T menu, Inventory inventory, Component title, int imageWidth, int imageHeight) {
		super(menu, inventory, title, imageWidth, imageHeight);
	}

	@Override
	protected void init() {
		// 必须读窗口尺寸而不是 this.width：rebuildWidgets 重入时 width 已被改成虚拟值
		int realWidth = minecraft.getWindow().getGuiScaledWidth();
		int realHeight = minecraft.getWindow().getGuiScaledHeight();
		guiScaleFactor = Math.min(1.0F, Math.min(
				realWidth / (float) imageWidth, realHeight / (float) imageHeight));
		if (guiScaleFactor < 1.0F) {
			width = (int) Math.ceil(realWidth / guiScaleFactor);
			height = (int) Math.ceil(realHeight / guiScaleFactor);
		} else {
			width = realWidth;
			height = realHeight;
		}
		super.init();
	}

	protected final int toVirtualMouse(int value) {
		return Math.round(value / guiScaleFactor);
	}

	protected final int toRealMouse(int value) {
		return Math.round(value * guiScaleFactor);
	}

	@Override
	public final void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		super.extractBackground(graphics, mouseX, mouseY, delta);
		graphics.pose().pushMatrix();
		graphics.pose().scale(guiScaleFactor, guiScaleFactor);
		extractScaledBackground(graphics, toVirtualMouse(mouseX), toVirtualMouse(mouseY), delta);
		graphics.pose().popMatrix();
	}

	/** 在缩放坐标系内绘制面板背景，leftPos/topPos 均为虚拟坐标。 */
	protected abstract void extractScaledBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta);

	@Override
	public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
		graphics.pose().pushMatrix();
		graphics.pose().scale(guiScaleFactor, guiScaleFactor);
		super.extractRenderState(graphics, toVirtualMouse(mouseX), toVirtualMouse(mouseY), delta);
		graphics.pose().popMatrix();
	}

	@Override
	protected final void extractTooltip(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		// 延迟渲染的 tooltip 在 pose 恢复后按原尺寸绘制，定位必须换回真实屏幕坐标
		extractScaledTooltip(graphics, toRealMouse(mouseX), toRealMouse(mouseY));
	}

	/** mouseX/mouseY 已换算回真实屏幕坐标；子类覆写此方法而不是 extractTooltip。 */
	protected void extractScaledTooltip(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		super.extractTooltip(graphics, mouseX, mouseY);
	}

	private MouseButtonEvent toVirtual(MouseButtonEvent event) {
		return guiScaleFactor == 1.0F ? event
				: new MouseButtonEvent(event.x() / guiScaleFactor, event.y() / guiScaleFactor, event.buttonInfo());
	}

	@Override
	public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
		return super.mouseClicked(toVirtual(event), doubleClick);
	}

	@Override
	public boolean mouseReleased(MouseButtonEvent event) {
		return super.mouseReleased(toVirtual(event));
	}

	@Override
	public boolean mouseDragged(MouseButtonEvent event, double dragX, double dragY) {
		return super.mouseDragged(toVirtual(event), dragX / guiScaleFactor, dragY / guiScaleFactor);
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
		return super.mouseScrolled(mouseX / guiScaleFactor, mouseY / guiScaleFactor, scrollX, scrollY);
	}

	@Override
	public void mouseMoved(double mouseX, double mouseY) {
		super.mouseMoved(mouseX / guiScaleFactor, mouseY / guiScaleFactor);
	}
}
