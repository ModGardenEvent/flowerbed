package net.modgarden.flowerbed.client.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.modgarden.flowerbed.client.FlowerbedClient;
import net.modgarden.flowerbed.network.clientbound.*;
import net.modgarden.flowerbed.registry.FlowerbedGameRules;

public class FlowerbedNetworkClient {
	public static void init() {
		ClientPlayNetworking.registerGlobalReceiver(SendDisabledPassiveHungerLossValueClientboundPacket.TYPE, FlowerbedNetworkClient::handleDisabledPassiveHungerLossValue);
		ClientPlayNetworking.registerGlobalReceiver(SendPerPlayerPvpValueClientboundPacket.TYPE, FlowerbedNetworkClient::handlePerPlayerPvpValue);
		ClientPlayNetworking.registerGlobalReceiver(ClientboundShowWorldBorderPacket.TYPE, FlowerbedNetworkClient::handleShowWorldBorder);
		ClientPlayNetworking.registerGlobalReceiver(ClientboundWorldBorderRenderDistancePacket.TYPE, FlowerbedNetworkClient::handleWorldBorderDistance);
		ClientPlayNetworking.registerGlobalReceiver(ClientboundWorldBorderFadeTicksPacket.TYPE, FlowerbedNetworkClient::handleWorldBorderFadeTicks);
	}

	private static void handleWorldBorderDistance(
			ClientboundWorldBorderRenderDistancePacket packet,
			ClientPlayNetworking.Context context
	) {
		context.client().execute(() ->
				FlowerbedGameRules.worldBorderRenderDistance = packet.value());
	}

	private static void handleWorldBorderFadeTicks(
			ClientboundWorldBorderFadeTicksPacket packet,
			ClientPlayNetworking.Context context
	) {
		context.client().execute(() ->
				FlowerbedGameRules.worldBorderFadeTicks = packet.value());
	}

	private static void handleDisabledPassiveHungerLossValue(SendDisabledPassiveHungerLossValueClientboundPacket packet, ClientPlayNetworking.Context context) {
		context.client().execute(() ->
				FlowerbedGameRules.disablePassiveHungerLossGameruleEnabled = packet.value());
	}

	private static void handlePerPlayerPvpValue(SendPerPlayerPvpValueClientboundPacket packet, ClientPlayNetworking.Context context) {
		context.client().execute(() ->
				FlowerbedClient.perPlayerPvPGameruleEnabled = packet.value());
	}

	private static void handleShowWorldBorder(ClientboundShowWorldBorderPacket packet,  ClientPlayNetworking.Context context) {
		context.client().execute(() ->
				FlowerbedGameRules.showWorldBorder = packet.value());
	}
}
