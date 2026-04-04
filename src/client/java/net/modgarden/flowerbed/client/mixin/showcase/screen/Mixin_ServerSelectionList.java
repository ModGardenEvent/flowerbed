package net.modgarden.flowerbed.client.mixin.showcase.screen;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
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

import java.util.ArrayList;

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

	@ModifyExpressionValue(method =  "refreshEntries", at = @At(value = "NEW", target = "(Ljava/util/Collection;)Ljava/util/ArrayList;"))
	private ArrayList<ServerSelectionList.Entry> flowerbed$forceModGardenServerIntoServerList(ArrayList<ServerSelectionList.Entry> original) {
		ArrayList<ServerSelectionList.Entry> entries = new ArrayList<>();
		// TODO: Unhardcode the server IP.
		entries.add(((ServerSelectionList)(Object)this).new OnlineServerEntry(screen, new ServerData("Mod Garden", "mc.modgarden.net", ServerData.Type.OTHER)));
		entries.addAll(original);
		return entries;
	}
}
