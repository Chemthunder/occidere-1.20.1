package net.chemthunder.occidere.impl.index;

import net.chemthunder.occidere.api.IgnoredByRegisterLangItem;
import net.chemthunder.occidere.impl.Occidere;
import net.chemthunder.occidere.impl.item.AuratusItem;
import net.chemthunder.occidere.impl.item.AurumItem;
import net.chemthunder.occidere.impl.item.FateweaverItem;
import net.chemthunder.occidere.impl.item.FlayedLustItem;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public interface OccidereItems {
    Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

    //Item SEVERED_SILENCE = create("severed_silence", new SeveredSilenceItem(SteelToolMaterials.DIVINE, 5, -2.6f, new Item.Settings().maxCount(1)));

    Item AURUM = create("aurum", new AurumItem(new Item.Settings()));
    Item FATEWEAVER = create("fateweaver", new FateweaverItem(new Item.Settings().maxCount(1)));
    Item AURATUS = create("auratus", new AuratusItem(new Item.Settings().maxCount(1)));
    Item FLAYED_LUST = create("flayed_lust", new FlayedLustItem(new Item.Settings().maxCount(1)));

    static <T extends Item> T create(String name, T item) {
        ITEMS.put(item, Occidere.id(name));
        return item;
    }

    static void init() {
        ITEMS.forEach((item, id) -> Registry.register(Registries.ITEM, id, item));
    }

    static void registerLang(FabricLanguageProvider.TranslationBuilder builder) {
        for (Item value : ITEMS.keySet()) {
            if (!(value instanceof IgnoredByRegisterLangItem)) {
                String name1 = value.toString();
                String first = String.valueOf(name1.charAt(0));

                String name2 = name1.replaceAll("_", " ").replaceFirst(first, first.toUpperCase());
                builder.add(value, name2);
            }
        }
        //TODO: make a way for names longer than one word to be registered easily
    }
}
