package io.github.aspshijiu.avaritia26.entity;

import io.github.aspshijiu.avaritia26.registry.ModEntityTypes;
import io.github.aspshijiu.avaritia26.registry.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public final class EndestPearlEntity extends ThrowableItemProjectile {
	public EndestPearlEntity(EntityType<? extends EndestPearlEntity> entityType, Level level) {
		super(entityType, level);
	}

	public EndestPearlEntity(ServerLevel level, LivingEntity owner, ItemStack stack) {
		super(ModEntityTypes.ENDEST_PEARL, owner, level, stack.copyWithCount(1));
	}

	public EndestPearlEntity(Level level, double x, double y, double z, ItemStack stack) {
		super(ModEntityTypes.ENDEST_PEARL, x, y, z, level, stack.copyWithCount(1));
	}

	@Override
	protected Item getDefaultItem() {
		return ModItems.ENDEST_PEARL;
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		super.onHitEntity(result);
		result.getEntity().hurt(damageSources().thrown(this, getOwner()), 0.0F);
	}

	@Override
	protected void onHit(HitResult result) {
		super.onHit(result);
		if (!(level() instanceof ServerLevel serverLevel) || isRemoved()) {
			return;
		}

		Vec3 voidPosition = result.getLocation();
		if (result instanceof BlockHitResult blockHit) {
			Direction direction = blockHit.getDirection();
			voidPosition = voidPosition.add(
					direction.getStepX() * 0.25,
					direction.getStepY() * 0.25,
					direction.getStepZ() * 0.25
			);
		}
		Entity owner = getOwner();
		GapingVoidEntity gapingVoid = new GapingVoidEntity(
				serverLevel,
				owner instanceof net.minecraft.world.entity.player.Player player ? player : null
		);
		gapingVoid.setPos(voidPosition);
		serverLevel.addFreshEntity(gapingVoid);
		serverLevel.broadcastEntityEvent(this, (byte) 3);
		discard();
	}

	@Override
	public void handleEntityEvent(byte event) {
		if (event == 3) {
			for (int index = 0; index < 8; index++) {
				level().addParticle(
						net.minecraft.core.particles.ParticleTypes.PORTAL,
						getX(),
						getY(),
						getZ(),
						0.0,
						0.0,
						0.0
				);
			}
			return;
		}
		super.handleEntityEvent(event);
	}
}
