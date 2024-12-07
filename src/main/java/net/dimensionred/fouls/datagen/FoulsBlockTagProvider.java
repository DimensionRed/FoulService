package net.dimensionred.fouls.datagen;

import net.dimensionred.fouls.item.FoulsItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class FoulsBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public FoulsBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(FoulsItems.FLOWERING_PALE_OAK_LEAVES)
                .add(FoulsItems.GREEN_FRUIT_LEAVES)
                .add(FoulsItems.SWEET_FRUIT_LEAVES);
    }
}
