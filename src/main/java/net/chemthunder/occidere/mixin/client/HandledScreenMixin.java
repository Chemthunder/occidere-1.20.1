package net.chemthunder.occidere.mixin.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin<T extends ScreenHandler> extends Screen implements ScreenHandlerProvider<T> {

    @Shadow
    @Final
    protected T handler;

    @Shadow
    @Nullable
    protected Slot focusedSlot;

    protected HandledScreenMixin(Text title) {
        super(title);
    }

//    @WrapMethod(method = "drawMouseoverTooltip")
//    private void silly(DrawContext context, int x, int y, Operation<Void> original) {
//        ItemStack stack = this.handler.getCursorStack();
//
//        if (stack.isEmpty() && this.focusedSlot != null && this.focusedSlot.hasStack()) {
//            ItemStack miscStack = this.focusedSlot.getStack();
//
//            if (miscStack.getItem() instanceof ColorableItem item) {
//
//            }
//        }
//    }
}
