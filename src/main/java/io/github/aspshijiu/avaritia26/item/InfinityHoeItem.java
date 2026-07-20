package io.github.aspshijiu.avaritia26.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public final class InfinityHoeItem extends Item {
	private static final int RANGE = 4;

	public InfinityHoeItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		if (context.getClickedFace() == Direction.DOWN) {
			return InteractionResult.PASS;
		}
		Level level = context.getLevel();
		BlockPos origin = context.getClickedPos();
		Player player = context.getPlayer();
		if (!canStartTilling(level, origin)) {
			return InteractionResult.PASS;
		}
		level.playSound(player, origin, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
		if (level.isClientSide()) {
			return InteractionResult.SUCCESS;
		}

		till(level, origin);
		if (player == null || player.isShiftKeyDown()) {
			return InteractionResult.SUCCESS_SERVER;
		}
		for (BlockPos target : BlockPos.betweenClosed(origin.offset(-RANGE, 0, -RANGE), origin.offset(RANGE, 0, RANGE))) {
			if (target.equals(origin)) {
				continue;
			}
			prepareSurface(level, target, player);
			till(level, target);
		}
		return InteractionResult.SUCCESS_SERVER;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		return state.is(BlockTags.MINEABLE_WITH_HOE) ? 9999.0F : 1.0F;
	}

	@Override
	public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
		return state.is(BlockTags.MINEABLE_WITH_HOE);
	}

	private static boolean canStartTilling(Level level, BlockPos pos) {
		return level.getBlockState(pos).is(Blocks.FARMLAND) || canTill(level, pos);
	}

	private static boolean canTill(Level level, BlockPos pos) {
		BlockState state = level.getBlockState(pos);
		return level.getBlockState(pos.above()).isAir()
				&& (state.is(Blocks.GRASS_BLOCK)
				|| state.is(Blocks.DIRT_PATH)
				|| state.is(Blocks.DIRT)
				|| state.is(Blocks.COARSE_DIRT));
	}

	private static void till(Level level, BlockPos pos) {
		if (!canTill(level, pos)) {
			return;
		}
		BlockState state = level.getBlockState(pos);
		level.setBlock(pos, state.is(Blocks.COARSE_DIRT) ? Blocks.DIRT.defaultBlockState() : Blocks.FARMLAND.defaultBlockState(), 11);
	}

	private static void prepareSurface(Level level, BlockPos pos, Player player) {
		BlockState state = level.getBlockState(pos);
		if ((state.isAir() || state.canBeReplaced())
				&& level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP)
				&& consumeDirt(player)) {
			level.setBlock(pos, Blocks.DIRT.defaultBlockState(), 11);
		}

		BlockPos above = pos.above();
		BlockState aboveState = level.getBlockState(above);
		BlockState twoAboveState = level.getBlockState(above.above());
		boolean earth = aboveState.is(Blocks.DIRT) || aboveState.is(Blocks.GRASS_BLOCK) || aboveState.is(Blocks.FARMLAND);
		if (!aboveState.isAir() && (earth || aboveState.canBeReplaced()) && (twoAboveState.isAir() || twoAboveState.canBeReplaced())) {
			if (earth) {
				Blocks.DIRT.popResource(level, above, new ItemStack(Items.DIRT));
			}
			level.removeBlock(above, false);
		}
	}

	private static boolean consumeDirt(Player player) {
		if (player.getAbilities().instabuild) {
			return true;
		}
		for (ItemStack stack : player.getInventory().getNonEquipmentItems()) {
			if (stack.is(Items.DIRT)) {
				stack.shrink(1);
				return true;
			}
		}
		return false;
	}
}
