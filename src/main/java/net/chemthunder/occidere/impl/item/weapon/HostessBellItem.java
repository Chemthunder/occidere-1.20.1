package net.chemthunder.occidere.impl.item.weapon;

import net.chemthunder.legere.api.v1.extendable.item.WeaponItem;
import net.chemthunder.legere.api.v1.interfaces.SimpleModelItem;
import net.chemthunder.occidere.api.OccidereItemStatus;
import net.chemthunder.occidere.api.interfaces.IgnoredByRegisterLangItem;
import net.chemthunder.occidere.api.interfaces.ItemStatus;
import net.minecraft.item.ItemStack;

public class HostessBellItem extends WeaponItem implements SimpleModelItem, IgnoredByRegisterLangItem, ItemStatus {
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

    public OccidereItemStatus getStatus() {
        return OccidereItemStatus.UNFINISHED;
    }
}