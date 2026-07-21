package io.github.aspshijiu.avaritia26.mixin;

import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity$1")
abstract class AbstractFurnaceContainerDataMixin {
	@Shadow
	@Final
	private AbstractFurnaceBlockEntity this$0;

	@Inject(method = "get", at = @At("HEAD"), cancellable = true)
	private void avaritia26$scaleLongFuelTime(int index, CallbackInfoReturnable<Integer> callbackInfo) {
		if (index != 0 && index != 1) {
			return;
		}
		AbstractFurnaceBlockEntityAccessor furnace = (AbstractFurnaceBlockEntityAccessor) this$0;
		int total = furnace.avaritia26$getLitTotalTime();
		if (total <= Short.MAX_VALUE) {
			return;
		}
		if (index == 1) {
			callbackInfo.setReturnValue((int) Short.MAX_VALUE);
			return;
		}
		int remaining = furnace.avaritia26$getLitTimeRemaining();
		int scaled = (int) ((long) remaining * Short.MAX_VALUE / total);
		callbackInfo.setReturnValue(remaining > 0 ? Math.max(1, scaled) : 0);
	}
}
