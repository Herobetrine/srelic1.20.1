package com.dinzeer.srelic.data;

import com.dinzeer.srelic.Srelic;
import com.dinzeer.srelic.registry.SRslashArtRegsitry;
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
    public static void registerAll(BootstapContext<SlashBladeDefinition> bootstrap) {
        bootstrap.register(START,
                new SlashBladeDefinition(Srelic.prefix("none_blue"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/stairail/none_blue.png"))
                                .modelName(Srelic.prefix("model/stairail/none.obj"))
                                .effectColor(2003199)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(17)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(SlashArtsRegistry.SAKURA_END.getId())
                                .maxDamage(80)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3))));
        bootstrap.register(NONE,
                new SlashBladeDefinition(Srelic.prefix("none_red"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/stairail/none_red.jpg"))
                                .modelName(Srelic.prefix("model/stairail/none.obj"))
                                .effectColor(13504014)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(21)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(SlashArtsRegistry.VOID_SLASH.getId())
                                .maxDamage(80)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3))));
        bootstrap.register(YE_TACHI_FIRE,
                new SlashBladeDefinition(Srelic.prefix("ye_taichi"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/ornament/ye_taichi.png"))
                                .modelName(Srelic.prefix("model/ornament/ye_taichi.obj"))
                                .effectColor(13504014)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(17)
                                .slashArtsType(SlashArtsRegistry.WAVE_EDGE.getId())
                                .maxDamage(80)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3))));
        bootstrap.register(YE_TACHI,
                new SlashBladeDefinition(Srelic.prefix("ye_tachi"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/ornament/ye_tachi_v.jpg"))
                                .modelName(Srelic.prefix("model/ornament/ye_tachi_v.obj"))
                                .effectColor(13504014)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(21)
                                .slashArtsType(SlashArtsRegistry.WAVE_EDGE.getId())
                                .maxDamage(80)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3))));
        bootstrap.register(FIRE,
                new SlashBladeDefinition(Srelic.prefix("the_fire"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/the_fire.jpg"))
                                .modelName(Srelic.prefix("model/honkai/the_fire.obj"))
                                .effectColor(16760832)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .baseAttackModifier(21)
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .slashArtsType(SlashArtsRegistry.WAVE_EDGE.getId())
                                .maxDamage(80)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3))));
        bootstrap.register(WOLF,
                new SlashBladeDefinition(Srelic.prefix("the_wolf"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/genshine/the_wolf.png"))
                                .modelName(Srelic.prefix("model/genshine/the_wolf.obj"))
                                .effectColor(12779520)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(21)
                                .slashArtsType(SlashArtsRegistry.WAVE_EDGE.getId())
                                .maxDamage(80)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3))));


        bootstrap.register(ARK_FIRE,
                new SlashBladeDefinition(Srelic.prefix("ark_fire"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/ark_fire.png"))
                                .modelName(Srelic.prefix("model/honkai/ark_fire.obj"))
                                .effectColor(16760832)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(21)
                                .slashArtsType(SlashArtsRegistry.WAVE_EDGE.getId())
                                .maxDamage(80)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3))));

        bootstrap.register(END_FIRE,
                new SlashBladeDefinition(Srelic.prefix("end_fire"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/honkai/end_fire.png"))
                                .modelName(Srelic.prefix("model/honkai/end_fire.obj"))
                                .effectColor(16760832)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(21)
                                .slashArtsType(SlashArtsRegistry.WAVE_EDGE.getId())
                                .maxDamage(80)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3))));

        bootstrap.register(WATER,
                new SlashBladeDefinition(Srelic.prefix("the_water"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/genshine/sword_of_narzissenkreuz.png"))
                                .modelName(Srelic.prefix("model/genshine/the_water.obj"))
                                .effectColor(2003199)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(21)
                                .slashArtsType(SlashArtsRegistry.WAVE_EDGE.getId())
                                .maxDamage(80)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3))));

        bootstrap.register(ICECRY,
                new SlashBladeDefinition(Srelic.prefix("ice_cry"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/ow/ice_cry.png"))
                                .modelName(Srelic.prefix("model/ow/frostmourne.obj"))
                                .effectColor(2003199)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(17)
                                .addSpecialEffect(SpecialEffectsRegistry.WITHER_EDGE.getId())
                                .slashArtsType(SRslashArtRegsitry.WitherBreaker.getId())
                                .maxDamage(80)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 5),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3)

                        )));

        bootstrap.register(KAFUKA,
                new SlashBladeDefinition(Srelic.prefix("kafuka"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/stairail/kafuka.png"))
                                .modelName(Srelic.prefix("model/stairail/kafuka.obj"))
                                .effectColor(14947317)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(21)
                                .slashArtsType(SlashArtsRegistry.VOID_SLASH.getId())
                                .maxDamage(80)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3))));


        bootstrap.register(STAR,
                new SlashBladeDefinition(Srelic.prefix("star"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/stairail/star.png"))
                                .modelName(Srelic.prefix("model/stairail/star.obj"))
                                .effectColor(2003199)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(17)
                                .slashArtsType(SlashArtsRegistry.SAKURA_END.getId())
                                .maxDamage(80)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3))));
        bootstrap.register(FIRE_KNIGHT,
                new SlashBladeDefinition(Srelic.prefix("fire_knight"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/stairail/fire_knight.png"))
                                .modelName(Srelic.prefix("model/stairail/fire_knight.obj"))
                                .effectColor(2003199)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(17)
                                .slashArtsType(SlashArtsRegistry.SAKURA_END.getId())
                                .maxDamage(80)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3))));

        bootstrap.register(ETERNAL_VOWS,
                new SlashBladeDefinition(Srelic.prefix("eternal_vows"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/wuwa/eternal_vows.png"))
                                .modelName(Srelic.prefix("model/wuwa/older.obj"))
                                .effectColor(2003199)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(17)
                                .slashArtsType(SRslashArtRegsitry.EXPLASHON.getId())
                                .maxDamage(80)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 3)
                        )));

        bootstrap.register(YE_FIRE,
                new SlashBladeDefinition(Srelic.prefix("ye_fire"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/ornament/ye_fire.png"))
                                .modelName(Srelic.prefix("model/ornament/ye_fire.obj"))
                                .effectColor(2003199)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(17)
                                .slashArtsType(SlashArtsRegistry.SAKURA_END.getId())
                                .maxDamage(80)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3))));
        bootstrap.register(YE_STAR,
                new SlashBladeDefinition(Srelic.prefix("ye_star"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/ornament/ye_star.png"))
                                .modelName(Srelic.prefix("model/ornament/ye_star.obj"))
                                .effectColor(2003199)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(17)
                                .slashArtsType(SlashArtsRegistry.SAKURA_END.getId())
                                .maxDamage(80)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3))));
        bootstrap.register(RED_QUEEN,
                new SlashBladeDefinition(Srelic.prefix("red_queen"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/dmc/red_queen.png"))
                                .modelName(Srelic.prefix("model/dmc/red_queen.obj"))
                                .effectColor(16711697)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(20)
                                .slashArtsType(SRslashArtRegsitry.XDRIVE.getId())
                                .maxDamage(80)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS), 10),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                                new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 3)

                        )));
        bootstrap.register(RAIDIAN,
                new SlashBladeDefinition(Srelic.prefix("raiden"),
                        RenderDefinition.Builder.newInstance()
                                .textureName(Srelic.prefix("model/genshine/raiden.png"))
                                .modelName(Srelic.prefix("model/genshine/raiden.obj"))
                                .effectColor(8454388)
                                .build(),
                        PropertiesDefinition.Builder.newInstance()
                                .defaultSwordType(List.of(SwordType.BEWITCHED))
                                .baseAttackModifier(17)
                                .slashArtsType(SRslashArtRegsitry.THO.getId())
                                .maxDamage(80)
                                .build(),
                        List.of(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS), 3),
                           new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE), 3),
                        new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING), 3),
                         new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING), 3)



                        )));

    }




    private static ResourceKey<SlashBladeDefinition> register(String id) {
        return ResourceKey.create(SlashBladeDefinition.REGISTRY_KEY, Srelic.prefix(id));
    }
    private static ResourceLocation getEnchantmentID(Enchantment enchantment) {
        return ForgeRegistries.ENCHANTMENTS.getKey(enchantment);
    }

}
