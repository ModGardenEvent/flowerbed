package net.modgarden.flowerbed.client.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.modgarden.flowerbed.client.FlowerbedClient;
import net.modgarden.flowerbed.network.clientbound.SendDisabledPassiveHungerLossValueClientboundPacket;
import net.modgarden.flowerbed.network.clientbound.SendPerPlayerPvpValueClientboundPacket;
import net.modgarden.flowerbed.registry.FlowerbedGameRules;

public class FlowerbedNetworkClient {
	public static void init() {
		ClientPlayNetworking.registerGlobalReceiver(SendDisabledPassiveHungerLossValueClientboundPacket.TYPE, FlowerbedNetworkClient::handleDisabledPassiveHungerLossValue);
		ClientPlayNetworking.registerGlobalReceiver(SendPerPlayerPvpValueClientboundPacket.TYPE, FlowerbedNetworkClient::handlePerPlayerPvpValue);
	}

	private static void handleDisabledPassiveHungerLossValue(SendDisabledPassiveHungerLossValueClientboundPacket packet, ClientPlayNetworking.Context context) {
		context.client().execute(() ->
				FlowerbedGameRules.disablePassiveHungerLossGameruleEnabled = packet.value());
	}

	private static void handlePerPlayerPvpValue(SendPerPlayerPvpValueClientboundPacket packet, ClientPlayNetworking.Context context) {
		context.client().execute(() ->
				FlowerbedClient.perPlayerPvPGameruleEnabled = packet.value());
	}
}
