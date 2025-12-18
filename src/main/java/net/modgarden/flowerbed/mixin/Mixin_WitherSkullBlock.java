package net.modgarden.flowerbed.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WitherSkullBlock;
import net.modgarden.flowerbed.registry.FlowerbedGameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WitherSkullBlock.class)
public class Mixin_WitherSkullBlock {
	@Inject(method = "checkSpawn(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V", at = @At("HEAD"), cancellable = true)
	private static void flowerbed$preventWitherSpawning(Level level, BlockPos pos, CallbackInfo ci) {
		if (level instanceof ServerLevel serverLevel && serverLevel.getGameRules().get(FlowerbedGameRules.DISABLE_WITHER_SPAWNING)) {
			ci.cancel();
		}
	}
}
