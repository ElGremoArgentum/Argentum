package argentum.screen;

import argentum.registry.ModRecipeTypes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.AbstractFurnaceScreenHandler;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerType;

public class OllaScreenHandler extends AbstractFurnaceScreenHandler {

    // Constructor simple (sin BE ni props)
    public OllaScreenHandler(int syncId, PlayerInventory inv) {
        super(ModScreenHandlers.OLLA, ModRecipeTypes.FRITURA, RecipeBookCategory.FURNACE, syncId, inv);
    }

    // Constructor con inventario del bloque y PropertyDelegate
    public OllaScreenHandler(int syncId, PlayerInventory inv, Inventory beInv, PropertyDelegate props) {
        super(ModScreenHandlers.OLLA, ModRecipeTypes.FRITURA, RecipeBookCategory.FURNACE, syncId, inv, beInv, props);
    }
}
