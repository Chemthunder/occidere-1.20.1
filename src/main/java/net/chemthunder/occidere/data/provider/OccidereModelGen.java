package net.chemthunder.occidere.data.provider;

import net.chemthunder.occidere.api.interfaces.model.SimpleModelItem;
import net.chemthunder.occidere.impl.index.OccidereItems;
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

        itemModelGenerator.register(OccidereItems.AURATUS, Models.GENERATED);

        itemModelGenerator.register(OccidereItems.NYRULNA_VAIN, Models.GENERATED);

        itemModelGenerator.register(OccidereItems.FLAYED_LUST, Models.GENERATED);

        itemModelGenerator.register(OccidereItems.PACT, Models.GENERATED);
        itemModelGenerator.register(OccidereItems.PACT, "_signed", Models.GENERATED);
    }
}