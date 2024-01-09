package com.mrbysco.hex;

import com.mojang.logging.LogUtils;
import com.mrbysco.hex.config.HexConfig;
import com.mrbysco.hex.handler.AvoidingHandler;
import com.mrbysco.hex.handler.CheapskateHandler;
import com.mrbysco.hex.handler.EnchantmentHandler;
import com.mrbysco.hex.handler.YingYangHandler;
import com.mrbysco.hex.registry.EnchantmentRegistry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(Hex.MOD_ID)
public class Hex {
	public static final String MOD_ID = "hex";
	public static final Logger LOGGER = LogUtils.getLogger();

	public Hex(IEventBus eventBus) {
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HexConfig.commonSpec);
		eventBus.register(HexConfig.class);

		EnchantmentRegistry.ENCHANTMENTS.register(eventBus);

		NeoForge.EVENT_BUS.register(new EnchantmentHandler());
		NeoForge.EVENT_BUS.register(new YingYangHandler());
		NeoForge.EVENT_BUS.register(new AvoidingHandler());
		NeoForge.EVENT_BUS.register(new CheapskateHandler());
	}
}