package net.dimensionred.fouls.world.biome;

import net.dimensionred.fouls.world.FoulsPlacedFeatures;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class FoulsBiomeFeatures {

    public static void addPaleOrchardFeatures(GenerationSettings.LookupBackedBuilder builder) {
        //builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, FoulsPlacedFeatures.FLOWERING_PALE_OAK_PLACED_KEY);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PALE_GARDEN_VEGETATION);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PALE_MOSS_PATCH);

    }
}
