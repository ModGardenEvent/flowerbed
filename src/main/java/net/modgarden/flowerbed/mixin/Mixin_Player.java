package net.modgarden.flowerbed.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.modgarden.flowerbed.registry.FlowerbedGameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public abstract class Mixin_Player extends LivingEntity {
	protected Mixin_Player(EntityType<? extends LivingEntity> entityType, Level level) {
		super(entityType, level);
	}

	@WrapWithCondition(method = "actuallyHurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V"))
	private boolean flowerbed$cancelHurtFoodExhaustion(Player instance, float exhaustion, @Local(argsOnly = true) ServerLevel serverLevel) {
		return !serverLevel.getGameRules().get(FlowerbedGameRules.DISABLE_EXHAUSTION);
	}

	@WrapWithCondition(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V"))
	private boolean flowerbed$cancelAttackFoodExhaustion(Player instance, float exhaustion) {
		if (instance instanceof ServerPlayer serverPlayer) {
			return !serverPlayer.level().getGameRules().get(FlowerbedGameRules.DISABLE_EXHAUSTION);
		}
		return true;
	}

	@ModifyReturnValue(method = "canEat", at = @At("RETURN"))
	private boolean flowerbed$cancelAttackFoodExhaustion(boolean original) {
		return !FlowerbedGameRules.isExhaustionDisabled(level());
	}
}
