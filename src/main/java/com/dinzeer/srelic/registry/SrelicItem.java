package com.dinzeer.srelic.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SrelicItem extends Item {
    public SrelicItem(Properties p_41383_) {
        super(p_41383_);
    }
    @Override
    public boolean isFireResistant() {
        return true; // 火焰免疫
    }
    @Override
    public boolean isFoil(ItemStack stack) {
        return true; // 添加发光效果
    }

}
