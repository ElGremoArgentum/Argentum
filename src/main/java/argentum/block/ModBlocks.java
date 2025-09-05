package argentum.block;

import argentum.Argentum;
import argentum.block.custom.YerbaPlantaBlock;
import argentum.block.custom.YerbaPlantaTopBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    // Bloques de prueba
    public static final Block UNO = registerBlock("uno", createDefaultBlock());
    public static final Block DOS = registerBlock("dos", createDefaultBlock());
    public static final Block TRES = registerBlock("tres", createDefaultBlock());
    public static final Block CUATRO = registerBlock("cuatro", createDefaultBlock());
    public static final Block CINCO = registerBlock("cinco", createDefaultBlock());
    public static final Block SEIS = registerBlock("seis", createDefaultBlock());

    // Planta de yerba de 2 bloques
    public static final Block YERBA_PLANTA = registerBlockWithoutItem("yerba_planta",
            new YerbaPlantaBlock(AbstractBlock.Settings.create()
                    .noCollision()
                    .ticksRandomly()   // <- IMPORTANTE
                    .breakInstantly()
                    .sounds(BlockSoundGroup.CROP)
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .mapColor(MapColor.DARK_GREEN)));


    public static final Block YERBA_TOP = registerBlockWithoutItem("yerba_top",
            new YerbaPlantaTopBlock(AbstractBlock.Settings.create()
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.CROP)
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .mapColor(MapColor.DARK_GREEN)));

    // Métodos auxiliares
    private static Block createDefaultBlock() {
        return new Block(AbstractBlock.Settings.create()
                .strength(4f)
                .requiresTool()
                .sounds(BlockSoundGroup.AMETHYST_BLOCK));
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Argentum.MOD_ID, name), block);
    }

    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(Argentum.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Argentum.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    // Registrar bloques en grupo de ítems
    public static void registerModBlocks() {
        Argentum.LOGGER.info("Registering Mod Blocks for " + Argentum.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(UNO);
            entries.add(DOS);
            entries.add(TRES);
            entries.add(CUATRO);
            entries.add(CINCO);
            entries.add(SEIS);
        });
    }
}
