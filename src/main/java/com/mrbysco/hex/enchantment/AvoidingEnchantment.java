package com.mrbysco.hex.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class AvoidingEnchantment extends Enchantment {
	public AvoidingEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
		super(rarity, category, slots);
	}

	public int getMinCost(int level) {
		return level * 32;
	}

	public int getMaxCost(int level) {
		return this.getMinCost(level) + 50;
	}

	public int getMaxLevel() {
		return 1;
	}
}
