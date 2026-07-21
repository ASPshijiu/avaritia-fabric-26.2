package io.github.aspshijiu.avaritia26.block;

import java.util.List;

import com.mojang.serialization.MapCodec;
import io.github.aspshijiu.avaritia26.block.entity.CompressedChestBlockEntity;
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
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;

public final class CompressedChestBlock extends BaseEntityBlock {
	public static final MapCodec<CompressedChestBlock> CODEC = simpleCodec(CompressedChestBlock::new);

	public CompressedChestBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new CompressedChestBlockEntity(pos, state);
	}

	@Override
	protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
		if (!level.isClientSide() && level.getBlockEntity(pos) instanceof CompressedChestBlockEntity chest) {
			player.openMenu(chest);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	protected List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
		ItemStack drop = new ItemStack(this);
		BlockEntity blockEntity = params.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
		if (blockEntity instanceof CompressedChestBlockEntity chest) {
			drop.applyComponents(chest.collectComponents());
		}
		return List.of(drop);
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
		ItemStack stack = new ItemStack(this);
		if (includeData && level.getBlockEntity(pos) instanceof CompressedChestBlockEntity chest) {
			stack.applyComponents(chest.collectComponents());
		}
		return stack;
	}
}
