package com.dinzeer.srelic.registry;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.dinzeer.srelic.Srelic.MODID;

public class SRPaintings {
    // 创建独立的注册器
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, MODID);

    // 注册画作变体
    public static final RegistryObject<PaintingVariant> gwen = registerPainting(
            "gwen", 64, 32);


    private static RegistryObject<PaintingVariant> registerPainting(String name, int width, int height) {
        return PAINTING_VARIANTS.register(name,
                () -> new PaintingVariant(width, height));
    }

    // 在主类初始化时调用
    public static void register(IEventBus bus) {
        PAINTING_VARIANTS.register(bus);
    }
}
