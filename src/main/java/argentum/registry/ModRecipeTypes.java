package argentum.registry;

import argentum.recipe.FrituraRecipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;

public class ModRecipeTypes {
    public static final RecipeType<FrituraRecipe> FRITURA = RecipeType.register("argentum:fritura");

    public static void register() {
        // se llama desde Argentum.java para inicializar
    }
}
