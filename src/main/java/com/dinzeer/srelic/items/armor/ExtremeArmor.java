package com.dinzeer.srelic.items.armor;

import com.dinzeer.srelic.registry.SRItemRegsitry;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import mods.flammpfeil.slashblade.registry.ModAttributes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.UUID;

import static com.dinzeer.srelic.Srelic.MODID;

public class ExtremeArmor extends ArmorItem {
    private static final int[] DURABILITY_PER_SLOT = new int[]{0, 0, 0, 0};
    private static final int[] PROTECTION_PER_SLOT = new int[]{9, 18, 16, 9};
    private static final UUID[] ATTACK_DAMAGE_UUIDS = {
            UUID.nameUUIDFromBytes("ExtremeArmorA".getBytes()),
            UUID.nameUUIDFromBytes("ExtremeArmorB".getBytes()),
            UUID.nameUUIDFromBytes("ExtremeArmorC".getBytes()),
            UUID.nameUUIDFromBytes("ExtremeArmorD".getBytes())
    };

	public ExtremeArmor(ArmorItem.Type type, Item.Properties properties) {
            super(new ArmorMaterial() {
                @Override
                public int getDurabilityForType(ArmorItem.Type type) {
                    return DURABILITY_PER_SLOT[type.getSlot().getIndex()];
                }

                @Override
                public int getDefenseForType(ArmorItem.Type type) {
                    return PROTECTION_PER_SLOT[type.getSlot().getIndex()];
                }

                @Override
                public int getEnchantmentValue() {
                    return 32;
                }

                @Override
                public SoundEvent getEquipSound() {
                    return SoundEvents.EMPTY;
                }

                @Override
                public Ingredient getRepairIngredient() {
                    return Ingredient.of(new ItemStack(SRItemRegsitry.max_ingot));
                }

                @Override
                public String getName() {
                    return "extreme_armor";
                }

                @Override
                public float getToughness() {
                    return 5.0F;
                }

                @Override
                public float getKnockbackResistance() {
                    return 0.2F;
                }
            }, type, properties);
        }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);

        String[] a=itemstack.getItem().getDescriptionId().split("\\.",3);


        list.add(Component.literal("========================").withStyle(ChatFormatting.GOLD));
        list.add(Component.translatable("item."+MODID+".max_armor.desc").withStyle(ChatFormatting.GOLD));
        list.add(Component.translatable("item."+MODID+".max_armor.desc_1").withStyle(ChatFormatting.GOLD));
        list.add(Component.translatable("item."+MODID+".max_armor.desc_2").withStyle(ChatFormatting.GOLD));
        list.add(Component.translatable("item."+MODID+".max_armor.desc_3").withStyle(ChatFormatting.GOLD));
        list.add(Component.translatable("item."+MODID+".max_armor.desc_4").withStyle(ChatFormatting.GOLD));
        list.add(Component.translatable("item."+MODID+".max_armor.desc_5").withStyle(ChatFormatting.GOLD));
        list.add(Component.literal("========================").withStyle(ChatFormatting.GOLD));


    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = ArrayListMultimap.create();

        if (slot == this.type.getSlot()) {
            int slotIndex = this.type.getSlot().getIndex();


            modifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                    ATTACK_DAMAGE_UUIDS[slotIndex],
                    "Armor attack boost",
                    0.1,
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            ));
            modifiers.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(
                    ATTACK_DAMAGE_UUIDS[slotIndex],
                    "Armor toughness boost",
                    5.0,
                    AttributeModifier.Operation.ADDITION
            ));


            modifiers.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(
                    ATTACK_DAMAGE_UUIDS[slotIndex],
                    "Armor knockback resistance boost",
                    0.2,
                    AttributeModifier.Operation.ADDITION
            ));
            modifiers.put(Attributes.ARMOR, new AttributeModifier(
                    ATTACK_DAMAGE_UUIDS[slotIndex],
                    "Armor boost",
                    PROTECTION_PER_SLOT[slotIndex],
                    AttributeModifier.Operation.ADDITION
            ));
            modifiers.put(ModAttributes.SLASHBLADE_DAMAGE.get(), new AttributeModifier(
                    ATTACK_DAMAGE_UUIDS[slotIndex],
                    "Armor boost",
                   0.25f,
                    AttributeModifier.Operation.ADDITION
            ));
        }

        return modifiers;
    }


    public static class Helmet extends ExtremeArmor {
        public Helmet() {
            super(ArmorItem.Type.HELMET, new Item.Properties());
        }

        @Override
        public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
            super.appendHoverText(itemstack, world, list, flag);
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "srelic:textures/models/armor/extreme_armor_layer_1.png";
        }

        @Override
        public boolean makesPiglinsNeutral(ItemStack itemstack, LivingEntity entity) {
            return false;
        }
    }

    public static class Chestplate extends ExtremeArmor {
        public Chestplate() {
            super(ArmorItem.Type.CHESTPLATE, new Item.Properties());
        }

        @Override
        public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
            super.appendHoverText(itemstack, world, list, flag);
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "srelic:textures/models/armor/extreme_armor_layer_1.png";
        }

        @Override
        public boolean makesPiglinsNeutral(ItemStack itemstack, LivingEntity entity) {
            return false;
        }
    }


    public static class Leggings extends ExtremeArmor {
        public Leggings() {
            super(ArmorItem.Type.LEGGINGS, new Item.Properties());
        }

        @Override
        public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
            super.appendHoverText(itemstack, world, list, flag);
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "srelic:textures/models/armor/extreme_armor_layer_2.png";
        }

        @Override
        public boolean makesPiglinsNeutral(ItemStack itemstack, LivingEntity entity) {
            return false;
        }
    }

    public static class Boots extends ExtremeArmor {
        public Boots() {
            super(ArmorItem.Type.BOOTS, new Item.Properties());
        }

        @Override
        public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
            super.appendHoverText(itemstack, world, list, flag);
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "srelic:textures/models/armor/extreme_armor_layer_1.png";
        }

        @Override
        public boolean makesPiglinsNeutral(ItemStack itemstack, LivingEntity entity) {
            return false;
        }
    }






}