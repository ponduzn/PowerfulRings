package com.powerfulrings;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryKeys;
import powerfulrings.powerfulringsmod.datagen.ModBlockTagProvider;
import powerfulrings.powerfulringsmod.datagen.ModItemTagProvider;
import powerfulrings.powerfulringsmod.datagen.ModLootTableProvider;
import powerfulrings.powerfulringsmod.datagen.ModRecipeProvider;
import powerfulrings.powerfulringsmod.datagen.ModRegistryDataGenerator;
import powerfulrings.powerfulringsmod.world.ModConfiguredFeatures;
import powerfulrings.powerfulringsmod.world.ModPlacedFeatures;
import net.minecraft.registry.RegistryBuilder;

public class PowerfulringsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModRegistryDataGenerator::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
	}
}
