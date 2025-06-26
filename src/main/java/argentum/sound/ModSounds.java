package argentum.sound;

import argentum.Argentum;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

    public static final SoundEvent MATE_RUIDO = registerSoundEvent("mate_ruido");
    public static final SoundEvent TERMO_RUIDO = registerSoundEvent("termo_ruido");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(Argentum.MOD_ID, name); // ← CORREGIDO: .of() en lugar de new
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        Argentum.LOGGER.info("Registering Mod Sounds for " + Argentum.MOD_ID);
    }
}