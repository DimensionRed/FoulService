package net.dimensionred.fouls.block;

import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

public class FoulsCarpetBlock extends FlowerBlock {

    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.0, 16.0);

    public FoulsCarpetBlock(Settings settings) {
        super(StatusEffects.POISON, 5, settings);
    }

    public static CarpetBlock.Settings createSettings() {
        return CarpetBlock.Settings.create()
                .sounds(BlockSoundGroup.AZALEA_LEAVES)
                .mapColor(MapColor.WHITE_GRAY)
                .nonOpaque()
                .noCollision();

    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos belowPos = pos.down();
        BlockState belowState = world.getBlockState(belowPos);

        // Ensure the block below is a solid block
        if (belowState.isSolidBlock(world, belowPos)) {
            return true;
        }

        return super.canPlaceAt(state, world, pos);


    }
}
