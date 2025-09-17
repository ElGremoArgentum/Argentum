package argentum.item;

import argentum.entity.PelotaEntity;
import argentum.entity.ModEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PelotaItem extends Item {
    public PelotaItem(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {
            // calcular velocidad inicial (similar a una snowball pero más pesada)
            float pitch = user.getPitch();
            float yaw = user.getYaw();
            double fuerza = 0.9; // ajustá la potencia del “chutazo”
            Vec3d look = user.getRotationVec(1.0f).normalize().multiply(fuerza);
            // un pequeño lift para que no caiga inmediamente
            look = look.add(0, 0.05, 0);

            PelotaEntity ball = new PelotaEntity(world, user.getX(), user.getEyeY() - 0.1, user.getZ(), look);
            ball.setYaw(yaw);
            ball.setPitch(pitch);
            world.spawnEntity(ball);

            world.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.PLAYERS, 0.7f, 0.9f + world.getRandom().nextFloat() * 0.2f);

            // si querés que NO consuma el ítem, comentá lo de abajo
            if (!user.getAbilities().creativeMode) {
                stack.decrement(1);
            }
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        return TypedActionResult.success(stack, world.isClient());
    }
}
