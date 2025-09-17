package argentum.screen;

import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<OllaScreenHandler> OLLA = Registry.register(
            Registries.SCREEN_HANDLER,
            Identifier.of("argentum", "olla"),
            new ScreenHandlerType<>(OllaScreenHandler::new, FeatureFlags.VANILLA_FEATURES)
    );

    public static void register() {
        // Llamar desde Argentum.java si querés, aunque el static ya registra.
    }
}
