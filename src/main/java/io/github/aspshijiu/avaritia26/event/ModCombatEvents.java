package io.github.aspshijiu.avaritia26.event;

import java.util.List;

import io.github.aspshijiu.avaritia26.registry.ModItems;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public final class ModCombatEvents {
	private ModCombatEvents() {
	}

	public static void initialize() {
		ServerLivingEntityEvents.AFTER_DEATH.register(ModCombatEvents::dropWitherSkeletonSkull);
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
