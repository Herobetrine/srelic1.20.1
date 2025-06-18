package com.dinzeer.srelic.registry.imp;

import net.minecraft.world.entity.player.Player;

public interface IStackManager {
    void addStacks(Player player, int amount);
    int getCurrentStacks(Player player);
    int getCurrentStacksoffhand(Player player);
    void resetStacks(Player player);
    String getEffectKey();
    int getMaxStacks();
}