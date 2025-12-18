package net.modgarden.flowerbed.network.clientbound;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.modgarden.flowerbed.Flowerbed;
import org.jetbrains.annotations.NotNull;

public record ClientboundShowWorldBorderPacket(boolean value) implements CustomPacketPayload {
    public static final Identifier ID = Flowerbed.asResource("show_world_border");
    public static final Type<ClientboundShowWorldBorderPacket> TYPE = new Type<>(ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundShowWorldBorderPacket> STREAM_CODEC = ByteBufCodecs.BOOL
			.map(ClientboundShowWorldBorderPacket::new, ClientboundShowWorldBorderPacket::value).cast();

	@Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
