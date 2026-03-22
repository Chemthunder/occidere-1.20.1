package net.chemthunder.occidere.impl.entity;

import net.chemthunder.occidere.api.ApiUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AurumEntity extends ThrownEntity implements Ownable {
    private final int maxAge = 268;

    public AurumEntity(EntityType<? extends ThrownEntity> entityType, World world) {
        super(entityType, world);
    }


    protected void initDataTracker() {}

    public void tick() {
        if (this.age >= this.maxAge) {
            this.discard();
        } else {
            List<LivingEntity> entities = ApiUtils.getEntitiesInBox(this.getBlockPos(), getWorld(), 10);

            for (LivingEntity living : entities) {
                if (living != this.getOwner()) {
                    living.damage(living.getDamageSources().magic(), 1.0f);
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
