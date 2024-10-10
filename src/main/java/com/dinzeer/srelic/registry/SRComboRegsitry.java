package com.dinzeer.srelic.registry;

import mods.flammpfeil.slashblade.registry.combo.ComboState;
import net.minecraftforge.registries.DeferredRegister;

import static com.dinzeer.srelic.Srelic.MODID;

public class SRComboRegsitry {
    public static final DeferredRegister<ComboState> COMBO_STATES =
            DeferredRegister.create(ComboState.REGISTRY_KEY,MODID);

}
