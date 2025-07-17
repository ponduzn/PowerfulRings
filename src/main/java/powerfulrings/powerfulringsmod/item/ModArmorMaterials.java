package powerfulrings.powerfulringsmod.item;

import java.util.EnumMap;

import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import powerfulrings.powerfulringsmod.ModMain;
import powerfulrings.powerfulringsmod.util.ModTags;

public class ModArmorMaterials {

    public static final int BASE_DURABILITY = 500;
    
    public static final RegistryKey<EquipmentAsset> PONERITE_MATERIAL_KEY =
        RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY,
            Identifier.of(ModMain.MOD_ID, "ponerite"));

    public static final ArmorMaterial PONERITE_MATERIAL = new ArmorMaterial(BASE_DURABILITY, Util.make(new EnumMap<>(EquipmentType.class), map -> {
        map.put(EquipmentType.BOOTS, 6);
        map.put(EquipmentType.LEGGINGS, 9);
        map.put(EquipmentType.CHESTPLATE, 10);
        map.put(EquipmentType.HELMET, 8);
        map.put(EquipmentType.BODY, 4);
    }), 
    25, 
    SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 
    5.0F, 
    0.8F, 
    ModTags.Items.PONERITE_REPAIR, 
    PONERITE_MATERIAL_KEY
    );
}
