package io.github.aspshijiu.avaritia26.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.entity.BladeSlashEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public final class BladeSlashRenderer extends EntityRenderer<BladeSlashEntity, BladeSlashRenderState> {
	private static final Identifier TEXTURE = Avaritia26.id("textures/entity/blade_slash.png");
	private static final RenderType RENDER_TYPE = RenderTypes.entityTranslucentEmissive(TEXTURE);

	public BladeSlashRenderer(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public void submit(
			BladeSlashRenderState state,
			PoseStack poseStack,
			SubmitNodeCollector collector,
			CameraRenderState camera
	) {
		poseStack.pushPose();
		poseStack.mulPose(Axis.YP.rotationDegrees(state.yRot - 90.0F));
		poseStack.mulPose(Axis.ZP.rotationDegrees(state.xRot + 10.0F));
		poseStack.mulPose(Axis.XP.rotationDegrees(state.zRot));
		poseStack.scale(0.6F, 0.6F, 1.8F);
		int alpha = Math.max(0, 200 * (BladeSlashEntity.LIFETIME - state.age) / BladeSlashEntity.LIFETIME);
		collector.submitCustomGeometry(poseStack, RENDER_TYPE,
				(pose, consumer) -> buildQuad(pose, consumer, alpha));
		poseStack.popPose();
		super.submit(state, poseStack, collector, camera);
	}

	@Override
	public void extractRenderState(BladeSlashEntity entity, BladeSlashRenderState state, float partialTick) {
		super.extractRenderState(entity, state, partialTick);
		state.yRot = Mth.lerp(partialTick, entity.yRotO, entity.getYRot());
		state.xRot = Mth.lerp(partialTick, entity.xRotO, entity.getXRot());
		state.zRot = (entity.getId() * 31 % 50) - 25.0F;
		state.age = entity.tickCount;
	}

	@Override
	public BladeSlashRenderState createRenderState() {
		return new BladeSlashRenderState();
	}

	private static void buildQuad(PoseStack.Pose pose, VertexConsumer consumer, int alpha) {
		vertex(consumer, pose, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, alpha);
		vertex(consumer, pose, 1.0F, 0.0F, -1.0F, 0.0F, 0.0F, alpha);
		vertex(consumer, pose, -1.0F, 0.0F, -1.0F, 0.0F, 1.0F, alpha);
		vertex(consumer, pose, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, alpha);
	}

	private static void vertex(
			VertexConsumer consumer,
			PoseStack.Pose pose,
			float x,
			float y,
			float z,
			float u,
			float v,
			int alpha
	) {
		consumer.addVertex(pose, x, y, z)
				.setColor(255, 255, 255, alpha)
				.setUv(u, v)
				.setOverlay(OverlayTexture.NO_OVERLAY)
				.setLight(0x00F000F0)
				.setNormal(pose, 0.0F, 1.0F, 0.0F);
	}
}
