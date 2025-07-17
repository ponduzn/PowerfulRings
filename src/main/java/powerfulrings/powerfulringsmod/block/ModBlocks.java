package powerfulrings.powerfulringsmod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import powerfulrings.powerfulringsmod.ModMain;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.function.Function;

public class ModBlocks {

    public static final Block PONERITE_ORE = registerBlock(
        "ponerite_ore",
            properties -> new ExperienceDroppingBlock(UniformIntProvider.create(5, 9),
            properties.strength(3.0F, 3.0F)
            .requiresTool()
            .sounds(BlockSoundGroup.SLIME)
            .instrument(NoteBlockInstrument.BASEDRUM)));

    public static final Block PONERITE_ORE_DEEPSLATE = registerBlock(
        "ponerite_ore_deepslate",
            properties -> new ExperienceDroppingBlock(UniformIntProvider.create(5, 9),
            properties.strength(3.0F, 3.0F)
            .requiresTool()
            .sounds(BlockSoundGroup.SLIME)
            .instrument(NoteBlockInstrument.BASEDRUM)));

    public static final Block PONERITE_ORE_NETHER = registerBlock(
        "ponerite_ore_nether",
            properties -> new ExperienceDroppingBlock(UniformIntProvider.create(5, 9),
            properties.strength(3.0F, 3.0F)
            .requiresTool()
            .sounds(BlockSoundGroup.SLIME)
            .instrument(NoteBlockInstrument.BASEDRUM)));

    public static final Block PONERITE_ORE_END = registerBlock(
        "ponerite_ore_end",
            properties -> new ExperienceDroppingBlock(UniformIntProvider.create(5, 9),
            properties.strength(3.0F, 3.0F)
            .requiresTool()
            .sounds(BlockSoundGroup.SLIME)
            .instrument(NoteBlockInstrument.BASEDRUM)));

    public static final Block PONERITE_BLOCK = registerBlock(
        "ponerite_block",
            properties -> new ExperienceDroppingBlock(UniformIntProvider.create(5, 9),
            properties.strength(3.0F, 3.0F)
            .requiresTool()
            .sounds(BlockSoundGroup.SLIME)
            .instrument(NoteBlockInstrument.BASEDRUM)));

    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function) {
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(ModMain.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(Registries.BLOCK, Identifier.of(ModMain.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(ModMain.MOD_ID, name),
                new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(ModMain.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register((ItemGroup) -> {
            ItemGroup.add(ModBlocks.PONERITE_ORE);
            ItemGroup.add(ModBlocks.PONERITE_ORE_DEEPSLATE);
            ItemGroup.add(ModBlocks.PONERITE_ORE_NETHER);
            ItemGroup.add(ModBlocks.PONERITE_ORE_END);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register((ItemGroup) -> {
            ItemGroup.add(ModBlocks.PONERITE_BLOCK);
        });
    }
}
