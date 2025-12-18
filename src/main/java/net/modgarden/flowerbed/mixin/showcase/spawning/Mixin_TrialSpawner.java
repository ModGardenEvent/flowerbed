package net.modgarden.flowerbed.mixin.showcase.spawning;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRules;
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
			at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/gamerules/GameRules;get(Lnet/minecraft/world/level/gamerules/GameRule;)Ljava/lang/Object;")
	)
	private <T> T flowerbed$allowSpawning(
			GameRules instance, GameRule<T> gameRule, Operation<T> original
	) {
		return (T) Boolean.TRUE;
	}
}
