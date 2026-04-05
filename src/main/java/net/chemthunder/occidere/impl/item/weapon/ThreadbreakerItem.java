package net.chemthunder.occidere.impl.item.weapon;

import net.chemthunder.occidere.api.extendable.WeaponItem;
import net.chemthunder.occidere.api.interfaces.SimpleModelItem;
import net.chemthunder.occidere.impl.cca.entity.ThreadbreakerComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
        ThreadbreakerComponent threadbreakerComponent = ThreadbreakerComponent.KEY.get(target);

        threadbreakerComponent.tetherPosX = target.getBlockX();
        threadbreakerComponent.tetherPosY = target.getBlockY();
        threadbreakerComponent.tetherPosZ = target.getBlockZ();
        //todo FINISH THIS YOU FUCK
        super.critEffect(player, target, stack, world);
    }
}
