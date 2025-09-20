package argentum.tag;

import argentum.Argentum;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {

    public static class Items {
        // Tag para que la Olla reconozca combustibles exclusivos (grasa, aceite, etc.)
        public static final TagKey<Item> COMBUSTIBLES_OLLA =
                TagKey.of(RegistryKeys.ITEM, Identifier.of(Argentum.MOD_ID, "combustibles_olla"));
    }

    private ModTags() {}
}
