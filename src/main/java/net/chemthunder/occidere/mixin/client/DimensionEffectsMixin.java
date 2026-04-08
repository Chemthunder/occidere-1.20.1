package net.chemthunder.occidere.mixin.client;

import net.chemthunder.occidere.impl.cca.world.FatesDominionWorldEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DimensionEffects.Overworld.class)
public abstract class DimensionEffectsMixin {

    @Inject(method = "adjustFogColor", at = @At("HEAD"), cancellable = true)
    private void occidere$customFogColor(Vec3d color, float sunHeight, CallbackInfoReturnable<Vec3d> cir) {
        ClientWorld world = MinecraftClient.getInstance().world;

        if (world != null && FatesDominionWorldEvent.KEY.get(world).getActive()) {
            cir.setReturnValue(Vec3d.ZERO);
        }
    }
}
