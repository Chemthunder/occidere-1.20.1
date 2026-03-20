package net.chemthunder.occidere.impl.cca.entity;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.chemthunder.occidere.impl.Occidere;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;

public class AccursedComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static ComponentKey<AccursedComponent> KEY = ComponentRegistry.getOrCreate(Occidere.id("accursed"), AccursedComponent.class);
    private final PlayerEntity player;
    private boolean isActive = false;

    public AccursedComponent(PlayerEntity player) {
        this.player = player;
    }

    public void tick() {
        if (isActive) {
            if (player instanceof ServerPlayerEntity serverPlayer) {
                if (!serverPlayer.isSpectator()) {
                    serverPlayer.changeGameMode(GameMode.SPECTATOR);
                }
            }
        }
    }

    public void readFromNbt(NbtCompound nbtCompound) {
        this.isActive = nbtCompound.getBoolean("IsActive");
    }

    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putBoolean("IsActive", this.isActive);
    }

    public boolean getActive() {
        return this.isActive;
    }

    public void setActive(boolean bl) {
        this.isActive = bl;
    }
}
