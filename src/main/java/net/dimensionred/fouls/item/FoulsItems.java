package net.dimensionred.fouls.item;


import net.dimensionred.fouls.Fouls;
import net.dimensionred.fouls.block.FloweringPaleOakLeaves;
import net.dimensionred.fouls.block.FoulsCarpetBlock;
import net.dimensionred.fouls.block.FruitLeavesBlock;
import net.dimensionred.fouls.misc.FoulsFoodComponents;
import net.dimensionred.fouls.world.tree.FoulsSaplingGenerators;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class FoulsItems {

    public static final Item PALE_FLOWER = registerItem(
            "pale_flower",
            id -> new Item(new Item.Settings()
                    .registryKey(itemKey(id))
            ));
    public static final Item PALE_OAK_BRANCH = registerItem(
            "pale_oak_branch",
            id -> new Item(new Item.Settings()
                    .registryKey(itemKey(id))
            ));
    public static final Item GREEN_FRUIT = registerItem(
            "green_fruit",
            id -> new Item(new Item.Settings()
                    .registryKey(itemKey(id))
                    .food(FoulsFoodComponents.GREEN_FRUIT, FoulsFoodComponents.GREEN_FRUIT_EFFECT)

            ));
    public static final Item VENOM_FLASK = registerItem(
            "venom_flask",
            id -> new Item(new Item.Settings()
                    .registryKey(itemKey(id))
                    .recipeRemainder(Items.GLASS_BOTTLE)
            ));
    public static final Item SWEET_FRUIT = registerItem(
            "sweet_fruit",
            id -> new Item(new Item.Settings()
                    .registryKey(itemKey(id))
                    .food(FoulsFoodComponents.SWEET_FRUIT, FoulsFoodComponents.SWEET_FRUIT_EFFECT)
            ));
    public static final Item SWEET_PIE = registerItem(
            "sweet_pie",
            id -> new Item(new Item.Settings()
                    .registryKey(itemKey(id))
                    .food(FoulsFoodComponents.SWEET_PIE, FoulsFoodComponents.SWEET_PIE_EFFECT)
            ));




    public static final Block FLOWERING_PALE_OAK_LEAVES = registerBlock(
            "flowering_pale_oak_leaves",
            id -> new FloweringPaleOakLeaves(FloweringPaleOakLeaves
                    .createSettings()
                    .registryKey(FoulsItems.blockKey(id))
            ));

    public static final Block GREEN_FRUIT_LEAVES = registerBlock(
            "green_fruit_leaves",
            id -> new FruitLeavesBlock(FruitLeavesBlock
                    .createSettings()
                    .registryKey(FoulsItems.blockKey(id))
            ));

    public static final Block SWEET_FRUIT_LEAVES = registerBlock(
            "sweet_fruit_leaves",
            id -> new FruitLeavesBlock(FruitLeavesBlock
                    .createSettings()
                    .registryKey(FoulsItems.blockKey(id))
            ));
    public static final Block FLOWERING_LEAVES_CARPET = registerBlock(
        "flowering_pale_oak_leaves_carpet",
        id -> new FoulsCarpetBlock(FoulsCarpetBlock
                .createSettings()
                .registryKey(FoulsItems.blockKey(id))
        ));
    public static final Block PALE_OAK_LEAVES_CARPET = registerBlock(
            "pale_oak_leaves_carpet",
            id -> new FoulsCarpetBlock(FoulsCarpetBlock
                    .createSettings()
                    .registryKey(FoulsItems.blockKey(id))
            ));
    public static final Block PALE_PETALS = registerBlock(
            "pale_petals",
            id -> new FlowerbedBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .noCollision()
                    .sounds(BlockSoundGroup.PINK_PETALS)
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .registryKey(FoulsItems.blockKey(id))
            ));
    public static final Block FLOWERING_PALE_OAK_SAPLING = registerBlock(
            "flowering_pale_oak_sapling",
            id -> new SaplingBlock(FoulsSaplingGenerators.FLOWERING_PALE_OAK, SaplingBlock.Settings
                    .create()
                    .sounds(BlockSoundGroup.CHERRY_SAPLING)
                    .noCollision()
                    .registryKey(FoulsItems.blockKey(id))
            ));

    public static final Block POTTED_FLOWERING_PALE_OAK_SAPLING = registerBlock(
            "potted_flowering_pale_oak_sapling",
            id -> new FlowerPotBlock(FLOWERING_PALE_OAK_SAPLING,
                    AbstractBlock.Settings.create()
                    .registryKey(FoulsItems.blockKey(id))
            ));




    public static Item registerItem(String id, Function<Identifier, Item> itemFactory) {
        Identifier identifier = Identifier.of(Fouls.MOD_ID, id);
        return Registry.register(Registries.ITEM, identifier, itemFactory.apply(identifier));
    }
    public static Block registerBlock(String id, Function<String, Block> blockFactory) {
        Identifier identifier = Identifier.of(Fouls.MOD_ID, id);
        Block block = blockFactory.apply(id);
        Block registry = Registry.register(Registries.BLOCK, identifier, block);
        registerBlockItem(block);
        return registry;
    }

    public static void registerBlockItem(Block block){
        Items.register(block);
    }

    public static RegistryKey<Item> itemKey(Identifier id) {
        return RegistryKey.of(RegistryKeys.ITEM, id);
    }
    public static RegistryKey<Block> blockKey(String id) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Fouls.MOD_ID, id));
    }


    public static void setup() {
        Fouls.LOGGER.info("Registering items and blocks...");

    }

}
