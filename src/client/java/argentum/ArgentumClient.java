package argentum;

import argentum.block.ModBlocks;
import argentum.item.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import argentum.entity.ModEntities;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class ArgentumClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
    MateModelPredicates.registerPredicates();
    TermoModelPredicates.registerPredicates();

    BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.YERBA_PLANTA, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.YERBA_PLANTA);
    BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TE_PLANTA, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.TE_PLANTA);
    BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MEMBRILLO_PLANTA, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.MEMBRILLO_PLANTA);
    BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BATATA_PLANTA, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.BATATA_PLANTA);

    EntityRendererRegistry.register(ModEntities.PELOTA, (ctx) -> new FlyingItemEntityRenderer<>(ctx));
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), ModBlocks.OLLA);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.OLLA, RenderLayer.getCutout());





    }
}