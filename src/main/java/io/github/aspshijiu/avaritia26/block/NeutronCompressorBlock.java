package io.github.aspshijiu.avaritia26.block;

import com.mojang.serialization.MapCodec;
import io.github.aspshijiu.avaritia26.block.entity.NeutronCompressorBlockEntity;
import io.github.aspshijiu.avaritia26.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;

public final class NeutronCompressorBlock extends BaseEntityBlock {
	public static final MapCodec<NeutronCompressorBlock> CODEC = simpleCodec(NeutronCompressorBlock::new);
	public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;

	public NeutronCompressorBlock(BlockBehaviour.Properties properties) {
		super(properties);
		registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	@Override
	protected boolean shouldChangedStateKeepBlockEntity(BlockState oldState) {
		return oldState.getBlock() instanceof NeutronCompressorBlock;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new NeutronCompressorBlockEntity(pos, state);
	}

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
			Level level,
			BlockState state,
			BlockEntityType<T> type
	) {
		return level.isClientSide()
				? null
				: createTickerHelper(type, ModBlockEntities.NEUTRON_COMPRESSOR, NeutronCompressorBlockEntity::serverTick);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	protected BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
	}

	@Override
	protected BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}

	@Override
	protected InteractionResult useWithoutItem(
			BlockState state,
			Level level,
			BlockPos pos,
			Player player,
			BlockHitResult hitResult
	) {
		if (!level.isClientSide()) {
			if (player.isShiftKeyDown() && level.getBlockEntity(pos) instanceof NeutronCompressorBlockEntity compressor) {
				compressor.ejectBufferedMaterial(player);
			} else {
				MenuProvider provider = state.getMenuProvider(level, pos);
				if (provider != null) {
					player.openMenu(provider);
				}
			}
		}
		return InteractionResult.SUCCESS;
	}
}
