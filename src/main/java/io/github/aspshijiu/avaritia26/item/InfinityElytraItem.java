package io.github.aspshijiu.avaritia26.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	// 落地冲击只在滑翔结束后的短暂窗口内有效，避免死亡/断线/空中卸下鞘翅留下的旧条目误触发
	private static final long LANDING_GRACE_TICKS = 20;
	private static final Map<UUID, Long> ACTIVE_FLIGHT = new HashMap<>();

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
			ACTIVE_FLIGHT.put(wearer.getUUID(), level.getGameTime());
			applyFlightTick(level, wearer);
		} else if (wearer.onGround()) {
			Long lastGlideTime = ACTIVE_FLIGHT.remove(wearer.getUUID());
			if (lastGlideTime != null && level.getGameTime() - lastGlideTime <= LANDING_GRACE_TICKS) {
				applyLandingImpact(level, wearer);
			}
		}
	}

	public static void clearFlightTracking() {
		ACTIVE_FLIGHT.clear();
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
				target -> target != wearer && !(target instanceof Player)
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
