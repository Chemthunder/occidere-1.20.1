package net.chemthunder.occidere.impl.item.weapon;

import net.chemthunder.legere.api.v1.extendable.item.WeaponItem;
import net.chemthunder.legere.api.v1.interfaces.ComplexModelItem;
import net.chemthunder.legere.api.v1.util.ApiUtils;
import net.chemthunder.occidere.api.OccidereItemStatus;
import net.chemthunder.occidere.api.interfaces.IgnoredByRegisterLangItem;
import net.chemthunder.occidere.api.interfaces.ItemStatus;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.List;

public class IntertwinedGlaiveItem extends WeaponItem implements ComplexModelItem, IgnoredByRegisterLangItem, ItemStatus {
    private final int ticksToActivate = 10;

    public IntertwinedGlaiveItem(Settings settings) {
        super(settings, 8.0f, -2.6f, true);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!user.getItemCooldownManager().isCoolingDown(this)) {
            user.setCurrentHand(hand);
        }
        return super.use(world, user, hand);
    }

    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (this.getMaxUseTime(stack) - remainingUseTicks == ticksToActivate) {
            if (user instanceof PlayerEntity player) {
                player.getItemCooldownManager().set(this, 30);
            }
        }
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    public String getModel(ItemStack stack, PlayerEntity player, ModelTransformationMode modelTransformationMode, World world) {
        if (player.isUsingItem()) {
            return ApiUtils.isGui(modelTransformationMode) ? "intertwined_glaive" : "intertwined_glaive_brandished";
        }
        return ApiUtils.isGui(modelTransformationMode) ? "intertwined_glaive" : "intertwined_glaive_handheld";
    }

    public List<String> getLoadedModels() {
        return List.of(
                "intertwined_glaive",
                "intertwined_glaive_handheld",
                "intertwined_glaive_brandished"
        );
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    public OccidereItemStatus getStatus() {
        return OccidereItemStatus.UNFINISHED;
    }
}
