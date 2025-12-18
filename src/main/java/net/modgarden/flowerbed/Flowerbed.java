package net.modgarden.flowerbed;

import me.lucko.fabric.api.permissions.v0.Permissions;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.modgarden.flowerbed.command.FlowerbedCommands;
import net.modgarden.flowerbed.network.FlowerbedNetwork;
import net.modgarden.flowerbed.network.clientbound.ClientboundShowWorldBorderPacket;
import net.modgarden.flowerbed.network.clientbound.ClientboundWorldBorderFadeTicksPacket;
import net.modgarden.flowerbed.network.clientbound.ClientboundWorldBorderRenderDistancePacket;
import net.modgarden.flowerbed.network.clientbound.SendPerPlayerPvpValueClientboundPacket;
import net.modgarden.flowerbed.permission.FlowerbedPermissions;
import net.modgarden.flowerbed.registry.FlowerbedAttachments;
import net.modgarden.flowerbed.registry.FlowerbedGameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Flowerbed implements ModInitializer {
	public static final String MOD_ID = "flowerbed";
	public static final String MOD_NAME = "Flowerbed";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	@Override
	public void onInitialize() {
		FlowerbedAttachments.init();
		FlowerbedGameRules.init();
		FlowerbedNetwork.init();

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
				FlowerbedCommands.register(dispatcher, registryAccess));

		ServerPlayerEvents.JOIN.register(serverPlayer -> {
			if (serverPlayer.level() != null) {
				var gameRules = serverPlayer.level().getGameRules();
				ServerPlayNetworking.send(serverPlayer, new SendPerPlayerPvpValueClientboundPacket(gameRules.get(FlowerbedGameRules.PER_PLAYER_PVP)));
				ServerPlayNetworking.send(serverPlayer, new ClientboundShowWorldBorderPacket(gameRules.get(FlowerbedGameRules.SHOW_WORLD_BORDER)));
				ServerPlayNetworking.send(serverPlayer, new ClientboundWorldBorderRenderDistancePacket(gameRules.get(FlowerbedGameRules.WORLD_BORDER_RENDER_DISTANCE)));
				ServerPlayNetworking.send(serverPlayer, new ClientboundWorldBorderFadeTicksPacket(gameRules.get(FlowerbedGameRules.WORLD_BORDER_FADE_TICKS)));

				// Ensure players without permission are put in Adventure Mode
				if (!Permissions.check(serverPlayer, FlowerbedPermissions.NON_ADVENTURE)) {
					serverPlayer.setGameMode(GameType.ADVENTURE);
				}
			}
		});
		GameRuleEvents.changeCallback(FlowerbedGameRules.PER_PLAYER_PVP).register((booleanValue, server) -> {
			for (ServerPlayer serverPlayer : server.getPlayerList().getPlayers()) {
				ServerPlayNetworking.send(serverPlayer, new SendPerPlayerPvpValueClientboundPacket(booleanValue));
			}
		});
		GameRuleEvents.changeCallback(FlowerbedGameRules.SHOW_WORLD_BORDER).register((booleanValue, server) -> {
			for (ServerPlayer serverPlayer : server.getPlayerList().getPlayers()) {
				ServerPlayNetworking.send(serverPlayer, new ClientboundShowWorldBorderPacket(booleanValue));
			}
		});
		GameRuleEvents.changeCallback(FlowerbedGameRules.WORLD_BORDER_RENDER_DISTANCE).register((integerValue, server) -> {
			for (ServerPlayer serverPlayer : server.getPlayerList().getPlayers()) {
				ServerPlayNetworking.send(serverPlayer, new ClientboundWorldBorderRenderDistancePacket(integerValue));
			}
		});
		GameRuleEvents.changeCallback(FlowerbedGameRules.WORLD_BORDER_FADE_TICKS).register((integerValue, server) -> {
			for (ServerPlayer serverPlayer : server.getPlayerList().getPlayers()) {
				ServerPlayNetworking.send(serverPlayer, new ClientboundWorldBorderFadeTicksPacket(integerValue));
			}
		});
	}

	public static Identifier asResource(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
