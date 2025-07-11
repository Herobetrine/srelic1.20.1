package com.dinzeer.srelic.registry;

import cn.mmf.slashblade_addon.registry.SBASpecialEffectsRegistry;
import com.dinzeer.srelic.specialeffects.*;
import com.dinzeer.srelic.specialeffects.ThePath.*;
import com.dinzeer.srelic.specialeffects.Lich;
import com.dinzeer.srelic.specialeffects.MaxBreak;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import com.dinzeer.srelic.specialeffects.superse.*;
import com.dinzeer.srelic.specialeffects.superSe2.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.dinzeer.srelic.Srelic.MODID;

public class SRSpecialEffectsRegistry {















    public static final DeferredRegister<SpecialEffect> REGISTRY_KEY2;//这玩意是最重要的
    public static final RegistryObject<SpecialEffect> FLAMEROSION;//火焰侵蚀
    public static final RegistryObject<SpecialEffect> MAXBREAK;//上限突破·救世光辉
    public static final RegistryObject<SpecialEffect> LICH;//上限突破·亡灵序曲
    public static final RegistryObject<SpecialEffect> WhiteMaker;//构造即此刻

    public static final RegistryObject<SpecialEffect> PATH_OF_DESTRUCTION;//命途·毁灭
    public static final RegistryObject<SpecialEffect> PATH_OF_THE_HUNT;//命途·巡猎
    public static final RegistryObject<SpecialEffect> PATH_OF_PRESERVATION;//命途·存护
    public static final RegistryObject<SpecialEffect> PATH_OF_THE_GLUTTONY;//命途·贪饕
    public static final RegistryObject<SpecialEffect> PATH_OF_THE_UNDYING;//命途·不朽
    public static final RegistryObject<SpecialEffect> PATH_OF_ABUNDANCE;//命途·丰饶
    public static final RegistryObject<SpecialEffect> PATH_OF_REMEMBRANCE;//命途·记忆
    public static final RegistryObject<SpecialEffect> PATH_OF_HARMONY;//命途·同谐
    public static final RegistryObject<SpecialEffect> PATH_OF_NIHILITY;//命途·虚无
    public static final RegistryObject<SpecialEffect> PATH_OF_PROPAGATION;//命途·繁育
    public static final RegistryObject<SpecialEffect> PATH_OF_ERUDITION;//命途·智识
    public static final RegistryObject<SpecialEffect> PATH_OF_ELATION;//命途·欢愉
    public static final RegistryObject<SpecialEffect> PATH_OF_EQUILIBRIUM;//命途·均衡
    public static final RegistryObject<SpecialEffect> PATH_OF_FINALITY;//命途·终末
    public static final RegistryObject<SpecialEffect> PATH_OF_TRAILBLAZE;//命途·开拓
    public static final RegistryObject<SpecialEffect> PATH_OF_MYSTERY;//命途·神秘
    public static final RegistryObject<SpecialEffect> PATH_OF_ORDER;//命途·秩序
    public static final RegistryObject<SpecialEffect> PATH_OF_PURITY;//命途·纯美
    public static final RegistryObject<SpecialEffect> PATH_OF_HEALING;//丰饶·慈悲

    public static final RegistryObject<SpecialEffect> VOID_FINALE;//虚无之终末
    public static final RegistryObject<SpecialEffect> LAMENT_OF_PHANTOMS;//幽魂的哀叹

