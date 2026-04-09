package net.chemthunder.occidere.impl.cca.ivoryevent;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.chemthunder.occidere.impl.Occidere;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;

public class AuratusStunlockComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<AuratusStunlockComponent> KEY = ComponentRegistry.getOrCreate(Occidere.id("auratus_ivory"), AuratusStunlockComponent.class);

    private final LivingEntity living;
    private int activeTicks = 0;
    private boolean isStunlocked = false;

    public AuratusStunlockComponent(LivingEntity living) {
        this.living = living;
    }

    public void sync() {
        KEY.sync(living);
    }

    public void tick() {
        if (this.activeTicks > 0) {
            this.activeTicks--;
            this.isStunlocked = true;
            if (this.activeTicks == 0) {
                this.isStunlocked = false;
                sync();
            }
        }
    }

    public void readFromNbt(NbtCompound nbtCompound) {
        this.activeTicks = nbtCompound.getInt("ActiveTicks");
        this.isStunlocked = nbtCompound.getBoolean("IsStunlocked");
    }

    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putInt("ActiveTicks", activeTicks);
        nbtCompound.putBoolean("IsStunlocked", isStunlocked);
    }

    public void setActiveTicks(int ticks) {
        this.activeTicks = ticks;
        sync();
    }

    public int getActiveTicks() {
        return this.activeTicks;
    }
}
