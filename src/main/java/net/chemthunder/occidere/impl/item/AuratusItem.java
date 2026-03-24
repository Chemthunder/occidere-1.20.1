package net.chemthunder.occidere.impl.item;

import net.chemthunder.occidere.api.ApiUtils;
import net.chemthunder.occidere.api.MiscItem;
import net.chemthunder.occidere.impl.cca.entity.AccursedComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;

import java.util.List;

public class AuratusItem extends MiscItem {
    public AuratusItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            List<LivingEntity> entities = ApiUtils.getEntitiesInBox(user.getBlockPos(), world, 10);

            for (LivingEntity entity : entities) {
                if (entity instanceof ServerPlayerEntity serverPlayer) {
                    AccursedComponent component = AccursedComponent.KEY.get(serverPlayer);

                    if (component.getActive()) {
                        component.setActive(false);

                        user.sendMessage(Text.literal(serverPlayer.getEntityName() + " was revived successfully"));
                        serverPlayer.changeGameMode(GameMode.SURVIVAL);
                    }
                }
            }

            user.swingHand(hand);
        }

        return super.use(world, user, hand);
    }

    public int getNameColor(ItemStack stack) {
        return 0xFFb89848;
    }
}
