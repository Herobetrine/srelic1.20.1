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
    //天火圣裁·绿
    public static final ResourceKey<SlashBladeDefinition> the_fire_green=register("the_fire_green" );
    //天火圣裁·绿
    public static final ResourceKey<SlashBladeDefinition> the_fire_green2=register("the_fire_green2" );
    //天火圣裁·绿
    public static final ResourceKey<SlashBladeDefinition> the_fire_pink=register("the_fire_pink" );
    //天火圣裁·绿
    public static final ResourceKey<SlashBladeDefinition> the_fire_pink2=register("the_fire_pink2" );
    //天火圣裁·绿
    public static final ResourceKey<SlashBladeDefinition> the_fire_pinks=register("the_fire_pinks" );
    //爱莉的弓
    public static final ResourceKey<SlashBladeDefinition> el=register("el");
    //魂妖刀「血樱寂灭」
    public static final ResourceKey<SlashBladeDefinition> redsakura=register("redsakura");
    //磁暴斩
    public static final ResourceKey<SlashBladeDefinition> galaxy=register("galaxy");
    //圣卡特琳娜「Alter」
    public static final ResourceKey<SlashBladeDefinition> santa_caterina=register("santa_caterina");
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
                                .addSpecialEffect(SRSpecialEffectsRegistry.path_of_nihility.getId())
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
                                .addSpecialEffect(SRSpecialEffectsRegistry.path_of_finality.getId())
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
                                .addSpecialEffect(SRSpecialEffectsRegistry.path_of_destruction.getId())
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
                                .addSpecialEffect(SRSpecialEffectsRegistry.path_of_preservation.getId())
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
                                .addSpecialEffect(SRSpecialEffectsRegistry.path_of_the_hunt.getId())
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
                                .addSpecialEffect(SRSpecialEffectsRegistry.path_of_trailblaze.getId())
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
                                .addSpecialEffect(SRSpecialEffectsRegistry.path_of_erudition.getId())
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
                                .addSpecialEffect(SRSpecialEffectsRegistry.path_of_finality.getId())
                                .addSpecialEffect(SRSpecialEffectsRegistry.SAM_OVERDRIVE.getId())
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
                the_fire_green, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("the_fire_green"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/thefireextra/the_fire_green.png"))
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
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 5)
                        )
                )
        );
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
                the_fire_pink, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("the_fire_pink"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/thefireextra/the_fire_pink.png"))
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
                the_fire_pinks, new SlashBladeDefinition(SRItem.SRELIC_BLADE_ID,Srelic.prefix("the_fire_pinks"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/thefireextra/the_fire_pinks.png"))
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
    }




    private static ResourceKey<SlashBladeDefinition> register(String id) {
        return ResourceKey.create(SlashBladeDefinition.REGISTRY_KEY, Srelic.prefix(id));
    }
    static ResourceLocation getEnchantmentID(Enchantment enchantment) {
        return ForgeRegistries.ENCHANTMENTS.getKey(enchantment);
    }

}
