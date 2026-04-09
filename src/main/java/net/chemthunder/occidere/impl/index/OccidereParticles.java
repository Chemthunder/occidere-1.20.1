package net.chemthunder.occidere.impl.index;

import net.chemthunder.occidere.impl.Occidere;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.SweepAttackParticle;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public interface OccidereParticles {
    Map<ParticleType<?>, Identifier> PARTICLES = new LinkedHashMap<>();

    DefaultParticleType BLOOD_SWEEP = create("blood_sweep", FabricParticleTypes.simple());
    DefaultParticleType GILDED_SWEEP = create("gilded_sweep", FabricParticleTypes.simple());

    private static <T extends ParticleType<?>> T create(String name, T particle) {
        PARTICLES.put(particle, Occidere.id(name));
        return particle;
    }

    static void init() {
        PARTICLES.keySet().forEach(particle -> Registry.register(Registries.PARTICLE_TYPE, PARTICLES.get(particle), particle));
    }

    static void clientInit() {
        ParticleFactoryRegistry.getInstance().register(BLOOD_SWEEP, SweepAttackParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(GILDED_SWEEP, SweepAttackParticle.Factory::new);
    }
}
