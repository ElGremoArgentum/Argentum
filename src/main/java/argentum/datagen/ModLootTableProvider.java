package argentum.datagen;

import argentum.block.ModBlocks;
import argentum.block.custom.Yerba_Planta;
import argentum.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
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
        // Creamos la condición de que la planta esté madura (edad 5)
        BlockStatePropertyLootCondition.Builder condition = BlockStatePropertyLootCondition.builder(ModBlocks.YERBA_PLANTA)
                .properties(StatePredicate.Builder.create().exactMatch(Yerba_Planta.AGE, 5));

        // Agregamos el loot para el bloque Yerba_Planta
        this.addDrop(ModBlocks.YERBA_PLANTA, yerbaConFortuna(ModBlocks.YERBA_PLANTA, ModItem.YERBA, ModItem.YERBA_SEMILLA, condition));
    }

    // Esta función genera el loot de yerba y yerba_semilla, aplicando el efecto de Fortuna si está presente
    public LootTable.Builder yerbaConFortuna(Block crop, Item dropItem, Item seedItem, BlockStatePropertyLootCondition.Builder condition) {
        // Obtenemos la referencia al encantamiento Fortuna
        RegistryWrapper.Impl<Enchantment> enchantments = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

        // Creamos el LootPool para las gotas de yerba
        LootPool.Builder drops = LootPool.builder()
                .conditionally(condition.build())  // Aplica la condición de que la planta esté madura
                .with(ItemEntry.builder(dropItem)  // Añade el ítem de yerba
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 3.0f))) // Establece la cantidad de yerba que se dropea
                        .apply(ApplyBonusLootFunction.oreDrops(enchantments.getOrThrow(Enchantments.FORTUNE))) // Aplica el encantamiento de Fortuna
                );

        // Creamos el LootPool para las semillas
        LootPool.Builder seeds = LootPool.builder()
                .conditionally(condition.build())  // Aplica la misma condición de madurez
                .with(ItemEntry.builder(seedItem));  // Añade el ítem de yerba_semilla

        // Devolvemos el LootTable con ambos pools: uno para las gotas de yerba y otro para las semillas
        return LootTable.builder().pool(drops).pool(seeds);
    }
}
