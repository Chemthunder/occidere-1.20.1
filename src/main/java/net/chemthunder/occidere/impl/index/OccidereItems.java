package net.chemthunder.occidere.impl.index;

import net.chemthunder.occidere.api.interfaces.IgnoredByRegisterLangItem;
import net.chemthunder.occidere.impl.Occidere;
import net.chemthunder.occidere.impl.item.*;
import net.chemthunder.occidere.impl.util.AuratusHereticState;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("unused")
public interface OccidereItems {
    Map<Item, Identifier> ITEMS = new LinkedHashMap<>();

    Item AURUM = create("aurum", new AurumItem(new Item.Settings()));
    Item FLAYED_LUST = create("flayed_lust", new FlayedLustItem(new Item.Settings()));
    Item NYRULNA_VAIN = create("nyrulna_vain", new NyrulnaVainItem(new Item.Settings()));
    Item BEATING_HEART = create("beating_heart", new BeatingHeartItem(new Item.Settings()));
    Item THREADBREAKER = create("threadbreaker", new ThreadbreakerItem(new Item.Settings()));
    Item HOSTESS_BELL = create("hostess_bell", new HostessBellItem(new Item.Settings()));
    Item INTERTWINED_GLAIVE = create("intertwined_glaive", new IntertwinedGlaiveItem(new Item.Settings()));

    Item FATEWEAVER = create("fateweaver", new FateweaverItem(new Item.Settings().maxCount(1)));
    Item PACT = create("pact", new PactItem(new Item.Settings().maxCount(1)));
    Item RIFT_CRACKER = create("rift_cracker", new RiftCrackerItem(new Item.Settings().maxCount(1)));
    Item VULKAN = create("vulkan", new VulkanItem(new Item.Settings().maxCount(1)));

    Item AURATUS_HERETIC = create("auratus_heretic", new AuratusHereticItem(new Item.Settings()));
    Item AURATUS = create("auratus", new AHGemItem(new Item.Settings().maxCount(1), AuratusHereticState.GILDED));
    Item HERETIC = create("heretic", new AHGemItem(new Item.Settings().maxCount(1), AuratusHereticState.TAINTED));

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