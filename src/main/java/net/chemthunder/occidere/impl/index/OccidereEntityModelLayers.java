package net.chemthunder.occidere.impl.index;

import net.chemthunder.occidere.impl.Occidere;
import net.chemthunder.occidere.impl.client.render.model.WovenAdmirationModel;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;

import java.util.LinkedHashMap;
import java.util.Map;

public interface OccidereEntityModelLayers {
    Map<EntityModelLayer, EntityModelLayerRegistry.TexturedModelDataProvider> MODEL_LAYERS = new LinkedHashMap<>();

    EntityModelLayer WOVEN_ADMIRATION = createModelLayer("woven_admiration", WovenAdmirationModel::getTexturedModelData);

    private static EntityModelLayer createModelLayer(String name, EntityModelLayerRegistry.TexturedModelDataProvider provider) {
        EntityModelLayer entityModelLayer = createMain(name);
        MODEL_LAYERS.put(entityModelLayer, provider);
        return entityModelLayer;
    }

    private static EntityModelLayer createMain(String id) {
        return create(id, "main");
    }

    private static EntityModelLayer create(String id, String layer) {
        return new EntityModelLayer(Occidere.id(id), layer);
    }

    static void clientInit() {
        MODEL_LAYERS.forEach(EntityModelLayerRegistry::registerModelLayer);
    }
}