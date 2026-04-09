package net.chemthunder.occidere.data.provider.asset;

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

        // item
        translationBuilder.add(OccidereItems.FLAYED_LUST, "Flayed Lust");
        translationBuilder.add(OccidereItems.NYRULNA_VAIN, "Vain of Nyrulna");
        translationBuilder.add(OccidereItems.BEATING_HEART, "Beating Heart");
        translationBuilder.add(OccidereItems.RIFT_CRACKER, "Rift Cracker");
        translationBuilder.add(OccidereItems.HOSTESS_BELL, "Hostess' Bell");
        translationBuilder.add(OccidereItems.INTERTWINED_GLAIVE, "Intertwined Glaive");
        translationBuilder.add(OccidereItems.AURATUS_HERETIC, "Auratus Heretic");

        // item misc
        translationBuilder.add("text.occidere.vain_ready", "<Vain of Nyrulna charged.>");

        translationBuilder.add("item.pact.signed", "Signed by ");
        translationBuilder.add("item.pact.unsigned", "Unsigned.");

        translationBuilder.add("item.occidere.signed_pact", "Signed Pact");

        // dmg
        translationBuilder.add("death.attack.bone_shard", "%1$s was speared by a bone shard");
        translationBuilder.add("death.attack.bone_shard.player", "%1$s was speared by %2$s's bone shard");

        translationBuilder.add("death.attack.pact", "%1$s couldn't survive the stakes");
        translationBuilder.add("death.attack.pact.player", "%1$s couldn't survive the stakes whilst fighting %2$s");

        translationBuilder.add("death.attack.aurum", "%1$s was covered in gold");
        translationBuilder.add("death.attack.aurum.player", "%1$s was covered in gold by %2$s");

        translationBuilder.add("death.attack.vain_impact", "%1$s was disgraced");
        translationBuilder.add("death.attack.vain_impact.player", "%1$s was disgraced by %2$s");

        translationBuilder.add("death.attack.gunshot", "%1$s was shot down");
        translationBuilder.add("death.attack.gunshot.player", "%1$s was shot down by %2$s");

        translationBuilder.add("death.attack.fateweaver", "%1$s was disfigured by God");
        translationBuilder.add("death.attack.fateweaver.player", "%1$s was disfigured by God");
    }
}