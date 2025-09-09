package argentum.block;

import argentum.Argentum;
import argentum.block.custom.Yerba_Planta;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    // Bloques normales (con BlockItem)
    public static final Block SEIS = registerBlock("seis",
            new Block(AbstractBlock.Settings.create().strength(4f)
                    .requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block CINCO = registerBlock("cinco",
            new Block(AbstractBlock.Settings.create().strength(4f)
                    .requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block CUATRO = registerBlock("cuatro",
            new Block(AbstractBlock.Settings.create().strength(4f)
                    .requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block TRES = registerBlock("tres",
            new Block(AbstractBlock.Settings.create().strength(4f)
                    .requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block DOS = registerBlock("dos",
            new Block(AbstractBlock.Settings.create().strength(4f)
                    .requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block UNO = registerBlock("uno",
            new Block(AbstractBlock.Settings.create().strength(4f)
                    .requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    // ✅ Yerba_Planta (sin BlockItem, porque es cultivo)
    public static final Block YERBA_PLANTA = registerBlockWithoutItem(
            "yerba_planta",
            new Yerba_Planta(
                    Block.Settings.copy(Blocks.WHEAT)
                            .noCollision()
                            .nonOpaque()
                            .breakInstantly()
                            .sounds(BlockSoundGroup.CROP)
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );

    // ───────────────────────────────
    // Helpers
    // ───────────────────────────────
    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(
                Registries.BLOCK,
                Identifier.of(Argentum.MOD_ID, name),
                block
        );
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Argentum.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(
                Registries.ITEM,
                Identifier.of(Argentum.MOD_ID, name),
                new BlockItem(block, new Item.Settings())
        );
    }

    // Registrar bloques en el grupo de ítems
    public static void registerModBlocks() {
        Argentum.LOGGER.info("Registering Mod Blocks for " + Argentum.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.UNO);
            entries.add(ModBlocks.DOS);
            entries.add(ModBlocks.TRES);
            entries.add(ModBlocks.CUATRO);
            entries.add(ModBlocks.CINCO);
            entries.add(ModBlocks.SEIS);
            // ⚠️ No agregues YERBA_PLANTA acá porque no tiene BlockItem
        });
    }
}
