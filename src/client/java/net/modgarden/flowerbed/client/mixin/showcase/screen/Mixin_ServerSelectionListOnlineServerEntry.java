package net.modgarden.flowerbed.client.mixin.showcase.screen;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.screens.multiplayer.ServerSelectionList;
import net.modgarden.flowerbed.annotation.PatchMetadata;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@PatchMetadata(
		id = "prevent_first_server_movement",
		description = "Prevents the first server in the server list from being moved."
)
@Mixin(ServerSelectionList.OnlineServerEntry.class)
public class Mixin_ServerSelectionListOnlineServerEntry {
	@ModifyVariable(
			method = "extractContent",
			at = @At("HEAD"),
			ordinal = 0,
			argsOnly = true)
	private int flowerbed$modifyOnlineServerIndex(int index) {
		return index - 1;
	}

	@ModifyExpressionValue(
			method = "extractContent",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ServerList;size()I")
	)
	private int flowerbed$modifyRequiredDownArrowIndexRender(int original, @Local(ordinal = 0, argsOnly = true) int index) {
		if (index == -1) {
			return Integer.MIN_VALUE + 1; // Add 1 to avoid integer underflow.
		}
		return original;
	}

	@ModifyVariable(
			method = "keyPressed",
			at = @At(value = "INVOKE_ASSIGN", target = "Ljava/util/List;indexOf(Ljava/lang/Object;)I"),
			ordinal = 3
	)
	private int flowerbed$modifyKeyPressWidgetIndex(int value) {
		return Math.max(value - 1, -1);
	}

	@ModifyVariable(
			method = "mouseClicked",
			at = @At(value = "INVOKE_ASSIGN", target = "Ljava/util/List;indexOf(Ljava/lang/Object;)I"),
			ordinal = 1
	)
	private int flowerbed$modifyMouseClickWidgetIndex(int value) {
		return Math.max(value - 1, -1);
	}

	@ModifyExpressionValue(
			method = "mouseClicked",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ServerList;size()I")
	)
	private int flowerbed$preventDownArrowIndexClickAtBottom(int original, @Local(name = "currentIndex")  int currentIndex) {
		if (currentIndex == -1) {
			return Integer.MIN_VALUE + 1; // Add 1 to avoid integer underflow.
		}
		return original;
	}

	@ModifyArg(
			method = "swap",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ServerList;swap(II)V")
	)
	private int flowerbed$modifyServerSelectionEntry(int pos2) {
		return pos2 + 1;
	}
}
