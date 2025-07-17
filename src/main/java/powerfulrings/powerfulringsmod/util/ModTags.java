package powerfulrings.powerfulringsmod.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import powerfulrings.powerfulringsmod.ModMain;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_PONERITE_TOOL = createTag("needs_ponerite_tool");
        public static final TagKey<Block> INCORRECT_FOR_PONERITE_TOOL = createTag("incorrect_for_ponerite_tool");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(ModMain.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> PONERITE_REPAIR = createTag("ponerite_repair");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(ModMain.MOD_ID, name));
        }
    }
}