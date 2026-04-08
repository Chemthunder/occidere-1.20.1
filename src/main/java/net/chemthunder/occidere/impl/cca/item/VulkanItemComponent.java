package net.chemthunder.occidere.impl.cca.item;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponent;
import net.chemthunder.occidere.impl.Occidere;
import net.minecraft.item.ItemStack;

public class VulkanItemComponent extends ItemComponent {
    public static final ComponentKey<VulkanItemComponent> KEY = ComponentRegistry.getOrCreate(Occidere.id("vulkan"), VulkanItemComponent.class);

    public VulkanItemComponent(ItemStack stack) {
        super(stack);
    }

    private static final String REMAINING_AMMUNITION = "remaining_ammunition";

    public int getRemainingAmmo() {
        return this.getInt(REMAINING_AMMUNITION);
    }

    public void setAmmo(int integer) {
        this.putInt(REMAINING_AMMUNITION, integer);
    }

    public void addAmmo() {
        this.putInt(REMAINING_AMMUNITION, this.getRemainingAmmo() + 1);
    }

    public void subtractAmmo() {
        this.putInt(REMAINING_AMMUNITION, this.getRemainingAmmo() - 1);
    }
}