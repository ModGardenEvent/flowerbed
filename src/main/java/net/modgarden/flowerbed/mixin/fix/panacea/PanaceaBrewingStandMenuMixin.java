package net.modgarden.flowerbed.mixin.fix.panacea;

import lgbt.greenhouse.panacea.world.menu.PanaceaBrewingStandMenu;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PanaceaBrewingStandMenu.PotionSlot.class)
public class PanaceaBrewingStandMenuMixin extends Slot {

	public PanaceaBrewingStandMenuMixin(Container container, int slot, int x, int y) {
		super(container, slot, x, y);
	}

	/**
	 * @reason Trying to brew multiple potions in one slot fails and seems to revert the potion instead
	 */
	@SuppressWarnings("JavadocDeclaration")
	@Override
	public int getMaxStackSize() {
		return 1; // mixinoverride, yeah I know, whatever, it's fine here
	}
}
