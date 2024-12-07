package net.dimensionred.fouls.misc;

import net.dimensionred.fouls.item.FoulsItems;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;

public class FoulsComposting {

    public static void setup(){

        CompostingChanceRegistry.INSTANCE.add(FoulsItems.FLOWERING_PALE_OAK_LEAVES, 0.3F);
        CompostingChanceRegistry.INSTANCE.add(FoulsItems.GREEN_FRUIT_LEAVES, 0.7F);
        CompostingChanceRegistry.INSTANCE.add(FoulsItems.SWEET_FRUIT_LEAVES, 0.7F);
        CompostingChanceRegistry.INSTANCE.add(FoulsItems.SWEET_FRUIT,0.65F);
        CompostingChanceRegistry.INSTANCE.add(FoulsItems.GREEN_FRUIT,0.65F);
        CompostingChanceRegistry.INSTANCE.add(FoulsItems.PALE_FLOWER,0.3F);
        CompostingChanceRegistry.INSTANCE.add(FoulsItems.FLOWERING_LEAVES_CARPET,0.3F);
        CompostingChanceRegistry.INSTANCE.add(FoulsItems.PALE_OAK_LEAVES_CARPET,0.3F);
        CompostingChanceRegistry.INSTANCE.add(FoulsItems.PALE_PETALS,0.3F);
        CompostingChanceRegistry.INSTANCE.add(FoulsItems.FLOWERING_PALE_OAK_SAPLING,0.3F);


    }
}
