package io.github.aspshijiu.avaritia26.registry;

import io.github.aspshijiu.avaritia26.Avaritia26;
import io.github.aspshijiu.avaritia26.effect.BurningEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;

public final class ModMobEffects {
	public static final ResourceKey<MobEffect> BURNING_KEY = ResourceKey.create(
			Registries.MOB_EFFECT,
			Avaritia26.id("burning")
	);
	public static final Holder.Reference<MobEffect> BURNING = Registry.registerForHolder(
			BuiltInRegistries.MOB_EFFECT,
			BURNING_KEY,
			new BurningEffect()
	);

	private ModMobEffects() {
	}

	public static void initialize() {
	}
}
