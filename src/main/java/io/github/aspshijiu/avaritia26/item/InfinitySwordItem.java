package io.github.aspshijiu.avaritia26.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public final class InfinitySwordItem extends Item {
	public InfinitySwordItem(Properties properties) {
		super(properties);
	}

	@Override
	public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (target.level() instanceof ServerLevel level) {
			kill(target, attacker, level);
		}
	}

	public static void kill(LivingEntity target, LivingEntity attacker, ServerLevel level) {
		if (target.isDeadOrDying()) {
			return;
		}
		if (attacker instanceof Player player) {
			target.setLastHurtByPlayer(player, 100);
		}
		target.kill(level);
	}
}
