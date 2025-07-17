package powerfulrings.powerfulringsmod.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import powerfulrings.powerfulringsmod.block.ModBlocks;
import powerfulrings.powerfulringsmod.item.ModItems;


import java.util.List;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public String getName() {
        return "TutorialMod Recipes";
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                List<ItemConvertible> PONERITE_SMELTABLES = List.of(ModItems.RAW_PONERITE, ModBlocks.PONERITE_ORE, ModItems.PON_ARMOR_BOOTS, ModItems.PON_ARMOR_CHEST, ModItems.PON_ARMOR_HELM, ModItems.PON_ARMOR_LEGGINGS);

                offerSmelting(PONERITE_SMELTABLES, RecipeCategory.MISC, ModItems.PONERITE_INGOT, 0.25f, 200, "ponerite_ingot");
            }
        };
    }  
}
