package net.dimensionred.fouls.world;

import net.dimensionred.fouls.Fouls;
import net.dimensionred.fouls.item.FoulsItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerbedBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.foliage.DarkOakFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer;

import java.util.OptionalInt;


public class FoulsConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> FLOWERING_PALE_OAK_KEY = registerKey("flowering_pale_oak");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TREE_FLOWERING_PALE_OAK_KEY = registerKey("tree_flowering_pale_oak");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PALE_PETALS_KEY = registerKey("pale_petals");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PALE_ORCHARD_PATCH_KEY = registerKey("pale_orchard_patch");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LEAVES_PALE_ORCHARD_PATCH_KEY = registerKey("leaves_pale_orchard_patch");



    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {

        DataPool.Builder<BlockState> pale_oak_leaves = DataPool.builder();
        pale_oak_leaves.add(Blocks.PALE_OAK_LEAVES.getDefaultState(), 1);
        pale_oak_leaves.add(FoulsItems.FLOWERING_PALE_OAK_LEAVES.getDefaultState().with(LeavesBlock.PERSISTENT, false), 1);

        DataPool.Builder<BlockState> tree_pale_oak_leaves = DataPool.builder();
        tree_pale_oak_leaves.add(Blocks.PALE_OAK_LEAVES.getDefaultState(), 1);
        tree_pale_oak_leaves.add(FoulsItems.FLOWERING_PALE_OAK_LEAVES.getDefaultState().with(LeavesBlock.PERSISTENT, true), 1);



        ConfiguredFeatures.register(context, FLOWERING_PALE_OAK_KEY, Feature.TREE,
                (new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.PALE_OAK_LOG),
                        new DarkOakTrunkPlacer(6, 2, 1),
                        new WeightedBlockStateProvider(pale_oak_leaves),//not persistent
                        new DarkOakFoliagePlacer(ConstantIntProvider.create(0),
                        ConstantIntProvider.create(0)),
                        new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())))
                        .ignoreVines()
                        .build());
        ConfiguredFeatures.register(context, TREE_FLOWERING_PALE_OAK_KEY, Feature.TREE,
                (new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.PALE_OAK_LOG),
                        new DarkOakTrunkPlacer(6, 2, 1),
                        new WeightedBlockStateProvider(pale_oak_leaves), //persistent
                        new DarkOakFoliagePlacer(ConstantIntProvider.create(0),
                                ConstantIntProvider.create(0)),
                        new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())))
                        .ignoreVines()
                        .build());
//        ConfiguredFeatures.register(context, PALE_PETALS_KEY, Feature.FLOWER, ConfiguredFeatures.createRandomPatchFeatureConfig(
//                64, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(FoulsItems.PALE_PETALS)))
//        ));

        DataPool.Builder<BlockState> pale_petals = DataPool.builder();
        for (int i = 1; i <= 4; i++) {
            for (Direction direction : Direction.Type.HORIZONTAL) {
                pale_petals.add(FoulsItems.PALE_PETALS.getDefaultState().with(FlowerbedBlock.FLOWER_AMOUNT, Integer.valueOf(i)).with(FlowerbedBlock.FACING, direction), 1);
            }
        }
        ConfiguredFeatures.register(
                context,
                PALE_PETALS_KEY,
                Feature.FLOWER,
                new RandomPatchFeatureConfig(//96
                        66, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(pale_petals)))
                )
        );

        ConfiguredFeatures.register(
                context,
                PALE_ORCHARD_PATCH_KEY,
                Feature.FLOWER,
                new RandomPatchFeatureConfig(
                        86, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(FoulsItems.FLOWERING_LEAVES_CARPET.getDefaultState())))
                )
        );
        ConfiguredFeatures.register(
                context,
                LEAVES_PALE_ORCHARD_PATCH_KEY,
                Feature.FLOWER,
                new RandomPatchFeatureConfig(
                        86, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(FoulsItems.PALE_OAK_LEAVES_CARPET.getDefaultState())))
                )
        );

    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Fouls.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
