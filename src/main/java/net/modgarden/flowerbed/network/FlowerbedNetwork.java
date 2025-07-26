package net.modgarden.flowerbed.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.modgarden.flowerbed.network.clientbound.SendPerPlayerPvpValueClientboundPacket;

public class FlowerbedNetwork {
	public static void init() {
		PayloadTypeRegistry.playS2C().register(SendPerPlayerPvpValueClientboundPacket.TYPE, SendPerPlayerPvpValueClientboundPacket.STREAM_CODEC);
	}
}
