package powerfulrings.powerfulringsmod;

import net.fabricmc.api.ModInitializer;
import powerfulrings.powerfulringsmod.item.ModItems;

public class ModMain implements ModInitializer {
    public static final String MOD_ID = "powerfulrings";

    @Override
    public void onInitialize() {
        ModItems.initialize();
    }
}