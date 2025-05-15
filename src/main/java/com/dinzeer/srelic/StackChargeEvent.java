package com.dinzeer.srelic;

import com.dinzeer.srelic.registry.imp.RegisteredStackManager;
import com.dinzeer.srelic.specialeffects.StackManager;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class StackChargeEvent extends PlayerEvent {
    public RegisteredStackManager stackManager;
    public int amount;
    public StackChargeEvent(Player player, RegisteredStackManager stackManager, int amount) {
        super(player);
        this.stackManager = stackManager;
        this.amount = amount;
    }
}
