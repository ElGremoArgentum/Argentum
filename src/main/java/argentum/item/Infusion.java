package argentum.item;

import argentum.sound.ModSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class Infusion extends Item {

    public Infusion(Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        // Animación de beber
        return UseAction.DRINK;
    }

    public net.minecraft.sound.SoundEvent getUseSound(ItemStack stack) {
        // Sonido base de bebida
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient) {
            user.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.NIGHT_VISION,
                    20 * 60, // 1 minuto
                    0,
                    false,
                    true
            ));

            world.playSound(
                    null,
                    user.getX(), user.getY(), user.getZ(),
                    ModSounds.MATE_RUIDO,
                    SoundCategory.PLAYERS,
                    0.0F,
                    0.0F
            );
        }

        // primero aplica el efecto de comida/saturación
        ItemStack resultStack = super.finishUsing(stack, world, user);

        // después devuelve la taza vacía si no es creativo
        if (user instanceof PlayerEntity player && !player.getAbilities().creativeMode) {
            return new ItemStack(ModItem.TAZA);
        }

        return resultStack;
    }

}
