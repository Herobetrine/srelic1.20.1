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
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.UUID;

import static com.dinzeer.srelic.Srelic.MODID;

public class SkyArmor extends ArmorItem {
    private static final int[] DURABILITY_PER_SLOT = new int[]{2000, 3600, 3200, 1600};
    private static final int[] PROTECTION_PER_SLOT = new int[]{9, 15, 13,8};
    private static final UUID[] ATTACK_DAMAGE_UUIDS = {
            UUID.nameUUIDFromBytes("SkyArmorA".getBytes()),
            UUID.nameUUIDFromBytes("SkyArmorB".getBytes()),
            UUID.nameUUIDFromBytes("SkyArmorC".getBytes()),
            UUID.nameUUIDFromBytes("SkyArmorD".getBytes())
    };

	public SkyArmor(Type type, Properties properties) {
            super(new ArmorMaterial() {
                @Override
                public int getDurabilityForType(Type type) {
                    return DURABILITY_PER_SLOT[type.getSlot().getIndex()];
                }

                @Override
                public int getDefenseForType(Type type) {
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
                    return Ingredient.of(new ItemStack(SRItemRegsitry.windy_core_ingot));
                }

                @Override
                public String getName() {
                    return "sky_armor";
                }

                @Override
                public float getToughness() {
                    return 3.0F;
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
        list.add(Component.translatable("item."+MODID+".sky_armor.desc").withStyle(ChatFormatting.GOLD));
        list.add(Component.translatable("item."+MODID+".sky_armor.desc_1").withStyle(ChatFormatting.GOLD));
        list.add(Component.literal("========================").withStyle(ChatFormatting.GOLD));


    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = ArrayListMultimap.create();

        if (slot == this.type.getSlot()) {
            int slotIndex = this.type.getSlot().getIndex();



            modifiers.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(
                    ATTACK_DAMAGE_UUIDS[slotIndex],
                    "Armor toughness boost",
                    2.0,
                    AttributeModifier.Operation.ADDITION
            ));
            modifiers.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(
                    ATTACK_DAMAGE_UUIDS[slotIndex],
                    "Armor knockback resistance boost",
                    0.05,
                    AttributeModifier.Operation.ADDITION
            ));
            modifiers.put(ModAttributes.SLASHBLADE_DAMAGE.get(), new AttributeModifier(
                    ATTACK_DAMAGE_UUIDS[slotIndex],
                    "Armor  boost",
                    0.05,
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            ));
            modifiers.put(Attributes.ARMOR, new AttributeModifier(
                    ATTACK_DAMAGE_UUIDS[slotIndex],
                    "Armor boost",
                    PROTECTION_PER_SLOT[slotIndex],
                    AttributeModifier.Operation.ADDITION
            ));
        }

        return modifiers;
    }


    public static class Helmet extends SkyArmor {
        public Helmet() {
            super(Type.HELMET, new Properties());
        }

        @Override
        public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
            super.appendHoverText(itemstack, world, list, flag);
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "srelic:textures/models/armor/sky_armor_layer_1.png";
        }

        @Override
        public boolean makesPiglinsNeutral(ItemStack itemstack, LivingEntity entity) {
            return false;
        }
    }

    public static class Chestplate extends SkyArmor {
        public Chestplate() {
            super(Type.CHESTPLATE, new Properties());
        }

        @Override
        public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
            super.appendHoverText(itemstack, world, list, flag);
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "srelic:textures/models/armor/sky_armor_layer_1.png";
        }

        @Override
        public boolean makesPiglinsNeutral(ItemStack itemstack, LivingEntity entity) {
            return false;
        }
    }


    public static class Leggings extends SkyArmor {
        public Leggings() {
            super(Type.LEGGINGS, new Properties());
        }

        @Override
        public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
            super.appendHoverText(itemstack, world, list, flag);
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "srelic:textures/models/armor/sky_armor_layer_2.png";
        }

        @Override
        public boolean makesPiglinsNeutral(ItemStack itemstack, LivingEntity entity) {
            return false;
        }
    }

    public static class Boots extends SkyArmor {
        public Boots() {
            super(Type.BOOTS, new Properties());
        }

        @Override
        public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
            super.appendHoverText(itemstack, world, list, flag);
        }

        @Override
        public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
            return "srelic:textures/models/armor/sky_armor_layer_1.png";
        }

        @Override
        public boolean makesPiglinsNeutral(ItemStack itemstack, LivingEntity entity) {
            return false;
        }
    }






}