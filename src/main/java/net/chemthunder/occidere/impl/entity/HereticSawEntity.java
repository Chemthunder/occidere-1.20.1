package net.chemthunder.occidere.impl.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.world.World;

public class HereticSawEntity extends ThrownEntity {
    public HereticSawEntity(EntityType<? extends ThrownEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initDataTracker() {}

    public boolean hasNoGravity() {
        return true;
    }
}
