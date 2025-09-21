package argentum.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import argentum.registry.ModRecipes;

import net.minecraft.registry.Registries;
import net.minecraft.util.math.random.Random;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * Receta minimalista para "fritar" con un único ingrediente (item directo)
 * y resultado referenciado por string ID. Sin NBT.
 *
 * JSON esperado (ejemplo):
 * {
 *   "type": "argentum:fritar",
 *   "id": "argentum:tortafrita_olla",
 *   "category": "food",
 *   "group": "olla",
 *   "ingredient_item": "argentum:tortafrita_cruda",
 *   "result": "argentum:tortafrita",
 *   "experience": 0.1,
 *   "cookingtime": 180
 * }
 */
public final class FritarRecipe implements Recipe<SingleStackRecipeInput> {

    private final Identifier id;              // lo tomamos del JSON (simple)
    private final Item ingredientItem;        // item directo (no Ingredient)
    private final Identifier resultId;        // string ID del resultado
    private final float experience;
    private final int cookingTime;
    private final CookingRecipeCategory category;

    public FritarRecipe(
            Identifier id,
            Item ingredientItem,
            Identifier resultId,
            float experience,
            int cookingTime,
            CookingRecipeCategory category
    ) {
        this.id = id;
        this.ingredientItem = ingredientItem;
        this.resultId = resultId;
        this.experience = experience;
        this.cookingTime = cookingTime;
        this.category = category;
    }

    // ===== Recipe impl =====

    @Override
    public boolean matches(SingleStackRecipeInput input, net.minecraft.world.World world) {
        ItemStack stack = input.getStackInSlot(0);
        return !stack.isEmpty() && stack.getItem() == ingredientItem;
    }

    @Override
    public ItemStack craft(SingleStackRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return new ItemStack(Registries.ITEM.get(resultId));
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(RegistryWrapper.WrapperLookup lookup) {
        return new ItemStack(Registries.ITEM.get(resultId));
    }

    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.FRITAR_SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.FRITAR_TYPE;
    }



    public float getExperience() { return experience; }
    public int getCookingTime() { return cookingTime; }
    public CookingRecipeCategory getCategory() { return category; }
    public Item getIngredientItem() { return ingredientItem; }
    public Identifier getResultId() { return resultId; }

    // ===== Serializer (Codec + PacketCodec) =====

    public static final class Serializer implements RecipeSerializer<FritarRecipe> {

        // JSON → objeto (¡OJO: MapCodec, no Codec!)
        private static final MapCodec<FritarRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        Identifier.CODEC.fieldOf("id").forGetter(FritarRecipe::getId),
                        Registries.ITEM.getCodec().fieldOf("ingredient_item").forGetter(FritarRecipe::getIngredientItem),
                        Identifier.CODEC.fieldOf("result").forGetter(FritarRecipe::getResultId),
                        Codec.FLOAT.optionalFieldOf("experience", 0.0f).forGetter(FritarRecipe::getExperience),
                        Codec.INT.optionalFieldOf("cookingtime", 200).forGetter(FritarRecipe::getCookingTime),
                        Codec.STRING.optionalFieldOf("category", "food")
                                .xmap(s -> CookingRecipeCategory.FOOD, c -> "food")
                                .forGetter(FritarRecipe::getCategory)
                ).apply(instance, FritarRecipe::new)
        );

        // Red → objeto (todo con RegistryByteBuf)
        private static final PacketCodec<RegistryByteBuf, FritarRecipe> PACKET_CODEC =
                new PacketCodec<RegistryByteBuf, FritarRecipe>() {
                    @Override
                    public void encode(RegistryByteBuf buf, FritarRecipe r) {
                        buf.writeIdentifier(r.getId());
                        buf.writeIdentifier(Registries.ITEM.getId(r.getIngredientItem()));
                        buf.writeIdentifier(r.getResultId());
                        buf.writeFloat(r.getExperience());
                        buf.writeVarInt(r.getCookingTime());
                        buf.writeVarInt(r.getCategory().ordinal());
                    }

                    @Override
                    public FritarRecipe decode(RegistryByteBuf buf) {
                        Identifier id = buf.readIdentifier();
                        var ingredient = Registries.ITEM.get(buf.readIdentifier());
                        Identifier resultId = buf.readIdentifier();
                        float exp = buf.readFloat();
                        int time = buf.readVarInt();
                        int ord = buf.readVarInt();
                        var cat = CookingRecipeCategory.values()[Math.max(0, Math.min(ord, CookingRecipeCategory.values().length - 1))];
                        return new FritarRecipe(id, ingredient, resultId, exp, time, cat);
                    }
                };

        @Override public MapCodec<FritarRecipe> codec() { return CODEC; }
        @Override public PacketCodec<RegistryByteBuf, FritarRecipe> packetCodec() { return PACKET_CODEC; }
    }
}
