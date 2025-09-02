package com.dinzeer.srelic.registry;

import com.dinzeer.srelic.Srelic;
import com.dinzeer.srelic.items.armor.DiamondBlockArmor;
import com.dinzeer.srelic.items.armor.ExtremeArmor;
import com.dinzeer.srelic.items.armor.OceanArmor;
import com.dinzeer.srelic.items.armor.SkyArmor;
import com.dinzeer.srelic.items.tool.BlazeTool;
import com.dinzeer.srelic.items.tool.ExtremeTool;
import com.dinzeer.srelic.registry.item.SrelicItem;
import com.dinzeer.srelic.registry.item.SrelicStar;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.SwordItem;

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

    public static final ItemEntry<SrelicItem> maxim = iteminit("maxim"); // 鸢尾花

    public static final ItemEntry<SrelicItem> shirin_fish = iteminit("shirin_fish"); // 某不知名紫色河豚
    public static final ItemEntry<SrelicItem> void_ingot = iteminit("void_ingot");//亚空玄钢
    public static final ItemEntry<SrelicItem> void_core = iteminit("void_core");//亚空星璇
    public static final ItemEntry<SrelicItem> fire_smithing_template = iteminit("fire_smithing_template");//灼热下界合金升级模板
    public static final ItemEntry<SrelicItem> max_smithing_template = iteminit("max_smithing_template");//极限下界合金升级模板

    public static final ItemEntry<ExtremeArmor.Helmet> max_helmet=REGISTRATE.item(
            "max_helmet", properties -> new ExtremeArmor.Helmet()
             ).defaultModel().defaultLang().tab(Srelic.SRItems.getKey()).register();
    public static final ItemEntry<ExtremeArmor.Chestplate> max_chestplate=REGISTRATE.item(
            "max_chestplate", properties -> new ExtremeArmor.Chestplate()
    ).defaultModel().defaultLang().tab(Srelic.SRItems.getKey()).register();
    public static final ItemEntry<ExtremeArmor.Leggings> max_leggings=REGISTRATE.item(
            "max_leggings", properties -> new ExtremeArmor.Leggings()
            ).defaultModel().defaultLang().tab(Srelic.SRItems.getKey()).register();
    public static final ItemEntry<ExtremeArmor.Boots> max_boots=REGISTRATE.item(
            "max_boots", properties -> new ExtremeArmor.Boots()
            ).defaultModel().defaultLang().tab(Srelic.SRItems.getKey()).register();

    public static final ItemEntry<DiamondBlockArmor.Helmet> diamond_block_helmet=REGISTRATE.item(
            "diamond_block_helmet", properties -> new DiamondBlockArmor.Helmet()
    ).defaultModel().defaultLang().tab(Srelic.SRItems.getKey()).register();
    public static final ItemEntry<DiamondBlockArmor.Chestplate> diamond_block_chestplate=REGISTRATE.item(
        "diamond_block_chestplate", properties -> new DiamondBlockArmor.Chestplate()
            ).defaultModel().defaultLang().tab(Srelic.SRItems.getKey()).register();
    public static final ItemEntry<DiamondBlockArmor.Leggings> diamond_block_leggings=REGISTRATE.item(
            "diamond_block_leggings", properties -> new DiamondBlockArmor.Leggings()
    ).defaultModel().defaultLang().tab(Srelic.SRItems.getKey()).register();
    public static final ItemEntry<DiamondBlockArmor.Boots> diamond_block_boots=REGISTRATE.item(
            "diamond_block_boots", properties -> new DiamondBlockArmor.Boots()
    ).defaultModel().defaultLang().tab(Srelic.SRItems.getKey()).register();

    public static final ItemEntry<SkyArmor.Helmet> sky_helmet=REGISTRATE.item(
            "sky_helmet", properties -> new SkyArmor.Helmet()
    ).defaultModel().defaultLang().tab(Srelic.SRItems.getKey()).register();
    public static final ItemEntry<SkyArmor.Chestplate> sky_chestplate=REGISTRATE.item(
            "sky_chestplate", properties -> new SkyArmor.Chestplate()
    ).defaultModel().defaultLang().tab(Srelic.SRItems.getKey()).register();
    public static final ItemEntry<SkyArmor.Leggings> sky_leggings=REGISTRATE.item(
            "sky_leggings", properties -> new SkyArmor.Leggings()
    ).defaultModel().defaultLang().tab(Srelic.SRItems.getKey()).register();
    public static final ItemEntry<SkyArmor.Boots> sky_boots=REGISTRATE.item(
            "sky_boots", properties -> new SkyArmor.Boots()
    ).defaultModel().defaultLang().tab(Srelic.SRItems.getKey()).register();

    public static final ItemEntry<OceanArmor.Helmet> ocean_helmet=REGISTRATE.item(
            "ocean_helmet", properties -> new OceanArmor.Helmet()
    ).defaultModel().defaultLang().tab(Srelic.SRItems.getKey()).register();
    public static final ItemEntry<OceanArmor.Chestplate> ocean_chestplate=REGISTRATE.item(
            "ocean_chestplate", properties -> new OceanArmor.Chestplate()
    ).defaultModel().defaultLang().tab(Srelic.SRItems.getKey()).register();
    public static final ItemEntry<OceanArmor.Leggings> ocean_leggings=REGISTRATE.item(
            "ocean_leggings", properties -> new OceanArmor.Leggings()
    ).defaultModel().defaultLang().tab(Srelic.SRItems.getKey()).register();
    public static final ItemEntry<OceanArmor.Boots> ocean_boots=REGISTRATE.item(
            "ocean_boots", properties -> new OceanArmor.Boots()
    ).defaultModel().defaultLang().tab(Srelic.SRItems.getKey()).register();






















    public static final ItemEntry<SwordItem> max_sword=REGISTRATE.item(
            "max_sword", properties -> new SwordItem(ExtremeTool.EXTREME_SWORD_TIER, 5, -2.4f, new Item.Properties())
             )
            .tag(ItemTags.SWORDS)
            .model((ctx, prov) -> prov.handheld(ctx))
            .defaultLang().tab(Srelic.SRItems.getKey()).register();

    public static final ItemEntry<PickaxeItem> max_pickaxe=REGISTRATE.item(
                    "max_pickaxe", properties -> new PickaxeItem(ExtremeTool.EXTREME_PICKAXE_TIER, 5, -2.4f, new Item.Properties())
            )
            .tag(ItemTags.PICKAXES)
            .model((ctx, prov) -> prov.handheld(ctx))
            .defaultLang().tab(Srelic.SRItems.getKey()).register();
    public static final ItemEntry<SwordItem> fire_netherite_sword=REGISTRATE.item(
                    "fire_netherite_sword",
                    properties -> new SwordItem(BlazeTool.Blaze_TIER, 3, -2.4f, new Item.Properties())
            )
            .tag(ItemTags.SWORDS)
            .model((ctx, prov) -> prov.handheld(ctx))
            .defaultLang().tab(Srelic.SRItems.getKey()).register();

    public static final ItemEntry<PickaxeItem> fire_netherite_pickaxe=REGISTRATE.item(
                    "fire_netherite_pickaxe",
                    properties -> new PickaxeItem(BlazeTool.Blaze_TIER, 2, -2.4f, new Item.Properties())
            )
            .tag(ItemTags.PICKAXES)
            .model((ctx, prov) -> prov.handheld(ctx))
            .defaultLang().tab(Srelic.SRItems.getKey()).register();




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
