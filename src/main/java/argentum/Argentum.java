package argentum;

import argentum.block.ModBlocks;
import argentum.event.BlockUseHandlers;
import argentum.item.ModItem;
import argentum.item.ModItemGroups;
import argentum.sound.ModSounds;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Argentum implements ModInitializer {
	public static final String MOD_ID = "argentum";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItem.registerModItem();
		// Registrar bloques
		ModBlocks.registerModBlocks();
		// Registrar block entities
		ModItemGroups.registerItemsGroups(); // 3) al final grupos creativos
		ModSounds.registerSounds();    // 4) sonidos
		BlockUseHandlers.init();




		LOGGER.info("Hello Fabric world!");
	}
}