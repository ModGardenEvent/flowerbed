package net.modgarden.flowerbed.registry;


import net.fabricmc.fabric.api.gamerule.v1.GameRuleBuilder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRule;
import net.modgarden.flowerbed.Flowerbed;

public class FlowerbedGameRules {
	public static boolean disablePassiveHungerLossGameruleEnabled = false;
	public static boolean showWorldBorder = true;
	public static int worldBorderRenderDistance = 0;
	public static int worldBorderFadeTicks = 40;

	public static final GameRule<Boolean> PER_PLAYER_PVP =
			GameRuleBuilder.forBoolean(true).buildAndRegister(Flowerbed.asResource("per_player_pvp"));
	public static final GameRule<Boolean> DISABLE_EXHAUSTION =
			GameRuleBuilder.forBoolean(true).buildAndRegister(Flowerbed.asResource("disable_exhaustion"));

	public static final GameRule<Boolean> DISABLE_GOLEM_SPAWNING =
			GameRuleBuilder.forBoolean(true).buildAndRegister(Flowerbed.asResource("disable_golem_spawning"));

	public static final GameRule<Boolean> DISABLE_WITHER_SPAWNING =
			GameRuleBuilder.forBoolean(true).buildAndRegister(Flowerbed.asResource("disable_wither_spawning"));

	public static final GameRule<Boolean> DISABLE_ENTERING_PORTALS =
			GameRuleBuilder.forBoolean(true).buildAndRegister(Flowerbed.asResource("disable_entering_portals"));

	public static final GameRule<Boolean> SHOW_WORLD_BORDER =
			GameRuleBuilder.forBoolean(true).buildAndRegister(Flowerbed.asResource("show_world_border"));

	public static final GameRule<Integer> WORLD_BORDER_RENDER_DISTANCE =
			GameRuleBuilder.forInteger(0).buildAndRegister(Flowerbed.asResource("world_border_render_distance"));

	public static final GameRule<Integer> WORLD_BORDER_FADE_TICKS =
			GameRuleBuilder.forInteger(40).buildAndRegister(Flowerbed.asResource("world_border_fade_ticks"));


	public static void init() {

	}

	public static boolean isExhaustionDisabled(Level level) {
		if (level instanceof ServerLevel serverLevel) {
			serverLevel.getGameRules().get(FlowerbedGameRules.DISABLE_EXHAUSTION);
		}
		return disablePassiveHungerLossGameruleEnabled;
	}
}
