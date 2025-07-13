package powerfulrings.powerfulringsmod.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

public class ModToolMaterial {
    public static final ToolMaterial PONERITE_TOOL_MATERIAL = new ToolMaterial(
		BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
		5000,
		10.0F,
		4.5F,
		25,
		ItemTags.NETHERITE_TOOL_MATERIALS
);
}
