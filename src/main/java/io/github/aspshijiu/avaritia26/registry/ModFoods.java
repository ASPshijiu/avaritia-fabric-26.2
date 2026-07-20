package io.github.aspshijiu.avaritia26.registry;

import java.util.List;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public final class ModFoods {
	public static final FoodProperties ULTIMATE_STEW = new FoodProperties.Builder()
			.nutrition(20)
			.saturationModifier(20.0F)
			.alwaysEdible()
			.build();
	public static final Consumable ULTIMATE_STEW_CONSUMABLE = Consumables.defaultFood()
			.consumeSeconds(0.8F)
			.onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
					new MobEffectInstance(MobEffects.STRENGTH, 5 * 60 * 20, 4),
					new MobEffectInstance(MobEffects.HASTE, 3 * 60 * 20, 2),
					new MobEffectInstance(MobEffects.SPEED, 3 * 60 * 20, 2),
					new MobEffectInstance(MobEffects.JUMP_BOOST, 3 * 60 * 20, 2)
			)))
			.build();
	public static final FoodProperties COSMIC_MEATBALLS = new FoodProperties.Builder()
			.nutrition(20)
			.saturationModifier(20.0F)
			.alwaysEdible()
			.build();
	public static final Consumable COSMIC_MEATBALLS_CONSUMABLE = Consumables.defaultFood()
			.onConsume(new ApplyStatusEffectsConsumeEffect(List.of(
					new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 5 * 60 * 20, 0),
					new MobEffectInstance(MobEffects.RESISTANCE, 1 * 60 * 20, 1),
					new MobEffectInstance(MobEffects.ABSORPTION, 3 * 60 * 20, 2),
					new MobEffectInstance(MobEffects.NIGHT_VISION, 3 * 60 * 20, 0),
					new MobEffectInstance(MobEffects.WATER_BREATHING, 2 * 60 * 20, 2),
					new MobEffectInstance(MobEffects.REGENERATION, 5 * 60 * 20, 4)
			)))
			.build();

	private ModFoods() {
	}
}
