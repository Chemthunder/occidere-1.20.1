package net.chemthunder.occidere.impl.item.weapon;

import net.chemthunder.legere.api.v1.util.ApiUtils;
import net.chemthunder.legere.api.v1.extendable.item.WeaponItem;
import net.chemthunder.legere.api.v1.interfaces.model.SimpleModelItem;
import net.chemthunder.occidere.impl.cca.entity.ThreadbreakerComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ThreadbreakerItem extends WeaponItem implements SimpleModelItem {
    public ThreadbreakerItem(Settings settings) {
        super(settings, 8.0f, -2.6f, true);
    }

    public int getNameColor(ItemStack stack) {
        return 0xFFb73551;
    }

    public String getItemId() {
        return "threadbreaker";
    }

    public String handheldId() {
        return "handheld";
    }

    public void critEffect(PlayerEntity player, LivingEntity target, ItemStack stack, World world) {
        if (!player.getItemCooldownManager().isCoolingDown(this)) {
            ThreadbreakerComponent threadbreakerComponent = ThreadbreakerComponent.KEY.get(target);

            threadbreakerComponent.tetherPosX = target.getX();
            threadbreakerComponent.tetherPosY = target.getY();
            threadbreakerComponent.tetherPosZ = target.getZ();

            threadbreakerComponent.setTetheredTicks(60);

            ApiUtils.applyCooldown(player, this, 30);
        }

        super.critEffect(player, target, stack, world);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!user.getItemCooldownManager().isCoolingDown(this)) {
            ThreadbreakerComponent threadbreakerComponent = ThreadbreakerComponent.KEY.get(user);

            threadbreakerComponent.tetherPosX = user.getX();
            threadbreakerComponent.tetherPosY = user.getY();
            threadbreakerComponent.tetherPosZ = user.getZ();

            threadbreakerComponent.setTetheredTicks(60);

            ApiUtils.applyCooldown(user, this, 30);

            if (world.isClient) {
                user.swingHand(hand);
            }
        }

        return super.use(world, user, hand);
    }
}
