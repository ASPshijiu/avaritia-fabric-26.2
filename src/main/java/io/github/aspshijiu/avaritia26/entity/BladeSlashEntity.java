package io.github.aspshijiu.avaritia26.entity;

import java.util.HashSet;
import java.util.Set;

import io.github.aspshijiu.avaritia26.registry.ModEntityTypes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public final class BladeSlashEntity extends Projectile {
	public static final float DAMAGE = 200.0F;
	public static final int LIFETIME = 10;
	public static final float SPEED = 2.0F;

	private final Set<Integer> hitEntities = new HashSet<>();

	public BladeSlashEntity(EntityType<? extends BladeSlashEntity> entityType, Level level) {
		super(entityType, level);
		noPhysics = true;
		setNoGravity(true);
	}

	public BladeSlashEntity(ServerLevel level, LivingEntity owner) {
		this(ModEntityTypes.BLADE_SLASH, level);
		setOwner(owner);
		setPos(owner.getX(), owner.getY() * 0.3 + owner.getEyeY() * 0.7, owner.getZ());
		shootFromRotation(owner, owner.getXRot(), owner.getYRot(), 0.0F, SPEED, 0.5F);
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
	}

	@Override
	public void tick() {
		super.tick();
		if (tickCount > LIFETIME) {
			discard();
			return;
		}

		Vec3 start = position();
		Vec3 movement = getDeltaMovement();
		Vec3 end = start.add(movement);
		BlockHitResult blockHit = level().clip(new ClipContext(
				start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this
		));
		if (blockHit.getType() != HitResult.Type.MISS) {
			end = blockHit.getLocation();
		}

		if (level() instanceof ServerLevel serverLevel) {
			for (Entity target : serverLevel.getEntities(
					this,
					getBoundingBox().expandTowards(movement).inflate(0.5),
					this::canHitEntity
			)) {
				if (!hitEntities.contains(target.getId())
						&& (target.getBoundingBox().inflate(0.5).contains(start)
						|| target.getBoundingBox().inflate(0.5).clip(start, end).isPresent())) {
					applyImpact(serverLevel, target);
				}
			}
		}

		setPos(end);
		updateRotation();
		if (blockHit.getType() != HitResult.Type.MISS) {
			if (level() instanceof ServerLevel serverLevel) {
				serverLevel.sendParticles(ParticleTypes.CRIT, getX(), getY(), getZ(),
						15, 0.5, 0.5, 0.5, 0.1);
			}
			discard();
		}
	}

	public void applyImpact(ServerLevel level, Entity target) {
		if (!hitEntities.add(target.getId())) {
			return;
		}
		Entity owner = getOwner();
		DamageSource source;
		if (owner instanceof Player player) {
			source = damageSources().playerAttack(player);
		} else if (owner instanceof LivingEntity living) {
			source = damageSources().mobAttack(living);
		} else {
			source = damageSources().generic();
		}
		target.hurtServer(level, source, DAMAGE);
	}
}
