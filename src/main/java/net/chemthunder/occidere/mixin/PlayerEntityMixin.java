package net.chemthunder.occidere.mixin;

import net.chemthunder.occidere.impl.cca.entity.HostessComponent;
import net.chemthunder.occidere.impl.cca.entity.VainComponent;
import net.chemthunder.occidere.impl.cca.item.PactItemComponent;
import net.chemthunder.occidere.impl.entity.WovenAdmirationEntity;
import net.chemthunder.occidere.impl.index.OccidereItems;
import net.chemthunder.occidere.impl.item.PactItem;
import net.chemthunder.occidere.impl.item.FlayedLustItem;
import net.chemthunder.occidere.impl.item.NyrulnaVainItem;
import net.chemthunder.occidere.impl.manager.HostessManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PlayerEntity.class, priority = 9000)
public abstract class PlayerEntityMixin extends LivingEntity {
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

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void occidere$negateFallDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (player.getMainHandStack().getItem() instanceof NyrulnaVainItem) {
            if (VainComponent.KEY.get(player).isActive()) {
                if (source.isOf(DamageTypes.FALL)) {
                    cir.setReturnValue(true);
                }
            }
        }
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void occidere$blockHits(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        Entity entity = source.getAttacker();

        if (player != null) {
            if (entity instanceof LivingEntity living) {
                ItemStack stack = player.getStackInHand(player.getActiveHand());

                if (stack.getItem() instanceof FlayedLustItem flayedLustItem) {
                    if (player.isUsingItem() && !player.getItemCooldownManager().isCoolingDown(OccidereItems.FLAYED_LUST)) {
                        flayedLustItem.block(player, living, player.getWorld());
                        cir.setReturnValue(false);
                        player.damage(source, amount / 2);
                    }
                }
            }
        }
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void occidere$hostessNegateDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (HostessManager.isHostess(player)) {
            if (HostessComponent.KEY.get(player).isInvincible()) {
                cir.setReturnValue(false);
            }
        }
    }

    @Inject(method = "attack", at = @At("HEAD"))
    private void occidere$contractAbility(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (HostessManager.isHostess(player)) {
            if (player.getOffHandStack().getItem() instanceof PactItem && player.getMainHandStack().isEmpty()) {
                ItemStack stack = player.getOffHandStack();

                PactItemComponent pact = PactItemComponent.KEY.get(stack);

                if (pact.getSigned()) {
                    if (target instanceof PlayerEntity liv) {
                        HostessManager.contractAbility(liv, liv.getUuid(), player, stack);
                    }
                }
            }
        }
    }
}