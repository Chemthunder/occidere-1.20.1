package net.chemthunder.occidere.impl.index;

import net.chemthunder.occidere.impl.Occidere;
import net.chemthunder.occidere.impl.client.render.entity.AurumEntityRenderer;
import net.chemthunder.occidere.impl.client.render.entity.BoneShardEntityRenderer;
import net.chemthunder.occidere.impl.client.render.entity.WovenAdmirationEntityRenderer;
import net.chemthunder.occidere.impl.entity.AurumEntity;
import net.chemthunder.occidere.impl.entity.BoneShardEntity;
import net.chemthunder.occidere.impl.entity.VulkanShotEntity;
import net.chemthunder.occidere.impl.entity.WovenAdmirationEntity;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.EmptyEntityRenderer;
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
                    SpawnGroup.MISC,
                    WovenAdmirationEntity::new
            ).disableSaving().disableSummon().dimensions(EntityDimensions.changing(1.3f, 0.3f)
            ).build());

    EntityType<AurumEntity> AURUM = createEntity(
            "aurum",
            FabricEntityTypeBuilder.create(
                    SpawnGroup.MISC,
                    AurumEntity::new
            ).disableSaving().dimensions(EntityDimensions.changing(0.4f, 1.3f)
            ).build());

    EntityType<BoneShardEntity> BONE_SHARD = createEntity(
            "bone_shard",
            FabricEntityTypeBuilder.create(
                    SpawnGroup.MISC,
                    BoneShardEntity::new
            ).disableSaving().disableSummon().dimensions(EntityDimensions.changing(0.5f, 0.5f)
            ).build());

    EntityType<VulkanShotEntity> VULKAN_SHOT = createEntity(
            "vulkan_shot",
            FabricEntityTypeBuilder.create(
                    SpawnGroup.MISC,
                    VulkanShotEntity::new
            ).disableSaving().dimensions(EntityDimensions.changing(0.5f, 0.5f)
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
        EntityRendererRegistry.register(AURUM, AurumEntityRenderer::new);
        EntityRendererRegistry.register(BONE_SHARD, BoneShardEntityRenderer::new);
        EntityRendererRegistry.register(VULKAN_SHOT, EmptyEntityRenderer::new);
    }
}