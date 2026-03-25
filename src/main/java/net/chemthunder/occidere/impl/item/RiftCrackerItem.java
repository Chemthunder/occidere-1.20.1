package net.chemthunder.occidere.impl.item;

import net.chemthunder.occidere.api.extendable.MiscItem;
import net.chemthunder.occidere.api.interfaces.IgnoredByRegisterLangItem;
import net.chemthunder.occidere.impl.cca.item.RiftCrackerComponent;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RiftCrackerItem extends MiscItem implements IgnoredByRegisterLangItem {
    public RiftCrackerItem(Settings settings) {
        super(settings);
    }

    public int getNameColor(ItemStack stack) {
        return 0xFFffd447;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        RiftCrackerComponent component = RiftCrackerComponent.KEY.get(user.getStackInHand(hand));


        if (user.isSneaking()) {
            component.setX(Math.round(user.getX()));
            component.setY(Math.round(user.getY()));
            component.setZ(Math.round(user.getZ()));

            user.sendMessage(Text.literal("Set Waypoint to: " + component.getX() + ", " + component.getY() + ", " + component.getZ()).formatted(Formatting.GOLD), true);
        } else {
            user.teleport(component.getX(), component.getY(), component.getZ(), true);
        }

        return super.use(world, user, hand);
    }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        RiftCrackerComponent component = RiftCrackerComponent.KEY.get(stack);

        double x = component.getX();
        double y = component.getY();
        double z = component.getZ();

        tooltip.add(
                Text.literal("Waypoint at: ").formatted(Formatting.DARK_GRAY)
                        .append(Text.literal(x + "").formatted(Formatting.GOLD)
                                .append(Text.literal(", ").formatted(Formatting.DARK_GRAY)
                                        .append(Text.literal(y + "").formatted(Formatting.GOLD)
                                                .append(Text.literal(", ").formatted(Formatting.DARK_GRAY)
                                                        .append(Text.literal(z + "").formatted(Formatting.GOLD))
                                                )
                                        )
                                )
                        )
        );

        super.appendTooltip(stack, world, tooltip, context);
    }

    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        RiftCrackerComponent component = RiftCrackerComponent.KEY.get(stack);

        if (attacker.isSneaking()) {
            target.teleport(component.getX(), component.getY(), component.getZ(), true);
            target.getWorld().playSound(null, target.getBlockPos(), SoundEvents.ITEM_TRIDENT_THUNDER, SoundCategory.PLAYERS, 1, 1);
        }

        return super.postHit(stack, target, attacker);
    }
}