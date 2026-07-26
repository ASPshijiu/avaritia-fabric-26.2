package io.github.aspshijiu.avaritia26.inventory;

import net.minecraft.world.inventory.ContainerData;

/**
 * 原版 ClientboundContainerSetDataPacket 以 short 编码数据槽的值，超过 16 位的
 * 数值在客户端会被截断回绕。该包装器把每个 int 逻辑值拆成低/高两个 16 位半槽
 * 参与原版同步，读取方用 {@link #combine} 从任意一侧的数据源无损还原。
 */
public final class WideContainerData implements ContainerData {
	private final ContainerData delegate;

	public WideContainerData(ContainerData delegate) {
		this.delegate = delegate;
	}

	public static int wideCount(int logicalCount) {
		return logicalCount * 2;
	}

	public static int combine(ContainerData data, int logicalIndex) {
		return (data.get(logicalIndex * 2 + 1) << 16) | (data.get(logicalIndex * 2) & 0xFFFF);
	}

	@Override
	public int get(int index) {
		int value = delegate.get(index / 2);
		return index % 2 == 0 ? value & 0xFFFF : value >>> 16;
	}

	@Override
	public void set(int index, int value) {
		int logicalIndex = index / 2;
		int current = delegate.get(logicalIndex);
		int combined = index % 2 == 0
				? (current & 0xFFFF0000) | (value & 0xFFFF)
				: (value << 16) | (current & 0xFFFF);
		delegate.set(logicalIndex, combined);
	}

	@Override
	public int getCount() {
		return wideCount(delegate.getCount());
	}
}
