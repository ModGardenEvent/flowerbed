package net.modgarden.flowerbed.client.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.modgarden.flowerbed.client.FlowerbedClient;
import net.modgarden.flowerbed.registry.FlowerbedAttachments;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityRenderer.class)
public class Mixin_EntityRenderer {
	@SuppressWarnings("UnstableApiUsage")
	@ModifyReturnValue(method = "getNameTag", at = @At("RETURN"))
	private <T extends Entity> @Nullable Component flowerbed$suffixNameTagForPvP(@Nullable Component original, T entity) {
		if (FlowerbedClient.perPlayerPvPGameruleEnabled && entity.getAttached(FlowerbedAttachments.ACCEPT_PVP) != null) {
			return Component.empty()
					.append(original)
					.append(" ")
					.append(Component.literal("⚔").withStyle(ChatFormatting.RED));
		}
		return original;
	}
}
