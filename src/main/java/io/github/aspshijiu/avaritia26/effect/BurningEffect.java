package io.github.aspshijiu.avaritia26.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public final class BurningEffect extends MobEffect {
	public BurningEffect() {
		super(MobEffectCategory.HARMFUL, 0xD0F9FF);
	}

	@Override
	public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
		entity.hurtServer(level, entity.damageSources().inFire(), entity.getMaxHealth() * 0.05F);
		entity.invulnerableTime = 10;
		return true;
	}

	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		int interval = 20 >> amplifier;
		return interval == 0 || duration % interval == 0;
	}
}
