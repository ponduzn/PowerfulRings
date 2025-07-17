package powerfulrings.powerfulringsmod.world;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import powerfulrings.powerfulringsmod.ModMain;
import powerfulrings.powerfulringsmod.block.ModBlocks;
import net.minecraft.world.gen.feature.*;
import net.minecraft.util.Identifier;

import java.util.List;


public class ModConfiguredFeatures {
    
    public static final RegistryKey<ConfiguredFeature<?, ?>> PONERITE_ORE_KEY = registerKey("ponerite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PONERITE_ORE_END_KEY = registerKey("ponerite_ore_end");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PONERITE_ORE_NETHER_KEY = registerKey("ponerite_ore_nether");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplaceables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplaceables = new BlockMatchRuleTest(Blocks.END_STONE);

        List<OreFeatureConfig.Target> overworldPoneriteOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.PONERITE_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.PONERITE_ORE_DEEPSLATE.getDefaultState()));
        List<OreFeatureConfig.Target> netherPoneriteOres =
                List.of(OreFeatureConfig.createTarget(netherReplaceables, ModBlocks.PONERITE_ORE_NETHER.getDefaultState()));
        List<OreFeatureConfig.Target> endPoneriteOres =
                List.of(OreFeatureConfig.createTarget(endReplaceables, ModBlocks.PONERITE_ORE_END.getDefaultState()));

        register(context, PONERITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldPoneriteOres, 3));
        register(context, PONERITE_ORE_NETHER_KEY, Feature.ORE, new OreFeatureConfig(netherPoneriteOres, 3));
        register(context, PONERITE_ORE_END_KEY, Feature.ORE, new OreFeatureConfig(endPoneriteOres, 4));

    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(ModMain.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
