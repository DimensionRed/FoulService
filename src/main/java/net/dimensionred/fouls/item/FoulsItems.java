package net.dimensionred.fouls.item;


import net.dimensionred.fouls.Fouls;
import net.dimensionred.fouls.block.FloweringPaleOakLeaves;
import net.dimensionred.fouls.block.FoulsCarpetBlock;
import net.dimensionred.fouls.block.FoulsPottedFlowerBlock;
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
            "pale_flower", new Item.Settings()
    );
    public static final Item GREEN_FRUIT = registerItem(
            "green_fruit", new Item.Settings()
                    .food(FoulsFoodComponents.GREEN_FRUIT, FoulsFoodComponents.GREEN_FRUIT_EFFECT)
    );
    public static final Item VENOM_FLASK = registerItem(
            "venom_flask", new Item.Settings().recipeRemainder(Items.GLASS_BOTTLE)
    );
    public static final Item SWEET_FRUIT = registerItem(
            "sweet_fruit", new Item.Settings()
                    .food(FoulsFoodComponents.SWEET_FRUIT, FoulsFoodComponents.SWEET_FRUIT_EFFECT)
    );
    public static final Item SWEET_PIE = registerItem(
            "sweet_pie", new Item.Settings()
                    .food(FoulsFoodComponents.SWEET_PIE, FoulsFoodComponents.SWEET_PIE_EFFECT)
    );
    public static final Item PALE_OAK_BRANCH = registerItem(
            "pale_oak_branch", new Item.Settings()
    );





    public static final Block FLOWERING_PALE_OAK_LEAVES = registerBlock(
            "flowering_pale_oak_leaves",
            FloweringPaleOakLeaves::new,
            FloweringPaleOakLeaves.createSettings()
    );
    public static final Block GREEN_FRUIT_LEAVES = registerBlock(
            "green_fruit_leaves",
            FruitLeavesBlock::new,
            FruitLeavesBlock.createSettings()
    );
    public static final Block SWEET_FRUIT_LEAVES = registerBlock(
            "sweet_fruit_leaves",
            FruitLeavesBlock::new,
            FruitLeavesBlock.createSettings()
    );
    public static final Block FLOWERING_LEAVES_CARPET = registerBlock(
            "flowering_pale_oak_leaves_carpet",
            FoulsCarpetBlock::new,
            FoulsCarpetBlock.createSettings()
    );
    public static final Block PALE_OAK_LEAVES_CARPET = registerBlock(
            "pale_oak_leaves_carpet",
            FoulsCarpetBlock::new,
            FoulsCarpetBlock.createSettings()
    );
    public static final Block PALE_PETALS = registerBlock(
            "pale_petals",
            FlowerbedBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .noCollision()
                    .sounds(BlockSoundGroup.PINK_PETALS)
                    .pistonBehavior(PistonBehavior.DESTROY)
    );
    public static final Block FLOWERING_PALE_OAK_SAPLING = registerBlock(
            "flowering_pale_oak_sapling",
            settings -> new SaplingBlock(FoulsSaplingGenerators.FLOWERING_PALE_OAK, settings),
            SaplingBlock.Settings.create()
                    .sounds(BlockSoundGroup.CHERRY_SAPLING)
                    .noCollision()
    );


    public static final Block POTTED_FLOWERING_PALE_OAK_SAPLING = registerBlock(
            "potted_flowering_pale_oak_sapling",
            settings -> new FoulsPottedFlowerBlock(FLOWERING_PALE_OAK_SAPLING, settings),
            AbstractBlock.Settings.create()
    );



    public static Item registerItem(String id, Item.Settings settings) {
        Identifier item_id = Identifier.of(Fouls.MOD_ID, id);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, item_id);
        Item registeredItem = Registry.register(Registries.ITEM, key, new Item(settings
                .useItemPrefixedTranslationKey()
                .registryKey(key)));
        return registeredItem;
    }



    private static <T extends Block> T registerBlock(
            String id,
            Function<AbstractBlock.Settings, Block> factory,
            AbstractBlock.Settings settings) {

        Identifier identifier = Identifier.of(Fouls.MOD_ID, id);
        RegistryKey<Block> registryKey = RegistryKey.of(RegistryKeys.BLOCK, identifier);
        T block = (T) Blocks.register(registryKey, factory, settings);

        registerBlockItem(block);
        return block;
    }

    public static void registerBlockItem(Block block){
        Items.register(block);
    }

    public static void setup() {
        Fouls.LOGGER.info("[" + Fouls.MOD_ID + "] Registering items...");

    }
}
