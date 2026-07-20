package io.github.aspshijiu.avaritia26.block;

import com.mojang.serialization.MapCodec;
import io.github.aspshijiu.avaritia26.block.entity.SculkCraftingTableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public final class SculkCraftingTableBlock extends BaseEntityBlock {
	public static final MapCodec<SculkCraftingTableBlock> CODEC = simpleCodec(SculkCraftingTableBlock::new);

	public SculkCraftingTableBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new SculkCraftingTableBlockEntity(pos, state);
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
			MenuProvider provider = state.getMenuProvider(level, pos);
			if (provider != null) {
				player.openMenu(provider);
			}
		}
		return InteractionResult.SUCCESS;
	}
}
