package io.github.aspshijiu.avaritia26.entity;

import io.github.aspshijiu.avaritia26.registry.ModEntityTypes;
import io.github.aspshijiu.avaritia26.registry.ModMobEffects;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public final class BurningBallEntity extends ThrowableProjectile implements ItemSupplier {
	public static final int BURNING_DURATION = 600;
	public static final int BURNING_AMPLIFIER = 3;
	public static final double EFFECT_RADIUS = 10.0;

	public BurningBallEntity(EntityType<? extends BurningBallEntity> entityType, Level level) {
		super(entityType, level);
	}

	public BurningBallEntity(ServerLevel level, LivingEntity owner) {
		this(ModEntityTypes.BURNING_BALL, level);
		setOwner(owner);
		setPos(owner.getX(), owner.getEyeY() + 0.1, owner.getZ());
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		if (level() instanceof ServerLevel serverLevel) {
			applyImpact(serverLevel, result.getLocation());
		}
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		if (level() instanceof ServerLevel serverLevel) {
			applyImpact(serverLevel, result.getLocation());
		}
	}

	public void applyImpact(ServerLevel level, Vec3 center) {
		Entity owner = getOwner();
		level.getEntitiesOfClass(LivingEntity.class,
				net.minecraft.world.phys.AABB.ofSize(center, EFFECT_RADIUS * 2.0, EFFECT_RADIUS * 2.0,
						EFFECT_RADIUS * 2.0),
				entity -> entity.isAlive() && !entity.isSpectator() && entity != owner)
				.forEach(entity -> entity.addEffect(
						new MobEffectInstance(ModMobEffects.BURNING, BURNING_DURATION, BURNING_AMPLIFIER)));
		level.sendParticles(ParticleTypes.FLAME, center.x, center.y, center.z,
				80, EFFECT_RADIUS * 0.5, 1.0, EFFECT_RADIUS * 0.5, 0.08);
		discard();
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(Items.FIRE_CHARGE);
	}

	@Override
	public boolean ignoreExplosion(Explosion explosion) {
		return true;
	}
}
