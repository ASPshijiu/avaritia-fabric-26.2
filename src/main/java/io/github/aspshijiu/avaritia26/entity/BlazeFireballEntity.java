package io.github.aspshijiu.avaritia26.entity;

import io.github.aspshijiu.avaritia26.registry.ModEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public final class BlazeFireballEntity extends SmallFireball implements ItemSupplier {
	public static final float DAMAGE = 50.0F;

	public BlazeFireballEntity(EntityType<? extends BlazeFireballEntity> entityType, Level level) {
		super(entityType, level);
	}

	public BlazeFireballEntity(ServerLevel level, LivingEntity owner) {
		this(ModEntityTypes.BLAZE_FIREBALL, level);
		setOwner(owner);
		setPos(owner.getX(), owner.getEyeY() + 0.1, owner.getZ());
		shootFromRotation(owner, owner.getXRot(), owner.getYRot(), 0.0F, 1.5F, 1.0F);
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		if (level() instanceof ServerLevel serverLevel) {
			applyEntityImpact(serverLevel, result.getEntity());
		}
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		if (level() instanceof ServerLevel serverLevel) {
			applyBlockImpact(serverLevel, result.getBlockPos());
		}
	}

	public void applyEntityImpact(ServerLevel level, Entity target) {
		target.setRemainingFireTicks(100);
		target.hurtServer(level, damageSources().inFire(), DAMAGE);
		discard();
	}

	public void applyBlockImpact(ServerLevel level, BlockPos pos) {
		if (level.getBlockState(pos).is(Blocks.OBSIDIAN)) {
			level.setBlockAndUpdate(pos, Blocks.LAVA.defaultBlockState());
		} else if (level.getBlockState(pos).is(Blocks.SAND)) {
			for (BlockPos current : BlockPos.betweenClosed(pos.offset(-2, -2, -2), pos.offset(2, 2, 2))) {
				if (level.getBlockState(current).is(Blocks.SAND)) {
					level.setBlockAndUpdate(current, Blocks.GLASS.defaultBlockState());
				}
			}
		} else {
			for (BlockPos current : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
				if (level.isEmptyBlock(current)) {
					level.setBlockAndUpdate(current, Blocks.FIRE.defaultBlockState());
				}
			}
		}
		discard();
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
