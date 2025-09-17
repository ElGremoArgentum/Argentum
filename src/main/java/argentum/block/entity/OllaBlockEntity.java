package argentum.block.entity;

import argentum.Argentum;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.FurnaceScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;
import net.minecraft.world.World;

/**
 * Horno a Aceite:
 * - Combustible: #argentum:aceite_combustible
 * - Inputs permitidos: #argentum:aceite_horneables
 * - Recetas: vanilla smelting
 * - GUI: vanilla (FurnaceScreenHandler)
 */
public class OllaBlockEntity extends AbstractFurnaceBlockEntity {

    // Tags de datos
    private static final TagKey<net.minecraft.item.Item> TAG_COMBUSTIBLE =
            TagKey.of(RegistryKeys.ITEM, Identifier.of("argentum", "aceite_combustible"));
    private static final TagKey<net.minecraft.item.Item> TAG_HORNEABLES =
            TagKey.of(RegistryKeys.ITEM, Identifier.of("argentum", "aceite_horneables"));

    public OllaBlockEntity(BlockPos pos, BlockState state) {
        super(Argentum.OLLA_BE, pos, state, RecipeType.SMELTING);
    }

    /* Título de la pantalla (lang: container.argentum.horno_aceite) */
    // Nota: en algunos mapeos este método existe; en otros hay getDisplayName(); dejamos éste:
    protected Text getContainerName() {
        return Text.translatable("container.argentum.olla");
    }

    /* Combustible: solo aceite (tag) */
    protected boolean isFuel(ItemStack stack) {
        return stack.isIn(TAG_COMBUSTIBLE);
    }

    /* Validación por slot al insertar manualmente (GUI) */
    public boolean isValid(int slot, ItemStack stack) {
        if (slot == 0) return stack.isIn(TAG_HORNEABLES);   // INPUT
        if (slot == 1) return stack.isIn(TAG_COMBUSTIBLE);  // FUEL
        return false;                                       // OUTPUT
    }

    /* Hoppers: qué pueden insertar desde lados */
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        if (slot == 0) return stack.isIn(TAG_HORNEABLES);   // INPUT
        if (slot == 1) return stack.isIn(TAG_COMBUSTIBLE);  // FUEL
        return false;
    }

    /* Hoppers: qué pueden extraer */
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return slot == 2; // solo OUTPUT
    }

    /* === GUI vanilla del horno ===
       Dependiendo del mapeo, el abstract requerido puede llamarse:
       - createScreenHandler(int, PlayerInventory)  (común en 1.20/1.21)
       - createMenu(int, PlayerInventory)           (algunas variantes)
       Implementamos ambos; el compilador usará el que exista como abstract. */

    // Versión 1 (la más común en 1.21):
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new FurnaceScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    // Versión 2 (por si tu mapeo usa este nombre):
    @SuppressWarnings("unused")
    protected ScreenHandler createMenu(int syncId, PlayerInventory playerInventory) {
        return new FurnaceScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public static void tick(World world, BlockPos pos, BlockState state, OllaBlockEntity be) {
        AbstractFurnaceBlockEntity.tick(world, pos, state, be);
    }

}
