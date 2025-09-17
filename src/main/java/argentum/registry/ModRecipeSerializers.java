package argentum.registry;

import argentum.recipe.FrituraRecipe;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipeSerializers {
    public static final RecipeSerializer<FrituraRecipe> FRITURA = Registry.register(
            Registries.RECIPE_SERIALIZER,
            Identifier.of("argentum", "fritura"),           // <- usamos Identifier.of como pediste
            new CookingRecipeSerializer<>(FrituraRecipe::new, 200) // 200 ticks por defecto
    );

    public static void register() {
        // Llamar desde Argentum.java si querés mantener el patrón, aunque el static ya registra.
    }
}
