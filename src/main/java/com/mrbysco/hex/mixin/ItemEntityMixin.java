package com.mrbysco.hex.mixin;

import com.mrbysco.hex.handler.AvoidingHandler;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {

	@Inject(method = "tick()V", locals = LocalCapture.CAPTURE_FAILEXCEPTION,
			at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;tick()V",
					ordinal = 0, shift = Shift.BEFORE))
	public void hexTick(CallbackInfo ci) {
		ItemEntity itemEntity = ((ItemEntity) (Object) this);
		if (itemEntity.getY() < (double) (itemEntity.level().getMinBuildHeight() - 64)) {
			AvoidingHandler.entityLeaveWorldEvent(itemEntity);
		}
	}
}
