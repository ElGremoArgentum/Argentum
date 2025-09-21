package argentum.registry;

import argentum.Argentum;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public final class ModSounds {
    public static final SoundEvent OLLA_FRY_START = SoundEvent.of(Identifier.of(Argentum.MOD_ID, "olla_fry_start"));
    public static final SoundEvent OLLA_FRY_END   = SoundEvent.of(Identifier.of(Argentum.MOD_ID, "olla_fry_end"));

    public static void registerAll() {
        Registry.register(Registries.SOUND_EVENT, OLLA_FRY_START.getId(), OLLA_FRY_START);
        Registry.register(Registries.SOUND_EVENT, OLLA_FRY_END.getId(), OLLA_FRY_END);
    }

    private ModSounds() {}
}
