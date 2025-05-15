package com.dinzeer.srelic.registry;

import com.dinzeer.srelic.registry.imp.IStackManager;
import com.dinzeer.srelic.registry.imp.RegisteredStackManager;

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


    public static IStackManager register(String name,int max){
      return RegisteredStackManager.Registry.register(name, max);
    }
}
