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

    // Cultivos (sin BlockItem)
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

    public static final Block TE_PLANTA = registerBlockWithoutItem(
            "te_planta",
            new Yerba_Planta(
                    Block.Settings.copy(Blocks.WHEAT)
                            .noCollision()
                            .nonOpaque()
                            .breakInstantly()
                            .sounds(BlockSoundGroup.CROP)
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );


    /** Registrar bloques (llamar desde Argentum.onInitialize() antes de ModBlockEntities.registerAll()) */
    public static void registerBlocks() {
        Argentum.LOGGER.info("Registrando bloques de Argentum");

    }

    // Helpers
    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(Argentum.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Argentum.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Argentum.MOD_ID, name), new BlockItem(block, new Item.Settings()));
    }

    /** Agrega bloques a creative tabs (opcional) */
    public static void registerModBlocks() {
        Argentum.LOGGER.info("Registering Mod Blocks for " + Argentum.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.UNO);
            entries.add(ModBlocks.DOS);
            entries.add(ModBlocks.TRES);
            entries.add(ModBlocks.CUATRO);
            entries.add(ModBlocks.CINCO);
            entries.add(ModBlocks.SEIS);
            // No agregar YERBA_PLANTA / TE_PLANTA (no tienen BlockItem)
            // La OLLA podés sumarla a otro ItemGroup si querés
        });
    }
}
