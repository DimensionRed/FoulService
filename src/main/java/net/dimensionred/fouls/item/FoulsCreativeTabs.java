package net.dimensionred.fouls.item;

import net.dimensionred.fouls.Fouls;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class FoulsCreativeTabs {


    public static final ItemGroup FOULS_BLOCKS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Fouls.MOD_ID, "fouls_blocks"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(FoulsItems.SWEET_FRUIT_LEAVES))
                    .displayName(Text.translatable("itemgroup.fouls.fouls_blocks"))
                    .entries(((displayContext, entries) -> {
                        entries.add(FoulsItems.FLOWERING_PALE_OAK_LEAVES);
                        entries.add(FoulsItems.GREEN_FRUIT_LEAVES);
                        entries.add(FoulsItems.SWEET_FRUIT_LEAVES);
                        entries.add(FoulsItems.FLOWERING_LEAVES_CARPET);
                        entries.add(FoulsItems.PALE_OAK_LEAVES_CARPET);
                        entries.add(FoulsItems.PALE_PETALS);
                    }))
                    .build()
    );
    public static final ItemGroup FOULS_ITEMS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Fouls.MOD_ID, "fouls_items"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(FoulsItems.GREEN_FRUIT))
                    .displayName(Text.translatable("itemgroup.fouls.fouls_items"))
                    .entries(((displayContext, entries) -> {
                        entries.add(FoulsItems.PALE_FLOWER);
                        entries.add(FoulsItems.GREEN_FRUIT);
                        entries.add(FoulsItems.SWEET_FRUIT);
                        entries.add(FoulsItems.PALE_OAK_BRANCH);
                        entries.add(FoulsItems.SWEET_PIE);
                        entries.add(FoulsItems.VENOM_FLASK);
                    }))
                    .build()
    );

    public static void setup() {
        Fouls.LOGGER.info("[" + Fouls.MOD_ID + "] Registering creative tabs...");
    }
}
