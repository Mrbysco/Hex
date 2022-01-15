package com.mrbysco.hex;

import com.mrbysco.hex.config.HexConfig;
import com.mrbysco.hex.handler.AvoidingHandler;
import com.mrbysco.hex.handler.CheapskateHandler;
import com.mrbysco.hex.handler.EnchantmentHandler;
import com.mrbysco.hex.handler.YingYangHandler;
import com.mrbysco.hex.registry.EnchantmentRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Reference.MOD_ID)
public class Hex {
    public static final Logger LOGGER = LogManager.getLogger();

    public Hex() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(Type.COMMON, HexConfig.commonSpec);
        eventBus.register(HexConfig.class);

        EnchantmentRegistry.ENCHANTMENTS.register(eventBus);

        MinecraftForge.EVENT_BUS.register(new EnchantmentHandler());
        MinecraftForge.EVENT_BUS.register(new YingYangHandler());
        MinecraftForge.EVENT_BUS.register(new AvoidingHandler());
        MinecraftForge.EVENT_BUS.register(new CheapskateHandler());
    }
}