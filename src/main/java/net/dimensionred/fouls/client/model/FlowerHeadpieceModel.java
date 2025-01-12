package net.dimensionred.fouls.client.model;

import net.dimensionred.fouls.Fouls;
import net.dimensionred.fouls.item.FlowerHeadpiece;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class FlowerHeadpieceModel extends GeoModel<FlowerHeadpiece> {
    @Override
    public Identifier getModelResource(FlowerHeadpiece flowerHeadpiece, @Nullable GeoRenderer<FlowerHeadpiece> geoRenderer) {
        return Identifier.of(Fouls.MOD_ID, "geo/item/flower_headpiece.geo.json");
    }

    @Override
    public Identifier getTextureResource(FlowerHeadpiece flowerHeadpiece, @Nullable GeoRenderer<FlowerHeadpiece> geoRenderer) {
        return Identifier.of(Fouls.MOD_ID, "textures/armor/flower_headpiece.png");
    }

    @Override
    public Identifier getAnimationResource(FlowerHeadpiece flowerHeadpiece) {
        return Identifier.of(Fouls.MOD_ID, "animations/flower_headpiece.animation.json");
    }
}
