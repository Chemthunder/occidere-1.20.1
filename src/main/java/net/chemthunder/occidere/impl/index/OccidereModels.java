package net.chemthunder.occidere.impl.index;

import net.chemthunder.occidere.impl.Occidere;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureKey;

import java.util.Optional;

public interface OccidereModels {
    Model GEM = create("gem", TextureKey.LAYER0);
    Model HERETIC_HANDHELD = create("auratus_heretic_handheld", TextureKey.LAYER0);
    Model HERETIC_BLOCKING = create("auratus_heretic_blocking", TextureKey.LAYER0);

    static Model create(String parent,  TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(Occidere.id("item/template/" + parent)), Optional.empty(), requiredTextureKeys);
    }
}
