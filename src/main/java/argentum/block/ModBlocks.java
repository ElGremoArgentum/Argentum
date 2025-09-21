package argentum.block;

import argentum.Argentum;
import argentum.block.custom.Yerba_Planta;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public final class ModBlocks {

    // Bloques normales (con BlockItem)
    public static final Block SEIS = registerBlock("seis",
            new Block(Block.Settings.create().strength(4f)
                    .requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block CINCO = registerBlock("cinco",
            new Block(Block.Settings.create().strength(4f)
                    .requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block CUATRO = registerBlock("cuatro",
            new Block(Block.Settings.create().strength(4f)
                    .requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block TRES = registerBlock("tres",
            new Block(Block.Settings.create().strength(4f)
                    .requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block DOS = registerBlock("dos",
            new Block(Block.Settings.create().strength(4f)
                    .requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    public static final Block UNO = registerBlock("uno",
            new Block(Block.Settings.create().strength(4f)
                    .requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));

    // Cultivos (sin BlockItem)
    public static final Block YERBA_PLANTA = registerBlockWithoutItem(
            "yerba_planta",
            new Yerba_Planta(
                    Block.Settings.copy(Blocks.WHEAT)
                            .noCollision()
                            .nonOpaque()
                            .breakInstantly()
                            .sounds(BlockSoundGroup.CROP)
                            .pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block TE_PLANTA = registerBlockWithoutItem(
            "te_planta",
            new Yerba_Planta(
                    Block.Settings.copy(Blocks.WHEAT)
                            .noCollision()
                            .nonOpaque()
                            .breakInstantly()
                            .sounds(BlockSoundGroup.CROP)
                            .pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block MEMBRILLO_PLANTA = registerBlockWithoutItem(
            "membrillo_planta",
            new Yerba_Planta(
                    Block.Settings.copy(Blocks.WHEAT)
                            .noCollision()
                            .nonOpaque()
                            .breakInstantly()
                            .sounds(BlockSoundGroup.CROP)
                            .pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block BATATA_PLANTA = registerBlockWithoutItem(
            "batata_planta",
            new Yerba_Planta(
                    Block.Settings.copy(Blocks.WHEAT)
                            .noCollision()
                            .nonOpaque()
                            .breakInstantly()
                            .sounds(BlockSoundGroup.CROP)
                            .pistonBehavior(PistonBehavior.DESTROY)));

    // Olla (bloque sin BE)
    public static final Block OLLA = registerBlock("olla",
            new OllaBlock(Block.Settings.create()
                    .mapColor(MapColor.IRON_GRAY)
                    .strength(2.0f)
                    .nonOpaque()));

    /** Llamar en onInitialize() */
    public static void registerModBlocks() {
        Argentum.LOGGER.info("Registering Mod Blocks for " + Argentum.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(UNO);
            entries.add(DOS);
            entries.add(TRES);
            entries.add(CUATRO);
            entries.add(CINCO);
            entries.add(SEIS);
            // Podés agregar la OLLA a otro ItemGroup si querés:
            // ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(e -> e.add(OLLA));
        });
    }

    // ---- helpers ----
    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(Argentum.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        Registry.register(Registries.BLOCK, Identifier.of(Argentum.MOD_ID, name), block);
        Registry.register(Registries.ITEM, Identifier.of(Argentum.MOD_ID, name), new BlockItem(block, new Item.Settings()));
        return block;
    }

    private ModBlocks() {}
}
