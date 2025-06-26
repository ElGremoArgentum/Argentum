package argentum.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TermoItem extends Item {
    public TermoItem(Settings settings) {
        super(settings.maxDamage(20).maxCount(1));
    }}

    /*@Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!world.isClient) {
            // Buscá el mate en el inventario
            ItemStack mateStack = buscarMate(player);
            if (mateStack != null && mateStack.getItem() instanceof MateItem) {
                if (MateItem.estaVacio()) {
                    MateItem.recargar();
                    player.sendMessage(Text.literal("Mate recargado!").formatted(Formatting.GREEN), true);
                    return TypedActionResult.success(stack);
                } else {
                    player.sendMessage(Text.literal("El mate ya está lleno.").formatted(Formatting.YELLOW), true);
                    return TypedActionResult.fail(stack);
                }
            } else {
                player.sendMessage(Text.literal("No tenés un mate para recargar.").formatted(Formatting.RED), true);
                return TypedActionResult.fail(stack);
            }
        }

        return TypedActionResult.pass(stack);
    }

    private ItemStack buscarMate(PlayerEntity player) {
        for (ItemStack inventoryStack : player.getInventory().main) {
            if (inventoryStack.getItem() == ModItem.MATE) {
                return inventoryStack;
            }
        }
        return null;
    }

}*/