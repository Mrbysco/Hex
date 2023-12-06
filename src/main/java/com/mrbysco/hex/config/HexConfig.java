package com.mrbysco.hex.config;

import com.mrbysco.hex.Hex;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

public class HexConfig {
	public static class Common {
		public final IntValue maxCultivationLevel;
		public final IntValue maxYieldingLevel;
		public final DoubleValue affectionPercentage;
		public final BooleanValue avoidingUponDeath;

		Common(ForgeConfigSpec.Builder builder) {
			builder.comment("General settings")
					.push("General");

			maxCultivationLevel = builder
					.comment("Defines the maximum level of the Cultivation Enchantment that can be achieved [Default: 4]")
					.defineInRange("maxAffection", 4, 1, 5);

			maxYieldingLevel = builder
					.comment("Defines the maximum level of the Yielding Enchantment that can be achieved [Default: 4]")
					.defineInRange("maxYieldingLevel", 4, 1, 5);

			affectionPercentage = builder
					.comment("Defines the durability percentage that affects the Affection Enchantment [Default: 0.01 (1%)]")
					.defineInRange("affectionPercentage", 0.01, 0.01, 1.0);

			avoidingUponDeath = builder
					.comment("Defines if the Avoiding Enchantment should teleport the item to world spawn upon death [Default: true]")
					.define("avoidingUponDeath", true);

			builder.pop();
		}
	}

	public static final ForgeConfigSpec commonSpec;
	public static final Common COMMON;

	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		commonSpec = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	@SubscribeEvent
	public static void onLoad(final ModConfigEvent.Loading configEvent) {
		Hex.LOGGER.debug("Loaded Hex's config file {}", configEvent.getConfig().getFileName());
	}

	@SubscribeEvent
	public static void onFileChange(final ModConfigEvent.Reloading configEvent) {
		Hex.LOGGER.debug("Hex's config just got changed on the file system!");
	}
}
