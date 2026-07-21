package io.github.aspshijiu.avaritia26.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class EndlessCakeBlock extends Block {
	public static final MapCodec<EndlessCakeBlock> CODEC = simpleCodec(EndlessCakeBlock::new);
	public static final VoxelShape SHAPE = Block.box(1, 0, 1, 15, 8, 15);

	public EndlessCakeBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	protected MapCodec<? extends Block> codec() {
		return CODEC;
	}

	public static InteractionResult tryEat(Level level, BlockPos pos, Player player) {
		if (!player.canEat(true)) {
			return InteractionResult.TRY_WITH_EMPTY_HAND;
		}

		player.awardStat(Stats.EAT_CAKE_SLICE);
		player.getFoodData().eat(2, 0.1F);
		if (!level.isClientSide()) {
			player.removeEffect(MobEffects.POISON);
			player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 400, 1));
			player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 6000, 0));
			player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0));
			player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3));
			if (level instanceof ServerLevel serverLevel) {
				serverLevel.sendParticles(
						ParticleTypes.WITCH,
						pos.getX() + 0.5,
						pos.getY() + 1.0,
						pos.getZ() + 0.5,
						10,
						0.5,
						0.5,
						0.5,
						0.1
				);
			}
		}
		level.gameEvent(player, GameEvent.EAT, pos);
		return InteractionResult.SUCCESS;
	}

	@Override
	protected InteractionResult useItemOn(
			ItemStack stack,
			BlockState state,
			Level level,
			BlockPos pos,
			Player player,
			InteractionHand hand,
			BlockHitResult hitResult
	) {
		return tryEat(level, pos, player);
	}

	@Override
	protected InteractionResult useWithoutItem(
			BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		return tryEat(level, pos, player);
	}

	@Override
	protected VoxelShape getShape(
			BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	protected BlockState updateShape(
			BlockState state,
			LevelReader level,
			ScheduledTickAccess tickAccess,
			BlockPos pos,
			Direction direction,
			BlockPos neighborPos,
			BlockState neighborState,
			RandomSource random
	) {
		return direction == Direction.DOWN && !state.canSurvive(level, pos)
				? Blocks.AIR.defaultBlockState()
				: super.updateShape(state, level, tickAccess, pos, direction, neighborPos, neighborState, random);
	}

	@Override
	protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		return level.getBlockState(pos.below()).isSolid();
	}

	@Override
	protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos, Direction direction) {
		return 14;
	}

	@Override
	protected boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	protected boolean isPathfindable(BlockState state, PathComputationType type) {
		return true;
	}
}
