package argentum.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.registry.Registries;

public class RecipeFrita extends AbstractCookingRecipe {

    private final ItemStack resultStack;

    public RecipeFrita(String group,
                       CookingRecipeCategory category,
                       Ingredient input,
                       ItemStack result,
                       float experience,
                       int cookingTime) {
        super(ModRecipes.FRITAR, group, category, input, result, experience, cookingTime);
        this.resultStack = result.copy();
    }

    @Override public RecipeSerializer<?> getSerializer() { return ModRecipes.FRITAR_SERIALIZER; }
    @Override public RecipeType<?> getType() { return ModRecipes.FRITAR; }

    public ItemStack getFixedResultStack() { return resultStack.copy(); }

    // ================== Serializer (Codecs 1.21) ==================
    public static class Serializer implements RecipeSerializer<RecipeFrita> {

        // JSON codec (igual a smelting pero con nuestro type)
        private static final MapCodec<RecipeFrita> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(RecipeFrita::getGroup),
                CookingRecipeCategory.CODEC.optionalFieldOf("category", CookingRecipeCategory.MISC)
                        .forGetter(RecipeFrita::getCategory),
                // Importante: en 1.21 usar DISALLOW_EMPTY_CODEC
                Ingredient.DISALLOW_EMPTY_CODEC.fieldOf("ingredient")
                        .forGetter(r -> r.getIngredients().get(0)),
                // "result": "namespace:item"  -> ItemStack(1)
                Registries.ITEM.getCodec().fieldOf("result")
                        .xmap(ItemStack::new, ItemStack::getItem)
                        .forGetter(RecipeFrita::getFixedResultStack),
                Codec.FLOAT.optionalFieldOf("experience", 0.0f).forGetter(RecipeFrita::getExperience),
                Codec.INT.optionalFieldOf("cookingtime", 200).forGetter(RecipeFrita::getCookingTime)
        ).apply(inst, (group, cat, ing, result, exp, time) ->
                new RecipeFrita(group, cat, ing, result, exp, time)));

        // Codec de ENUM para red (sin xmap, sin enumConstant)
        private static final PacketCodec<RegistryByteBuf, CookingRecipeCategory> CAT_PACKET =
                new PacketCodec<RegistryByteBuf, CookingRecipeCategory>() {
                    @Override
                    public CookingRecipeCategory decode(RegistryByteBuf buf) {
                        int i = buf.readVarInt();
                        CookingRecipeCategory[] vals = CookingRecipeCategory.values();
                        return (i >= 0 && i < vals.length) ? vals[i] : CookingRecipeCategory.MISC;
                    }
                    @Override
                    public void encode(RegistryByteBuf buf, CookingRecipeCategory value) {
                        buf.writeVarInt(value.ordinal());
                    }
                };

        // Packet codec completo de la receta
        private static final PacketCodec<RegistryByteBuf, RecipeFrita> PACKET_CODEC =
                PacketCodec.tuple(
                        PacketCodecs.STRING,            RecipeFrita::getGroup,
                        CAT_PACKET,                     RecipeFrita::getCategory,
                        Ingredient.PACKET_CODEC,        r -> r.getIngredients().get(0),
                        ItemStack.PACKET_CODEC,         RecipeFrita::getFixedResultStack,
                        PacketCodecs.FLOAT,             RecipeFrita::getExperience,
                        PacketCodecs.VAR_INT,           RecipeFrita::getCookingTime,
                        RecipeFrita::new // (group, category, ingredient, result, exp, time)
                );

        @Override public MapCodec<RecipeFrita> codec() { return CODEC; }
        @Override public PacketCodec<RegistryByteBuf, RecipeFrita> packetCodec() { return PACKET_CODEC; }
    }
}
