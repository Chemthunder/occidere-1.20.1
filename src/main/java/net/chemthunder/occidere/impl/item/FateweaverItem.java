package net.chemthunder.occidere.impl.item;

import net.chemthunder.legere.api.v1.extendable.item.MiscItem;
import net.chemthunder.occidere.impl.entity.WovenAdmirationEntity;
import net.chemthunder.occidere.impl.index.OccidereEntities;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

@SuppressWarnings("unused")
public class FateweaverItem extends MiscItem {
    public FateweaverItem(Settings settings) {
        super(settings);
    }

    public void killEffect(PlayerEntity user, LivingEntity target, World world, ItemStack stack) {
        world.playSound(null, target.getBlockPos(), SoundEvents.ENTITY_ALLAY_HURT, SoundCategory.MASTER, 1, 0.3f);

        target.setHealth(target.getMaxHealth());
        target.setPos(target.getX(), target.getY() + 0.5f, target.getZ());
        WovenAdmirationEntity entity = new WovenAdmirationEntity(OccidereEntities.WOVEN_ADMIRATION, world);

        ((ServerWorld) world).getServer().execute(() -> target.startRiding(entity, true));

        entity.setPos(target.getX(), target.getY(), target.getZ());
        world.spawnEntity(entity);
    }

    public int getNameColor(ItemStack stack) {
        return 0xFF1f824f;
    }
}