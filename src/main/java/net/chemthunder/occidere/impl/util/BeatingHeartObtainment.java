package net.chemthunder.occidere.impl.util;

import net.chemthunder.legere.api.v1.extendable.SpecialObtainment;
import net.chemthunder.occidere.impl.index.OccidereDamageSources;
import net.chemthunder.occidere.impl.index.OccidereItems;
import net.chemthunder.occidere.impl.index.api.OccidereUUIDs;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.UUID;

public class BeatingHeartObtainment extends SpecialObtainment {

    public static UUID getPlayerSpecificUUID() {return OccidereUUIDs.MIRA;}

    public static void onCreateEffects(PlayerEntity player, World world) {
        player.damage(OccidereDamageSources.boneShard(player), 2.0f);
        player.sendMessage(Text.literal("You feel disfigured.").styled(style -> style.withColor(0xFF7a1d32)), true);
    }

    public static Item getBase() {
        return Items.NETHERITE_SWORD;
    }

    public static Item getAdd() {
        return Items.BEEF;
    }

    public static Block getToInteract() {
        return Blocks.BONE_BLOCK;
    }

    public static Item getResult() {
        return OccidereItems.BEATING_HEART;
    }
}
