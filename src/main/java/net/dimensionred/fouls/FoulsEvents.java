package net.dimensionred.fouls;

import net.dimensionred.fouls.block.FloweringPaleOakLeaves;
import net.dimensionred.fouls.item.FoulsItems;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

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

    public static void registerEvents() {

        //BONEMEAL PALE OAK LEAVES
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            ItemStack stack = player.getStackInHand(hand);

            if (stack.getItem() == Items.BONE_MEAL) {
                BlockPos blockPos = hitResult.getBlockPos();
                BlockState state = world.getBlockState(blockPos);

                if (world.getBlockState(blockPos).getBlock() == Blocks.PALE_OAK_LEAVES) {
                    if (!world.isClient) {

                        boolean persistence = state.get(LeavesBlock.PERSISTENT);
                        boolean waterlogged = state.get(LeavesBlock.WATERLOGGED);
                        world.setBlockState(blockPos, FoulsItems.FLOWERING_PALE_OAK_LEAVES.getDefaultState()
                                .with(LeavesBlock.PERSISTENT, persistence)
                                .with(LeavesBlock.WATERLOGGED, waterlogged)
                                .with(FloweringPaleOakLeaves.AGE, 0)
                        );

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

    }

}
