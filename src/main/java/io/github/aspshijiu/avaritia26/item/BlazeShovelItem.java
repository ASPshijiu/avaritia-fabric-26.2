package io.github.aspshijiu.avaritia26.item;

import java.util.Map;
import java.util.function.Consumer;

import io.github.aspshijiu.avaritia26.registry.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public final class BlazeShovelItem extends ShovelItem {
	public static final Map<Block, Block> TRANSFORMATIONS = Map.ofEntries(
			Map.entry(Blocks.DIRT, Blocks.SOUL_SOIL),
			Map.entry(Blocks.SAND, Blocks.SOUL_SAND),
			Map.entry(Blocks.STONE, Blocks.BLACKSTONE),
			Map.entry(Blocks.DEEPSLATE, Blocks.BASALT),
			Map.entry(Blocks.OBSIDIAN, Blocks.CRYING_OBSIDIAN),
			Map.entry(Blocks.GRASS_BLOCK, Blocks.WARPED_NYLIUM),
			Map.entry(Blocks.PODZOL, Blocks.CRIMSON_NYLIUM),
			Map.entry(Blocks.RED_MUSHROOM, Blocks.CRIMSON_FUNGUS),
			Map.entry(Blocks.BROWN_MUSHROOM, Blocks.WARPED_FUNGUS),
			Map.entry(Blocks.PACKED_ICE, Blocks.MAGMA_BLOCK),
			Map.entry(Blocks.BEACON, Blocks.ANCIENT_DEBRIS),
			Map.entry(Blocks.STONE_BRICKS, Blocks.NETHER_BRICKS),
			Map.entry(Blocks.BRICKS, Blocks.RED_NETHER_BRICKS),
			Map.entry(Blocks.TORCH, Blocks.SOUL_TORCH),
			Map.entry(Blocks.LANTERN, Blocks.SOUL_LANTERN),
			Map.entry(Blocks.CAMPFIRE, Blocks.SOUL_CAMPFIRE),
			Map.entry(Blocks.DEAD_BUSH, Blocks.WITHER_ROSE)
	);

	public BlazeShovelItem(ToolMaterial material, Properties properties) {
		super(material, 0.0F, 25.0F, properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		if (!player.isShiftKeyDown()) {
			return InteractionResult.PASS;
		}
		ItemStack stack = player.getItemInHand(hand);
		boolean enabled = !isTransformationEnabled(stack);
		stack.set(ModDataComponents.BLAZE_TOOL_MODE, enabled);
		if (player instanceof ServerPlayer serverPlayer && serverPlayer.connection != null) {
			serverPlayer.sendSystemMessage(Component.translatable(enabled
					? "tooltip.avaritia26.blaze_shovel.transformation_enabled"
					: "tooltip.avaritia26.blaze_shovel.transformation_disabled"), true);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		if (!isTransformationEnabled(context.getItemInHand())) {
			return super.useOn(context);
		}
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		Block result = TRANSFORMATIONS.get(level.getBlockState(pos).getBlock());
		if (result == null) {
			return InteractionResult.PASS;
		}
		level.playSound(context.getPlayer(), pos, SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0F, 1.0F);
		if (!level.isClientSide()) {
			level.setBlockAndUpdate(pos, result.defaultBlockState());
		}
		return InteractionResult.SUCCESS;
	}

	public static boolean isTransformationEnabled(ItemStack stack) {
		return stack.getOrDefault(ModDataComponents.BLAZE_TOOL_MODE, false);
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
		tooltip.accept(Component.translatable("tooltip.avaritia26.blaze_shovel.desc")
				.withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
		tooltip.accept(Component.translatable(isTransformationEnabled(stack)
				? "tooltip.avaritia26.blaze_shovel.transformation_enabled"
				: "tooltip.avaritia26.blaze_shovel.transformation_disabled").withStyle(ChatFormatting.GRAY));
	}
}
