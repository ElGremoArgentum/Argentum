// BlockUseHandlers.java
package argentum.event;

import argentum.item.ModItem;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public final class BlockUseHandlers {
    public static void init() {
        UseBlockCallback.EVENT.register(BlockUseHandlers::onUseBlock);
    }

    private static ActionResult onUseBlock(PlayerEntity player, World world, Hand hand, BlockHitResult hit) {
        if (world.isClient || player.isSpectator()) return ActionResult.PASS;

        BlockPos pos = hit.getBlockPos();
        BlockState state = world.getBlockState(pos);

        if (!state.isOf(Blocks.SUNFLOWER)) return ActionResult.PASS;

        // El girasol es doble. Calculamos la base (LOWER) para romper ambas mitades si queremos.
        DoubleBlockHalf half = state.get(Properties.DOUBLE_BLOCK_HALF);
        BlockPos basePos = (half == DoubleBlockHalf.LOWER) ? pos : pos.down();


        // OPCIÓN B (balanceado): requerir botella de vidrio y no romper la planta
        // Descomenta esto y cambia el return de arriba por PASS si querés esta lógica en su lugar.
        ItemStack inHand = player.getStackInHand(hand);
        if (inHand.isOf(Items.GLASS_BOTTLE)) {
            inHand.decrement(1); // consume una botella
            ItemStack aceite = new ItemStack(ModItem.ACEITE);
            if (!player.getInventory().insertStack(aceite)) player.dropItem(aceite, false);
            world.playSound(null, basePos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.PLAYERS, 1f, 1f);
            // Ejemplo: cooldown para evitar spam
            player.getItemCooldownManager().set(ModItem.ACEITE, 20 * 2);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}
