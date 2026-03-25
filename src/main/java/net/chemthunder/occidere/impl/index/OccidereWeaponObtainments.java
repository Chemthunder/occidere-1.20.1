package net.chemthunder.occidere.impl.index;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.LinkedList;
import java.util.List;

// inspired by / modified of Onlooker by AcoYT
public interface OccidereWeaponObtainments {
    List<Obtainment> OBTAINMENTS = new LinkedList<>(); // we'll see what works :p

    Obtainment TEST = create(Items.OXEYE_DAISY, Items.OXEYE_DAISY, Blocks.GRASS_BLOCK, Items.NETHERITE_SWORD);

    Obtainment VAIN = create(Items.HEART_OF_THE_SEA, Items.BRAIN_CORAL, Blocks.DARK_PRISMARINE, OccidereItems.NYRULNA_VAIN);
    Obtainment HEART = create(Items.NETHERITE_SWORD, Items.BEEF, Blocks.BONE_BLOCK, OccidereItems.BEATING_HEART);
    Obtainment FLAYED_LUST = create(Items.IRON_SWORD, Items.NETHER_WART, Blocks.SMITHING_TABLE, OccidereItems.FLAYED_LUST);
    Obtainment AURUM = create(Items.GOLDEN_SWORD, Items.NETHERITE_SCRAP, Blocks.SMITHING_TABLE, OccidereItems.AURUM);

    Obtainment PACT = create(Items.PAPER, Items.BONE, Blocks.FLETCHING_TABLE, OccidereItems.PACT);
    Obtainment RIFT_CRACKER = create(Items.END_CRYSTAL, Items.ENDER_PEARL, Blocks.RAW_GOLD_BLOCK, OccidereItems.RIFT_CRACKER);

    static Obtainment create(Item base, Item add, Block interactor, Item result) {
        return new Obtainment(base, add, interactor, result);
    }

    static void init() {
        OBTAINMENTS.add(VAIN);
        OBTAINMENTS.add(HEART);
        OBTAINMENTS.add(AURUM);
        OBTAINMENTS.add(PACT);
        OBTAINMENTS.add(FLAYED_LUST);
    }

    static boolean isBaseItem(Item toTest) {
        for (Obtainment ob : OBTAINMENTS) {
            if (ob.base.equals(toTest)) {
                return true;
            }
        }
        return false;
    }

    static boolean isAdditionalItem(Item toTest) {
        for (Obtainment ob : OBTAINMENTS) {
            if (ob.add.equals(toTest)) {
                return true;
            }
        }
        return false;
    }

    static boolean isAcceptableBlock(Block toTest) {
        for (Obtainment ob : OBTAINMENTS) {
            if (ob.interactor.equals(toTest)) {
                return true;
            }
        }
        return false;
    }

    static Item getResult(Item base, Item add) {
        for (Obtainment ob : OBTAINMENTS) {
            if (ob.base == base && ob.add == add) {
                return ob.result;
            }
        }

        return Items.AIR;
    }

    record Obtainment(Item base, Item add, Block interactor, Item result) {}
}
