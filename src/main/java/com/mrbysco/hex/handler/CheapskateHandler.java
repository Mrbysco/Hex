package com.mrbysco.hex.handler;

import com.mrbysco.hex.registry.EnchantmentRegistry;
import com.mrbysco.hex.util.EnchantmentUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CheapskateHandler {
	@SubscribeEvent
	public void onRepairEvent(AnvilUpdateEvent event) {
		ItemStack input = event.getLeft();
		if(EnchantmentUtil.hasEnchantment(EnchantmentRegistry.CHEAPSKATE.get(), input)) {
			ItemStack rightStack = event.getRight();
			if(input.isDamageableItem() && input.getItem().isValidRepairItem(input, rightStack)) {
				int cost = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegistry.CHEAPSKATE.get(), input);
				switch (cost) {
					default -> input.setRepairCost(30);
					case 2 -> input.setRepairCost(20);
					case 3 -> input.setRepairCost(10);
				}
			}
		}
	}
}
