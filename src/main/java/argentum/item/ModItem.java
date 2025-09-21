package argentum.item;

import argentum.Argentum;
import argentum.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class ModItem {


    public static final Item CALABAZA_MATE = registerItem("calabaza_mate", new Item(new Item.Settings()));

    public static final Item TERMO = registerItem("termo", new TermoItem(new Item.Settings().maxCount(1)));
    public static final Item TERMO_ARGENTO = registerItem("termo_argento", new TermoItem(new Item.Settings().maxCount(1)));

    public static final Item BOMBILLA = registerItem("bombilla", new Item(new Item.Settings()));

    public static final Item MATE = registerItem("mate", new MateItem(new Item.Settings()
            .food(ModFoodComponent.MATE)
            .maxCount(1)));

    public static final Item TAZA_DE_ARCILLA = registerItem("taza_de_arcilla", new Item(new Item.Settings().maxCount(64)));
    public static final Item TAZA = registerItem("taza", new Item(new Item.Settings().maxCount(64)));
    public static final Item SAQUITO_TE = registerItem("saquito_te", new Item(new Item.Settings().maxCount(64)));
    public static final Item SAQUITO_MATECOCIDO = registerItem("saquito_matecocido", new Item(new Item.Settings().maxCount(64)));
    public static final Item TAZA_DE_TE = registerItem("taza_de_te", new Infusion(new Item.Settings()
            .food(ModFoodComponent.INFUSION)
            .maxCount(1)));
    public static final Item TAZA_DE_MATECOCIDO = registerItem("taza_de_matecocido", new Infusion(new Item.Settings()
            .food(ModFoodComponent.INFUSION)
            .maxCount(1)));

    public static final Item MATE_LISTO_BLANCO = registerItem("mate_listo_blanco", new MateListoItem(new Item.Settings()
            .food(ModFoodComponent.MATELISTO)
            .maxCount(1)));
    public static final Item MATE_LISTO_NARANJA = registerItem("mate_listo_naranja", new MateListoItem(new Item.Settings()
            .food(ModFoodComponent.MATELISTO)
            .maxCount(1)));
    public static final Item MATE_LISTO_MARRON = registerItem("mate_listo_marron", new MateListoItem(new Item.Settings()
            .food(ModFoodComponent.MATELISTO)
            .maxCount(1)));
    public static final Item MATE_LISTO_MAGENTA = registerItem("mate_listo_magenta", new MateListoItem(new Item.Settings()
            .food(ModFoodComponent.MATELISTO)
            .maxCount(1)));
    public static final Item MATE_LISTO_ROJO = registerItem("mate_listo_rojo", new MateListoItem(new Item.Settings()
            .food(ModFoodComponent.MATELISTO)
            .maxCount(1)));
    public static final Item MATE_LISTO_VIOLETA = registerItem("mate_listo_violeta", new MateListoItem(new Item.Settings()
            .food(ModFoodComponent.MATELISTO)
            .maxCount(1)));
    public static final Item MATE_LISTO_ROSA = registerItem("mate_listo_rosa", new MateListoItem(new Item.Settings()
            .food(ModFoodComponent.MATELISTO)
            .maxCount(1)));
    public static final Item MATE_LISTO_VERDE2 = registerItem("mate_listo_verde2", new MateListoItem(new Item.Settings()
            .food(ModFoodComponent.MATELISTO)
            .maxCount(1)));
    public static final Item MATE_LISTO_VERDE1 = registerItem("mate_listo_verde1", new MateListoItem(new Item.Settings()
            .food(ModFoodComponent.MATELISTO)
            .maxCount(1)));
    public static final Item MATE_LISTO_NEGRO = registerItem("mate_listo_negro", new MateListoItem(new Item.Settings()
            .food(ModFoodComponent.MATELISTO)
            .maxCount(1)));
    public static final Item MATE_LISTO_AMARILLO = registerItem("mate_listo_amarillo", new MateListoItem(new Item.Settings()
            .food(ModFoodComponent.MATELISTO)
            .maxCount(1)));
    public static final Item MATE_LISTO_GRIS1 = registerItem("mate_listo_gris1", new MateListoItem(new Item.Settings()
            .food(ModFoodComponent.MATELISTO)
            .maxCount(1)));
    public static final Item MATE_LISTO_GRIS2 = registerItem("mate_listo_gris2", new MateListoItem(new Item.Settings()
            .food(ModFoodComponent.MATELISTO)
            .maxCount(1)));
    public static final Item MATE_LISTO_AZUL = registerItem("mate_listo_azul", new MateListoItem(new Item.Settings()
            .food(ModFoodComponent.MATELISTO)
            .maxCount(1)));
    public static final Item MATE_LISTO_CELESTE = registerItem("mate_listo_celeste", new MateListoItem(new Item.Settings()
            .food(ModFoodComponent.MATELISTO)
            .maxCount(1)));
    public static final Item MATE_LISTO_CYAN = registerItem("mate_listo_cyan", new MateListoItem(new Item.Settings()
            .food(ModFoodComponent.MATELISTO)
            .maxCount(1)));
    public static final Item MATE_LISTO_ARGENTO = registerItem("mate_listo_argento", new MateListoItem(new Item.Settings()
            .food(ModFoodComponent.MATELISTO)
            .maxCount(1)
    ));

    public static final Item GRASA = Registry.register(
            Registries.ITEM, Identifier.of(Argentum.MOD_ID, "grasa"),
            new Item(new Item.Settings())
    );

    public static final Item CUCHILLO = Registry.register(
            Registries.ITEM, Identifier.of(Argentum.MOD_ID, "cuchillo"),
            new CuchilloItem(new Item.Settings())
    );

    // Crudos
    public static final Item BIFE_CRUDO = Registry.register(
            Registries.ITEM, Identifier.of(Argentum.MOD_ID, "bife_crudo"),
            new BifeItem(new Item.Settings().food(ModFoodComponent.CARNE_CRUDA))
    );

    public static final Item CARNE_CORTADA_CRUDA = Registry.register(
            Registries.ITEM, Identifier.of(Argentum.MOD_ID, "carne_cortada_cruda"),
            new Item(new Item.Settings().food(ModFoodComponent.CARNE_CRUDA))
    );





    public static final Item FICHA_CASINO_5 = registerItem("ficha_casino_5", new Item(new Item.Settings()));
    public static final Item FICHA_CASINO_32 = registerItem("ficha_casino_32", new Item(new Item.Settings()));
    public static final Item FICHA_CASINO_64 = registerItem("ficha_casino_64", new Item(new Item.Settings()));
    public static final Item FICHA_CASINO_ESPECIAL = registerItem("ficha_casino_especial", new Item(new Item.Settings()));
    public static final Item FICHA_CASINO_OTRA = registerItem("ficha_casino_otra", new Item(new Item.Settings()));
    public static final Item FICHA_CASINO_1 = registerItem("ficha_casino_1", new Item(new Item.Settings()));

    public static final Item YERBA = registerItem("yerba", new Item(new Item.Settings()));
    public static final Item YERBA_SEMILLA = Registry.register(Registries.ITEM,
            Identifier.of("argentum", "yerba_semilla"),
            new AliasedBlockItem(ModBlocks.YERBA_PLANTA, new Item.Settings()));

    public static final Item TE = registerItem("te", new Item(new Item.Settings()));
    public static final Item TE_SEMILLA = Registry.register(Registries.ITEM,
            Identifier.of("argentum", "te_semilla"),
            new AliasedBlockItem(ModBlocks.TE_PLANTA, new Item.Settings()));

    public static final Item ACEITE = Registry.register(
            Registries.ITEM, Identifier.of("argentum", "aceite"),
            new Item(new Item.Settings().maxCount(16).recipeRemainder(Items.GLASS_BOTTLE)));
    public static final Item HARINA = registerItem("harina", new Item(new Item.Settings()));
    public static final Item MANTECA = registerItem("manteca", new Item(new Item.Settings()));
    public static final Item DULCE_LECHE = registerItem("dulce_leche", new Item(new Item.Settings()));
    public static final Item PAN_RALLADO = registerItem("pan_rallado", new Item(new Item.Settings()));
    public static final Item TAPAS_EMPANADA = registerItem("tapas_empanada", new Item(new Item.Settings()));
    public static final Item TAPAS_HOJALDRE = registerItem("tapas_hojaldre", new Item(new Item.Settings()));
    public static final Item DULCE_BATATA = registerItem("dulce_batata", new Item(new Item.Settings()));
    public static final Item DULCE_MEMBRILLO = registerItem("dulce_membrillo", new Item(new Item.Settings()));






    public static final Item MEDIALUNA_CRUDA = registerItem("medialuna_cruda",
            new Item(new Item.Settings()
                    .food(new FoodComponent.Builder()
                            .nutrition(4) // 2 muslitos (igual que antes pero con nutrition)
                            .saturationModifier(0.3f)
                            .statusEffect(
                                    new StatusEffectInstance(StatusEffects.HUNGER, 300, 0), // 15s de Hambre I
                                    0.8f // 80% probabilidad
                            )
                            .alwaysEdible()
                            .build())));
    public static final Item MEDIALUNA_COCINADA = registerItem("medialuna_cocinada",
            new Item(new Item.Settings()
                    .food(new FoodComponent.Builder()
                            .nutrition(6) // 3 muslitos
                            .saturationModifier(0.8f)
                            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 0), 1.0f) // 10s Fuerza I
                            .build())));

    public static final Item LECHE = registerItem("leche",
            new LecheBebibleItem(new Item.Settings())); // apila a 16 por la clase



    public static final Item PELOTA = Registry.register(
                Registries.ITEM,
                Identifier.of(Argentum.MOD_ID, "pelota"),
                new PelotaItem(new Item.Settings())
        );

    public static final Item CUADRO_ARGENTO = Registry.register(
            Registries.ITEM, Identifier.of("argentum", "cuadro_argento"),
            new CuadroArgentoItem(new Item.Settings().maxCount(16))
    );


    public static final Item EMPANADA_FRITA = registerItem("empanada_frita",
            new Item(new Item.Settings()
                    .food(ModFoodComponent.FRITO)   // <- tu componente de comida frita compartido
                    .maxCount(64)));
    public static final Item EMPANADA_CRUDA = registerItem("empanada_cruda",
                    new Item(new Item.Settings()
                            .food(ModFoodComponent.CRUDOS)
                            .maxCount(64)));
    // Tortafrita
    public static final Item TORTAFRITA_CRUDA = registerItem("tortafrita_cruda",
            new Item(new Item.Settings()
                    .food(ModFoodComponent.CRUDOS)
                    .maxCount(64)));

    public static final Item TORTAFRITA = registerItem("tortafrita",
            new Item(new Item.Settings()
                    .food(ModFoodComponent.FRITO)
                    .maxCount(64)));

    // Pastelito (membrillo)
    public static final Item PASTELITO_MEMBRILLO_CRUDO = registerItem("pastelito_membrillo_crudo",
            new Item(new Item.Settings()
                    .food(ModFoodComponent.CRUDOS)
                    .maxCount(64)));

    public static final Item PASTELITO_MEMBRILLO_FRITO = registerItem("pastelito_membrillo_frito",
            new Item(new Item.Settings()
                    .food(ModFoodComponent.FRITO)
                    .maxCount(64)));

    // Pastelito (batata)
    public static final Item PASTELITO_BATATA_CRUDO = registerItem("pastelito_batata_crudo",
            new Item(new Item.Settings()
                    .food(ModFoodComponent.CRUDOS)
                    .maxCount(64)));

    public static final Item PASTELITO_BATATA_FRITO = registerItem("pastelito_batata_frito",
            new Item(new Item.Settings()
                    .food(ModFoodComponent.FRITO)
                    .maxCount(64)));

    // Churros
    public static final Item CHURRO_CRUDO = registerItem("churro_crudo",
            new Item(new Item.Settings()
                    .food(ModFoodComponent.CRUDOS)
                    .maxCount(64)));

    public static final Item CHURRO_FRITO = registerItem("churro_frito",
            new Item(new Item.Settings()
                    .food(ModFoodComponent.FRITO)
                    .maxCount(64)));

    // Variante con dulce de leche (ítem aparte)
    public static final Item CHURRO_FRITO_DULCE = registerItem("churro_frito_dulce",
            new Item(new Item.Settings()
                    .food(ModFoodComponent.FRITO)
                    .maxCount(64)));

    // Milanesa
    public static final Item MILANESA_CRUDA = registerItem("milanesa_cruda",
            new Item(new Item.Settings()
                    .food(ModFoodComponent.CRUDOS)
                    .maxCount(64)));

    public static final Item MILANESA_FRITA = registerItem("milanesa_frita",
            new Item(new Item.Settings()
                    .food(ModFoodComponent.FRITO)
                    .maxCount(64)));



    // Palos y números de cartas
    private static final String[] PALOS = {"espada", "palo", "copa", "oro"};
    private static final int[] NUMEROS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};



    // Array para guardar las cartas si querés acceder después
    public static final Item[][] CARTAS = new Item[PALOS.length][NUMEROS.length];

    // Declaración del grupo creativo (se inicializa después)
    public static ItemGroup CARTAS_GROUP;

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Argentum.MOD_ID, name), item);
    }

    public static final Item CARTA_COMODIN = registerItem("carta_comodin", new Item(new Item.Settings()));



    public static void registerModItem() {
        Argentum.LOGGER.info("Registering Mod Item for " + Argentum.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.add(PELOTA));

        // Ingredientes (como antes)
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(CALABAZA_MATE);
            entries.add(TERMO);
            entries.add(BOMBILLA);
            entries.add(YERBA);
            entries.add(ModItem.YERBA_SEMILLA);
            entries.add(MATE);
            entries.add(TERMO_ARGENTO);
            entries.add(TAZA);
            entries.add(TAZA_DE_ARCILLA);
            entries.add(SAQUITO_TE);
            entries.add(SAQUITO_MATECOCIDO);
            entries.add(TAZA_DE_TE);
            entries.add(TAZA_DE_MATECOCIDO);
            entries.add(TE);
            entries.add(TE_SEMILLA);
            entries.add(ACEITE);
            entries.add(HARINA);
            entries.add(MANTECA);
            entries.add(MEDIALUNA_COCINADA);
            entries.add(MEDIALUNA_CRUDA);
            entries.add(LECHE);
            entries.add(EMPANADA_CRUDA);
            entries.add(EMPANADA_FRITA);
            entries.add(TORTAFRITA_CRUDA);
            entries.add(TORTAFRITA);
            entries.add(PASTELITO_MEMBRILLO_CRUDO);
            entries.add(PASTELITO_MEMBRILLO_FRITO);
            entries.add(PASTELITO_BATATA_CRUDO);
            entries.add(PASTELITO_BATATA_FRITO);
            entries.add(CHURRO_CRUDO);
            entries.add(CHURRO_FRITO);
            entries.add(CHURRO_FRITO_DULCE);
            entries.add(MILANESA_CRUDA);
            entries.add(MILANESA_FRITA);
            entries.add(DULCE_LECHE);
            entries.add(PAN_RALLADO);
            entries.add(TAPAS_EMPANADA);
            entries.add(TAPAS_HOJALDRE);
            entries.add(DULCE_BATATA);
            entries.add(DULCE_MEMBRILLO);



            entries.add(MATE_LISTO_BLANCO);
            entries.add(MATE_LISTO_NARANJA);
            entries.add(MATE_LISTO_MARRON);
            entries.add(MATE_LISTO_MAGENTA);
            entries.add(MATE_LISTO_ROJO);
            entries.add(MATE_LISTO_VIOLETA);
            entries.add(MATE_LISTO_ROSA);
            entries.add(MATE_LISTO_VERDE2);
            entries.add(MATE_LISTO_VERDE1);
            entries.add(MATE_LISTO_NEGRO);
            entries.add(MATE_LISTO_AMARILLO);
            entries.add(MATE_LISTO_GRIS1);
            entries.add(MATE_LISTO_GRIS2);
            entries.add(MATE_LISTO_AZUL);
            entries.add(MATE_LISTO_CELESTE);
            entries.add(MATE_LISTO_CYAN);
            entries.add(MATE_LISTO_ARGENTO);

            entries.add(CUCHILLO);
            entries.add(CARNE_CORTADA_CRUDA);
            entries.add(GRASA);
            entries.add(BIFE_CRUDO);

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
                            entries.add(CARTA_COMODIN);
                        })
                        .build()
        );
    }
}