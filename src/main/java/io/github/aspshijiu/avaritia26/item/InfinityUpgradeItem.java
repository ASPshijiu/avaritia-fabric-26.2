package io.github.aspshijiu.avaritia26.item;

import java.util.function.Consumer;

import io.github.aspshijiu.avaritia26.registry.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public final class InfinityUpgradeItem extends Item {
	public InfinityUpgradeItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Player player = context.getPlayer();
		if (player == null || !player.isShiftKeyDown()) {
			return InteractionResult.PASS;
		}
		BlockPos pos = context.getClickedPos();
		BlockState state = context.getLevel().getBlockState(pos);
		Block nextBlock = nextBlock(state);
		if (nextBlock == null) {
			return InteractionResult.PASS;
		}
		if (!(context.getLevel() instanceof ServerLevel level)) {
			return InteractionResult.SUCCESS;
		}
		if (!level.setBlockAndUpdate(pos, nextBlock.withPropertiesOf(state))) {
			return InteractionResult.FAIL;
		}
		level.playSound(null, pos, SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 1.0F, 1.0F);
		damage(context.getItemInHand());
		return InteractionResult.SUCCESS_SERVER;
	}

	@Override
	public void appendHoverText(
			ItemStack stack,
			TooltipContext context,
			TooltipDisplay display,
			Consumer<Component> tooltipAdder,
			TooltipFlag flag
	) {
		tooltipAdder.accept(
				Component.translatable("tooltip.avaritia26.infinity_upgrade")
						.withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC)
		);
	}

	private static void damage(ItemStack stack) {
		int damage = stack.getDamageValue() + 1;
		if (damage >= stack.getMaxDamage()) {
			stack.shrink(1);
		} else {
			stack.setDamageValue(damage);
		}
	}

	private static Block nextBlock(BlockState state) {
		if (state.is(ModBlocks.NEUTRON_COLLECTOR)) {
			return ModBlocks.DENSE_NEUTRON_COLLECTOR;
		}
		if (state.is(ModBlocks.DENSE_NEUTRON_COLLECTOR)) {
			return ModBlocks.DENSER_NEUTRON_COLLECTOR;
		}
		if (state.is(ModBlocks.DENSER_NEUTRON_COLLECTOR)) {
			return ModBlocks.DENSEST_NEUTRON_COLLECTOR;
		}
		if (state.is(ModBlocks.NEUTRON_COMPRESSOR)) {
			return ModBlocks.DENSE_NEUTRON_COMPRESSOR;
		}
		if (state.is(ModBlocks.DENSE_NEUTRON_COMPRESSOR)) {
			return ModBlocks.DENSER_NEUTRON_COMPRESSOR;
		}
		if (state.is(ModBlocks.DENSER_NEUTRON_COMPRESSOR)) {
			return ModBlocks.DENSEST_NEUTRON_COMPRESSOR;
		}
		return null;
	}
}
