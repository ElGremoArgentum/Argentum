package argentum.registry;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class ModTags {
    public static final TagKey<Item> ACEITES = TagKey.of(RegistryKeys.ITEM, Identifier.of("argentum","aceites"));
    public static final TagKey<Item> FRITABLES = TagKey.of(RegistryKeys.ITEM, Identifier.of("argentum","fritables"));
    private ModTags() {}
}
