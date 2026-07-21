package io.github.aspshijiu.avaritia26.mixin;

import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFurnaceBlockEntity.class)
abstract class AbstractFurnaceBlockEntityMixin {
	@Shadow
	private int litTimeRemaining;

	@Shadow
	private int litTotalTime;

	@Inject(method = "loadAdditional", at = @At("TAIL"))
	private void avaritia26$loadLongFuelTime(ValueInput input, CallbackInfo callbackInfo) {
		litTimeRemaining = input.getIntOr("lit_time_remaining", 0);
		litTotalTime = input.getIntOr("lit_total_time", 0);
	}

	@Inject(method = "saveAdditional", at = @At("TAIL"))
	private void avaritia26$saveLongFuelTime(ValueOutput output, CallbackInfo callbackInfo) {
		if (litTimeRemaining > Short.MAX_VALUE || litTotalTime > Short.MAX_VALUE) {
			output.putInt("lit_time_remaining", litTimeRemaining);
			output.putInt("lit_total_time", litTotalTime);
		}
	}
}
