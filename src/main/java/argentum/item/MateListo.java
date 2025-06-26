package argentum.item;

import argentum.sound.ModSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;

public class MateListo extends Item {

    public MateListo(Settings settings) {
        super(settings.maxDamage(20).maxCount(1));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient) {
            world.playSound(
                    null,
                    user.getX(), user.getY(), user.getZ(),
                    ModSounds.MATE_RUIDO,
                    SoundCategory.PLAYERS,
                    1.0F,
                    1.0F
            );
        }
        return super.finishUsing(stack, world, user);
    }
}
