package net.chemthunder.occidere.data;

import net.chemthunder.occidere.data.provider.OccidereDamageSourceTagGen;
import net.chemthunder.occidere.data.provider.OccidereItemTagGen;
import net.chemthunder.occidere.data.provider.OccidereLangGen;
import net.chemthunder.occidere.data.provider.OccidereModelGen;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class OccidereDataGen implements DataGeneratorEntrypoint {
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(OccidereLangGen::new);
        pack.addProvider(OccidereModelGen::new);
        pack.addProvider(OccidereItemTagGen::new);
        pack.addProvider(OccidereDamageSourceTagGen::new);
	}
}
