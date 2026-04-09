package net.chemthunder.occidere.impl.item;

import net.chemthunder.legere.api.v1.extendable.item.MiscItem;
import net.chemthunder.occidere.impl.index.OccidereItems;
import net.chemthunder.occidere.impl.manager.IvoryEventManager;
import net.chemthunder.occidere.impl.util.AuratusHereticState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class AHGemItem extends MiscItem {
    public final AuratusHereticState stateToTransformTo;

    public AHGemItem(Settings settings, AuratusHereticState stateToTransformTo) {
        super(settings);
        this.stateToTransformTo = stateToTransformTo;
    }

    public int getNameColor(ItemStack stack) {
        return stack.isOf(OccidereItems.AURATUS) ? 0xFFb3873d : 0xFFb00938;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (stack.isOf(OccidereItems.AURATUS)) {
            IvoryEventManager.auratus$getUseAbility(user, stack, world);
        } else {
            IvoryEventManager.heretic$getUseAbility(user, stack, world);
        }


        return super.use(world, user, hand);
    }
}