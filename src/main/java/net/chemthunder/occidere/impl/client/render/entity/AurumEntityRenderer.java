package net.chemthunder.occidere.impl.client.render.entity;

import net.chemthunder.occidere.impl.entity.AurumEntity;
import net.chemthunder.occidere.impl.index.OccidereItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class AurumEntityRenderer extends EntityRenderer<AurumEntity> {
    public AurumEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    public void render(AurumEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        ItemStack stack = new ItemStack(OccidereItems.AURUM);
        ItemRenderer renderer = MinecraftClient.getInstance().getItemRenderer();

        matrices.push();

        int speed = 1;

        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(10));

        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees((entity.age + tickDelta) * speed));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((entity.age + tickDelta) * speed));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((entity.age + tickDelta) * speed));

        renderer.renderItem(stack,
                ModelTransformationMode.THIRD_PERSON_RIGHT_HAND,
                light,
                OverlayTexture.DEFAULT_UV,
                matrices,
                vertexConsumers,
                entity.getWorld(),
                entity.getId()
        );

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    public Identifier getTexture(AurumEntity entity) {
        return null;
    }
}
