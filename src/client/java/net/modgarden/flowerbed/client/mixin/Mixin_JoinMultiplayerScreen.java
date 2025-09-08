package net.modgarden.flowerbed.client.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.layouts.EqualSpacingLayout;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(JoinMultiplayerScreen.class)
public final class Mixin_JoinMultiplayerScreen {
	@ModifyConstant(
			method = "init",
			constant = @Constant(intValue = 308)
	)
	private int flowerbed$decreaseButtonWidth(int constant) {
		return (int) (constant / 1.5f);
	}

	@WrapOperation(
			method = "init",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/gui/screens/multiplayer/JoinMultiplayerScreen;addRenderableWidget(Lnet/minecraft/client/gui/components/events/GuiEventListener;)Lnet/minecraft/client/gui/components/events/GuiEventListener;",
					ordinal = 3
			)
	)
	private GuiEventListener flowerbed$cancelRenderAddButton(
			JoinMultiplayerScreen instance,
			GuiEventListener guiEventListener,
			Operation<GuiEventListener> original
	) {
		return guiEventListener;
	}

	@WrapOperation(
			method = "init",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/gui/screens/multiplayer/JoinMultiplayerScreen;addRenderableWidget(Lnet/minecraft/client/gui/components/events/GuiEventListener;)Lnet/minecraft/client/gui/components/events/GuiEventListener;",
					ordinal = 4
			)
	)
	private GuiEventListener flowerbed$cancelRenderEditButton(
			JoinMultiplayerScreen instance,
			GuiEventListener guiEventListener,
			Operation<GuiEventListener> original
	) {
		return guiEventListener;
	}

	@WrapOperation(
			method = "init",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/gui/screens/multiplayer/JoinMultiplayerScreen;addRenderableWidget(Lnet/minecraft/client/gui/components/events/GuiEventListener;)Lnet/minecraft/client/gui/components/events/GuiEventListener;",
					ordinal = 5
			)
	)
	private GuiEventListener flowerbed$cancelRenderDeleteButton(
			JoinMultiplayerScreen instance,
			GuiEventListener guiEventListener,
			Operation<GuiEventListener> original
	) {
		return guiEventListener;
	}

	@WrapOperation(
			method = "init",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/gui/layouts/EqualSpacingLayout;addChild(Lnet/minecraft/client/gui/layouts/LayoutElement;)Lnet/minecraft/client/gui/layouts/LayoutElement;",
					ordinal = 2
			)
	)
	private LayoutElement flowerbed$cancelAddButton(EqualSpacingLayout instance, LayoutElement child, Operation<LayoutElement> original) {
		return null;
	}

	@WrapOperation(
			method = "init",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/gui/layouts/EqualSpacingLayout;addChild(Lnet/minecraft/client/gui/layouts/LayoutElement;)Lnet/minecraft/client/gui/layouts/LayoutElement;",
					ordinal = 3
			)
	)
	private LayoutElement flowerbed$cancelEditButton(EqualSpacingLayout instance, LayoutElement child, Operation<LayoutElement> original) {
		return null;
	}

	@WrapOperation(
			method = "init",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/gui/layouts/EqualSpacingLayout;addChild(Lnet/minecraft/client/gui/layouts/LayoutElement;)Lnet/minecraft/client/gui/layouts/LayoutElement;",
					ordinal = 4
			)
	)
	private LayoutElement flowerbed$cancelDeleteButton(EqualSpacingLayout instance, LayoutElement child, Operation<LayoutElement> original) {
		return null;
	}
}
