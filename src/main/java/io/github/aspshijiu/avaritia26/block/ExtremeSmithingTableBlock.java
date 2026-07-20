package io.github.aspshijiu.avaritia26.block;

import com.mojang.serialization.MapCodec;
import io.github.aspshijiu.avaritia26.inventory.ExtremeSmithingMenu;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public final class ExtremeSmithingTableBlock extends Block {
	public static final MapCodec<ExtremeSmithingTableBlock> CODEC = simpleCodec(ExtremeSmithingTableBlock::new);

	public ExtremeSmithingTableBlock(BlockBehaviour.Properties properties) {
		super(properties);
	}

	@Override
	protected MapCodec<? extends Block> codec() {
		return CODEC;
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
			player.openMenu(new ExtendedMenuProvider<BlockPos>() {
				@Override
				public BlockPos getScreenOpeningData(ServerPlayer serverPlayer) {
					return pos;
				}

				@Override
				public Component getDisplayName() {
					return Component.translatable("container.avaritia26.extreme_smithing_table");
				}

				@Override
				public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player menuPlayer) {
					return new ExtremeSmithingMenu(
							containerId,
							inventory,
							ContainerLevelAccess.create(level, pos)
					);
				}
			});
		}
		return InteractionResult.SUCCESS;
	}
}
