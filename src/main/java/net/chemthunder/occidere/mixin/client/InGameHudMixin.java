package net.chemthunder.occidere.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.chemthunder.occidere.impl.Occidere;
import net.chemthunder.occidere.impl.cca.entity.ThreadbreakerComponent;
import net.chemthunder.occidere.impl.item.VulkanItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Shadow
    protected abstract void renderOverlay(DrawContext context, Identifier texture, float opacity);

    @Unique
    private final Identifier THREADBROKEN = Occidere.id("textures/gui/threadbreak.png");
    @Unique
    private final Identifier VULKAN_CROSSHAIR = Occidere.id("hud/vulkan_icons.png");

    @Inject(method = "renderCrosshair", at = @At(value = "HEAD"), cancellable = true)
    private void occidere$removeCrosshair(DrawContext context, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;

        if (player != null) {
            if (player.getStackInHand(player.getActiveHand()).getItem() instanceof VulkanItem) {
                if (player.isSneaking()) ci.cancel();
            }
        }
    }
}
