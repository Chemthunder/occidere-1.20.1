package net.chemthunder.occidere.impl.entity;

import net.chemthunder.lux.api.LuxFlashRenderer;
import net.chemthunder.lux.impl.util.Easing;
import net.chemthunder.occidere.api.ApiUtils;
import net.chemthunder.occidere.impl.cca.entity.AccursedComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import java.util.List;

public class WovenAdmirationEntity extends Entity {
    public int ticksActive;
    public boolean reachedFinisher;

    public WovenAdmirationEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    public void tick() {
        if (this.ticksActive < 300 && !reachedFinisher) {
            ticksActive++;
        } else {
            reachedFinisher = true;
            ticksActive = 0;
            getWorld().playSound(null, this.getBlockPos(), SoundEvents.ITEM_TRIDENT_THUNDER, SoundCategory.MASTER, 1, 0.2f);

            if (this.hasPassengers()) {
                Entity entity = this.getFirstPassenger();

                if (entity instanceof LivingEntity living && !(entity instanceof PlayerEntity)) {
                    living.kill();
                }

                if (entity instanceof PlayerEntity player) {
                    AccursedComponent component = AccursedComponent.KEY.get(player);

                    component.setActive(true);
                }

                List<Entity> targets = ApiUtils.getEntitiesInBox(this.getBlockPos(), getWorld(), 50);

                for (Entity living : targets) {
                    if (living instanceof PlayerEntity player) {
                        LuxFlashRenderer.sendFlash(player, 0xffffff, Easing.linear);
                    }
                }
            }

            if (getWorld() instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(ParticleTypes.END_ROD,
                        this.getX(),
                        this.getY() + 0.5f,
                        this.getZ(),
                        48,
                        0,
                        0,
                        0,
                        0.4f
                );

                serverWorld.spawnParticles(ParticleTypes.SOUL,
                        this.getX(),
                        this.getY() + 0.5f,
                        this.getZ(),
                        48,
                        0,
                        0,
                        0,
                        0.4f
                );
            }

            this.discard();
        }

        if (getWorld() instanceof ServerWorld serverWorld) {
            if (this.ticksActive > 150) {
                serverWorld.spawnParticles(ParticleTypes.END_ROD,
                        this.getX(),
                        this.getY() + 0.5f,
                        this.getZ(),
                        16,
                        0,
                        0,
                        0,
                        1
                );
            }
        }
        super.tick();
    }

    protected void initDataTracker() {}

    protected void readCustomDataFromNbt(NbtCompound nbt) {
        this.ticksActive = nbt.getInt("TicksActive");
        this.reachedFinisher = nbt.getBoolean("ReachedFinisher");
    }

    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putInt("TicksActive", ticksActive);
        nbt.putBoolean("ReachedFinisher", reachedFinisher);
    }

    public boolean canMoveVoluntarily() {
        return false;
    }

    public void pushAwayFrom(Entity entity) {}

    public boolean isPushable() {
        return false;
    }

    public boolean hasNoGravity() {
        return true;
    }

    public boolean canHit() {
        return false;
    }
}