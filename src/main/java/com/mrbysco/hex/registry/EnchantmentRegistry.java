package com.mrbysco.hex.registry;

import com.mrbysco.hex.Hex;
import com.mrbysco.hex.category.EnchantmentCategories;
import com.mrbysco.hex.enchantment.AffectionEnchantment;
import com.mrbysco.hex.enchantment.AvoidingEnchantment;
import com.mrbysco.hex.enchantment.CheapskateEnchantment;
import com.mrbysco.hex.enchantment.CultivationEnchantment;
import com.mrbysco.hex.enchantment.GoldenGlintEnchantment;
import com.mrbysco.hex.enchantment.NoncombiningEnchantment;
import com.mrbysco.hex.enchantment.YieldingEnchantment;
import com.mrbysco.hex.enchantment.YingYangEnchantment;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantment.Rarity;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EnchantmentRegistry {
	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(Registries.ENCHANTMENT, Hex.MOD_ID);

	private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

	public static final Supplier<Enchantment> CULTIVATION = ENCHANTMENTS.register("cultivation", () -> new CultivationEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategories.HOE, EquipmentSlot.MAINHAND));
	public static final Supplier<Enchantment> YIELDING = ENCHANTMENTS.register("yielding", () -> new YieldingEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategories.HOE, EquipmentSlot.MAINHAND));
	public static final Supplier<Enchantment> AFFECTION = ENCHANTMENTS.register("affection", () -> new AffectionEnchantment(Rarity.VERY_RARE, EnchantmentCategory.VANISHABLE, EquipmentSlot.values()));

	public static final Supplier<Enchantment> YING = ENCHANTMENTS.register("ying", () -> new YingYangEnchantment(Rarity.RARE, EnchantmentCategory.VANISHABLE, EquipmentSlot.values()));
	public static final Supplier<Enchantment> YANG = ENCHANTMENTS.register("yang", () -> new YingYangEnchantment(Rarity.RARE, EnchantmentCategory.VANISHABLE, EquipmentSlot.values()));

	public static final Supplier<Enchantment> GOLDEN_GLINT = ENCHANTMENTS.register("golden_glint", () -> new GoldenGlintEnchantment(Rarity.VERY_RARE, EnchantmentCategory.ARMOR, ARMOR_SLOTS));
	public static final Supplier<Enchantment> AVOIDING = ENCHANTMENTS.register("avoiding", () -> new AvoidingEnchantment(Rarity.RARE, EnchantmentCategory.VANISHABLE, EquipmentSlot.values()));

	public static final Supplier<Enchantment> CHEAPSKATE = ENCHANTMENTS.register("cheapskate", () -> new CheapskateEnchantment(Rarity.UNCOMMON, EnchantmentCategories.REPAIRABLE, EquipmentSlot.values()));

	public static final Supplier<Enchantment> NONCOMBINING = ENCHANTMENTS.register("noncombining", () -> new NoncombiningEnchantment(Rarity.VERY_RARE, EnchantmentCategories.REPAIRABLE, EquipmentSlot.values()));
}
