package io.github.aspshijiu.avaritia26.item;

import io.github.aspshijiu.avaritia26.event.ModToolEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public final class InfinityAxeItem extends Item {
	public InfinityAxeItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		if (!player.isShiftKeyDown()) {
			return InteractionResult.PASS;
		}
		if (level instanceof ServerLevel serverLevel) {
			ModToolEvents.destroyClassicAxeArea(serverLevel, player, player.getItemInHand(hand));
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		return state.is(BlockTags.MINEABLE_WITH_AXE) || state.is(BlockTags.LEAVES) ? 9999.0F : 6.0F;
	}

	@Override
	public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
		return canClearClassicMaterial(state);
	}

	public static boolean canClearClassicMaterial(BlockState state) {
		return state.is(BlockTags.MINEABLE_WITH_AXE)
				|| state.is(BlockTags.LEAVES)
				|| state.is(BlockTags.CORALS)
				|| state.is(BlockTags.CORAL_BLOCKS)
				|| state.is(BlockTags.CROPS)
				|| state.is(BlockTags.FLOWERS)
				|| state.is(BlockTags.REPLACEABLE_BY_TREES)
				|| state.is(BlockTags.CAVE_VINES)
				|| state.is(BlockTags.CLIMBABLE);
	}
}
