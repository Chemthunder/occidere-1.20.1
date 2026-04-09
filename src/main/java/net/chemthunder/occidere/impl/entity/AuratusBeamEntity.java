package net.chemthunder.occidere.impl.entity;

import net.chemthunder.occidere.impl.index.OccidereItems;
import net.chemthunder.occidere.impl.index.OccidereParticles;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public class AuratusBeamEntity extends ThrownItemEntity {
    private final int lifespan = 200;

    public AuratusBeamEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    protected Item getDefaultItem() {
        return OccidereItems.AURATUS;
    }

    public void tick() {
        getWorld().addParticle(ParticleTypes.END_ROD,
                true,
                this.getX(),
                this.getY(),
                this.getZ(),
                0,
                0,
                0
        );

        getWorld().addParticle(ParticleTypes.EFFECT,
                true,
                this.getX(),
                this.getY(),
                this.getZ(),
                0,
                0,
                0
        );

        if (this.age >= this.lifespan) {
            this.discard();
        }
        super.tick();
    }

    protected void onBlockHit(BlockHitResult blockHitResult) {
        Entity saucey = this.getOwner();

        if (saucey != null) {
            if (saucey instanceof PlayerEntity player) {
                if (!getWorld().getBlockState(blockHitResult.getBlockPos()).isOf(Blocks.AIR)) {
                    player.teleport(this.getBlockX(), this.getBlockY(), this.getBlockZ());
                    this.discard();
                }
            }
        }
        super.onBlockHit(blockHitResult);
    }

    public boolean hasNoGravity() {
        return true;
    }
}
