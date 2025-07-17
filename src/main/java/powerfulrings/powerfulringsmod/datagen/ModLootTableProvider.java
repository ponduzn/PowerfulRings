package powerfulrings.powerfulringsmod.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import powerfulrings.powerfulringsmod.block.ModBlocks;
import powerfulrings.powerfulringsmod.item.ModItems;

public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.PONERITE_BLOCK);
        addDrop(ModBlocks.PONERITE_ORE, multipleOreDrops(ModBlocks.PONERITE_ORE, ModItems.RAW_PONERITE, 1, 1));
        addDrop(ModBlocks.PONERITE_ORE_DEEPSLATE, multipleOreDrops(ModBlocks.PONERITE_ORE_DEEPSLATE, ModItems.RAW_PONERITE, 1, 2));
        addDrop(ModBlocks.PONERITE_ORE_END, multipleOreDrops(ModBlocks.PONERITE_ORE_END, ModItems.RAW_PONERITE, 2, 5));
        addDrop(ModBlocks.PONERITE_ORE_NETHER, multipleOreDrops(ModBlocks.PONERITE_ORE_NETHER, ModItems.RAW_PONERITE, 1, 3));
    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}
