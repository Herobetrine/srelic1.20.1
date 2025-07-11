package com.dinzeer.srelic.registry;

import com.dinzeer.srelic.Srelic;
import com.dinzeer.srelic.registry.item.SrelicItem;
import com.dinzeer.srelic.registry.item.SrelicStar;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;

import static com.dinzeer.srelic.Srelic.REGISTRATE;

public class SRItemRegsitry {


    public static final ItemEntry<SrelicItem> rainbow_star = iteminit("rainbow_star");
    public static final ItemEntry<Item> universal_test = iteminittest("universal_test");
    public static final ItemEntry<SrelicItem> black_hole_metal = iteminit("black_hole_metal");
    public static final ItemEntry<SrelicItem> fel_metal = iteminit("fel_metal");
    public static final ItemEntry<SrelicItem> compressed_alloy = iteminit("compressed_alloy");
    public static final ItemEntry<SrelicItem> ender_metal = iteminit("ender_metal");
    public static final ItemEntry<SrelicItem> soul_metal = iteminit("soul_metal");
    public static final ItemEntry<SrelicStar> ex_star = iteminitStar("ex_star");
    public static final ItemEntry<SrelicItem> flame_netherite_alloy = iteminit("flame_netherite_alloy");//烈焰下界合金锭
    public static final ItemEntry<SrelicItem> frozen_netherite_alloy = iteminit("frozen_netherite_alloy");//极寒下界合金锭
    public static final ItemEntry<SrelicItem> thunder_netherite_alloy = iteminit("thunder_netherite_alloy");
    public static final ItemEntry<SrelicItem> oceanic_netherite_alloy = iteminit("oceanic_netherite_alloy");//海洋下界合金锭
    public static final ItemEntry<SrelicItem> demon_ingot = iteminit("demon_ingot");
    public static final ItemEntry<SrelicItem> sakura_steel_ingot = iteminit("sakura_steel_ingot");
    public static final ItemEntry<SrelicItem> diamond_star = iteminit("diamond_star");
    public static final ItemEntry<SrelicItem> crimson_paper = iteminit("crimson_paper");
    public static final ItemEntry<SrelicItem> eternal_plum = iteminit("eternal_plum");
    public static final ItemEntry<SrelicItem> spirit_butterfly = iteminit("spirit_butterfly");
    public static final ItemEntry<SrelicItem> max_ingot = iteminit("max_ingot");
    public static final ItemEntry<SrelicItem> windy_core = iteminit("windy_core");
    public static final ItemEntry<SrelicItem> windy_core_ingot = iteminit("windy_core_ingot");

    public static final ItemEntry<SrelicItem> Emberquill = iteminit("emberquill");//炎翎
    public static final ItemEntry<SrelicItem> PledgeflameVermilionStrand = iteminit("pledge_flame_vermilion_strand");//誓炎绯丝
    public static final ItemEntry<SrelicItem> ScarletflameIngot = iteminit("scarletflame_ingot");//绯炎锭
    public static final ItemEntry<SrelicItem> PledgeflameHeartforge = iteminit("pledgeflame_heartforge");//「誓炎熔芯」

    public static final ItemEntry<SrelicItem> AbyssalLuminanceIngot = iteminit("abyssal_luminance_ingot");//渊海流辉锭
    public static final ItemEntry<SrelicItem> NereidSecretBrew = iteminit("nereid_s_secret_brew");//水神的秘制调饮
    public static final ItemEntry<SrelicItem> SurgeheartVortex = iteminit("surgeheart_vortex");//流涌涡心

    public static final ItemEntry<SrelicItem> ignis_cube = iteminit("ignis_cube");//火焰能量立方

    public static final ItemEntry<SrelicItem> NightterrorBloodwing = iteminit("nightterror_bloodwing");//夜魇血蝶
    public static final ItemEntry<SrelicItem> PhantomirageButterfly = iteminit("butterfly");//影幻梦蝶

    public static final ItemEntry<SrelicItem> DreamweaveIngot = iteminit("dreamweave_ingot");//织梦锭
    public static final ItemEntry<SrelicItem> PhantomTraceIngot = iteminit("phantom_trace_ingot");//幻痕锭
    public static final ItemEntry<SrelicItem> NightstalkerIngot = iteminit("nightstalker_ingot");//夜狩锭
    public static final ItemEntry<SrelicItem> NightmareCoreIngot = iteminit("nightmare_core_ingot");//梦魇锭

    public static final ItemEntry<SrelicItem> GrammerAlloy = iteminit("grammer_alloy");//格拉默合金
    public static final ItemEntry<SrelicItem> GrammerAlloyEx = iteminit("grammer_alloy_ex");//格拉默合金·完全燃烧
    public static final ItemEntry<SrelicItem> GrammerCore = iteminit("grammer_core");//格拉默核心
    public static final ItemEntry<SrelicItem> PuleApple = iteminit("pule_apple");//结业果

    public static final ItemEntry<SrelicItem> crimson_core = iteminit("crimson_core"); // 绯红核心
    public static final ItemEntry<SrelicItem> rough_crimson_shadow_ore = iteminit("rough_crimson_shadow_ore"); // 粗绯影矿
    public static final ItemEntry<SrelicItem> crimson_shadow_particle = iteminit("crimson_shadow_particle"); // 绯影粒
    public static final ItemEntry<SrelicItem> crimson_shadow_ingot = iteminit("crimson_shadow_ingot"); // 绯影锭
    public static final ItemEntry<SrelicItem> red_tide_core = iteminit("red_tide_core"); // 红潮核心

    public static ItemEntry<SrelicItem> iteminit(String name) {
        return REGISTRATE.item(
                name, SrelicItem::new
        ).defaultModel().defaultLang().tab(Srelic.SRItems.getKey()).register();
    }
    public static ItemEntry<SrelicStar> iteminitStar(String name) {
        return REGISTRATE.item(
                name, SrelicStar::new
        ).defaultModel().defaultLang().tab(Srelic.SRItems.getKey()).register();


    }

    public static ItemEntry<Item> iteminittest(String name) {
        return REGISTRATE.item(
                        name, Item::new
                ).defaultModel()
                .model((ctx, prov) -> prov.generated(ctx, prov.modLoc("item/rainbow_star")))
                .lang("既定轨迹·命运之心")
                .register();


    }


    public static void regsitry() {

    }
}
