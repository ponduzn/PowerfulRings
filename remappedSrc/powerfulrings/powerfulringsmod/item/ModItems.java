package powerfulrings.powerfulringsmod.item;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import powerfulrings.powerfulringsmod.ModMain;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.entity.effect.StatusEffects;

import java.util.function.Function;

public class ModItems {
    public static final Item RING_OF_FLIGHT = register(
            "ring_of_flight",
            settings -> new RingOfFlight(settings.maxCount(1)),
            new Item.Settings()
    );

    public static final Item RING_OF_HASTE = register(
            "ring_of_haste",
            settings -> new BaseEffectRing(settings.maxCount(1), StatusEffects.HASTE.value(), 1),
            new Item.Settings()
    );

    public static final Item RING_OF_SPEED = register(
            "ring_of_speed",
            settings -> new BaseEffectRing(settings.maxCount(1), StatusEffects.SPEED.value(), 1),
            new Item.Settings()
    );

    public static final Item RING_OF_STRENGTH = register(
            "ring_of_strength",
            settings -> new BaseEffectRing(settings.maxCount(1), StatusEffects.STRENGTH.value(), 1),
            new Item.Settings()
    );

    public static final Item RING_OF_RESISTANCE = register(
            "ring_of_resistance",
            settings -> new BaseEffectRing(settings.maxCount(1), StatusEffects.RESISTANCE.value(), 0),
            new Item.Settings()
    );

    private static Item register(String name,
                                 Function<Item.Settings, Item> factory,
                                 Item.Settings settings) {
        Identifier id = Identifier.of(ModMain.MOD_ID, name);
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        Item item = factory.apply(settings.registryKey(key));
        return Registry.register(Registries.ITEM, key, item);
    }

    public static void initialize() {
        // Trigger static init
    }
}