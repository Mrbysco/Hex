package com.mrbysco.hex.enchantment;

import com.mrbysco.hex.config.HexConfig;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class CultivactionEnchantment extends Enchantment {
	public CultivactionEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... slots) {
		super(rarity, category, slots);
	}

	public int getMinCost(int level) {
		return 5 + level * 7;
	}

	public int getMaxCost(int level) {
		return 50;
	}

	public int getMaxLevel() {
		return HexConfig.COMMON.maxCultivationLevel.get();
	}
}
