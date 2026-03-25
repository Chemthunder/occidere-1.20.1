package net.chemthunder.occidere.api.extendable;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.util.UUID;

@SuppressWarnings("unused")
public class SpecialObtainment {
    public static UUID getPlayerSpecificUUID() {return null;}
    public static void onCreateEffects(PlayerEntity player, World world) {}

    public static Item getBase() {
        return null;
    }

    public static Item getAdd() {
        return null;
    }

    public static Block getToInteract() {
        return null;
    }

    public static Item getResult() {
        return null;
    }
}