package net.chemthunder.occidere.api;

import net.minecraft.item.ItemStack;

public interface ColorableItem {
    int startColor(ItemStack stack);
    int endColor(ItemStack stack);
    int bgColor(ItemStack stack);
}
