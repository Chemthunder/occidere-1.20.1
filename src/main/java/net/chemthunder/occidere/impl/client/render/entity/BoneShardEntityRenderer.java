package net.chemthunder.occidere.impl.client.render.entity;

import net.chemthunder.occidere.impl.entity.BoneShardEntity;
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

public class BoneShardEntityRenderer extends EntityRenderer<BoneShardEntity> {
    public BoneShardEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    public Identifier getTexture(BoneShardEntity entity) {
        return null;
    }

    public void render(BoneShardEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        MinecraftClient client = MinecraftClient.getInstance();
        ItemStack stack = new ItemStack(Items.BONE);
        ItemRenderer renderer = client.getItemRenderer();

        matrices.push();

        matrices.multiply(this.dispatcher.getRotation());
        renderer.renderItem(
                stack,
                ModelTransformationMode.GROUND,
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
}