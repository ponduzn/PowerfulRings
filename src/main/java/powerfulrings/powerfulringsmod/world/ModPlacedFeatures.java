package powerfulrings.powerfulringsmod.world;

import java.util.List;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import powerfulrings.powerfulringsmod.ModMain;
import net.minecraft.util.Identifier;

public class ModPlacedFeatures {

    public static final RegistryKey<PlacedFeature> PONERITE_ORE_PLACED_KEY = registerKey("ponerite_ore_placed");
    public static final RegistryKey<PlacedFeature> PONERITE_ORE_END_PLACED_KEY = registerKey("ponerite_ore_end_placed");
    public static final RegistryKey<PlacedFeature> PONERITE_ORE_NETHER_PLACED_KEY = registerKey("ponerite_ore_nether_placed");
    
    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatures = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, PONERITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PONERITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(9,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-10), YOffset.fixed(10))));
        register(context, PONERITE_ORE_END_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PONERITE_ORE_END_KEY),
                ModOrePlacement.modifiersWithCount(15,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-80), YOffset.fixed(80))));
        register(context, PONERITE_ORE_NETHER_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PONERITE_ORE_NETHER_KEY),
                ModOrePlacement.modifiersWithCount(13,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(30), YOffset.fixed(45))));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(ModMain.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
