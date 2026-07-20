package io.github.aspshijiu.avaritia26.item;

import io.github.aspshijiu.avaritia26.entity.EndestPearlEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;

public final class EndestPearlItem extends Item implements ProjectileItem {
	public EndestPearlItem(Properties properties) {
		super(properties);
	}

	@Override
	public Projectile asProjectile(Level level, Position position, ItemStack stack, Direction direction) {
		return new EndestPearlEntity(level, position.x(), position.y(), position.z(), stack.copyWithCount(1));
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		level.playSound(
				null,
				player.getX(),
				player.getY(),
				player.getZ(),
				SoundEvents.ENDER_PEARL_THROW,
				SoundSource.NEUTRAL,
				0.5F,
				0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
		);
		if (level instanceof ServerLevel serverLevel) {
			Projectile.spawnProjectileFromRotation(
					EndestPearlEntity::new,
					serverLevel,
					stack,
					player,
					0.0F,
					1.5F,
					1.0F
			);
			player.getCooldowns().addCooldown(stack, 30);
		}
		player.awardStat(Stats.ITEM_USED.get(this));
		stack.consume(1, player);
		return InteractionResult.SUCCESS;
	}
}
