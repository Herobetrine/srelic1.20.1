package com.dinzeer.srelic;

import com.dinzeer.srelic.blade.SRItem;
import com.dinzeer.srelic.blade.re.SrelicRecipeSerializerRegistry;
import com.dinzeer.srelic.registry.*;
import com.mojang.logging.LogUtils;
import com.tterrag.registrate.Registrate;
import mods.flammpfeil.slashblade.client.renderer.model.BladeModelManager;
import mods.flammpfeil.slashblade.init.SBItems;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
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
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.*;
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

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS
            = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,MODID);  ;

    public static final Registrate REGISTRATE = Registrate.create(MODID);


    public static final RegistryObject<CreativeModeTab> SRSlashblade = CREATIVE_MODE_TABS.register(MODID+"_slashblade",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("item_group."+MODID+"."+MODID+"_slashblade")).icon(() -> {
                        ItemStack stack = new ItemStack(SBItems.slashblade);
                        stack.getCapability(ItemSlashBlade.BLADESTATE).ifPresent(s -> {
                            s.setModel(new ResourceLocation(MODID, "model/stairail/none.obj"));
                            s.setTexture(new ResourceLocation(MODID, "model/stairail/none_red.jpg"));
                        });
                        return stack;
                    })
                    .displayItems((parameters, tabData) -> {

                        fillBlades(tabData);
                    })
                    .build());



    private static void fillBlades(CreativeModeTab.Output output) {
        if (Minecraft.getInstance().getConnection() != null) {
            BladeModelManager.getClientSlashBladeRegistry()
                    .entrySet().stream()
                    // 步骤1：过滤空值
                    .filter(entry -> entry.getKey() != null && entry.getValue() != null)
                    // 步骤2：解析字符串为ResourceLocation并过滤命名空间
                    .filter(entry -> {
                        ResourceLocation loc = ResourceLocation.tryParse(entry.getKey().location().toString());
                        return loc != null && loc.getNamespace().equals(MODID);
                    })
                    // 步骤3：按字符串键排序
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry -> {
                        LOGGER.info("Registering Slashblade: {}", entry.getKey());
                        output.accept(entry.getValue().getBlade());
                    });
        }
    }



    public Srelic() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::register);
        SREntiteRegristrys.register(modEventBus);
        ParticleRegistry.register(modEventBus);
        //注册
        SRComboRegsitry.COMBO_STATES.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        HeitaComBoRegistry.COMBO_STATES.register(modEventBus);
        SRslashArtRegsitry.SLASH_ARTS.register(modEventBus);
        SRSpecialEffectsRegistry.REGISTRY_KEY2.register(modEventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        SRPaintings.register(modEventBus);
        SrelicRecipeSerializerRegistry.RECIPE_SERIALIZER.register(modEventBus);
        SRItemRegsitry.regsitry();
        LangRegistry.register();
//        int id = 0;
//        INSTANCE.messageBuilder(DashMessage.class, id++)
//                .encoder(DashMessage::encode)
//                .decoder(DashMessage::decode)
//                .consumerMainThread(DashMessage::handle)
//                .add();
    }
    public void register(RegisterEvent event) {
        SREntiteRegristrys.register(event);
        SRItem.register(event);
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
