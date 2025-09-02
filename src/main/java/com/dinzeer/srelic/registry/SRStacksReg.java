package com.dinzeer.srelic.registry;

import com.dinzeer.legendreliclib.lib.util.impl.IStackManager;
import com.dinzeer.legendreliclib.lib.util.impl.RegisteredStackManager;
import com.dinzeer.srelic.registry.imp.SRRegisteredStackManager;

import static com.dinzeer.srelic.Srelic.MODID;


public class SRStacksReg {
    public static final IStackManager BUTTERFLY_STACKS =register("butterfly_falling", 10);
    public static final IStackManager BLOOD_PLUM_STACKS =register("blood_plum", 30);
    public static final IStackManager BLAZING_VALOR_STACKS =register("blazing_valor", 15);
    public static final IStackManager PureElegy =register("pure_elegy", 300);
    public static final IStackManager WATER_SYMPHONY_STACKS =register("water_symphony", 5);
    public static final IStackManager LIANG_DIE_STACKS =register("liang_die", 10);
    public static final IStackManager LIANG_FENG_STACKS =register("liang_feng", 3);
    public static final IStackManager CUBE_MATRIX_STACKS =register("cube_matrix", 10);
    public static final IStackManager CRIMSON_SCAR_STACKS =register("crimson_scar", 10);
    public static final IStackManager NIGHTMARE_LAYER_STACKS = register("nightmare_layer", 6);
    public static final IStackManager RHYTHM_VALUE = register("rhythm_value", 50);
    public static final IStackManager ICE_LAYER_STACK = register("ice_layer", 20);
    public static final IStackManager FLAME_RESONANCE_STACKS =register("flame_resonance", 50);
    public static final IStackManager WHITE_ROSE_STACKS =register("white_rose", 15);
    public static final IStackManager FLY_YELLOW_STACKS =register("fly_yellow", 30);
    public static final IStackManager ICE_RHYTHM_STACKS = register("ice_rhythm", 15);
    public static final IStackManager OVERHEAT_VALUE_STACKS = register("overheat_value", 300);
    public static final IStackManager SECONDARY_COMBUSTION_STACKS = register("secondary_combustion", 1);
    public static final IStackManager RED_SCAR = register("red_scar", 12);
    public static final IStackManager SKY_SWORD = register("sky_sword", 5);
    public static final IStackManager CELESTIAL_STRIKE = register("celestial_strike", 100);
    public static final IStackManager ICE_BLOOM_STACKS = register("ice_bloom", 9);
    public static final IStackManager MING_MANG_STACKS = register("ming_mang", 10);
    public static final IStackManager ICE_SOUL_FROST_SKY_STACKS = register("ice_soul_frost_sky", 15);
    public static final IStackManager BITTER_COLD_HELL_STACKS = register("bitter_cold_hell", 10);
    public static final IStackManager BITTER_COLD_HELL_EX_STACKS = register("bitter_cold_hell_ex", 10);
    public static final IStackManager FROST_FLAME_STACKS = register("frost_flame", 6);
    public static final IStackManager HUNTER_TOXIN_STACKS = register("hunter_toxin", 10);
    public static final IStackManager SHRIN_FISH_STACKS=register("shrin_fish", 200);



    public static final IStackManager PETAL_STACKS = SRStacksReg.register("crimson_petal", 100);


    public static final IStackManager SAM_OVERDRIVE_STACKS = register("SamHeat", 300);
    public static final IStackManager CHAOS_BREAKER_STACKS = register("chaos_breaker", 3);




    public static final IStackManager SANCTUARY_SHIELD = register("sanctuary_shield", Integer.MAX_VALUE);




    // 新增方法：根据ID获取堆栈管理器
    public static IStackManager getStackManager(String id) {
        return RegisteredStackManager.Registry.get(id).orElse(null);
    }

    public static IStackManager register(String name, int max){
        return RegisteredStackManager.Registry.register(MODID+":"+name, max);
    }
}
