package net.modgarden.flowerbed.client.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.multiplayer.ServerSelectionList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerSelectionList.class)
public final class Mixin_SeverSelectionList {
	@SuppressWarnings("unused") // Mixin IDEA plugin can't find this
	@Mixin(ServerSelectionList.OnlineServerEntry.class)
	private static final class Mixin_OnlineServerEntry {
		@WrapMethod(method = "swap")
		private void flowerbed$disableSwap(int pos1, int pos2, Operation<Void> original) {}

		@Inject(
				method = "render",
				at = @At(
						value = "INVOKE",
						target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIII)V",
						ordinal = 3
				),
				cancellable = true
		)
		private void flowerbed$cancelMoveButton3(
				GuiGraphics guiGraphics,
				int index,
				int top,
				int left,
				int width,
				int height,
				int mouseX,
				int mouseY,
				boolean hovering,
				float partialTick,
				CallbackInfo ci
		) {
			ci.cancel();
		}

		@Inject(
				method = "render",
				at = @At(
						value = "INVOKE",
						target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIII)V",
						ordinal = 4
				),
				cancellable = true
		)
		private void flowerbed$cancelMoveButton4(
				GuiGraphics guiGraphics,
				int index,
				int top,
				int left,
				int width,
				int height,
				int mouseX,
				int mouseY,
				boolean hovering,
				float partialTick,
				CallbackInfo ci
		) {
			ci.cancel();
		}

		@Inject(
				method = "render",
				at = @At(
						value = "INVOKE",
						target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIII)V",
						ordinal = 5
				),
				cancellable = true
		)
		private void flowerbed$cancelMoveButton5(
				GuiGraphics guiGraphics,
				int index,
				int top,
				int left,
				int width,
				int height,
				int mouseX,
				int mouseY,
				boolean hovering,
				float partialTick,
				CallbackInfo ci
		) {
			ci.cancel();
		}

		@Inject(
				method = "render",
				at = @At(
						value = "INVOKE",
						target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIII)V",
						ordinal = 6
				),
				cancellable = true
		)
		private void flowerbed$cancelMoveButton6(
				GuiGraphics guiGraphics,
				int index,
				int top,
				int left,
				int width,
				int height,
				int mouseX,
				int mouseY,
				boolean hovering,
				float partialTick,
				CallbackInfo ci
		) {
			ci.cancel();
		}
	}
}
