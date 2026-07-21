package io.github.aspshijiu.avaritia26.event;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import io.github.aspshijiu.avaritia26.item.BlazeHoeItem;
import io.github.aspshijiu.avaritia26.item.CrystalPickaxeItem;
import io.github.aspshijiu.avaritia26.item.InfinityAxeItem;
import io.github.aspshijiu.avaritia26.item.InfinityPickaxeItem;
import io.github.aspshijiu.avaritia26.item.InfinityShovelItem;
import io.github.aspshijiu.avaritia26.item.MatterClusterItem;
import io.github.aspshijiu.avaritia26.registry.ModItems;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;

public final class ModToolEvents {
	private static final int RANGE = 8;
	private static final int AXE_RANGE = 13;
	private static final int AXE_CHAIN_STEPS = 32;

	private ModToolEvents() {
	}

	public static void initialize() {
		AttackBlockCallback.EVENT.register(ModToolEvents::breakWithInfinityTool);
		LootTableEvents.MODIFY_DROPS.register((table, context, drops) -> smeltBlazeHoeDrops(context, drops));
	}

	private static void smeltBlazeHoeDrops(LootContext context, List<ItemStack> drops) {
		BlockState state = context.getOptionalParameter(LootContextParams.BLOCK_STATE);
		var item = context.getOptionalParameter(LootContextParams.TOOL);
		if (!(item instanceof ItemStack tool)
				|| !tool.is(ModItems.BLAZE_HOE)
				|| !BlazeHoeItem.isSmeltingEnabled(tool)
				|| state == null
				|| state.getBlock() instanceof CropBlock) {
			return;
		}
		ServerLevel level = context.getLevel();
		boolean changed = false;
		for (int index = 0; index < drops.size(); index++) {
			ItemStack input = drops.get(index);
			ItemStack output = BlazeHoeItem.smeltDrop(level, input);
			if (!ItemStack.matches(input, output)) {
				drops.set(index, output);
				changed = true;
			}
		}
		Vec3 origin = context.getOptionalParameter(LootContextParams.ORIGIN);
		if (changed && origin != null) {
			level.sendParticles(ParticleTypes.FLAME, origin.x, origin.y + 0.5, origin.z,
					10, 0.35, 0.35, 0.35, 0.02);
			level.playSound(null, BlockPos.containing(origin), SoundEvents.FIRECHARGE_USE,
					SoundSource.BLOCKS, 1.0F, 1.0F);
		}
	}

