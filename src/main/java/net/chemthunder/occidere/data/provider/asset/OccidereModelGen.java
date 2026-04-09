package net.chemthunder.occidere.data.provider.asset;

import net.chemthunder.legere.api.v1.interfaces.SimpleModelItem;
import net.chemthunder.occidere.impl.index.OccidereItems;
import net.chemthunder.occidere.impl.index.OccidereModels;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;

public class OccidereModelGen extends FabricModelProvider {
    public OccidereModelGen(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {}

    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        for (Item value : OccidereItems.ITEMS.keySet()) {
            if (value instanceof SimpleModelItem) {
                itemModelGenerator.register(value, Models.GENERATED);
            }
        }

        itemModelGenerator.register(OccidereItems.NYRULNA_VAIN, Models.GENERATED);
        itemModelGenerator.register(OccidereItems.VULKAN, Models.GENERATED);

        itemModelGenerator.register(OccidereItems.FLAYED_LUST, Models.GENERATED);

        itemModelGenerator.register(OccidereItems.PACT, Models.GENERATED);
        itemModelGenerator.register(OccidereItems.PACT, "_signed", Models.GENERATED);

        itemModelGenerator.register(OccidereItems.INTERTWINED_GLAIVE, Models.GENERATED);

        itemModelGenerator.register(OccidereItems.AURATUS, OccidereModels.GEM);
        itemModelGenerator.register(OccidereItems.HERETIC, OccidereModels.GEM);

        itemModelGenerator.register(OccidereItems.AURATUS_HERETIC, Models.GENERATED);
        itemModelGenerator.register(OccidereItems.AURATUS_HERETIC, "_tainted", Models.GENERATED);
        itemModelGenerator.register(OccidereItems.AURATUS_HERETIC, "_gilded_handheld", OccidereModels.HERETIC_HANDHELD);
        itemModelGenerator.register(OccidereItems.AURATUS_HERETIC, "_tainted_handheld", OccidereModels.HERETIC_HANDHELD);
    }
}