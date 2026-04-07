package net.chemthunder.occidere.impl.cca.entity;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.chemthunder.occidere.impl.Occidere;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class HostessComponent implements AutoSyncedComponent {
    public static ComponentKey<HostessComponent> KEY = ComponentRegistry.getOrCreate(Occidere.id("hostess"), HostessComponent.class);

    private final PlayerEntity player;

    private boolean invincible = false;

    public HostessComponent(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(player);
    }

    public void readFromNbt(NbtCompound nbtCompound) {
        this.invincible = nbtCompound.getBoolean("Invincible");
    }

    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putBoolean("Invincible", invincible);
    }

    public boolean isInvincible() {
        return this.invincible;
    }

    public void setInvincible(boolean bl) {
        this.invincible = bl;
        sync();
    }
}
