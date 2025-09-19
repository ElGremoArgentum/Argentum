package argentum.item;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponent {
    public static final FoodComponent MATE = new FoodComponent.Builder().snack().saturationModifier(3f)
            .nutrition(3).build();
    public static final FoodComponent MATELISTO = new FoodComponent.Builder().snack().saturationModifier(3f)
            .nutrition(3).build();
    public static final FoodComponent INFUSION = new FoodComponent.Builder().snack().saturationModifier(3f)
            .nutrition(3).build();
    public static final FoodComponent FRITO = new FoodComponent.Builder()
            .nutrition(6)          // “muslitos” de hambre
            .saturationModifier(0.6f)
            //.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 0), 1.0f) // si querés fuerza 10s
            .build();
    public static final FoodComponent CRUDOS = new FoodComponent.Builder()
            .nutrition(2)            // 1 muslito (2 = medio muslo visualmente)
            .saturationModifier(0.1f)
            // .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 200, 0), 1.0f) // opcional
            .build();
}
