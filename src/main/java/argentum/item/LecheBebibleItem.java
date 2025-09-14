package argentum.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class LecheBebibleItem extends Item {
    public LecheBebibleItem(Settings settings) {
        super(settings.maxCount(64)); // apila como botellas
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 32; // duración al beber
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        user.playSound(SoundEvents.ENTITY_GENERIC_DRINK, 1.0F, 1.0F);
        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity player) {
            player.incrementStat(Stats.USED.getOrCreateStat(this));

            if (!world.isClient) {
                // Efecto de leche: limpia efectos
                player.clearStatusEffects();
                // Un poco de comida/saturación (ajustá si querés)
                player.getHungerManager().add(2, 0.2f);
            }

            if (!player.getAbilities().creativeMode) {
                stack.decrement(1);
                ItemStack botellaVacia = new ItemStack(Items.GLASS_BOTTLE);
                if (stack.isEmpty()) {
                    return botellaVacia; // reemplaza el stack consumido
                } else {
                    player.getInventory().offerOrDrop(botellaVacia);
                }
            }
        }
        return stack;
    }
}
