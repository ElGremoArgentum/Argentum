package argentum.registry;

import argentum.Argentum;
import argentum.recipe.FritarRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ModRecipes {
    public static final RecipeType<FritarRecipe> FRITAR_TYPE = new RecipeType<FritarRecipe>() {
        public String toString() { return "argentum:fritar"; }
    };

    public static final RecipeSerializer<FritarRecipe> FRITAR_SERIALIZER = new FritarRecipe.Serializer();

    public static void registerAll() {
        Registry.register(Registries.RECIPE_TYPE, Identifier.of(Argentum.MOD_ID, "fritar"), FRITAR_TYPE);
        Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(Argentum.MOD_ID, "fritar"), FRITAR_SERIALIZER);
    }

    private ModRecipes() {}
}
