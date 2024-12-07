package net.dimensionred.fouls.potion;

import net.dimensionred.fouls.Fouls;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class FoulsPotions {

    public static final RegistryEntry<Potion> NAUSEA = register(
            "nausea", new Potion("nausea", new StatusEffectInstance(StatusEffects.NAUSEA, 800))
    );
    public static final RegistryEntry<Potion> LONG_NAUSEA = register(
            "long_nausea", new Potion("nausea", new StatusEffectInstance(StatusEffects.NAUSEA, 1200))
    );

    private static RegistryEntry<Potion> register(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.ofVanilla(name), potion);
    }

    public static void setup(){
        Fouls.LOGGER.info("Registering effects...");

    }
}
