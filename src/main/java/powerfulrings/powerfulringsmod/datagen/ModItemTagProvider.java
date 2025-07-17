package powerfulrings.powerfulringsmod.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.ItemTagProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import powerfulrings.powerfulringsmod.item.ModItems;
import powerfulrings.powerfulringsmod.util.ModTags;

public class ModItemTagProvider extends ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(WrapperLookup wrapperLookup) {
        valueLookupBuilder(ModTags.Items.PONERITE_REPAIR)
            .add(ModItems.PON_AXE)
            .add(ModItems.PON_HOE)
            .add(ModItems.PON_PICKAXE)
            .add(ModItems.PON_SHOVEL)
            .add(ModItems.PON_SWORD);

        valueLookupBuilder(ModTags.Items.PONERITE_REPAIR) 
            .add(ModItems.PONERITE_INGOT);
        

        valueLookupBuilder(ModTags.Items.PONERITE_REPAIR)
            .add(ModItems.PON_ARMOR_BOOTS)
            .add(ModItems.PON_ARMOR_CHEST)
            .add(ModItems.PON_ARMOR_HELM)
            .add(ModItems.PON_ARMOR_LEGGINGS);
    }
}
