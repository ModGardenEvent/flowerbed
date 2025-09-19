package net.modgarden.flowerbed.client.render;

import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.phys.Vec3;
import net.modgarden.flowerbed.registry.FlowerbedGameRules;

/**
 * Copied from Barricade lol
 */
public final class OpacityState {
	private static float fadeTime = 0.0f;
	private static boolean isFading = false;
	private static boolean isChanging = false;
	private static boolean shouldRender = false;

	public static void update(WorldBorder worldBorder, Vec3 cameraPosition) {
		int fadeTicks = FlowerbedGameRules.worldBorderFadeTicks;
		int fadeDistance = FlowerbedGameRules.worldBorderRenderDistance;
		boolean shouldRender = worldBorder.getDistanceToBorder(cameraPosition.x(), cameraPosition.z()) < fadeDistance;
		boolean fadeBarriers = fadeTicks > 0.0f;
		if (fadeBarriers && OpacityState.shouldRender && !shouldRender) {
			OpacityState.isFading = true;
			if (!OpacityState.isChanging) {
				OpacityState.fadeTime = fadeTicks;
			}
			OpacityState.isChanging = true;
		} else if (fadeBarriers && !OpacityState.shouldRender && shouldRender) {
			OpacityState.isFading = false;
			if (!OpacityState.isChanging) {
				OpacityState.fadeTime = 0.0f;
			}
			OpacityState.isChanging = true;
		}
		OpacityState.shouldRender = shouldRender;
	}

	public static float getAlpha(float deltaTick) {
		int fadeTicks = FlowerbedGameRules.worldBorderFadeTicks;
		if (OpacityState.isFading && OpacityState.fadeTime >= 0.0f) {
			OpacityState.fadeTime -= deltaTick;
			return OpacityState.fadeTime / fadeTicks;
		} else if (!OpacityState.isFading && OpacityState.fadeTime <= fadeTicks) {
			OpacityState.fadeTime += deltaTick;
			return OpacityState.fadeTime / fadeTicks;
		} else {
			OpacityState.isChanging = false;
			return OpacityState.shouldRender ? 1.0f : 0.0f;
		}
	}

	private OpacityState() {}
}
