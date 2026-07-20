package io.github.aspshijiu.avaritia26.item;

import io.github.aspshijiu.avaritia26.entity.HeavenArrowEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

public final class InfinityBowItem extends Item {
	private static final int USE_DURATION = 13;

	public InfinityBowItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		player.startUsingItem(hand);
		return InteractionResult.SUCCESS;
	}

	@Override
	public void onUseTick(Level level, LivingEntity user, ItemStack stack, int remainingUseDuration) {
		if (remainingUseDuration == 1 && level instanceof ServerLevel serverLevel) {
			fire(serverLevel, user, stack);
		}
	}

	@Override
	public boolean releaseUsing(ItemStack stack, Level level, LivingEntity user, int remainingUseDuration) {
		return false;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity user) {
		return USE_DURATION;
	}

	@Override
	public ItemUseAnimation getUseAnimation(ItemStack stack) {
		return ItemUseAnimation.BOW;
	}

	private void fire(ServerLevel level, LivingEntity user, ItemStack bow) {
		HeavenArrowEntity arrow = new HeavenArrowEntity(level, user, bow);
		arrow.shootFromRotation(user, user.getXRot(), user.getYRot(), 0.0F, 3.0F, 1.0F);
		arrow.setBaseDamage(20.0);
		arrow.setCritArrow(true);
		arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
		EnchantmentHelper.onProjectileSpawned(level, bow, arrow, item -> {
		});
		level.addFreshEntity(arrow);
		level.playSound(
				null,
				user.getX(),
				user.getY(),
				user.getZ(),
				SoundEvents.ARROW_SHOOT,
				SoundSource.PLAYERS,
				1.0F,
				1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + 0.5F
		);
		if (user instanceof Player player) {
			player.awardStat(Stats.ITEM_USED.get(this));
		}
	}
}
