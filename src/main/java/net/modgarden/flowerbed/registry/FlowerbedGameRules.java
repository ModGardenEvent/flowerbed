package net.modgarden.flowerbed.registry;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.modgarden.flowerbed.network.clientbound.ClientboundShowWorldBorderPacket;
import net.modgarden.flowerbed.network.clientbound.ClientboundWorldBorderRenderDistancePacket;
import net.modgarden.flowerbed.network.clientbound.ClientboundWorldBorderFadeTicksPacket;
import net.modgarden.flowerbed.network.clientbound.SendPerPlayerPvpValueClientboundPacket;

public class FlowerbedGameRules {
	public static boolean disablePassiveHungerLossGameruleEnabled = false;
	public static boolean showWorldBorder = true;
	public static int worldBorderRenderDistance = 0;
	public static int worldBorderFadeTicks = 40;

	public static final GameRules.Key<GameRules.BooleanValue> PER_PLAYER_PVP =
			GameRuleRegistry.register("flowerbed:per_player_pvp", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true,
					(server, booleanValue) -> {
				for (ServerPlayer serverPlayer : server.getPlayerList().getPlayers()) {
					ServerPlayNetworking.send(serverPlayer, new SendPerPlayerPvpValueClientboundPacket(booleanValue.get()));
				}
			}));
	public static final GameRules.Key<GameRules.BooleanValue> DISABLE_EXHAUSTION =
			GameRuleRegistry.register("flowerbed:disable_exhaustion", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(true));

	public static final GameRules.Key<GameRules.BooleanValue> DISABLE_GOLEM_SPAWNING =
			GameRuleRegistry.register("flowerbed:disable_golem_spawning", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));

	public static final GameRules.Key<GameRules.BooleanValue> DISABLE_WITHER_SPAWNING =
			GameRuleRegistry.register("flowerbed:disable_wither_spawning", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(true));

	public static final GameRules.Key<GameRules.BooleanValue> DISABLE_ENTERING_PORTALS =
			GameRuleRegistry.register("flowerbed:disable_entering_portals", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));

	public static final GameRules.Key<GameRules.BooleanValue> SHOW_WORLD_BORDER =
			GameRuleRegistry.register("flowerbed:show_world_border", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true, (server, value) -> {
				for (ServerPlayer serverPlayer : server.getPlayerList().getPlayers()) {
					ServerPlayNetworking.send(serverPlayer, new ClientboundShowWorldBorderPacket(value.get()));
				}
			}));

	public static final GameRules.Key<GameRules.IntegerValue> WORLD_BORDER_RENDER_DISTANCE =
			GameRuleRegistry.register("flowerbed:world_border_render_distance", GameRules.Category.MISC, GameRuleFactory.createIntRule(0, (server, value) -> {
				for (ServerPlayer serverPlayer : server.getPlayerList().getPlayers()) {
					ServerPlayNetworking.send(serverPlayer, new ClientboundWorldBorderRenderDistancePacket(value.get()));
				}
			}));

	public static final GameRules.Key<GameRules.IntegerValue> WORLD_BORDER_FADE_TICKS =
			GameRuleRegistry.register("flowerbed:world_border_fade_ticks", GameRules.Category.MISC, GameRuleFactory.createIntRule(40, (server, value) -> {
				for (ServerPlayer serverPlayer : server.getPlayerList().getPlayers()) {
					ServerPlayNetworking.send(serverPlayer, new ClientboundWorldBorderFadeTicksPacket(value.get()));
				}
			}));

	public static void init() {

	}

	public static boolean isExhaustionDisabled(Level level) {
		if (level instanceof ServerLevel serverLevel) {
			serverLevel.getGameRules().getBoolean(FlowerbedGameRules.DISABLE_EXHAUSTION);
		}
		return disablePassiveHungerLossGameruleEnabled;
	}
}
