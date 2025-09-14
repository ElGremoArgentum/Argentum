package argentum;

import argentum.block.ModBlocks;
import argentum.event.BlockUseHandlers;
import argentum.item.ModItem;
import argentum.item.ModItemGroups;
import argentum.sound.ModSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import argentum.item.ModItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

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
		UseEntityCallback.EVENT.register((player, world, hand, entity, hit) ->
				tryBottleMilk(world, player, hand, entity, hit)
		);




		LOGGER.info("Hello Fabric world!");
	}
	private static ActionResult tryBottleMilk(World world, PlayerEntity player, Hand hand, Entity entity, EntityHitResult hit) {
		if (world.isClient) return ActionResult.PASS;

		boolean isMilkable = (entity instanceof CowEntity) || (entity instanceof GoatEntity);
		if (!isMilkable) return ActionResult.PASS;

		ItemStack held = player.getStackInHand(hand);
		if (!held.isOf(Items.GLASS_BOTTLE)) return ActionResult.PASS;

		// Consumir la botella si no es creativo
		if (!player.getAbilities().creativeMode) {
			held.decrement(1);
		}

		// Dar tu botella de leche
		ItemStack leche = new ItemStack(ModItem.LECHE);

		if (held.isEmpty()) {
			player.setStackInHand(hand, leche);
		} else if (!player.getInventory().insertStack(leche)) {
			player.dropItem(leche, false);}
		// Sonido ordeñe
		player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0f, 1.0f);
		return ActionResult.SUCCESS;}
}