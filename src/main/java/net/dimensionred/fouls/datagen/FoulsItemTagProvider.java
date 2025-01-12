package net.dimensionred.fouls.datagen;

import net.dimensionred.fouls.item.FoulsItemTags;
import net.dimensionred.fouls.item.FoulsItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class FoulsItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public FoulsItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(FoulsItemTags.REPAIRS_FLOWER_HEADPIECE)
                .add(FoulsItems.PALE_FLOWER);
    }
}
