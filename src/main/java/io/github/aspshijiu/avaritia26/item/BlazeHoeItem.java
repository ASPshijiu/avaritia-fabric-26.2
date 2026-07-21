package io.github.aspshijiu.avaritia26.item;

import java.util.function.Consumer;

import io.github.aspshijiu.avaritia26.registry.ModBlocks;
import io.github.aspshijiu.avaritia26.registry.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public final class BlazeHoeItem extends HoeItem {
	public BlazeHoeItem(ToolMaterial material, Properties properties) {
		super(material, 0.0F, 25.0F, properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (!player.isShiftKeyDown()) {
			return InteractionResult.PASS;
		}
		boolean enabled = !isSmeltingEnabled(stack);
		stack.set(ModDataComponents.BLAZE_TOOL_MODE, enabled);
		if (player instanceof ServerPlayer serverPlayer && serverPlayer.connection != null) {
			serverPlayer.sendSystemMessage(Component.translatable(enabled
					? "tooltip.avaritia26.blaze_hoe.smelt_enabled"
					: "tooltip.avaritia26.blaze_hoe.smelt_disabled"), true);
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		BlockPos pos = context.getClickedPos();
		Level level = context.getLevel();
		BlockState state = level.getBlockState(pos);
		if (isSmeltingEnabled(context.getItemInHand())) {
			if (state.is(Blocks.SOUL_SAND)) {
				return transform(context, Blocks.SOUL_SOIL.defaultBlockState());
			}
			if (state.is(Blocks.SOUL_SOIL)) {
				return transform(context, ModBlocks.SOUL_FARMLAND.defaultBlockState());
			}
		}
		if (state.is(ModBlocks.SOUL_FARMLAND)) {
			return InteractionResult.PASS;
		}
		return super.useOn(context);
	}

	public static boolean isSmeltingEnabled(ItemStack stack) {
		return stack.getOrDefault(ModDataComponents.BLAZE_TOOL_MODE, false);
	}

	public static ItemStack smeltDrop(ServerLevel level, ItemStack input) {
		return level.getServer().getRecipeManager()
				.getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(input), level)
				.map(recipe -> recipe.value().assemble(new SingleRecipeInput(input)))
				.filter(result -> !result.isEmpty())
				.map(result -> result.copyWithCount(result.getCount() * input.getCount()))
				.orElse(input);
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
		tooltip.accept(Component.translatable("tooltip.avaritia26.blaze_hoe.desc")
				.withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
		tooltip.accept(Component.translatable(isSmeltingEnabled(stack)
				? "tooltip.avaritia26.blaze_hoe.smelt_enabled"
				: "tooltip.avaritia26.blaze_hoe.smelt_disabled").withStyle(ChatFormatting.GRAY));
	}

	private static InteractionResult transform(UseOnContext context, BlockState result) {
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		level.playSound(context.getPlayer(), pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
		if (!level.isClientSide()) {
			level.setBlockAndUpdate(pos, result);
		}
		return InteractionResult.SUCCESS;
	}
}
