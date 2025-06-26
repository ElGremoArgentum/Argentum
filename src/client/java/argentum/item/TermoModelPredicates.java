package argentum.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class TermoModelPredicates {
    public static void registerPredicates() {
        // Predicate para termo
        ModelPredicateProviderRegistry.register(
                ModItem.TERMO, // tu instancia del termo
                Identifier.of("argentum", "in_hand_termo"),
                (stack, world, entity, seed) -> {
                    if (entity != null && (entity.getMainHandStack() == stack || entity.getOffHandStack() == stack)) {
                        return 1.0F;
                    }
                    return 0.0F;
                }
        );

        // Predicate para termo_argento
        ModelPredicateProviderRegistry.register(
                ModItem.TERMO_ARGENTO, // tu instancia del termo con bandera
                Identifier.of("argentum", "in_hand_termo_argento"),
                (stack, world, entity, seed) -> {
                    if (entity != null && (entity.getMainHandStack() == stack || entity.getOffHandStack() == stack)) {
                        return 1.0F;
                    }
                    return 0.0F;
                }
        );
    }
}