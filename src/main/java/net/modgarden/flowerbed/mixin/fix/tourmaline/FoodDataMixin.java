package net.modgarden.flowerbed.mixin.fix.tourmaline;

import net.minecraft.world.food.FoodData;
import net.modgarden.flowerbed.duck.tourmaline.ClientFoodLimiter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = FoodData.class, priority = 2000)
public class FoodDataMixin implements ClientFoodLimiter {
    @SuppressWarnings("MixinAnnotationTarget")
    @Shadow
    int tourmaline$maxFood;

    @Override
    public void doing_it_myself$setMaxFood(int maxFood) {
        this.tourmaline$maxFood = maxFood;
    }
}
