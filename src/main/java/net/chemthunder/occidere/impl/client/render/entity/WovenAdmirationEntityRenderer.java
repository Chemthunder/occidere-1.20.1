package net.chemthunder.occidere.impl.client.render.entity;

import net.chemthunder.occidere.impl.Occidere;
import net.chemthunder.occidere.impl.client.render.model.WovenAdmirationModel;
import net.chemthunder.occidere.impl.entity.WovenAdmirationEntity;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class WovenAdmirationEntityRenderer extends EntityRenderer<WovenAdmirationEntity> {
    public static final Identifier TEXTURE = Occidere.id("textures/entity/woven_admiration.png");
    private final WovenAdmirationModel model;

    public WovenAdmirationEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new WovenAdmirationModel(ctx.getPart(WovenAdmirationModel.MODEL_LAYER));
    }

    public Identifier getTexture(WovenAdmirationEntity entity) {
        return TEXTURE;
    }

    public boolean shouldRender(WovenAdmirationEntity mobEntity, Frustum frustum, double d, double e, double f) {
        return true;
    }

    public void render(WovenAdmirationEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((entity.age + tickDelta) * entity.ticksActive / 20));

        matrices.translate(0, 0, 0);
        matrices.scale(1.6f, 1.6f, 1.6f);
        this.model.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(TEXTURE)), light, OverlayTexture.DEFAULT_UV, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 1);

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}