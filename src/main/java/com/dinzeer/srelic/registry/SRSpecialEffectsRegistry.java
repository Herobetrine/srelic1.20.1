package com.dinzeer.srelic.registry;

import com.dinzeer.srelic.specialeffects.*;
import com.dinzeer.srelic.specialeffects.ThePath.*;
import com.dinzeer.srelic.specialeffects.Lich;
import com.dinzeer.srelic.specialeffects.MaxBreak;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static com.dinzeer.srelic.Srelic.MODID;

public class SRSpecialEffectsRegistry {















    public static final DeferredRegister<SpecialEffect> REGISTRY_KEY2;//这玩意是最重要的
    public static final RegistryObject<SpecialEffect> FLAMEROSION;//火焰侵蚀
    public static final RegistryObject<SpecialEffect> MAXBREAK;//上限突破·救世光辉
    public static final RegistryObject<SpecialEffect> LICH;//上限突破·亡灵序曲
    public static final RegistryObject<SpecialEffect> WhiteMaker;//构造即此刻

    public static final RegistryObject<SpecialEffect> path_of_destruction;//命途·毁灭
    public static final RegistryObject<SpecialEffect> path_of_the_hunt;//命途·巡猎
    public static final RegistryObject<SpecialEffect> path_of_preservation;//命途·存护
    public static final RegistryObject<SpecialEffect> path_of_the_gluttony;//命途·贪饕
    public static final RegistryObject<SpecialEffect> path_of_the_undying;//命途·不朽
    public static final RegistryObject<SpecialEffect> path_of_abundance;//命途·丰饶
    public static final RegistryObject<SpecialEffect> path_of_remembrance;//命途·记忆
    public static final RegistryObject<SpecialEffect> path_of_harmony;//命途·同谐
    public static final RegistryObject<SpecialEffect> path_of_nihility;//命途·虚无
    public static final RegistryObject<SpecialEffect> path_of_propagation;//命途·繁育
    public static final RegistryObject<SpecialEffect> path_of_erudition;//命途·智识
    public static final RegistryObject<SpecialEffect> path_of_elation;//命途·欢愉
    public static final RegistryObject<SpecialEffect> path_of_equilibrium;//命途·均衡
    public static final RegistryObject<SpecialEffect> path_of_finality;//命途·终末
    public static final RegistryObject<SpecialEffect> path_of_trailblaze;//命途·开拓
    public static final RegistryObject<SpecialEffect> path_of_mystery;//命途·神秘
    public static final RegistryObject<SpecialEffect> path_of_order;//命途·秩序
    public static final RegistryObject<SpecialEffect> path_of_purity;//命途·纯美


    public SRSpecialEffectsRegistry() {
    }
    static {
        REGISTRY_KEY2 = DeferredRegister.create(SpecialEffect.REGISTRY_KEY, MODID);
        FLAMEROSION= REGISTRY_KEY2.register("flamerosion", Flameerosion::new);
        MAXBREAK= REGISTRY_KEY2.register("maxbreak", MaxBreak::new);
        LICH= REGISTRY_KEY2.register("lich", Lich::new);
        WhiteMaker= REGISTRY_KEY2.register("white_maker",WhiteMaker::new);
        path_of_destruction= REGISTRY_KEY2.register("path_of_destruction", PathOfDestruction::new);
        path_of_the_hunt= REGISTRY_KEY2.register("path_of_the_hunt", PathOfTheHunt::new);
        path_of_preservation= REGISTRY_KEY2.register("path_of_preservation", PathOfPreservation::new);
        path_of_the_gluttony= REGISTRY_KEY2.register("path_of_the_gluttony", PathOfTheGluttony::new);
        path_of_the_undying = REGISTRY_KEY2.register(
                "path_of_the_undying", PathOfTheUndying::new);
        path_of_abundance = REGISTRY_KEY2.register(
                "path_of_abundance", PathOfAbundance::new);
        path_of_remembrance = REGISTRY_KEY2.register(
                "path_of_remembrance", PathOfRemembrance::new);
        path_of_harmony = REGISTRY_KEY2.register(
                "path_of_harmony", PathOfHarmony::new);
        path_of_nihility = REGISTRY_KEY2.register(
                "path_of_nihility", PathOfNihility::new);
        path_of_propagation = REGISTRY_KEY2.register(
                "path_of_propagation", PathOfPropagation::new);
        path_of_erudition = REGISTRY_KEY2.register(
                "path_of_erudition", PathOfErudition::new);
        path_of_elation = REGISTRY_KEY2.register(
                "path_of_elation", PathOfElation::new);
        path_of_equilibrium = REGISTRY_KEY2.register(
                "path_of_equilibrium", PathOfEquilibrium::new);
        path_of_finality = REGISTRY_KEY2.register(
                "path_of_finality", PathOfFinality::new);
        path_of_trailblaze = REGISTRY_KEY2.register(
                "path_of_trailblaze", PathOfTrailblaze::new);
        path_of_mystery = REGISTRY_KEY2.register(
                "path_of_mystery", PathOfMystery::new);
        path_of_order = REGISTRY_KEY2.register(
                "path_of_order", PathOfOrder::new);
        path_of_purity = REGISTRY_KEY2.register(
                "path_of_purity", PathOfPurity::new);
    }






    //奖池集合
    public static final List<RegistryObject<SpecialEffect>> PATH_SE_POOL = List.of(
            path_of_destruction,
            path_of_the_hunt,
            path_of_preservation,
            path_of_the_gluttony,
            path_of_the_undying,
            path_of_abundance,
            path_of_remembrance,
            path_of_harmony,
            path_of_nihility,
            path_of_propagation,
            path_of_erudition,
            path_of_elation,
            path_of_equilibrium,
            path_of_finality,
            path_of_trailblaze,
            path_of_mystery,
            path_of_order,
            path_of_purity
    );
}
