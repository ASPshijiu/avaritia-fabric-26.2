package io.github.aspshijiu.avaritia26.block;

import com.mojang.serialization.MapCodec;
import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.inventory.ExtremeAnvilMenu;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class ExtremeAnvilBlock extends FallingBlock {
	public static final MapCodec<ExtremeAnvilBlock> CODEC = simpleCodec(ExtremeAnvilBlock::new);
	public static final EnumProperty<Direction> FACING = HorizontalDirectionalBlock.FACING;
	public static final TagKey<Block> UNBREAKABLE_SUPPORT = TagKey.create(
			Registries.BLOCK, Avaritia26.id("extreme_anvil_unbreak"));
	private static final VoxelShape BASE = Block.box(2, 0, 2, 14, 4, 14);
	private static final VoxelShape X_LEG_1 = Block.box(3, 4, 4, 13, 5, 12);
	private static final VoxelShape X_LEG_2 = Block.box(4, 5, 6, 12, 10, 10);
	private static final VoxelShape X_TOP = Block.box(0, 10, 3, 16, 16, 13);
	private static final VoxelShape Z_LEG_1 = Block.box(4, 4, 3, 12, 5, 13);
	private static final VoxelShape Z_LEG_2 = Block.box(6, 5, 4, 10, 10, 12);
	private static final VoxelShape Z_TOP = Block.box(3, 10, 0, 13, 16, 16);
	private static final VoxelShape X_SHAPE = Shapes.or(BASE, X_LEG_1, X_LEG_2, X_TOP);
	private static final VoxelShape Z_SHAPE = Shapes.or(BASE, Z_LEG_1, Z_LEG_2, Z_TOP);

	public ExtremeAnvilBlock(BlockBehaviour.Properties properties) {
		super(properties);
		registerDefaultState(stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	protected MapCodec<? extends FallingBlock> codec() {
		return CODEC;
	}

	@Override
	protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (!isUnbreakableSupport(level.getBlockState(pos.below())) && pos.getY() >= level.getMinY()) {
			level.destroyBlock(pos.below(), true);
			FallingBlockEntity falling = FallingBlockEntity.fall(level, pos, state);
			falling(falling);
		}
	}

	public static boolean isUnbreakableSupport(BlockState state) {
		return state.is(net.minecraft.world.level.block.Blocks.BEDROCK) || state.is(UNBREAKABLE_SUPPORT);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(FACING, context.getHorizontalDirection());
	}

	@Override
	protected InteractionResult useWithoutItem(
			BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		if (!level.isClientSide()) {
			player.openMenu(new ExtendedMenuProvider<BlockPos>() {
				@Override
				public BlockPos getScreenOpeningData(ServerPlayer serverPlayer) {
					return pos;
				}

				@Override
				public Component getDisplayName() {
					return Component.translatable("container.repair");
				}

				@Override
				public AbstractContainerMenu createMenu(int id, Inventory inventory, Player menuPlayer) {
					return new ExtremeAnvilMenu(id, inventory, ContainerLevelAccess.create(level, pos));
				}
			});
			player.awardStat(Stats.INTERACT_WITH_ANVIL);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	protected VoxelShape getShape(
			BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return state.getValue(FACING).getAxis() == Direction.Axis.X ? Z_SHAPE : X_SHAPE;
	}

	@Override
	protected void falling(FallingBlockEntity falling) {
		falling.setHurtsEntities(2000.0F, 2_000_000);
	}

	@Override
	public void onLand(
			Level level, BlockPos pos, BlockState state, BlockState replaced, FallingBlockEntity falling) {
		if (!falling.isSilent()) {
			level.levelEvent(1031, pos, 0);
		}
	}

	@Override
	public void onBrokenAfterFall(Level level, BlockPos pos, FallingBlockEntity falling) {
		if (!falling.isSilent()) {
			level.levelEvent(1029, pos, 0);
		}
		if (level instanceof ServerLevel serverLevel && serverLevel.getGameRules().get(GameRules.ENTITY_DROPS)) {
			Block.popResource(level, pos, new ItemStack(this));
		}
	}

	@Override
	public DamageSource getFallDamageSource(Entity entity) {
		return entity.damageSources().anvil(entity);
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}

	@Override
	protected boolean isPathfindable(BlockState state, PathComputationType type) {
		return false;
	}

	@Override
	public int getDustColor(BlockState state, BlockGetter level, BlockPos pos) {
		return state.getMapColor(level, pos).col;
	}

	@Override
	public boolean dropFromExplosion(Explosion explosion) {
		return false;
	}
}
