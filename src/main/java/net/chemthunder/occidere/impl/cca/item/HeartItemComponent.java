package net.chemthunder.occidere.impl.cca.item;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponent;
import net.chemthunder.occidere.impl.Occidere;
import net.minecraft.item.ItemStack;

public class HeartItemComponent extends ItemComponent {
    public static final ComponentKey<HeartItemComponent> KEY = ComponentRegistry.getOrCreate(Occidere.id("heart_item"), HeartItemComponent.class);

    private static final String CAPTURED_BONES = "captured_bones";

    public HeartItemComponent(ItemStack stack) {
        super(stack);
    }

    public int getCapturedBones() {
        return this.getInt(CAPTURED_BONES);
    }

    public void setCapturedBones(int i) {
        this.putInt(CAPTURED_BONES, i);
    }

    public void addCapturedBones() {
        this.putInt(CAPTURED_BONES, this.getCapturedBones() + 1);
    }
}
