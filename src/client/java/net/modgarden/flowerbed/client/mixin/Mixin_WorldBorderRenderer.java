package net.modgarden.flowerbed.client.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WorldBorderRenderer;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.phys.Vec3;
import net.modgarden.flowerbed.client.render.OpacityState;
import net.modgarden.flowerbed.registry.FlowerbedGameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WorldBorderRenderer.class)
public class Mixin_WorldBorderRenderer {
	@WrapMethod(method = "render")
	private void flowerbed$cancelRender(
			WorldBorder worldBorder,
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

	@WrapOperation(
			method = "render",
			at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderColor(FFFF)V", ordinal = 0)
	)
	private void flowerbed$changeAlpha(
			float f,
			float g,
			float h,
			float i,
			Operation<Void> original
	) {
		original.call(f, g, h, i * OpacityState.getAlpha(Minecraft.getInstance().getDeltaTracker().getRealtimeDeltaTicks()));
	}
}
