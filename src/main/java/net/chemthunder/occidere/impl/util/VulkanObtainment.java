package net.chemthunder.occidere.impl.util;

import net.chemthunder.legere.api.v1.extendable.SpecialObtainment;
import net.chemthunder.occidere.impl.index.OccidereItems;
import net.chemthunder.occidere.impl.index.OccidereUUIDs;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.World;

import java.util.UUID;

public class VulkanObtainment extends SpecialObtainment {
    public static UUID getPlayerSpecificUUID() {return OccidereUUIDs.TURTLE;}
    public static void onCreateEffects(PlayerEntity player, World world) {}

    public static Item getBase() {
        return Items.CROSSBOW;
    }

    public static Item getAdd() {
        return Items.GILDED_BLACKSTONE;
    }

    public static Block getToInteract() {
        return Blocks.SMITHING_TABLE;
    }

    public static Item getResult() {
        return OccidereItems.VULKAN;
    }
}