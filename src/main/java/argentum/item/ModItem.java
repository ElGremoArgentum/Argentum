package argentum.item;

import argentum.Argentum;
import argentum.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import argentum.item.ModItemGroups;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ModItem {
    public static final Item CALABAZA_MATE = registerItem("calabaza_mate", new Item(new Item.Settings()));

    public static final Item TERMO = registerItem("termo", new TermoItem(new Item.Settings().maxCount(1)));
    public static final Item TERMO_ARGENTO = registerItem("termo_argento", new TermoItem(new Item.Settings().maxCount(1)));

    public static final Item BOMBILLA = registerItem("bombilla", new Item(new Item.Settings()));

    public static final Item MATE = registerItem("mate", new MateItem(new Item.Settings()
            .food(ModFoodComponent.MATE)
            .maxCount(1)));
    public static final Item MATE_LISTO_BLANCO = registerItem("mate_listo_blanco", new MateListoItem(new Item.Settings()
            .food(ModFoodComponent.MATELISTO)  // si querés que sea comestible igual que el mate
            .maxCount(1)
            .maxDamage(16)
    ));

    public static final Item YERBA = registerItem("yerba", new Item(new Item.Settings()));
    public static final Item YERBA_PLANTA = registerItem("yerba_planta", new Item(new Item.Settings()));
    public static final Item YERBA_SEMILLA = registerItem("yerba_semilla",
            new AliasedBlockItem(ModBlocks.YERBA_PLANTA, new Item.Settings()));

    // Palos y números de cartas
    private static final String[] PALOS = {"espada", "palo", "copa", "oro"};
    private static final int[] NUMEROS = {1, 2, 3, 4, 5, 6, 7, 10, 11, 12};


    // Array para guardar las cartas si querés acceder después
    public static final Item[][] CARTAS = new Item[PALOS.length][NUMEROS.length];

    // Declaración del grupo creativo (se inicializa después)
    public static ItemGroup CARTAS_GROUP;

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Argentum.MOD_ID, name), item);
    }



    public static void registerModItem() {
        Argentum.LOGGER.info("Registering Mod Item for " + Argentum.MOD_ID);

        // Ingredientes (como antes)
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(CALABAZA_MATE);
            entries.add(TERMO);
            entries.add(BOMBILLA);
            entries.add(YERBA);
            entries.add(MATE);
            entries.add(TERMO_ARGENTO);
        });

        // Registrar todas las cartas
        for (int i = 0; i < PALOS.length; i++) {
            for (int j = 0; j < NUMEROS.length; j++) {
                String palo = PALOS[i];
                int numero = NUMEROS[j];
                String nombre = "carta_" + palo + "_" + numero;
                Item carta = registerItem(nombre, new Item(new Item.Settings()));
                CARTAS[i][j] = carta;
            }
        }

        // Ahora que las cartas están registradas, creamos el grupo creativo
        CARTAS_GROUP = Registry.register(
                Registries.ITEM_GROUP,
                Identifier.of(Argentum.MOD_ID, "cartas"),
                FabricItemGroup.builder()
                        .displayName(Text.translatable("itemGroup.argentum.cartas"))
                        .icon(() -> new ItemStack(Registries.ITEM.get(Identifier.of(Argentum.MOD_ID, "carta_espada_1"))))
                        .entries((context, entries) -> {
                            for (int i = 0; i < PALOS.length; i++) {
                                for (int j = 0; j < NUMEROS.length; j++) {
                                    String palo = PALOS[i];
                                    int numero = NUMEROS[j];
                                    String nombre = "carta_" + palo + "_" + numero;
                                    entries.add(Registries.ITEM.get(Identifier.of(Argentum.MOD_ID, nombre)));
                                }
                            }
                        })
                        .build()
        );
    }
}