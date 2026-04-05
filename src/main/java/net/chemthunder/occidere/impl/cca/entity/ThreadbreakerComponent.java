package net.chemthunder.occidere.impl.cca.entity;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.chemthunder.occidere.impl.Occidere;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class ThreadbreakerComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static ComponentKey<ThreadbreakerComponent> KEY = ComponentRegistry.getOrCreate(Occidere.id("threadbreaker"), ThreadbreakerComponent.class);

    private final LivingEntity player;

    public int tetherPosX = 0;
    public int tetherPosY = 0;
    public int tetherPosZ = 0;

    private int tetheredTicks = 0;

    public void sync() {
        KEY.sync(player);
    }

    public ThreadbreakerComponent(LivingEntity player) {
        this.player = player;
    }

    public void readFromNbt(NbtCompound nbtCompound) {
        this.tetheredTicks = nbtCompound.getInt("TetheredTicks");

        this.tetherPosX = nbtCompound.getInt("TetherPosX");
        this.tetherPosY = nbtCompound.getInt("TetherPosY");
        this.tetherPosZ = nbtCompound.getInt("TetherPosZ");
    }

    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putInt("TetheredTicks", tetheredTicks);

        nbtCompound.putInt("TetherPosX", tetherPosX);
        nbtCompound.putInt("TetherPosY", tetherPosY);
        nbtCompound.putInt("TetherPosZ", tetherPosZ);
    }

    public void tick() {
        if (this.tetheredTicks > 0) {
            this.tetheredTicks--;

            if (this.tetheredTicks == 0) {
                lockAndLoad(player);
                sync();
            }
        }
    }

    private void lockAndLoad(LivingEntity living) {
        BlockPos tether = new BlockPos(this.tetherPosX, this.tetherPosY, this.tetherPosZ);

        if (living != null) {
            if (living.getBlockPos() != tether) {
                living.teleport(this.tetherPosX, this.tetherPosY, this.tetherPosZ, true);
                double damage = living.getPos().distanceTo(tether.toCenterPos());

                living.damage(living.getDamageSources().generic(), (float) damage);
            }
        }
    }
}
