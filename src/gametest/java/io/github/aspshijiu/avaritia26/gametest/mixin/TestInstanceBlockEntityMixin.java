package io.github.aspshijiu.avaritia26.gametest.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.world.level.block.entity.TestInstanceBlockEntity;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

/**
 * 测试大量在结构包围盒（默认空结构仅 8×8×8）之外的相对坐标上生成或驱动实体，
 * 这些位置可能落入未被强制加载的邻接区块（实体不 tick 甚至不可见），是否踩中取决于
 * 网格摆放位置，因而失败随机。把强制加载范围外扩一整圈区块，保证测试可触及的
 * 邻接区块也达到 ENTITY_TICKING 等级。批次结束时 GameTestRunner 会统一解除全部
 * 强制区块，无需额外清理。
 */
@Mixin(TestInstanceBlockEntity.class)
public abstract class TestInstanceBlockEntityMixin {
	@Redirect(
			method = "forceLoadChunks",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/block/entity/TestInstanceBlockEntity;getStructureBoundingBox()Lnet/minecraft/world/level/levelgen/structure/BoundingBox;"
			)
	)
	private BoundingBox avaritia26$forceLoadChunkMargin(TestInstanceBlockEntity blockEntity) {
		return blockEntity.getStructureBoundingBox().inflatedBy(GameTestChunkMargin.BLOCKS);
	}
}
