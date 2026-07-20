package io.github.aspshijiu.avaritia26.client.render;

import io.github.aspshijiu.avaritia26.Avaritia26;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;

public final class HeavenArrowRenderer<T extends AbstractArrow> extends ArrowRenderer<T, ArrowRenderState> {
	private static final Identifier TEXTURE = Avaritia26.id("textures/entity/heaven_arrow.png");

	public HeavenArrowRenderer(EntityRendererProvider.Context context) {
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
