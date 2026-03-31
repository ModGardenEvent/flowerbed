package net.modgarden.flowerbed.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.modgarden.flowerbed.network.clientbound.*;

public class FlowerbedNetwork {
	public static void init() {
		PayloadTypeRegistry.clientboundPlay().register(SendDisabledPassiveHungerLossValueClientboundPacket.TYPE, SendDisabledPassiveHungerLossValueClientboundPacket.STREAM_CODEC);
		PayloadTypeRegistry.clientboundPlay().register(SendPerPlayerPvpValueClientboundPacket.TYPE, SendPerPlayerPvpValueClientboundPacket.STREAM_CODEC);
		PayloadTypeRegistry.clientboundPlay().register(ClientboundShowWorldBorderPacket.TYPE, ClientboundShowWorldBorderPacket.STREAM_CODEC);
		PayloadTypeRegistry.clientboundPlay().register(ClientboundWorldBorderRenderDistancePacket.TYPE, ClientboundWorldBorderRenderDistancePacket.STREAM_CODEC);
		PayloadTypeRegistry.clientboundPlay().register(ClientboundWorldBorderFadeTicksPacket.TYPE, ClientboundWorldBorderFadeTicksPacket.STREAM_CODEC);
	}
}
