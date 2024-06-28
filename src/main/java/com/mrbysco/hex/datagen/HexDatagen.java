package com.mrbysco.hex.datagen;

import com.mrbysco.hex.Hex;
import com.mrbysco.hex.registry.EnchantmentRegistry;
import net.minecraft.core.Cloner;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class HexDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();

		if (event.includeServer()) {
			generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(
					packOutput, CompletableFuture.supplyAsync(HexDatagen::getProvider), Set.of(Hex.MOD_ID)));

			generator.addProvider(event.includeServer(), new ModEnchantmentTagProvider(packOutput,
					CompletableFuture.supplyAsync(() -> HexDatagen.getProvider().full()), helper));
		}
		if (event.includeClient()) {
			generator.addProvider(event.includeClient(), new HexLanguage(packOutput));
		}
	}

	private static RegistrySetBuilder.PatchedRegistries getProvider() {
		final RegistrySetBuilder registryBuilder = new RegistrySetBuilder();
		registryBuilder.add(Registries.ENCHANTMENT, EnchantmentRegistry::bootstrap);

		RegistryAccess.Frozen regAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
		Cloner.Factory cloner$factory = new Cloner.Factory();
		net.neoforged.neoforge.registries.DataPackRegistriesHooks.getDataPackRegistriesWithDimensions().forEach(data -> data.runWithArguments(cloner$factory::addCodec));
		return registryBuilder.buildPatch(regAccess, VanillaRegistries.createLookup(), cloner$factory);
	}

	private static class ModEnchantmentTagProvider extends EnchantmentTagsProvider {
		public ModEnchantmentTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> plookupprovider, ExistingFileHelper fileHelper) {
			super(output, plookupprovider, Hex.MOD_ID, fileHelper);
		}

		@Override
		protected void addTags(HolderLookup.Provider pProvider) {
			tag(EnchantmentTags.CURSE).add(EnchantmentRegistry.AFFECTION).add(EnchantmentRegistry.NONCOMBINING);
			tag(EnchantmentTags.TREASURE).add(EnchantmentRegistry.AFFECTION).add(EnchantmentRegistry.NONCOMBINING);
			tag(EnchantmentTags.NON_TREASURE).add(
					EnchantmentRegistry.CULTIVATION,
					EnchantmentRegistry.YIELDING,
					EnchantmentRegistry.YING,
					EnchantmentRegistry.YANG,
					EnchantmentRegistry.GOLDEN_GLINT,
					EnchantmentRegistry.AVOIDING,
					EnchantmentRegistry.CHEAPSKATE
			);
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
			ResourceLocation location = key.location();
			add("enchantment." + location.getNamespace() + "." + location.getPath(), name);
		}

		private void addEnchantmentDescription(ResourceKey<Enchantment> key, String description) {
			ResourceLocation location = key.location();
			add("enchantment." + location.getNamespace() + "." + location.getPath() + ".desc", description);
		}
	}
}
