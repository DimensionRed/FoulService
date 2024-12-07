package net.dimensionred.fouls.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarpetBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;

public class FoulsCarpetBlock extends CarpetBlock {
    public FoulsCarpetBlock(Settings settings) {
        super(settings);
    }

    public static CarpetBlock.Settings createSettings() {
        return CarpetBlock.Settings.create()
                .sounds(BlockSoundGroup.AZALEA_LEAVES)
                .mapColor(MapColor.WHITE_GRAY)
                .nonOpaque();
    }

}
