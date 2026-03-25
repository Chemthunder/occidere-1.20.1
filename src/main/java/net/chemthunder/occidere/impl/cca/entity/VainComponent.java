package net.chemthunder.occidere.impl.cca.entity;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.chemthunder.occidere.impl.Occidere;
import net.chemthunder.occidere.impl.item.weapon.NyrulnaVainItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class VainComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static ComponentKey<VainComponent> KEY = ComponentRegistry.getOrCreate(Occidere.id("vain"), VainComponent.class);

    //region variables
    private final PlayerEntity player;
    private boolean isActive = false;
    //endregion

    public VainComponent(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(player);
    }

    public void tick() {
        if (this.isActive) {
            if (player.isOnGround()) {
                if (player.getMainHandStack().getItem() instanceof NyrulnaVainItem vainItem) {
                    this.isActive = false;
                    vainItem.impact(player, player.getMainHandStack());
                    sync();
                }
            }
        }
    }

    public void readFromNbt(NbtCompound nbtCompound) {
        this.isActive = nbtCompound.getBoolean("IsActive");
    }

    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putBoolean("IsActive", isActive);
    }

    public void setActive(boolean bl) {
        this.isActive = bl;
        sync();
    }

    public boolean isActive() {
        return this.isActive;
    }
}
