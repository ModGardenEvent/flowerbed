package net.modgarden.flowerbed.mixin.fix.eiv;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import de.crafty.eiv.fabric.resolver.FabricEivResolver;
import net.modgarden.flowerbed.annotation.PatchMetadata;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;

@PatchMetadata(
		id = "prevent_eiv_crash",
		description = "Prevents EIV from throwing a NPE when it fails to read a mod id."
)
@Pseudo
@Mixin(FabricEivResolver.class)
public class Mixin_FabricEivResolver {
	@ModifyReturnValue(
			method = "getModNameForItem",
			at = @At(value = "RETURN")
	)
	private String flowerbed$fixNpe(
			String original
	) {
		if (original == null) {
			return "";
		} return original;
	}
}
