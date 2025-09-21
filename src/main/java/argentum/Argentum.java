package argentum;

import argentum.block.ModBlocks;
import argentum.item.ModItem;
import argentum.item.ModItemGroups;
import argentum.registry.ModRecipes;
import argentum.registry.ModBlockEntities;
import argentum.sound.ModSounds;
import net.fabricmc.api.ModInitializer;
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



		LOGGER.info("Argentum inicializado ✅");
	}
}
