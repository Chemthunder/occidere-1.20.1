package net.chemthunder.occidere.impl.entity;

import net.chemthunder.occidere.impl.index.OccidereDamageSources;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class VulkanShotEntity extends ThrownEntity {
    public VulkanShotEntity(EntityType<? extends ThrownEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initDataTracker() {}

    public void tick() {
        getWorld().addParticle(ParticleTypes.FLAME,
                true,
                this.getX(),
                this.getY(),
                this.getZ(),
                0,
                0,
                0
        );


        super.tick();
    }

    protected void onBlockCollision(BlockState state) {
        if (!state.isOf(Blocks.AIR)) {
            this.discard();
        }
        super.onBlockCollision(state);
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();

        if (entity instanceof LivingEntity target) {
            this.discard();

            target.damage(OccidereDamageSources.gunshot(target), 2.0f);
        }
        super.onEntityHit(entityHitResult);
    }
}