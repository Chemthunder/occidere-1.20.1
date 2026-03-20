package net.chemthunder.occidere.impl.cca;

import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.chemthunder.occidere.impl.cca.entity.AccursedComponent;
import net.minecraft.entity.player.PlayerEntity;

public class OccidereComponents implements EntityComponentInitializer {


    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.beginRegistration(PlayerEntity.class, AccursedComponent.KEY).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(AccursedComponent::new);
    }
}
