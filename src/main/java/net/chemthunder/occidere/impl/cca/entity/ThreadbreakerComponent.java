package net.chemthunder.occidere.impl.cca.entity;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.CommonTickingComponent;
import net.chemthunder.occidere.impl.Occidere;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class ThreadbreakerComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static ComponentKey<ThreadbreakerComponent> KEY = ComponentRegistry.getOrCreate(Occidere.id("threadbreaker"), ThreadbreakerComponent.class);

    private final LivingEntity player;

    public double tetherPosX = 0;
    public double tetherPosY = 0;
    public double tetherPosZ = 0;

    private int tetheredTicks = 0;

    public void sync() {
        KEY.sync(player);
    }

    public ThreadbreakerComponent(LivingEntity player) {
        this.player = player;
    }

    public void readFromNbt(NbtCompound nbtCompound) {
        this.tetheredTicks = nbtCompound.getInt("TetheredTicks");

        this.tetherPosX = nbtCompound.getDouble("TetherPosX");
        this.tetherPosY = nbtCompound.getDouble("TetherPosY");
        this.tetherPosZ = nbtCompound.getDouble("TetherPosZ");
    }

    public void writeToNbt(NbtCompound nbtCompound) {
        nbtCompound.putInt("TetheredTicks", tetheredTicks);

        nbtCompound.putDouble("TetherPosX", tetherPosX);
        nbtCompound.putDouble("TetherPosY", tetherPosY);
        nbtCompound.putDouble("TetherPosZ", tetherPosZ);
    }

    public int getTetheredTicks() {
        return this.tetheredTicks;
    }

    public void setTetheredTicks(int i) {
        this.tetheredTicks = i;
        this.sync();
    }

    public void tick() {
        if (this.tetheredTicks > 0) {
            this.tetheredTicks--;

            if (player.getWorld() instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(ParticleTypes.WAX_ON,
                        player.getX(),
                        player.getY() + 0.5f,
                        player.getZ(),
                        2,
                        0,
                        0,
                        0,
                        0.2f
                );

                serverWorld.spawnParticles(ParticleTypes.END_ROD,
                        this.tetherPosX,
                        this.tetherPosY + 0.5f,
                        this.tetherPosZ,
                        1,
                        0,
                        0,
                        0,
                        0f
                );
            }

            if (this.tetheredTicks == 0) {
                lockAndLoad(player);
                sync();
            }
        }
    }

    private void lockAndLoad(LivingEntity living) {
        BlockPos tether = new BlockPos((int) this.tetherPosX, (int) this.tetherPosY, (int) this.tetherPosZ);

        if (living != null) {
            living.teleport(this.tetherPosX, this.tetherPosY, this.tetherPosZ, false);
            float damage = Math.round(living.getPos().distanceTo(tether.toCenterPos()) * 4);

            living.damage(living.getDamageSources().generic(), damage);

            if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
                Occidere.LOGGER.info("Dealt {} damage", damage);
                Occidere.LOGGER.info("Threaadbreaker completed");
                Occidere.LOGGER.info("spacing message {}", living.getWorld().getRandom().nextBetween(0, 90));
            }
        }
    }
}
