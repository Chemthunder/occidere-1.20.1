package net.chemthunder.occidere.impl.index;

import net.chemthunder.occidere.impl.Occidere;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.HashMap;
import java.util.Map;

public interface OccidereDamageSources {
    Map<RegistryKey<DamageType>, String> SOURCE_MAP = new HashMap<>();

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

    RegistryKey<DamageType> FATEWEAVER = of("fateweaver");
    static DamageSource fateweaver(Entity entity) {
        return entity.getDamageSources().create(FATEWEAVER);
    }

    RegistryKey<DamageType> GUNSHOT = of("gunshot");
    static DamageSource gunshot(Entity entity) {
        return entity.getDamageSources().create(GUNSHOT);
    }

    private static RegistryKey<DamageType> of(String name) {
        RegistryKey<DamageType> key = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Occidere.id(name));
        SOURCE_MAP.put(key, name);
        return key;
    }

    static void bootstrap(Registerable<DamageType> registerable) {
        SOURCE_MAP.forEach((damageTypeRegistryKey, s) -> registerable.register(damageTypeRegistryKey, new DamageType(s, 0.0f)));
    }
}