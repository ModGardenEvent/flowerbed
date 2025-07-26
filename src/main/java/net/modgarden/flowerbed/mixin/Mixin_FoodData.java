package net.modgarden.flowerbed.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.food.FoodData;
import net.modgarden.flowerbed.registry.FlowerbedGameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FoodData.class)
public class Mixin_FoodData {
	@WrapWithCondition(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodData;addExhaustion(F)V", ordinal = 1))
	private boolean flowerbed$cancelTickingHungerWithoutSaturation(FoodData instance, float f, @Local(argsOnly = true) ServerPlayer player) {
		return !player.serverLevel().getGameRules().getBoolean(FlowerbedGameRules.DISABLE_EXHAUSTION);
	}
}
