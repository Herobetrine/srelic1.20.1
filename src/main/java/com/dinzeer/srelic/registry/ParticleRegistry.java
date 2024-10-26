package com.dinzeer.srelic.registry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.dinzeer.srelic.Srelic.MODID;

public class ParticleRegistry
{
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MODID);

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
    public static final RegistryObject<SimpleParticleType> UNSTABLE_ENDER_PARTICLE = PARTICLE_TYPES.register("unstable_ender", () -> new SimpleParticleType(false));

}
