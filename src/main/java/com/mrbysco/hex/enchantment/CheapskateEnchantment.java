package com.mrbysco.hex.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.MendingEnchantment;

public class CheapskateEnchantment extends Enchantment {
	public CheapskateEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
		super(rarity, category, slots);
	}

	public int getMinCost(int level) {
		return 5 + level * 7;
	}

	public int getMaxCost(int level) {
		return 50;
	}

	public int getMaxLevel() {
		return 3;
	}

	@Override
	protected boolean checkCompatibility(Enchantment enchantment) {
		return !(enchantment instanceof MendingEnchantment) && super.checkCompatibility(enchantment);
	}
}
