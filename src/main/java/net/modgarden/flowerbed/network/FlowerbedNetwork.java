package net.modgarden.flowerbed.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.modgarden.flowerbed.network.clientbound.*;

public class FlowerbedNetwork {
	public static void init() {
		PayloadTypeRegistry.playS2C().register(SendDisabledPassiveHungerLossValueClientboundPacket.TYPE, SendDisabledPassiveHungerLossValueClientboundPacket.STREAM_CODEC);
		PayloadTypeRegistry.playS2C().register(SendPerPlayerPvpValueClientboundPacket.TYPE, SendPerPlayerPvpValueClientboundPacket.STREAM_CODEC);
		PayloadTypeRegistry.playS2C().register(ClientboundShowWorldBorderPacket.TYPE, ClientboundShowWorldBorderPacket.STREAM_CODEC);
		PayloadTypeRegistry.playS2C().register(ClientboundWorldBorderRenderDistancePacket.TYPE, ClientboundWorldBorderRenderDistancePacket.STREAM_CODEC);
		PayloadTypeRegistry.playS2C().register(ClientboundWorldBorderFadeTicksPacket.TYPE, ClientboundWorldBorderFadeTicksPacket.STREAM_CODEC);
	}
}
