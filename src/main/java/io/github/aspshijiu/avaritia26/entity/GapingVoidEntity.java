package io.github.aspshijiu.avaritia26.entity;

import java.util.List;
import java.util.UUID;

import io.github.aspshijiu.avaritia26.registry.ModSounds;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public final class GapingVoidEntity extends Entity {
	public static final int MAX_LIFETIME = 186;
	public static final double SUCK_RANGE = 20.0;
	private static final double COLLAPSE_START = 0.95;
	private static final EntityDataAccessor<Integer> AGE = SynchedEntityData.defineId(
			GapingVoidEntity.class,
			EntityDataSerializers.INT
	);

	private UUID userUuid;
	private Player user;

	public GapingVoidEntity(EntityType<? extends GapingVoidEntity> entityType, Level level) {
		super(entityType, level);
		noPhysics = true;
		setNoGravity(true);
	}

	public GapingVoidEntity(ServerLevel level, Player user) {
		this(io.github.aspshijiu.avaritia26.registry.ModEntityTypes.GAPING_VOID, level);
		this.user = user;
		userUuid = user == null ? null : user.getUUID();
	}

	public int getAge() {
		return entityData.get(AGE);
	}

	public static double getVoidScale(double age) {
		double life = age / MAX_LIFETIME;
		double curve;
		if (life < COLLAPSE_START) {
			curve = 0.005 + ease(1.0 - (COLLAPSE_START - life) / COLLAPSE_START) * 0.995;
		} else {
			curve = ease(1.0 - (life - COLLAPSE_START) / (1.0 - COLLAPSE_START));
		}
		return 10.0 * curve;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		builder.define(AGE, 0);
	}

	@Override
	protected void readAdditionalSaveData(ValueInput input) {
		entityData.set(AGE, input.getIntOr("age", 0));
		String user = input.getStringOr("user", "");
		if (!user.isEmpty()) {
			try {
				userUuid = UUID.fromString(user);
			} catch (IllegalArgumentException ignored) {
				userUuid = null;
			}
		}
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput output) {
		output.putInt("age", getAge());
		if (userUuid != null) {
			output.putString("user", userUuid.toString());
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (!(level() instanceof ServerLevel serverLevel)) {
			return;
		}

		int age = getAge();
		if (age >= MAX_LIFETIME) {
			collapse(serverLevel);
			return;
		}
		if (age == 0) {
			serverLevel.playSound(
					null,
					blockPosition(),
					ModSounds.GAPING_VOID,
					SoundSource.HOSTILE,
					8.0F,
					1.0F
			);
		}
		entityData.set(AGE, age + 1);
		serverLevel.sendParticles(
				ParticleTypes.PORTAL,
				getX(),
				getY(),
				getZ(),
				12,
				2.5,
				2.5,
				2.5,
				0.15
		);
		pullEntities(serverLevel, age);
		damageEntities(serverLevel, age, 5.0F);
		if (age % 10 == 0) {
			breakBlocks(serverLevel, age);
		}
	}

	private void pullEntities(ServerLevel serverLevel, int age) {
		double radius = getVoidScale(age) * 0.5;
		double currentRange = Math.min(SUCK_RANGE, Math.max(1.0, radius * SUCK_RANGE));
		AABB area = getBoundingBox().inflate(currentRange);
		List<Entity> entities = serverLevel.getEntitiesOfClass(Entity.class, area, entity -> {
			if (entity == this || entity.isRemoved()) {
				return false;
			}
			return !(entity instanceof Player player) || !player.isCreative() || !player.isFallFlying();
		});
		for (Entity entity : entities) {
			Vec3 offset = position().subtract(entity.position());
			double distance = offset.length();
			if (distance < 1.0E-6 || distance > SUCK_RANGE) {
				continue;
			}
			double strength = Math.pow(1.0 - distance / SUCK_RANGE, 2.0);
			double power = 0.075 * radius;
			entity.setDeltaMovement(entity.getDeltaMovement().add(offset.scale(strength * power / distance)));
		}
	}

	private void damageEntities(ServerLevel serverLevel, int age, float damage) {
		double radius = getVoidScale(age) * 0.5 * 0.95;
		if (radius <= 0.0) {
			return;
		}
		Player user = getUser(serverLevel);
		for (LivingEntity entity : serverLevel.getEntitiesOfClass(
				LivingEntity.class,
				getBoundingBox().inflate(radius),
				entity -> !(entity instanceof Player player) || !player.isCreative()
		)) {
			if (entity.position().distanceToSqr(position()) <= radius * radius) {
				entity.hurtServer(
						serverLevel,
						user == null ? damageSources().fellOutOfWorld() : damageSources().playerAttack(user),
						damage
				);
			}
		}
	}

	private void breakBlocks(ServerLevel serverLevel, int age) {
		Player user = getUser(serverLevel);
		if (user == null || !user.mayBuild()) {
			return;
		}
		double radius = getVoidScale(age) * 0.5 * 0.95;
		int range = (int) Math.floor(radius);
		BlockPos center = blockPosition();
		for (BlockPos mutablePos : BlockPos.betweenClosed(center.offset(-range, -range, -range), center.offset(range, range, range))) {
			BlockPos pos = mutablePos.immutable();
			if (serverLevel.isOutsideBuildHeight(pos) || center.distSqr(pos) > radius * radius) {
				continue;
			}
			BlockState state = serverLevel.getBlockState(pos);
			if (state.isAir() || state.getBlock().getExplosionResistance() > 10.0F || !serverLevel.mayInteract(user, pos)) {
				continue;
			}
			BlockEntity blockEntity = serverLevel.getBlockEntity(pos);
			if (!PlayerBlockBreakEvents.BEFORE.invoker().beforeBlockBreak(serverLevel, user, pos, state, blockEntity)) {
				PlayerBlockBreakEvents.CANCELED.invoker().onBlockBreakCanceled(
						serverLevel,
						user,
						pos,
						state,
						blockEntity
				);
				continue;
			}
			if (serverLevel.destroyBlock(pos, false, user, 512)) {
				PlayerBlockBreakEvents.AFTER.invoker().afterBlockBreak(serverLevel, user, pos, state, blockEntity);
			}
		}
	}

	private void collapse(ServerLevel serverLevel) {
		serverLevel.explode(this, getX(), getY(), getZ(), 6.0F, Level.ExplosionInteraction.NONE);
		damageEntities(serverLevel, MAX_LIFETIME - 1, 1000.0F);
		discard();
	}

	private Player getUser(ServerLevel serverLevel) {
		if (user != null && !user.isRemoved()) {
			return user;
		}
		user = userUuid == null ? null : serverLevel.getPlayerByUUID(userUuid);
		return user;
	}

	private static double ease(double input) {
		double shifted = input - 1.0;
		return Math.sqrt(Math.max(0.0, 1.0 - shifted * shifted));
	}

	@Override
	public boolean hurtServer(ServerLevel serverLevel, DamageSource source, float amount) {
		return false;
	}

	@Override
	public boolean shouldRenderAtSqrDistance(double distance) {
		return true;
	}
}
