package io.github.aspshijiu.avaritia26.entity;

import io.github.aspshijiu.avaritia26.registry.ModEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public final class HeavenArrowEntity extends AbstractArrow {
	public HeavenArrowEntity(EntityType<? extends HeavenArrowEntity> entityType, Level level) {
		super(entityType, level);
	}

	public HeavenArrowEntity(ServerLevel level, LivingEntity owner, ItemStack bow) {
		super(ModEntityTypes.HEAVEN_ARROW, owner, level, new ItemStack(Items.ARROW), bow.copy());
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);
		if (level() instanceof ServerLevel serverLevel) {
			createBarrage(serverLevel, result.getBlockPos());
			discard();
		}
	}

	public void createBarrage(ServerLevel level, BlockPos impactPos) {
		Entity owner = getOwner();
		ItemStack weapon = getWeaponItem();
		var random = level.getRandom();
		for (int index = 0; index < 10; index++) {
			double angle = random.nextDouble() * Math.PI * 2.0;
			double distance = random.nextGaussian() * 0.5;
			double x = impactPos.getX() + 0.5 + Math.sin(angle) * distance;
			double y = impactPos.getY() + 25.5;
			double z = impactPos.getZ() + 0.5 + Math.cos(angle) * distance;
			double velocityAngle = random.nextDouble() * Math.PI * 2.0;
			double velocityDistance = random.nextDouble() * 0.35;
			HeavenSubArrowEntity subArrow = new HeavenSubArrowEntity(
					level,
					owner,
					x,
					y,
					z,
					weapon == null ? ItemStack.EMPTY : weapon
			);
			subArrow.setDeltaMovement(
					Math.sin(velocityAngle) * velocityDistance,
					-(random.nextDouble() * 1.85 + 0.15),
					Math.cos(velocityAngle) * velocityDistance
			);
			subArrow.setBaseDamage(20.0);
			subArrow.setCritArrow(true);
			subArrow.pickup = pickup;
			level.addFreshEntity(subArrow);
		}
	}

	@Override
	protected ItemStack getDefaultPickupItem() {
		return new ItemStack(Items.ARROW);
	}
}
