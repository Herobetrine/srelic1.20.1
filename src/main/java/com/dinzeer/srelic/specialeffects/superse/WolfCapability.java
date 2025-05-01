package com.dinzeer.srelic.specialeffects.superse;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class WolfCapability {
    public static final Capability<WolfSoul.WolfState> WOLF_STATE =
            CapabilityManager.get(new CapabilityToken<>(){});
    private int combo;

    @SubscribeEvent
    public static void registerCapability(RegisterCapabilitiesEvent event) {
        event.register(WolfSoul.WolfState.class);
    }





    
}