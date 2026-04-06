package net.chemthunder.legere.mixin.client.item;

import net.chemthunder.legere.api.v1.interfaces.ComplexModelItem;
import net.chemthunder.legere.api.v1.interfaces.SimpleModelItem;
import net.chemthunder.occidere.impl.Occidere;
import net.chemthunder.occidere.impl.index.OccidereItems;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.item.Item;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@SuppressWarnings("rawtypes")
@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {
    @Shadow protected abstract void addModel(ModelIdentifier modelId);

    @Inject(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/model/ModelLoader;addModel(Lnet/minecraft/client/util/ModelIdentifier;)V",
                    ordinal = 3,
                    shift = At.Shift.AFTER
            )
    )
    public void addModels(BlockColors blockColors, Profiler profiler, Map jsonUnbakedModels, Map blockStates, CallbackInfo ci) {
        for (Item value : OccidereItems.ITEMS.keySet()) {
            if (value instanceof SimpleModelItem item) {
                this.addModel(new ModelIdentifier(Occidere.MOD_ID, item.getItemId() + "_" + item.handheldId(), "inventory"));
            }

            if (value instanceof ComplexModelItem complexModelItem) {
                for (var s : complexModelItem.getLoadedModels()) {
                    this.addModel(new ModelIdentifier(Occidere.MOD_ID, s, "inventory"));
                }
            }
        }
    }
}