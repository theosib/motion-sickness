package eigencraft.motionsickness.mixin;

import eigencraft.motionsickness.Settings;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public abstract class MixinBlock {

    @Inject(method = "getSlipperiness", at = @At("HEAD"), cancellable = true)
    public void setCustomSlipperiness(CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(Settings.slipperiness);
        cir.cancel();
    }
}