    public static final RegistryObject<SpecialEffect> CELESTIAL_COLLAPSE;//规则击破
    public static final RegistryObject<SpecialEffect> BLAZING_HEART_SHIELD;//熔火护心
    public static final RegistryObject<SpecialEffect> WOLF_SOUL;//北风之魂
    public static final RegistryObject<SpecialEffect> THUNDERSTORM_SLASH;//电磁风暴
    public static final RegistryObject<SpecialEffect> SAM_OVERDRIVE;//过载·炽焰形态
    public static final RegistryObject<SpecialEffect> AQUATIC_SANCTUARY;//圣礼·水仙十字
    public static final RegistryObject<SpecialEffect> CHAOS_BREAKER;//七雷·雷殛连星
    public static final RegistryObject<SpecialEffect> RIAN_BOW_SHOOT;//夜魂·元素连射
    public static final RegistryObject<SpecialEffect> KAFKA_STRINGS;//蛛网·命运提线
    public static final RegistryObject<SpecialEffect> JINGYUAN_THUNDER;//神策·天霆断空
    public static final RegistryObject<SpecialEffect> NERO_DEVIL_BREAKER;//魔剑·绯红序曲
    public static final RegistryObject<SpecialEffect> HERTA_CRYO_STAGE;//「霜星·永冻剧场」
    public static final RegistryObject<SpecialEffect> WELT_SE;//虚数·时空奇点
    public static final RegistryObject<SpecialEffect> RAIDEN_SHOGUN;//永恒·雷电将军
    public static final RegistryObject<SpecialEffect> ETERNAL_FLOW;//千古·洑流潮涌
    public static final RegistryObject<SpecialEffect> AVENGER_JEANNE;//复仇者·贞德
    public static final RegistryObject<SpecialEffect> BLOOD_SAKURA;//魂念·血樱寂灭
    public static final RegistryObject<SpecialEffect> CRIMSON_MOON;//赤月·永夜巡礼
    public static final RegistryObject<SpecialEffect> INFERNO_BLAZE;//炎狱劫火·天烬
    public static final RegistryObject<SpecialEffect> DICE_EFFECT;//命运骰子
    public static final RegistryObject<SpecialEffect> TRUTH_REALM;//终焉律·星之归
    public static final RegistryObject<SpecialEffect> PERFECT_SANCTUARY;//真我·无瑕护佑
    public static final RegistryObject<SpecialEffect> FROST_FORGING;//寒气炼成
    public static final RegistryObject<SpecialEffect> GHOST_THUNDER;//百鬼噬魂·雷劫
    public static final RegistryObject<SpecialEffect> STAR_THUNDER;//星轨雷殛·天罚
    public static final RegistryObject<SpecialEffect> FLAME_EROSION;//炎鳞蚀骨·焚烬
    public static final RegistryObject<SpecialEffect> CRIMSON_ANNIHILATION;//红椿蚀骨·湮烬
    public static final RegistryObject<SpecialEffect> SAKURA_BLOOM;//樱华·刹那芳华
    public static final RegistryObject<SpecialEffect> STORM_FURY;//疾风·暴风之怒
    public static final RegistryObject<SpecialEffect> MING_MANG; //冥芒
    public static final RegistryObject<SpecialEffect> BUTTERFLY_FALLING; //蝶落冥世
    public static final RegistryObject<SpecialEffect> BLOOD_PLUM_MEMORY;//血梅之忆
    public static final RegistryObject<SpecialEffect> BLAZING_VALOR;//炽炎勇魄
    public static final RegistryObject<SpecialEffect> PureElegy;//纯美赞歌
    public static final RegistryObject<SpecialEffect> WATER_SYMPHONY;//纵水交响曲
    public static final RegistryObject<SpecialEffect> QUANTUM_DANCE;//量镰影舞
    public static final RegistryObject<SpecialEffect> VOID_SCISSORS;//空境紫剪
    public static final RegistryObject<SpecialEffect> NIGHTMARE_ABYSS;//魇梦渊卷之夜
    public static final RegistryObject<SpecialEffect> MUGEN_ICE;//无我·极冰演算
    public static final RegistryObject<SpecialEffect> PLEDGE_MATRIX;//誓约矩阵
    public static final RegistryObject<SpecialEffect> WHITE_ROSE;//白夜蔷薇
    public static final RegistryObject<SpecialEffect> STORM_BODY;//风岚之身
    public static final RegistryObject<SpecialEffect> ICE_RHYTHM ;
    public static final RegistryObject<SpecialEffect> FIRE_FLY;
    public static final RegistryObject<SpecialEffect> RED_SCAR;
    public static final RegistryObject<SpecialEffect> Elemental_Explosion;
    public static final RegistryObject<SpecialEffect> VACUUM_BLADE;
    public static final RegistryObject<SpecialEffect> CELESTIAL_STRIKE;
    public static final RegistryObject<SpecialEffect> INFERNO_THOUSAND_TRIALS; // 炎烬千劫
    public static final RegistryObject<SpecialEffect> ICE_BLADE;
    public static final RegistryObject<SpecialEffect> ICE_BLOOM;
    public static final RegistryObject<SpecialEffect> HOLY_ENERGY_OVERFLOW;
    public static final RegistryObject<SpecialEffect> DREAM_COMPANION;
    public static final RegistryObject<SpecialEffect> BITTER_COLD_HELL;
    public static final RegistryObject<SpecialEffect> BITTER_COLD_HELL_EX;
    public static final RegistryObject<SpecialEffect> AFFLORDITE;
    public static final RegistryObject<SpecialEffect> FROST_FLAME;
    // 新增冰魄·寒月霜天特效
    public static final RegistryObject<SpecialEffect> ICE_SOUL_FROST_SKY;
    // 新增追猎者特效
    public static final RegistryObject<SpecialEffect> HUNTER;

