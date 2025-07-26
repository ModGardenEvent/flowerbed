package net.modgarden.flowerbed.command;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Unit;
import net.modgarden.flowerbed.mixin.Accessor_CommandSourceStack;
import net.modgarden.flowerbed.mixin.Accessor_ServerPlayerCommandSource;
import net.modgarden.flowerbed.registry.FlowerbedAttachments;
import net.modgarden.flowerbed.registry.FlowerbedGamerules;

import java.util.Objects;

public class PvPCommand {
	private static final SimpleCommandExceptionType NO_PLAYER = new SimpleCommandExceptionType(Component.translatable("commands.flowerbed.pvp.toggle.failed.non_player"));

	public static void register(CommandNode<CommandSourceStack> root, CommandBuildContext context) {
		LiteralCommandNode<CommandSourceStack> pvpNode = Commands
				.literal("pvp")
				.executes(ctx ->
						getPvP(ctx, ctx.getSource().getPlayer()))
				.build();

		LiteralCommandNode<CommandSourceStack> toggleNode = Commands
				.literal("toggle")
				.executes(ctx ->
					togglePvP(ctx, ctx.getSource().getPlayer()))
				.build();

		pvpNode.addChild(toggleNode);
		root.addChild(pvpNode);
	}

	@SuppressWarnings("UnstableApiUsage")
	private static int getPvP(CommandContext<CommandSourceStack> context, ServerPlayer player) throws CommandSyntaxException {
		if (player == null) {
			throw NO_PLAYER.create();
		}

		boolean gameruleValue = Objects.requireNonNull(player.getServer()).getGameRules().getBoolean(FlowerbedGamerules.PER_PLAYER_PVP);
		boolean pvpValue = player.getAttached(FlowerbedAttachments.ACCEPT_PVP) != null;
		context.getSource().sendSuccess(() -> Component.translatable(gameruleValue
						? "commands.flowerbed.pvp.get.success.gamerule_enabled" : "commands.flowerbed.pvp.get.success.gamerule_disabled",
				pvpValue ? "on" : "off"), false);

		return pvpValue ? 1 : 0;
	}

	@SuppressWarnings("UnstableApiUsage")
	private static int togglePvP(CommandContext<CommandSourceStack> context, ServerPlayer player) throws CommandSyntaxException {
		if (player == null) {
			throw NO_PLAYER.create();
		}

		if (!(((Accessor_CommandSourceStack)context.getSource()).flowerbed$getSource() instanceof Accessor_ServerPlayerCommandSource playerCommandSource) ||
				!playerCommandSource.flowerbed$getPlayer().equals(player)) {
			context.getSource().sendFailure(Component.translatable("commands.flowerbed.pvp.toggle.failed.source"));
			return 0;
		}

		boolean gameruleValue = Objects.requireNonNull(player.getServer()).getGameRules().getBoolean(FlowerbedGamerules.PER_PLAYER_PVP);
		boolean pvpValue = player.getAttached(FlowerbedAttachments.ACCEPT_PVP) != null;
		if (pvpValue) {
			player.removeAttached(FlowerbedAttachments.ACCEPT_PVP);
			context.getSource().sendSuccess(() -> Component.translatable(gameruleValue ?
					"commands.flowerbed.pvp.toggle.success.disable.gamerule_enabled" : "commands.flowerbed.pvp.toggle.success.disable.gamerule_disabled"), true);
		} else {
			player.setAttached(FlowerbedAttachments.ACCEPT_PVP, Unit.INSTANCE);
			context.getSource().sendSuccess(() -> Component.translatable(gameruleValue ?
					"commands.flowerbed.pvp.toggle.success.enable.gamerule_enabled" : "commands.flowerbed.pvp.toggle.success.enable.gamerule_disabled"), true);
			context.getSource().sendSuccess(() -> Component.translatable("commands.flowerbed.pvp.toggle.success.enable.info",
					Component.literal("⚔").withStyle(ChatFormatting.RED)), false);
		}

		return 1;
	}
}
