package argentum.item;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponent {
    public static final FoodComponent MATE = new FoodComponent.Builder().snack().saturationModifier(3f)
            .nutrition(3).build();
    public static final FoodComponent MATELISTO = new FoodComponent.Builder().snack().saturationModifier(3f)
            .nutrition(3).build();
    public static final FoodComponent INFUSION = new FoodComponent.Builder().snack().saturationModifier(3f)
            .nutrition(3).build();
}
