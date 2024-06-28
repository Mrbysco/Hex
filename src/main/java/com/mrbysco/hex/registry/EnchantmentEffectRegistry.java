package com.mrbysco.hex.registry;

import com.mrbysco.hex.Hex;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.Unit;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class EnchantmentEffectRegistry {
	public static final DeferredRegister<DataComponentType<?>> ENCHANTMENT_EFFECT_REGISTER = DeferredRegister.create(BuiltInRegistries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, Hex.MOD_ID);

	public static final DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> AFFECTION = register("affection", builder -> builder.persistent(Unit.CODEC));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> AVOIDING = register("avoiding", builder -> builder.persistent(Unit.CODEC));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> NON_COMBINING = register("non_combining", builder -> builder.persistent(Unit.CODEC));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> GOLDEN_GLINT = register("golden_glint", builder -> builder.persistent(Unit.CODEC));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> YING = register("ying", builder -> builder.persistent(Unit.CODEC));
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> YANG = register("yang", builder -> builder.persistent(Unit.CODEC));

	private static <T> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String path, UnaryOperator<DataComponentType.Builder<T>> pOperator) {
		return ENCHANTMENT_EFFECT_REGISTER.register(path, () -> pOperator.apply(DataComponentType.builder()).build());
	}
}
