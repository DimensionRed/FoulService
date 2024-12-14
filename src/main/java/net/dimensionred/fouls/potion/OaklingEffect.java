package net.dimensionred.fouls.potion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.CreakingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class OaklingEffect extends StatusEffect {

    protected OaklingEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }


    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity player) {
            World currentWorld = entity.getWorld();

            currentWorld.getEntitiesByClass(CreakingEntity.class, player.getBoundingBox().expand(10.0), creaking -> true)
                    .forEach(creaking -> {
                        creaking.setTarget(null);
                        Vec3d awayDirection = creaking.getPos().subtract(player.getPos()).normalize().multiply(10.0);
                        Vec3d targetPosition = creaking.getPos().add(awayDirection);
                        creaking.getNavigation().startMovingTo(targetPosition.x, targetPosition.y, targetPosition.z, 1.5);
                    });
            return true;
        }
        return false;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {

        return true;
    }




}
