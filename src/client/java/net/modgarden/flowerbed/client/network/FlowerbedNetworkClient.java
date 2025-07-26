package net.modgarden.flowerbed.client.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.modgarden.flowerbed.client.FlowerbedClient;
import net.modgarden.flowerbed.network.clientbound.SendPerPlayerPvpValueClientboundPacket;

public class FlowerbedNetworkClient {
	public static void init() {
		ClientPlayNetworking.registerGlobalReceiver(SendPerPlayerPvpValueClientboundPacket.TYPE, FlowerbedNetworkClient::handlePerPlayerPvpValue);
	}

	private static void handlePerPlayerPvpValue(SendPerPlayerPvpValueClientboundPacket packet, ClientPlayNetworking.Context context) {
		context.client().execute(() ->
				FlowerbedClient.perPlayerPvPGameruleEnabled = packet.value());
	}
}
