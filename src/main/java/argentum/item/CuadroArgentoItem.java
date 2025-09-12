package argentum.item;

import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CuadroArgentoItem extends Item {
    // Ordená de mayor a menor: probamos Iguazú (4x3) primero y luego Obelisco (1x3)
    private static final List<RegistryKey<PaintingVariant>> VARIANTS = List.of(
            key("iguazu"),
            key("obelisco"),
            key("tres_estrellas"),
            key("islas_malvinas")
            // podés sumar más: key("fileteado"), key("perito"), etc.
    );

    public static RegistryKey<PaintingVariant> key(String path) {
        return RegistryKey.of(RegistryKeys.PAINTING_VARIANT, Identifier.of("argentum", path));
    }

    public CuadroArgentoItem(Settings settings) { super(settings); }

    @Override
    public ActionResult useOnBlock(ItemUsageContext ctx) {
        Direction face = ctx.getSide();
        if (!face.getAxis().isHorizontal()) return ActionResult.PASS;

        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos().offset(face);

        var reg = world.getRegistryManager().get(RegistryKeys.PAINTING_VARIANT);
        List<RegistryEntry<PaintingVariant>> fitting = new ArrayList<>();

        // juntamos las variantes que ENTRAN en el hueco actual
        for (var k : VARIANTS) {
            var entryOpt = reg.getEntry(k);
            if (entryOpt.isEmpty()) continue;
            var entry = entryOpt.get();

            // instancia temporal para testear encastre
            PaintingEntity test = new PaintingEntity(world, pos, face, entry);
            if (test.canStayAttached()) fitting.add(entry);
        }

        if (fitting.isEmpty()) return ActionResult.CONSUME_PARTIAL;

        // elección SIN estado: si entra más de una, alternamos usando tiempo/posición
        RegistryEntry<PaintingVariant> chosen;
        if (fitting.size() == 1) {
            chosen = fitting.get(0);
        } else {
            long seed = world.getTime() + pos.asLong();
            int idx = (int)Math.floorMod(seed, fitting.size());
            chosen = fitting.get(idx);
        }

        PaintingEntity painting = new PaintingEntity(world, pos, face, chosen);
        if (!painting.canStayAttached()) return ActionResult.CONSUME_PARTIAL;

        if (!world.isClient) {
            world.spawnEntity(painting);
            ctx.getStack().decrementUnlessCreative(1, ctx.getPlayer());
        }
        return ActionResult.SUCCESS;
    }
}
