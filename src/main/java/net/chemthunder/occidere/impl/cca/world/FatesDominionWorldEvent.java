package net.chemthunder.occidere.impl.cca.world;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.chemthunder.occidere.impl.Occidere;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class FatesDominionWorldEvent implements AutoSyncedComponent {
    public static final ComponentKey<FatesDominionWorldEvent> KEY = ComponentRegistry.getOrCreate(Occidere.id("fates_dominion"), FatesDominionWorldEvent.class);

    private final World world;
    private boolean active = false;
    private boolean fogActive = false;

    public void sync() {
        KEY.sync(world);
    }

    public FatesDominionWorldEvent(World world) {
        this.world = world;
    }

    public void readFromNbt(NbtCompound nbtCompound) {
        this.active = nbtCompound.getBoolean("Active");
        this.fogActive = nbtCompound.getBoolean("FogActive");
    }

    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putBoolean("Active", active);
        nbtCompound.putBoolean("FogActive", fogActive);
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean bl) {
        this.active = bl;
        sync();
    }

    public boolean getFogActive() {
        return this.fogActive;
    }

    public void setFogActive(boolean bl) {
        this.fogActive = bl;
        sync();
    }
}