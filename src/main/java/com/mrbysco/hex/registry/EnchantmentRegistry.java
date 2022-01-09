package com.mrbysco.hex.registry;

import com.mrbysco.hex.Reference;
import com.mrbysco.hex.category.EnchantmentCategories;
import com.mrbysco.hex.enchantment.AffectionEnchantment;
import com.mrbysco.hex.enchantment.CultivactionEnchantment;
import com.mrbysco.hex.enchantment.YieldingEnchantment;
import com.mrbysco.hex.enchantment.YingYangEnchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantment.Rarity;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EnchantmentRegistry {
	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Reference.MOD_ID);

	public static final RegistryObject<Enchantment> CULTIVATION = ENCHANTMENTS.register("cultivation", () -> new CultivactionEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategories.HOE, EquipmentSlot.MAINHAND));
	public static final RegistryObject<Enchantment> YIELDING = ENCHANTMENTS.register("yielding", () -> new YieldingEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategories.HOE, EquipmentSlot.MAINHAND));
	public static final RegistryObject<Enchantment> AFFECTION = ENCHANTMENTS.register("affection", () -> new AffectionEnchantment(Rarity.VERY_RARE, EnchantmentCategory.VANISHABLE, EquipmentSlot.values()));

	public static final RegistryObject<Enchantment> YING = ENCHANTMENTS.register("ying", () -> new YingYangEnchantment(Rarity.RARE, EnchantmentCategory.VANISHABLE, EquipmentSlot.values()));
	public static final RegistryObject<Enchantment> YANG = ENCHANTMENTS.register("yang", () -> new YingYangEnchantment(Rarity.RARE, EnchantmentCategory.VANISHABLE, EquipmentSlot.values()));
}
