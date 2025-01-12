package net.dimensionred.fouls.client.render;

import net.dimensionred.fouls.Fouls;
import net.dimensionred.fouls.client.model.FlowerHeadpieceModel;
import net.dimensionred.fouls.item.FlowerHeadpiece;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class FlowerHeadpieceRenderer extends GeoArmorRenderer<FlowerHeadpiece> {

    public FlowerHeadpieceRenderer() {
        super(new FlowerHeadpieceModel());
    }

}
