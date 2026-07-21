package io.github.aspshijiu.avaritia26.client.render;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.entity.BurningArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.Identifier;

public final class BurningArrowRenderer extends ArrowRenderer<BurningArrowEntity, ArrowRenderState> {
	private static final Identifier TEXTURE = Avaritia26.id("textures/entity/burning_arrow.png");

	public BurningArrowRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	protected Identifier getTextureLocation(ArrowRenderState state) {
		return TEXTURE;
	}

	@Override
	public ArrowRenderState createRenderState() {
		return new ArrowRenderState();
	}
}
