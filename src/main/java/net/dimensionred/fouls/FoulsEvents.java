package net.dimensionred.fouls;

import net.dimensionred.fouls.block.PaleOakLeavesBlock;
import net.dimensionred.fouls.item.FoulsItems;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.event.Vibrations;

import java.util.Random;

public class FoulsEvents {

    private static final Random RANDOM = new Random();
    public static final StatusEffectInstance[] BAD_EFFECTS = new StatusEffectInstance[] {
            new StatusEffectInstance(StatusEffects.POISON, 80, 0),
            new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 1),
            new StatusEffectInstance(StatusEffects.WEAKNESS, 200, 0),
            new StatusEffectInstance(StatusEffects.NAUSEA, 200, 0)
    };

    private static StatusEffectInstance getRandomEffect() {
        return BAD_EFFECTS[RANDOM.nextInt(BAD_EFFECTS.length)];
    }

    public static final TagKey<Biome> SWEET_FRUIT_BIOMES = TagKey.of(RegistryKeys.BIOME, Identifier.of(Fouls.MOD_ID, "sweet_fruit_biomes"));

    public static void registerEvents() {

        //BONEMEAL PALE OAK LEAVES
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            ItemStack stack = player.getStackInHand(hand);

            if (stack.getItem() == Items.BONE_MEAL) {
                BlockPos blockPos = hitResult.getBlockPos();
                BlockState state = world.getBlockState(blockPos);

                if (world.getBlockState(blockPos).getBlock() == Blocks.PALE_OAK_LEAVES) {
                    if (!world.isClient) {

                        boolean persistence = (Boolean) state.get(LeavesBlock.PERSISTENT);
                        boolean waterlogged = (Boolean) state.get(LeavesBlock.WATERLOGGED);
                        world.setBlockState(blockPos, FoulsItems.FLOWERING_PALE_OAK_LEAVES.getDefaultState()
                                .with(PaleOakLeavesBlock.PERSISTENT, persistence)
                                .with(PaleOakLeavesBlock.WATERLOGGED, waterlogged));

                        if (!player.getAbilities().creativeMode) {
                            stack.decrement(1);
                        }
                        world.playSound(null, blockPos, SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        if (world instanceof ServerWorld) {
                            ((ServerWorld) world).spawnParticles(ParticleTypes.HAPPY_VILLAGER,
                                    blockPos.getX() + 0.5,
                                    blockPos.getY() + 0.5,
                                    blockPos.getZ() + 0.5,
                                    10,
                                    0.5,
                                    0.5,
                                    0.5,
                                    0.1);
                        }
                    }
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.PASS;
        });

        //BONEMEAL FLOWERING PALE OAK LEAVES
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            ItemStack stack = player.getStackInHand(hand);
            if (stack.getItem() == Items.BONE_MEAL) {
                BlockPos blockPos = hitResult.getBlockPos();
                BlockState state = world.getBlockState(blockPos);

                var biome = world.getBiome(blockPos);

                if (world.getBlockState(blockPos).getBlock() == FoulsItems.FLOWERING_PALE_OAK_LEAVES) {
                    if (!world.isClient) {
                        boolean persistence = (Boolean) state.get(LeavesBlock.PERSISTENT);
                        boolean waterlogged = (Boolean) state.get(LeavesBlock.WATERLOGGED);

                        if (biome.isIn(SWEET_FRUIT_BIOMES)) {
                            world.setBlockState(blockPos, FoulsItems.SWEET_FRUIT_LEAVES.getDefaultState()
                                    .with(LeavesBlock.PERSISTENT, persistence)
                                    .with(LeavesBlock.WATERLOGGED, waterlogged));
                        } else {
                            world.setBlockState(blockPos, FoulsItems.GREEN_FRUIT_LEAVES.getDefaultState()
                                    .with(LeavesBlock.PERSISTENT, persistence)
                                    .with(LeavesBlock.WATERLOGGED, waterlogged));
                        }


                        if (!player.getAbilities().creativeMode) {
                            stack.decrement(1);
                        }

                        world.playSound(null, blockPos, SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        if (world instanceof ServerWorld) {
                            ((ServerWorld) world).spawnParticles(ParticleTypes.HAPPY_VILLAGER,
                                    blockPos.getX() + 0.5,
                                    blockPos.getY() + 0.5,
                                    blockPos.getZ() + 0.5,
                                    10,
                                    0.5,
                                    0.5,
                                    0.5,
                                    0.1);
                        }
                    }


                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.PASS;
        });

        //GATHER FRUITS
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            ItemStack stack = player.getStackInHand(hand);
            Block block = world.getBlockState(hitResult.getBlockPos()).getBlock();

            if (stack.isEmpty()) {
                if (block == FoulsItems.SWEET_FRUIT_LEAVES ||
                    block == FoulsItems.GREEN_FRUIT_LEAVES) {

                    if( !world.isClient){

                        BlockPos blockPos = hitResult.getBlockPos();
                        BlockState state = world.getBlockState(blockPos);

                        if(world.getBlockState(blockPos).getBlock() == FoulsItems.SWEET_FRUIT_LEAVES) {

                            boolean persistence = (Boolean) state.get(LeavesBlock.PERSISTENT);
                            boolean waterlogged = (Boolean) state.get(LeavesBlock.WATERLOGGED);
                            world.setBlockState(blockPos, FoulsItems.FLOWERING_PALE_OAK_LEAVES
                                    .getDefaultState()
                                    .with(LeavesBlock.PERSISTENT, persistence)
                                    .with(LeavesBlock.WATERLOGGED, waterlogged));

                            world.spawnEntity(new ItemEntity(world, blockPos.getX() + 0.5, blockPos.getY() - 0.5, blockPos.getZ() + 0.5, new ItemStack(FoulsItems.SWEET_FRUIT)));

                        } else if(world.getBlockState(blockPos).getBlock() == FoulsItems.GREEN_FRUIT_LEAVES) {

                            boolean persistence = (Boolean) state.get(LeavesBlock.PERSISTENT);
                            boolean waterlogged = (Boolean) state.get(LeavesBlock.WATERLOGGED);
                            world.setBlockState(blockPos, FoulsItems.FLOWERING_PALE_OAK_LEAVES
                                    .getDefaultState()
                                    .with(LeavesBlock.PERSISTENT, persistence)
                                    .with(LeavesBlock.WATERLOGGED, waterlogged));

                            world.spawnEntity(new ItemEntity(world, blockPos.getX() + 0.5, blockPos.getY() - 0.5, blockPos.getZ() + 0.5, new ItemStack(FoulsItems.GREEN_FRUIT)));

                        }
                        world.playSound(null, blockPos, SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES, SoundCategory.BLOCKS, 1.0F, 1.0F);

                    }
                }
            }

            return ActionResult.PASS;
        });
    }

}
