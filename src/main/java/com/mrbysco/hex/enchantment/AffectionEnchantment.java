package com.mrbysco.hex.enchantment;

import net.minecraft.world.item.enchantment.Enchantment;

public class AffectionEnchantment extends Enchantment {
	public AffectionEnchantment(Enchantment.EnchantmentDefinition definition) {
		super(definition);
	}

	public boolean isTreasureOnly() {
		return true;
	}

	public boolean isCurse() {
		return true;
	}
}
