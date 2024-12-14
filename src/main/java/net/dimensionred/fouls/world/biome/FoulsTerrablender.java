package net.dimensionred.fouls.world.biome;

import net.dimensionred.fouls.Fouls;
import net.dimensionred.fouls.world.biome.surface.FoulsMaterialRules;
import net.minecraft.util.Identifier;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class FoulsTerrablender implements TerraBlenderApi {

    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new FoulsOverworldRegion(Identifier.of(Fouls.MOD_ID, "overworld"), 6));

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, Fouls.MOD_ID, FoulsMaterialRules.makeRules());
    }

}
