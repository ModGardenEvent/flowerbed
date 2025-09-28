package net.modgarden.flowerbed.mixin.showcase.griefing;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.modgarden.flowerbed.annotation.PatchMetadata;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@PatchMetadata(
		id = "dont_trample_crops",
		description = "Don't allow players and entities to trample crops."
)
@Mixin(FarmBlock.class)
public class Mixin_FarmBlock {
	@WrapOperation(
			method = "fallOn",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/block/FarmBlock;turnToDirt(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V"
			)
	)
	private void flowerbed$preventTrampling(
			Entity entity,
			BlockState state,
			Level level,
			BlockPos pos,
			Operation<Void> original
	) {
		// no
	}
}
