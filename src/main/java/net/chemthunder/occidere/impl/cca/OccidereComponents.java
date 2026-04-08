package net.chemthunder.occidere.impl.cca;

import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer;
import net.chemthunder.occidere.impl.cca.entity.*;
import net.chemthunder.occidere.impl.cca.item.HeartItemComponent;
import net.chemthunder.occidere.impl.cca.item.PactItemComponent;
import net.chemthunder.occidere.impl.cca.item.RiftCrackerItemComponent;
import net.chemthunder.occidere.impl.cca.item.VulkanItemComponent;
import net.chemthunder.occidere.impl.cca.world.FatesDominionWorldEvent;
import net.chemthunder.occidere.impl.item.PactItem;
import net.chemthunder.occidere.impl.item.RiftCrackerItem;
import net.chemthunder.occidere.impl.item.VulkanItem;
import net.chemthunder.occidere.impl.item.BeatingHeartItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class OccidereComponents implements EntityComponentInitializer, ItemComponentInitializer, WorldComponentInitializer {
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(PlayerEntity.class, AccursedComponent.KEY).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(AccursedComponent::new);
        registry.beginRegistration(PlayerEntity.class, VainComponent.KEY).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(VainComponent::new);
        registry.beginRegistration(PlayerEntity.class, HeartComponent.KEY).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(HeartComponent::new);
        registry.beginRegistration(PlayerEntity.class, HostessComponent.KEY).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(HostessComponent::new);

        registry.beginRegistration(LivingEntity.class, ThreadbreakerComponent.KEY).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(ThreadbreakerComponent::new);
    }

    public void registerItemComponentFactories(ItemComponentFactoryRegistry registry) {
        registry.register(item -> item instanceof PactItem, PactItemComponent.KEY, PactItemComponent::new);
        registry.register(item -> item instanceof BeatingHeartItem, HeartItemComponent.KEY, HeartItemComponent::new);
        registry.register(item -> item instanceof RiftCrackerItem, RiftCrackerItemComponent.KEY, RiftCrackerItemComponent::new);
        registry.register(item -> item instanceof VulkanItem, VulkanItemComponent.KEY, VulkanItemComponent::new);
    }

    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
        registry.register(FatesDominionWorldEvent.KEY, FatesDominionWorldEvent::new);
    }
}