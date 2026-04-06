package net.chemthunder.occidere.impl.item;

import net.chemthunder.legere.api.v1.util.ApiUtils;
import net.chemthunder.legere.api.v1.extendable.item.MiscItem;
import net.chemthunder.legere.api.v1.interfaces.ComplexModelItem;
import net.chemthunder.occidere.impl.cca.item.VulkanItemComponent;
import net.chemthunder.occidere.impl.entity.VulkanShotEntity;
import net.chemthunder.occidere.impl.index.OccidereEntities;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VulkanItem extends MiscItem implements ComplexModelItem {
    public final int maxAmmo = 6;

    public VulkanItem(Settings settings) {
        super(settings);
    }

    public int getNameColor(ItemStack stack) {
        return 0xFFfcba03;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        VulkanItemComponent comp = VulkanItemComponent.KEY.get(user.getStackInHand(hand));

        if (!user.isSneaking()) {
            if (comp.getRemainingAmmo() > 0) {
                for (int i = 0; i < world.getRandom().nextBetween(2, 3); i++) {
                    VulkanShotEntity createdEntity = new VulkanShotEntity(OccidereEntities.VULKAN_SHOT, world);

                    createdEntity.setPosition(user.getX(), user.getEyeY() - 0.10000000149011612, user.getZ());
                    createdEntity.setVelocity(user, user.getPitch(), user.getHeadYaw(), 0.0f, 2.5f, 3.0f);
                    world.spawnEntity(createdEntity);
                }

                comp.subtractAmmo();
            }
        } else {
            user.setCurrentHand(hand);
        }

        return super.use(world, user, hand);
    }

    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        VulkanItemComponent comp = VulkanItemComponent.KEY.get(stack);

        if (user instanceof PlayerEntity player) {
            if (!player.getItemCooldownManager().isCoolingDown(this)) {
                if (player.isSneaking()) {
                    if (comp.getRemainingAmmo() < maxAmmo) {
                        comp.addAmmo();
                        player.getItemCooldownManager().set(this, 20);
                    }
                }
            }
        }


        super.usageTick(world, user, stack, remainingUseTicks);
    }

    public void onEntityHit(VulkanShotEntity entity, PlayerEntity player, ItemStack stack, LivingEntity target) {

    }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        VulkanItemComponent comp = VulkanItemComponent.KEY.get(stack);

        tooltip.add(Text.literal(comp.getRemainingAmmo() + " - -").styled(style -> style.withColor(getNameColor(stack))));

        super.appendTooltip(stack, world, tooltip, context);
    }

    public int getItemBarStep(ItemStack stack) {
        VulkanItemComponent comp = VulkanItemComponent.KEY.get(stack);
        return Math.round((float) comp.getRemainingAmmo() / maxAmmo * 13);
    }

    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    public int getItemBarColor(ItemStack stack) {
        return this.getNameColor(stack);
    }

    public String getModel(ItemStack stack, PlayerEntity player, ModelTransformationMode modelTransformationMode, World world) {
        if (player.isSneaking()) {
            return ApiUtils.isGui(modelTransformationMode) ? "vulkan" : "vulkan_loading";
        }
        return ApiUtils.isGui(modelTransformationMode) ? "vulkan" : "vulkan_handheld";
    }

    public List<String> getLoadedModels() {
        return List.of(
                "vulkan",
                "vulkan_handheld",
                "vulkan_loading"
        );
    }
}
