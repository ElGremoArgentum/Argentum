package argentum;

import argentum.block.ModBlocks;
import argentum.item.ModItem;
import argentum.item.ModItemGroups;
import argentum.registry.ModRecipes;
import argentum.registry.ModBlockEntities;
import argentum.sound.ModSounds;
import argentum.villages.ModVillagers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Argentum implements ModInitializer {
	public static final String MOD_ID = "argentum";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {// 1) bloques
		ModBlockEntities.registerAll();   // 2) block entities
		ModItem.registerModItem();        // 3) resto
		ModItemGroups.registerItemsGroups();
		ModSounds.registerSounds();
		argentum.event.BlockUseHandlers.init();
		ModBlocks.registerModBlocks();
		ModRecipes.registerAll();
		ModVillagers.registerVillagers();


		TradeOfferHelper.registerVillagerOffers(ModVillagers.JONI, 1, factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 3),
					new ItemStack(ModItem.TE_SEMILLA, 9), 7, 2, 0.04f));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 3),
					new ItemStack(ModItem.YERBA_SEMILLA, 9), 7, 2, 0.04f));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 3),
					new ItemStack(ModItem.BATATA, 9), 7, 2, 0.04f));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 3),
					new ItemStack(ModItem.MEMBRILLO_SEMILLA, 9), 7, 2, 0.04f));
		});
		TradeOfferHelper.registerVillagerOffers(ModVillagers.JONI, 2, factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 10),
					new ItemStack(ModItem.TERMO_ARGENTO, 1), 10, 2, 0.04f));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 3),
					new ItemStack(ModItem.MATE_LISTO_ARGENTO, 1), 2, 2, 0.04f));
		});
		TradeOfferHelper.registerVillagerOffers(ModVillagers.JONI, 3, factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 64),
					new ItemStack(ModItem.PELOTA, 1), 1, 2, 0.04f));
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 32),
					new ItemStack(ModItem.CARTA_COMODIN, 1), 1, 2, 0.04f));
		});
		TradeOfferHelper.registerVillagerOffers(ModVillagers.JONI, 4, factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.EMERALD, 2),
					new ItemStack(ModItem.CUADRO_ARGENTO, 1), 999, 2, 0.04f));
		});



		LOGGER.info("Argentum inicializado ✅");
	}
}
