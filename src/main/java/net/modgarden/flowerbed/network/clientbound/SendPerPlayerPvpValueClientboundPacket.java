package net.modgarden.flowerbed.network.clientbound;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.modgarden.flowerbed.Flowerbed;

public record SendPerPlayerPvpValueClientboundPacket(boolean value) implements CustomPacketPayload {
	public static final ResourceLocation ID = Flowerbed.asResource("send_per_player_pvp_value");
	public static final Type<SendPerPlayerPvpValueClientboundPacket> TYPE = new Type<>(ID);
	public static final StreamCodec<RegistryFriendlyByteBuf, SendPerPlayerPvpValueClientboundPacket> STREAM_CODEC = ByteBufCodecs.BOOL
			.map(SendPerPlayerPvpValueClientboundPacket::new, SendPerPlayerPvpValueClientboundPacket::value).cast();

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
