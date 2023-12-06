package com.mrbysco.hex.handler;

import com.mrbysco.hex.config.HexConfig;
import com.mrbysco.hex.registry.EnchantmentRegistry;
import com.mrbysco.hex.util.EnchantmentUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AvoidingHandler {
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void onLivingDrops(LivingDropsEvent event) {
		Collection<ItemEntity> drops = event.getDrops();
		List<ItemEntity> toRemove = new ArrayList<>();
		final boolean avoidingFlag = HexConfig.COMMON.avoidingUponDeath.get();
		if (avoidingFlag) {
			for (ItemEntity drop : drops) {
				ItemStack stack = drop.getItem().copy();
				if (EnchantmentUtil.hasEnchantment(EnchantmentRegistry.AVOIDING.get(), stack)) {
					Level level = drop.level();
					if (!level.isClientSide) {
						ServerLevel serverLevel = (ServerLevel) level;
						if (!level.dimension().location().equals(Level.OVERWORLD.location())) {
							MinecraftServer server = serverLevel.getServer();
							serverLevel = server.getLevel(Level.OVERWORLD);
						}
						BlockPos spawnPos = serverLevel.getSharedSpawnPos();
						ItemEntity newEntity = new ItemEntity(serverLevel, spawnPos.getX(), spawnPos.getY() + 0.5, spawnPos.getZ(), stack);
						serverLevel.addFreshEntity(newEntity);
					}
					toRemove.add(drop);
				}
			}
		}

		drops.removeAll(toRemove);
	}

	public static void entityLeaveWorldEvent(ItemEntity itemEntity) {
		Level level = itemEntity.level();
		ItemStack stack = itemEntity.getItem().copy();
		if (!level.isClientSide && EnchantmentUtil.hasEnchantment(EnchantmentRegistry.AVOIDING.get(), stack)) {
			ServerLevel serverLevel = (ServerLevel) level;
			if (!level.dimension().location().equals(Level.OVERWORLD.location())) {
				MinecraftServer server = serverLevel.getServer();
				serverLevel = server.getLevel(Level.OVERWORLD);
			}
			BlockPos spawnPos = serverLevel.getSharedSpawnPos();
			ItemEntity newEntity = new ItemEntity(serverLevel, spawnPos.getX(), spawnPos.getY() + 0.5, spawnPos.getZ(), stack);
			serverLevel.addFreshEntity(newEntity);
		}
	}
}
