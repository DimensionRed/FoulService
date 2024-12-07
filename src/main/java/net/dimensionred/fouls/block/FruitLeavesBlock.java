package net.dimensionred.fouls.block;

import net.dimensionred.fouls.Fouls;
import net.dimensionred.fouls.item.FoulsItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class FruitLeavesBlock extends LeavesBlock {

    public static final TagKey<Biome> SWEET_FRUIT_BIOMES = TagKey.of(RegistryKeys.BIOME, Identifier.of(Fouls.MOD_ID, "sweet_fruit_biomes"));


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
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {

        var block = hit.getBlockPos();
        boolean persistence = state.get(LeavesBlock.PERSISTENT);
        boolean waterlogged = state.get(LeavesBlock.WATERLOGGED);

        world.setBlockState(block, FoulsItems.FLOWERING_PALE_OAK_LEAVES.getDefaultState()
                .with(LeavesBlock.PERSISTENT, persistence)
                .with(LeavesBlock.WATERLOGGED, waterlogged)
                .with(FloweringPaleOakLeaves.AGE, 0));

        world.playSound(null, block, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 1.0F);

        if(world.getBiome(hit.getBlockPos()).isIn(SWEET_FRUIT_BIOMES)){
            world.spawnEntity(new ItemEntity(world,
                    block.getX() + 0.5,
                    block.getY() - 0.5,
                    block.getZ() + 0.5,
                    new ItemStack(FoulsItems.SWEET_FRUIT)));
        } else {
            world.spawnEntity(new ItemEntity(world,
                    block.getX() + 0.5,
                    block.getY() - 0.5,
                    block.getZ() + 0.5,
                    new ItemStack(FoulsItems.GREEN_FRUIT)));
        }

        return super.onUse(state, world, pos, player, hit);
    }


    //    @Override
//    protected float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos) {
//
//        ItemStack stack = player.getMainHandStack();
//        if (stack.getItem() == Items.SHEARS) {
//            return 1.0F;
//        }
//        return super.calcBlockBreakingDelta(state, player, world, pos);
//    }

}
