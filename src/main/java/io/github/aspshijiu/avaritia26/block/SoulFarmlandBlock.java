package io.github.aspshijiu.avaritia26.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class SoulFarmlandBlock extends Block {
	public static final MapCodec<SoulFarmlandBlock> CODEC = simpleCodec(SoulFarmlandBlock::new);
	private static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 15.0, 16.0);
	private static final float GROWTH_CHANCE = 0.8F;

	public SoulFarmlandBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	protected MapCodec<? extends Block> codec() {
		return CODEC;
	}

	@Override
	protected boolean useShapeForLightOcclusion(BlockState state) {
		return true;
	}

	@Override
	protected VoxelShape getShape(
			BlockState state,
			BlockGetter level,
			BlockPos pos,
			CollisionContext context
	) {
		return SHAPE;
	}

	@Override
	protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		BlockPos abovePos = pos.above();
		BlockState aboveState = level.getBlockState(abovePos);
		if (aboveState.getBlock() instanceof TallFlowerBlock) {
			return;
		}

		if (aboveState.getBlock() instanceof SugarCaneBlock && random.nextFloat() <= GROWTH_CHANCE) {
			int age = aboveState.getValue(SugarCaneBlock.AGE);
			level.setBlock(abovePos, aboveState.setValue(SugarCaneBlock.AGE, Math.min(15, age + 5)), 4);
		}

		if (aboveState.getBlock() instanceof NetherWartBlock && random.nextFloat() <= GROWTH_CHANCE) {
			int age = aboveState.getValue(NetherWartBlock.AGE);
			if (age < 3) {
				level.setBlock(abovePos, aboveState.setValue(NetherWartBlock.AGE, age + 1), 2);
				level.levelEvent(2005, abovePos, 0);
			}
			return;
		}

		if (aboveState.getBlock() instanceof BonemealableBlock growable
				&& random.nextFloat() <= GROWTH_CHANCE
				&& growable.isValidBonemealTarget(level, abovePos, aboveState)) {
			growable.performBonemeal(level, random, abovePos, aboveState);
			level.levelEvent(2005, abovePos, 0);
		}
	}
}
