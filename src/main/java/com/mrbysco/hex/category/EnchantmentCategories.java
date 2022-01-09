package com.mrbysco.hex.category;

import com.mrbysco.hex.Reference;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.common.ToolActions;

public class EnchantmentCategories {
	public static final EnchantmentCategory HOE = EnchantmentCategory.create(Reference.MOD_ID + ":hoes_only", i -> i.getDefaultInstance().canPerformAction(ToolActions.HOE_DIG));
}
