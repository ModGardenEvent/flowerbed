package net.modgarden.flowerbed.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.modgarden.flowerbed.network.clientbound.ClientboundShowWorldBorderPacket;
import net.modgarden.flowerbed.network.clientbound.SendDisabledPassiveHungerLossValueClientboundPacket;
import net.modgarden.flowerbed.network.clientbound.SendPerPlayerPvpValueClientboundPacket;

public class FlowerbedNetwork {
	public static void init() {
		PayloadTypeRegistry.playS2C().register(SendDisabledPassiveHungerLossValueClientboundPacket.TYPE, SendDisabledPassiveHungerLossValueClientboundPacket.STREAM_CODEC);
		PayloadTypeRegistry.playS2C().register(SendPerPlayerPvpValueClientboundPacket.TYPE, SendPerPlayerPvpValueClientboundPacket.STREAM_CODEC);
		PayloadTypeRegistry.playS2C().register(ClientboundShowWorldBorderPacket.TYPE, ClientboundShowWorldBorderPacket.STREAM_CODEC);
	}
}
