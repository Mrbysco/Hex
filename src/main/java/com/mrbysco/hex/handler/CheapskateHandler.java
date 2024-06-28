package com.mrbysco.hex.handler;

import com.mrbysco.hex.registry.EnchantmentRegistry;
import com.mrbysco.hex.util.EnchantmentUtil;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.AnvilUpdateEvent;

public class CheapskateHandler {
	@SubscribeEvent
	public void onRepairEvent(AnvilUpdateEvent event) {
		final ItemStack input = event.getLeft();
		final Player player = event.getPlayer();
		if(player == null) return;
		final Level level = player.level();
		int cheapskateLevel = input.getEnchantmentLevel(EnchantmentUtil.unwrap(level, EnchantmentRegistry.CHEAPSKATE));
		if (cheapskateLevel > 0) {
			ItemStack rightStack = event.getRight();
			if (input.isDamageableItem() && input.getItem().isValidRepairItem(input, rightStack)) {
				switch (cheapskateLevel) {
					default -> input.set(DataComponents.REPAIR_COST, 30);
					case 2 -> input.set(DataComponents.REPAIR_COST, 20);
					case 3 -> input.set(DataComponents.REPAIR_COST, 10);
				}
			}
		}
	}
}
