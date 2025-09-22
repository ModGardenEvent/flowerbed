package net.modgarden.flowerbed.mixin.showcase.gamemode;

import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.modgarden.flowerbed.annotation.PatchMetadata;
import net.modgarden.flowerbed.permission.FlowerbedPermissions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@PatchMetadata(
		id = "force_adventure",
		description = "Force Adventure Mode for players without permission."
)
@Mixin(ServerPlayer.class)
public abstract class Mixin_ServerPlayer {
	@Shadow
	public abstract boolean setGameMode(GameType gameMode);

	@Inject(
			method = "tick",
			at = @At("RETURN")
	)
	private void flowerbed$forceAdventure(
			CallbackInfo ci
	) {
		if (!Permissions.check((ServerPlayer) (Object) this, FlowerbedPermissions.NON_ADVENTURE)) {
			this.setGameMode(GameType.ADVENTURE);
		}
	}
}
