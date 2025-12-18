package net.modgarden.flowerbed.client.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WorldBorderRenderer;
import net.minecraft.client.renderer.state.WorldBorderRenderState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.phys.Vec3;
import net.modgarden.flowerbed.client.render.OpacityState;
import net.modgarden.flowerbed.registry.FlowerbedGameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldBorderRenderer.class)
public class Mixin_WorldBorderRenderer {
	@WrapMethod(method = "render")
	private void flowerbed$cancelRender(
			WorldBorderRenderState worldBorder,
			Vec3 cameraPosition,
			double renderDistance,
			double farPlaneDepth,
			Operation<Void> original
	) {
		if (FlowerbedGameRules.showWorldBorder)
		{
			OpacityState.update(worldBorder, cameraPosition);
			original.call(worldBorder, cameraPosition, renderDistance, farPlaneDepth);
		}
	}

	/*
	@Inject(
			method = "extract",
			at = @At(value = "RETURN")
	)
	private void flowerbed$changeAlpha(
			WorldBorder worldBorder, float f, Vec3 vec3, double d, WorldBorderRenderState worldBorderRenderState, CallbackInfo ci
	) {
		worldBorderRenderState.alpha = worldBorderRenderState.alpha * OpacityState.getAlpha(Minecraft.getInstance().getDeltaTracker().getRealtimeDeltaTicks());
	}
	 */
}
