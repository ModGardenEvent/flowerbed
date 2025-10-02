package net.modgarden.flowerbed.client.mixin.fix;

import net.minecraft.client.gui.components.LogoRenderer;
import net.modgarden.flowerbed.annotation.PatchMetadata;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@PatchMetadata(
		id = "prevent_logo_cutoff",
		description = "Don't cut off the Minecraft logo texture. Useful for Minecraft logo replacements."
)
@Mixin(LogoRenderer.class)
public class Mixin_LogoRenderer {
	@ModifyArg(
			method = "renderLogo(Lnet/minecraft/client/gui/GuiGraphics;IFI)V",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIFFIIIII)V", ordinal = 0),
			index = 7
	)
	private int flowerbed$extendLogoTextureV(int v) {
		return 64;
	}
}
