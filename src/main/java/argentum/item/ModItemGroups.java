package argentum.item;

import argentum.Argentum;
import argentum.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static ItemGroup TERMO; // dejamos el nombre de variable, pero el tab se muestra como "Argentum"
    public static ItemGroup UNO;

    public static void registerItemsGroups() {
        Argentum.LOGGER.info("Registering Mod Item Groups for " + Argentum.MOD_ID);

        // Renombrado: el displayName ahora es "Argentum"
        // (Si preferís translatable, ver notas de lang más abajo)
        TERMO = Registry.register(Registries.ITEM_GROUP,
                Identifier.of(Argentum.MOD_ID, "argentum"), // antes "termo"
                FabricItemGroup.builder()
                        .icon(() -> new ItemStack(ModItem.TERMO))
                        .displayName(Text.literal("Argentum")) // <- nombre visible del tab
                        .entries((displayContext, entries) -> {
                            // === Items mates/termos/comidas ===
                            entries.add(ModItem.TERMO);
                            entries.add(ModItem.BOMBILLA);
                            entries.add(ModItem.YERBA);
                            entries.add(ModItem.CALABAZA_MATE);
                            entries.add(ModItem.MATE);
                            entries.add(ModItem.TERMO_ARGENTO);
                            entries.add(ModItem.YERBA_SEMILLA);
                            entries.add(ModItem.TE_SEMILLA);

                            entries.add(ModItem.TAZA_DE_ARCILLA);
                            entries.add(ModItem.TAZA);
                            entries.add(ModItem.SAQUITO_TE);
                            entries.add(ModItem.SAQUITO_MATECOCIDO);
                            entries.add(ModItem.TAZA_DE_TE);
                            entries.add(ModItem.TAZA_DE_MATECOCIDO);
                            entries.add(ModItem.TE);

                            entries.add(ModItem.ACEITE);
                            entries.add(ModItem.HARINA);
                            entries.add(ModItem.MANTECA);
                            entries.add(ModItem.LECHE);
                            entries.add(ModItem.MEDIALUNA_CRUDA);
                            entries.add(ModItem.MEDIALUNA_COCINADA);

                            entries.add(ModItem.EMPANADA_CRUDA);
                            entries.add(ModItem.EMPANADA_FRITA);
                            entries.add(ModItem.TORTAFRITA_CRUDA);
                            entries.add(ModItem.TORTAFRITA);
                            entries.add(ModItem.PASTELITO_MEMBRILLO_CRUDO);
                            entries.add(ModItem.PASTELITO_MEMBRILLO_FRITO);
                            entries.add(ModItem.PASTELITO_BATATA_CRUDO);
                            entries.add(ModItem.PASTELITO_BATATA_FRITO);
                            entries.add(ModItem.CHURRO_CRUDO);
                            entries.add(ModItem.CHURRO_FRITO);
                            entries.add(ModItem.CHURRO_FRITO_DULCE);
                            entries.add(ModItem.MILANESA_CRUDA);
                            entries.add(ModItem.MILANESA_FRITA);

                            entries.add(ModItem.MATE_LISTO_BLANCO);
                            entries.add(ModItem.MATE_LISTO_NARANJA);
                            entries.add(ModItem.MATE_LISTO_MARRON);
                            entries.add(ModItem.MATE_LISTO_MAGENTA);
                            entries.add(ModItem.MATE_LISTO_ROJO);
                            entries.add(ModItem.MATE_LISTO_VIOLETA);
                            entries.add(ModItem.MATE_LISTO_ROSA);
                            entries.add(ModItem.MATE_LISTO_VERDE2);
                            entries.add(ModItem.MATE_LISTO_VERDE1);
                            entries.add(ModItem.MATE_LISTO_NEGRO);
                            entries.add(ModItem.MATE_LISTO_AMARILLO);
                            entries.add(ModItem.MATE_LISTO_GRIS1);
                            entries.add(ModItem.MATE_LISTO_GRIS2);
                            entries.add(ModItem.MATE_LISTO_AZUL);
                            entries.add(ModItem.MATE_LISTO_CELESTE);
                            entries.add(ModItem.MATE_LISTO_CYAN);
                            entries.add(ModItem.MATE_LISTO_ARGENTO);

                        })
                        .build()

        );

        UNO = Registry.register(Registries.ITEM_GROUP,
                Identifier.of(Argentum.MOD_ID, "uno"),
                FabricItemGroup.builder()
                        .icon(() -> new ItemStack(ModBlocks.UNO))
                        .displayName(Text.translatable("itemGroup.argentum.uno"))
                        .entries((displayContext, entries) -> {
                            entries.add(ModBlocks.UNO);
                            entries.add(ModBlocks.DOS);
                            entries.add(ModBlocks.TRES);
                            entries.add(ModBlocks.CUATRO);
                            entries.add(ModBlocks.CINCO);
                            entries.add(ModBlocks.SEIS);
                            entries.add(ModItem.FICHA_CASINO_1);
                            entries.add(ModItem.FICHA_CASINO_5);
                            entries.add(ModItem.FICHA_CASINO_32);
                            entries.add(ModItem.FICHA_CASINO_64);
                            entries.add(ModItem.FICHA_CASINO_ESPECIAL);
                            entries.add(ModItem.FICHA_CASINO_OTRA);
                            entries.add(ModItem.CUADRO_ARGENTO);
                        })
                        .build()
        );
    }
}
