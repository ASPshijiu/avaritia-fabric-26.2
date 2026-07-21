package io.github.aspshijiu.avaritia26.event;

import java.util.List;

import io.github.aspshijiu.avaritia26.item.InfinitySwordItem;
import io.github.aspshijiu.avaritia26.registry.ModItems;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public final class ModCombatEvents {
	private ModCombatEvents() {
	}

	public static void initialize() {
		ServerLivingEntityEvents.AFTER_DEATH.register(ModCombatEvents::dropWitherSkeletonSkull);
		ServerLivingEntityEvents.ALLOW_DEATH.register(ModCombatEvents::useInfinityTotem);
		AttackEntityCallback.EVENT.register((player, level, hand, entity, hitResult) -> {
			if (player.getItemInHand(hand).is(ModItems.CRYSTAL_SWORD)
					|| player.getItemInHand(hand).is(ModItems.CRYSTAL_HOE)
					|| player.getItemInHand(hand).is(ModItems.CRYSTAL_SHOVEL)) {
				player.resetAttackStrengthTicker();
				entity.setInvulnerable(false);
				return InteractionResult.PASS;
			}
			if (!(level instanceof ServerLevel serverLevel)
					|| !(entity instanceof LivingEntity target)
					|| !player.getItemInHand(hand).is(ModItems.INFINITY_SWORD)) {
				return InteractionResult.PASS;
			}
			InfinitySwordItem.kill(target, player, serverLevel);
			return InteractionResult.SUCCESS_SERVER;
		});
	}

	private static boolean useInfinityTotem(LivingEntity entity, DamageSource source, float amount) {
		if (!(entity instanceof ServerPlayer player) || ModArmorEvents.hasFullSet(player)) {
			return true;
		}
		ItemStack totem = findInfinityTotem(player);
		if (totem.isEmpty()) {
			return true;
		}

		if (player.connection == null) {
			player.getActiveEffectsMap().clear();
		} else {
			player.removeAllEffects();
		}
		player.setHealth(player.getMaxHealth());
		if (totem.getDamageValue() % 10 == 0) {
			ServerLevel level = (ServerLevel) player.level();
			addEffect(player, new MobEffectInstance(MobEffects.JUMP_BOOST, 800, 1));
			addEffect(player, new MobEffectInstance(MobEffects.SPEED, 800, 1));
			level.getEntitiesOfClass(
					LivingEntity.class,
					player.getBoundingBox().inflate(8.0),
					target -> target != player && target instanceof Enemy
			).forEach(target -> target.hurtServer(
					level,
					player.damageSources().playerAttack(player),
					1000.0F
			));
		}
		addEffect(player, new MobEffectInstance(MobEffects.REGENERATION, 2600, 4));
		addEffect(player, new MobEffectInstance(MobEffects.RESISTANCE, 400, 1));
		addEffect(player, new MobEffectInstance(MobEffects.ABSORPTION, 700, 2));
		addEffect(player, new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1100, 0));
		player.level().broadcastEntityEvent(player, EntityEvent.PROTECTED_FROM_DEATH);

		int damage = totem.getDamageValue() + 1;
		if (damage >= totem.getMaxDamage()) {
			totem.shrink(1);
		} else {
			totem.setDamageValue(damage);
		}
		return false;
	}

	public static ItemStack findInfinityTotem(Player player) {
		for (ItemStack stack : player.getInventory().getNonEquipmentItems()) {
			if (stack.is(ModItems.INFINITY_TOTEM)) {
				return stack;
			}
		}
		return player.getOffhandItem().is(ModItems.INFINITY_TOTEM) ? player.getOffhandItem() : ItemStack.EMPTY;
	}

	private static void addEffect(ServerPlayer player, MobEffectInstance effect) {
		if (player.connection == null) {
			player.getActiveEffectsMap().put(effect.getEffect(), effect);
		} else {
			player.addEffect(effect);
		}
	}

	private static void dropWitherSkeletonSkull(LivingEntity deadEntity, DamageSource source) {
		if (!(deadEntity instanceof AbstractSkeleton)
				|| !(source.getEntity() instanceof Player player)
				|| !player.getMainHandItem().is(ModItems.SKULL_FIRE_SWORD)
				|| !(deadEntity.level() instanceof ServerLevel level)) {
			return;
		}

		List<ItemEntity> skullDrops = level.getEntitiesOfClass(
				ItemEntity.class,
				deadEntity.getBoundingBox().inflate(1.0),
				itemEntity -> itemEntity.getAge() == 0
						&& (itemEntity.getItem().is(Items.SKELETON_SKULL)
						|| itemEntity.getItem().is(Items.WITHER_SKELETON_SKULL))
		);
		if (skullDrops.isEmpty()) {
			deadEntity.spawnAtLocation(level, new ItemStack(Items.WITHER_SKELETON_SKULL));
			return;
		}

		for (ItemEntity skullDrop : skullDrops) {
			if (skullDrop.getItem().is(Items.SKELETON_SKULL)) {
				skullDrop.setItem(new ItemStack(Items.WITHER_SKELETON_SKULL, skullDrop.getItem().getCount()));
			}
		}
	}
}
