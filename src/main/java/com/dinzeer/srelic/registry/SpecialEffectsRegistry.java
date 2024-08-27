package com.dinzeer.srelic.registry;

import java.util.function.Supplier;


import com.dinzeer.srelic.Srelic;
import com.dinzeer.srelic.registry.SpecialEffectsRegistry;
import com.dinzeer.srelic.registry.specialeffects.SpecialEffect;
import com.dinzeer.srelic.registry.specialeffects.VeryGreen;

import mods.flammpfeil.slashblade.SlashBlade;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryObject;

import static com.dinzeer.srelic.Srelic.*;

public class SpecialEffectsRegistry {
    public static final ResourceKey<Registry<SpecialEffect>> REGISTRY_KEY2 = ResourceKey
            .createRegistryKey(Srelic.prefix("special_effect"));
    public static final DeferredRegister<SpecialEffect> SPECIAL_EFFECT =
            DeferredRegister.create(REGISTRY_KEY2, MODID);

    public static final Supplier<IForgeRegistry<SpecialEffect>> REGISTRY = SPECIAL_EFFECT.makeRegistry(RegistryBuilder::new);

    public static final RegistryObject<SpecialEffect> VERY_GREEN = SPECIAL_EFFECT.register("very_green",
            VeryGreen::new);
}
