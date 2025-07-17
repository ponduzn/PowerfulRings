package powerfulrings.powerfulringsmod.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import powerfulrings.powerfulringsmod.util.ModTags;

public class WeaponItemMaterial {
    public static final ToolMaterial PONERITE_TOOL_MATERIAL = new ToolMaterial(
		BlockTags.SWORD_EFFICIENT,
		5000,
		1.0F,
		5.5F,
		25,
		ModTags.Items.PONERITE_REPAIR
);
}
