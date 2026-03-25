package net.chemthunder.occidere.impl.cca.item;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponent;
import net.chemthunder.occidere.impl.Occidere;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class PactComponent extends ItemComponent {
    public static final ComponentKey<PactComponent> KEY = ComponentRegistry.getOrCreate(Occidere.id("pact"), PactComponent.class);

    private static final String IS_SIGNED = "is_signed";
    private static final String SIGNER = "signer";
    private static final String SIGNER_NAME = "signer_name";

    public PactComponent(ItemStack stack) {
        super(stack);
    }

    public boolean getSigned() {
        return this.getBoolean(IS_SIGNED);
    }

    public void setIsSigned(boolean bl) {
        this.putBoolean(IS_SIGNED, bl);
    }

    public UUID getSigner() {
        return this.getUuid(SIGNER);
    }

    public void setSigner(UUID uuid) {
        this.putUuid(SIGNER, uuid);
    }

    public String getSignerName() {
        return this.getString(SIGNER_NAME);
    }

    public void setSignerName(String str) {
        this.putString(SIGNER_NAME, str);
    }
}