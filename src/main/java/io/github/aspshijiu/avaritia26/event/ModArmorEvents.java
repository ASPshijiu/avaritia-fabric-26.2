package io.github.aspshijiu.avaritia26.event;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import io.github.aspshijiu.avaritia26.registry.ModItems;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public final class ModArmorEvents {
	private static final Set<UUID> FLIGHT_GRANTED = new HashSet<>();

	private ModArmorEvents() {
	}

	public static void initialize() {
		ServerTickEvents.END_SERVER_TICK.register(server -> server.getPlayerList().getPlayers().forEach(ModArmorEvents::tickPlayer));
		ServerLivingEntityEvents.ALLOW_DAMAGE.register(ModArmorEvents::allowDamage);
		ServerLivingEntityEvents.ALLOW_DEATH.register(ModArmorEvents::allowDeath);
		ServerLifecycleEvents.SERVER_STOPPED.register(server -> FLIGHT_GRANTED.clear());
	}

	public static void tickPlayer(ServerPlayer player) {
		if (player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.INFINITY_HELMET)) {
			player.setAirSupply(300);
			player.getFoodData().setFoodLevel(20);
			player.getFoodData().setSaturation(20.0F);
			MobEffectInstance nightVision = new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 0, false, false);
			if (player.connection == null) {
				player.getActiveEffectsMap().put(MobEffects.NIGHT_VISION, nightVision);
			} else {
				player.addEffect(nightVision);
			}
		}

		if (player.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.INFINITY_CHESTPLATE)) {
			grantFlight(player);
			List.copyOf(player.getActiveEffects()).stream()
					.filter(effect -> !effect.getEffect().value().isBeneficial())
					.forEach(effect -> removeEffect(player, effect));
		} else {
			revokeFlight(player);
		}

		if (player.getItemBySlot(EquipmentSlot.LEGS).is(ModItems.INFINITY_PANTS)) {
			player.clearFire();
		}
	}

	public static boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {
		return !hasFullSet(entity);
	}

	public static boolean allowDeath(LivingEntity entity, DamageSource source, float amount) {
		if (!hasFullSet(entity)) {
			return true;
		}
		entity.setHealth(entity.getMaxHealth());
		return false;
	}

	public static boolean hasFullSet(LivingEntity entity) {
		return entity.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.INFINITY_HELMET)
				&& entity.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.INFINITY_CHESTPLATE)
				&& entity.getItemBySlot(EquipmentSlot.LEGS).is(ModItems.INFINITY_PANTS)
				&& entity.getItemBySlot(EquipmentSlot.FEET).is(ModItems.INFINITY_BOOTS);
	}

	private static void grantFlight(ServerPlayer player) {
		if (!player.getAbilities().mayfly) {
			player.getAbilities().mayfly = true;
			FLIGHT_GRANTED.add(player.getUUID());
			syncAbilities(player);
		}
	}

	private static void revokeFlight(ServerPlayer player) {
		if (!FLIGHT_GRANTED.contains(player.getUUID()) || player.isCreative() || player.isSpectator()) {
			return;
		}
		FLIGHT_GRANTED.remove(player.getUUID());
		player.getAbilities().mayfly = false;
		player.getAbilities().flying = false;
		syncAbilities(player);
	}

	private static void removeEffect(ServerPlayer player, MobEffectInstance effect) {
		if (player.connection == null) {
			player.removeEffectNoUpdate(effect.getEffect());
		} else {
			player.removeEffect(effect.getEffect());
		}
	}

	private static void syncAbilities(ServerPlayer player) {
		if (player.connection != null) {
			player.onUpdateAbilities();
		}
	}
}
