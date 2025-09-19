package net.modgarden.flowerbed.client.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.renderer.WorldBorderRenderer;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.phys.Vec3;
import net.modgarden.flowerbed.registry.FlowerbedGameRules;
import org.spongepowered.asm.mixin.Mixin;

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
			original.call(worldBorder, cameraPosition, renderDistance, farPlaneDepth);
		}
	}
}
