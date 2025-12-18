package net.modgarden.flowerbed.network.clientbound;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.modgarden.flowerbed.Flowerbed;
import org.jetbrains.annotations.NotNull;

public record SendDisabledPassiveHungerLossValueClientboundPacket(boolean value) implements CustomPacketPayload {
    public static final Identifier ID = Flowerbed.asResource("send_disabled_passive_hunger_loss");
    public static final Type<SendDisabledPassiveHungerLossValueClientboundPacket> TYPE = new Type<>(ID);
    public static final StreamCodec<RegistryFriendlyByteBuf, SendDisabledPassiveHungerLossValueClientboundPacket> STREAM_CODEC = ByteBufCodecs.BOOL
			.map(SendDisabledPassiveHungerLossValueClientboundPacket::new, SendDisabledPassiveHungerLossValueClientboundPacket::value).cast();

	@Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
