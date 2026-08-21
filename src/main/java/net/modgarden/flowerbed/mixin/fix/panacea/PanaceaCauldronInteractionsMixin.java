package net.modgarden.flowerbed.mixin.fix.panacea;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import lgbt.greenhouse.panacea.world.block.PanaceaCauldronInteractions;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PanaceaCauldronInteractions.class)
public class PanaceaCauldronInteractionsMixin {
    /**
     * @reason ItemUtil handles the decrementing already, so this prevents double-shrinking
     */
    @WrapWithCondition(method = "takePotsicle", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"))
    private static boolean decrementOnce(ItemStack instance, int amount) {
        return false;
    }
}
