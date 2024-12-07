package net.dimensionred.fouls.client.particle;

import net.dimensionred.fouls.Fouls;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class FoulsParticleTypes {

    public static final SimpleParticleType PALE_FLOWER_PETALS = FabricParticleTypes.simple();

    private static SimpleParticleType register(String name, SimpleParticleType particle){
        Identifier id = Identifier.of(Fouls.MOD_ID, name);
        return Registry.register(Registries.PARTICLE_TYPE, id, particle);
    }

    public static void setup(){

        register("pale_flower_petals", PALE_FLOWER_PETALS);

    }


}
