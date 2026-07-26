package io.github.aspshijiu.avaritia26.gametest.mixin;

/**
 * 测试结构包围盒的外扩距离（方块）。16 保证结构每个方向至少多覆盖一整个区块，
 * 覆盖测试使用的最大越界相对坐标（当前约 12）及结构内尺度的实体位移。
 */
final class GameTestChunkMargin {
	static final int BLOCKS = 16;

	private GameTestChunkMargin() {
	}
}
