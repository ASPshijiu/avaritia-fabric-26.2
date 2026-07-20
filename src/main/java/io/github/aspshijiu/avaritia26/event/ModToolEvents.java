package io.github.aspshijiu.avaritia26.event;

import java.util.ArrayList;
import java.util.List;

import io.github.aspshijiu.avaritia26.item.InfinityPickaxeItem;
import io.github.aspshijiu.avaritia26.item.InfinityShovelItem;
import io.github.aspshijiu.avaritia26.item.MatterClusterItem;
import io.github.aspshijiu.avaritia26.registry.ModItems;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public final class ModToolEvents {
	private static final int RANGE = 8;

	private ModToolEvents() {
	}

	public static void initialize() {
		AttackBlockCallback.EVENT.register(ModToolEvents::breakWithInfinityTool);
	}

	private static InteractionResult breakWithInfinityTool(
			Player player,
			net.minecraft.world.level.Level level,
			net.minecraft.world.InteractionHand hand,
			BlockPos origin,
			Direction face
	) {
		ItemStack tool = player.getItemInHand(hand);
		boolean activePickaxe = tool.is(ModItems.INFINITY_PICKAXE) && InfinityPickaxeItem.isHammer(tool);
		boolean activeShovel = tool.is(ModItems.INFINITY_SHOVEL) && InfinityShovelItem.isDestroyer(tool);
		if (!(level instanceof ServerLevel serverLevel) || (!activePickaxe && !activeShovel)) {
			return InteractionResult.PASS;
		}
		List<ItemStack> drops = new ArrayList<>();
		int minY = face.getAxis() == Direction.Axis.Y ? -RANGE : -1;
		int maxY = face.getAxis() == Direction.Axis.Y ? RANGE : RANGE * 2 - 2;
		for (int x = -RANGE; x < RANGE; x++) {
			for (int y = minY; y < maxY; y++) {
				for (int z = -RANGE; z < RANGE; z++) {
					breakBlock(serverLevel, player, tool, origin.offset(x, y, z), drops);
				}
			}
		}
		for (ItemStack cluster : MatterClusterItem.createClusters(drops)) {
			Block.popResource(serverLevel, origin, cluster);
		}
		return InteractionResult.SUCCESS_SERVER;
	}

	private static void breakBlock(
			ServerLevel level,
			Player player,
			ItemStack tool,
			BlockPos pos,
			List<ItemStack> drops
	) {
		if (level.isOutsideBuildHeight(pos) || !level.mayInteract(player, pos)) {
			return;
		}
		BlockState state = level.getBlockState(pos);
		if (state.isAir() || state.getDestroySpeed(level, pos) < 0.0F || !tool.isCorrectToolForDrops(state)) {
			return;
		}
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (!PlayerBlockBreakEvents.BEFORE.invoker().beforeBlockBreak(level, player, pos, state, blockEntity)) {
			PlayerBlockBreakEvents.CANCELED.invoker().onBlockBreakCanceled(level, player, pos, state, blockEntity);
			return;
		}
		if (!player.getAbilities().instabuild) {
			drops.addAll(Block.getDrops(state, level, pos, blockEntity, player, tool));
		}
		if (level.destroyBlock(pos, false, player, 512)) {
			PlayerBlockBreakEvents.AFTER.invoker().afterBlockBreak(level, player, pos, state, blockEntity);
		}
	}
}
