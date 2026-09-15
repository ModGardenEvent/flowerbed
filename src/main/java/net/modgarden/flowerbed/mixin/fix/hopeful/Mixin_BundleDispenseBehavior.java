package net.modgarden.flowerbed.mixin.fix.hopeful;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import doublepi.hopeful.evenly.content.bundle.BundleDispenseBehavior;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BundleDispenseBehavior.class)
public class Mixin_BundleDispenseBehavior {
	@WrapOperation(
			method = "tryPlacingBundle",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;setBlockAndUpdate(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z")
	)
	private static boolean flowerbed$fixBundlesBreakingBlocks(
			Level level, BlockPos pos, BlockState blockState, Operation<Boolean> original
	) {
		if (level.isEmptyBlock(pos)) {
			return original.call(level, pos, blockState);
		} else {
			return false;
		}
	}
}
