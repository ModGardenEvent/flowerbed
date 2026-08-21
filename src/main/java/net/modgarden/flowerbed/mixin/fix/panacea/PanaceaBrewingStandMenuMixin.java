package net.modgarden.flowerbed.mixin.fix.panacea;

import lgbt.greenhouse.panacea.world.menu.PanaceaBrewingStandMenu;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PanaceaBrewingStandMenu.class)
public class PanaceaBrewingStandMenuMixin {

    @SuppressWarnings("unused")
    @Mixin(PanaceaBrewingStandMenu.PotionSlot.class)
    public abstract static class PotionSlotMixin extends Slot {
        public PotionSlotMixin(Container container, int slot, int x, int y) {
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
}
