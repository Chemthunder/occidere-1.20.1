package net.chemthunder.occidere.impl.item.weapon;

import net.chemthunder.legere.api.v1.util.ApiUtils;
import net.chemthunder.legere.api.v1.extendable.item.WeaponItem;
import net.chemthunder.occidere.api.interfaces.IgnoredByRegisterLangItem;
import net.chemthunder.legere.api.v1.interfaces.SimpleModelItem;
import net.chemthunder.occidere.impl.cca.entity.HeartComponent;
import net.chemthunder.occidere.impl.cca.item.HeartItemComponent;
import net.chemthunder.occidere.impl.entity.BoneShardEntity;
import net.chemthunder.occidere.impl.index.OccidereDamageSources;
import net.chemthunder.occidere.impl.index.OccidereEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

@SuppressWarnings("rawtypes")
public class BeatingHeartItem extends WeaponItem implements SimpleModelItem, IgnoredByRegisterLangItem {
    List<EntityType> IGNORED = List.of(
            EntityType.ARROW,
            EntityType.ITEM,
            EntityType.TRIDENT,
            EntityType.ITEM_FRAME,
            EntityType.GLOW_ITEM_FRAME,
            EntityType.ARMOR_STAND,
            EntityType.MINECART,
            EntityType.BOAT,
            EntityType.CHEST_BOAT
    );

    public BeatingHeartItem(Settings settings) {
        super(settings, 8.5f, -2.9f, true);
    }

    public String getItemId() {
        return "beating_heart";
    }

    public String handheldId() {
        return "handheld";
    }

    public int getNameColor(ItemStack stack) {
        return 0xFF7a1d32;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        HeartComponent heart = HeartComponent.KEY.get(user);
        HeartItemComponent heartItem = HeartItemComponent.KEY.get(stack);
        Vec3d pos = user.getPos();

        if (!user.isSneaking()) {
            if (heart.getCapturedBones() != heart.max) {
                BoneShardEntity shard = new BoneShardEntity(OccidereEntities.BONE_SHARD, world);

                shard.setPos(pos.x, pos.y + 1.5f, pos.z);
                shard.setOwner(user);

                world.spawnEntity(shard);
                heart.addCapturesBones();
                heartItem.addCapturedBones();

                world.playSound(null,
                        user.getBlockPos(),
                        SoundEvents.ENTITY_WITHER_BREAK_BLOCK,
                        SoundCategory.PLAYERS,
                        0.3f,
                        1
                );

                ApiUtils.spawnSweepAttackParticles(user);
                ApiUtils.applyCooldown(user, this, 20 + (heart.getCapturedBones() + 3));

                ApiUtils.swingHand(user, hand);
            }
        } else {
            List<Entity> t = ApiUtils.getEntitiesInBox(user.getBlockPos(), world, 9000);

            for (Entity e : t) {
                if (e instanceof BoneShardEntity entity) {
                    if (entity.getOwner() != null) {
                        if (entity.getOwner().equals(user)) {
                            if (world instanceof ServerWorld serverWorld) {
                                serverWorld.spawnParticles(ParticleTypes.SMOKE,
                                        entity.getX(),
                                        entity.getY(),
                                        entity.getZ(),
                                        world.getRandom().nextBetween(6, 11),
                                        0,
                                        0,
                                        0,
                                        0.1f
                                );

                                serverWorld.spawnParticles(ParticleTypes.EXPLOSION,
                                        entity.getX(),
                                        entity.getY(),
                                        entity.getZ(),
                                        1,
                                        0,
                                        0,
                                        0,
                                        1f
                                );

                                serverWorld.playSound(null,
                                        entity.getX(),
                                        entity.getY(),
                                        entity.getZ(),
                                        SoundEvents.BLOCK_MANGROVE_ROOTS_BREAK,
                                        SoundCategory.PLAYERS,
                                        1,
                                        0.2f
                                );

                                serverWorld.playSound(null,
                                        entity.getX(),
                                        entity.getY(),
                                        entity.getZ(),
                                        SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE,
                                        SoundCategory.PLAYERS,
                                        1,
                                        0.2f
                                );
                            }

                            List<Entity> targets = ApiUtils.getEntitiesInBox(entity.getBlockPos(), world, 7);

                            for (Entity gooner : targets) {
                                if (!(IGNORED.contains(gooner.getType()))) {
                                    gooner.damage(OccidereDamageSources.boneShard(user), 3.0f);
                                } else {
                                    gooner.setVelocity(0, 0, 0);
                                }
                            }
                            entity.discard();
                        }
                    }
                }
            }
            heart.setCapturedBones(0);
            heartItem.setCapturedBones(0);
            ApiUtils.applyCooldown(user, this, 120);
        }

        return super.use(world, user, hand);
    }

    public int getItemBarStep(ItemStack stack) {
        HeartItemComponent heartComponent = HeartItemComponent.KEY.get(stack);
        return Math.round((float) heartComponent.getCapturedBones() / 7 * 13);
    }

    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }
}