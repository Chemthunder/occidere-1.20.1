package net.chemthunder.occidere.impl.client.render.entity;

import net.chemthunder.occidere.impl.Occidere;
import net.chemthunder.occidere.impl.entity.WovenAdmirationEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;

// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class WovenAdmirationModel extends Model {
    private final ModelPart bb_main;

    public WovenAdmirationModel(ModelPart root) {
        super(RenderLayer::getEntityCutout);
        this.bb_main = root.getChild("bb_main");
    }

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(-32, 0).cuboid(-31.0F, 0.0F, -1.0F, 32.0F, 0.0F, 32.0F, new Dilation(0.0F)), ModelTransform.of(15.0F, -1.0F, 0.0F, 1.5708F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}

	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		this.bb_main.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

    public static final EntityModelLayer MODEL_LAYER = new EntityModelLayer(Occidere.id("woven_admiration"), "main");
}