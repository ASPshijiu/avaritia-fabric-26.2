package io.github.aspshijiu.avaritia26.item;

import java.util.function.Consumer;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public final class CrystalShovelItem extends Item {
	public CrystalShovelItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		BlockState state = level.getBlockState(context.getClickedPos());
		if (context.getClickedFace() == Direction.DOWN
				|| !level.getBlockState(context.getClickedPos().above()).isAir()
				|| !canFlatten(state)) {
			return InteractionResult.PASS;
		}
		level.playSound(context.getPlayer(), context.getClickedPos(),
				SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
		if (!level.isClientSide()) {
			level.setBlock(context.getClickedPos(), Blocks.DIRT_PATH.defaultBlockState(), 11);
			if (context.getPlayer() != null) {
				context.getItemInHand().hurtAndBreak(1, context.getPlayer(), context.getHand());
			}
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, EquipmentSlot slot) {
		if (slot != EquipmentSlot.MAINHAND || !(entity instanceof Player player)) {
			return;
		}
		addEffect(player, new MobEffectInstance(MobEffects.HASTE, 20, 2, false, true));
		addEffect(player, new MobEffectInstance(MobEffects.SPEED, 20, 2, false, true));
		removeEffect(player, MobEffects.SLOWNESS);
		removeEffect(player, MobEffects.MINING_FATIGUE);
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
		tooltip.accept(Component.translatable("tooltip.avaritia26.crystal_shovel.desc")
				.withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
	}

	private static void addEffect(Player player, MobEffectInstance effect) {
		if (player instanceof ServerPlayer serverPlayer && serverPlayer.connection == null) {
			player.getActiveEffectsMap().put(effect.getEffect(), effect);
		} else {
			player.addEffect(effect);
		}
	}

	private static void removeEffect(Player player, Holder<MobEffect> effect) {
		if (player instanceof ServerPlayer serverPlayer && serverPlayer.connection == null) {
			player.getActiveEffectsMap().remove(effect);
		} else {
			player.removeEffect(effect);
		}
	}

	private static boolean canFlatten(BlockState state) {
		return state.is(Blocks.GRASS_BLOCK)
				|| state.is(Blocks.DIRT)
				|| state.is(Blocks.COARSE_DIRT)
				|| state.is(Blocks.PODZOL)
				|| state.is(Blocks.MYCELIUM)
				|| state.is(Blocks.ROOTED_DIRT);
	}
}
