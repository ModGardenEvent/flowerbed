package net.modgarden.flowerbed;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GameRules;
import net.modgarden.flowerbed.command.FlowerbedCommands;
import net.modgarden.flowerbed.network.FlowerbedNetwork;
import net.modgarden.flowerbed.network.clientbound.ClientboundShowWorldBorderPacket;
import net.modgarden.flowerbed.network.clientbound.SendPerPlayerPvpValueClientboundPacket;
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
			if (serverPlayer.getServer() != null) {
				GameRules gameRules = serverPlayer.getServer().getGameRules();
				ServerPlayNetworking.send(serverPlayer, new SendPerPlayerPvpValueClientboundPacket(gameRules.getBoolean(FlowerbedGameRules.PER_PLAYER_PVP)));
				ServerPlayNetworking.send(serverPlayer, new ClientboundShowWorldBorderPacket(gameRules.getBoolean(FlowerbedGameRules.SHOW_WORLD_BORDER)));
			}
		});
	}

	public static ResourceLocation asResource(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}
