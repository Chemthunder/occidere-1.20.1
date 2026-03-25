package net.chemthunder.occidere.api;

import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class ApiUtils {
    public static List<Entity> getEntitiesInBox(BlockPos pos, World world, int expansion) {
        Box area = new Box(pos).expand(expansion);

        return world.getEntitiesByClass(Entity.class, area, entity -> true);
    }

    public static void applyRiptide(PlayerEntity player, float strength, int riptideTicks) {
        float f = player.getYaw();
        float g = player.getPitch();
        float h = -MathHelper.sin(f * ((float)Math.PI / 180F)) * MathHelper.cos(g * ((float)Math.PI / 180F));
        float k = -MathHelper.sin(g * ((float)Math.PI / 180F));
        float l = MathHelper.cos(f * ((float)Math.PI / 180F)) * MathHelper.cos(g * ((float)Math.PI / 180F));
        float m = MathHelper.sqrt(h * h + k * k + l * l);
        float n = 3.0F * ((1.0F + strength) / 4.0F);
        h *= n / m;
        k *= n / m;
        l *= n / m;
        player.addVelocity(h, k, l);
        player.useRiptide(riptideTicks);
        if (player.isOnGround()) {
            float o = 1.1999999F;
            player.move(MovementType.SELF, new Vec3d(0.0F, 1.1999999F, 0.0F));
        }
    }

    public static boolean isGui(ModelTransformationMode modelTransformationMode) {
        return modelTransformationMode == ModelTransformationMode.GROUND || modelTransformationMode == ModelTransformationMode.GUI || modelTransformationMode == ModelTransformationMode.FIXED;
    }
}
