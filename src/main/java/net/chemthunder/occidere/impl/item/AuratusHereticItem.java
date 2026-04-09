package net.chemthunder.occidere.impl.item;

import net.chemthunder.legere.api.v1.extendable.item.WeaponItem;
import net.chemthunder.legere.api.v1.interfaces.ComplexModelItem;
import net.chemthunder.legere.api.v1.util.ApiUtils;
import net.chemthunder.occidere.api.interfaces.IgnoredByRegisterLangItem;
import net.chemthunder.occidere.impl.cca.item.AuratusHereticItemComponent;
import net.chemthunder.occidere.impl.index.OccidereParticles;
import net.chemthunder.occidere.impl.util.AuratusHereticState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.particle.SweepAttackParticle;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AuratusHereticItem extends WeaponItem implements ComplexModelItem, IgnoredByRegisterLangItem {
    public AuratusHereticItem(Settings settings) {
        super(settings, 8f, -2.7f, true);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        AuratusHereticItemComponent component = AuratusHereticItemComponent.KEY.get(stack);

        if (user.getOffHandStack().getItem() instanceof AHGemItem gem) {
            component.setMode(gem.stateToTransformTo);
        } else {
            if (component.getMode().equals(AuratusHereticState.GILDED)) {
                user.setCurrentHand(hand);
            }
        }

        return super.use(world, user, hand);
    }

    public ParticleEffect getSweepParticle(PlayerEntity player, ItemStack stack) {
        AuratusHereticItemComponent comp = AuratusHereticItemComponent.KEY.get(stack);
        return comp.getMode().equals(AuratusHereticState.GILDED) ? OccidereParticles.GILDED_SWEEP : OccidereParticles.BLOOD_SWEEP;
    }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        AuratusHereticItemComponent component = AuratusHereticItemComponent.KEY.get(stack);

        tooltip.add(Text.literal(component.getMode().string).formatted(Formatting.ITALIC).styled(style -> style.withColor(component.getMode().descColor)));
        tooltip.add(Text.literal(component.getMode().description).formatted(Formatting.DARK_GRAY));

        super.appendTooltip(stack, world, tooltip, context);
    }

    public String getModel(ItemStack stack, PlayerEntity player, ModelTransformationMode modelTransformationMode, World world) {
        AuratusHereticItemComponent component = AuratusHereticItemComponent.KEY.get(stack);

        if (player.isUsingItem()) {
            return switch (component.getMode()) {
                case GILDED -> ApiUtils.isGui(modelTransformationMode) ? "auratus_heretic" : "auratus_heretic_gilded_blocking";
                case TAINTED -> ApiUtils.isGui(modelTransformationMode) ? "auratus_heretic_tainted" : "auratus_heretic_tainted_blocking";
            };
        }

        return switch (component.getMode()) {
            case GILDED -> ApiUtils.isGui(modelTransformationMode) ? "auratus_heretic" : "auratus_heretic_gilded_handheld";
            case TAINTED -> ApiUtils.isGui(modelTransformationMode) ? "auratus_heretic_tainted" : "auratus_heretic_tainted_handheld";
        };
    }

    public List<String> getLoadedModels() {
        return List.of(
                "auratus_heretic",
                "auratus_heretic_tainted",
                "auratus_heretic_gilded_handheld",
                "auratus_heretic_tainted_handheld",
                "auratus_heretic_gilded_blocking",
                "auratus_heretic_tainted_blocking"
        );
    }

    public int getNameColor(ItemStack stack) {
        AuratusHereticItemComponent component = AuratusHereticItemComponent.KEY.get(stack);

        return switch (component.getMode()) {
            case GILDED -> 0xFFb3873d;
            case TAINTED -> 0xFFb00938;
        };
    }
}
