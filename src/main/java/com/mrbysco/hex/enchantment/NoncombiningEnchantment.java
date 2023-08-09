package com.mrbysco.hex.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class NoncombiningEnchantment extends Enchantment {
	public NoncombiningEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
		super(rarity, category, slots);
	}

	public int getMinCost(int level) {
		return 25;
	}

	public int getMaxCost(int level) {
		return 50;
	}

	public int getMaxLevel() {
		return 1;
	}

	public boolean isTreasureOnly() {
		return true;
	}

	public boolean isCurse() {
		return true;
	}
}
