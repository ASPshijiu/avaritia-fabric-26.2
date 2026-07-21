package io.github.aspshijiu.avaritia26.item;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.Vec3;

public final class InfinityElytraItem extends Item {
	public static final double FLIGHT_SPEED = 1.5;
	public static final double COLLISION_RANGE = 3.0;
	public static final float COLLISION_DAMAGE = 100.0F;
	public static final double LANDING_RANGE = 2.5;
	public static final float LANDING_DAMAGE = 6.0F;
	private static final Set<UUID> ACTIVE_FLIGHT = new HashSet<>();

	public InfinityElytraItem(Properties properties) {
		super(properties);
	}

	@Override
	public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, EquipmentSlot slot) {
		if (slot != EquipmentSlot.CHEST || !(entity instanceof LivingEntity wearer)
				|| !wearer.getItemBySlot(EquipmentSlot.CHEST).is(this)) {
			return;
		}

		if (wearer.isFallFlying()) {
			ACTIVE_FLIGHT.add(wearer.getUUID());
			applyFlightTick(level, wearer);
		} else if (wearer.onGround() && ACTIVE_FLIGHT.remove(wearer.getUUID())) {
			applyLandingImpact(level, wearer);
		}
	}

	public static int applyFlightTick(ServerLevel level, LivingEntity wearer) {
		Vec3 look = wearer.getLookAngle().normalize();
		wearer.setDeltaMovement(look.scale(FLIGHT_SPEED));
		wearer.hurtMarked = true;

		List<LivingEntity> targets = level.getEntities(
				EntityTypeTest.forClass(LivingEntity.class),
				wearer.getBoundingBox().inflate(COLLISION_RANGE),
				target -> target != wearer && !(target instanceof Player)
		);
		for (LivingEntity target : targets) {
			target.hurt(wearer.damageSources().flyIntoWall(), COLLISION_DAMAGE);
			target.push(target.getX() - wearer.getX(), 0.5, target.getZ() - wearer.getZ());
		}

		createTrailParticles(level, wearer, look);
		return targets.size();
	}

	public static int applyLandingImpact(ServerLevel level, LivingEntity wearer) {
		List<LivingEntity> targets = level.getEntities(
				EntityTypeTest.forClass(LivingEntity.class),
				wearer.getBoundingBox().inflate(LANDING_RANGE),
				target -> target != wearer
		);
		targets.forEach(target -> target.hurt(wearer.damageSources().fellOutOfWorld(), LANDING_DAMAGE));
		return targets.size();
	}

	private static void createTrailParticles(ServerLevel level, LivingEntity wearer, Vec3 look) {
		RandomSource random = level.getRandom();
		for (int index = 0; index < 3; index++) {
			double offsetX = random.nextGaussian() * 0.1;
			double offsetY = random.nextGaussian() * 0.1;
			double offsetZ = random.nextGaussian() * 0.1;
			level.sendParticles(
					ParticleTypes.CLOUD,
					wearer.getX() - look.x * 1.5 + offsetX,
					wearer.getY() + wearer.getBbHeight() / 2.0 + offsetY,
					wearer.getZ() - look.z * 1.5 + offsetZ,
					1,
					offsetX * 0.5,
					offsetY * 0.5,
					offsetZ * 0.5,
					0.0
			);
		}
	}
}
