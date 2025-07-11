package com.dinzeer.srelic.registry.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class SrelicStar extends Item {
    public SrelicStar(Item.Properties p_41383_) {
        super(p_41383_.rarity(Rarity.EPIC));
    }
    @Override
    public boolean isFireResistant() {
        return true; // 火焰免疫
    }

    // 添加合成后保留物品的功能
    @Override
    public boolean hasCraftingRemainingItem() {
        return true; // 表示该物品在合成后应有剩余物品
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return itemStack.copy(); // 返回物品的副本，实现合成后保留效果
    }
}