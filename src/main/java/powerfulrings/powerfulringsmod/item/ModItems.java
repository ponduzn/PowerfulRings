package powerfulrings.powerfulringsmod.item;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import powerfulrings.powerfulringsmod.ModMain;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.effect.StatusEffects;

import java.util.function.Function;

public class ModItems {
    //rings
    public static final Item RING_OF_FLIGHT = register( "ring_of_flight",settings -> new RingOfFlight(settings.maxCount(1)), 
    new Item.Settings());
    public static final Item RING_OF_HASTE = register("ring_of_haste", settings -> new BaseEffectRing(settings.maxCount(1), StatusEffects.HASTE, 2), 
    new Item.Settings());
    public static final Item RING_OF_SPEED = register("ring_of_speed", settings -> new BaseEffectRing(settings.maxCount(1), StatusEffects.SPEED, 1), 
    new Item.Settings());
    public static final Item RING_OF_STRENGTH = register("ring_of_strength", settings -> new BaseEffectRing(settings.maxCount(1), StatusEffects.STRENGTH, 2), 
    new Item.Settings());
    public static final Item RING_OF_RESISTANCE = register("ring_of_resistance", settings -> new BaseEffectRing(settings.maxCount(1), StatusEffects.RESISTANCE, 2), 
    new Item.Settings());
    //armor

    public static final Item PON_ARMOR_CHEST = registerArmor(
        settings -> new Item(settings.armor(ModArmorMaterials.PONERITE_MATERIAL, EquipmentType.CHESTPLATE)), 
        new Item.Settings(), 
        "ponerite_chestplate");

    public static final Item PON_ARMOR_HELM = registerArmor(
        settings -> new Item(settings.armor(ModArmorMaterials.PONERITE_MATERIAL, EquipmentType.HELMET)), 
        new Item.Settings(), 
        "ponerite_helmet");

    public static final Item PON_ARMOR_BOOTS = registerArmor(
        settings -> new Item(settings.armor(ModArmorMaterials.PONERITE_MATERIAL, EquipmentType.BOOTS)), 
        new Item.Settings(), 
        "ponerite_boots");

    public static final Item PON_ARMOR_LEGGINGS = registerArmor(
        settings -> new Item(settings.armor(ModArmorMaterials.PONERITE_MATERIAL, EquipmentType.LEGGINGS)), 
        new Item.Settings(), 
        "ponerite_leggings");

    //tools
    public static final Item PON_PICKAXE = registerTool(
        settings -> new Item(settings.tool(ModToolMaterial.PONERITE_TOOL_MATERIAL, BlockTags.PICKAXE_MINEABLE, 10f, 5f, 0f)), 
        new Item.Settings(), 
        "ponerite_pickaxe");

    public static final Item PON_SWORD = registerTool(
        settings -> new Item(settings.tool(WeaponItemMaterial.PONERITE_TOOL_MATERIAL, BlockTags.PICKAXE_MINEABLE, 10f, 5f, 0f)), 
        new Item.Settings(), 
        "ponerite_sword");


    //ring register
    private static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        Identifier id = Identifier.of(ModMain.MOD_ID, name);
        RegistryKey<Item> RING_KEY = RegistryKey.of(RegistryKeys.ITEM, id);
        Item item = itemFactory.apply(settings.registryKey(RING_KEY));
        Registry.register(Registries.ITEM, RING_KEY, item);
        return item;
    }
    //armor register
    private static Item registerArmor(Function<Item.Settings, Item> factory, Item.Settings settings, String id) {
        Identifier itemID = Identifier.of(ModMain.MOD_ID, id);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, itemID);
        return Registry.register(Registries.ITEM, itemID, factory.apply(settings.registryKey(key)));
    }
    //tool register
    private static Item registerTool(Function<Item.Settings, Item> factory, Item.Settings settings, String id) {
        Identifier itemID = Identifier.of(ModMain.MOD_ID, id);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, itemID);
        return Registry.register(Registries.ITEM, itemID, factory.apply(settings.registryKey(key)));
    }

    //put custom items in certain item groups
    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register((itemGroup) -> itemGroup.add(ModItems.RING_OF_FLIGHT));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register((itemGroup) -> itemGroup.add(ModItems.RING_OF_HASTE));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register((itemGroup) -> itemGroup.add(ModItems.RING_OF_RESISTANCE));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register((itemGroup) -> itemGroup.add(ModItems.RING_OF_SPEED));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register((itemGroup) -> itemGroup.add(ModItems.RING_OF_STRENGTH));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((itemGroup) -> itemGroup.add(ModItems.PON_ARMOR_CHEST));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((itemGroup) -> itemGroup.add(ModItems.PON_ARMOR_HELM));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((itemGroup) -> itemGroup.add(ModItems.PON_ARMOR_BOOTS));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((itemGroup) -> itemGroup.add(ModItems.PON_ARMOR_LEGGINGS));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register((itemGroup) -> itemGroup.add(ModItems.PON_PICKAXE));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((itemGroup) -> itemGroup.add(ModItems.PON_SWORD));
    }
}