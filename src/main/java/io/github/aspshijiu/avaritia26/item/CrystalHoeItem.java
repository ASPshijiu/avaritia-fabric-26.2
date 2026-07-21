package io.github.aspshijiu.avaritia26.item;

import java.util.function.Consumer;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.state.BlockState;

public final class CrystalHoeItem extends Item {
	private static final int RANGE = 1;

	public CrystalHoeItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockPos origin = context.getClickedPos();
		Player player = context.getPlayer();
		if (context.getClickedFace() != Direction.DOWN && canTill(level, origin)) {
			level.playSound(player, origin, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
			if (level.isClientSide()) {
				return InteractionResult.SUCCESS;
			}
			for (BlockPos pos : BlockPos.betweenClosed(
					origin.offset(-RANGE, 0, -RANGE), origin.offset(RANGE, 0, RANGE))) {
				if (canTill(level, pos)) {
					level.setBlock(pos, Blocks.FARMLAND.defaultBlockState()
							.setValue(FarmlandBlock.MOISTURE, FarmlandBlock.MAX_MOISTURE), 11);
				}
			}
			return InteractionResult.SUCCESS_SERVER;
		}

		BlockState state = level.getBlockState(origin);
		if (level instanceof ServerLevel serverLevel && state.getBlock() instanceof BonemealableBlock growable
				&& growable.isValidBonemealTarget(level, origin, state)) {
			growable.performBonemeal(serverLevel, level.getRandom(), origin, state);
			serverLevel.levelEvent(2005, origin, 0);
			return InteractionResult.SUCCESS_SERVER;
		}
		return InteractionResult.PASS;
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return false;
	}

	@Override
	public void appendHoverText(
			ItemStack stack,
			TooltipContext context,
			TooltipDisplay display,
			Consumer<Component> tooltip,
			TooltipFlag flag
	) {
		tooltip.accept(Component.translatable("tooltip.avaritia26.crystal_hoe.desc")
				.withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
	}

	private static boolean canTill(Level level, BlockPos pos) {
		BlockState state = level.getBlockState(pos);
		return level.getBlockState(pos.above()).isAir()
				&& (state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT) || state.is(Blocks.COARSE_DIRT));
	}
}
