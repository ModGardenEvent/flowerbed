package net.modgarden.flowerbed.client.mixin.showcase.screen;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.multiplayer.ServerSelectionList;
import net.modgarden.flowerbed.annotation.PatchMetadata;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@PatchMetadata(
		id = "prevent_mod_garden_server_editing",
		description = "Prevents the Mod Garden Server in the server list from being edited or deleted."
)
@Mixin(JoinMultiplayerScreen.class)
public class Mixin_JoinMultiplayerScreen {
	@Shadow
	private Button editButton;

	@Shadow
	private Button deleteButton;

	@Inject(method = "onSelectedChange", at = @At(value = "TAIL"))
	private void flowerbed$disallowModGardenEditingAndDeletion(CallbackInfo ci, @Local ServerSelectionList.Entry entry) {
		if (entry instanceof ServerSelectionList.OnlineServerEntry onlineServerEntry && onlineServerEntry.getServerData().ip.equals("mc.modgarden.net")) {
			editButton.active = false;
			deleteButton.active = false;
		}
	}
}

