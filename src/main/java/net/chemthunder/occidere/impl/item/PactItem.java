package net.chemthunder.occidere.impl.item;

import net.chemthunder.occidere.api.extendable.MiscItem;
import net.chemthunder.occidere.api.interfaces.model.ComplexModelItem;
import net.chemthunder.occidere.impl.cca.item.PactComponent;
import net.chemthunder.occidere.impl.index.OccidereDamageSources;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PactItem extends MiscItem implements ComplexModelItem {
    public PactItem(Settings settings) {
        super(settings);
    }

    public int getNameColor(ItemStack stack) {
        PactComponent pact = PactComponent.KEY.get(stack);

        if (pact.getSigned()) {
            return 0xFFab0c30;
        }
        return 0xFF3b0b16;
    }

    public Text getName(ItemStack stack) {
        PactComponent pact = PactComponent.KEY.get(stack);

        if (pact.getSigned()) {
            return Text.translatable("item.occidere.signed_pact").styled(style -> style.withColor(getNameColor(stack)));
        }
        return super.getName(stack).copy().styled(style -> style.withColor(getNameColor(stack)));
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        PactComponent pact = PactComponent.KEY.get(user.getStackInHand(hand));

        if (user.isSneaking()) {
            if (!pact.getSigned()) {
                pact.setIsSigned(true);
                pact.setSigner(user.getUuid());
                pact.setSignerName(user.getDisplayName().getString());

                user.damage(OccidereDamageSources.pact(user), 1.0f);

                if (world.isClient) {
                    user.swingHand(hand);
                }
            }
        }
        return super.use(world, user, hand);
    }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        PactComponent pact = PactComponent.KEY.get(stack);

        if (pact.getSigned()) {
            tooltip.add(Text.translatable("item.pact.signed").formatted(Formatting.DARK_GRAY).append(Text.literal(pact.getSignerName()).styled(style -> style.withColor(0xFFff0055))));
        } else {
            tooltip.add(Text.translatable("item.pact.unsigned").formatted(Formatting.DARK_GRAY));
        }

        super.appendTooltip(stack, world, tooltip, context);
    }

    public String getModel(ItemStack stack, PlayerEntity player, ModelTransformationMode modelTransformationMode, World world) {
        return PactComponent.KEY.get(stack).getSigned() ? "pact_signed" : "pact";
    }

    public List<String> getLoadedModels() {
        return List.of(
                "pact",
                "pact_signed"
        );
    }
}