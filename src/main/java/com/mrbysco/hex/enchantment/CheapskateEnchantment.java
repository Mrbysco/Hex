package com.mrbysco.hex.enchantment;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.MendingEnchantment;

public class CheapskateEnchantment extends Enchantment {
	public CheapskateEnchantment(Enchantment.EnchantmentDefinition definition) {
		super(definition);
	}

	@Override
	protected boolean checkCompatibility(Enchantment enchantment) {
		return !(enchantment instanceof MendingEnchantment) && super.checkCompatibility(enchantment);
	}
}
