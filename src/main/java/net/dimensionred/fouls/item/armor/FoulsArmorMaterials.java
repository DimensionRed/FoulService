package net.dimensionred.fouls.item.armor;

import net.dimensionred.fouls.item.FoulsItemTags;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.Map;

public class FoulsArmorMaterials {

    public static final ArmorMaterial FLOWER = new ArmorMaterial(5, (Map) Util.make(new EnumMap(EquipmentType.class), (map) -> {
        map.put(EquipmentType.BOOTS, 0);
        map.put(EquipmentType.LEGGINGS, 0);
        map.put(EquipmentType.CHESTPLATE, 0);
        map.put(EquipmentType.HELMET, 0);
        map.put(EquipmentType.BODY, 0);
    }), 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, FoulsItemTags.REPAIRS_FLOWER_HEADPIECE, FoulsEquipmentAssetKeys.FLOWER);


}
