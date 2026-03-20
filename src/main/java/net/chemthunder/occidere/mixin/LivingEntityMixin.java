package net.chemthunder.occidere.mixin;

import net.chemthunder.occidere.impl.item.FateweaverItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "tryUseTotem", at = @At("HEAD"), cancellable = true)
    private void occidere$fateweaver(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        Entity entity = source.getAttacker();

        if (entity instanceof PlayerEntity player) {
            if (player.getOffHandStack().getItem() instanceof FateweaverItem item) {
                item.killEffect(player, living, player.getWorld(), player.getOffHandStack());
                cir.setReturnValue(true);
            }
        }
    }
}
