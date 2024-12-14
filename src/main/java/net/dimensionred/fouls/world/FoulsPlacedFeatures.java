package net.dimensionred.fouls.world;

import net.dimensionred.fouls.Fouls;
import net.dimensionred.fouls.item.FoulsItems;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class FoulsPlacedFeatures {

    public static final RegistryKey<PlacedFeature> FLOWERING_PALE_OAK_PLACED_KEY = registerKey("flowering_pale_oak_placed");
    public static final RegistryKey<PlacedFeature> TREE_FLOWERING_PALE_OAK_PLACED_KEY = registerKey("tree_flowering_pale_oak_placed");
    public static final RegistryKey<PlacedFeature> PALE_PETALS_PLACED_KEY = registerKey("pale_petals_placed");
    public static final RegistryKey<PlacedFeature> PALE_ORCHARD_PATCH_PLACED_KEY = registerKey("pale_orchard_patch_placed");
    public static final RegistryKey<PlacedFeature> LEAVES_PALE_ORCHARD_PATCH_PLACED_KEY = registerKey("leaves_pale_orchard_patch_placed");


    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatures = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, FLOWERING_PALE_OAK_PLACED_KEY, configuredFeatures.getOrThrow(FoulsConfiguredFeatures.FLOWERING_PALE_OAK_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(2, 0.1F, 2), FoulsItems.FLOWERING_PALE_OAK_SAPLING
                )
                );
        register(context, TREE_FLOWERING_PALE_OAK_PLACED_KEY, configuredFeatures.getOrThrow(FoulsConfiguredFeatures.TREE_FLOWERING_PALE_OAK_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(2, 0.1F, 2), FoulsItems.FLOWERING_PALE_OAK_SAPLING
                )
        );
        register(context, PALE_ORCHARD_PATCH_PLACED_KEY, configuredFeatures.getOrThrow(FoulsConfiguredFeatures.PALE_ORCHARD_PATCH_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(2, 0.1F, 2), FoulsItems.FLOWERING_PALE_OAK_SAPLING
                )
        );
        register(context, LEAVES_PALE_ORCHARD_PATCH_PLACED_KEY, configuredFeatures.getOrThrow(FoulsConfiguredFeatures.LEAVES_PALE_ORCHARD_PATCH_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(2, 0.1F, 2), FoulsItems.FLOWERING_PALE_OAK_SAPLING
                )
        );
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        RegistryEntry<ConfiguredFeature<?, ?>> registryEntryPetals = registryEntryLookup.getOrThrow(FoulsConfiguredFeatures.PALE_PETALS_KEY);
        //RegistryEntry<ConfiguredFeature<?, ?>> registryEntryPatches = registryEntryLookup.getOrThrow(FoulsConfiguredFeatures.PALE_ORCHARD_PATCH_KEY);


        PlacedFeatures.register(
                context,
                PALE_PETALS_PLACED_KEY,
                registryEntryPetals,
                NoiseThresholdCountPlacementModifier.of(-0.8, 5, 10),
                SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.of()
        );
//        PlacedFeatures.register(
//                context,
//                PALE_ORCHARD_PATCH_PLACED_KEY,
//                registryEntryPatches,
//                NoiseThresholdCountPlacementModifier.of(-0.8, 5, 10),
//                SquarePlacementModifier.of(),
//                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
//                BiomePlacementModifier.of()
//        );

    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(Fouls.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }

}
