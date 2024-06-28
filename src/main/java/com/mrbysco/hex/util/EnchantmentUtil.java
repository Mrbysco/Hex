package com.mrbysco.hex.util;

import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class EnchantmentUtil {
	public static <T> Holder<T> unwrap(Level level, ResourceKey<T> key){
		return level.registryAccess().registryOrThrow(key.registryKey()).getHolderOrThrow(key);
	}

	public static <T> Holder<T> unwrap(BlockEntity entity, ResourceKey<T> key){
		return entity.getLevel().registryAccess().registryOrThrow(key.registryKey()).getHolderOrThrow(key);
	}
}
