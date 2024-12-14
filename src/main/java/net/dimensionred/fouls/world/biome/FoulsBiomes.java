package net.dimensionred.fouls.world.biome;

import net.dimensionred.fouls.Fouls;
import net.dimensionred.fouls.world.FoulsPlacedFeatures;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicType;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class FoulsBiomes {

    public static final RegistryKey<Biome> PALE_ORCHARD_BIOME = RegistryKey.of(RegistryKeys.BIOME,
            Identifier.of(Fouls.MOD_ID, "pale_orchard"));

    public static void boostrap(Registerable<Biome> context) {
        context.register(PALE_ORCHARD_BIOME, testBiome(context));
    }


    public static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addAmethystGeodes(builder);
        DefaultBiomeFeatures.addDungeons(builder);
        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addSprings(builder);
        DefaultBiomeFeatures.addFrozenTopLayer(builder);
    }

    public static Biome testBiome(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

        //spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 5, 4, 4));
        //NO SPAWNING - LEFT JUST FOR REFERENCE

        //DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        //DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);

        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);



        //DefaultBiomeFeatures.addMossyRocks(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, FoulsPlacedFeatures.TREE_FLOWERING_PALE_OAK_PLACED_KEY);
        //biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PALE_GARDEN_VEGETATION);
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PALE_MOSS_PATCH);

        //FoulsBiomeFeatures.addPaleOrchardFeatures(biomeBuilder);//CHANGE WITH PALE LEAVES AND PETALS

        //DefaultBiomeFeatures.addForestFlowers(biomeBuilder);
        DefaultBiomeFeatures.addDefaultGrass(biomeBuilder);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, FoulsPlacedFeatures.PALE_PETALS_PLACED_KEY);
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, FoulsPlacedFeatures.PALE_ORCHARD_PATCH_PLACED_KEY);
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, FoulsPlacedFeatures.LEAVES_PALE_ORCHARD_PATCH_PLACED_KEY);

        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);


        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.4f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0xb5bcbc)
                        .waterFogColor(0x5f676f)
                        .skyColor(0x8e8e8c)
                        .grassColor(0x858789)
                        .foliageColor(0xc8c7c1)
                        .fogColor(0xcbcbc9)
                        .moodSound(BiomeMoodSound.CAVE)
                        .music(MusicType.createIngameMusic(MusicType.MENU.getSound())).build())
                .build();
    }

}
