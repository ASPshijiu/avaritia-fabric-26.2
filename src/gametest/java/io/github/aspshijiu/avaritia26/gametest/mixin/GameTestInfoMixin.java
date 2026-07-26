package io.github.aspshijiu.avaritia26.gametest.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.gametest.framework.GameTestInfo;
import net.minecraft.world.level.block.entity.TestInstanceBlockEntity;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

/**
 * GameTestInfo.tick 在测试开始前只等待结构包围盒相交区块满足
 * areEntitiesActuallyLoadedAndTicking。把等待范围与强制加载范围一致地外扩一圈区块，
 * 确保测试首个 tick 时结构四周区块的实体也已就绪（可查询、可 tick）。
 * 外扩区块已由 {@link TestInstanceBlockEntityMixin} 强制加载到 ENTITY_TICKING 等级，
 * 等待必然收敛，不会悬挂。
 */
@Mixin(GameTestInfo.class)
public abstract class GameTestInfoMixin {
	@Redirect(
			method = "tick",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/block/entity/TestInstanceBlockEntity;getStructureBoundingBox()Lnet/minecraft/world/level/levelgen/structure/BoundingBox;"
			)
	)
	private BoundingBox avaritia26$waitForChunkMargin(TestInstanceBlockEntity blockEntity) {
		return blockEntity.getStructureBoundingBox().inflatedBy(GameTestChunkMargin.BLOCKS);
	}
}
