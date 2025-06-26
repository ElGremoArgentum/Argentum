package argentum.item;

import argentum.Argentum;
import argentum.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup TERMO = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Argentum.MOD_ID, "termo"),
            FabricItemGroup.builder() .icon(() -> new ItemStack(ModItem.TERMO))
                    .displayName(Text.translatable("ItemGroup.argentum.termo"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItem.TERMO);
                        entries.add(ModItem.BOMBILLA);
                        entries.add(ModItem.YERBA);
                        entries.add(ModItem.CALABAZA_MATE);
                        entries.add(ModItem.MATE);
                        entries.add(ModItem.TERMO_ARGENTO);
                        entries.add(ModItem.YERBA_SEMILLA);
                        entries.add(ModItem.MATE_LISTO_BLANCO);

                    })
                    .build());

    public static final ItemGroup UNO = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Argentum.MOD_ID, "uno"),
            FabricItemGroup.builder() .icon(() -> new ItemStack(ModBlocks.UNO))
                    .displayName(Text.translatable("ItemGroup.argentum.uno"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.UNO);
                        entries.add(ModBlocks.DOS);
                        entries.add(ModBlocks.TRES);
                        entries.add(ModBlocks.CUATRO);
                        entries.add(ModBlocks.CINCO);
                        entries.add(ModBlocks.SEIS);
                    })
                    .build());

    public static void registerItemsGroups() {
        Argentum.LOGGER.info("Registering Mod Item Groups for" + Argentum.MOD_ID);
    }
}

