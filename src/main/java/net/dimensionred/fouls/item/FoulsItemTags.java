package net.dimensionred.fouls.item;

import net.dimensionred.fouls.Fouls;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class FoulsItemTags {

    public static final TagKey<Item> REPAIRS_FLOWER_HEADPIECE = of("repairs_flower_headpiece");

    private static TagKey<Item> of(String id) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(Fouls.MOD_ID, id));
    }

}
