package powerfulrings.powerfulringsmod.item;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import powerfulrings.powerfulringsmod.ModMain;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.effect.StatusEffects;

import java.util.function.Function;

public class ModItems {
        //rings
        public static final Item RING_OF_FLIGHT = registerItem("ring_of_flight",
            setting -> new RingOfFlight(setting.maxCount(1)));
        public static final Item RING_OF_HASTE = registerItem("ring_of_haste",
            setting -> new BaseEffectRing(setting.maxCount(1), StatusEffects.HASTE, 2));
        public static final Item RING_OF_SPEED = registerItem("ring_of_speed",
            setting -> new BaseEffectRing(setting.maxCount(1), StatusEffects.SPEED, 1));
        public static final Item RING_OF_STRENGTH = registerItem("ring_of_strength",
            setting -> new BaseEffectRing(setting.maxCount(1), StatusEffects.STRENGTH, 2));
        public static final Item RING_OF_RESISTANCE = registerItem("ring_of_resistance",
            setting -> new BaseEffectRing(setting.maxCount(1), StatusEffects.RESISTANCE, 2));

        //armor
        public static final Item PON_ARMOR_CHEST = registerItem("ponerite_chestplate",
            setting -> new Item(setting.armor(ModArmorMaterials.PONERITE_MATERIAL, EquipmentType.CHESTPLATE)));
        public static final Item PON_ARMOR_HELM = registerItem("ponerite_helmet",
            setting -> new Item(setting.armor(ModArmorMaterials.PONERITE_MATERIAL, EquipmentType.HELMET)));
        public static final Item PON_ARMOR_BOOTS = registerItem("ponerite_boots",
            setting -> new Item(setting.armor(ModArmorMaterials.PONERITE_MATERIAL, EquipmentType.BOOTS)));
        public static final Item PON_ARMOR_LEGGINGS = registerItem("ponerite_leggings",
            setting -> new Item(setting.armor(ModArmorMaterials.PONERITE_MATERIAL, EquipmentType.LEGGINGS)));

        //weps
        public static final Item PON_SWORD = registerItem("ponerite_sword",
            setting -> new Item(setting.sword(WeaponItemMaterial.PONERITE_TOOL_MATERIAL, 10.0F, 12.0F)));

        //tools
        public static final Item PON_PICKAXE = registerItem("ponerite_pickaxe",
            setting -> new Item(setting.pickaxe(ModToolMaterial.PONERITE_TOOL_MATERIAL, 12.0F, 3.0F)));
        public static final Item PON_AXE = registerItem("ponerite_axe",
            setting -> new AxeItem(ModToolMaterial.PONERITE_TOOL_MATERIAL, 12.0F, 9.0F, setting));
        public static final Item PON_SHOVEL = registerItem("ponerite_shovel",
            setting -> new ShovelItem(ModToolMaterial.PONERITE_TOOL_MATERIAL, 2.0F, 1.0F, setting));
        public static final Item PON_HOE = registerItem("ponerite_hoe",
            setting -> new HoeItem(ModToolMaterial.PONERITE_TOOL_MATERIAL, 2.0F, 1.0F, setting));

        //Items
        public static final Item PONERITE_INGOT = registerItem("ponerite_ingot", Item::new);
        public static final Item RAW_PONERITE = registerItem("raw_ponerite", Item::new);

        //item register
        private static Item registerItem(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(ModMain.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(ModMain.MOD_ID, name)))));
        }

        public static void registerModItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(RING_OF_FLIGHT);
            entries.add(RING_OF_HASTE);
            entries.add(RING_OF_RESISTANCE);
            entries.add(RING_OF_SPEED);
            entries.add(RING_OF_STRENGTH);
            entries.add(PON_PICKAXE);
            entries.add(PON_AXE);
            entries.add(PON_HOE);
            entries.add(PON_SHOVEL);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(PON_ARMOR_BOOTS);
            entries.add(PON_ARMOR_CHEST);
            entries.add(PON_ARMOR_HELM);
            entries.add(PON_ARMOR_LEGGINGS);
            entries.add(PON_SWORD);
        });  
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(PONERITE_INGOT);
            entries.add(RAW_PONERITE);
        });  
    }
}