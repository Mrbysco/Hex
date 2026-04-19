package com.mrbysco.hex.mixin;

import com.mrbysco.hex.registry.EnchantmentEffectRegistry;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RepairItemRecipe;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RepairItemRecipe.class)
public class RepairItemRecipeMixin {

	@Inject(method = "matches(Lnet/minecraft/world/item/crafting/CraftingInput;Lnet/minecraft/world/level/Level;)Z", at = @At(value = "HEAD"), cancellable = true)
	public void hex$matches(CraftingInput input, Level level, CallbackInfoReturnable<Boolean> cir) {
		for (int i = 0; i < input.size(); ++i) {
			ItemStack stack = input.getItem(i);
			if (EnchantmentHelper.has(stack, EnchantmentEffectRegistry.NON_COMBINING.get())) {
				cir.setReturnValue(false);
			}
		}
	}
}
