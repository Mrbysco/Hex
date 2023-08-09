package com.mrbysco.hex.datagen;

import com.mrbysco.hex.Reference;
import com.mrbysco.hex.registry.EnchantmentRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class HexDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();

		if (event.includeClient()) {
			generator.addProvider(event.includeClient(), new HexLanguage(packOutput));
		}
	}

	private static class HexLanguage extends LanguageProvider {
		public HexLanguage(PackOutput gen) {
			super(gen, Reference.MOD_ID, "en_us");
		}

		@Override
		protected void addTranslations() {
			addEnchantment(EnchantmentRegistry.CULTIVATION, "Cultivation");
			addEnchantmentDescription(EnchantmentRegistry.CULTIVATION, "Tills an area around water when right-clicked");

			addEnchantment(EnchantmentRegistry.YIELDING, "Yielding");
			addEnchantmentDescription(EnchantmentRegistry.YIELDING, "Increases the amount of crops you can harvest");

			addEnchantment(EnchantmentRegistry.AFFECTION, "Curse of Affection");
			addEnchantmentDescription(EnchantmentRegistry.AFFECTION, "Prevents the item from reaching durability 0");

			addEnchantment(EnchantmentRegistry.YING, "Ying");
			addEnchantmentDescription(EnchantmentRegistry.YING, "Repairs the item up to 50% while in darkness");

			addEnchantment(EnchantmentRegistry.YANG, "Yang");
			addEnchantmentDescription(EnchantmentRegistry.YANG, "Repairs the item up to 50% while in light");

			addEnchantment(EnchantmentRegistry.GOLDEN_GLINT, "Golden Glint");
			addEnchantmentDescription(EnchantmentRegistry.GOLDEN_GLINT, "Tricks Piglins into thinking you are wearing gold armor");

			addEnchantment(EnchantmentRegistry.AVOIDING, "Avoiding");
			addEnchantmentDescription(EnchantmentRegistry.AVOIDING, "Upon falling into the void teleports the item to world spawn");

			addEnchantment(EnchantmentRegistry.CHEAPSKATE, "Cheap Skate");
			addEnchantmentDescription(EnchantmentRegistry.CHEAPSKATE, "Locks the base repair level of an item");

			addEnchantment(EnchantmentRegistry.NONCOMBINING, "Curse of Non Combining");
			addEnchantmentDescription(EnchantmentRegistry.NONCOMBINING, "Stops the tool from being used in repair recipes");
		}

		private void addEnchantmentDescription(Supplier<? extends Enchantment> key, String description) {
			add(key.get().getDescriptionId() + ".desc", description);
		}
	}
}
