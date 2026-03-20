package net.chemthunder.occidere.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.chemthunder.occidere.api.WeaponItem;
import net.chemthunder.occidere.impl.entity.WovenAdmirationEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Shadow
    public abstract void spawnSweepAttackParticles();

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean isSneaking() {
        if (this.getVehicle() instanceof WovenAdmirationEntity) {
            return false;
        }
        return super.isSneaking();
    }

    @Inject(method = "attack", at = @At(value = "TAIL"))
    private void occidere$weaponItemSpawnSweepParticles(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (player.getMainHandStack().getItem() instanceof WeaponItem item) {
            if (item.isSword) {
                this.spawnSweepAttackParticles();
            }
        }
    }

    @WrapOperation(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/player/PlayerEntity;DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V", ordinal = 3))
    private void occidere$customHitSound(World instance, PlayerEntity except, double x, double y, double z, SoundEvent sound, SoundCategory category, float volume, float pitch, Operation<Void> original) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack main = player.getMainHandStack();

        if (main.getItem() instanceof WeaponItem item) {
            item.customHitSound(player, main);
        }
    }

    @WrapOperation(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/player/PlayerEntity;DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V", ordinal = 4))
    private void occidere$customHitSoundWeak(World instance, PlayerEntity except, double x, double y, double z, SoundEvent sound, SoundCategory category, float volume, float pitch, Operation<Void> original) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack main = player.getMainHandStack();

        if (main.getItem() instanceof WeaponItem item) {
            item.customHitSound(player, main);
        }
    }
}
