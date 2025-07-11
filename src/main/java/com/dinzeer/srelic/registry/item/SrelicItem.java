package com.dinzeer.srelic.registry.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class SrelicItem extends Item {
    public SrelicItem(Properties p_41383_) {
        super(p_41383_.rarity(Rarity.EPIC));
    }
    @Override
    public boolean isFireResistant() {
        return true; // 火焰免疫
    }


}
