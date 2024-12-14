package net.dimensionred.fouls;

import net.dimensionred.fouls.advancement.FoulsAdvancements;
import net.dimensionred.fouls.datagen.FoulsBlockTagProvider;
import net.dimensionred.fouls.datagen.FoulsRegistryDataGenerator;
import net.dimensionred.fouls.world.FoulsConfiguredFeatures;
import net.dimensionred.fouls.world.FoulsPlacedFeatures;
import net.dimensionred.fouls.world.biome.FoulsBiomes;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class FoulsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(FoulsBlockTagProvider::new);
		pack.addProvider(FoulsRegistryDataGenerator::new);
		pack.addProvider(FoulsAdvancements::new);



	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {

		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, FoulsConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, FoulsPlacedFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.BIOME, FoulsBiomes::boostrap);
	}
}
