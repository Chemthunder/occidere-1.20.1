package net.chemthunder.occidere.mixin;

import net.chemthunder.occidere.impl.cca.ivoryevent.AuratusStunlockComponent;
import net.chemthunder.occidere.impl.item.FateweaverItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
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

    @Inject(method = "applyMovementInput", at = @At("HEAD"), cancellable = true)
    private void ivoryevent$stunlock(Vec3d movementInput, float slipperiness, CallbackInfoReturnable<Vec3d> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (AuratusStunlockComponent.KEY.get(living).getActiveTicks() > 0) {
            cir.setReturnValue(Vec3d.ZERO);
        }
    }

    @Inject(method = "applyFluidMovingSpeed", at = @At("HEAD"), cancellable = true)
    private void ivoryevent$stunlockFluid(double gravity, boolean falling, Vec3d motion, CallbackInfoReturnable<Vec3d> cir) {
        LivingEntity living = (LivingEntity) (Object) this;
        if (AuratusStunlockComponent.KEY.get(living).getActiveTicks() > 0) {
            cir.setReturnValue(Vec3d.ZERO);
        }
    }
}