package net.modgarden.flowerbed.mixin;

import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "net.minecraft.server.level.ServerPlayer$3")
public interface Accessor_ServerPlayerCommandSource {
	@Accessor("field_54403")
	ServerPlayer flowerbed$getPlayer();
}
