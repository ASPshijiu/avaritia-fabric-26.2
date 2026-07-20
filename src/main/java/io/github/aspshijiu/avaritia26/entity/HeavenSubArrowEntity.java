package io.github.aspshijiu.avaritia26.entity;

import io.github.aspshijiu.avaritia26.registry.ModEntityTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public final class HeavenSubArrowEntity extends AbstractArrow {
	public HeavenSubArrowEntity(EntityType<? extends HeavenSubArrowEntity> entityType, Level level) {
		super(entityType, level);
	}

	public HeavenSubArrowEntity(
			ServerLevel level,
			Entity owner,
			double x,
			double y,
			double z,
			ItemStack bow
	) {
		super(ModEntityTypes.HEAVEN_SUB_ARROW, x, y, z, level, new ItemStack(Items.ARROW), bow.copy());
		setOwner(owner);
	}

	@Override
	public void tick() {
		super.tick();
		if (isInGround() && inGroundTime >= 20) {
			discard();
		}
	}

	@Override
	protected ItemStack getDefaultPickupItem() {
		return new ItemStack(Items.ARROW);
	}
}
