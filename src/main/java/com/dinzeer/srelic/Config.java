package com.dinzeer.srelic;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs

@Mod.EventBusSubscriber(modid = Srelic.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.IntValue THE_DREAM_DAMEGE = BUILDER
            .comment("Unintended one strike damage multiplier, default is 5 or 5 times, damage algorithm is: (get player attack value * increase/decrease item) * current configuration item")
            .comment("无想的一刀伤害倍率，默认为10即10倍，伤害算法为：（获取玩家攻击数值*增衰项）*当前配置项" )
            .defineInRange("the_dream_damage", 10, 1, Integer.MAX_VALUE);
    private static final ForgeConfigSpec.IntValue NEO_DRIVE_DAMAGE = BUILDER
            .comment("Phantom Blade Drive · Extreme Damage multiplier, default is 12 or 12 times, algorithm is: 20+(get player attack value * increase/decrease item)/2 * current configuration item")
            .comment("幻影刃驱动·极伤害倍率,默认为12即12倍，算法为：20+（获取玩家攻击数值*增衰项）/2*当前配置项" )
            .defineInRange("neo_drive_damage", 12, 1, Integer.MAX_VALUE);
    private static final ForgeConfigSpec.DoubleValue ATTENUATION = BUILDER
            .comment("Obtain the increase and decrease items of player attack values. If it is greater than 1, the increase will be greater than 1. If it is less than 1, the default decrease will be 0.25, which is a 75% decrease")
            .comment("获取玩家攻击数值增衰项,大于1即增加小于则衰减默认0.25，即玩家攻击力乘0.25" )
            .defineInRange("Attenuation_term", 0.25, 0.1, Double.MAX_VALUE);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int THE_DREAM_NUM;
    public static int neo_drive_NUM;
    public static double attenuation_term;


    private static boolean validateItemName(final Object obj) {
        return obj instanceof final String itemName && ForgeRegistries.ITEMS.containsKey(new ResourceLocation(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        THE_DREAM_NUM=THE_DREAM_DAMEGE.get();
         neo_drive_NUM=NEO_DRIVE_DAMAGE.get();
         attenuation_term=ATTENUATION.get();
    }
}