    public SRSpecialEffectsRegistry() {





    }
    public static void SRadd() {


        if (ModList.get().isLoaded("slashblade_addon")){
            PATH_SE_POOL.add(SBASpecialEffectsRegistry.BURST_DRIVE);
        }

    }
    static {
        REGISTRY_KEY2 = DeferredRegister.create(SpecialEffect.REGISTRY_KEY, MODID);
        FLAMEROSION= REGISTRY_KEY2.register("flamerosion", Flameerosion::new);
        MAXBREAK= REGISTRY_KEY2.register("maxbreak", MaxBreak::new);
        LICH= REGISTRY_KEY2.register("lich", Lich::new);
        WhiteMaker= REGISTRY_KEY2.register("white_maker",WhiteMaker::new);
        PATH_OF_DESTRUCTION = REGISTRY_KEY2.register("path_of_destruction", PathOfDestruction::new);
        PATH_OF_THE_HUNT = REGISTRY_KEY2.register("path_of_the_hunt", PathOfTheHunt::new);
        PATH_OF_PRESERVATION = REGISTRY_KEY2.register("path_of_preservation", PathOfPreservation::new);
        PATH_OF_THE_GLUTTONY = REGISTRY_KEY2.register("path_of_the_gluttony", PathOfTheGluttony::new);
        PATH_OF_THE_UNDYING = REGISTRY_KEY2.register(
                "path_of_the_undying", PathOfTheUndying::new);
        PATH_OF_ABUNDANCE = REGISTRY_KEY2.register(
                "path_of_abundance", PathOfAbundance::new);
        PATH_OF_REMEMBRANCE = REGISTRY_KEY2.register(
                "path_of_remembrance", PathOfRemembrance::new);
        PATH_OF_HARMONY = REGISTRY_KEY2.register(
                "path_of_harmony", PathOfHarmony::new);
        PATH_OF_NIHILITY = REGISTRY_KEY2.register(
                "path_of_nihility", PathOfNihility::new);
        PATH_OF_PROPAGATION = REGISTRY_KEY2.register(
                "path_of_propagation", PathOfPropagation::new);
        PATH_OF_ERUDITION = REGISTRY_KEY2.register(
                "path_of_erudition", PathOfErudition::new);
        PATH_OF_ELATION = REGISTRY_KEY2.register(
                "path_of_elation", PathOfElation::new);
        PATH_OF_EQUILIBRIUM = REGISTRY_KEY2.register(
                "path_of_equilibrium", PathOfEquilibrium::new);
        PATH_OF_FINALITY = REGISTRY_KEY2.register(
                "path_of_finality", PathOfFinality::new);
        PATH_OF_TRAILBLAZE = REGISTRY_KEY2.register(
                "path_of_trailblaze", PathOfTrailblaze::new);
        PATH_OF_MYSTERY = REGISTRY_KEY2.register(
                "path_of_mystery", PathOfMystery::new);
        PATH_OF_ORDER = REGISTRY_KEY2.register(
                "path_of_order", PathOfOrder::new);
        PATH_OF_PURITY = REGISTRY_KEY2.register(
                "path_of_purity", PathOfPurity::new);
        PATH_OF_HEALING = REGISTRY_KEY2.register("path_of_healing", PathOfHealing::new);
        VOID_FINALE = REGISTRY_KEY2.register("void_finale", VoidFinale::new);
        LAMENT_OF_PHANTOMS = REGISTRY_KEY2.register("lament_of_phantoms", LamentOfPhantoms::new);

        CELESTIAL_COLLAPSE = REGISTRY_KEY2.register("celestial_collapse", CelestialCollapse::new);
        BLAZING_HEART_SHIELD = REGISTRY_KEY2.register("blazing_heart_shield", BlazingHeartShield::new);
        WOLF_SOUL = REGISTRY_KEY2.register("wolf_soul", WolfSoul::new);
        THUNDERSTORM_SLASH = REGISTRY_KEY2.register("thunderstorm_slash", ThunderstormSlash::new);
        SAM_OVERDRIVE = REGISTRY_KEY2.register("sam_overdrive", SamOverdrive::new);
        AQUATIC_SANCTUARY = REGISTRY_KEY2.register("aquatic_sanctuary", AquaticSanctuary::new);
        CHAOS_BREAKER = REGISTRY_KEY2.register("chaos_breaker", ChaosBreaker::new);
        RIAN_BOW_SHOOT = REGISTRY_KEY2.register("rianbowshoot", RianBowShoot::new);
        KAFKA_STRINGS = REGISTRY_KEY2.register("kafka_strings", KafkaStrings::new);
        JINGYUAN_THUNDER = REGISTRY_KEY2.register("jingyuan_thunder", JingyuanThunder::new);
        NERO_DEVIL_BREAKER = REGISTRY_KEY2.register("nero_devil_breaker", NeroDevilBreaker::new);
        HERTA_CRYO_STAGE = REGISTRY_KEY2.register("herta_cryo_stage", HertaCryoStage::new);
        WELT_SE = REGISTRY_KEY2.register("welt_se", WeltSE::new);
        RAIDEN_SHOGUN = REGISTRY_KEY2.register("raiden_shogun", RaidenShogunSE::new);
        ETERNAL_FLOW = REGISTRY_KEY2.register("eternal_flow", EternalFlowSE::new);
        AVENGER_JEANNE = REGISTRY_KEY2.register("avenger_jeanne", AvengerJeanneSE::new);
        BLOOD_SAKURA = REGISTRY_KEY2.register("blood_sakura", BloodSakuraSE::new);
        CRIMSON_MOON = REGISTRY_KEY2.register("crimson_moon", CrimsonMoonSE::new);
        INFERNO_BLAZE = REGISTRY_KEY2.register("inferno_blaze", InfernoBlazeSE::new);
        DICE_EFFECT = REGISTRY_KEY2.register("dice_effect", DiceEffect::new);
        TRUTH_REALM = REGISTRY_KEY2.register("truth_realm", TruthRealmSE::new);
        PERFECT_SANCTUARY = REGISTRY_KEY2.register("perfect_sanctuary", PerfectSanctuarySE::new);
        FROST_FORGING = REGISTRY_KEY2.register("frost_forging", FrostForgingSE::new);
        GHOST_THUNDER = REGISTRY_KEY2.register("ghost_thunder", GhostThunderSE::new);
        STAR_THUNDER = REGISTRY_KEY2.register("star_thunder", StarThunderSE::new);
        FLAME_EROSION = REGISTRY_KEY2.register("flame_erosion", FlameErosionSE::new);
        CRIMSON_ANNIHILATION = REGISTRY_KEY2.register("crimson_annihilation", CrimsonAnnihilationSE::new);
        SAKURA_BLOOM = REGISTRY_KEY2.register("sakura_bloom", SakuraBloomSE::new);
        STORM_FURY = REGISTRY_KEY2.register("storm_fury", StormFurySE::new);
        MING_MANG = REGISTRY_KEY2.register("ming_mang", MingMangSE::new);
        BUTTERFLY_FALLING = REGISTRY_KEY2.register("butterfly_falling", ButterflyFalling::new);
        BLOOD_PLUM_MEMORY = REGISTRY_KEY2.register("blood_plum_memory", BloodPlumMemory::new);
        BLAZING_VALOR = REGISTRY_KEY2.register("blazing_valor", BlazingValor::new);
        PureElegy = REGISTRY_KEY2.register("pure_elegy", PureElegy::new);
        WATER_SYMPHONY = REGISTRY_KEY2.register("water_symphony", WaterSymphonySE::new);
        QUANTUM_DANCE = REGISTRY_KEY2.register("quantum_dance", QuantumDanceSE::new);
        VOID_SCISSORS = REGISTRY_KEY2.register("void_scissors", VoidScissorsSE::new);
        NIGHTMARE_ABYSS = REGISTRY_KEY2.register("nightmare_abyss", NightmareAbyssSE::new);
        MUGEN_ICE = REGISTRY_KEY2.register("mugen_ice", MugenIceSE::new);
        PLEDGE_MATRIX = REGISTRY_KEY2.register("pledge_matrix", PledgeMatrixSE::new);
        WHITE_ROSE = REGISTRY_KEY2.register("white_rose", WhiteRoseSE::new);
        STORM_BODY = REGISTRY_KEY2.register("storm_body", StormBodySE::new);
        ICE_RHYTHM= REGISTRY_KEY2.register("ice_rhythm", IceRhythmSE::new);
        FIRE_FLY= REGISTRY_KEY2.register("fire_fly", FireFly::new);
        RED_SCAR=  REGISTRY_KEY2.register("red_scar", RedScar::new);
        Elemental_Explosion=REGISTRY_KEY2.register("elemental_explosion", ElementalExplosion::new);
        VACUUM_BLADE=REGISTRY_KEY2.register("vacuum_blade", VacuumBlade::new);
        CELESTIAL_STRIKE=REGISTRY_KEY2.register("celestial_strike", CelestialStrike::new);
        INFERNO_THOUSAND_TRIALS = REGISTRY_KEY2.register("inferno_thousand_trials", InfernoThousandTrials::new);
        ICE_BLADE=REGISTRY_KEY2.register("ice_blade", IceBlade::new);
        HOLY_ENERGY_OVERFLOW = REGISTRY_KEY2.register("holy_energy_overflow", HolyEnergyOverflow::new);
        DREAM_COMPANION = REGISTRY_KEY2.register("dream_companion", DreamCompanion::new);
        BITTER_COLD_HELL = REGISTRY_KEY2.register("bitter_cold_hell", BitterColdHell::new);
        BITTER_COLD_HELL_EX = REGISTRY_KEY2.register("bitter_cold_hell_ex", BitterColdHellEX::new);
        // 注册风花霜月·寒炎特效
        FROST_FLAME = REGISTRY_KEY2.register("frost_flame", FrostFlame::new);
        // 注册冰华特效
        ICE_BLOOM = REGISTRY_KEY2.register("ice_bloom", IceBloom::new);
        AFFLORDITE=REGISTRY_KEY2.register("afflordite", Afflordite::new);
        // 注册冰魄·寒月霜天特效
        ICE_SOUL_FROST_SKY = REGISTRY_KEY2.register("ice_soul_frost_sky", IceSoulFrostSky::new);
        // 注册追猎者特效
        HUNTER = REGISTRY_KEY2.register("hunter", Hunter::new);
    }







    public static final List<RegistryObject<SpecialEffect>> PATH_SE_POOL = new ArrayList<>(Arrays.asList(
            PATH_OF_DESTRUCTION,
            PATH_OF_THE_HUNT,
            PATH_OF_PRESERVATION,
            PATH_OF_THE_GLUTTONY,
            PATH_OF_THE_UNDYING,
            PATH_OF_ABUNDANCE,
            PATH_OF_REMEMBRANCE,
            PATH_OF_HARMONY,
            PATH_OF_NIHILITY,
            PATH_OF_PROPAGATION,
            PATH_OF_ERUDITION,
            PATH_OF_ELATION,
            PATH_OF_EQUILIBRIUM,
            PATH_OF_FINALITY,
            PATH_OF_TRAILBLAZE,
            PATH_OF_MYSTERY,
            PATH_OF_ORDER,
            PATH_OF_PURITY,
            PATH_OF_HEALING,
            DICE_EFFECT
    ));
}
