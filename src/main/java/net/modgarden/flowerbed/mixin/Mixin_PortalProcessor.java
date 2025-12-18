package net.modgarden.flowerbed.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.PortalProcessor;
import net.minecraft.world.level.block.EndPortalBlock;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.Portal;
import net.modgarden.flowerbed.registry.FlowerbedGameRules;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PortalProcessor.class)
public class Mixin_PortalProcessor {
	@Shadow
	@Final
	private Portal portal;

	@ModifyReturnValue(method = "processPortalTeleportation", at = @At(value = "RETURN", ordinal = 0))
	private boolean flowerbed$cancelPortalTeleportation(boolean original, @Local(argsOnly = true) ServerLevel serverLevel) {
		if (serverLevel.getGameRules().get(FlowerbedGameRules.DISABLE_ENTERING_PORTALS) && (portal instanceof NetherPortalBlock || portal instanceof EndPortalBlock)) {
			return false;
		}
		return original;
	}
}
