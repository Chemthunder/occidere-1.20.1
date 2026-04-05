package net.chemthunder.occidere.impl.item.weapon;

import net.chemthunder.occidere.api.extendable.WeaponItem;
import net.chemthunder.occidere.api.interfaces.IgnoredByRegisterLangItem;
import net.chemthunder.occidere.api.interfaces.SimpleModelItem;
import net.minecraft.item.ItemStack;

public class HostessBellItem extends WeaponItem implements SimpleModelItem, IgnoredByRegisterLangItem {
    public HostessBellItem(Settings settings) {
        super(settings, 8.0f, -2.7f, false);
    }

    public int getNameColor(ItemStack stack) {
        return 0xFF903ec5;
    }

    public String getItemId() {
        return "hostess_bell";
    }

    public String handheldId() {
        return "handheld";
    }
}
