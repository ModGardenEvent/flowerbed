package net.modgarden.flowerbed.mixin.showcase.griefing.prevent_modfest_1_20;

import net.minecraft.commands.arguments.selector.EntitySelectorParser;
import net.minecraft.commands.arguments.selector.options.EntitySelectorOptions;
import net.minecraft.network.chat.Component;
import net.modgarden.flowerbed.duck.Duck_ExtESP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(EntitySelectorOptions.class)
public class Mixin_EntitySelectorOptions {
	@Shadow
	private static void register(
			String id, EntitySelectorOptions.Modifier handler,
			Predicate<EntitySelectorParser> predicate, Component tooltip
	) {
	}

	@Inject(
			method = "bootStrap",
			at = @At("RETURN")
	)
	private static void flowerbed$registerOption(CallbackInfo ci) {
		register(
				Duck_ExtESP.CONFIRMATION,
				entitySelectorParser -> {
					int j = entitySelectorParser.getReader().readInt();
					if (j == 1) {
						((Duck_ExtESP) entitySelectorParser)
								.flowerbed$setTheUserUnderstandsTheConsequencesOfTheirActions(true);
					}
				},
				entitySelectorParser -> !entitySelectorParser.isCurrentEntity() && !entitySelectorParser.isLimited(),
				Component.translatable("argument.entity.options.limit.description")
		);
	}
}
