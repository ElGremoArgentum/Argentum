package argentum;

import argentum.block.ModBlocks;
import argentum.item.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ArgentumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
    MateModelPredicates.registerPredicates();
    TermoModelPredicates.registerPredicates();
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.YERBA_PLANTA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.YERBA_PLANTA);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TE_PLANTA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.TE_PLANTA);




    }
}