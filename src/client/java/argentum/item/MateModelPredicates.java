package argentum.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class MateModelPredicates {

    public static void registerPredicates() {
        // Registra el predicate para el mate
        ModelPredicateProviderRegistry.register(
                ModItem.MATE, // Reemplazá por tu instancia del item mate
                Identifier.of("argentum", "in_hand"),
                (stack, world, entity, seed) -> {
                    // Si el item está en la mano principal o secundaria, mostrar modelo 3D
                    if (entity != null &&
                            (entity.getMainHandStack() == stack || entity.getOffHandStack() == stack)) {
                        return 1.0F;
                    }
                    return 0.0F;
                }
        );
    }
}