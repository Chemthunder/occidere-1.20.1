package net.chemthunder.occidere.impl.entity;

import net.chemthunder.occidere.api.ApiUtils;
import net.chemthunder.occidere.impl.index.OccidereDamageSources;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

import java.util.List;

public class AurumEntity extends ThrownEntity implements Ownable {

    public AurumEntity(EntityType<? extends ThrownEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initDataTracker() {}

    public void tick() {
        int maxAge = 268;
        if (this.age >= maxAge) {
            if (getWorld() instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(ParticleTypes.END_ROD,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        4,
                        0,
                        0,
                        0,
                        0.1f
                );
            }

            this.discard();
        } else {
            List<Entity> entities = ApiUtils.getEntitiesInBox(this.getBlockPos(), getWorld(), 10);

            for (Entity living : entities) {
                if (living != this.getOwner()) {
                    living.damage(OccidereDamageSources.aurum(living), 1.0f);
                }
            }
        }
        super.tick();
    }

    public boolean hasNoGravity() {
        return true;
    }

    protected void readCustomDataFromNbt(NbtCompound nbt) {}
    protected void writeCustomDataToNbt(NbtCompound nbt) {}
}