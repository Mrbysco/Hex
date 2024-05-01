package com.mrbysco.hex.registry;

import com.mrbysco.hex.Hex;
import com.mrbysco.hex.enchantment.AffectionEnchantment;
import com.mrbysco.hex.enchantment.CheapskateEnchantment;
import com.mrbysco.hex.enchantment.NoncombiningEnchantment;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EnchantmentRegistry {
	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(Registries.ENCHANTMENT, Hex.MOD_ID);

	private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

	public static final Supplier<Enchantment> CULTIVATION = ENCHANTMENTS.register(
			"cultivation",
			() -> new Enchantment(
					Enchantment.definition(ItemTags.HOES, 5, 3,
							Enchantment.dynamicCost(12, 7),
							Enchantment.constantCost(50), 2, EquipmentSlot.MAINHAND)
			)
	);

	public static final Supplier<Enchantment> YIELDING = ENCHANTMENTS.register(
			"yielding",
			() -> new Enchantment(
					Enchantment.definition(ItemTags.HOES, 5, 4,
							Enchantment.dynamicCost(12, 7),
							Enchantment.constantCost(50), 2, EquipmentSlot.MAINHAND)
			)
	);

	public static final Supplier<Enchantment> AFFECTION = ENCHANTMENTS.register(
			"affection",
			() -> new AffectionEnchantment(
					Enchantment.definition(ItemTags.VANISHING_ENCHANTABLE, 1, 1,
							Enchantment.constantCost(25),
							Enchantment.constantCost(50), 2, EquipmentSlot.values())
			)
	);

	public static final Supplier<Enchantment> YING = ENCHANTMENTS.register(
			"ying",
			() -> new Enchantment(
					Enchantment.definition(ItemTags.VANISHING_ENCHANTABLE, 2, 1,
							Enchantment.constantCost(30),
							Enchantment.constantCost(80), 2, EquipmentSlot.values())
			)
	);
	public static final Supplier<Enchantment> YANG = ENCHANTMENTS.register(
			"yang",
			() -> new Enchantment(
					Enchantment.definition(ItemTags.VANISHING_ENCHANTABLE, 2, 1,
							Enchantment.constantCost(30),
							Enchantment.constantCost(80), 2, EquipmentSlot.values())
			)
	);

	public static final Supplier<Enchantment> GOLDEN_GLINT = ENCHANTMENTS.register(
			"golden_glint",
			() -> new Enchantment(
					Enchantment.definition(ItemTags.VANISHING_ENCHANTABLE, 1, 1,
							Enchantment.constantCost(25),
							Enchantment.constantCost(75), 2, ARMOR_SLOTS)
			)
	);
	public static final Supplier<Enchantment> AVOIDING = ENCHANTMENTS.register(
			"avoiding",
			() -> new Enchantment(
					Enchantment.definition(ItemTags.VANISHING_ENCHANTABLE, 2, 1,
							Enchantment.constantCost(32),
							Enchantment.constantCost(82), 2, EquipmentSlot.values())
			)
	);

	public static final Supplier<Enchantment> CHEAPSKATE = ENCHANTMENTS.register(
			"cheapskate",
			() -> new CheapskateEnchantment(
					Enchantment.definition(ItemTags.DURABILITY_ENCHANTABLE, 5, 1,
							Enchantment.constantCost(10),
							Enchantment.constantCost(15), 2, EquipmentSlot.values())
			)
	);
	public static final Supplier<Enchantment> NONCOMBINING = ENCHANTMENTS.register(
			"noncombining",
			() -> new NoncombiningEnchantment(
					Enchantment.definition(ItemTags.DURABILITY_ENCHANTABLE, 1, 1,
							Enchantment.constantCost(25),
							Enchantment.constantCost(50), 1, EquipmentSlot.values())
			)
	);
}
