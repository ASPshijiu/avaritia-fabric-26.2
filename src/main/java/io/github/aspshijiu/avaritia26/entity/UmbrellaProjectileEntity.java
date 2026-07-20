package io.github.aspshijiu.avaritia26.entity;

import io.github.aspshijiu.avaritia26.registry.ModEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public final class UmbrellaProjectileEntity extends ThrowableItemProjectile {
	public enum Mode {
		SUN,
		RAIN,
		STORM
	}

	private final Mode mode;

	public UmbrellaProjectileEntity(
			EntityType<? extends UmbrellaProjectileEntity> entityType,
			Level level,
			Mode mode
	) {
		super(entityType, level);
		this.mode = mode;
	}

	public UmbrellaProjectileEntity(ServerLevel level, LivingEntity owner, Mode mode) {
		super(entityType(mode), owner, level, new ItemStack(Items.FIRE_CHARGE));
		this.mode = mode;
	}

	@Override
	protected Item getDefaultItem() {
		return Items.FIRE_CHARGE;
	}

	@Override
	protected void onHit(HitResult result) {
		super.onHit(result);
		if (!(level() instanceof ServerLevel serverLevel)) {
			return;
		}
		BlockPos impactPos = null;
		if (result instanceof BlockHitResult blockHit) {
			impactPos = blockHit.getBlockPos().relative(blockHit.getDirection());
		} else if (result instanceof EntityHitResult entityHit) {
			impactPos = entityHit.getEntity().blockPosition();
		}
		if (impactPos != null) {
			applyImpact(serverLevel, impactPos);
		}
		discard();
	}

	public void applyImpact(ServerLevel level, BlockPos pos) {
		switch (mode) {
			case SUN -> level.setBlock(pos, Blocks.LAVA.defaultBlockState(), 3);
			case RAIN -> level.setBlock(pos, Blocks.WATER.defaultBlockState(), 3);
			case STORM -> {
				LightningBolt lightning = EntityTypes.LIGHTNING_BOLT.create(level, EntitySpawnReason.TRIGGERED);
				if (lightning != null) {
					lightning.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
					if (getOwner() instanceof ServerPlayer player) {
						lightning.setCause(player);
					}
					level.addFreshEntity(lightning);
				}
			}
		}
	}

	private static EntityType<UmbrellaProjectileEntity> entityType(Mode mode) {
		return switch (mode) {
			case SUN -> ModEntityTypes.UMBRELLA_SUN_PROJECTILE;
			case RAIN -> ModEntityTypes.UMBRELLA_RAIN_PROJECTILE;
			case STORM -> ModEntityTypes.UMBRELLA_STORM_PROJECTILE;
		};
	}
}
