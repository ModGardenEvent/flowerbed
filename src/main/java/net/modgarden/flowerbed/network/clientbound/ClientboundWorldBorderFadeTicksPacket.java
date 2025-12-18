package net.modgarden.flowerbed.network.clientbound;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.modgarden.flowerbed.Flowerbed;
import org.jetbrains.annotations.NotNull;

public record ClientboundWorldBorderFadeTicksPacket(int value) implements CustomPacketPayload {
    public static final Identifier ID = Flowerbed.asResource("world_border_fade_ticks");
    public static final Type<ClientboundWorldBorderFadeTicksPacket> TYPE = new Type<>(ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundWorldBorderFadeTicksPacket> STREAM_CODEC = ByteBufCodecs.INT
			.map(ClientboundWorldBorderFadeTicksPacket::new, ClientboundWorldBorderFadeTicksPacket::value).cast();

	@Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
