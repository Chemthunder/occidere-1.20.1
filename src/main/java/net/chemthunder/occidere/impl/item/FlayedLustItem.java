package net.chemthunder.occidere.impl.item;

import net.chemthunder.occidere.api.ApiUtils;
import net.chemthunder.occidere.api.WeaponItem;
import net.chemthunder.occidere.api.interfaces.ComplexModelItem;
import net.chemthunder.occidere.api.interfaces.IgnoredByRegisterLangItem;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class FlayedLustItem extends WeaponItem implements ComplexModelItem, IgnoredByRegisterLangItem {
    public FlayedLustItem(Settings settings) {
        super(settings, 8.0f, -2.7f, true);
    }


    public int getNameColor(ItemStack stack) {
        return 0xFFa8325c;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!user.getItemCooldownManager().isCoolingDown(this)) {
            user.setCurrentHand(hand);
        }
        return super.use(world, user, hand);
    }

    public void block(PlayerEntity player, LivingEntity attacker, ItemStack stack, World world) {
        player.getItemCooldownManager().set(this, 40);
        player.stopUsingItem();

        attacker.setVelocity(player.getBlockPos().subtract(attacker.getBlockPos()).multiply(-1).toCenterPos());
        attacker.damage(attacker.getDamageSources().genericKill(), 4.0f);

        world.playSound(null, attacker.getBlockPos(), SoundEvents.ENTITY_ALLAY_HURT, SoundCategory.PLAYERS, 1, 0.2f);

        player.addVelocity(player.getRotationVec(0).multiply(-1));
        player.velocityModified = true;
    }

    public String getModel(ItemStack stack, PlayerEntity player, ModelTransformationMode modelTransformationMode, World world) {
        if (player.isUsingItem()) {
            return ApiUtils.isGui(modelTransformationMode) ? "flayed_lust" : "flayed_lust_blocking";
        }
        return ApiUtils.isGui(modelTransformationMode) ? "flayed_lust" : "flayed_lust_handheld";
    }

    public List<String> getLoadedModels() {
        return List.of(
                "flayed_lust",
                "flayed_lust_handheld",
                "flayed_lust_blocking"
        );
    }
}
