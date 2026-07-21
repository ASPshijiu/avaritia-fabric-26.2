package io.github.aspshijiu.avaritia26.mixin;

import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractFurnaceBlockEntity.class)
public interface AbstractFurnaceBlockEntityAccessor {
	@Accessor("litTimeRemaining")
	int avaritia26$getLitTimeRemaining();

	@Accessor("litTotalTime")
	int avaritia26$getLitTotalTime();
}
