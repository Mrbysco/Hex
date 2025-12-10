package com.mrbysco.hex.registry;

import com.mrbysco.hex.Hex;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

public class EnchantmentRegistry {
	public static final ResourceKey<Enchantment> CULTIVATION = key("cultivation");
	public static final ResourceKey<Enchantment> YIELDING = key("yielding");
	public static final ResourceKey<Enchantment> AFFECTION = key("affection");
	public static final ResourceKey<Enchantment> YING = key("ying");
	public static final ResourceKey<Enchantment> YANG = key("yang");
	public static final ResourceKey<Enchantment> GOLDEN_GLINT = key("golden_glint");
	public static final ResourceKey<Enchantment> AVOIDING = key("avoiding");
	public static final ResourceKey<Enchantment> CHEAPSKATE = key("cheapskate");
	public static final ResourceKey<Enchantment> NONCOMBINING = key("noncombining");

	public static void bootstrap(BootstrapContext<Enchantment> context) {
		HolderGetter<Item> itemHolderGetter = context.lookup(Registries.ITEM);

		register(
				context,
				CULTIVATION,
				Enchantment.enchantment(
						Enchantment.definition(
								itemHolderGetter.getOrThrow(ItemTags.HOES),
								5, 3,
								Enchantment.dynamicCost(12, 7),
								Enchantment.constantCost(50), 2, EquipmentSlotGroup.MAINHAND)
				)
		);

		register(
				context,
				YIELDING,
				Enchantment.enchantment(
						Enchantment.definition(itemHolderGetter.getOrThrow(ItemTags.HOES),
								5, 4,
								Enchantment.dynamicCost(12, 7),
								Enchantment.constantCost(50), 2, EquipmentSlotGroup.MAINHAND)
				)
		);

		register(
				context,
				AFFECTION,
				Enchantment.enchantment(
						Enchantment.definition(itemHolderGetter.getOrThrow(ItemTags.VANISHING_ENCHANTABLE),
								1, 1,
								Enchantment.constantCost(25),
								Enchantment.constantCost(50), 2, EquipmentSlotGroup.ANY)
				).withEffect(EnchantmentEffectRegistry.AFFECTION.get())
		);

		register(
				context,
				YING,
				Enchantment.enchantment(
						Enchantment.definition(itemHolderGetter.getOrThrow(ItemTags.VANISHING_ENCHANTABLE),
								2, 1,
								Enchantment.constantCost(30),
								Enchantment.constantCost(80), 2, EquipmentSlotGroup.ANY)
				).withEffect(EnchantmentEffectRegistry.YING.get())
		);

		register(
				context,
				YANG,
				Enchantment.enchantment(
						Enchantment.definition(itemHolderGetter.getOrThrow(ItemTags.VANISHING_ENCHANTABLE),
								2, 1,
								Enchantment.constantCost(30),
								Enchantment.constantCost(80), 2, EquipmentSlotGroup.ANY)
				).withEffect(EnchantmentEffectRegistry.YANG.get())
		);

		register(
				context,
				GOLDEN_GLINT,
				Enchantment.enchantment(
						Enchantment.definition(itemHolderGetter.getOrThrow(ItemTags.VANISHING_ENCHANTABLE),
								1, 1,
								Enchantment.constantCost(25),
								Enchantment.constantCost(75), 2, EquipmentSlotGroup.ARMOR)
				).withEffect(EnchantmentEffectRegistry.GOLDEN_GLINT.get())
		);

		register(
				context,
				AVOIDING,
				Enchantment.enchantment(
						Enchantment.definition(itemHolderGetter.getOrThrow(ItemTags.VANISHING_ENCHANTABLE),
								2, 1,
								Enchantment.constantCost(32),
								Enchantment.constantCost(82), 2, EquipmentSlotGroup.ANY)
				).withEffect(EnchantmentEffectRegistry.AVOIDING.get())
		);

		register(
				context,
				CHEAPSKATE,
				Enchantment.enchantment(
						Enchantment.definition(itemHolderGetter.getOrThrow(ItemTags.DURABILITY_ENCHANTABLE),
								5, 1,
								Enchantment.constantCost(10),
								Enchantment.constantCost(15), 2, EquipmentSlotGroup.ANY)
				)
		);

		register(
				context,
				NONCOMBINING,
				Enchantment.enchantment(
						Enchantment.definition(itemHolderGetter.getOrThrow(ItemTags.DURABILITY_ENCHANTABLE),
								1, 1,
								Enchantment.constantCost(25),
								Enchantment.constantCost(50), 1, EquipmentSlotGroup.ANY)
				).withEffect(EnchantmentEffectRegistry.NON_COMBINING.get())
		);
	}

	private static ResourceKey<Enchantment> key(String path) {
		return ResourceKey.create(Registries.ENCHANTMENT, Identifier.fromNamespaceAndPath(Hex.MOD_ID, path));
	}

	private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> resourceKey,
	                             Enchantment.Builder builder) {
		context.register(resourceKey, builder.build(resourceKey.identifier()));
	}
}
