package net.modgarden.flowerbed.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.modgarden.flowerbed.registry.FlowerbedGameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CarvedPumpkinBlock.class)
public class Mixin_CarvedPumpkinBlock {
	@Inject(method = "trySpawnGolem", at = @At("HEAD"), cancellable = true)
	private static void flowerbed$preventWitherSpawning(Level level, BlockPos pos, CallbackInfo ci) {
		if (level instanceof ServerLevel serverLevel && serverLevel.getGameRules().get(FlowerbedGameRules.DISABLE_GOLEM_SPAWNING)) {
			ci.cancel();
		}
	}
}
