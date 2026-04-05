package net.chemthunder.occidere.impl.client;

import net.chemthunder.occidere.impl.client.render.event.VulkanAmmoShowcaseEvent;
import net.chemthunder.occidere.impl.index.OccidereEntities;
import net.chemthunder.occidere.impl.index.OccidereEntityModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class OccidereClient implements ClientModInitializer {
    public void onInitializeClient() {
        OccidereEntityModelLayers.clientInit();
        OccidereEntities.clientInit();

        HudRenderCallback.EVENT.register(new VulkanAmmoShowcaseEvent());
    }
}