package net.chemthunder.occidere.mixin.client;

import net.chemthunder.occidere.impl.Occidere;
import net.chemthunder.occidere.impl.cca.entity.ThreadbreakerComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {

    @Shadow protected abstract void renderOverlay(DrawContext context, Identifier texture, float opacity);

    @Unique private final Identifier THREADBROKEN = Occidere.id("textures/gui/threadbreak.png");

    @Inject(method = "render", at = @At("HEAD"))
    private void occidere$statusEffectBg(DrawContext context, float tickDelta, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player != null && ThreadbreakerComponent.KEY.get(client.player).getTetheredTicks() > 0) {
            this.renderOverlay(context, THREADBROKEN, 1);
        }
    }
}
