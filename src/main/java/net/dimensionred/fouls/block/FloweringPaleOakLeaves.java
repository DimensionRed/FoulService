package net.dimensionred.fouls.block;

import net.dimensionred.fouls.Fouls;
import net.dimensionred.fouls.client.particle.FoulsParticleTypes;
import net.dimensionred.fouls.item.FoulsItems;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class FloweringPaleOakLeaves extends LeavesBlock {

    public static final TagKey<Biome> SWEET_FRUIT_BIOMES = TagKey.of(RegistryKeys.BIOME, Identifier.of(Fouls.MOD_ID, "sweet_fruit_biomes"));
    private final SimpleParticleType petals = FoulsParticleTypes.PALE_FLOWER_PETALS;
    private final int spawn_chance = 20;
    private final int cycle_chance = 99;
    public static final IntProperty AGE = Properties.AGE_7;

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

    public FloweringPaleOakLeaves(Settings settings) {

        super(settings);
        setDefaultState(this.stateManager.getDefaultState()
                .with(AGE, 0)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(AGE);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);

        //SPAWN PARTICLES
        if (random.nextInt(spawn_chance) == 0) {
            BlockPos blockPos = pos.down();
            BlockState blockState = world.getBlockState(blockPos);
            if (!isFaceFullSquare(blockState.getCollisionShape(world, blockPos), Direction.UP)) {
                ParticleUtil.spawnParticle(world, pos, random, petals);
            }
        }
    }

    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(cycle_chance) > 69) {
            cycleLeaves(state, world, pos);
        }
    }

    public static void cycleLeaves(BlockState state, World world, BlockPos pos) {
        if (world instanceof ServerWorld) {

            boolean waterlogged = state.get(LeavesBlock.WATERLOGGED);

            boolean persistence = state.get(LeavesBlock.PERSISTENT) ;
            int distance = state.get(LeavesBlock.DISTANCE);
            int age = state.get(FloweringPaleOakLeaves.AGE);

            if(age < 6){
                world.setBlockState(pos, FoulsItems.FLOWERING_PALE_OAK_LEAVES
                        .getDefaultState()
                        .with(FloweringPaleOakLeaves.AGE, age + 1)
                        .with(LeavesBlock.PERSISTENT, persistence)
                        .with(LeavesBlock.WATERLOGGED, waterlogged)
                        .with(LeavesBlock.DISTANCE, distance));
            } else {
                if(world.getBiome(pos).isIn(SWEET_FRUIT_BIOMES)) {
                    world.setBlockState(pos, FoulsItems.SWEET_FRUIT_LEAVES
                            .getDefaultState()
                            .with(LeavesBlock.PERSISTENT, persistence)
                            .with(LeavesBlock.WATERLOGGED, waterlogged)
                            .with(LeavesBlock.DISTANCE, distance));

                } else {
                    world.setBlockState(pos, FoulsItems.GREEN_FRUIT_LEAVES
                            .getDefaultState()
                            .with(LeavesBlock.PERSISTENT, persistence)
                            .with(LeavesBlock.WATERLOGGED, waterlogged)
                            .with(LeavesBlock.DISTANCE, distance));
                }
            }
        }
    }

    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        var block = hit.getBlockPos();
        boolean persistence = state.get(LeavesBlock.PERSISTENT);
        boolean waterlogged = state.get(LeavesBlock.WATERLOGGED);

        if (stack.getItem() == Items.BONE_MEAL) {

            if (!player.getAbilities().creativeMode) {
                stack.decrement(1);
            }
            world.playSound(null, block, SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (world instanceof ServerWorld) {
                ((ServerWorld) world).spawnParticles(ParticleTypes.HAPPY_VILLAGER,
                        block.getX() + 0.5,
                        block.getY() + 0.5,
                        block.getZ() + 0.5,
                        10,
                        0.5,
                        0.5,
                        0.5,
                        0.1);
            }

            if(world.getBiome(block).isIn(SWEET_FRUIT_BIOMES)) {
                world.setBlockState(block, FoulsItems.SWEET_FRUIT_LEAVES.getDefaultState()
                        .with(LeavesBlock.PERSISTENT, persistence)
                        .with(LeavesBlock.WATERLOGGED, waterlogged));

            } else {
                world.setBlockState(block, FoulsItems.GREEN_FRUIT_LEAVES.getDefaultState()
                        .with(LeavesBlock.PERSISTENT, persistence)
                        .with(LeavesBlock.WATERLOGGED, waterlogged));
            }
        }

        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }
}
