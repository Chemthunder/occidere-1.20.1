package net.chemthunder.occidere.data;

import net.chemthunder.occidere.data.provider.asset.OccidereLangGen;
import net.chemthunder.occidere.data.provider.asset.OccidereModelGen;
import net.chemthunder.occidere.data.provider.data.OccidereDamageSourceTagGen;
import net.chemthunder.occidere.data.provider.data.OccidereDynamicRegistryGen;
import net.chemthunder.occidere.data.provider.data.OccidereItemTagGen;
import net.chemthunder.occidere.impl.index.OccidereDamageSources;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class OccidereDataGen implements DataGeneratorEntrypoint {
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(OccidereLangGen::new);
        pack.addProvider(OccidereModelGen::new);
        pack.addProvider(OccidereItemTagGen::new);
        pack.addProvider(OccidereDamageSourceTagGen::new);
        pack.addProvider(OccidereDynamicRegistryGen::new);
    }

    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.DAMAGE_TYPE, OccidereDamageSources::bootstrap);
    }
}