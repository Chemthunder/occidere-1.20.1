package net.chemthunder.occidere.impl.cca.item;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponent;
import net.chemthunder.occidere.impl.Occidere;
import net.chemthunder.occidere.impl.util.AuratusHereticState;
import net.minecraft.item.ItemStack;

public class AuratusHereticItemComponent extends ItemComponent {
    public static final ComponentKey<AuratusHereticItemComponent> KEY = ComponentRegistry.getOrCreate(Occidere.id("auratus_heretic_item"), AuratusHereticItemComponent.class);

    private static final String MODE = "heretic_mode";

    public AuratusHereticItemComponent(ItemStack stack) {
        super(stack);
    }

    public int getModeAsInt() {
        return this.getInt(MODE);
    }

    public void setMode(AuratusHereticState state) {
        this.putInt(MODE, state.placement);
    }

    public AuratusHereticState getMode() {
        if (this.getModeAsInt() == AuratusHereticState.GILDED.placement) {
            return AuratusHereticState.GILDED;
        }

        if (this.getModeAsInt() == AuratusHereticState.TAINTED.placement) {
            return AuratusHereticState.TAINTED;
        }
        return null;
    }

}
