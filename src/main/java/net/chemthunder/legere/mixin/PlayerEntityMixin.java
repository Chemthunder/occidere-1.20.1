package net.chemthunder.legere.mixin;

import net.chemthunder.legere.api.v1.extendable.item.WeaponItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Shadow public abstract void spawnSweepAttackParticles();

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "attack", at = @At(value = "HEAD"))
    private void occidere$weaponItemSpawnSweepParticles(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (player.getMainHandStack().getItem() instanceof WeaponItem item) {
            if (item.isSword) {
                if (player.getAttackCooldownProgress(0.5f) > 0.9f) {
                    this.spawnSweepAttackParticles();
                }
            }
        }
    }

    @Inject(
            method = "attack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/PlayerEntity;addCritParticles(Lnet/minecraft/entity/Entity;)V"
            )
    )
    private void occidere$crit(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (player.getMainHandStack().getItem() instanceof WeaponItem item) {
            if (target instanceof LivingEntity living) {
                item.critEffect(player, living, player.getMainHandStack(), player.getWorld());
            }
        }
    }
}
