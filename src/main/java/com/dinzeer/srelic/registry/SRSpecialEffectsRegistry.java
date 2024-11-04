package com.dinzeer.srelic.registry;

import com.dinzeer.srelic.registry.specialeffects.Flameerosion;
import com.dinzeer.srelic.registry.specialeffects.MaxBreak;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.dinzeer.srelic.Srelic.MODID;

public class SRSpecialEffectsRegistry {

    public static final DeferredRegister<SpecialEffect> REGISTRY_KEY2;//这玩意是最重要的
    public static final RegistryObject<SpecialEffect> FLAMEROSION;
    public static final RegistryObject<SpecialEffect> MAXBREAK;

    public SRSpecialEffectsRegistry() {
    }
    static {
        REGISTRY_KEY2 = DeferredRegister.create(SpecialEffect.REGISTRY_KEY, MODID);
        FLAMEROSION= REGISTRY_KEY2.register("flamerosion", Flameerosion::new);
        MAXBREAK= REGISTRY_KEY2.register("maxbreak", MaxBreak::new);
    }
}
