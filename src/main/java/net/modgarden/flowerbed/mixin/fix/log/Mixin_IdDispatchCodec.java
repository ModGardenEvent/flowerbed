package net.modgarden.flowerbed.mixin.fix.log;

import com.llamalad7.mixinextras.sugar.Local;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.IdDispatchCodec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IdDispatchCodec.class)
public class Mixin_IdDispatchCodec {
	@Inject(
			method = "decode(Lio/netty/buffer/ByteBuf;)Ljava/lang/Object;",
			at = @At(value = "INVOKE", target = "Lio/netty/handler/codec/DecoderException;<init>(Ljava/lang/String;Ljava/lang/Throwable;)V")
	)
	private void flowerbed$forceStacktrace(
			ByteBuf byteBuf,
			CallbackInfoReturnable<Object> cir,
			@Local Exception var5
	) throws Exception {
		throw var5;
	}
}
