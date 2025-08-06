package com.dinzeer.srelic;

import com.dinzeer.srelic.registry.imp.SRRegisteredStackManager;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class StackChargeEvent extends PlayerEvent {
    public SRRegisteredStackManager stackManager;
    public int amount;
    public StackChargeEvent(Player player, SRRegisteredStackManager stackManager, int amount) {

        super(player);

        this.stackManager = stackManager;
        this.amount = amount;
    }
}
