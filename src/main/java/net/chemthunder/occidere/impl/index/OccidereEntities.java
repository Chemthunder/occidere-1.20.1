package net.chemthunder.occidere.impl.index;

import net.chemthunder.occidere.impl.Occidere;
import net.chemthunder.occidere.impl.client.render.entity.WovenAdmirationEntityRenderer;
import net.chemthunder.occidere.impl.client.render.entity.WovenAdmirationModel;
import net.chemthunder.occidere.impl.entity.WovenAdmirationEntity;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public interface OccidereEntities {
    Map<EntityType<? extends Entity>, Identifier> ENTITIES = new LinkedHashMap<>();

    EntityType<WovenAdmirationEntity> WOVEN_ADMIRATION = createEntity(
            "woven_admiration",
            FabricEntityTypeBuilder.create(
                    SpawnGroup.MISC
                    , WovenAdmirationEntity::new
            ).disableSaving().disableSummon().dimensions(EntityDimensions.changing(1.3f, 0.3f)
            ).build());

    private static <T extends EntityType<? extends Entity>> T createEntity(String name, T entity) {
        ENTITIES.put(entity, Occidere.id(name));
        return entity;
    }

    static void init() {
        ENTITIES.keySet().forEach(entityType -> Registry.register(Registries.ENTITY_TYPE, ENTITIES.get(entityType), entityType));
    }

    static void clientInit() {
        EntityRendererRegistry.register(WOVEN_ADMIRATION, WovenAdmirationEntityRenderer::new);
    }
}
