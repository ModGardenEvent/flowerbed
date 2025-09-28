package net.modgarden.flowerbed.mixin.showcase.griefing.prevent_modfest_1_20;

import net.minecraft.commands.arguments.selector.EntitySelectorParser;
import net.modgarden.flowerbed.duck.Duck_ExtESP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EntitySelectorParser.class)
public class Mixin_EntitySelectorParser implements Duck_ExtESP {
	@Unique
	private boolean flowerbed$theUserUnderstandsTheConsequencesOfTheirActions;

	@Override
	public boolean flowerbed$theUserUnderstandsTheConsequencesOfTheirActions() {
		return this.flowerbed$theUserUnderstandsTheConsequencesOfTheirActions;
	}

	@Override
	public void flowerbed$setTheUserUnderstandsTheConsequencesOfTheirActions(
			boolean theUserUnderstandsTheConsequencesOfTheirActions
	) {
		this.flowerbed$theUserUnderstandsTheConsequencesOfTheirActions = theUserUnderstandsTheConsequencesOfTheirActions;
	}
}
