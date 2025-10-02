package net.modgarden.flowerbed.mixin.fix.pigs_eat_mushrooms;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.hasdahlias.EatShroomsGoal;
import net.minecraft.world.level.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@Pseudo
@Mixin(EatShroomsGoal.class)
public class Mixin_EatShroomsGoal {
	@WrapOperation(
			method = "tick",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z")
	)
	private boolean flowerbed$allowPigsEatMushrooms(
			GameRules instance, GameRules.Key<GameRules.BooleanValue> key, Operation<Boolean> original
	) {
		return true;
	}
}
