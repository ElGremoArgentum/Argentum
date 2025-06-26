package argentum.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import argentum.sound.ModSounds;

public class MateItem extends Item {

    public MateItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        // Verificar si hay termo en la otra mano
        Hand otherHand = hand == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND;
        ItemStack otherStack = player.getStackInHand(otherHand);

        if (!(otherStack.getItem() == ModItem.TERMO || otherStack.getItem() == ModItem.TERMO_ARGENTO)) {
            if (!world.isClient) {
                player.sendMessage(Text.literal("Necesitás un termo en la otra mano para tomar mate").formatted(Formatting.RED), true);
            }
            return TypedActionResult.fail(stack);
        }

        // Si el termo está vacío (durabilidad máxima)
        if (otherStack.getDamage() >= otherStack.getMaxDamage()) {
            if (!world.isClient) {
                player.sendMessage(Text.literal("El termo está vacío. Recargalo!").formatted(Formatting.YELLOW), true);
            }
            return TypedActionResult.fail(stack);
        }

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
            // Buscar el termo en la otra mano
            ItemStack termoStack = player.getMainHandStack().getItem() == ModItem.MATE
                    ? player.getOffHandStack()
                    : player.getMainHandStack();

            if (termoStack.getItem() == ModItem.TERMO || termoStack.getItem() == ModItem.TERMO_ARGENTO) {
                // Gastar 1 de durabilidad del termo
                termoStack.damage(1, player, net.minecraft.entity.EquipmentSlot.MAINHAND);

                // Sonido del termo vacío (opcional)
                if (termoStack.getDamage() >= termoStack.getMaxDamage() && !world.isClient) {
                    world.playSound(null, player.getBlockPos(), ModSounds.TERMO_RUIDO, SoundCategory.PLAYERS, 0.8f, 1.2f);
                    player.sendMessage(Text.literal("El termo se quedó sin agua").formatted(Formatting.YELLOW), true);
                }

                // Efecto de alimentación similar a filete cocido
                player.getHungerManager().add(6, 12.8F);

                // Buff de velocidad por 2 segundos (40 ticks)
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 40, 0));
            }
        }
        return stack; // No llamamos a super.finishUsing para evitar sonido vanilla
    }

    public int getMaxUseTime(ItemStack stack) {
        return 32;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }
}