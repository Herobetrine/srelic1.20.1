package com.dinzeer.srelic;

import com.dinzeer.srelic.Utils.DashMessage;
import com.dinzeer.srelic.registry.*;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Srelic.MODID)
public class Srelic {
    public static ResourceLocation prefix(String path) {

        return new ResourceLocation(MODID, path);
    }

    public static ResourceLocation id(@NotNull String path) {
        return new ResourceLocation(MODID, path);
    }

    // Define mod id in a common place for everything to reference
    public static final String MODID = "srelic";
    public static SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MODID, "main"),
            () -> "1.0",
            s -> true,
            s -> true
    );
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "srelic" namespace

    public Srelic() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::register);
        SREntiteRegristrys.register(modEventBus);
        ParticleRegistry.register(modEventBus);
        //注册
        SRComboRegsitry.COMBO_STATES.register(modEventBus);
        HeitaComBoRegistry.COMBO_STATES.register(modEventBus);
        SRslashArtRegsitry.SLASH_ARTS.register(modEventBus);
        SRSpecialEffectsRegistry.REGISTRY_KEY2.register(modEventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
//        int id = 0;
//        INSTANCE.messageBuilder(DashMessage.class, id++)
//                .encoder(DashMessage::encode)
//                .decoder(DashMessage::decode)
//                .consumerMainThread(DashMessage::handle)
//                .add();
    }
    public void register(RegisterEvent event) {
        SREntiteRegristrys.register(event);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }
    @SubscribeEvent
    public void tick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
            workQueue.forEach(work -> {
                work.setValue(work.getValue() - 1);
                if (work.getValue() == 0)
                    actions.add(work);
            });
            actions.forEach(e -> e.getKey().run());
            workQueue.removeAll(actions);
        }
    }
    // Add the example block item to the building blocks tab
    @Mod.EventBusSubscriber
    public class simpleEvent{
        @SubscribeEvent
        public static void fmlload(FMLCommonSetupEvent event){
        }

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

    public static void queueServerWork(int tick, Runnable action) {
        workQueue.add(new AbstractMap.SimpleEntry(action, tick));
    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
