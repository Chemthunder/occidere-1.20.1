package net.chemthunder.occidere.impl.item;

import net.chemthunder.occidere.api.HandheldItem;
import net.chemthunder.occidere.api.IgnoredByRegisterLangItem;
import net.chemthunder.occidere.api.WeaponItem;
import net.minecraft.item.ItemStack;

public class FlayedLustItem extends WeaponItem implements HandheldItem, IgnoredByRegisterLangItem {
    public FlayedLustItem(Settings settings) {
        super(settings, 8.0f, -2.7f, true);
    }




    public String getItemId() {
        return "flayed_lust";
    }

    public String handheldId() {
        return "handheld";
    }

    public int getNameColor(ItemStack stack) {
        return 0xFFa8325c;
    }
}
