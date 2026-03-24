package net.chemthunder.occidere.mixin.client.item;

import net.chemthunder.occidere.api.interfaces.ComplexModelItem;
import net.chemthunder.occidere.api.interfaces.HandheldItem;
import net.chemthunder.occidere.impl.Occidere;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * This class was created by Vowxky.
 * All rights reserved to the developer.
 */

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel useModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            if ((stack.getItem() instanceof HandheldItem item) && (!(stack.getItem() instanceof ComplexModelItem)) && (renderMode != ModelTransformationMode.GUI) && renderMode != ModelTransformationMode.GROUND) {
                return ((ItemRendererAccessor) this).renderer$getModels().getModelManager().getModel(new ModelIdentifier(Occidere.MOD_ID, item.getItemId() + "_" + item.handheldId(), "inventory"));
            }

            if (stack.getItem() instanceof ComplexModelItem complexModelItem) {
                return ((ItemRendererAccessor) this).renderer$getModels().getModelManager().getModel(new ModelIdentifier(Occidere.MOD_ID, complexModelItem.getModel(stack, player, renderMode, player.getWorld()), "inventory"));
            }
        }
        return value;
    }
}