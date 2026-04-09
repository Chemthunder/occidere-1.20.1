package net.chemthunder.occidere.impl.manager;

import net.chemthunder.occidere.impl.cca.ivoryevent.AuratusStunlockComponent;
import net.chemthunder.occidere.impl.entity.AuratusBeamEntity;
import net.chemthunder.occidere.impl.index.OccidereDamageSources;
import net.chemthunder.occidere.impl.index.OccidereEntities;
import net.chemthunder.occidere.impl.index.OccidereUUIDs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class IvoryEventManager {

    public static boolean isIvory(Entity entity) {
        if (entity != null) {
            if (entity instanceof PlayerEntity player) {
                return player.getUuid().equals(OccidereUUIDs.SCARLET_IVORY);
            }
        }
        return false;
    }

    public static void auratus$getHitAbility(PlayerEntity player, LivingEntity target, ItemStack stack, World world) {
        AuratusStunlockComponent component = AuratusStunlockComponent.KEY.get(target);

        if (player.isSneaking()) {
            if (component.getActiveTicks() == 0) {
                component.setActiveTicks(400);

                world.playSoundFromEntity(null, target, SoundEvents.ITEM_TRIDENT_THUNDER, SoundCategory.PLAYERS, 1, 1);
                world.playSoundFromEntity(null, target, SoundEvents.ENTITY_ENDER_DRAGON_HURT, SoundCategory.PLAYERS, 1, 1);

                if (world instanceof ServerWorld serverWorld) {
                    serverWorld.spawnParticles(ParticleTypes.END_ROD,
                            target.getX(),
                            target.getY() + 0.5f,
                            target.getZ(),
                            25,
                            0,
                            0,
                            0,
                            0.3f);
                }
            }
        }
    }

    public static void heretic$getHitAbility(PlayerEntity player, LivingEntity target, ItemStack stack, World world) {
        if (player.isSneaking()) {
            target.damage(OccidereDamageSources.lifesteal(player), target.getHealth() / 2);
            player.heal(target.getHealth() / 2);
        }
    }

    public static void auratus$getUseAbility(PlayerEntity player, ItemStack stack, World world) {
        if (!player.getItemCooldownManager().isCoolingDown(stack.getItem())) {
            AuratusBeamEntity chain = new AuratusBeamEntity(OccidereEntities.AURATUS_CHAIN, world);

            chain.setPosition(player.getX(), player.getEyeY() - 0.10000000149011612, player.getZ());
            chain.setVelocity(player, player.getPitch(), player.getHeadYaw(), 0.0f, 2.5f, 0f);
            chain.setOwner(player);

            world.spawnEntity(chain);
        }
    }

    public static void heretic$getUseAbility(PlayerEntity player, ItemStack stack, World world) {
        //
    }

    public static void ivoryEvent$commonHitAbility(PlayerEntity player, LivingEntity target, ItemStack stack, World world) {

    }
}
