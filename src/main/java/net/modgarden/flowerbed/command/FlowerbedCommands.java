package net.modgarden.flowerbed.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;

public class FlowerbedCommands {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context) {
		PvPCommand.register(dispatcher.getRoot(), context);
	}
}
