package com.mrbysco.hex.datagen;

import com.mrbysco.hex.Hex;
import com.mrbysco.hex.registry.EnchantmentRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber
public class HexDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent.Client event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		generator.addProvider(true, new ModDatapackProvider(
				packOutput,
				event.getLookupProvider(),
				Set.of(Hex.MOD_ID)
		));
		generator.addProvider(true, new ModEnchantmentTagProvider(packOutput,
				lookupProvider));

		generator.addProvider(true, new HexLanguage(packOutput));
	}

	public static class ModDatapackProvider extends DatapackBuiltinEntriesProvider {
		public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
				.add(Registries.ENCHANTMENT, EnchantmentRegistry::bootstrap);

		public ModDatapackProvider(PackOutput output, CompletableFuture<Provider> registries, Set<String> modIds) {
			super(output, registries, BUILDER, modIds);
		}
	}


	private static class ModEnchantmentTagProvider extends EnchantmentTagsProvider {
		public ModEnchantmentTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
			super(output, lookupProvider, Hex.MOD_ID);
		}

		@Override
		protected void addTags(HolderLookup.Provider pProvider) {
			tag(EnchantmentTags.CURSE).addOptional(EnchantmentRegistry.AFFECTION).addOptional(EnchantmentRegistry.NONCOMBINING);
			tag(EnchantmentTags.TREASURE).addOptional(EnchantmentRegistry.AFFECTION).addOptional(EnchantmentRegistry.NONCOMBINING);
			tag(EnchantmentTags.NON_TREASURE)
					.addOptional(EnchantmentRegistry.CULTIVATION)
					.addOptional(EnchantmentRegistry.YIELDING)
					.addOptional(EnchantmentRegistry.YING)
					.addOptional(EnchantmentRegistry.YANG)
					.addOptional(EnchantmentRegistry.GOLDEN_GLINT)
					.addOptional(EnchantmentRegistry.AVOIDING)
					.addOptional(EnchantmentRegistry.CHEAPSKATE);
		}
	}

	private static class HexLanguage extends LanguageProvider {
		public HexLanguage(PackOutput gen) {
			super(gen, Hex.MOD_ID, "en_us");
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

		private void addEnchantment(ResourceKey<Enchantment> key, String name) {
			Identifier location = key.identifier();
			add("enchantment." + location.toLanguageKey(), name);
		}

		private void addEnchantmentDescription(ResourceKey<Enchantment> key, String description) {
			Identifier location = key.identifier();
			add("enchantment." + location.toLanguageKey() + ".desc", description);
		}
	}
}
