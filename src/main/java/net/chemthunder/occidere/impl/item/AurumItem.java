package net.chemthunder.occidere.impl.item;

import net.chemthunder.occidere.api.HandheldItem;
import net.chemthunder.occidere.api.WeaponItem;
import net.chemthunder.occidere.impl.entity.AurumEntity;
import net.chemthunder.occidere.impl.index.OccidereEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class AurumItem extends WeaponItem implements HandheldItem {
    public AurumItem(Settings settings) {
        super(settings, 7.5f, -2.6f, true);
    }

    public String getItemId() {
        return "aurum";
    }

    public String handheldId() {
        return "handheld";
    }

    public int getNameColor(ItemStack stack) {
        return 0xFFa54d45;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world instanceof ServerWorld serverWorld) {
            AurumEntity entity = new AurumEntity(OccidereEntities.AURUM, serverWorld);

            entity.setPos(user.getX(), user.getY() + 1.0f, user.getZ());

            entity.setOwner(user);
            serverWorld.spawnEntity(entity);
            user.swingHand(Hand.MAIN_HAND);

            if (!user.isCreative()) {
                user.getItemCooldownManager().set(this, 300);
            } else {
                user.getItemCooldownManager().set(this, 30);
            }
        }
        return super.use(world, user, hand);
    }
}
