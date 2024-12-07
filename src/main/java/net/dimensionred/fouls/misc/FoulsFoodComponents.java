package net.dimensionred.fouls.misc;

import com.mojang.datafixers.types.templates.CompoundList;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.item.consume.ConsumeEffect;
import net.minecraft.item.consume.UseAction;
import net.minecraft.registry.tag.TagGroupLoader;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import java.util.Random;

public class FoulsFoodComponents {


    public static final FoodComponent SWEET_FRUIT = new FoodComponent.Builder()
            .nutrition(4)
            .saturationModifier(0.3F)
            .build();
    public static final FoodComponent SWEET_PIE = new FoodComponent.Builder()
            .nutrition(6)
            .saturationModifier(0.6F)
            .build();
    public static final FoodComponent GREEN_FRUIT = new FoodComponent.Builder()
            .nutrition(4)
            .saturationModifier(0.3F)
            .build();


    public static final ConsumableComponent SWEET_FRUIT_EFFECT = food()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80, 0)))
            .build();
    public static final ConsumableComponent SWEET_PIE_EFFECT = food()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 80, 0)))
            .build();
    public static final ConsumableComponent GREEN_FRUIT_EFFECT = food()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 0)))
            .build();

    public static ConsumableComponent.Builder food() {
        return ConsumableComponent.builder()
                .consumeSeconds(1.6F)
                .useAction(UseAction.EAT)
                .sound(SoundEvents.ENTITY_GENERIC_EAT)
                .consumeParticles(true);
    }




}
