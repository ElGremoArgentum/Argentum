package argentum.recipe;

import argentum.Argentum;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static final RecipeType<RecipeFrita> FRITAR = Registry.register(
            Registries.RECIPE_TYPE,
            Identifier.of(Argentum.MOD_ID, "fritar"),
            new RecipeType<RecipeFrita>() { @Override public String toString() { return Argentum.MOD_ID + ":fritar"; } }
    );

    public static final RecipeSerializer<RecipeFrita> FRITAR_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER,
                    Identifier.of(Argentum.MOD_ID, "fritar"),
                    new RecipeFrita.Serializer());


    public static void register() {
        Argentum.LOGGER.info("Registering recipe types & serializers for {}", Argentum.MOD_ID);
    }


}
