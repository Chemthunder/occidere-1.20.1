package net.chemthunder.occidere.api.extendable;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class MiscItem extends Item {
    public MiscItem(Settings settings) {
        super(settings);
    }

    public int getNameColor(ItemStack stack) {
        return 0xFFffffff;
    }

    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().styled(style -> style.withColor(getNameColor(stack)));
    }
}
