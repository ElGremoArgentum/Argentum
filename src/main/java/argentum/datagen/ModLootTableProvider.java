package argentum.datagen;

import argentum.block.ModBlocks;
import argentum.block.custom.Batata_Planta;
import argentum.block.custom.Membrillo_Planta;
import argentum.block.custom.Te_Planta;
import argentum.block.custom.Yerba_Planta;
import argentum.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        // Té
        BlockStatePropertyLootCondition.Builder builderTe =
                BlockStatePropertyLootCondition.builder(ModBlocks.TE_PLANTA)
                        .properties(StatePredicate.Builder.create()
                                .exactMatch(Te_Planta.AGE, Te_Planta.MAX_AGE));

        // Yerba
        BlockStatePropertyLootCondition.Builder builderYerba =
                BlockStatePropertyLootCondition.builder(ModBlocks.YERBA_PLANTA)
                        .properties(StatePredicate.Builder.create()
                                .exactMatch(Yerba_Planta.AGE, Yerba_Planta.MAX_AGE));

        // Membrillo
        BlockStatePropertyLootCondition.Builder builderMembri =
                BlockStatePropertyLootCondition.builder(ModBlocks.MEMBRILLO_PLANTA)
                        .properties(StatePredicate.Builder.create()
                                .exactMatch(Membrillo_Planta.AGE, Membrillo_Planta.MAX_AGE));

        // Batata (¡ojo! usar Batata_Planta, no Membrillo)
        BlockStatePropertyLootCondition.Builder builderBata =
                BlockStatePropertyLootCondition.builder(ModBlocks.BATATA_PLANTA)
                        .properties(StatePredicate.Builder.create()
                                .exactMatch(Batata_Planta.AGE, Batata_Planta.MAX_AGE));

        // Drops de cultivos
        this.addDrop(ModBlocks.YERBA_PLANTA,
                this.cropDrops(ModBlocks.YERBA_PLANTA, ModItem.YERBA, ModItem.YERBA_SEMILLA, builderYerba));

        this.addDrop(ModBlocks.TE_PLANTA,
                this.cropDrops(ModBlocks.TE_PLANTA, ModItem.TE, ModItem.TE_SEMILLA, builderTe));

        this.addDrop(ModBlocks.MEMBRILLO_PLANTA,
                this.cropDrops(ModBlocks.MEMBRILLO_PLANTA, ModItem.MEMBRILLO, ModItem.MEMBRILLO_SEMILLA, builderMembri));

        // Batata: el propio ítem es la “semilla”
        this.addDrop(ModBlocks.BATATA_PLANTA,
                this.cropDrops(ModBlocks.BATATA_PLANTA, ModItem.BATATA, ModItem.BATATA, builderBata));
    }

    // (Opcional) helper para menas; no se usa en cultivos, pero lo dejo compilar OK.
    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}
