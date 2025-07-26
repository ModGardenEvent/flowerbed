package net.modgarden.flowerbed.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.modgarden.flowerbed.registry.FlowerbedAttachments;
import net.modgarden.flowerbed.registry.FlowerbedGameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ServerPlayer.class)
public abstract class Mixin_ServerPlayer extends Player {
	public Mixin_ServerPlayer(Level level, BlockPos blockPos, float f, GameProfile gameProfile) {
		super(level, blockPos, f, gameProfile);
	}

	@Shadow
	public abstract ServerLevel serverLevel();

	@WrapWithCondition(method = "checkMovementStatistics", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;causeFoodExhaustion(F)V"))
	private boolean flowerbed$cancelMovementStatisticsFoodExhaustion(ServerPlayer instance, float exhaustion) {
		return !serverLevel().getGameRules().getBoolean(FlowerbedGameRules.DISABLE_EXHAUSTION);
	}

	@WrapWithCondition(method = "jumpFromGround", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;causeFoodExhaustion(F)V"))
	private boolean flowerbed$cancelJumpFoodExhaustion(ServerPlayer instance, float exhaustion) {
		return !serverLevel().getGameRules().getBoolean(FlowerbedGameRules.DISABLE_EXHAUSTION);
	}

	@SuppressWarnings("UnstableApiUsage")
	@ModifyReturnValue(method = "canHarmPlayer", at = @At("RETURN"))
	public boolean flowerbed$disallowPvP(boolean original, Player other) {
		if (!serverLevel().getGameRules().getBoolean(FlowerbedGameRules.PER_PLAYER_PVP) ||
				hasAttached(FlowerbedAttachments.ACCEPT_PVP) && other.hasAttached(FlowerbedAttachments.ACCEPT_PVP))
			return original;

		return false;
	}
}
