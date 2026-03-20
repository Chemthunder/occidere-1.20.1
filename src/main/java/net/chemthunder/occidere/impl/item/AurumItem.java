package net.chemthunder.occidere.impl.item;

import net.chemthunder.occidere.api.HandheldItem;
import net.chemthunder.occidere.api.WeaponItem;
import net.minecraft.item.ItemStack;

public class AurumItem extends WeaponItem implements HandheldItem {
    public AurumItem(Settings settings) {
        super(settings, 7.5f, -2.6f, true);
    }

    public String getItemId() {
        return "aurum";
    }

    public String handheldId() {
        return "handheld";
    }

    public int getNameColor(ItemStack stack) {
        return 0xFFa54d45;
    }
}
