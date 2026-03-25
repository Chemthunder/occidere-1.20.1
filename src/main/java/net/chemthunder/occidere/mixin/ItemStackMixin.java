package net.chemthunder.occidere.mixin;

import net.chemthunder.occidere.impl.Occidere;
import net.chemthunder.occidere.impl.index.api.OccidereWeaponObtainments;
import net.chemthunder.occidere.impl.util.RiftCrackerObtainment;
import net.fabricmc.fabric.api.item.v1.FabricItemStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements FabricItemStack {

    @Inject(method = "useOnBlock", at = @At("TAIL"), cancellable = true)
    private void decider$weaponObtainment(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        PlayerEntity player = context.getPlayer();

        if (player != null) {
            ItemStack main = player.getMainHandStack();
            ItemStack off = player.getOffHandStack();

            if (OccidereWeaponObtainments.isBaseItem(main.getItem()) && OccidereWeaponObtainments.isAdditionalItem(off.getItem())) {
                if (OccidereWeaponObtainments.isAcceptableBlock(context.getWorld().getBlockState(context.getBlockPos()).getBlock())) {
                    player.equipStack(EquipmentSlot.MAINHAND, new ItemStack(OccidereWeaponObtainments.getResult(main.getItem(), off.getItem())));

                    off.decrement(1);

                    Occidere.LOGGER.info(OccidereWeaponObtainments.getResult(main.getItem(), off.getItem()).toString());
                    cir.setReturnValue(ActionResult.FAIL);
                }
            }

            if (RiftCrackerObtainment.getBase() == main.getItem() && RiftCrackerObtainment.getAdd() == off.getItem()) {
                if (RiftCrackerObtainment.getToInteract() == context.getWorld().getBlockState(context.getBlockPos()).getBlock()) {
                    if (RiftCrackerObtainment.getPlayerSpecificUUID().equals(player.getUuid())) {
                        player.equipStack(EquipmentSlot.MAINHAND, new ItemStack(RiftCrackerObtainment.getResult()));

                        off.decrement(1);

                        Occidere.LOGGER.info(RiftCrackerObtainment.getResult().toString());
                        cir.setReturnValue(ActionResult.FAIL);
                    }
                }
            }
        }
    }
}