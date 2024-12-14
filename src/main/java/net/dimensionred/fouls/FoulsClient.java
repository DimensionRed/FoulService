package net.dimensionred.fouls;

import net.dimensionred.fouls.client.particle.FoulsParticleTypes;
import net.dimensionred.fouls.item.FoulsItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.LeavesParticle;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class FoulsClient implements ClientModInitializer {


    public static final EntityModelLayer FLOWER_LAYER = new EntityModelLayer(Identifier.of(Fouls.MOD_ID, "flower_head"), "main");
    public static final Identifier TEXTURE = Identifier.of(Fouls.MOD_ID +":textures/armor/flower.png");

    @Override
    public void onInitializeClient() {



        BlockRenderLayerMap.INSTANCE.putBlock(FoulsItems.FLOWERING_PALE_OAK_LEAVES, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(FoulsItems.GREEN_FRUIT_LEAVES, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(FoulsItems.SWEET_FRUIT_LEAVES, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(FoulsItems.FLOWERING_LEAVES_CARPET, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(FoulsItems.PALE_OAK_LEAVES_CARPET, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(FoulsItems.PALE_PETALS, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(FoulsItems.FLOWERING_PALE_OAK_SAPLING, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(FoulsItems.POTTED_FLOWERING_PALE_OAK_SAPLING, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(FoulsItems.THORNS, RenderLayer.getCutoutMipped());


        ParticleFactoryRegistry.getInstance().register(FoulsParticleTypes.PALE_FLOWER_PETALS, LeavesParticle.PaleOakLeavesFactory::new);


    }
}
