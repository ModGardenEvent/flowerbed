package net.modgarden.flowerbed.registry;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.modgarden.flowerbed.network.clientbound.SendPerPlayerPvpValueClientboundPacket;

public class FlowerbedGameRules {
	public static boolean disablePassiveHungerLossGameruleEnabled = false;

	public static final GameRules.Key<GameRules.BooleanValue> PER_PLAYER_PVP =
			GameRuleRegistry.register("flowerbed:per_player_pvp", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true,
					(server, booleanValue) -> {
				for (ServerPlayer serverPlayer : server.getPlayerList().getPlayers()) {
					ServerPlayNetworking.send(serverPlayer, new SendPerPlayerPvpValueClientboundPacket(booleanValue.get()));
				}
			}));
	public static final GameRules.Key<GameRules.BooleanValue> DISABLE_EXHAUSTION =
			GameRuleRegistry.register("flowerbed:disable_exhaustion", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));

	public static final GameRules.Key<GameRules.BooleanValue> DISABLE_ENTERING_PORTALS =
			GameRuleRegistry.register("flowerbed:disable_entering_portals", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));

	public static void init() {

	}

	public static boolean isExhaustionDisabled(Level level) {
		if (level instanceof ServerLevel serverLevel) {
			serverLevel.getGameRules().getBoolean(FlowerbedGameRules.DISABLE_EXHAUSTION);
		}
		return disablePassiveHungerLossGameruleEnabled;
	}
}
