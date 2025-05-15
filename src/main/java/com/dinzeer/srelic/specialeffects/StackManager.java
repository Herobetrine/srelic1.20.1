package com.dinzeer.srelic.specialeffects;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class StackManager {
    public static void addStacks(Player player, String effectKey, int amount, int max) {
        ItemStack weapon = player.getMainHandItem();
        CompoundTag tag = weapon.getOrCreateTag();
        String key = effectKey + "_Stacks";

        int current = tag.getInt(key);
        int newValue = Math.min(current + amount, max);
        tag.putInt(key, newValue);

        // 强制同步到客户端
        weapon.setTag(tag);
        player.setItemInHand(player.getUsedItemHand(), weapon);
    }

    public static int getCurrentStacks(Player player, String effectKey) {
        return player.getMainHandItem().getOrCreateTag().getInt(effectKey + "_Stacks");
    }

    public static void resetStacks(Player player, String effectKey) {
        ItemStack weapon = player.getMainHandItem();
        CompoundTag tag = weapon.getOrCreateTag();
        tag.remove(effectKey + "_Stacks");
        weapon.setTag(tag);
        player.setItemInHand(player.getUsedItemHand(), weapon);
    }
}
