package net.chemthunder.occidere.impl.manager;

import net.chemthunder.legere.api.v1.util.ApiUtils;
import net.chemthunder.lux.api.LuxFlashRenderer;
import net.chemthunder.occidere.impl.index.OccidereDamageSources;
import net.chemthunder.occidere.impl.index.OccidereUUIDs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.UUID;

public class HostessManager {

    public static boolean isHostess(Entity entity) {
        if (entity != null) {
            if (entity instanceof PlayerEntity player) {
                if (player.getUuid().equals(OccidereUUIDs.CHEM)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void contractAbility(PlayerEntity target, UUID targetUUID, PlayerEntity hostess, ItemStack stack) {
        if (targetUUID.equals(OccidereUUIDs.MIRA)) {
            target.sendMessage(Text.literal("You feel disfigured. What have you done?").styled(style -> style.withColor(0xFF7a1d32)).formatted(Formatting.ITALIC), true);
            target.damage(OccidereDamageSources.boneShard(hostess), 2.0f);

            target.setVelocity(0, 0, 0);
            hostess.setVelocity(0, 0, 0);

            hostess.sendMessage(Text.literal("<x>     <x>"), true);

            hostess.getWorld().playSoundFromEntity(null, target, SoundEvents.ENTITY_WITHER_HURT, SoundCategory.PLAYERS, 1, 1);
            hostess.getWorld().playSoundFromEntity(null, target, SoundEvents.ENTITY_PLAYER_HURT, SoundCategory.PLAYERS, 1, 1);
            hostess.getWorld().playSoundFromEntity(null, target, SoundEvents.ENTITY_WITHER_DEATH, SoundCategory.PLAYERS, 1, 1);

            ApiUtils.spawnSweepAttackParticles(target);
            ApiUtils.spawnSweepAttackParticles(hostess);

            LuxFlashRenderer.sendFlash(target, 0x7a1d32);
            LuxFlashRenderer.sendFlash(hostess, 0x7a1d32);

            target.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 35, 0));
        }
    }
}