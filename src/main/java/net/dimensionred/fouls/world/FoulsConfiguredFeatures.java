package net.dimensionred.fouls.world;

import net.dimensionred.fouls.Fouls;
import net.dimensionred.fouls.item.FoulsItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.foliage.DarkOakFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer;

import java.util.OptionalInt;


public class FoulsConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> FLOWERING_PALE_OAK_KEY = registerKey("flowering_pale_oak");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FLOWERING_PALE_OAK_DECOR_KEY = registerKey("flowering_pale_oak");


    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {

        ConfiguredFeatures.register(context, FLOWERING_PALE_OAK_KEY, Feature.TREE,
                (new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.PALE_OAK_LOG),
                        new DarkOakTrunkPlacer(6, 2, 1),
                        BlockStateProvider.of(FoulsItems.FLOWERING_PALE_OAK_LEAVES),
                        new DarkOakFoliagePlacer(ConstantIntProvider.create(0),
                        ConstantIntProvider.create(0)),
                        new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()))).ignoreVines().build());

//        ConfiguredFeatures.register(context, FLOWERING_PALE_OAK_DECOR_KEY, Feature.TREE,
//                (new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.PALE_OAK_LOG),
//                        new DarkOakTrunkPlacer(6, 2, 1),
//                        BlockStateProvider.of(FoulsItems.FLOWERING_PALE_OAK_LEAVES),
//                        new DarkOakFoliagePlacer(ConstantIntProvider.create(0),
//                        ConstantIntProvider.create(0)),
//                        new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())))
//                        .decorators(ImmutableList.of(new PaleMossTreeDecorator(0.15F, 0.4F, 0.8F),
//                                new CreakingHeartTreeDecorator(1.0F)))
//                        .ignoreVines()
//                        .build());

    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Fouls.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
