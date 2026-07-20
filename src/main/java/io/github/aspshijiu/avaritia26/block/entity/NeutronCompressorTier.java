package io.github.aspshijiu.avaritia26.block.entity;

import io.github.aspshijiu.avaritia26.registry.ModBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public enum NeutronCompressorTier {
	DEFAULT(1, 1, 1, 1),
	DENSE(2, 1, 1, 1),
	DENSER(4, 3, 4, 1),
	DENSEST(8, 1, 2, 2);

	private final int timeDivisor;
	private final int inputNumerator;
	private final int inputDenominator;
	private final int outputMultiplier;

	NeutronCompressorTier(int timeDivisor, int inputNumerator, int inputDenominator, int outputMultiplier) {
		this.timeDivisor = timeDivisor;
		this.inputNumerator = inputNumerator;
		this.inputDenominator = inputDenominator;
		this.outputMultiplier = outputMultiplier;
	}

	public int scaleTime(int baseTime) {
		return Math.max(1, Math.ceilDiv(baseTime, timeDivisor));
	}

	public int scaleInput(int baseInput) {
		return Math.max(1, Math.ceilDiv(baseInput * inputNumerator, inputDenominator));
	}

	public ItemStack scaleOutput(ItemStack baseOutput) {
		return baseOutput.copyWithCount(baseOutput.getCount() * outputMultiplier);
	}

	public static NeutronCompressorTier from(BlockState state) {
		if (state.is(ModBlocks.DENSE_NEUTRON_COMPRESSOR)) {
			return DENSE;
		}
		if (state.is(ModBlocks.DENSER_NEUTRON_COMPRESSOR)) {
			return DENSER;
		}
		if (state.is(ModBlocks.DENSEST_NEUTRON_COMPRESSOR)) {
			return DENSEST;
		}
		return DEFAULT;
	}
}
