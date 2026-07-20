package io.github.aspshijiu.avaritia26.block;

import com.mojang.serialization.MapCodec;
import io.github.aspshijiu.avaritia26.block.entity.InfinityChestBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public final class InfinityChestBlock extends BaseEntityBlock {
	public static final MapCodec<InfinityChestBlock> CODEC = simpleCodec(InfinityChestBlock::new);

	public InfinityChestBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new InfinityChestBlockEntity(pos, state);
	}

	@Override
	protected InteractionResult useWithoutItem(
			BlockState state,
			Level level,
			BlockPos pos,
			Player player,
			BlockHitResult hitResult
	) {
		if (!level.isClientSide() && level.getBlockEntity(pos) instanceof InfinityChestBlockEntity chest) {
			player.openMenu(chest);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public void playerDestroy(
			Level level,
			Player player,
			BlockPos pos,
			BlockState state,
			BlockEntity blockEntity,
			ItemStack tool
	) {
		if (!level.isClientSide() && blockEntity instanceof InfinityChestBlockEntity chest) {
			ItemStack drop = new ItemStack(this);
			drop.applyComponents(chest.collectComponents());
			popResource(level, pos, drop);
		}
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
		ItemStack stack = new ItemStack(this);
		if (includeData && level.getBlockEntity(pos) instanceof InfinityChestBlockEntity chest) {
			stack.applyComponents(chest.collectComponents());
		}
		return stack;
	}
}
