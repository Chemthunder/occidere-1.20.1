package net.chemthunder.occidere.data.provider;

import net.chemthunder.occidere.impl.index.OccidereItemGroups;
import net.chemthunder.occidere.impl.index.OccidereItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class OccidereLangGen extends FabricLanguageProvider {
    public OccidereLangGen(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    public void generateTranslations(TranslationBuilder translationBuilder) {
        OccidereItems.registerLang(translationBuilder);
        OccidereItemGroups.registerLang(translationBuilder);

        translationBuilder.add(OccidereItems.FLAYED_LUST, "Flayed Lust");
        translationBuilder.add(OccidereItems.NYRULNA_VAIN, "Vain of Nyrulna");
    }
}
