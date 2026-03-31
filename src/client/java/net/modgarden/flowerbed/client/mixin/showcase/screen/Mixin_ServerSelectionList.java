package net.modgarden.flowerbed.client.mixin.showcase.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.multiplayer.ServerSelectionList;
import net.minecraft.client.multiplayer.ServerData;
import net.modgarden.flowerbed.annotation.PatchMetadata;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@PatchMetadata(
		id = "force_mod_garden_server",
		description = "Forces the Mod Garden server to appear at the top of the server list."
)
@Mixin(ServerSelectionList.class)
public final class Mixin_ServerSelectionList extends ObjectSelectionList<ServerSelectionList.Entry> {
	@Shadow
	@Final
	private JoinMultiplayerScreen screen;

	public Mixin_ServerSelectionList(Minecraft minecraft, int width, int height, int y, int itemHeight) {
		super(minecraft, width, height, y, itemHeight);
	}

	@Inject(method =  "refreshEntries", at = @At(value = "INVOKE", target = "Ljava/util/List;addAll(Ljava/util/Collection;)Z", ordinal = 0))
	private void flowerbed$forceModGardenServerIntoServerList(CallbackInfo ci) {
		// TODO: Unhardcode the server IP.
		addEntry(((ServerSelectionList)(Object)this).new OnlineServerEntry(screen, new ServerData("Mod Garden", "mc.modgarden.net", ServerData.Type.OTHER)));
	}
}
