package net.chemthunder.occidere.impl.index;

import net.chemthunder.occidere.impl.Occidere;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public interface OccidereDamageSources {
    RegistryKey<DamageType> BONE_SHARD = of("bone_shard");
    static DamageSource boneShard(Entity entity) {
        return entity.getDamageSources().create(BONE_SHARD);
    }

    private static RegistryKey<DamageType> of(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Occidere.id(name));
    }
}
