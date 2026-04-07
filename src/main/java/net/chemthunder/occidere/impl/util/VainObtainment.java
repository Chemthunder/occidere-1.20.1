package net.chemthunder.occidere.impl.util;

import net.chemthunder.legere.api.v1.extendable.SpecialObtainment;
import net.chemthunder.occidere.impl.index.OccidereItems;
import net.chemthunder.occidere.impl.index.OccidereUUIDs;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.UUID;

public class VainObtainment extends SpecialObtainment {
    public static UUID getPlayerSpecificUUID() {
        return OccidereUUIDs.LECTRA;
    }

    public static Item getBase() {
        return Items.TRIDENT;
    }

    public static Item getAdd() {
        return Items.HEART_OF_THE_SEA;
    }

    public static Block getToInteract() {
        return Blocks.CONDUIT;
    }

    public static Item getResult() {
        return OccidereItems.NYRULNA_VAIN;
    }
}