package com.mrbysco.hex.mixin;

import com.mrbysco.hex.registry.EnchantmentEffectRegistry;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinAi.class)
public class PiglinAiMixin {

	@Inject(method = "isWearingSafeArmor(Lnet/minecraft/world/entity/LivingEntity;)Z", at = @At(value = "HEAD"), cancellable = true)
	private static void hex$isWearingSafeArmor(LivingEntity livingEntity, CallbackInfoReturnable<Boolean> cir) {
		for (ItemStack stack : livingEntity.getArmorSlots()) {
			if (EnchantmentHelper.has(stack, EnchantmentEffectRegistry.GOLDEN_GLINT.get())) {
				cir.setReturnValue(true);
			}
		}
	}
}
