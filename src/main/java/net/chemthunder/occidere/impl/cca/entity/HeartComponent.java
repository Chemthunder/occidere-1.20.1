package net.chemthunder.occidere.impl.cca.entity;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.chemthunder.occidere.impl.Occidere;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class HeartComponent implements AutoSyncedComponent {
    public static ComponentKey<HeartComponent> KEY = ComponentRegistry.getOrCreate(Occidere.id("heart"), HeartComponent.class);

    private final PlayerEntity player;
    private int capturedBones = 0;
    public int max = 7;

    public HeartComponent(PlayerEntity player) {
        this.player = player;
    }

    public void sync() {
        KEY.sync(player);
    }

    public void readFromNbt(NbtCompound nbtCompound) {
        this.capturedBones = nbtCompound.getInt("CapturedBones");
    }

    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putInt("CapturedBones", capturedBones);
    }

    public int getCapturedBones() {
        return this.capturedBones;
    }

    public void setCapturedBones(int i) {
        this.capturedBones = i;
        sync();
    }

    public void addCapturesBones() {
        this.setCapturedBones(this.getCapturedBones() + 1);
        sync();
    }
}