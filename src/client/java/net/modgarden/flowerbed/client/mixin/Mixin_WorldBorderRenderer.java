package net.modgarden.flowerbed.client.mixin;

import net.minecraft.client.renderer.WorldBorderRenderer;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.phys.Vec3;
import net.modgarden.flowerbed.registry.FlowerbedGameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldBorderRenderer.class)
public class Mixin_WorldBorderRenderer {
	@Inject(
			method = "render",
			at = @At("HEAD"),
			cancellable = true
	)
	private void flowerbed$cancelRender(
			WorldBorder worldBorder,
			Vec3 cameraPosition,
			double renderDistance,
			double farPlaneDepth,
			CallbackInfo ci
	) {
		if (!FlowerbedGameRules.showWorldBorder) {
			ci.cancel();
		}
	}
}
