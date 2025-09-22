package net.modgarden.flowerbed.client;

import net.fabricmc.api.ClientModInitializer;
import net.modgarden.flowerbed.client.command.FlowerbedClientCommands;
import net.modgarden.flowerbed.client.network.FlowerbedNetworkClient;

public class FlowerbedClient implements ClientModInitializer {
	public static boolean perPlayerPvPGameruleEnabled = false;

    @Override
    public void onInitializeClient() {
		FlowerbedNetworkClient.init();
		FlowerbedClientCommands.init();
    }
}
