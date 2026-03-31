package net.modgarden.flowerbed.client.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.Gui;
import net.modgarden.flowerbed.registry.FlowerbedGameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Gui.class)
public class Mixin_Gui {
	@ModifyExpressionValue(method = "extractFood", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodData;getSaturationLevel()F"))
	private float flowerbed$dontRenderHungerBumping(float original) {
		if (FlowerbedGameRules.disablePassiveHungerLossGameruleEnabled) {
			return 1.0F; // The check is for <= 0.0F, so really any value above 0 will do.
		}
		return original;
	}
}
