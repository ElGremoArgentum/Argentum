package argentum;

import argentum.block.ModBlocks;
import argentum.event.BlockUseHandlers;
import argentum.item.ModItem;
import argentum.item.ModItemGroups;
import argentum.recipe.ModRecipes;
import argentum.sound.ModSounds;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.GoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// === Tus clases de la olla ===
import argentum.block.OllaBlock;
import argentum.block.entity.OllaBlockEntity;

public class Argentum implements ModInitializer {
	public static final String MOD_ID = "argentum";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// === Bloque OLLA ===
	public static final Block OLLA = Registry.register(
			Registries.BLOCK,
			Identifier.of(MOD_ID, "olla"),
			new OllaBlock(
					AbstractBlock.Settings.create()
							.strength(3.5f)
							.requiresTool()
							.luminance(state -> state.get(Properties.LIT) ? 13 : 0)
							.nonOpaque()
							.solidBlock((s, w, p) -> false)
							.suffocates((s, w, p) -> false)
							.blockVision((s, w, p) -> false)
			)
	);

	public static final BlockEntityType<OllaBlockEntity> OLLA_BE = Registry.register(
			Registries.BLOCK_ENTITY_TYPE,
			Identifier.of(MOD_ID, "olla"),
			BlockEntityType.Builder.create(OllaBlockEntity::new, OLLA).build(null)
	);
	// =====================

	@Override
	public void onInitialize() {
		ModItem.registerModItem();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemsGroups();
		ModSounds.registerSounds();
		ModRecipes.register();
		BlockUseHandlers.init();


		// Item de la OLLA (importante: registrar el BlockItem para que se vea en creativo)
		Registry.register(
				Registries.ITEM,
				Identifier.of(MOD_ID, "olla"),
				new BlockItem(OLLA, new Item.Settings())
		);

		// QUITADO: no la ponemos en ItemGroups.FUNCTIONAL, solo en tu tab custom.
		// ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> entries.add(OLLA.asItem()));

		// combustible aceite
		FuelRegistry.INSTANCE.add(ModItem.ACEITE, 3200);

		LOGGER.info("Hello Fabric world!");
	}

	private static ActionResult tryBottleMilk(World world, PlayerEntity player, Hand hand, Entity entity, EntityHitResult hit) {
		if (world.isClient) return ActionResult.PASS;

		boolean isMilkable = (entity instanceof CowEntity) || (entity instanceof GoatEntity);
		if (!isMilkable) return ActionResult.PASS;

		ItemStack held = player.getStackInHand(hand);
		if (!held.isOf(Items.GLASS_BOTTLE)) return ActionResult.PASS;

		if (!player.getAbilities().creativeMode) {
			held.decrement(1);
		}

		ItemStack leche = new ItemStack(ModItem.LECHE);

		if (held.isEmpty()) {
			player.setStackInHand(hand, leche);
		} else if (!player.getInventory().insertStack(leche)) {
			player.dropItem(leche, false);
		}

		player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0f, 1.0f);
		return ActionResult.SUCCESS;
	}
}
