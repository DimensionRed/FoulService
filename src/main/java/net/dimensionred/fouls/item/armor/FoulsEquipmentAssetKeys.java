package net.dimensionred.fouls.item.armor;

import net.dimensionred.fouls.Fouls;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class FoulsEquipmentAssetKeys {

    private static final RegistryKey<? extends Registry<EquipmentAsset>> REGISTRY_KEY = RegistryKey.ofRegistry(Identifier.of(Fouls.MOD_ID,"equipment_asset"));

    public static RegistryKey<EquipmentAsset> FLOWER = register("flower");

    private static  RegistryKey<EquipmentAsset> register(String name) {
        return RegistryKey.of(REGISTRY_KEY, Identifier.of(Fouls.MOD_ID, name));
    }

    public static void setup(){

    }
}
