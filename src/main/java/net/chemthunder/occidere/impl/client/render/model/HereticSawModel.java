package net.chemthunder.occidere.impl.client.render.model;

import net.chemthunder.occidere.impl.Occidere;
import net.chemthunder.occidere.impl.entity.HereticSawEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;

// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class HereticSawModel extends Model {
	private final ModelPart bb_main;

	public HereticSawModel(ModelPart root) {
        super(RenderLayer::getEntityCutout);
		this.bb_main = root.getChild("bb_main");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(-15, 2).cuboid(-9.0F, -1.0F, -8.0F, 18.0F, 0.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}

	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		this.bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

    public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(Occidere.id("heretic_saw"), "main");
}