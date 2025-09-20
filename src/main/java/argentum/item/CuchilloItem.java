package argentum.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CuchilloItem extends Item {
    public CuchilloItem(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public boolean hasRecipeRemainder() { return true; }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        ItemStack copy = stack.copy();
        copy.setCount(1);
        return copy; // vuelve el mismo cuchillo
    }
}
