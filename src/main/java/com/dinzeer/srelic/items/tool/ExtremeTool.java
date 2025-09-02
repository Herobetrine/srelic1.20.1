package com.dinzeer.srelic.items.tool;

import com.dinzeer.srelic.registry.SRItemRegsitry;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class ExtremeTool {
    public static final Tier EXTREME_PICKAXE_TIER = new Tier() {
        public int getUses() {
            return 0;
        }

        public float getSpeed() {
            return 20f;
        }

        public float getAttackDamageBonus() {
            return 5f;
        }

        public int getLevel() {
            return 7;
        }

        public int getEnchantmentValue() {
            return 30;
        }

        public Ingredient getRepairIngredient() {
            return Ingredient.of(new ItemStack(SRItemRegsitry.max_ingot));
        }
    };
    public static final Tier EXTREME_SWORD_TIER = new ForgeTier(
            5,
            0,
            6.0f,
            8.0f,
            30,
            Tags.Blocks.NEEDS_NETHERITE_TOOL,
            () -> Ingredient.of(SRItemRegsitry.max_ingot,SRItemRegsitry.maxim)
    );
}
