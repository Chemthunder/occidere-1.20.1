package net.chemthunder.occidere.impl.cca.item;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponent;
import net.chemthunder.occidere.impl.Occidere;
import net.minecraft.item.ItemStack;

public class RiftCrackerComponent extends ItemComponent {
    public static final ComponentKey<RiftCrackerComponent> KEY = ComponentRegistry.getOrCreate(Occidere.id("rift_cracker"), RiftCrackerComponent.class);

    private static final String WAYPOINT = "waypoint";

    public RiftCrackerComponent(ItemStack stack) {
        super(stack);
    }

    public double getX() {
        return this.getDouble(WAYPOINT + "_x");
    }

    public double getY() {
        return this.getDouble(WAYPOINT + "_y");
    }

    public double getZ() {
        return this.getDouble(WAYPOINT + "_z");
    }

    public void setX(double x) {
        this.putDouble(WAYPOINT + "_x", x);
    }

    public void setY(double y) {
        this.putDouble(WAYPOINT + "_y", y);
    }

    public void setZ(double z) {
        this.putDouble(WAYPOINT + "_z", z);
    }
}