package io.github.aspshijiu.avaritia26.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.hurtingprojectile.windcharge.WindCharge;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MaceItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;

public final class InfinityMaceItem extends MaceItem {
	public InfinityMaceItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		if (level instanceof ServerLevel serverLevel) {
			for (WindCharge charge : createWindCharges(serverLevel, player)) {
				serverLevel.addFreshEntity(charge);
			}
			player.getCooldowns().addCooldown(player.getItemInHand(hand), 20);
		}
		return InteractionResult.SUCCESS;
	}

	public static List<WindCharge> createWindCharges(ServerLevel level, Player player) {
		List<WindCharge> charges = new ArrayList<>(3);
		for (int index = 0; index < 3; index++) {
			WindCharge charge = new WindCharge(player, level, player.getX(), player.getEyeY(), player.getZ());
			charge.shoot(player.getLookAngle().x, player.getLookAngle().y, player.getLookAngle().z, 1.5F, 1.0F);
			charges.add(charge);
		}
		return charges;
	}

	@Override
	public float getAttackDamageBonus(Entity target, float damage, DamageSource source) {
		float base = super.getAttackDamageBonus(target, damage, source);
		return source.getDirectEntity() instanceof net.minecraft.world.entity.LivingEntity attacker
				&& canSmashAttack(attacker) && target instanceof net.minecraft.world.entity.LivingEntity living
				? base + living.getHealth() * 0.25F : base;
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return false;
	}

	public static ItemAttributeModifiers createAttributes() {
		return ItemAttributeModifiers.builder()
				.add(Attributes.ATTACK_DAMAGE, new AttributeModifier(
						BASE_ATTACK_DAMAGE_ID, 100.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
				.add(Attributes.ATTACK_SPEED, new AttributeModifier(
						BASE_ATTACK_SPEED_ID, 100.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
				.add(Attributes.ENTITY_INTERACTION_RANGE, new AttributeModifier(
						Identifier.withDefaultNamespace("attack_range_modifier"), 5.0,
						AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
				.build();
	}
}
