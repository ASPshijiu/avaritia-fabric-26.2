package io.github.aspshijiu.avaritia26.entity;

import io.github.aspshijiu.avaritia26.registry.ModEntityTypes;
import io.github.aspshijiu.avaritia26.registry.ModMobEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public final class BurningArrowEntity extends Arrow {
	public static final int BURNING_DURATION = 1200;
	private double configuredBaseDamage = 4.0;

	public BurningArrowEntity(EntityType<? extends BurningArrowEntity> entityType, Level level) {
		super(entityType, level);
		pickup = Pickup.DISALLOWED;
	}

	public BurningArrowEntity(ServerLevel level, LivingEntity owner, ItemStack bow) {
		this(ModEntityTypes.BURNING_ARROW, level);
		setOwner(owner);
		setPos(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
		setBaseDamage(configuredBaseDamage);
		pickup = Pickup.CREATIVE_ONLY;
	}

	public void configure(double baseDamage, boolean critical, int fireTicks) {
		configuredBaseDamage = baseDamage;
		setBaseDamage(baseDamage);
		setCritArrow(critical);
		setRemainingFireTicks(fireTicks);
	}

	public double getConfiguredBaseDamage() {
		return configuredBaseDamage;
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		if (level() instanceof ServerLevel serverLevel && result.getEntity() != getOwner()) {
			super.onHitEntity(result);
			applyImpact(serverLevel, result.getEntity());
		}
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		discard();
	}

	public void applyImpact(ServerLevel level, Entity target) {
		if (target instanceof LivingEntity livingEntity) {
			livingEntity.addEffect(new MobEffectInstance(ModMobEffects.BURNING, BURNING_DURATION));
		}
		discard();
	}

	@Override
	protected float getWaterInertia() {
		return 1.0F;
	}

	@Override
	protected ItemStack getDefaultPickupItem() {
		return new ItemStack(Items.ARROW);
	}
}
