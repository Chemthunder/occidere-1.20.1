package net.chemthunder.occidere.impl.item;

import net.chemthunder.legere.api.v1.extendable.item.MiscItem;
import net.minecraft.item.ItemStack;

public class DisfiguredMaskItem extends MiscItem {
    public DisfiguredMaskItem(Settings settings) {
        super(settings);
    }

    public int getNameColor(ItemStack stack) {
        return 0xFFab5c7e;
    }
}