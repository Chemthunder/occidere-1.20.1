package net.chemthunder.occidere.impl.index;

import net.chemthunder.occidere.impl.Occidere;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public interface OccidereDamageSources {
    RegistryKey<DamageType> BONE_SHARD = of("bone_shard");
    static DamageSource boneShard(Entity entity) {
        return entity.getDamageSources().create(BONE_SHARD);
    }

    RegistryKey<DamageType> PACT = of("pact");
    static DamageSource pact(Entity entity) {
        return entity.getDamageSources().create(PACT);
    }

    RegistryKey<DamageType> AURUM = of("aurum");
    static DamageSource aurum(Entity entity) {
        return entity.getDamageSources().create(AURUM);
    }

    RegistryKey<DamageType> VAIN_IMPACT = of("vain_impact");
    static DamageSource vainImpact(Entity entity) {
        return entity.getDamageSources().create(VAIN_IMPACT);
    }

    private static RegistryKey<DamageType> of(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Occidere.id(name));
    }
}