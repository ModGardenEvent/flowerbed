package net.modgarden.flowerbed.network.clientbound;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.modgarden.flowerbed.Flowerbed;
import org.jetbrains.annotations.NotNull;

public record ClientboundWorldBorderRenderDistancePacket(int value) implements CustomPacketPayload {
    public static final Identifier ID = Flowerbed.asResource("world_border_render_distance");
    public static final Type<ClientboundWorldBorderRenderDistancePacket> TYPE = new Type<>(ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundWorldBorderRenderDistancePacket> STREAM_CODEC = ByteBufCodecs.INT
			.map(ClientboundWorldBorderRenderDistancePacket::new, ClientboundWorldBorderRenderDistancePacket::value).cast();

	@Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
