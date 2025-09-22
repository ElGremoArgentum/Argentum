package argentum.registry;

import argentum.Argentum;
import argentum.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {

    public static void registerAll() {
        Argentum.LOGGER.info("Registering Block Entities for " + Argentum.MOD_ID);

    }

    private ModBlockEntities() {}
}
