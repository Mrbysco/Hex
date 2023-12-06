package com.mrbysco.hex.category;

import com.mrbysco.hex.Hex;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.neoforged.neoforge.common.ToolActions;

public class EnchantmentCategories {
	public static final EnchantmentCategory HOE = EnchantmentCategory.create(Hex.MOD_ID + ":hoes_only", i -> i.getDefaultInstance().canPerformAction(ToolActions.HOE_DIG));
	public static final EnchantmentCategory REPAIRABLE = EnchantmentCategory.create(Hex.MOD_ID + ":repairable", i -> new ItemStack(i).isRepairable());
}
