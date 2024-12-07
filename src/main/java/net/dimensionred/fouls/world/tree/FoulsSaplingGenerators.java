package net.dimensionred.fouls.world.tree;

import net.dimensionred.fouls.Fouls;
import net.dimensionred.fouls.world.FoulsConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class FoulsSaplingGenerators {

    public static final SaplingGenerator FLOWERING_PALE_OAK = new SaplingGenerator(Fouls.MOD_ID + ":flowering-pale_oak",
            Optional.of(FoulsConfiguredFeatures.FLOWERING_PALE_OAK_KEY), Optional.empty(), Optional.empty());
}
