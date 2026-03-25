package net.chemthunder.occidere.impl.cca;

import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
import net.chemthunder.occidere.impl.cca.entity.AccursedComponent;
import net.chemthunder.occidere.impl.cca.entity.HeartComponent;
import net.chemthunder.occidere.impl.cca.entity.VainComponent;
import net.chemthunder.occidere.impl.cca.item.HeartItemComponent;
import net.chemthunder.occidere.impl.cca.item.PactComponent;
import net.chemthunder.occidere.impl.cca.item.RiftCrackerComponent;
import net.chemthunder.occidere.impl.item.PactItem;
import net.chemthunder.occidere.impl.item.RiftCrackerItem;
import net.chemthunder.occidere.impl.item.weapon.BeatingHeartItem;
import net.minecraft.entity.player.PlayerEntity;

public class OccidereComponents implements EntityComponentInitializer, ItemComponentInitializer {
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(PlayerEntity.class, AccursedComponent.KEY).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(AccursedComponent::new);
        registry.beginRegistration(PlayerEntity.class, VainComponent.KEY).respawnStrategy(RespawnCopyStrategy.NEVER_COPY).end(VainComponent::new);
        registry.beginRegistration(PlayerEntity.class, HeartComponent.KEY).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(HeartComponent::new);
    }

    public void registerItemComponentFactories(ItemComponentFactoryRegistry registry) {
        registry.register(item -> item instanceof PactItem, PactComponent.KEY, PactComponent::new);
        registry.register(item -> item instanceof BeatingHeartItem, HeartItemComponent.KEY, HeartItemComponent::new);
        registry.register(item -> item instanceof RiftCrackerItem, RiftCrackerComponent.KEY, RiftCrackerComponent::new);
    }
}