package com.mrbysco.hex.handler;

import com.mrbysco.hex.registry.EnchantmentRegistry;
import com.mrbysco.hex.util.EnchantmentUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.function.Predicate;

public class YingYangHandler {
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event) {
		final Player player = event.player;
		if (event.phase == Phase.END && event.side.isServer() && player != null) {
			Level level = player.level;
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
				&& EnchantmentUtil.hasEnchantment(EnchantmentRegistry.YING.get(), stack);

		if (lightValue <= 7) {
			for (ItemStack stack : player.getArmorSlots()) {
				if (stackPredicate.test(stack)) {
					stack.setDamageValue(stack.getDamageValue() - 1);
				}
			}

			for (ItemStack stack : player.getHandSlots()) {
				if (stackPredicate.test(stack)) {
					stack.setDamageValue(stack.getDamageValue() - 1);
				}
			}
		}
	}

	private void repairYangItems(Player player, int lightValue) {
		Predicate<ItemStack> stackPredicate = (stack) -> stack.isDamaged() && stack.getDamageValue() > (int) ((double) stack.getMaxDamage() / 2.0D)
				&& EnchantmentUtil.hasEnchantment(EnchantmentRegistry.YANG.get(), stack);

		if (lightValue > 7) {
			for (ItemStack stack : player.getArmorSlots()) {
				if (stackPredicate.test(stack)) {
					stack.setDamageValue(stack.getDamageValue() - 1);
				}
			}

			for (ItemStack stack : player.getHandSlots()) {
				if (stackPredicate.test(stack)) {
					stack.setDamageValue(stack.getDamageValue() - 1);
				}
			}
		}
	}
}
