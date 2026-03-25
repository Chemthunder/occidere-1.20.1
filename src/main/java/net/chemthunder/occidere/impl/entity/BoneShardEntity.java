package net.chemthunder.occidere.impl.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class BoneShardEntity extends ThrownEntity implements Ownable {
    public BoneShardEntity(EntityType<? extends ThrownEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initDataTracker() {}

    public void tick() {
        getWorld().addParticle(ParticleTypes.SOUL,
                true,
                this.getX(),
                this.getY(),
                this.getZ(),
                0,
                0,
                0
        );

        getWorld().addParticle(ParticleTypes.EFFECT,
                false,
                this.getX(),
                this.getY(),
                this.getZ(),
                0,
                0,
                0
        );

        super.tick();
    }

    public boolean hasNoGravity() {
        return true;
    }
}
