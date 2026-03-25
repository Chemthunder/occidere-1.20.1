package net.chemthunder.occidere.impl.item.weapon;

import net.chemthunder.occidere.api.ApiUtils;
import net.chemthunder.occidere.api.extendable.WeaponItem;
import net.chemthunder.occidere.api.interfaces.ComplexModelItem;
import net.chemthunder.occidere.api.interfaces.IgnoredByRegisterLangItem;
import net.chemthunder.occidere.impl.cca.entity.VainComponent;
import net.chemthunder.occidere.impl.index.OccidereDamageSources;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.List;

@SuppressWarnings("unused")
public class NyrulnaVainItem extends WeaponItem implements ComplexModelItem, IgnoredByRegisterLangItem {
    private final int ticksToActivate = 10;

    public NyrulnaVainItem(Settings settings) {
        super(settings, 8.0f, -2.9f, false);
    }

    public int getMaxUseTime(ItemStack stack) {
        return 400;
    }

    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (this.getMaxUseTime(stack) - remainingUseTicks >= ticksToActivate) {
            if (user instanceof PlayerEntity player) {
                ApiUtils.applyRiptide(player, 3.5f, 20);

                VainComponent component = VainComponent.KEY.get(player);
                component.setActive(true);

                if (world.isClient) {
                    player.playSound(SoundEvents.ITEM_TRIDENT_RIPTIDE_3, SoundCategory.PLAYERS, 1, 1.3f);
                }
            }
        }
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return super.use(world, user, hand);
    }

    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (this.getMaxUseTime(stack) - remainingUseTicks == ticksToActivate) {
            if (user instanceof ServerPlayerEntity player) {
                player.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING.value(), SoundCategory.PLAYERS, 1, 1);
                player.sendMessage(Text.translatable("text.occidere.vain_ready").formatted(Formatting.ITALIC).formatted(Formatting.DARK_PURPLE), true);
            }
        }
        super.usageTick(world, user, stack, remainingUseTicks);
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    public int getNameColor(ItemStack stack) {
        return 0xFF3d2159;
    }

    public void impact(PlayerEntity player, ItemStack stack) {
        List<Entity> entities = ApiUtils.getEntitiesInBox(player.getBlockPos(), player.getWorld(), 7);

        for (Entity living : entities) {
            if (living != player) {
                living.setVelocity(player.getBlockPos().subtract(living.getBlockPos()).multiply(-1).toCenterPos());

                living.damage(OccidereDamageSources.vainImpact(living), 4.5f);
            }
        }

        player.setVelocity(0, 0, 0);

        ApiUtils.applyCooldown(player, this, 180);

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

            serverWorld.spawnParticles(ParticleTypes.EXPLOSION,
                    player.getX(),
                    player.getY() + 1.0f,
                    player.getZ(),
                    1,
                    0,
                    0,
                    0,
                    0
            );
        }
    }

    public String getModel(ItemStack stack, PlayerEntity player, ModelTransformationMode modelTransformationMode, World world) {
        if (player.isUsingItem()) {
            return ApiUtils.isGui(modelTransformationMode) ? "nyrulna_vain" : "nyrulna_vain_charging";
        }
        return ApiUtils.isGui(modelTransformationMode) ? "nyrulna_vain" : "nyrulna_vain_handheld";
    }

    public List<String> getLoadedModels() {
        return List.of(
                "nyrulna_vain",
                "nyrulna_vain_handheld",
                "nyrulna_vain_charging"
        );
    }
}