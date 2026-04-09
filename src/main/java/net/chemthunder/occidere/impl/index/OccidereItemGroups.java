package net.chemthunder.occidere.impl.index;

import net.chemthunder.occidere.impl.Occidere;
import net.chemthunder.occidere.impl.cca.item.AuratusHereticItemComponent;
import net.chemthunder.occidere.impl.item.AuratusHereticItem;
import net.chemthunder.occidere.impl.util.AuratusHereticState;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public interface OccidereItemGroups {
    Map<ItemGroup, RegistryKey<ItemGroup>> GROUPS = new HashMap<>();
    List<String> GROUP_NAMES = new ArrayList<>();

    ItemGroup MAIN = create(new ItemStack(OccidereItems.AURUM), "occidere", 0xFFa54d45);

    static void init() {
        ItemGroupEvents.modifyEntriesEvent(GROUPS.get(MAIN)).register(OccidereItemGroups::addEntries);

        GROUPS.forEach(((itemGroup, itemGroupRegistryKey) -> Registry.register(Registries.ITEM_GROUP, itemGroupRegistryKey, itemGroup)));
    }

    private static ItemGroup create(ItemStack icon, String name, int nameColor) {
        RegistryKey<ItemGroup> CREATED = RegistryKey.of(RegistryKeys.ITEM_GROUP, Occidere.id(name.toLowerCase()));
        ItemGroup CREATED_GROUP = FabricItemGroup.builder()
                .icon(() -> icon)
                .displayName(Text.translatable("itemGroup." + name.toLowerCase()).styled(style -> style.withColor(nameColor)))
                .build();

        GROUPS.put(CREATED_GROUP, CREATED);
        GROUP_NAMES.add(name);
        return CREATED_GROUP;
    }

    private static void addEntries(FabricItemGroupEntries itemGroup) {
        for (Item value : OccidereItems.ITEMS.keySet()) {
            itemGroup.add(value);
        }

        ItemStack TAINTED_AURATUS = new ItemStack(OccidereItems.AURATUS_HERETIC);
        AuratusHereticItemComponent.KEY.get(TAINTED_AURATUS).setMode(AuratusHereticState.TAINTED);

        itemGroup.addAfter(OccidereItems.AURATUS_HERETIC, TAINTED_AURATUS);
    }

    static void registerLang(FabricLanguageProvider.TranslationBuilder builder) {
        for (String group : GROUP_NAMES) {
            String name = group.replaceFirst(String.valueOf(group.charAt(0)), String.valueOf(group.charAt(0)).toUpperCase());
            builder.add("itemGroup." + group, name);

            Occidere.LOGGER.info(name);
        }
    }
}