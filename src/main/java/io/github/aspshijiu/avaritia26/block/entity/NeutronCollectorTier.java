package io.github.aspshijiu.avaritia26.block.entity;

import io.github.aspshijiu.avaritia26.registry.ModBlocks;
import io.github.aspshijiu.avaritia26.registry.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;

public enum NeutronCollectorTier {
	DEFAULT(ModItems.NEUTRON_PILE, 3600),
	DENSE(ModItems.NEUTRON_NUGGET, 3600),
	DENSER(ModItems.NEUTRON_INGOT, 3600),
	DENSEST(ModBlocks.NEUTRON_ITEM, 200);

	private final Item output;
	private final int productionTicks;

	NeutronCollectorTier(Item output, int productionTicks) {
		this.output = output;
		this.productionTicks = productionTicks;
	}

	public Item output() {
		return output;
	}

	public int productionTicks() {
		return productionTicks;
	}

	public static NeutronCollectorTier from(BlockState state) {
		if (state.is(ModBlocks.DENSE_NEUTRON_COLLECTOR)) {
			return DENSE;
		}
		if (state.is(ModBlocks.DENSER_NEUTRON_COLLECTOR)) {
			return DENSER;
		}
		if (state.is(ModBlocks.DENSEST_NEUTRON_COLLECTOR)) {
			return DENSEST;
		}
		return DEFAULT;
	}
}
