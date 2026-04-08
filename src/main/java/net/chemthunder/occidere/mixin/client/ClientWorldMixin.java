package net.chemthunder.occidere.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.chemthunder.occidere.impl.cca.world.FatesDominionWorldEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ClientWorld.class)
public abstract class ClientWorldMixin {

    @ModifyReturnValue(method = "getSkyColor", at = @At("RETURN"))
    private Vec3d occidere$customSkyColor(Vec3d original) {
        World world = MinecraftClient.getInstance().world;

        if (world != null) {
            FatesDominionWorldEvent event = FatesDominionWorldEvent.KEY.get(world);

            if (event.getActive()) {
                return Vec3d.ZERO;
            } else {
                return original;
            }
        }
        return original;
    }
}