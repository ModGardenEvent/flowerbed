package net.modgarden.flowerbed.client.mixin.showcase.screen;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.screens.multiplayer.ServerSelectionList;
import net.modgarden.flowerbed.annotation.PatchMetadata;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@PatchMetadata(
		id = "prevent_first_server_movement",
		description = "Prevents the first server in the server list from being moved."
)
@Mixin(ServerSelectionList.OnlineServerEntry.class)
public class Mixin_ServerSelectionListOnlineServerEntry {
	@ModifyVariable(
			method = "extractContent",
			at = @At(value = "INVOKE_ASSIGN", target = "Ljava/util/List;indexOf(Ljava/lang/Object;)I"),
			name = "index")
	private int flowerbed$modifyOnlineServerIndex(int index) {
		return index - 1;
	}

	@ModifyExpressionValue(
			method = "extractContent",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ServerList;size()I")
	)
	private int flowerbed$modifyRequiredDownArrowIndexRender(int original, @Local(name = "index") int index) {
		if (index == -1) {
			return Integer.MIN_VALUE + 1; // Add 1 to avoid integer underflow.
		}
		return original;
	}

	@ModifyVariable(
			method = "keyPressed",
			at = @At(value = "INVOKE_ASSIGN", target = "Ljava/util/List;indexOf(Ljava/lang/Object;)I"),
			name = "currentIndex"
	)
	private int flowerbed$modifyKeyPressWidgetIndex(int currentIndex) {
		return Math.max(currentIndex - 1, -1);
	}

	@ModifyVariable(
			method = "mouseClicked",
			at = @At(value = "INVOKE_ASSIGN", target = "Ljava/util/List;indexOf(Ljava/lang/Object;)I"),
			name = "currentIndex"
	)
	private int flowerbed$modifyMouseClickWidgetIndex(int currentIndex) {
		return Math.max(currentIndex - 1, -1);
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

	@ModifyArgs(
			method = "swap",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/multiplayer/ServerSelectionList;access$300(Lnet/minecraft/client/gui/screens/multiplayer/ServerSelectionList;II)V")
	)
	private void flowerbed$modifySwapIndexes(Args args) {
		args.set(1, (int)args.get(1) + 1);
		args.set(2, (int)args.get(2) + 1);
	}
}
