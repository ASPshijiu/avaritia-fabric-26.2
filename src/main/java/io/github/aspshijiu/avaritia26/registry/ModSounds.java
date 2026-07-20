package io.github.aspshijiu.avaritia26.registry;

import io.github.aspshijiu.avaritia26.Avaritia26;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;

public final class ModSounds {
	public static final SoundEvent GAPING_VOID = Registry.register(
			BuiltInRegistries.SOUND_EVENT,
			Avaritia26.id("gaping_void"),
			SoundEvent.createVariableRangeEvent(Avaritia26.id("gaping_void"))
	);

	private ModSounds() {
	}

	public static void initialize() {
	}
}
