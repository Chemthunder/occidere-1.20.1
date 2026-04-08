package net.chemthunder.occidere.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.chemthunder.occidere.impl.Occidere;
import net.chemthunder.occidere.impl.cca.world.FatesDominionWorldEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {

    @Unique private final Identifier FATES_DOMINION_SUN = Occidere.id("textures/environment/fates_dominion.png");

    @WrapOperation(
            method = "renderSky(Lnet/minecraft/client/util/math/MatrixStack;Lorg/joml/Matrix4f;FLnet/minecraft/client/render/Camera;ZLjava/lang/Runnable;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderTexture(ILnet/minecraft/util/Identifier;)V",
                    ordinal = 0
            )
    )
    private void woah(int texture, Identifier id, Operation<Void> original) {
        World world = MinecraftClient.getInstance().world;

        if (world != null) {
            FatesDominionWorldEvent event = FatesDominionWorldEvent.KEY.get(world);

            if (event.getActive()) {
                original.call(texture, FATES_DOMINION_SUN);
            } else {
                original.call(texture, id);
            }
        }
    }

    @Inject(method = "renderStars()V", at = @At("HEAD"), cancellable = true)
    private void occidere$disableStars(CallbackInfo ci) {
        World world = MinecraftClient.getInstance().world;

        if (world != null) {
            FatesDominionWorldEvent event = FatesDominionWorldEvent.KEY.get(world);

            if (event.getActive()) {
                ci.cancel();
            }
        }
    }
}
