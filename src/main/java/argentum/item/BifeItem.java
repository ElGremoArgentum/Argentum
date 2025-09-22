package argentum.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BifeItem extends Item {
    public BifeItem(Settings settings) { super(settings); }

    @Override public boolean hasRecipeRemainder() { return true; }

    @Override public ItemStack getRecipeRemainder(ItemStack stack) {
        return new ItemStack(ModItem.GRASA);
    }
}
