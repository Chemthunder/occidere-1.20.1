package net.chemthunder.occidere.impl.client.render.entity;

import net.chemthunder.occidere.impl.Occidere;
import net.chemthunder.occidere.impl.client.render.model.HereticSawModel;
import net.chemthunder.occidere.impl.client.render.model.WovenAdmirationModel;
import net.chemthunder.occidere.impl.entity.HereticSawEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class HereticSawEntityRenderer extends EntityRenderer<HereticSawEntity> {
    public static final Identifier TEXTURE = Occidere.id("textures/entity/heretic_saw.png");
    private final HereticSawModel model;

    public HereticSawEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new HereticSawModel(ctx.getPart(HereticSawModel.MODEL_LAYER));
    }

    public Identifier getTexture(HereticSawEntity entity) {
        return TEXTURE;
    }

    public void render(HereticSawEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {

        int speed = 1;

        matrices.push();

        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((entity.age + tickDelta) * speed));

        this.model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(TEXTURE)), light, OverlayTexture.DEFAULT_UV, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 1);
        matrices.pop();

        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}
