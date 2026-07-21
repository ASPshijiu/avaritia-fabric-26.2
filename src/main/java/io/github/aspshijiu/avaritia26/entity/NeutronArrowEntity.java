package io.github.aspshijiu.avaritia26.entity;

import io.github.aspshijiu.avaritia26.registry.ModEntityTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public final class NeutronArrowEntity extends AbstractArrow {
	public NeutronArrowEntity(EntityType<? extends NeutronArrowEntity> entityType, Level level) {
		super(entityType, level);
		pickup = Pickup.DISALLOWED;
	}

	public NeutronArrowEntity(ServerLevel level, LivingEntity owner, ItemStack bow) {
		super(ModEntityTypes.NEUTRON_ARROW, owner, level, new ItemStack(Items.ARROW), bow.copy());
		pickup = Pickup.DISALLOWED;
	}

	@Override
	protected double getDefaultGravity() {
		return 0.0;
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		Entity target = result.getEntity();
		super.onHitEntity(result);
		if (level() instanceof ServerLevel serverLevel && target != getOwner()) {
			applyImpact(serverLevel, target);
		}
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		super.onHitBlock(result);
		discard();
	}

	public void applyImpact(ServerLevel level, Entity target) {
		target.hurtServer(level, damageSources().fellOutOfWorld(), 32.0F);
		discard();
	}

	@Override
	protected ItemStack getDefaultPickupItem() {
		return ItemStack.EMPTY;
	}
}
