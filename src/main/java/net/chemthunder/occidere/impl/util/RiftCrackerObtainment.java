package net.chemthunder.occidere.impl.util;

import net.chemthunder.legere.api.v1.extendable.SpecialObtainment;
import net.chemthunder.occidere.impl.index.OccidereItems;
import net.chemthunder.occidere.impl.index.api.OccidereUUIDs;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.UUID;

public class RiftCrackerObtainment extends SpecialObtainment {
    public static UUID getPlayerSpecificUUID() {
        return OccidereUUIDs.HSTAR;
    }

    public static Item getBase() {
        return Items.END_CRYSTAL;
    }

    public static Item getAdd() {
        return Items.ENDER_PEARL;
    }

    public static Block getToInteract() {
        return Blocks.RAW_GOLD_BLOCK;
    }

    public static Item getResult() {
        return OccidereItems.RIFT_CRACKER;
    }
}