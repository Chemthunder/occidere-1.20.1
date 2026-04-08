package net.chemthunder.occidere.data.provider.data;

import net.chemthunder.occidere.impl.index.OccidereDamageSources;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.DamageTypeTags;

import java.util.concurrent.CompletableFuture;

public class OccidereDamageSourceTagGen extends FabricTagProvider<DamageType> {
    public OccidereDamageSourceTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.DAMAGE_TYPE, registriesFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        this.getOrCreateTagBuilder(DamageTypeTags.BYPASSES_COOLDOWN)
                .addOptional(OccidereDamageSources.BONE_SHARD);

        this.getOrCreateTagBuilder(DamageTypeTags.BYPASSES_INVULNERABILITY)
                .addOptional(OccidereDamageSources.FATEWEAVER);
        this.getOrCreateTagBuilder(DamageTypeTags.BYPASSES_ARMOR)
                .addOptional(OccidereDamageSources.FATEWEAVER)
                .addOptional(OccidereDamageSources.BONE_SHARD);
        this.getOrCreateTagBuilder(DamageTypeTags.BYPASSES_EFFECTS)
                .addOptional(OccidereDamageSources.FATEWEAVER);
    }
}