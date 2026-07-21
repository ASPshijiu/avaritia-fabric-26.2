package io.github.aspshijiu.avaritia26.item;

import java.util.function.Consumer;

import io.github.aspshijiu.avaritia26.entity.BlazeFireballEntity;
import io.github.aspshijiu.avaritia26.registry.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

public final class BlazeSwordItem extends Item {
	public BlazeSwordItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (player.isShiftKeyDown()) {
			boolean enabled = !isFireballEnabled(stack);
			stack.set(ModDataComponents.BLAZE_TOOL_MODE, enabled);
			if (player instanceof ServerPlayer serverPlayer && serverPlayer.connection != null) {
				serverPlayer.sendSystemMessage(Component.translatable(enabled
						? "tooltip.avaritia26.blaze_sword.fireball_enabled"
						: "tooltip.avaritia26.blaze_sword.fireball_disabled"), true);
			}
			return InteractionResult.SUCCESS;
		}
		if (!isFireballEnabled(stack)) {
			return InteractionResult.PASS;
		}
		if (level instanceof ServerLevel serverLevel) {
			serverLevel.addFreshEntity(createFireball(serverLevel, player));
			serverLevel.playSound(null, player.blockPosition(), SoundEvents.SNOWBALL_THROW,
					SoundSource.NEUTRAL, 0.5F, 0.7F);
			if (!(player instanceof ServerPlayer serverPlayer) || serverPlayer.connection != null) {
				player.getCooldowns().addCooldown(stack, 40);
			}
		}
		return InteractionResult.SUCCESS;
	}

	public BlazeFireballEntity createFireball(ServerLevel level, Player player) {
		return new BlazeFireballEntity(level, player);
	}

	public static boolean isFireballEnabled(ItemStack stack) {
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
		tooltip.accept(Component.translatable("tooltip.avaritia26.blaze_sword.desc")
				.withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
		tooltip.accept(Component.translatable(isFireballEnabled(stack)
				? "tooltip.avaritia26.blaze_sword.fireball_enabled"
				: "tooltip.avaritia26.blaze_sword.fireball_disabled").withStyle(ChatFormatting.GRAY));
	}
}
