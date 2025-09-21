package net.modgarden.flowerbed.mixin.showcase.spawning;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawner;
import net.modgarden.flowerbed.annotation.PatchMetadata;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@PatchMetadata(
		id = "allow_trial_spawning",
		description = "Allows Trial Spawners to spawn when the doMobSpawning GameRule is false."
)
@Mixin(TrialSpawner.class)
public class Mixin_TrialSpawner {
	@WrapOperation(
			method = "canSpawnInLevel",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z")
	)
	private boolean flowerbed$allowSpawning(
			GameRules instance,
			GameRules.Key<GameRules.BooleanValue> key,
			Operation<Boolean> original
	) {
		return true;
	}
}
