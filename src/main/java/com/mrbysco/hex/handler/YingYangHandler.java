package com.mrbysco.hex.handler;

import com.mrbysco.hex.registry.EnchantmentEffectRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.function.Predicate;

public class YingYangHandler {
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent.Post event) {
		final Player player = event.getEntity();
		final Level level = player.level();
		if (!level.isClientSide() && player != null) {
			BlockPos pos = player.blockPosition();
			if (!player.isSpectator() && level.getGameTime() % 50 == 0) {
				int lightEmission = level.getRawBrightness(pos, 0);

				repairYingItems(player, lightEmission);
				repairYangItems(player, lightEmission);
			}
		}
	}

	private void repairYingItems(Player player, int lightValue) {
		Predicate<ItemStack> stackPredicate = (stack) -> stack.isDamaged() && stack.getDamageValue() > (int) ((double) stack.getMaxDamage() / 2.0D)
				&& EnchantmentHelper.has(stack, EnchantmentEffectRegistry.YING.get());

		if (lightValue <= 7) {
			for (EquipmentSlot slot : EquipmentSlotGroup.ARMOR) {
				ItemStack stack = player.getItemBySlot(slot);
				if (stackPredicate.test(stack)) {
					stack.setDamageValue(stack.getDamageValue() - 1);
				}
			}

			for (EquipmentSlot slot : EquipmentSlotGroup.HAND) {
				ItemStack stack = player.getItemBySlot(slot);
				if (stackPredicate.test(stack)) {
					stack.setDamageValue(stack.getDamageValue() - 1);
				}
			}
		}
	}

	private void repairYangItems(Player player, int lightValue) {
		Predicate<ItemStack> stackPredicate = (stack) -> stack.isDamaged() && stack.getDamageValue() > (int) ((double) stack.getMaxDamage() / 2.0D)
				&& EnchantmentHelper.has(stack, EnchantmentEffectRegistry.YANG.get());

		if (lightValue > 7) {
			for (EquipmentSlot slot : EquipmentSlotGroup.ARMOR) {
				ItemStack stack = player.getItemBySlot(slot);
				if (stackPredicate.test(stack)) {
					stack.setDamageValue(stack.getDamageValue() - 1);
				}
			}

			for (EquipmentSlot slot : EquipmentSlotGroup.HAND) {
				ItemStack stack = player.getItemBySlot(slot);
				if (stackPredicate.test(stack)) {
					stack.setDamageValue(stack.getDamageValue() - 1);
				}
			}
		}
	}
}
