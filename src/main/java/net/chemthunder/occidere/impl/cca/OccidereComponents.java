package net.chemthunder.occidere.impl.cca;

import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer;
import net.chemthunder.occidere.impl.cca.entity.*;
import net.chemthunder.occidere.impl.cca.item.*;
import net.chemthunder.occidere.impl.cca.ivoryevent.AuratusStunlockComponent;
import net.chemthunder.occidere.impl.cca.world.FatesDominionWorldEvent;
import net.chemthunder.occidere.impl.item.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class OccidereComponents implements EntityComponentInitializer, ItemComponentInitializer, WorldComponentInitializer {
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(PlayerEntity.class, AccursedComponent.KEY).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(AccursedComponent::new);
        registry.beginRegistration(PlayerEntity.class, VainComponent.KEY).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(VainComponent::new);
        registry.beginRegistration(PlayerEntity.class, HeartComponent.KEY).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(HeartComponent::new);
        registry.beginRegistration(PlayerEntity.class, HostessComponent.KEY).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(HostessComponent::new);

        registry.beginRegistration(LivingEntity.class, ThreadbreakerComponent.KEY).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(ThreadbreakerComponent::new);
        registry.beginRegistration(LivingEntity.class, AuratusStunlockComponent.KEY).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(AuratusStunlockComponent::new);
    }

    public void registerItemComponentFactories(ItemComponentFactoryRegistry registry) {
        registry.register(item -> item instanceof PactItem, PactItemComponent.KEY, PactItemComponent::new);
        registry.register(item -> item instanceof BeatingHeartItem, HeartItemComponent.KEY, HeartItemComponent::new);
        registry.register(item -> item instanceof RiftCrackerItem, RiftCrackerItemComponent.KEY, RiftCrackerItemComponent::new);
        registry.register(item -> item instanceof VulkanItem, VulkanItemComponent.KEY, VulkanItemComponent::new);
        registry.register(item -> item instanceof AuratusHereticItem, AuratusHereticItemComponent.KEY, AuratusHereticItemComponent::new);
    }

    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
        registry.register(FatesDominionWorldEvent.KEY, FatesDominionWorldEvent::new);
    }
}