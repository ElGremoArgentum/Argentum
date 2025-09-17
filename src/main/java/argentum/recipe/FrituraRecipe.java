package argentum.recipe;

import argentum.registry.ModRecipeSerializers;
import argentum.registry.ModRecipeTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.CookingRecipeCategory;

public class FrituraRecipe extends AbstractCookingRecipe {
    // Firma que coincide con CookingRecipeSerializer<>(FrituraRecipe::new, defaultTime)
    public FrituraRecipe(String group, CookingRecipeCategory category,
                         Ingredient input, ItemStack output, float experience, int cookingTime) {
        // En tu mapping: AbstractCookingRecipe(RecipeType<?>, String, CookingRecipeCategory, Ingredient, ItemStack, float, int)
        super(ModRecipeTypes.FRITURA, group, category, input, output, experience, cookingTime);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.FRITURA;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeTypes.FRITURA;
    }
}
