package net.chemthunder.legere.api.v1.interfaces;

import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public interface ComplexModelItem {
    String getModel(ItemStack stack, PlayerEntity player, ModelTransformationMode modelTransformationMode, World world);
    List<String> getLoadedModels();
}