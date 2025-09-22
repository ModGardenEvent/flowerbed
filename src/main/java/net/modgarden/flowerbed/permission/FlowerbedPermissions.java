package net.modgarden.flowerbed.permission;

import net.modgarden.flowerbed.Flowerbed;

public final class FlowerbedPermissions {
	public static final String NON_ADVENTURE = register("non_adventure");

	private FlowerbedPermissions() {}

	private static String register(String id) {
		return Flowerbed.MOD_ID + "." + id;
	}
}
