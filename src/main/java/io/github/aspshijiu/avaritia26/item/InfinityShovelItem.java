package io.github.aspshijiu.avaritia26.item;

import io.github.aspshijiu.avaritia26.registry.ModDataComponents;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public final class InfinityShovelItem extends Item {
	public InfinityShovelItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		if (!player.isShiftKeyDown()) {
			return InteractionResult.PASS;
		}
		if (!level.isClientSide()) {
			ItemStack stack = player.getItemInHand(hand);
			stack.set(ModDataComponents.INFINITY_SHOVEL_DESTROYER, !isDestroyer(stack));
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockState state = level.getBlockState(context.getClickedPos());
		if (context.getClickedFace() == Direction.DOWN
				|| !level.getBlockState(context.getClickedPos().above()).isAir()
				|| !canFlatten(state)) {
			return InteractionResult.PASS;
		}
		level.playSound(context.getPlayer(), context.getClickedPos(), SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
		if (!level.isClientSide()) {
			level.setBlock(context.getClickedPos(), Blocks.DIRT_PATH.defaultBlockState(), 11);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		if (isDestroyer(stack)) {
			return 5.0F;
		}
		return InfinityPickaxeItem.canMineClassicMaterial(state) ? 9999.0F : 1.0F;
	}

	@Override
	public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
		return InfinityPickaxeItem.canMineClassicMaterial(state);
	}

	public static boolean isDestroyer(ItemStack stack) {
		return stack.getOrDefault(ModDataComponents.INFINITY_SHOVEL_DESTROYER, false);
	}

	private static boolean canFlatten(BlockState state) {
		return state.is(Blocks.GRASS_BLOCK)
				|| state.is(Blocks.DIRT)
				|| state.is(Blocks.COARSE_DIRT)
				|| state.is(Blocks.PODZOL)
				|| state.is(Blocks.MYCELIUM)
				|| state.is(Blocks.ROOTED_DIRT);
	}
}
