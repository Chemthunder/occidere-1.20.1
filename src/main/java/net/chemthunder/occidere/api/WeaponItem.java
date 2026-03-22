package net.chemthunder.occidere.api;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WeaponItem extends Item {
    public float attackDamage;
    public float attackSpeed;
    public boolean isSword;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();

    public WeaponItem(Settings settings, float attackDamage, float attackSpeed, boolean isSword) {
        super(settings.maxCount(1));

        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.isSword = isSword;

        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", this.attackDamage, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", this.attackSpeed, EntityAttributeModifier.Operation.ADDITION));

        this.attributeModifiers = builder.build();
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }

    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return this.isSword ? !miner.isCreative() : super.canMine(state, world, pos, miner);
    }

    public int getNameColor(ItemStack stack) {
        return 0xFFffffff;
    }

    public Text getName(ItemStack stack) {
        return super.getName(stack).copy().styled(style -> style.withColor(getNameColor(stack)));
    }

    public void critEffect(PlayerEntity player, LivingEntity target, ItemStack stack, World world) {}
}
