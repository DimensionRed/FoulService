package net.dimensionred.fouls.block;

import net.dimensionred.fouls.client.particle.FoulsParticleTypes;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class PaleOakLeavesBlock extends LeavesBlock {

    private final SimpleParticleType petals = FoulsParticleTypes.PALE_FLOWER_PETALS;
    private final int spawn_chance = 6;

    public PaleOakLeavesBlock(Settings settings) {
        super(settings);
    }

    public static Block.Settings createSettings() {
        return Block.Settings.create()
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

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        if (random.nextInt(spawn_chance) == 0) {
            BlockPos blockPos = pos.down();
            BlockState blockState = world.getBlockState(blockPos);
            if (!isFaceFullSquare(blockState.getCollisionShape(world, blockPos), Direction.UP)) {
                ParticleUtil.spawnParticle(world, pos, random, petals);
            }
        }
    }

}
