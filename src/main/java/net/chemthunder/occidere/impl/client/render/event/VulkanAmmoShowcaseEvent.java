package net.chemthunder.occidere.impl.client.render.event;

import net.chemthunder.occidere.impl.cca.item.VulkanItemComponent;
import net.chemthunder.occidere.impl.index.OccidereItems;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class VulkanAmmoShowcaseEvent implements HudRenderCallback {
    public void onHudRender(DrawContext drawContext, float v) {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;

        if (player != null) {
            if (player.getMainHandStack().isOf(OccidereItems.VULKAN) || player.getOffHandStack().isOf(OccidereItems.VULKAN)) {
                VulkanItemComponent component = null;

                if (player.getMainHandStack().isOf(OccidereItems.VULKAN)) {
                    component = VulkanItemComponent.KEY.get(player.getMainHandStack());
                }

                if (player.getOffHandStack().isOf(OccidereItems.VULKAN)) {
                    component = VulkanItemComponent.KEY.get(player.getOffHandStack());
                }

                if (component != null) {
                    if (!player.isSneaking()) {
                        drawContext.drawTextWithShadow(client.textRenderer,
                                Text.literal(component.getRemainingAmmo() + ""),
                                drawContext.getScaledWindowWidth() / 2 - 3,
                                drawContext.getScaledWindowHeight() / 2 + 15,
                                0xFFfcba03
                        );
                    } else {
                        drawContext.drawTextWithShadow(client.textRenderer,
                                Text.literal(component.getRemainingAmmo() + ""),
                                drawContext.getScaledWindowWidth() / 2 - 3,
                                drawContext.getScaledWindowHeight() / 2 - 2,
                                0xFFfcba03
                        );
                    }
                }
            }
        }
    }
}
