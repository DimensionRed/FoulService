package net.dimensionred.fouls;

import net.dimensionred.fouls.client.particle.FoulsParticleTypes;
import net.dimensionred.fouls.item.FoulsItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.LeavesParticle;
import net.minecraft.client.render.RenderLayer;

public class FoulsClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(FoulsItems.FLOWERING_PALE_OAK_LEAVES, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(FoulsItems.GREEN_FRUIT_LEAVES, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(FoulsItems.SWEET_FRUIT_LEAVES, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(FoulsItems.FLOWERING_LEAVES_CARPET, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(FoulsItems.PALE_OAK_LEAVES_CARPET, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(FoulsItems.PALE_PETALS, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(FoulsItems.FLOWERING_PALE_OAK_SAPLING, RenderLayer.getCutoutMipped());

        ParticleFactoryRegistry.getInstance().register(FoulsParticleTypes.PALE_FLOWER_PETALS, LeavesParticle.PaleOakLeavesFactory::new);
    }
}
