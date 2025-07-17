package powerfulrings.powerfulringsmod.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.BlockTags;
import powerfulrings.powerfulringsmod.block.ModBlocks;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(BlockTags.PICKAXE_MINEABLE)
            .add(ModBlocks.PONERITE_BLOCK)
            .add(ModBlocks.PONERITE_ORE)
            .add(ModBlocks.PONERITE_ORE_DEEPSLATE)
            .add(ModBlocks.PONERITE_ORE_NETHER)
            .add(ModBlocks.PONERITE_ORE_END);

        valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
            .add(ModBlocks.PONERITE_ORE)
            .add(ModBlocks.PONERITE_ORE_DEEPSLATE)
            .add(ModBlocks.PONERITE_ORE_NETHER)
            .add(ModBlocks.PONERITE_ORE_END);
    }
    
}
