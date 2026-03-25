package net.chemthunder.occidere.mixin;

import net.chemthunder.occidere.impl.Occidere;
import net.chemthunder.occidere.impl.index.OccidereWeaponObtainments;
import net.fabricmc.fabric.api.item.v1.FabricItemStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements FabricItemStack {

    @Inject(method = "useOnBlock", at = @At("TAIL"), cancellable = true)
    private void decider$weaponObtainment(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();

        if (player != null) {
            ItemStack main = player.getMainHandStack();
            ItemStack off = player.getOffHandStack();

            if (OccidereWeaponObtainments.isBaseItem(main.getItem()) && OccidereWeaponObtainments.isAdditionalItem(off.getItem())) {
                if (OccidereWeaponObtainments.isAcceptableBlock(context.getWorld().getBlockState(context.getBlockPos()).getBlock())) {
                    player.equipStack(EquipmentSlot.MAINHAND, new ItemStack(OccidereWeaponObtainments.getResult(main.getItem(), off.getItem())));

                    off.decrement(1);
                    main.decrement(1);

                    Occidere.LOGGER.info(OccidereWeaponObtainments.getResult(main.getItem(), off.getItem()).toString());
                    cir.setReturnValue(ActionResult.FAIL);
                }
            }
        }
    }
}
