package net.dimensionred.fouls.potion;

import com.mojang.serialization.Codec;
import net.dimensionred.fouls.Fouls;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class FoulsStatusEffects {

    public static final RegistryEntry<StatusEffect> OAKLING = Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Fouls.MOD_ID, "oakling"), new OaklingEffect(StatusEffectCategory.BENEFICIAL, 6050643 ));
    public static final RegistryEntry<StatusEffect> LONG_OAKLING = Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Fouls.MOD_ID, "long_oakling"), new OaklingEffect(StatusEffectCategory.BENEFICIAL, 6050643 ));


    public static void setup(){
        Fouls.LOGGER.info("Registering effects");
    }



}
