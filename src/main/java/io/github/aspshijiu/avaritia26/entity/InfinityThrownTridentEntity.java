package io.github.aspshijiu.avaritia26.entity;

import io.github.aspshijiu.avaritia26.registry.ModEntityTypes;
import io.github.aspshijiu.avaritia26.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public final class InfinityThrownTridentEntity extends ThrownTrident {
	private boolean returning;

	public InfinityThrownTridentEntity(EntityType<? extends InfinityThrownTridentEntity> type, Level level) {
		super(type, level);
	}

	public InfinityThrownTridentEntity(ServerLevel level, LivingEntity owner, ItemStack stack) {
		this(ModEntityTypes.INFINITY_THROWN_TRIDENT, level);
		setOwner(owner);
		setPos(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
		setPickupItemStack(stack.copyWithCount(1));
	}

	@Override
	public void tick() {
		super.tick();
		if (inGroundTime > 4) {
			returning = true;
		}
		Entity owner = getOwner();
		if (!returning || owner == null || !owner.isAlive()) {
			return;
		}
		setNoPhysics(true);
		Vec3 offset = owner.getEyePosition().subtract(position());
		if (offset.lengthSqr() < 2.25) {
			if (level() instanceof ServerLevel serverLevel && owner instanceof Player player
					&& !player.getAbilities().instabuild
					&& !player.getInventory().add(getPickupItemStackOrigin().copy())) {
				spawnAtLocation(serverLevel, getPickupItemStackOrigin().copy());
			}
			if (!level().isClientSide()) {
				discard();
			}
			return;
		}
		setDeltaMovement(getDeltaMovement().scale(0.95).add(offset.normalize().scale(0.15)));
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		applyEntityImpact(result.getEntity());
	}

	public void applyEntityImpact(Entity target) {
		returning = true;
		Entity owner = getOwner();
		if (level() instanceof ServerLevel level) {
			target.hurtServer(level, damageSources().trident(this, owner == null ? this : owner), Float.MAX_VALUE);
			summonLightning(level, target.blockPosition());
		}
		setDeltaMovement(getDeltaMovement().scale(-0.01));
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);
		applyBlockImpact(result.getBlockPos());
	}

	public void applyBlockImpact(BlockPos pos) {
		returning = true;
		if (level() instanceof ServerLevel level) {
			summonLightning(level, pos);
		}
	}

	@Override
	protected ItemStack getDefaultPickupItem() {
		return new ItemStack(ModItems.INFINITY_TRIDENT);
	}

	@Override
	public void tickDespawn() {
	}

	@Override
	protected float getWaterInertia() {
		return 1.49F;
	}

	private void summonLightning(ServerLevel level, BlockPos pos) {
		for (int index = 0; index < 2; index++) {
			LightningBolt lightning = EntityTypes.LIGHTNING_BOLT.create(level, EntitySpawnReason.TRIGGERED);
			if (lightning != null) {
				lightning.setPos(Vec3.atBottomCenterOf(pos));
				if (getOwner() instanceof ServerPlayer player) {
					lightning.setCause(player);
				}
				level.addFreshEntity(lightning);
			}
		}
	}
}
