package net.chemthunder.occidere.data.provider;

import net.chemthunder.occidere.impl.Occidere;
import net.chemthunder.occidere.impl.index.OccidereItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

public class OccidereItemTagGen extends FabricTagProvider.ItemTagProvider {
    public OccidereItemTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    TagKey<Item> ALL_ITEMS = TagKey.of(RegistryKeys.ITEM, Occidere.id("all_items"));

    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        for (Item val : OccidereItems.ITEMS.keySet()) {
            this.getOrCreateTagBuilder(ALL_ITEMS)
                    .add(val)
                    .setReplace(true);
        }
    }
}