package com.dinzeer.srelic.data;

import com.dinzeer.srelic.Srelic;
import com.dinzeer.srelic.blade.SRItem;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.registry.SRslashArtRegsitry;
import mods.flammpfeil.slashblade.client.renderer.CarryType;
import mods.flammpfeil.slashblade.item.SwordType;
import mods.flammpfeil.slashblade.registry.SlashArtsRegistry;
import mods.flammpfeil.slashblade.registry.SpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.slashblade.EnchantmentDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.PropertiesDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.RenderDefinition;
import mods.flammpfeil.slashblade.registry.slashblade.SlashBladeDefinition;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class SRelicBuiltInRegsitry {
    //始
    public static final ResourceKey<SlashBladeDefinition> START=register("none_blue");
    //无
    public static final ResourceKey<SlashBladeDefinition> NONE=register("none_red");
    //夜煌皮肤
    public static final ResourceKey<SlashBladeDefinition> YE_TACHI_FIRE=register("ye_taichi");
    //夜煌原皮
    public static final ResourceKey<SlashBladeDefinition> YE_TACHI=register("ye_tachi");
    //狼的末路
    public static final ResourceKey<SlashBladeDefinition> WOLF=register("the_wolf");
    //天火圣裁
    public static final ResourceKey<SlashBladeDefinition> FIRE=register("the_fire");
    //劫灭
    public static final ResourceKey<SlashBladeDefinition> ARK_FIRE=register("ark_fire");
    //终焉劫灭
    public static final ResourceKey<SlashBladeDefinition> END_FIRE=register("end_fire");
    //水仙十字之剑
    public static final ResourceKey<SlashBladeDefinition> WATER=register("the_water");
    //霜之哀伤
    public static final ResourceKey<SlashBladeDefinition> ICECRY=register("ice_cry");
    //卡夫卡的刀
    public static final ResourceKey<SlashBladeDefinition> KAFUKA=register("kafuka");
    //星辰王牌
    public static final ResourceKey<SlashBladeDefinition> STAR=register("star");
    //炎枪
    public static final ResourceKey<SlashBladeDefinition> FIRE_KNIGHT=register("fire_knight");
    //千古伏流
    public static final ResourceKey<SlashBladeDefinition> ETERNAL_VOWS=register("eternal_vows");
    //夜煌皮肤·灼魍
    public static final ResourceKey<SlashBladeDefinition> YE_FIRE=register("ye_fire");
    //夜煌皮肤·星霆
    public static final ResourceKey<SlashBladeDefinition> YE_STAR=register("ye_star");
    //绯红女皇
    public static final ResourceKey<SlashBladeDefinition> RED_QUEEN=register("red_queen");
    //梦想一心
    public static final ResourceKey<SlashBladeDefinition> RAIDIAN=register("raiden");
    //乱破武器
    public static final ResourceKey<SlashBladeDefinition> RAPPA=register("rappa");
    //可塑性白模
    public static final ResourceKey<SlashBladeDefinition> BLADE=register("blade");
    //老铁
    public static final ResourceKey<SlashBladeDefinition> LAOTIE=register("yunli");
    //景元
        public static final ResourceKey<SlashBladeDefinition> JINYUAN=register("jinyuan");
    //老杨
    public static final ResourceKey<SlashBladeDefinition> idanstar=register("idanstar");
    //黑塔
    public static final ResourceKey<SlashBladeDefinition> heita=register("heita");
    // 赤月之形
    public static final ResourceKey<SlashBladeDefinition> redmoon=register("redmoon");
    //夜魂左轮
    public static final ResourceKey<SlashBladeDefinition> sika_gun=register("sika_gun");
    //孤云
    public static final ResourceKey<SlashBladeDefinition> clound=register("clound");
    //萨姆的变身器
    public static final ResourceKey<SlashBladeDefinition> samu=register("samu");
    //涤罪七雷
    public static final ResourceKey<SlashBladeDefinition> lightning=register("lightning");
    //天火圣裁·蓝
    public static final ResourceKey<SlashBladeDefinition> FIREBlue=register("the_fire_bule" );
    //裁春
    public static final ResourceKey<SlashBladeDefinition> spring=register("spring");
    //天火圣裁「寒气炼成」
    public static final ResourceKey<SlashBladeDefinition> FIREBlue2=register("the_fire_bule2" );
    //天火圣裁「命运投掷」
    public static final ResourceKey<SlashBladeDefinition> the_fire_gray=register("the_fire_gray" );
    //天火圣裁「暴风之眼」
    public static final ResourceKey<SlashBladeDefinition> the_fire_green2=register("the_fire_green2" );
    //天火圣裁「樱落刹那」
    public static final ResourceKey<SlashBladeDefinition> the_fire_pink2=register("the_fire_pink2" );
    //爱莉的弓
    public static final ResourceKey<SlashBladeDefinition> el=register("el");
    //魂妖刀「血樱寂灭」
    public static final ResourceKey<SlashBladeDefinition> redsakura=register("redsakura");
    //磁暴斩
    public static final ResourceKey<SlashBladeDefinition> galaxy=register("galaxy");
    //圣卡特琳娜「Alter」
    public static final ResourceKey<SlashBladeDefinition> santa_caterina=register("santa_caterina");
    //冥镰「蝶哀」
    public static final ResourceKey<SlashBladeDefinition> ButterflyLament=register("butterfly_lament");
    //长枪「护摩之杖」
    public static final ResourceKey<SlashBladeDefinition> staff_of_homa=register("staff_of_homa");
    //誓焰「拂晓炎翎」
    public static final ResourceKey<SlashBladeDefinition> dawn_flame_feather=register("dawn_flame_feather");
    //纯美「玫瑰长枪」
    public static final ResourceKey<SlashBladeDefinition> rose_spear=register("rose_spear");
    //静水流涌之辉
    public static final ResourceKey<SlashBladeDefinition> The_radiance_of_stagnant_water_flow=register("the_radiance_of_stagnant_water_flow");
    //旧镰「蝶影」
    public static final ResourceKey<SlashBladeDefinition> SEELE=register("seele");
    //紫剪「空无」
    public static final ResourceKey<SlashBladeDefinition> AIR_NONE =register("air_none");
    //血渊之眸「如一」
    public static final ResourceKey<SlashBladeDefinition> Abyss_Eye=register("abyss_eye");
    //奥菲厄斯的摇篮
    public static final ResourceKey<SlashBladeDefinition> Aphrodite_Lyre=register("aphrodite_lyre");
    //「赫刀」
    public static final ResourceKey<SlashBladeDefinition> conspicuous=register("conspicuous");
    //「绯羽灼华」
    public static final ResourceKey<SlashBladeDefinition> radiance=register("radiance");
    //战戟「钺贯天冲」
    public static final ResourceKey<SlashBladeDefinition> dash=register("dash");
    //天空之刃
    public static final ResourceKey<SlashBladeDefinition> sky_sword=register("sky_sword");
    //天空之傲
    public static final ResourceKey<SlashBladeDefinition> sky_sword_ascent=register("sky_sword_ascent");
    //隧星盛耀「剑盾」
    public static final ResourceKey<SlashBladeDefinition> SEVEN_1 =register("seven_1");
    //隧星盛耀「盾斧」
    public static final ResourceKey<SlashBladeDefinition> SEVEN_2 =register("seven_2");
    //劫灭「无烬」
    public static final ResourceKey<SlashBladeDefinition> kill=register("kill");
    //雪葬的星荧
    public static final ResourceKey<SlashBladeDefinition> ice_sword=register("ice_sword");
    //3rd圣遗物
    public static final ResourceKey<SlashBladeDefinition> THIRD_RELIC=register("third_relic");
    //霜狙
    public static final ResourceKey<SlashBladeDefinition> FROST_SNIPER=register("frost_sniper");
    //仿灵刀「冰昙天」
    public static final ResourceKey<SlashBladeDefinition> ICE_BLUE=register("ice_blue");
    //御灵刀「寒狱冰天」
    public static final ResourceKey<SlashBladeDefinition> ICE_BLUE_EX=register("ice_blue_ex");
    //「昙华剑」
    public static final ResourceKey<SlashBladeDefinition> THIRD_BLUE_1=register("third_blue_1");
    //转魄「昙华剑」
    public static final ResourceKey<SlashBladeDefinition> THIRD_BLUE_2=register("third_blue_2");
    //「阿芙洛狄忒」
    public static final ResourceKey<SlashBladeDefinition> AFFLORDITE=register("afflordite");
    //裁星刃「双鱼座」
    public static final ResourceKey<SlashBladeDefinition> DOUBLE_FISH=register("double_fish");
    //妖刀「无尾」
    public static final ResourceKey<SlashBladeDefinition> NO_END=register("no_end");
    //天霜之斯卡蒂
    public static final ResourceKey<SlashBladeDefinition> THE_STAR_OF_THE_FROST=register("the_star_of_the_frost");
    //基因收割者
    public static final ResourceKey<SlashBladeDefinition> GENE_HARVESTER=register("gene_harvester");
    public static void registerAll(BootstapContext<SlashBladeDefinition> bootstrap) {
        bootstrap.register(START,
                new SlashBladeDefinition(
                        SRItem.SRELIC_BLADE_ID,
                        Srelic.prefix("none_blue"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/stairail/none_blue.png"))
                                .modelName(Srelic.prefix("model/stairail/none.obj"))
                                .effectColor(2003199)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(20)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(SlashArtsRegistry.VOID_SLASH.getId())
                                .maxDamage(200)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.PATH_OF_NIHILITY.getId())
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)



                        )));
        bootstrap.register(NONE,
                new SlashBladeDefinition(
                        SRItem.SRELIC_BLADE_ID,Srelic.prefix("none_red"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/stairail/none_red.jpg"))
                                .modelName(Srelic.prefix("model/stairail/none.obj"))
                                .effectColor(13504014)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(40)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(SRslashArtRegsitry.FORDEADCRY.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.LAMENT_OF_PHANTOMS.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.VOID_FINALE.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 9),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 7),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SWEEPING_EDGE), 4),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MENDING), 1))));
        bootstrap.register(YE_TACHI_FIRE,
                new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("ye_taichi"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/ornament/ye_taichi.png"))
                                .modelName(Srelic.prefix("model/ornament/ye_taichi.obj"))
                                .effectColor(13504014)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(25)
                                .slashArtsType(SlashArtsRegistry.WAVE_EDGE.getId())
                                .maxDamage(200)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.FLAME_EROSION.getId())
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 4),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING),3)

                        )));
        bootstrap.register(YE_TACHI,
                new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("ye_tachi"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/ornament/ye_tachi_v.jpg"))
                                .modelName(Srelic.prefix("model/ornament/ye_tachi_v.obj"))
                                .effectColor(13504014)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(25)
                                .slashArtsType(SlashArtsRegistry.WAVE_EDGE.getId())
                                .maxDamage(200)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING),1)
                        )));
        bootstrap.register(FIRE,
                new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("the_fire"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/the_fire.jpg"))
                                .modelName(Srelic.prefix("model/honkai/the_fire.obj"))
                                .effectColor(16760832)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(18)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(SlashArtsRegistry.WAVE_EDGE.getId())
                                .maxDamage(200)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.FLAMEROSION.getId())
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)
                        )));
        bootstrap.register(WOLF,
                new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("the_wolf"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/genshine/the_wolf.png"))
                                .modelName(Srelic.prefix("model/genshine/the_wolf.obj"))
                                .effectColor(12779520)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(40)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WOLF_SOUL.getId())
                                .slashArtsType(SRslashArtRegsitry.BIG_DRIVE.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 1)

                        )));


        bootstrap.register(ARK_FIRE,
                new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("ark_fire"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/ark_fire.png"))
                                .modelName(Srelic.prefix("model/honkai/ark_fire.obj"))
                                .effectColor(16760832)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(20)
                                .slashArtsType(SRslashArtRegsitry.XDRIVE.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.FLAMEROSION.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 2)


                        )));

        bootstrap.register(END_FIRE,
                new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("end_fire"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/end_fire.png"))
                                .modelName(Srelic.prefix("model/honkai/end_fire.obj"))
                                .effectColor(16760832)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(40)
                                .slashArtsType(SRslashArtRegsitry.NEODRIVE.getId())
                                .maxDamage(200)
                                .addSpecialEffect(SRSpecialEffectsRegistry.FLAMEROSION.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.MAXBREAK.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FALL_PROTECTION), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_PROTECTION), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 5))));

        bootstrap.register(WATER,
                new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("the_water"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/genshine/sword_of_narzissenkreuz.png"))
                                .modelName(Srelic.prefix("model/genshine/the_water.obj"))
                                .effectColor(2003199)
                                .standbyRenderType(CarryType.KATANA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(23)
                                .slashArtsType(SlashArtsRegistry.WAVE_EDGE.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.AQUATIC_SANCTUARY.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FALL_PROTECTION), 5)


                        )));

        bootstrap.register(ICECRY,
                new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("ice_cry"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/ow/ice_cry.png"))
                                .modelName(Srelic.prefix("model/ow/frostmourne.obj"))
                                .effectColor(2003199)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(25)
                                .addSpecialEffect(SpecialEffectsRegistry.WITHER_EDGE.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.LICH.getId())
                                .slashArtsType(SRslashArtRegsitry.WitherBreaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)

                        )));

        bootstrap.register(KAFUKA,
                new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("kafuka"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/stairail/kafuka.png"))
                                .modelName(Srelic.prefix("model/stairail/kafuka.obj"))
                                .effectColor(14947317)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(23)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .slashArtsType(SRslashArtRegsitry.BIG_DRIVEF.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.PATH_OF_FINALITY.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.KAFKA_STRINGS.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)
                        )));


        bootstrap.register(STAR,
                new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("star"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/stairail/star.png"))
                                .modelName(Srelic.prefix("model/stairail/star.obj"))
                                .effectColor(2003199)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.CELESTIAL_COLLAPSE.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.PATH_OF_DESTRUCTION.getId())
                                .baseAttackModifier(21)
                                .slashArtsType(SRslashArtRegsitry.HOMERUN.getId())

                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 5)

                        )));
        bootstrap.register(FIRE_KNIGHT,
                new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("fire_knight"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/stairail/fire_knight.png"))
                                .modelName(Srelic.prefix("model/stairail/fire_knight.obj"))
                                .effectColor(16760832)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.BLAZING_HEART_SHIELD.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.PATH_OF_PRESERVATION.getId())
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(23)
                                .slashArtsType(SlashArtsRegistry.PIERCING.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 5)
                        )));

        bootstrap.register(ETERNAL_VOWS,
                new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("eternal_vows"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/wuwa/eternal_vows.png"))
                                .modelName(Srelic.prefix("model/wuwa/older.obj"))
                                .effectColor(2003199)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(30)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .slashArtsType(SRslashArtRegsitry.EXPLANATION.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.ETERNAL_FLOW.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )));

        bootstrap.register(YE_FIRE,
                new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("ye_fire"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/ornament/ye_fire.png"))
                                .modelName(Srelic.prefix("model/ornament/ye_fire.obj"))
                                .effectColor(16760832)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(24)
                                .slashArtsType(SlashArtsRegistry.SAKURA_END.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.GHOST_THUNDER.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 5)

                        )));
        bootstrap.register(YE_STAR,
                new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("ye_star"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/ornament/ye_star.png"))
                                .modelName(Srelic.prefix("model/ornament/ye_star.obj"))
                                .effectColor(2003199)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(32)
                                .slashArtsType(SRslashArtRegsitry.BIG_DRIVEF.getId())
                                .maxDamage(200)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.STAR_THUNDER.getId())
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10))));
        bootstrap.register(RED_QUEEN,
                new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("red_queen"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/dmc/red_queen.png"))
                                .modelName(Srelic.prefix("model/dmc/red_queen.obj"))
                                .effectColor(16711697)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(27)
                                .slashArtsType(SRslashArtRegsitry.XDRIVE.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.NERO_DEVIL_BREAKER.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 3)

                        )));
        bootstrap.register(RAIDIAN,
                new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("raiden"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/genshine/raiden.png"))
                                .modelName(Srelic.prefix("model/genshine/raiden.obj"))
                                .effectColor(8454388)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(40)
                                .slashArtsType(SRslashArtRegsitry.THO.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.RAIDEN_SHOGUN.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3),
                           new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 3),
                        new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                         new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 3)



                        )));
        bootstrap.register(RAPPA,
                new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("rappa"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/util/rappa.png"))
                                .modelName(Srelic.prefix("model/stairail/rappa.obj"))
                                .effectColor(16711697)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(26)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .slashArtsType(SRslashArtRegsitry.Rappa.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 1)


                        )));
        bootstrap.register(
                    BLADE, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("blade"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/blade/blade.png"))
                                .modelName(Srelic.prefix("model/blade/blade.obj"))
                                .standbyRenderType(CarryType.NINJA)
                                .effectColor(16777215)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(10)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .maxDamage(200)
                                .build(),
                        List.of()
                        ));
        bootstrap.register(
                LAOTIE, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("laotie"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/stairail/yunli.png"))
                                .modelName(Srelic.prefix("model/stairail/yunli.obj"))
                                .standbyRenderType(CarryType.NINJA)
                                .effectColor(16711697)

                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(30)
                                .maxDamage(200)
                                .slashArtsType(SRslashArtRegsitry.YUNLI.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                          new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )
                ));
        bootstrap.register(
                JINYUAN, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("jinyuan"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/stairail/jinyuan.png"))
                                .modelName(Srelic.prefix("model/stairail/jinyuan.obj"))
                                .effectColor(14665221)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(22)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.PATH_OF_THE_HUNT.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.JINGYUAN_THUNDER.getId())
                                .slashArtsType(SRslashArtRegsitry.BIGSLASH.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                )));
        bootstrap.register(
                idanstar, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("idanstar"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/stairail/idanstar.png"))
                                .modelName(Srelic.prefix("model/stairail/idanstar.obj"))
                                .effectColor(16711697)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(22)
                                .slashArtsType(SRslashArtRegsitry.HQUAN.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.PATH_OF_TRAILBLAZE.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WELT_SE.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )));
        bootstrap.register(
                heita, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("heita"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/stairail/heita.png"))
                                .modelName(Srelic.prefix("model/stairail/heita.obj"))
                                .effectColor(4456703)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(25)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.PATH_OF_ERUDITION.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.HERTA_CRYO_STAGE.getId())
                                .slashArtsType(SRslashArtRegsitry.Heita.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )));
        bootstrap.register(
                redmoon, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("redmoon"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/genshine/redmoon.png"))
                                .modelName(Srelic.prefix("model/genshine/redmoon.obj"))
                                .effectColor(16711697)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(30)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.CRIMSON_MOON.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )));
        bootstrap.register(
                sika_gun, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("sika_gun"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/genshine/sika_gun.png"))
                                .modelName(Srelic.prefix("model/genshine/sika_gun.obj"))
                                .effectColor(16711697)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(17)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.RIAN_BOW_SHOOT.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )));
        bootstrap.register(
                clound, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("clound"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/genshine/clound.png"))
                                .modelName(Srelic.prefix("model/genshine/clound.obj"))
                                .effectColor(16711697)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(17)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )));
        bootstrap.register(
                samu, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("samu"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/stairail/samu.png"))
                                .modelName(Srelic.prefix("model/stairail/samu.obj"))
                                .effectColor(65500)
                                .standbyRenderType(CarryType.KATANA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(20)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.PATH_OF_FINALITY.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.FIRE_FLY.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 7),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )));
        bootstrap.register(
                lightning, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("lightning"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/lightning.png"))
                                .modelName(Srelic.prefix("model/honkai/lightning.obj"))
                                .effectColor(11862271)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(25)
                                .slashArtsType(SRslashArtRegsitry.SummoningThunderNi.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.CHAOS_BREAKER.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )));
        bootstrap.register(
                FIREBlue, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("fireblue"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/the_fire_blue.png"))
                                .modelName(Srelic.prefix("model/honkai/the_fire.obj"))
                                .effectColor(16711697)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(35)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )));
        bootstrap.register(
                spring, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("spring"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/wuwa/spring.png"))
                                .modelName(Srelic.prefix("model/wuwa/spring.obj"))
                                .effectColor(16711697)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(25)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.CRIMSON_ANNIHILATION.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )));
        bootstrap.register(
                FIREBlue2, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("fireblue2"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/thefireextra/the_fire_blue2.png"))
                                .modelName(Srelic.prefix("model/honkai/the_fire.obj"))
                                .effectColor(57087)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(20)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.FROST_FORGING.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )));
        bootstrap.register(
                the_fire_gray, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("the_fire_gray"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/thefireextra/the_fire_gray.png"))
                                .modelName(Srelic.prefix("model/honkai/the_fire.obj"))
                                .effectColor(0)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(40)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.DICE_EFFECT.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )));

        bootstrap.register(
                the_fire_green2, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("the_fire_green2"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/thefireextra/the_fire_green2.png"))
                                .modelName(Srelic.prefix("model/honkai/the_fire.obj"))
                                .effectColor(16711697)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(20)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.STORM_FURY.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )));
        bootstrap.register(
                the_fire_pink2, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("the_fire_pink2"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/thefireextra/the_fire_pink2.png"))
                                .modelName(Srelic.prefix("model/honkai/the_fire.obj"))
                                .effectColor(16711697)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(35)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.SAKURA_BLOOM.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )));

        bootstrap.register(
                el, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("el"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/el.png"))
                                .modelName(Srelic.prefix("model/honkai/el.obj"))
                                .effectColor(16711863)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(20)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.PERFECT_SANCTUARY.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )));
        bootstrap.register(
                redsakura, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("redsakura"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/redsakura.png"))
                                .modelName(Srelic.prefix("model/honkai/redsakura.obj"))
                                .effectColor(16711697)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(27)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.BLOOD_SAKURA.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )));
        bootstrap.register(
                galaxy, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("galaxy"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/galaxy.png"))
                                .modelName(Srelic.prefix("model/honkai/galaxy.obj"))
                                .effectColor(60159)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(35)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.THUNDERSTORM_SLASH.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )));
        bootstrap.register(
                santa_caterina, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("santa_caterina"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/fate/santa_caterina.png"))
                                .modelName(Srelic.prefix("model/fate/santa_caterina.obj"))
                                .effectColor(16711697)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(27)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.AVENGER_JEANNE.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )));
        bootstrap.register(
                ButterflyLament, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("butterfly_lament"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/stairail/butterfly_lament.png"))
                                .modelName(Srelic.prefix("model/stairail/butterfly_lament.obj"))
                                .effectColor(1835263)
                                .standbyRenderType(CarryType.NINJA)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(27)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.BUTTERFLY_FALLING.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5)
                        )
                )
        );
        bootstrap.register(
                staff_of_homa, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("staff_of_homa"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/genshine/staff_of_homa.png"))
                                .modelName(Srelic.prefix("model/genshine/staff_of_homa.obj"))
                                .effectColor(16711710)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(31)
                                .slashArtsType(SRslashArtRegsitry.Bloodspirit.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.BLOOD_PLUM_MEMORY.getId())
                                .maxDamage(200)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 15),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5)
                        )
                )
        );
        bootstrap.register(
                dawn_flame_feather, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("dawn_flame_feather"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/ornament/dawn_flame_feather.png"))
                                .modelName(Srelic.prefix("model/ornament/dawn_flame_feather.obj"))
                                .effectColor(16711710)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(21)
                                .slashArtsType(SRslashArtRegsitry.Closingsong.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.BLAZING_VALOR.getId())
                                .maxDamage(616)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 3)
                        )
                )
        );
        bootstrap.register(
                rose_spear, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("rose_spear"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/stairail/pure_elegy.png"))
                                .modelName(Srelic.prefix("model/stairail/pure_elegy.obj"))
                                .effectColor(16711710)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(25)
                                .slashArtsType(SRslashArtRegsitry.pure_elegy.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.PureElegy.getId())
                                .maxDamage(616)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5)
                        )
                )
        );
        bootstrap.register(
                The_radiance_of_stagnant_water_flow, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("the_radiance_of_stagnant_water_flow"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/genshine/the_radiance_of_stagnant_water_flow.png"))
                                .modelName(Srelic.prefix("model/genshine/the_radiance_of_stagnant_water_flow.obj"))
                                .effectColor(22015)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(20)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WATER_SYMPHONY.getId())
                                .maxDamage(1013)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SWEEPING_EDGE), 1),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.RESPIRATION), 1)
                        )
                )
        );
        bootstrap.register(
                SEELE
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("seele"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/stairail/seele.png"))
                                .modelName(Srelic.prefix("model/stairail/seele.obj"))
                                .effectColor(4063487)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(15)
                                .slashArtsType(SRslashArtRegsitry.ButterflyDance.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.QUANTUM_DANCE.getId())
                                .maxDamage(1013)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 3)
                        )
                )
        );
        bootstrap.register(
                AIR_NONE
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("air_none"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/air_none.png"))
                                .modelName(Srelic.prefix("model/honkai/air_none.obj"))
                                .effectColor(4063487)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(23)
                                .slashArtsType(SRslashArtRegsitry.Judgmentcube.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.VOID_SCISSORS.getId())
                                .maxDamage(1013)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 3)
                        )
                )
        );
        bootstrap.register(
                Abyss_Eye
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("abyss_eye"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/abyss_eye.png"))
                                .modelName(Srelic.prefix("model/honkai/abyss_eye.obj"))
                                .effectColor(16711710)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(37)
                                .slashArtsType(SRslashArtRegsitry.RedScarslash.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.RED_SCAR.getId())
                                .maxDamage(2026)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 7),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 7)
                        )
                )
        );
        bootstrap.register(
                Aphrodite_Lyre
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("aphrodite_lyre"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/ornament/aphrodite_lyre.png"))
                                .modelName(Srelic.prefix("model/ornament/aphrodite_lyre.obj"))
                                .effectColor(459263)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(40)
                                .slashArtsType(SRslashArtRegsitry.Icemusic.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.ICE_RHYTHM.getId())
                                .maxDamage(2026)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BLOCK_FORTUNE), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 4),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MENDING), 1)
                        )
                )
        );
        bootstrap.register(
                conspicuous
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("conspicuous"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/ornament/conspicuous.png"))
                                .modelName(Srelic.prefix("model/ornament/conspicuous.obj"))
                                .effectColor(16711710)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(25)
                                .slashArtsType(SRslashArtRegsitry.EXPLANATION.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.PLEDGE_MATRIX.getId())
                                .maxDamage(1013)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BLOCK_FORTUNE), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 4),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MENDING), 1)
                        )
                )
        );

        bootstrap.register(
                radiance
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("radiance"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/ornament/radiance.png"))
                                .modelName(Srelic.prefix("model/ornament/radiance.obj"))
                                .effectColor(16711710)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(25)
                                .slashArtsType(SRslashArtRegsitry.Whitenight.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WHITE_ROSE.getId())
                                .maxDamage(1013)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BLOCK_FORTUNE), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 4),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MENDING), 1)
                        )
                )
        );
        bootstrap.register(
                dash
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("dash"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/stairail/dash.png"))
                                .modelName(Srelic.prefix("model/stairail/dash.obj"))
                                .effectColor(7012096)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(35)
                                .slashArtsType(SRslashArtRegsitry.BreakSky.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.STORM_BODY.getId())
                                .maxDamage(1013)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BLOCK_FORTUNE), 10)
                        )
                )
        );
        bootstrap.register(
                sky_sword
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("sky_sword"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/genshine/sky/sky_sword.png"))
                                .modelName(Srelic.prefix("model/genshine/sky/sky_sword.obj"))
                                .effectColor(56292)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(20)
                                .slashArtsType(SRslashArtRegsitry.SKY_EXPLOSION_SWORD.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.Elemental_Explosion.getId())
                                .maxDamage(1013)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BLOCK_FORTUNE), 10)
                        )
                )
        );
        bootstrap.register(
                sky_sword_ascent
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("sky_sword_ascent"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/genshine/sky/sky_sword_ascent.bmp"))
                                .modelName(Srelic.prefix("model/genshine/sky/sky_sword_ascent.obj"))
                                .effectColor(56292)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(40)

                                .slashArtsType(SRslashArtRegsitry.SKY_WAVE_EDGE.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.VACUUM_BLADE.getId())

                                .maxDamage(1013)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BLOCK_FORTUNE), 10)
                        )
                )
        );
        bootstrap.register(
                SEVEN_1
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("seven_1"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/ornament/seven/seven.png"))
                                .modelName(Srelic.prefix("model/ornament/seven/seven_1.obj"))
                                .effectColor(16760832)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(30)
                                .slashArtsType(SRslashArtRegsitry.CELESTIAL_STRIKE.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.CELESTIAL_STRIKE.getId())
                                .maxDamage(1013)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 4),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 2)
                        )
                )
        );
        bootstrap.register(
                SEVEN_2
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("seven_2"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/ornament/seven/seven.png"))
                                .modelName(Srelic.prefix("model/ornament/seven/seven_2.obj"))
                                .effectColor(16760832)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(30)
                                .slashArtsType(SRslashArtRegsitry.CELESTIAL_STRIKE.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.CELESTIAL_STRIKE.getId())
                                .maxDamage(1013)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 2),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 4),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT), 2)
                        )
                )
        );
        bootstrap.register(
                kill
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("kill"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/thefire/kill.bmp"))
                                .modelName(Srelic.prefix("model/honkai/thefire/kill.obj"))
                                .effectColor(16760832)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(40)
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.INFERNO_THOUSAND_TRIALS.getId())
                                .maxDamage(1013)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BLOCK_FORTUNE), 10)
                        )
                )
        );
        bootstrap.register(
                ice_sword
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("ice_sword"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/genshine/ice/ice_sowrd.png"))
                                .modelName(Srelic.prefix("model/genshine/ice/ice_sowrd.obj"))
                                .effectColor(2003199)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(23)
                                .slashArtsType(SRslashArtRegsitry.ICE_EDGE.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.ICE_BLADE.getId())
                                .maxDamage(300)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BLOCK_FORTUNE), 10)
                        )
                )
        );
        bootstrap.register(
                THIRD_RELIC
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("third_relic"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/3rd/third_relic.png"))
                                .modelName(Srelic.prefix("model/honkai/3rd/third_relic.obj"))
                                .effectColor(16056064)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(15)
                                .slashArtsType(SlashArtsRegistry.WAVE_EDGE.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.HOLY_ENERGY_OVERFLOW.getId())
                                .maxDamage(300)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BLOCK_FORTUNE), 10)
                        )
                )
        );
        bootstrap.register(
                FROST_SNIPER
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("frost_sniper"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/frost_sniper/frost_sniper.png"))
                                .modelName(Srelic.prefix("model/honkai/frost_sniper/frost_sniper.obj"))
                                .effectColor(2003199)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(25)
                                .slashArtsType(SlashArtsRegistry.DRIVE_VERTICAL.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.DREAM_COMPANION.getId())
                                .maxDamage(300)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BLOCK_FORTUNE), 10)
                        )
                )
        );
        bootstrap.register(
                ICE_BLUE
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("ice_blue"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/ice_blue/ice_blue.png"))
                                .modelName(Srelic.prefix("model/honkai/ice_blue/ice_blue.obj"))
                                .effectColor(2003199)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(17)
                                .slashArtsType(SlashArtsRegistry.DRIVE_VERTICAL.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.BITTER_COLD_HELL.getId())
                                .maxDamage(300)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BLOCK_FORTUNE), 10)
                        )
                )
        );
        bootstrap.register(
                ICE_BLUE_EX
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("ice_blue_ex"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/ice_blue/ice_blue_ex.png"))
                                .modelName(Srelic.prefix("model/honkai/ice_blue/ice_blue_ex.obj"))
                                .effectColor(2003199)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(32)
                                .slashArtsType(SlashArtsRegistry.DRIVE_VERTICAL.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.BITTER_COLD_HELL_EX.getId())
                                .maxDamage(300)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BLOCK_FORTUNE), 10)
                        )
                )
        );


        bootstrap.register(
                THIRD_BLUE_1
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("third_blue_1"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/stairail/third_blue/third_blue.png"))
                                .modelName(Srelic.prefix("model/stairail/third_blue/third_blue_1.obj"))
                                .effectColor(2003199)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(40)
                                .slashArtsType(SlashArtsRegistry.DRIVE_VERTICAL.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.ICE_BLOOM.getId())
                                .maxDamage(300)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BLOCK_FORTUNE), 10)
                        )
                )
        );

        bootstrap.register(
                THIRD_BLUE_2
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("third_blue_2"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/stairail/third_blue/third_blue.png"))
                                .modelName(Srelic.prefix("model/stairail/third_blue/third_blue_2.obj"))
                                .effectColor(2003199)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(40)
                                .slashArtsType(SlashArtsRegistry.DRIVE_VERTICAL.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.ICE_BLOOM.getId())
                                .maxDamage(300)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BLOCK_FORTUNE), 10)
                        )
                )
        );
        bootstrap.register(
                AFFLORDITE
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("afflordite"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/afflordite/afflordite.png"))
                                .modelName(Srelic.prefix("model/honkai/afflordite/afflordite.obj"))
                                .effectColor(16711710)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(20)
                                .slashArtsType(SRslashArtRegsitry.AFFLORDITE.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.AFFLORDITE.getId())

                                .maxDamage(300)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BLOCK_FORTUNE), 10)
                        )
                )
        );
        bootstrap.register(
                DOUBLE_FISH
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("double_fish"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/double_fish/double_fish.png"))
                                .modelName(Srelic.prefix("model/honkai/double_fish/double_fish.obj"))
                                .effectColor(2003199)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(35)
                                .slashArtsType(SRslashArtRegsitry.AFFLORDITE.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.AFFLORDITE.getId())

                                .maxDamage(300)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BLOCK_FORTUNE), 10)
                        )
                )
        );
        bootstrap.register(
                NO_END
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("no_end"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/zzz/no_end/no_end.png"))
                                .modelName(Srelic.prefix("model/zzz/no_end/no_end.obj"))
                                .effectColor(2003199)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(26)
                                .slashArtsType(SRslashArtRegsitry.GoGogo.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.FROST_FLAME.getId())
                                .maxDamage(300)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BLOCK_FORTUNE), 10)
                        )
                )
        );
        bootstrap.register(
                THE_STAR_OF_THE_FROST
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("the_star_of_the_frost"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/the_star_of_the_frost/the_star_of_the_frost.png"))
                                .modelName(Srelic.prefix("model/honkai/the_star_of_the_frost/the_star_of_the_frost.obj"))
                                .effectColor(2003199)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(26)
                                .slashArtsType(SlashArtsRegistry.CIRCLE_SLASH.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.ICE_SOUL_FROST_SKY.getId())

                                .maxDamage(300)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BLOCK_FORTUNE), 10)
                        )
                )
        );
        bootstrap.register(
                GENE_HARVESTER
                , new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("gene_harvester"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/gene_harvester/gene_harvester.png"))
                                .modelName(Srelic.prefix("model/honkai/gene_harvester/gene_harvester.obj"))
                                .effectColor(1769216)
                                .standbyRenderType(CarryType.PSO2)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(26)
                                .slashArtsType(SlashArtsRegistry.CIRCLE_SLASH.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.WhiteMaker.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.HUNTER.getId())

                                .maxDamage(300)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.BLOCK_FORTUNE), 10)
                        )
                )
        );
    }




    private static ResourceKey<SlashBladeDefinition> register(String id) {
        return ResourceKey.create(SlashBladeDefinition.REGISTRY_KEY, Srelic.prefix(id));
    }
    static ResourceLocation getEnchantmentID(Enchantment enchantment) {
        return ForgeRegistries.ENCHANTMENTS.getKey(enchantment);
    }

}
