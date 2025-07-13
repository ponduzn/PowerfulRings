package powerfulrings.powerfulringsmod.item;

import java.util.Map;

import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import powerfulrings.powerfulringsmod.ModMain;

public class PoneriteArmorMaterial {
    public static final int BASE_DURABILITY = 50;

    public static final RegistryKey<EquipmentAsset> PONERITE_MATERIAL_KEY =
        RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY,
            Identifier.of(ModMain.MOD_ID, "ponerite"));

    public static final ArmorMaterial INSTANCE = new ArmorMaterial(
		BASE_DURABILITY,
		Map.of(
				EquipmentType.HELMET, 8,
				EquipmentType.CHESTPLATE, 10,
				EquipmentType.LEGGINGS, 9,
				EquipmentType.BOOTS, 6
		),
		5, //enchantability
		SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
		5.0F, //toughness
		0.6F, //knockback resist
		ItemTags.NETHERITE_TOOL_MATERIALS,
		PONERITE_MATERIAL_KEY
);
}
