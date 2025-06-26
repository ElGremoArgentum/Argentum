package argentum.item;

import argentum.sound.ModSounds;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class MateListoItem extends Item {

    public MateListoItem(Settings settings) {
        super(settings.maxDamage(15).maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        // Sonido personalizado al inicio
        if (!world.isClient) {
            world.playSound(null, player.getBlockPos(), ModSounds.MATE_RUIDO, SoundCategory.PLAYERS, 1f, 1f);
        }

        player.setCurrentHand(hand);
        return TypedActionResult.consume(stack);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity player) {
            stack.damage(1, player, EquipmentSlot.MAINHAND);

            if (stack.getDamage() >= stack.getMaxDamage()) {
                player.sendEquipmentBreakStatus(stack.getItem(), EquipmentSlot.MAINHAND);
                player.getInventory().removeOne(stack);
            }

            player.getHungerManager().add(6, 12.8F);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 40, 0));
        }
        return stack;
    }

    public int getMaxUseTime(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }
}