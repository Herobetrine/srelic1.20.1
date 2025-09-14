package com.dinzeer.srelic.items.tool;

import com.dinzeer.srelic.registry.SRItemRegsitry;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class BlazeTool {
    public static final Tier Blaze_TIER = new ForgeTier(
            5,
            3000,
            6.0f,
            4.0f,
            30,
            Tags.Blocks.NEEDS_NETHERITE_TOOL,
            () -> Ingredient.of(SRItemRegsitry.flame_netherite_alloy)
    );
}
