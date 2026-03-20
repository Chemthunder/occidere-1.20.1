package net.chemthunder.occidere.mixin.client;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.chemthunder.occidere.api.ColorableItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.TooltipBackgroundRenderer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TooltipBackgroundRenderer.class)
public abstract class TooltipBackgroundRendererMixin {

    @Shadow
    @Final
    private static int START_Y_BORDER_COLOR;
    @Shadow
    @Final
    private static int END_Y_BORDER_COLOR;
    @Shadow
    @Final
    private static int BACKGROUND_COLOR;

    @Shadow
    private static void renderHorizontalLine(DrawContext context, int x, int y, int width, int z, int color) {
    }

    @Shadow
    private static void renderRectangle(DrawContext context, int x, int y, int width, int height, int z, int color) {
    }

    @Shadow
    private static void renderVerticalLine(DrawContext context, int x, int y, int height, int z, int startColor, int endColor) {
    }

    @Shadow
    private static void renderBorder(DrawContext context, int x, int y, int width, int height, int z, int startColor, int endColor) {
    }

//    @WrapMethod(method = "render")
//    private static void s(DrawContext context, int x, int y, int width, int height, int z, Operation<Void> original) {
//        if (MinecraftClient.getInstance().getCameraEntity() != null) {
//            ItemStack stack = MinecraftClient.getInstance().getCameraEntity().getPickBlockStack();
//
//
//            assert stack != null;
//            if (stack.getItem() != null) {
//                if (stack.getItem() instanceof ColorableItem item) {
//                    int i = x - 3;
//                    int j = y - 3;
//                    int k = width + 3 + 3;
//                    int l = height + 3 + 3;
//                    renderHorizontalLine(context, i, j - 1, k, z, item.bgColor(stack));
//                    renderHorizontalLine(context, i, j + l, k, z, item.bgColor(stack));
//                    renderRectangle(context, i, j, k, l, z, item.bgColor(stack));
//                    renderVerticalLine(context, i - 1, j, l, z, item.startColor(stack), item.endColor(stack));
//                    renderVerticalLine(context, i + k, j, l, z, item.startColor(stack), item.endColor(stack));
//                    renderBorder(context, i, j + 1, k, l, z, item.startColor(stack), item.endColor(stack));
//                } else {
//                    original.call(context, x, y, width, height, z);
//                }
//
//                original.call(context, x, y, width, height, z);
//            }
//        }
//    }
}
