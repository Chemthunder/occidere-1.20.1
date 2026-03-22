package net.chemthunder.occidere.api;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class ApiUtils {

    public static List<LivingEntity> getEntitiesInBox(BlockPos pos, World world, int expansion) {
        Box area = new Box(pos).expand(expansion);

        return world.getEntitiesByClass(LivingEntity.class, area, entity -> true);
    }
}
