package net.chemthunder.occidere.impl.item.weapon;

import net.chemthunder.legere.api.v1.util.ApiUtils;
import net.chemthunder.legere.api.v1.extendable.item.WeaponItem;
import net.chemthunder.legere.api.v1.interfaces.SimpleModelItem;
import net.chemthunder.occidere.impl.entity.AurumEntity;
import net.chemthunder.occidere.impl.index.OccidereEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class AurumItem extends WeaponItem implements SimpleModelItem {
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
            ApiUtils.applyCooldown(user, this, 300);
        }

        ApiUtils.swingHand(user, hand);
        return super.use(world, user, hand);
    }
}