	private static InteractionResult breakWithInfinityTool(
			Player player,
			net.minecraft.world.level.Level level,
			net.minecraft.world.InteractionHand hand,
			BlockPos origin,
			Direction face
	) {
		ItemStack tool = player.getItemInHand(hand);
		if (level instanceof ServerLevel serverLevel && tool.is(ModItems.CRYSTAL_PICKAXE)
				&& CrystalPickaxeItem.canBreakUnbreakable(serverLevel.getBlockState(origin))) {
			return breakUnbreakable(serverLevel, player, hand, tool, origin);
		}
		if (level instanceof ServerLevel serverLevel && tool.is(ModItems.INFINITY_AXE) && !player.isShiftKeyDown()) {
			return destroyAxeChain(serverLevel, player, tool, origin)
					? InteractionResult.SUCCESS_SERVER
					: InteractionResult.PASS;
		}
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
					breakBlock(serverLevel, player, tool, origin.offset(x, y, z), drops, state -> tool.isCorrectToolForDrops(state));
				}
			}
		}
		for (ItemStack cluster : MatterClusterItem.createClusters(drops)) {
			Block.popResource(serverLevel, origin, cluster);
		}
		return InteractionResult.SUCCESS_SERVER;
	}

	private static InteractionResult breakUnbreakable(
			ServerLevel level,
			Player player,
			net.minecraft.world.InteractionHand hand,
			ItemStack tool,
			BlockPos pos
	) {
		if (!level.mayInteract(player, pos)) {
			return InteractionResult.PASS;
		}
		BlockState state = level.getBlockState(pos);
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (!PlayerBlockBreakEvents.BEFORE.invoker().beforeBlockBreak(level, player, pos, state, blockEntity)) {
			PlayerBlockBreakEvents.CANCELED.invoker().onBlockBreakCanceled(level, player, pos, state, blockEntity);
			return InteractionResult.FAIL;
		}
		if (!player.getAbilities().instabuild && !state.getBlock().asItem().equals(Items.AIR)) {
			Block.popResource(level, pos, new ItemStack(state.getBlock().asItem()));
		}
		if (level.destroyBlock(pos, false, player, 512)) {
			if (!player.getAbilities().instabuild) {
				tool.hurtAndBreak(1, player, hand);
			}
			PlayerBlockBreakEvents.AFTER.invoker().afterBlockBreak(level, player, pos, state, blockEntity);
			return InteractionResult.SUCCESS_SERVER;
		}
		return InteractionResult.PASS;
	}

	public static List<ItemStack> destroyClassicAxeArea(ServerLevel level, Player player, ItemStack tool) {
		BlockPos origin = player.blockPosition();
		List<ItemStack> drops = new ArrayList<>();
		for (int x = -AXE_RANGE; x < AXE_RANGE; x++) {
			for (int y = -3; y < AXE_RANGE * 2 - 3; y++) {
				for (int z = -AXE_RANGE; z < AXE_RANGE; z++) {
					breakBlock(level, player, tool, origin.offset(x, y, z), drops, InfinityAxeItem::canClearClassicMaterial);
				}
			}
		}
		List<ItemStack> clusters = MatterClusterItem.createClusters(drops);
		for (ItemStack cluster : clusters) {
			Block.popResource(level, origin, cluster);
		}
		return clusters;
	}

	private static boolean destroyAxeChain(ServerLevel level, Player player, ItemStack tool, BlockPos origin) {
		ArrayDeque<AxeNode> pending = new ArrayDeque<>();
		Set<BlockPos> visited = new HashSet<>();
		pending.add(new AxeNode(origin, AXE_CHAIN_STEPS, false));
		visited.add(origin);
		boolean destroyedOrigin = false;
		while (!pending.isEmpty()) {
			AxeNode node = pending.removeFirst();
			List<ItemStack> drops = new ArrayList<>();
			boolean destroyed = breakBlock(level, player, tool, node.pos(), drops, InfinityAxeItem::canClearClassicMaterial);
			if (node.pos().equals(origin)) {
				destroyedOrigin = destroyed;
			}
			for (ItemStack drop : drops) {
				Block.popResource(level, node.pos(), drop);
			}
			if (node.steps() == 0) {
				continue;
			}
			for (Direction direction : Direction.values()) {
				BlockPos nextPos = node.pos().relative(direction);
				if (!visited.add(nextPos)) {
					continue;
				}
				BlockState nextState = level.getBlockState(nextPos);
				boolean leaves = nextState.is(BlockTags.LEAVES);
				if (!nextState.is(BlockTags.LOGS) && !leaves) {
					continue;
				}
				int nextSteps = leaves && !node.leaves() ? 3 : node.steps() - 1;
				pending.addLast(new AxeNode(nextPos, nextSteps, leaves));
			}
		}
		return destroyedOrigin;
	}

	private static boolean breakBlock(
			ServerLevel level,
			Player player,
			ItemStack tool,
			BlockPos pos,
			List<ItemStack> drops,
			Predicate<BlockState> canBreak
	) {
		if (level.isOutsideBuildHeight(pos) || !level.mayInteract(player, pos)) {
			return false;
		}
		BlockState state = level.getBlockState(pos);
		if (state.isAir() || state.getDestroySpeed(level, pos) < 0.0F || !canBreak.test(state)) {
			return false;
		}
		BlockEntity blockEntity = level.getBlockEntity(pos);
		if (!PlayerBlockBreakEvents.BEFORE.invoker().beforeBlockBreak(level, player, pos, state, blockEntity)) {
			PlayerBlockBreakEvents.CANCELED.invoker().onBlockBreakCanceled(level, player, pos, state, blockEntity);
			return false;
		}
		List<ItemStack> blockDrops = player.getAbilities().instabuild
				? List.of()
				: Block.getDrops(state, level, pos, blockEntity, player, tool);
		if (level.destroyBlock(pos, false, player, 512)) {
			drops.addAll(blockDrops);
			PlayerBlockBreakEvents.AFTER.invoker().afterBlockBreak(level, player, pos, state, blockEntity);
			return true;
		}
		return false;
	}

	private record AxeNode(BlockPos pos, int steps, boolean leaves) {
	}
}
