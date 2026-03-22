package net.chemthunder.occidere.impl.item;

import net.chemthunder.occidere.api.ApiUtils;
import net.chemthunder.occidere.api.HandheldItem;
import net.chemthunder.occidere.api.IgnoredByRegisterLangItem;
import net.chemthunder.occidere.api.WeaponItem;
import net.chemthunder.occidere.impl.cca.entity.VainComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.List;

public class NyrulnaVainItem extends WeaponItem implements HandheldItem, IgnoredByRegisterLangItem {
    public NyrulnaVainItem(Settings settings) {
        super(settings, 8.0f, -2.9f, false);
    }

    public int getMaxUseTime(ItemStack stack) {
        return 400;
    }

    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (this.getMaxUseTime(stack) - remainingUseTicks >= 20) {
            if (user instanceof PlayerEntity player) {
                ApiUtils.applyRiptide(player, 3.5f, 20);

                VainComponent component = VainComponent.KEY.get(player);
                component.setActive(true);
            }
        }
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return super.use(world, user, hand);
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    public String getItemId() {
        return "nyrulna_vain";
    }

    public String handheldId() {
        return "handheld";
    }

    public int getNameColor(ItemStack stack) {
        return 0xFF3d2159;
    }

    public void impact(PlayerEntity player) {
        List<LivingEntity> entities = ApiUtils.getEntitiesInBox(player.getBlockPos(), player.getWorld(), 7);

        for (LivingEntity living : entities) {
            living.setVelocity(player.getBlockPos().subtract(living.getBlockPos()).multiply(-1).toCenterPos());
        }

        player.setVelocity(0, 0, 0);

        if (player.getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.END_ROD,
                    player.getX(),
                    player.getY() + 1.0f,
                    player.getZ(),
                    40,
                    0,
                    0,
                    0,
                    0.2f
            );

            serverWorld.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.ITEM_TRIDENT_THUNDER,
                    SoundCategory.PLAYERS,
                    1,
                    1
            );
        }
    }
}
