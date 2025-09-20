package com.dinzeer.srelic;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.item.SBItems;
import mods.flammpfeil.slashblade.registry.CreativeModeTabRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.stream.Collectors;

@Mod(Srelic.MODID)
public class Srelic {
    public static final String MODID = "srelic";
    
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = 
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    
    // 修复创意模式标签，添加客户端环境检查
    public static final RegistryObject<CreativeModeTab> SRSlashblade = CREATIVE_MODE_TABS.register(MODID + "_slashblade",
        () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabRegistry.SLASHBLADE_GROUP.getId())
            .title(Component.translatable("item_group." + MODID + "." + MODID + "_slashblade"))
            .icon(() -> {
                // 仅在客户端执行图标初始化
                if (FMLEnvironment.dist == Dist.CLIENT) {
                    ItemStack stack = new ItemStack(SBItems.slashblade);
                    stack.getCapability(ItemSlashBlade.BLADESTATE).ifPresent(s -> {
                        s.setModel(new ResourceLocation(MODID, "model/stairail/none.obj"));
                        s.setTexture(new ResourceLocation(MODID, "model/stairail/none_red.jpg"));
                    });
                    return stack;
                }
                // 服务端返回空物品
                return new ItemStack(Items.AIR);
            })
            .displayItems((parameters, tabData) -> {
                // 仅在客户端填充物品
                if (FMLEnvironment.dist == Dist.CLIENT) {
                    fillBladesForNamespace(parameters, tabData, MODID);
                }
            })
            .build());
    
    public static final RegistryObject<CreativeModeTab> SRSE = CREATIVE_MODE_TABS.register(MODID + "_se",
        () -> CreativeModeTab.builder()
            .withTabsBefore(SRSlashblade.getId())
            .title(Component.translatable("item_group." + MODID + "." + MODID + "_se"))
            .icon(() -> new ItemStack(ModItems.empty_se.get()))
            .displayItems((parameters, tabData) -> {
                // 仅在客户端填充物品
                if (FMLEnvironment.dist == Dist.CLIENT) {
                    fillSEs(tabData);
                }
            })
            .build());
    
    public static final RegistryObject<CreativeModeTab> SRSE2 = CREATIVE_MODE_TABS.register(MODID + "_se2",
        () -> CreativeModeTab.builder()
            .withTabsBefore(SRSE.getId())
            .title(Component.translatable("item_group." + MODID + "." + MODID + "_se2"))
            .icon(() -> new ItemStack(ModItems.empty_se2.get()))
            .displayItems((parameters, tabData) -> {
                // 仅在客户端填充物品
                if (FMLEnvironment.dist == Dist.CLIENT) {
                    fillSEs2(tabData);
                }
            })
            .build());

    public Srelic() {
        IEventBus bus = MinecraftForge.EVENT_BUS;
        
        // 注册创意模式标签
        CREATIVE_MODE_TABS.register(bus);
        
        // 注册物品和其他内容
        ModItems.ITEMS.register(bus);
        ModBlades.BLADES.register(bus);
        ModSE.SPECIAL_EFFECTS.register(bus);
        
        // 注册事件监听器
        bus.register(this);
        bus.register(new EventHandler());
    }
    
    // 仅在客户端填充刀类物品
    private static void fillBladesForNamespace(CreativeModeTab.ItemDisplayParameters parameters, 
                                              CreativeModeTab.Output output, String namespace) {
        if (FMLEnvironment.dist != Dist.CLIENT) {
            return;
        }
        
        try {
            // 获取所有指定命名空间的刀
            SlashBlade.getRegistry().getValues().stream()
                .filter(b -> namespace.equals(b.getRegistryName().getNamespace()))
                .forEach(b -> {
                    ItemStack stack = new ItemStack(b);
                    output.accept(stack);
                });
        } catch (Exception e) {
            Srelic.LOGGER.error("Error filling blades for namespace: " + namespace, e);
        }
    }
    
    // 仅在客户端填充特殊效果物品
    private static void fillSEs(CreativeModeTab.Output output) {
        if (FMLEnvironment.dist != Dist.CLIENT) {
            return;
        }
        
        // 添加特殊效果物品
        output.accept(ModItems.empty_se.get());
        // 其他特殊效果物品...
    }
    
    // 仅在客户端填充第二组特殊效果物品
    private static void fillSEs2(CreativeModeTab.Output output) {
        if (FMLEnvironment.dist != Dist.CLIENT) {
            return;
        }
        
        // 添加第二组特殊效果物品
        output.accept(ModItems.empty_se2.get());
        // 其他第二组特殊效果物品...
    }
    
    // 添加日志记录器
    public static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MODID);
}
