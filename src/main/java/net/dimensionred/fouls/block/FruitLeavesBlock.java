package net.dimensionred.fouls.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class FruitLeavesBlock extends LeavesBlock {

    public FruitLeavesBlock(Settings settings) {
        super(settings);
    }

    public static Settings createSettings() {
        return Settings.create()
                .sounds(BlockSoundGroup.AZALEA_LEAVES)
                .mapColor(MapColor.WHITE_GRAY)
                .strength(0.2F)
                .ticksRandomly()
                .nonOpaque()
                .allowsSpawning(Blocks::canSpawnOnLeaves)
                .suffocates(Blocks::never)
                .blockVision(Blocks::never)
                .burnable()
                .pistonBehavior(PistonBehavior.DESTROY)
                .solidBlock(Blocks::never);
    }

    @Override
    protected float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos) {

        ItemStack stack = player.getMainHandStack();
        if (stack.getItem() == Items.SHEARS) {
            return 1.0F;
        }
        return super.calcBlockBreakingDelta(state, player, world, pos);
    }

}
