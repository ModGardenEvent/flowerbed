package net.modgarden.flowerbed.mixin.showcase.griefing.prevent_modfest_1_20;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.selector.EntitySelector;
import net.minecraft.commands.arguments.selector.EntitySelectorParser;
import net.minecraft.network.chat.Component;
import net.modgarden.flowerbed.duck.Duck_ExtESP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityArgument.class)
public class Mixin_EntityArgument {
	@Unique
	private static final SimpleCommandExceptionType E_NOT_ALLOWED = new SimpleCommandExceptionType(Component.translatable("argument.flowerbed.e_not_allowed"));

	@Inject(
			method = "parse(Lcom/mojang/brigadier/StringReader;Z)Lnet/minecraft/commands/arguments/selector/EntitySelector;",
			at = @At("TAIL")
	)
	private void flowerbed$preventModfest120(
			StringReader reader,
			boolean allowSelectors,
			CallbackInfoReturnable<EntitySelector> cir,
			@Local EntitySelector entitySelector,
			@Local EntitySelectorParser entitySelectorParser
	) throws CommandSyntaxException {
		if (!entitySelector.includesEntities() || entitySelector.isSelfSelector() || !entitySelector.usesSelector()) return;

		boolean unaware = !((Duck_ExtESP) entitySelectorParser)
				.flowerbed$theUserUnderstandsTheConsequencesOfTheirActions();
		if (unaware) {
			throw E_NOT_ALLOWED.createWithContext(reader);
		}
	}
}
