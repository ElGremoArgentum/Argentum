package argentum;

import argentum.block.ModBlocks;
import argentum.item.ModItem;
import argentum.item.ModItemGroups;
import argentum.sound.ModSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class Argentum implements ModInitializer {
	public static final String MOD_ID = "argentum";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItem.registerModItem();     // 1) primero ítems
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemsGroups(); // 3) al final grupos creativos
		ModSounds.registerSounds();    // 4) sonidos



	// Compostaje
		CompostingChanceRegistry.INSTANCE.add(ModItem.YERBA, 0.5f);
		CompostingChanceRegistry.INSTANCE.add(ModItem.YERBA_SEMILLA, 0.25f);
	}
}
