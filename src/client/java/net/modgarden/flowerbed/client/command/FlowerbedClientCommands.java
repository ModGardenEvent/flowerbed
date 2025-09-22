package net.modgarden.flowerbed.client.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.chat.Component;

public final class FlowerbedClientCommands {
	private FlowerbedClientCommands() {}

	public static void init() {
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> dispatcher.register(
				ClientCommandManager.literal("shrug").then(ClientCommandManager.argument("message", StringArgumentType.greedyString())
						.executes(ctx -> {
							String message = ctx.getArgument("message", String.class);
							ClientPacketListener connection = ctx.getSource().getClient().getConnection();
							if (connection == null) {
								ctx.getSource().sendError(Component.translatable("commands.flowerbed.shrug.failed.no_server"));
								return 0;
							}
							connection.sendChat(message + " ¯\\_(ツ)_/¯");
							return 1;
						}))
		));
	}
}
