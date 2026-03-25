package net.chemthunder.occidere.impl.client;

import net.chemthunder.occidere.impl.index.OccidereEntities;
import net.chemthunder.occidere.impl.index.OccidereEntityModelLayers;
import net.fabricmc.api.ClientModInitializer;

public class OccidereClient implements ClientModInitializer {
    public void onInitializeClient() {
        OccidereEntityModelLayers.clientInit();
        OccidereEntities.clientInit();
    }
}