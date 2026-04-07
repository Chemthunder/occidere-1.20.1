package net.chemthunder.occidere.impl.event;

import net.chemthunder.occidere.api.interfaces.ItemStatus;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class ItemStatusEvent implements ItemTooltipCallback {

    public void getTooltip(ItemStack itemStack, TooltipContext tooltipContext, List<Text> list) {
        if (itemStack.getItem() instanceof ItemStatus status) {
            list.add(Text.literal(status.getStatus().string).formatted(Formatting.DARK_GRAY));
        }
    }
}
