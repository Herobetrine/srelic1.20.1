package com.dinzeer.srelic.registry;

import com.dinzeer.srelic.data.SRelicBuiltInRegsitry;
import mods.flammpfeil.slashblade.registry.slashblade.SlashBladeDefinition;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.slasharts.SlashArts;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.registries.RegistryObject;

import static com.dinzeer.srelic.Srelic.MODID;
import static com.dinzeer.srelic.Srelic.REGISTRATE;

public class LangRegistry {
    public static void register() {
        // 绘画
        REGISTRATE.addRawLang("painting.srelic.gwen.author", "LOL");
        REGISTRATE.addRawLang("painting.srelic.gwen.title", "格温");

        // 物品
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.YE_TACHI), "封刃太刀「夜煌」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.NONE), "「无」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.START), "「始」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.RED_QUEEN), "绯红女皇");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.ICECRY), "魔剑「霜之哀伤」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.ETERNAL_VOWS), "千古「伏流」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.RAIDIAN), "梦之刃「梦想一心」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.YE_FIRE), "夜煌「灼傀」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.WOLF), "残剑「狼的末路」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.WATER), "水仙十字之剑");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.RAPPA), "缭乱忍法「缭乱破魔」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.FIRE), "破坏之键「天火圣裁」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.ARK_FIRE), "异形大剑「天火圣裁·劫灭」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.END_FIRE), "天火圣裁「无尽终焉」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.KAFUKA), "「游丝」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.STAR), "毁灭「星辰王牌」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.FIRE_KNIGHT), "存护「炎枪」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.YE_STAR), "夜煌「星霆」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.BLADE), "可塑性白模");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.LAOTIE), "「老铁」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.JINYUAN), "阵刀「石火梦身」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.idanstar), "伊甸之星「拐杖」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.redmoon), "赤镰「赤月之形」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.heita), "§m黑塔の大钻石锤子§r§5「璀璨重锥」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.santa_caterina), "圣卡特琳娜「Alter」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.galaxy), "重磁暴「斩」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.samu), "火萤五型「向死而生」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.lightning), "天殛之境「裁决」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.sika_gun), "「夜魂左轮」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.redsakura), "魂妖刀「血樱寂灭」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.clound), "孤云");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.el), "真我之境「无瑕回归」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.the_fire_gray), "天火圣裁「命运之骰」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.FIREBlue2), "天火圣裁「寒锋聚气」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.spring), "「裁椿」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.the_fire_pink2), "天火圣裁「落樱刹那」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.the_fire_green2), "天火圣裁「暴风天眼」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.ButterflyLament), "冥镰「蝶哀」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.staff_of_homa), "长枪「护摩之杖」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.dawn_flame_feather), "誓焰「拂晓炎翎」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.rose_spear), "纯美「玫瑰长枪」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.The_radiance_of_stagnant_water_flow), "静水流涌之辉");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.SEELE), "旧镰「蝶影」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.AIR_NONE), "紫剪「空无」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.Abyss_Eye), "血渊之眸「如一」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.Aphrodite_Lyre), "奥菲厄斯的摇篮");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.conspicuous), "「赫刀」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.radiance), "「绯羽灼华」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.dash), "战戟「钺贯天冲」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.sky_sword), "「天空之刃」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.sky_sword_ascent), "「天空之傲」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.SEVEN_1), "隧星盛耀「剑盾」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.SEVEN_2), "隧星盛耀「盾斧」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.kill), "劫灭「无烬」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.ice_sword), "雪葬的星荧");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.THIRD_RELIC), "3rd圣遗物");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.FROST_SNIPER), "「霜狙」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.ICE_BLUE), "仿灵刀「冰昙天」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.ICE_BLUE_EX), "御灵刀「寒狱冰天」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.THIRD_BLUE_1), "「昙华剑」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.THIRD_BLUE_2), "转魄「昙华剑」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.AFFLORDITE), "「阿芙洛狄忒」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.DOUBLE_FISH), "裁星刃「双鱼座」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.NO_END), "妖刀「无尾」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.THE_STAR_OF_THE_FROST), "「天霜之斯卡蒂」");
        REGISTRATE.addRawLang(CreateItemLang(SRelicBuiltInRegsitry.GENE_HARVESTER), "「基因收割者」");

        REGISTRATE.addRawLang("patchouli.srelic.book.name", "记录了异界信息的书");
        REGISTRATE.addRawLang("patchouli.srelic.book.landing_text", "一本来自异界的书，上面记载了许多锻造异界之刃的方式，以白模为基础奏响共鸣之路");
        REGISTRATE.addRawLang("patchouli.srelic.book.categories.arrow.name", "链接一切的起始·超级材料");
        REGISTRATE.addRawLang("patchouli.srelic.book.categories.universal.name", "精神与命运的共鸣·命途虹星");
        REGISTRATE.addRawLang("patchouli.srelic.book.categories.arrow.description", "本章节记载了游戏扩增新加材料的获取方式和背景介绍");
        REGISTRATE.addRawLang("patchouli.srelic.book.categories.universal.description", "本章节记载了游戏扩增命途的名称以及效果");
        REGISTRATE.addRawLang("patchouli.srelic.book.entries.arrow.ender_metal.pages.0.text", "让末影人愤怒的特殊金属，可以用于协助白模链接世界并构筑成型,只不过配方让人想说：“这玩意不应该叫末影合金吗?”");
        REGISTRATE.addRawLang("patchouli.srelic.book.entries.arrow.black_hole_metal.pages.0.text", "四块坚硬无比的下界合金在聚合后因邪灵金属被引爆诞生的终极合金，它将链接白模将异界中“黑洞”这个概念的物品共鸣出来");
        REGISTRATE.addRawLang("patchouli.srelic.book.entries.arrow.compressed_alloy.pages.0.text", "通常来说压缩不成这样，但是这不是通常情况，毕竟你有下界之星");
        REGISTRATE.addRawLang("patchouli.srelic.book.entries.arrow.fel_metal.pages.0.text", "来自远古文明的禁忌炼金术带来的特殊金属");
        REGISTRATE.addRawLang("tips.black_hole.name", "黑洞压缩仪式");
        REGISTRATE.addRawLang("tips.black_hole.name_tip", "在末地手持邪灵金属对准中心的黑曜石激活");
        REGISTRATE.addRawLang("tips.compressed_alloy.name", "下界之星压缩");
        REGISTRATE.addRawLang("tips.ex_star.name", "蓝莓酱你想一个吧，这几个仪式的名字");
        REGISTRATE.addRawLang("tips.compressed_alloy.name_tip", "在下界手持下界执行对准中心的下界合金激活");
        REGISTRATE.addRawLang("tips.fel_metal.name", "远古邪灵炼成");
        REGISTRATE.addRawLang("tips.fel_metal.name_tip", "手持下界合金碎片对准中心炼药锅激活");
        REGISTRATE.addRawLang("tips.ex_star.name_tip", "在末地手持下界之星右键激活仪式");
        REGISTRATE.addRawLang("tips.frozen_netherite_alloy.name_tip", "在水中手持下界合金右击蓝冰获得");
        REGISTRATE.addRawLang("tips.oceanic_netherite_alloy.name_tip", "在下界手持下界合金右击海晶石块获得");
        REGISTRATE.addRawLang("tips.sakura_steel_ingot.name_tip", "将樱花树叶和樱花原木相邻放置，然后手持铁锭右键樱花原木获得");
        REGISTRATE.addRawLang("tips.crimson_paper.name_tip", "与流浪商人交易获得");
        REGISTRATE.addRawLang("tips.thunder_netherite_alloy.name_tip", "在雷暴天气手持下界合金右击避雷针获得");
        REGISTRATE.addRawLang("patchouli.srelic.book.entries.arrow.soul_metal.pages.0.text", "纳米机器人组成的金属块，用于隔断崩坏能。当金属块被崩坏能侵蚀时，纳米机器人可以自动修复。");
        REGISTRATE.addRawLang("patchouli.srelic.book.entries.arrow.soul_metal.pages.1.text", "我愚弄了友人，愚弄了至亲，愚弄了世界和它之上的规则……只为了给予那唯一真实的你，以第二次生命。我回来了，卡莲。");
        REGISTRATE.addRawLang("painting.srelic.aotuo.title", "§a奥§r托·阿波卡利斯");
        REGISTRATE.addRawLang("painting.srelic.huoqu.title", "获取方式");
        REGISTRATE.addRawLang("patchouli.srelic.book.entries.arrow.spirit_butterfly.pages.0.text", "万花丛中，蝴蝶乱舞，于凋零之中绽放，于往生中留香，灵芳环绕三生，蝴蝶留有血梅香。");
        REGISTRATE.addRawLang("patchouli.srelic.book.entries.arrow.diamond_star.pages.0.text", "珍贵且坚硬的材料，三首恶魔的核心与坚硬的材料融合，产生意想不到的材料，钻石之星闪耀着星辰的光芒。");
        REGISTRATE.addRawLang("patchouli.srelic.book.entries.arrow.crimson_core.pages.0.text", "绯红色的折纸，拥有无穷的可能性，不同的方式，造就不同的未来，可能会对一些事物产生影响。");
        REGISTRATE.addRawLang("patchouli.srelic.book.entries.arrow.eternal_plum.pages.0.text", "前生今生与来生，人虽变而梅不变。蝴蝶与梅相依恋，共渡凋零赴往生。");
        REGISTRATE.addRawLang("patchouli.srelic.book.entries.arrow.frozen_netherite_alloy.pages.0.text", "坚硬的合金经过极寒的淬炼，蕴含着冰雪的力量，隐约可以看到冰雪的缩影。");
        REGISTRATE.addRawLang("patchouli.srelic.book.entries.arrow.flame_netherite_alloy.pages.0.text", "来自下界的金属，经过岩浆的淬炼，蕴含熔岩的力量，隐约可以听到岩浆的轰鸣。");
        REGISTRATE.addRawLang("patchouli.srelic.book.entries.arrow.oceanic_netherite_alloy.pages.0.text", "温柔且坚韧的海洋将力量赋予下界合金，使其拥有潮水的力量与波纹的和谐。");
        REGISTRATE.addRawLang("patchouli.srelic.book.entries.arrow.thunder_netherite_alloy.pages.0.text", "经历神的怒火，下界合金更加完善，上面环绕的雷电展现其蕴含的强大力量。");
        REGISTRATE.addRawLang("patchouli.srelic.book.entries.arrow.demon_ingot.pages.0.text", "在下界之星的介入下，四种下界合金融合而产生的不应存在之物，世界为它赋予无与伦比的力量。（什么电王顶点形态啊）");
        REGISTRATE.addRawLang("patchouli.srelic.book.entries.arrow.sakura_steel_ingot.pages.0.text", "铁锭与樱花相结合的材料，有着钢铁的硬度和樱花的香气，表面的樱纹使其更具美感。");
        REGISTRATE.addRawLang("patchouli.srelic.book.entries.arrow.ex_star.pages.0.text", "极致的实体化，可以淬炼武器，激发其蕴含的力量，获取新的能力，帮助武器超越极限。");
        REGISTRATE.addRawLang("patchouli.srelic.book.entries.arrow.crimson_core.pages.0.text", "");
        REGISTRATE.addRawLang("patchouli.srelic.book.entries.arrow.crimson_core.pages.0.easter-egg", "");


        REGISTRATE.addRawLang("se.slashblade.wither_edge.desc", "攻击成功施加凋零效果");
        REGISTRATE.addRawLang("slashblade.tooltip.srelic.santa_caterina", "「复仇的圣女持剑归来」");
        REGISTRATE.addRawLang("slashblade.tooltip.srelic.santa_caterina_1", "「带着仇人的名单」");


        // 拔刀技
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.XDRIVE), "X幻影刃");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.WitherBreaker), "幻影衰落矢·哀");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.EXPLANATION), "幻影爆裂驱动");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.THO), "影·无想的一刀");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.BIG_DRIVE), "千兆·极巨幻影刃");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.Rappa), "缭乱忍法·手里剑");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.HQUAN), "黑洞吞噬万物灭");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.FORDEADCRY), "为逝者哀哭");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.NEODRIVE), "幻影刃驱动·极");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.HOMERUN), "安息全垒打");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.BIG_DRIVEF), "千兆·极巨幻影刃·横");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.YUNLI), "堪破·斩");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.BIGSLASH), "斩·无赦");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.Heita), "转圈圈！");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.SummoningThunderNi), "唤霆霓");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.confused), "燎乱忍法·万千忍杀");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.Bloodspirit), "往生愿");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.Closingsong), "红莲谢幕曲");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.pure_elegy), "纯美之技艺");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.ButterflyDance), "蝶刃");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.Judgmentcube), "裁决魔方");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.the_moment_when_the_scale_collapses), "天秤崩落之刻");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.SonAta), "二间岚啸鸣");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.Whitenight), "誓约之舞·白夜");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.BreakSky), "凿破大荒");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.Icemusic), "赋格奏鸣曲");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.RedScarslash), "赤痕斩击");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.SKY_EXPLOSION_SWORD), "剑技·天空·幻影风暴");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.SKY_WAVE_EDGE), "剑技·天空·龙胆波涛");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.CELESTIAL_STRIKE), "决胜轰解");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.ICE_EDGE), "雪葬");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.AFFLORDITE), "血刺玫");
        REGISTRATE.addRawLang(CreateSaLang(SRslashArtRegsitry.GoGogo), "迅速居合");


        // 特殊效果
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.FLAMEROSION), "火焰侵蚀");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.MAXBREAK), "上限突破·救世光辉");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.LICH), "亡灵序曲");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.LICH) + ".desc", "敌人存在凋零效果时，你每次攻击都会让其层数+!");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.LICH) + ".desc_1", "十层时引爆并发射高倍率幻影刃");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.WhiteMaker), "构造即此刻");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.WhiteMaker) + ".desc", "每次攻击概率投射一道幻影刃");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_DESTRUCTION), "§6命途·毁灭");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_DESTRUCTION) + ".desc", "§6生命低于30%时获得150%攻击加成和持续恢复\n受击有25%概率触发伤害反弹");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_DESTRUCTION) + ".text", "星神崩星碎辰，耀变临渊，万物归尘，人魂于毁灭中淬炼，文明在耀变中新生。");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_DESTRUCTION) + ".easter-egg", "当你点燃第一颗恒星时——记住，那火光是你献给终焉的婚烛");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_THE_HUNT), "§6命途·巡猎");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_THE_HUNT) + ".desc", "§6每次攻击积累「星矢」层数，7层后释放高额伤害");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_THE_HUNT) + ".text", "审判罪恶，万殛狩渊，星矢破空，渊狩无归，于至暗中迸发复仇烈光，以毁灭创造新生。");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_THE_HUNT) + ".easter-egg", "当第一支箭离弦时——听，弓弦震响是万千湮灭文明的遗恨共鸣 ");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_PRESERVATION), "§6命途·存护");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_PRESERVATION) + ".desc", "§6获得伤害吸收");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_PRESERVATION) + ".text", "琥珀坚固，铸就城墙，铁誓护疆，晶泪封誓，永屹晨碑，千星共御。");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_THE_GLUTTONY) + "easter-egg", "当琥珀巨墙拔地而起时——看，墙痕是文明的伤疤，墙顶是未熄的星火  ");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_THE_GLUTTONY), "§6命途·贪饕");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_THE_GLUTTONY) + ".desc", "§6攻击时吸血10%伤害值\n每次攻击叠加伤害层数最高十层");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_THE_GLUTTONY) + ".text", "饕星嚼辰，永饥永蚀，将一切化为养料，修复自身，直至吞噬一切。");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_THE_UNDYING) + "easter-egg", "贪饕的震撼在于其荒诞悲剧内核——当他吞下最后一粒星辰时，将发现自己啃噬的竟是镜中倒影");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_THE_UNDYING), "§6命途·不朽");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_THE_UNDYING) + ".desc", "§6濒死时触发免死并恢复50%生命\n获得十秒无敌，冷却十分钟");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_THE_UNDYING) + ".text", "肉身亘古、精魂永续、龙裔承志，天脊承鳞，残鳞继道，渊眠蜕神。当最后一片龙鳞沉入星渊时——看，锈骨中正蠕动新生的初卵");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_THE_UNDYING) + ".easter-egg", "不朽的震撼正在于其破碎性——龙祖陨落昭示：真正的永恒，需以千万次死亡为祭品。 ");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_ABUNDANCE), "§6命途·丰饶");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_ABUNDANCE) + ".desc", "§6每5秒治疗8格内队友\n群体抗性提升效果");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_ABUNDANCE) + ".text", "星神不忍见世间之苦，故降下赐福，赐福成界，护佑众生，一法界心。");
        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.PATH_OF_ABUNDANCE) + ".easter-egg", "当第一滴玉露愈合伤口时——听见了吗？你的骨骼正发出新芽的裂响");

        // 修正重复键问题
        REGISTRATE.addRawLang("se.srelic.shift_nodown", "按下Shift查看SE详细信息");

        // 修正重复键问题
        REGISTRATE.addRawLang("se.srelic.path_of_remembrance", "§6命途·记忆");
        REGISTRATE.addRawLang("se.srelic.path_of_remembrance.desc", "§6攻击有50%的概率冻结目标");
        REGISTRATE.addRawLang("se.srelic.path_of_remembrance.text1", "记忆承载岁月，光锥封存永恒，残响筑城，焚忆燃夜，永眠钟塔，冰封万物。\n");
        REGISTRATE.addRawLang("se.srelic.path_of_remembrance.text2", "当最后一盏记忆灯熄灭时——看，浮黎神躯正绽放亿万湮灭宇宙的余晖。");
        REGISTRATE.addRawLang("se.srelic.path_of_remembrance.easter-egg", "记忆介入轮回，让消散的星光成为刺穿终末黑暗的利刃，以岁月斩破轮回。");
        REGISTRATE.addRawLang("se.srelic.path_of_harmony", "§6命途·同谐");
        REGISTRATE.addRawLang("se.srelic.path_of_harmony.text1", "万人齐颂，千心同律，交织和谐之网，万灵交响，千灵同辉，回荡圣咏共鸣。群星依序，齐誓永谐。");
        REGISTRATE.addRawLang("se.srelic.path_of_harmony.easter-egg", "当孤星试图闪耀时，宇宙沉默；当群星同频时，黑洞亦为之歌咏 ");
        REGISTRATE.addRawLang("se.srelic.path_of_harmony.desc", "§6群体获得力量效果");
        REGISTRATE.addRawLang("se.srelic.path_of_nihility", "§6命途·虚无");
        REGISTRATE.addRawLang("se.srelic.path_of_nihility.text1", "无念无想，万色褪皁，醒寐同棺，万物归寂，空渊吞噬，永燃余烬。");
        REGISTRATE.addRawLang("se.srelic.path_of_nihility.easter-egg", "当你为胜利欢呼时——请听，余烬中传来所有湮灭宇宙的哄笑");
        REGISTRATE.addRawLang("se.srelic.path_of_nihility.desc", "§6攻击成功后给敌方虚弱和挖掘疲劳3");
        REGISTRATE.addRawLang("se.srelic.path_of_propagation", "§6命途·繁育");
        REGISTRATE.addRawLang("se.srelic.path_of_propagation.text", "虫群倾巢，万卵噬星，慈胎孽种，无穷无尽，肉绽天穹，虫灾天劫，寰宇蝗灾。");
        REGISTRATE.addRawLang("se.srelic.path_of_propagation.easter-egg", "当第一枚虫卵嵌入地核时——听，星辰的心跳正渐变成孵化的胎音 ");
        REGISTRATE.addRawLang("se.srelic.path_of_propagation.desc", "§6每次攻击敌人都会生成一只蠹虫（无限增值，但是周围最多40只蠹虫）");
        REGISTRATE.addRawLang("se.srelic.path_of_erudition", "§6命途·智识");
        REGISTRATE.addRawLang("se.srelic.path_of_erudition.text", "焚典开天，万理织宙，冻光穹脑，熵锁归一，知识就是力量，附魔转化为增幅，");
        REGISTRATE.addRawLang("se.srelic.path_of_erudition.easter-egg", "当你解出万物公式时——请记住，被删除的“灵魂变量”正在废墟里冷笑 ");
        REGISTRATE.addRawLang("se.srelic.path_of_erudition.desc", "§6根据武器附魔等级获得额外效果：\n锋利→伤害提升（每级+10%）\n火焰附加→点燃时间翻倍\n抢夺→额外掉落概率");
        REGISTRATE.addRawLang("se.srelic.path_of_elation", "§6命途·欢愉");
        REGISTRATE.addRawLang("se.srelic.path_of_elation.text1", "愚者千面，游戏寰尘，戏谑众生，颠倒是非。");
        REGISTRATE.addRawLang("se.srelic.path_of_elation.text2", "若有人在现实中对你说 “法典该擦擦了” ，记得立刻把手边的书倒过来——或许真有一颗糖等着你。");
        REGISTRATE.addRawLang("se.srelic.path_of_elation.easter-egg", "第〇条：所有规则，皆可被阿哈的喷嚏推翻！\n终极真理：蛋糕比哲学重要——尤其当它砸在你脸上时。");
        REGISTRATE.addRawLang("se.srelic.path_of_elation.desc", "§6将50%治疗量转化为群体恢复\n为受影响目标附加5秒发光效果\n每触发一次群体治疗提升10%移动速度（可叠加3层）");
        REGISTRATE.addRawLang("se.srelic.path_of_equilibrium", "§6命途·均衡");
        REGISTRATE.addRawLang("se.srelic.path_of_equilibrium.text", "均衡维持世间的平衡，万竞息争，赏罚同契，理爆维稳，阴阳相合。 ");
        REGISTRATE.addRawLang("se.srelic.path_of_equilibrium.easter-egg", "当文明为胜利欢呼时——看，仲裁官已在新生的墓碑上刻下倒计时");
        REGISTRATE.addRawLang("se.srelic.path_of_equilibrium.desc", "§6切换武器时转换攻防姿态\n攻击姿态：+150%伤害\n防御姿态：+40%伤害抵抗");
        REGISTRATE.addRawLang("se.srelic.path_of_finality", "§6命途·终末");
        REGISTRATE.addRawLang("se.srelic.path_of_finality.text", "终焉回响，命轮永固，终末降临，恒纪无回，永劫无笑，诸神无泪。");
        REGISTRATE.addRawLang("se.srelic.path_of_finality.easter-egg", "“欢愉”在戏弄中诞生闹剧，而“终末”在沉寂中书写句点——永劫无笑，诸神无泪。");
        REGISTRATE.addRawLang("se.srelic.path_of_finality.desc", "§6每3次连续击杀触发范围爆炸\n受伤时重置连杀计数");
        REGISTRATE.addRawLang("se.srelic.path_of_trailblaze", "§6命途·开拓");
        REGISTRATE.addRawLang("se.srelic.path_of_trailblaze.text1", "凿宙寻迹的列车，穿行在未知之间，无径筑虹，残舵指辰，虚海留辙，绝地创生，留下无名客的故事，创造无名客的辉煌。");
        REGISTRATE.addRawLang("se.srelic.path_of_trailblaze.text2", "当列车撞向宇宙障壁时——听，裂痕中传来万千未诞世界的啼哭与欢呼。");
        REGISTRATE.addRawLang("se.srelic.path_of_trailblaze.easter-egg1", "毁灭中孕育新生的开拓，以自我湮灭为代价唤醒沉睡文明，化不可能为可能。");
        REGISTRATE.addRawLang("se.srelic.path_of_trailblaze.easter-egg2", "真正的航道，要用神骸铺就，最辽阔的边疆，永远以第一个倒下者的名字命名。");
        REGISTRATE.addRawLang("se.srelic.path_of_trailblaze.desc", "§6获得速度3、力量3、村庄英雄1");
        REGISTRATE.addRawLang("se.srelic.path_of_mystery", "§6命途·神秘");
        REGISTRATE.addRawLang("se.srelic.path_of_mystery.text", "歪曲是非，悖论织理，万相遮目，虚构史书，渊识蚀智，痴愚贤者，诡计虚妄，信者为真。");
        REGISTRATE.addRawLang("se.srelic.path_of_mystery.easter-egg", "当你读懂星空时，星空已坍缩成你眼底的灰烬——所谓真相，不过是认知牢狱的新砖");
        REGISTRATE.addRawLang("se.srelic.path_of_mystery.desc", "§6攻击时随机触发以下效果之一：\n时间扭曲/空间裂隙/能量反噬/命运庇护/混沌爆发");
        REGISTRATE.addRawLang("se.srelic.path_of_order", "§6命途·秩序");
        REGISTRATE.addRawLang("se.srelic.path_of_order.text", "秩序维持稳定，稳定给予力量，万像归模，永锢颂碑，戒律锁星，圣裁雕刀。");
        REGISTRATE.addRawLang("se.srelic.path_of_order.easter-egg", "当你为永恒法典欢呼时——听，齿轮咬合的声响正碾碎你最后一缕呼吸  ");
        REGISTRATE.addRawLang("se.srelic.path_of_order.desc", "§6未受负面效果时获得攻防加成\n攻击清除敌方所有增益效果");
        REGISTRATE.addRawLang("se.srelic.path_of_purity", "§6命途·纯美");
        REGISTRATE.addRawLang("se.srelic.path_of_purity.text1", "碎镜星冕，泪塑圣境，追求之人，虹脱囚牢，万诗成刃，千眸凝辉。");
        REGISTRATE.addRawLang("se.srelic.path_of_purity.text2", "当最后一滴泪结晶时——看，裂痕中的星冕比完整宇宙更接近天国");
        REGISTRATE.addRawLang("se.srelic.path_of_purity.easter-egg", "银白色的骑士追求世间之美，用手中长枪扫清黑暗，用毕生追寻镜中碎光。");
        REGISTRATE.addRawLang("se.srelic.path_of_purity.desc", "§6获得抗性提升3");
        REGISTRATE.addRawLang("se.srelic.path_of_healing", "§6丰饶·慈悲");
        REGISTRATE.addRawLang("se.srelic.path_of_healing.text", "千眸慈视，不忍见众生之苦，降下赐福，愿促世间万物和谐，但事与愿违，慈肉噬骨。");
        REGISTRATE.addRawLang("se.srelic.path_of_healing.easter-egg", "当魔力被释放时——听，星辰的明暗中传来所有虚无的noise");
        REGISTRATE.addRawLang("se.srelic.path_of_healing.desc", "§6你的攻击将会治疗受击者");


        REGISTRATE.addRawLang("se.slashblade_addon.burst_drive.desc", "§6每次攻击都触发幻影刃");

        REGISTRATE.addRawLang("se.srelic.void_finale", "终景·虚无之终末");
        REGISTRATE.addRawLang("se.srelic.void_finale.desc", "§6每次攻击给敌人施加一层虚弱效果，满七层引爆");

        REGISTRATE.addRawLang("se.srelic.celestial_collapse", "毁灭·规则击破");
        REGISTRATE.addRawLang("se.srelic.celestial_collapse.desc", "§6通过连续攻击积累最多7层连击，每层提供15%伤害加成");
        REGISTRATE.addRawLang("se.srelic.celestial_collapse.desc_1", "§67层时触发破空斩，造成2.5倍伤害");
        REGISTRATE.addRawLang("se.srelic.blazing_heart_shield", "存护·熔火护心");
        REGISTRATE.addRawLang("se.srelic.blazing_heart_shield.desc", "§6生命低于30%时自动生成熔火护盾,吸收70%伤害，持续20秒,冷却30秒");
        REGISTRATE.addRawLang("se.srelic.blazing_heart_shield.desc_1", "§6护盾期间受击时，对攻击者施加5秒灼烧");
        REGISTRATE.addRawLang("se.srelic.blazing_heart_shield.desc_2", "§6护盾结束时引发火焰爆炸（4格范围）");
        REGISTRATE.addRawLang("se.srelic.blazing_heart_shield.desc_3", "§6并获得50%伤害值的治疗");
        REGISTRATE.addRawLang("se.srelic.lament_of_phantoms", "§5虚无·幽魂的哀叹");
        REGISTRATE.addRawLang("se.srelic.lament_of_phantoms.desc", "§c生命≤25%时：");
        REGISTRATE.addRawLang("se.srelic.lament_of_phantoms.desc_1", "§e• 转移50%伤害给攻击者");
        REGISTRATE.addRawLang("se.srelic.lament_of_phantoms.desc_2", "§b• 获得3秒无敌§c（附带虚弱II）");
        REGISTRATE.addRawLang("se.srelic.lament_of_phantoms.desc_3", "§d• 伤害值的30%转化为治疗");
        REGISTRATE.addRawLang("se.srelic.lament_of_phantoms.desc_4", "§6冷却时间：30秒");
        REGISTRATE.addRawLang("se.srelic.thunderstorm_slash", "「电磁风暴」");
        REGISTRATE.addRawLang("se.srelic.thunderstorm_slash.desc", "§6每次攻击触发5次连锁闪电,每次连锁造成60%前次伤害");
        REGISTRATE.addRawLang("se.srelic.thunderstorm_slash.desc_1", "§6每次攻击叠加感电层数（最大10层）层数满时触发全局闪电冲击波");
        REGISTRATE.addRawLang("se.srelic.thunderstorm_slash.desc_2", "§6感电满层时激活10秒超载状态");


// 炽焰过载
        REGISTRATE.addRawLang("se.srelic.sam_overdrive", "§6过载·炽焰形态");
        REGISTRATE.addRawLang("se.srelic.sam_overdrive.desc", "§6【熔核过载系统】");
        REGISTRATE.addRawLang("se.srelic.sam_overdrive.desc_1", "§e• 每次攻击积累35点过热值（最大300）");
        REGISTRATE.addRawLang("se.srelic.sam_overdrive.desc_2", "§e• 过热≥250时进入20秒过载模式：");
        REGISTRATE.addRawLang("se.srelic.sam_overdrive.desc_3", "§b  - 移速提升III §7| §b伤害抗性II");
        REGISTRATE.addRawLang("se.srelic.sam_overdrive.desc_4", "§6【炽焰突袭】");
        REGISTRATE.addRawLang("se.srelic.sam_overdrive.desc_5", "§e• 每1秒可触发火焰突进");
        REGISTRATE.addRawLang("se.srelic.sam_overdrive.desc_6", "§e• 冲刺路径造成12点范围伤害");
        REGISTRATE.addRawLang("se.srelic.sam_overdrive.desc_7", "§6【焚烬领域】");
        REGISTRATE.addRawLang("se.srelic.sam_overdrive.desc_8", "§e• 过载期间生成5格灼烧领域");
        REGISTRATE.addRawLang("se.srelic.sam_overdrive.desc_9", "§e• 领域内敌人每秒受到3点火焰伤害");
        REGISTRATE.addRawLang("se.srelic.sam_overdrive.desc_10", "§c冷却时间：§730秒");
        REGISTRATE.addRawLang("se.srelic.sam_overdrive.desc_11", "§e如果副手也有这把刀则允许启用进行双持·大开大合");

        REGISTRATE.addRawLang("se.srelic.aquatic_sanctuary", "§b圣礼·水仙十字");
        REGISTRATE.addRawLang("se.srelic.aquatic_sanctuary.desc", "§b【生命共鸣】");
        REGISTRATE.addRawLang("se.srelic.aquatic_sanctuary.desc_1", "§e• 每2秒恢复3%最大生命值");
        REGISTRATE.addRawLang("se.srelic.aquatic_sanctuary.desc_2", "§e• 治疗时产生水环粒子特效");
        REGISTRATE.addRawLang("se.srelic.aquatic_sanctuary.desc_3", "§b【净化领域】");
        REGISTRATE.addRawLang("se.srelic.aquatic_sanctuary.desc_4", "§e• 6格范围内队友获得抗性提升");
        REGISTRATE.addRawLang("se.srelic.aquatic_sanctuary.desc_5", "§e• 清除中毒和凋零效果");
        REGISTRATE.addRawLang("se.srelic.aquatic_sanctuary.desc_6", "§b【潮汐守护】");
        REGISTRATE.addRawLang("se.srelic.aquatic_sanctuary.desc_7", "§e• 生命≤25%时触发水波冲击");
        REGISTRATE.addRawLang("se.srelic.aquatic_sanctuary.desc_8", "§e• 获得吸收8点伤害的护盾");
        REGISTRATE.addRawLang("se.srelic.aquatic_sanctuary.desc_9", "§c冷却时间：§75秒");
        REGISTRATE.addRawLang("se.srelic.aquatic_sanctuary.desc_10", "§e如果副手也有这把刀则允许启用进行双持·细心姿态");

        REGISTRATE.addRawLang("se.srelic.chaos_breaker", "§5七雷·雷殛连星");
        REGISTRATE.addRawLang("se.srelic.chaos_breaker.desc", "§5【极速连斩】");
        REGISTRATE.addRawLang("se.srelic.chaos_breaker.desc_1", "§d• 每次攻击提升15%伤害（最大7层）");
        REGISTRATE.addRawLang("se.srelic.chaos_breaker.desc_2", "§d• 2秒未攻击重置连击数");
        REGISTRATE.addRawLang("se.srelic.chaos_breaker.desc_3", "§5【雷核充能】");
        REGISTRATE.addRawLang("se.srelic.chaos_breaker.desc_4", "§d• 每3连击积累1个充能");
        REGISTRATE.addRawLang("se.srelic.chaos_breaker.desc_5", "§d• 满3充能进入星裂模式");
        REGISTRATE.addRawLang("se.srelic.chaos_breaker.desc_6", "§5【星裂爆发】");
        REGISTRATE.addRawLang("se.srelic.chaos_breaker.desc_7", "§d• 移速提升III + 伤害提升II");
        REGISTRATE.addRawLang("se.srelic.chaos_breaker.desc_8", "§d• 攻击附带120度扇形雷击");
        REGISTRATE.addRawLang("se.srelic.chaos_breaker.desc_9", "§c持续时间：§75秒");

        REGISTRATE.addRawLang("se.srelic.rianbowshoot", "§5七元·夜魂子弹");
        REGISTRATE.addRawLang("se.srelic.rianbowshoot.desc", "§5【七重元素子弹】");
        REGISTRATE.addRawLang("se.srelic.rianbowshoot.desc_1", "§5每次攻击都会打出元素子弹，分别是:");
        REGISTRATE.addRawLang("se.srelic.rianbowshoot.desc_2", "§5火：点燃");
        REGISTRATE.addRawLang("se.srelic.rianbowshoot.desc_3", "§5风：高倍率");
        REGISTRATE.addRawLang("se.srelic.rianbowshoot.desc_4", "§5岩：虚弱");
        REGISTRATE.addRawLang("se.srelic.rianbowshoot.desc_5", "§5冰：缓慢");
        REGISTRATE.addRawLang("se.srelic.rianbowshoot.desc_6", "§5水：瞬间伤害");
        REGISTRATE.addRawLang("se.srelic.rianbowshoot.desc_7", "§5草：中毒");
        REGISTRATE.addRawLang("se.srelic.rianbowshoot.desc_8", "§5雷：雷击");

        REGISTRATE.addRawLang("se.srelic.kafka_strings", "§5蛛网·命运提线");
        REGISTRATE.addRawLang("se.srelic.kafka_strings.desc", "§5【神经毒素】");
        REGISTRATE.addRawLang("se.srelic.kafka_strings.desc_1", "§d• 攻击叠加毒素层数（最大5层）");
        REGISTRATE.addRawLang("se.srelic.kafka_strings.desc_2", "§d• 每层使目标减速并持续受到伤害");
        REGISTRATE.addRawLang("se.srelic.kafka_strings.desc_3", "§5【雷暴蛛网】");
        REGISTRATE.addRawLang("se.srelic.kafka_strings.desc_4", "§d• 满层毒素引发连锁闪电（3次跳跃）");
        REGISTRATE.addRawLang("se.srelic.kafka_strings.desc_5", "§d• 每次跳跃伤害衰减30%");
        REGISTRATE.addRawLang("se.srelic.kafka_strings.desc_6", "§5【傀儡操控】");
        REGISTRATE.addRawLang("se.srelic.kafka_strings.desc_7", "§d• 30%概率使被雷击中的目标暂时失智");
        REGISTRATE.addRawLang("se.srelic.kafka_strings.desc_8", "§d• 失控目标无法攻击且发光10秒");
        REGISTRATE.addRawLang("se.srelic.kafka_strings.desc_9", "§8冷却时间：§720秒");

        REGISTRATE.addRawLang("se.srelic.jingyuan_thunder", "§6神策·天霆断空");
        REGISTRATE.addRawLang("se.srelic.jingyuan_thunder.desc", "§6【神君威仪】");
        REGISTRATE.addRawLang("se.srelic.jingyuan_thunder.desc_1", "§e• 每次攻击积累神君层数（最大40层）");
        REGISTRATE.addRawLang("se.srelic.jingyuan_thunder.desc_2", "§e• 每层提升20%伤害，5秒未攻击重置");
        REGISTRATE.addRawLang("se.srelic.jingyuan_thunder.desc_3", "§6【雷殛领域】");
        REGISTRATE.addRawLang("se.srelic.jingyuan_thunder.desc_4", "§e• 5格范围内持续电击敌人");
        REGISTRATE.addRawLang("se.srelic.jingyuan_thunder.desc_5", "§e• 伤害基于当前层数");
        REGISTRATE.addRawLang("se.srelic.jingyuan_thunder.desc_6", "§6【天霆断空斩】");
        REGISTRATE.addRawLang("se.srelic.jingyuan_thunder.desc_7", "§e• 消耗10层发动扇形范围斩击");
        REGISTRATE.addRawLang("se.srelic.jingyuan_thunder.desc_8", "§e• 对主目标触发3次闪电连锁");
        REGISTRATE.addRawLang("se.srelic.jingyuan_thunder.desc_9", "§c冷却时间：§710秒");

        REGISTRATE.addRawLang("se.srelic.nero_devil_breaker", "§c魔剑·绯红序曲");
        REGISTRATE.addRawLang("se.srelic.nero_devil_breaker.desc", "§c【红刀充能】");
        REGISTRATE.addRawLang("se.srelic.nero_devil_breaker.desc_1", "§4• 每次攻击积累充能（最大3层）");
        REGISTRATE.addRawLang("se.srelic.nero_devil_breaker.desc_2", "§4• 每层提升50%伤害，5秒未攻击重置");
        REGISTRATE.addRawLang("se.srelic.nero_devil_breaker.desc_3", "§c【恶魔投掷】");
        REGISTRATE.addRawLang("se.srelic.nero_devil_breaker.desc_4", "§4• 近战攻击时抓取并投掷敌人（4秒冷却）");
        REGISTRATE.addRawLang("se.srelic.nero_devil_breaker.desc_5", "§4• 落地时产生冲击波伤害");
        REGISTRATE.addRawLang("se.srelic.nero_devil_breaker.desc_6", "§c【超载爆发】");
        REGISTRATE.addRawLang("se.srelic.nero_devil_breaker.desc_7", "§4• 满充能时激活3秒超载状态");
        REGISTRATE.addRawLang("se.srelic.nero_devil_breaker.desc_8", "§4• 期间攻击附加火焰伤害");
        REGISTRATE.addRawLang("se.srelic.nero_devil_breaker.desc_9", "§8冷却时间：§715秒");

        REGISTRATE.addRawLang("se.srelic.herta_cryo_stage", "§b霜星·永冻剧场");
        REGISTRATE.addRawLang("se.srelic.herta_cryo_stage.desc", "§b【冰霜充能】");
        REGISTRATE.addRawLang("se.srelic.herta_cryo_stage.desc_1", "§3• 每次攻击积累冰霜层数（最大5层）");
        REGISTRATE.addRawLang("se.srelic.herta_cryo_stage.desc_2", "§3• 每层提升冻结概率和领域伤害");
        REGISTRATE.addRawLang("se.srelic.herta_cryo_stage.desc_3", "§b【冰霜人偶】");
        REGISTRATE.addRawLang("se.srelic.herta_cryo_stage.desc_4", "§3• 每5秒召唤自动攻击的冰霜人偶");
        REGISTRATE.addRawLang("se.srelic.herta_cryo_stage.desc_5", "§3• 人偶发射冰锥造成4点伤害");
        REGISTRATE.addRawLang("se.srelic.herta_cryo_stage.desc_6", "§b【绝对零度】");
        REGISTRATE.addRawLang("se.srelic.herta_cryo_stage.desc_7", "§3• 6格范围内持续造成冰霜伤害");
        REGISTRATE.addRawLang("se.srelic.herta_cryo_stage.desc_8", "§3• 基础30%概率冻结敌人");
        REGISTRATE.addRawLang("se.srelic.herta_cryo_stage.desc_9", "§b冻结时附加§c缓慢III§b和§c虚弱I");

        // 在特殊效果区块添加以下内容
        REGISTRATE.addRawLang("se.srelic.welt_se", "§5虚数·时空奇点");
        REGISTRATE.addRawLang("se.srelic.welt_se.desc", "§5【重力操控系统】");
        REGISTRATE.addRawLang("se.srelic.welt_se.desc_1", "§d• 攻击施加减速");
        REGISTRATE.addRawLang("se.srelic.welt_se.desc_2", "§d• 生成反向传送门粒子轨迹");
        REGISTRATE.addRawLang("se.srelic.welt_se.desc_3", "§5【时空停滞领域】");
        REGISTRATE.addRawLang("se.srelic.welt_se.desc_4", "§d• 30%概率触发黑洞（15秒冷却）");
        REGISTRATE.addRawLang("se.srelic.welt_se.desc_5", "§d• 范围内敌人挖掘疲劳V+发光");
        REGISTRATE.addRawLang("se.srelic.welt_se.desc_6", "§5【虚数坍缩】");
        REGISTRATE.addRawLang("se.srelic.welt_se.desc_7", "§d• 每击叠加15%伤害（最大7层）");
        REGISTRATE.addRawLang("se.srelic.welt_se.desc_8", "§d• 满层触发浮空爆炸+电磁火花");
        REGISTRATE.addRawLang("se.srelic.welt_se.desc_9", "§8领域持续时间：§710秒");
        REGISTRATE.addRawLang("se.srelic.welt_se.desc_10", "§8坍缩冷却：§715秒");

        REGISTRATE.addRawLang("se.srelic.raiden_shogun", "§d永恒·雷电将军");
        REGISTRATE.addRawLang("se.srelic.raiden_shogun.desc", "§5【诸愿百眼之轮】");
        REGISTRATE.addRawLang("se.srelic.raiden_shogun.desc_1", "§5• 攻击积累愿力层数（最大60层）");
        REGISTRATE.addRawLang("se.srelic.raiden_shogun.desc_2", "§5• 每层提供0.5%伤害加成");
        REGISTRATE.addRawLang("se.srelic.raiden_shogun.desc_3", "§d【雷罚恶曜之眼】");
        REGISTRATE.addRawLang("se.srelic.raiden_shogun.desc_4", "§5• 8格范围持续雷伤领域");
        REGISTRATE.addRawLang("se.srelic.raiden_shogun.desc_5", "§5• 30%概率标记敌人附加发光");
        REGISTRATE.addRawLang("se.srelic.raiden_shogun.desc_6", "§d【奥义·梦想真说】");
        REGISTRATE.addRawLang("se.srelic.raiden_shogun.desc_7", "§5• 消耗全部愿力释放范围斩击");
        REGISTRATE.addRawLang("se.srelic.raiden_shogun.desc_8", "§5• 造成基础20+愿力×0.4的伤害");
        REGISTRATE.addRawLang("se.srelic.raiden_shogun.desc_9", "§8冷却时间：§71分钟");
        REGISTRATE.addRawLang("se.srelic.raiden_shogun.desc_10", "§d击杀敌人重置奥义冷却");

        // 特殊效果
        REGISTRATE.addRawLang("se.srelic.eternal_flow", "§b千古·洑流潮涌");
        REGISTRATE.addRawLang("se.srelic.eternal_flow.desc", "§b【潮汐充能】");
        REGISTRATE.addRawLang("se.srelic.eternal_flow.desc_1", "§3• 每次攻击积累潮汐层数（最大7层）");
        REGISTRATE.addRawLang("se.srelic.eternal_flow.desc_2", "§3• 每层提升3%群体治疗量");
        REGISTRATE.addRawLang("se.srelic.eternal_flow.desc_3", "§b【水华护盾】");
        REGISTRATE.addRawLang("se.srelic.eternal_flow.desc_4", "§3• 生命低于30%时消耗3层生成护盾");
        REGISTRATE.addRawLang("se.srelic.eternal_flow.desc_5", "§3• 吸收70%伤害，持续5秒");
        REGISTRATE.addRawLang("se.srelic.eternal_flow.desc_6", "§b【激流爆发】");
        REGISTRATE.addRawLang("se.srelic.eternal_flow.desc_7", "§3• 消耗7层释放锥形水流冲击");
        REGISTRATE.addRawLang("se.srelic.eternal_flow.desc_8", "§3• 造成基础12+层数加成的伤害");

        REGISTRATE.addRawLang("se.srelic.avenger_jeanne", "§4复仇者·贞德");
        REGISTRATE.addRawLang("se.srelic.avenger_jeanne.desc", "§4【复仇之炎】");
        REGISTRATE.addRawLang("se.srelic.avenger_jeanne.desc_1", "§c• 受击时35%概率反击，施加8秒黑炎");
        REGISTRATE.addRawLang("se.srelic.avenger_jeanne.desc_2", "§c• 攻击附加持续15秒的缓慢");
        REGISTRATE.addRawLang("se.srelic.avenger_jeanne.desc_3", "§4【怨念收集】");
        REGISTRATE.addRawLang("se.srelic.avenger_jeanne.desc_4", "§c• 每次受击积累复仇层数（最大7层）");
        REGISTRATE.addRawLang("se.srelic.avenger_jeanne.desc_5", "§c• 每层提升黑炎伤害10%");
        REGISTRATE.addRawLang("se.srelic.avenger_jeanne.desc_6", "§4【宝具解放】");
        REGISTRATE.addRawLang("se.srelic.avenger_jeanne.desc_7", "§c• 消耗7层释放12范围AOE真实伤害");
        REGISTRATE.addRawLang("se.srelic.avenger_jeanne.desc_8", "§c• 获得200%伤害加成与抗性提升");
        REGISTRATE.addRawLang("se.srelic.avenger_jeanne.desc_9", "§8冷却时间：§730秒");

        REGISTRATE.addRawLang("se.srelic.blood_sakura", "§c魂念·血樱寂灭");
        REGISTRATE.addRawLang("se.srelic.blood_sakura.desc", "§4【血樱刻印】");
        REGISTRATE.addRawLang("se.srelic.blood_sakura.desc_1", "§c• 攻击时40%概率附加血樱标记（上限5层）");
        REGISTRATE.addRawLang("se.srelic.blood_sakura.desc_2", "§c• 每层提升30%对该目标伤害");
        REGISTRATE.addRawLang("se.srelic.blood_sakura.desc_3", "§4【生命汲取】");
        REGISTRATE.addRawLang("se.srelic.blood_sakura.desc_4", "§c• 攻击时吸收目标25%最大生命值");
        REGISTRATE.addRawLang("se.srelic.blood_sakura.desc_5", "§4【妖刀解放】");
        REGISTRATE.addRawLang("se.srelic.blood_sakura.desc_6", "§c• 消耗3层释放8格范围斩击（180%伤害）");
        REGISTRATE.addRawLang("se.srelic.blood_sakura.desc_7", "§c• 获得10秒急速III效果");
        REGISTRATE.addRawLang("se.srelic.blood_sakura.desc_8", "§8冷却时间：§720秒");

        REGISTRATE.addRawLang("se.srelic.crimson_moon", "§4赤月·永夜巡礼");
        REGISTRATE.addRawLang("se.srelic.crimson_moon.desc", "§5【血月刻印】");
        REGISTRATE.addRawLang("se.srelic.crimson_moon.desc_1", "§d• 暴击时凝聚「血之回响」提升15%暴伤（上限8层）");
        REGISTRATE.addRawLang("se.srelic.crimson_moon.desc_2", "§5【暮夜形态】");
        REGISTRATE.addRawLang("se.srelic.crimson_moon.desc_3", "§d• 生命低于30%时化身暗影，获得隐身与伤害提升");
        REGISTRATE.addRawLang("se.srelic.crimson_moon.desc_4", "§5【猩红盛宴】");
        REGISTRATE.addRawLang("se.srelic.crimson_moon.desc_5", "§d• 暗影状态下攻击附加血月斩击");
        REGISTRATE.addRawLang("se.srelic.crimson_moon.desc_6", "§d• 吸血效果提升150%并附加范围真实伤害");

        REGISTRATE.addRawLang("se.srelic.inferno_blaze", "§6炎狱劫火·天烬");
        REGISTRATE.addRawLang("se.srelic.inferno_blaze.desc", "§c【熔火核心】");
        REGISTRATE.addRawLang("se.srelic.inferno_blaze.desc_1", "§6• 每次攻击积累热能，每层提升20%火焰伤害");
        REGISTRATE.addRawLang("se.srelic.inferno_blaze.desc_2", "§c【赤炎之径】");
        REGISTRATE.addRawLang("se.srelic.inferno_blaze.desc_3", "§6• 移动时留下燃烧路径，持续点燃敌人");
        REGISTRATE.addRawLang("se.srelic.inferno_blaze.desc_4", "§c【天烬爆发】");
        REGISTRATE.addRawLang("se.srelic.inferno_blaze.desc_5", "§6• 达到8层时释放熔核冲击，重置热能计数");
        REGISTRATE.addRawLang("se.srelic.inferno_blaze.desc_6", "§6• 生成半径5格的烈焰风暴持续15秒");


        REGISTRATE.addRawLang("se.srelic.wolf_soul", "§4北境狼魂·咆哮");
        REGISTRATE.addRawLang("se.srelic.wolf_soul.desc", "§4【凛冬意志】");
        REGISTRATE.addRawLang("se.srelic.wolf_soul.desc_1", "§c• 生命≤40%时：伤害+30%，攻击积累「狼魂」");
        REGISTRATE.addRawLang("se.srelic.wolf_soul.desc_2", "§c• 生命≤25%时：伤害+60%，40%吸血");
        REGISTRATE.addRawLang("se.srelic.wolf_soul.desc_3", "§4【永冻连击】");
        REGISTRATE.addRawLang("se.srelic.wolf_soul.desc_4", "§c• 提升伤害");
        REGISTRATE.addRawLang("se.srelic.wolf_soul.desc_5", "§c• 攻击获得10格团队§b力量III§c/§b速度II");
        REGISTRATE.addRawLang("se.srelic.wolf_soul.desc_6", "§4【终末狼嚎】");
        REGISTRATE.addRawLang("se.srelic.wolf_soul.desc_7", "§c•攻击概率释放冰霜冲击波：施加§4虚弱III§c/§5发光");
        REGISTRATE.addRawLang("se.srelic.wolf_soul.desc_8", "§8冷却：§725秒");


        REGISTRATE.addRawLang("se.srelic.dice_effect", "§d命运骰子");
        REGISTRATE.addRawLang("se.srelic.dice_effect.desc", "§5【随机法则】");
        REGISTRATE.addRawLang("se.srelic.dice_effect.desc_1", "§d• 20%概率造成50%伤害");
        REGISTRATE.addRawLang("se.srelic.dice_effect.desc_2", "§d• 40%概率正常伤害");
        REGISTRATE.addRawLang("se.srelic.dice_effect.desc_3", "§d• 39%概率治疗目标600%伤害值");
        REGISTRATE.addRawLang("se.srelic.dice_effect.desc_4", "§d• 1%概率造成1000%暴击伤害");
        REGISTRATE.addRawLang("se.srelic.dice_effect.text1", "无穷面的骰子，它是运气的提现，渊面判命，天劫戏谑，万千结局坍缩于一掷之间。");
        REGISTRATE.addRawLang("se.srelic.dice_effect.text2", "无穷可能性在骰落时焚灭成唯一。欧非一念，天堂地狱。");
        REGISTRATE.addRawLang("se.srelic.dice_effect.text3", "当听见骰子在虚空中咯咯轻笑时——  记住，不是你在掷骰，而是骰在掷你。");
        REGISTRATE.addRawLang("se.srelic.dice_effect.easter-egg", "狂赌回合开启！执骰互斫——下一刀，天诛还是天赐？毕竟赌狗最爱的不是赢，而是开骰前心跳停拍的刹那。");


        REGISTRATE.addRawLang("se.srelic.truth_realm", "§6终焉律·星之归");
        REGISTRATE.addRawLang("se.srelic.truth_realm.desc", "§e【时空奇点】");
        REGISTRATE.addRawLang("se.srelic.truth_realm.desc_1", "§b• 攻击积累「时之熵」层数（最大36层）");
        REGISTRATE.addRawLang("se.srelic.truth_realm.desc_2", "§b• 每层提升5%攻速与3%攻击力");
        REGISTRATE.addRawLang("se.srelic.truth_realm.desc_3", "§e【逆时回廊】");
        REGISTRATE.addRawLang("se.srelic.truth_realm.desc_4", "§b• 受击时消耗全部层数：");
        REGISTRATE.addRawLang("se.srelic.truth_realm.desc_5", "§d  - 每层恢复2%生命并重置负面状态");
        REGISTRATE.addRawLang("se.srelic.truth_realm.desc_6", "§d  - 生成时之护盾（吸收量=层数×15%）");
        REGISTRATE.addRawLang("se.srelic.truth_realm.desc_7", "§e【终焉重构】");
        REGISTRATE.addRawLang("se.srelic.truth_realm.desc_8", "§b• 护盾破裂时触发时空停滞：");
        REGISTRATE.addRawLang("se.srelic.truth_realm.desc_9", "§d  - 冻结8格内敌人动作3秒");
        REGISTRATE.addRawLang("se.srelic.truth_realm.desc_10", "§d  - 发射12道时空裂隙斩击");
        REGISTRATE.addRawLang("se.srelic.truth_realm.desc_11", "§8冷却时间：§72分钟");


        REGISTRATE.addRawLang("se.srelic.perfect_sanctuary", "§b真我·无瑕护佑");
        REGISTRATE.addRawLang("se.srelic.perfect_sanctuary.desc", "§6【无瑕穹顶】");
        REGISTRATE.addRawLang("se.srelic.perfect_sanctuary.desc_1", "§b生命值低于50%时自动生成护盾（吸收量=已损失生命×200%）");
        REGISTRATE.addRawLang("se.srelic.perfect_sanctuary.desc_2", "§6【凋零领域】");
        REGISTRATE.addRawLang("se.srelic.perfect_sanctuary.desc_3", "§b护盾期间每秒施加凋零III（8格范围）");
        REGISTRATE.addRawLang("se.srelic.perfect_sanctuary.desc_4", "§6【水晶爆破】");
        REGISTRATE.addRawLang("se.srelic.perfect_sanctuary.desc_5", "§b护盾结束时造成剩余护盾值80%的范围伤害");
        REGISTRATE.addRawLang("se.srelic.perfect_sanctuary.desc_6", "§b并为友方恢复10%最大生命值");
        REGISTRATE.addRawLang("se.srelic.perfect_sanctuary.desc_7", "§8冷却时间：§730秒");

        REGISTRATE.addRawLang("se.srelic.frost_forging", "§b寒锋聚气");
        REGISTRATE.addRawLang("se.srelic.frost_forging.desc", "§b【寒锋叠加】");
        REGISTRATE.addRawLang("se.srelic.frost_forging.desc_1", "§7未攻击时每秒叠加1层（上限10层）");
        REGISTRATE.addRawLang("se.srelic.frost_forging.desc_2", "§b【霜刃爆发】");
        REGISTRATE.addRawLang("se.srelic.frost_forging.desc_3", "§7攻击消耗层数，每层附加1.5%目标当前生命伤害");
        REGISTRATE.addRawLang("se.srelic.frost_forging.desc_4", "§b【凛冬庇护】");
        REGISTRATE.addRawLang("se.srelic.frost_forging.desc_5", "§7每层提供1%减伤和移速提升");
        REGISTRATE.addRawLang("se.srelic.frost_forging.desc_6", "§b【霜裂蔓延】");
        REGISTRATE.addRawLang("se.srelic.frost_forging.desc_7", "§7消耗≥5层时附加缓慢，传递效果叠加寒锋");

        REGISTRATE.addRawLang("se.srelic.ghost_thunder", "§d百鬼噬魂·雷劫");
        REGISTRATE.addRawLang("se.srelic.ghost_thunder.desc", "§5【噬魂标记】");
        REGISTRATE.addRawLang("se.srelic.ghost_thunder.desc_1", "§d击杀时50%概率触发范围雷伤并叠加标记");
        REGISTRATE.addRawLang("se.srelic.ghost_thunder.desc_2", "§5【百鬼夜行】");
        REGISTRATE.addRawLang("se.srelic.ghost_thunder.desc_3", "§d5层时造成400%雷电伤害并生成雷魍领域");
        REGISTRATE.addRawLang("se.srelic.ghost_thunder.desc_4", "§5【雷魍领域】");
        REGISTRATE.addRawLang("se.srelic.ghost_thunder.desc_5", "§d领域内持续造成伤害并获得减伤");

        REGISTRATE.addRawLang("se.srelic.star_thunder", "§e星轨雷殛·天罚");
        REGISTRATE.addRawLang("se.srelic.star_thunder.desc", "§6【星霆印记】");
        REGISTRATE.addRawLang("se.srelic.star_thunder.desc_1", "§e普攻25%概率附加印记（上限3层）");
        REGISTRATE.addRawLang("se.srelic.star_thunder.desc_2", "§6【星轨雷殛】");
        REGISTRATE.addRawLang("se.srelic.star_thunder.desc_3", "§e长按右键蓄力触发范围雷暴");
        REGISTRATE.addRawLang("se.srelic.star_thunder.desc_4", "§6【雷霆增幅】");
        REGISTRATE.addRawLang("se.srelic.star_thunder.desc_5", "§e每层增伤120%，2层追加魔法伤害");
        REGISTRATE.addRawLang("se.srelic.star_thunder.desc_6", "§6【天罚领域】");
        REGISTRATE.addRawLang("se.srelic.star_thunder.desc_7", "§e3层时额外造成范围减防效果");
        REGISTRATE.addRawLang("se.srelic.star_thunder.desc_8", "§8连击机制：连续触发提升伤害系数");

        REGISTRATE.addRawLang("se.srelic.flame_erosion", "§6炎鳞蚀骨·焚烬");
        REGISTRATE.addRawLang("se.srelic.flame_erosion.desc", "§4【蚀骨炎鳞】");
        REGISTRATE.addRawLang("se.srelic.flame_erosion.desc_1", "§6火伤20%概率叠加灼烧层数");
        REGISTRATE.addRawLang("se.srelic.flame_erosion.desc_2", "§4【鳞爆】");
        REGISTRATE.addRawLang("se.srelic.flame_erosion.desc_3", "§63层时触发范围爆炸和易伤效果");
        REGISTRATE.addRawLang("se.srelic.flame_erosion.desc_4", "§4【焚烬】");
        REGISTRATE.addRawLang("se.srelic.flame_erosion.desc_5", "§6真实伤害基于目标已损生命值");
        REGISTRATE.addRawLang("se.srelic.flame_erosion.desc_6", "§4【连锁反应】");
        REGISTRATE.addRawLang("se.srelic.flame_erosion.desc_7", "§6击杀触发连锁爆炸（伤害衰减）");

        REGISTRATE.addRawLang("se.srelic.crimson_annihilation", "§4红椿蚀骨·湮烬");
        REGISTRATE.addRawLang("se.srelic.crimson_annihilation.desc", "§c【湮烬之痕】");
        REGISTRATE.addRawLang("se.srelic.crimson_annihilation.desc_1", "§4普攻25%概率叠加魔法易伤层数");
        REGISTRATE.addRawLang("se.srelic.crimson_annihilation.desc_2", "§c【湮灭爆发】");
        REGISTRATE.addRawLang("se.srelic.crimson_annihilation.desc_3", "§43层时造成300%魔法伤害+已损生命15%");
        REGISTRATE.addRawLang("se.srelic.crimson_annihilation.desc_4", "§c【烬火蔓延】");
        REGISTRATE.addRawLang("se.srelic.crimson_annihilation.desc_5", "§4爆炸后转化50%伤害为治疗值");
        REGISTRATE.addRawLang("se.srelic.crimson_annihilation.desc_6", "§c【终末红椿】");
        REGISTRATE.addRawLang("se.srelic.crimson_annihilation.desc_7", "§4连续触发提升爆炸范围");

        REGISTRATE.addRawLang("se.srelic.sakura_bloom", "§d樱华·刹那芳华");
        REGISTRATE.addRawLang("se.srelic.sakura_bloom.desc", "§5每次攻击积累樱花层数（最大7层）");
        REGISTRATE.addRawLang("se.srelic.sakura_bloom.desc_1", "§5每层提供1.5额外伤害");
        REGISTRATE.addRawLang("se.srelic.sakura_bloom.desc_2", "§5满层时释放樱花风暴：造成范围伤害并治疗");
        REGISTRATE.addRawLang("se.srelic.sakura_bloom.desc_3", "§5持续治疗：每2秒恢复生命，层数越高效果越强");

        // 特殊效果
        REGISTRATE.addRawLang("se.srelic.storm_fury", "§a疾风·风暴之怒");
        REGISTRATE.addRawLang("se.srelic.storm_fury.desc", "§a【风能充能】");
        REGISTRATE.addRawLang("se.srelic.storm_fury.desc_1", "§e• 攻击35%概率积累风能(最多5层)");
        REGISTRATE.addRawLang("se.srelic.storm_fury.desc_2", "§a【飓风领域】");
        REGISTRATE.addRawLang("se.srelic.storm_fury.desc_3", "§e• 5格内持续风压伤害和击退");
        REGISTRATE.addRawLang("se.srelic.storm_fury.desc_4", "§e• 友方获得速度提升");
        REGISTRATE.addRawLang("se.srelic.storm_fury.desc_5", "§a【龙卷降临】");
        REGISTRATE.addRawLang("se.srelic.storm_fury.desc_6", "§e• 消耗3层召唤龙卷风牵引敌人");
        REGISTRATE.addRawLang("se.srelic.storm_fury.desc_7", "§e• 移动速度提升攻击速度");

        // 特殊效果
        REGISTRATE.addRawLang("se.srelic.ming_mang", "§5幽冥·冥芒噬魂");
        REGISTRATE.addRawLang("se.srelic.ming_mang.desc", "§5【魂火献祭】");
        REGISTRATE.addRawLang("se.srelic.ming_mang.desc_1", "§d每次攻击消耗5%当前生命获取冥芒(上限10层)");
        REGISTRATE.addRawLang("se.srelic.ming_mang.desc_2", "§5【冥府恩赐】");
        REGISTRATE.addRawLang("se.srelic.ming_mang.desc_3", "§d每层提供3%攻速与1%伤害增幅");
        REGISTRATE.addRawLang("se.srelic.ming_mang.desc_4", "§5【黄泉回溯】");
        REGISTRATE.addRawLang("se.srelic.ming_mang.desc_5", "§d生命低于30%时消耗冥芒治疗(每层3%最大生命)");
        REGISTRATE.addRawLang("se.srelic.ming_mang.desc_6", "§5【九幽爆发】");
        REGISTRATE.addRawLang("se.srelic.ming_mang.desc_7", "§d满层时造成(攻击+生命)x5的幽冥伤害");

        // 特殊效果
        REGISTRATE.addRawLang("se.srelic.butterfly_falling", "§5蝶落冥世·其为死亡");
        REGISTRATE.addRawLang("se.srelic.butterfly_falling.desc", "§5【魂火献祭】");
        REGISTRATE.addRawLang("se.srelic.butterfly_falling.desc_1", "§d每次攻击消耗50%当前生命获取冥芒(上限10层)");
        REGISTRATE.addRawLang("se.srelic.butterfly_falling.desc_2", "§5【冥府恩赐】");
        REGISTRATE.addRawLang("se.srelic.butterfly_falling.desc_3", "§d每层提供40%伤害加成");
        REGISTRATE.addRawLang("se.srelic.butterfly_falling.desc_4", "§5【黄泉回溯】");
        REGISTRATE.addRawLang("se.srelic.butterfly_falling.desc_5", "§d生命低于30%时消耗冥芒治疗(每层4%最大生命)");
        REGISTRATE.addRawLang("se.srelic.butterfly_falling.desc_6", "§5【九幽爆发】");
        REGISTRATE.addRawLang("se.srelic.butterfly_falling.desc_7", "§d满层时造成((攻击+生命)x5)/2的幽冥伤害");

        REGISTRATE.addRawLang("se.srelic.blood_plum_memory", "§5血梅之忆·往生之梅");
        REGISTRATE.addRawLang("se.srelic.blood_plum_memory.desc", "§5【血梅香】");
        REGISTRATE.addRawLang("se.srelic.blood_plum_memory.desc_1", "§d允许叠加血梅，血梅上限为30层，每层提供10%增伤。");
        REGISTRATE.addRawLang("se.srelic.blood_plum_memory.desc_2", "§5【血棺】");
        REGISTRATE.addRawLang("se.srelic.blood_plum_memory.desc_3", "§对亡灵生物造成300%额外伤害.");
        REGISTRATE.addRawLang("se.srelic.blood_plum_memory.desc_4", "§5【血忆】");
        REGISTRATE.addRawLang("se.srelic.blood_plum_memory.desc_5", "§d血量每降低10%会获得40%的增伤，最多280%。");

        // 炽炎勇魄
        REGISTRATE.addRawLang("se.srelic.blazing_valor", "§c炽炎勇魄");
        REGISTRATE.addRawLang("se.srelic.blazing_valor.desc", "§4【勇炎】");
        REGISTRATE.addRawLang("se.srelic.blazing_valor.desc_1", "§c• 攻击伤害+45% §7| §c获得§6抗火效果");
        REGISTRATE.addRawLang("se.srelic.blazing_valor.desc_2", "§4【烈炎】");
        REGISTRATE.addRawLang("se.srelic.blazing_valor.desc_3", "§c攻击燃烧目标时§4无视防御§c（护甲/韧性）");
        REGISTRATE.addRawLang("se.srelic.blazing_valor.desc_4", "§4【红莲战歌】");
        REGISTRATE.addRawLang("se.srelic.blazing_valor.desc_5", "§c10格内燃烧敌人死亡叠加§4红莲印记§c（+10%伤害/层）");
        REGISTRATE.addRawLang("se.srelic.blazing_valor.desc_6", "§8最大层数：§715 §7| §8持续：§730秒");

        // 特殊效果
        REGISTRATE.addRawLang("se.srelic.pure_elegy", "§6纯美赞歌");
        REGISTRATE.addRawLang("se.srelic.pure_elegy.desc", "§6【升格】");
        REGISTRATE.addRawLang("se.srelic.pure_elegy.desc_1", "§6每次攻击叠加升格（上限300层）");
        REGISTRATE.addRawLang("se.srelic.pure_elegy.desc_2", "§6【骑士之护】");
        REGISTRATE.addRawLang("se.srelic.pure_elegy.desc_3", "§6每75层获得抗性提升");
        REGISTRATE.addRawLang("se.srelic.pure_elegy.desc_4", "§6【赞颂】");
        REGISTRATE.addRawLang("se.srelic.pure_elegy.desc_5", "§6100层时潜行右键可冲刺");


        // 特殊效果
        REGISTRATE.addRawLang("se.srelic.water_symphony", "§b纵水交响曲");
        REGISTRATE.addRawLang("se.srelic.water_symphony.desc", "§b【众水叠章】");
        REGISTRATE.addRawLang("se.srelic.water_symphony.desc_1", "§3Ⅰ-Ⅲ层: 攻击附加§9缓慢Ⅰ§3，持续5秒");
        REGISTRATE.addRawLang("se.srelic.water_symphony.desc_2", "§b【歌剧叠章】");
        REGISTRATE.addRawLang("se.srelic.water_symphony.desc_3", "§3Ⅳ-Ⅴ层: 每层提供§c15%§3伤害加成");
        REGISTRATE.addRawLang("se.srelic.water_symphony.desc_4", "§d【终幕独奏】");
        REGISTRATE.addRawLang("se.srelic.water_symphony.desc_5", "§5满层时获得§c40%§5额外加成");
        REGISTRATE.addRawLang("se.srelic.water_symphony.desc_6", "§8※副手持有时同样生效");

// 特殊效果
        REGISTRATE.addRawLang("se.srelic.quantum_dance", "§5量镰影舞");
        REGISTRATE.addRawLang("se.srelic.quantum_dance.desc", "§5【再现·量蝶】");
        REGISTRATE.addRawLang("se.srelic.quantum_dance.desc_1", "§d击杀叠加量蝶层数§7(每层+40%伤害，上限10层)");
        REGISTRATE.addRawLang("se.srelic.quantum_dance.desc_2", "§5【增幅·量锋】");
        REGISTRATE.addRawLang("se.srelic.quantum_dance.desc_3", "§d攻击叠加量锋层数§7(每层+50%伤害，上限3层)");
        REGISTRATE.addRawLang("se.srelic.quantum_dance.desc_4", "§5【回蝶共鸣】");
        REGISTRATE.addRawLang("se.srelic.quantum_dance.desc_5", "§d双buff共存时获得§b速度IV+夜视+力量II+发光+急迫II");
        REGISTRATE.addRawLang("se.srelic.quantum_dance.desc_6", "§8※效果持续期间自动刷新");

// 特殊效果
        REGISTRATE.addRawLang("se.srelic.void_scissors", "§5空境紫剪");
        REGISTRATE.addRawLang("se.srelic.void_scissors.desc", "§d【魔方立场】");
        REGISTRATE.addRawLang("se.srelic.void_scissors.desc_1", "§5每层提供§c40%§5增伤，十层时每层额外+§c10%§5（最高500%）");
        REGISTRATE.addRawLang("se.srelic.void_scissors.desc_2", "§d【焦糖布丁】");
        REGISTRATE.addRawLang("se.srelic.void_scissors.desc_3", "§5持续散发金/紫交替粒子特效");
        REGISTRATE.addRawLang("se.srelic.void_scissors.desc_4", "§d【祝福之火】");
        REGISTRATE.addRawLang("se.srelic.void_scissors.desc_5", "§5对燃烧目标造成§c150%§5额外伤害，5%概率叠加立场");
// 特殊效果
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss", "§5魇梦渊卷之夜");
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss.desc", "§d【赤痕渊锁】");
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss.desc_1", "§5攻击15%概率获得§c赤痕§5（上限10层）");
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss.desc_2", "§5当§c赤痕≥8层§5时，每秒流失§41%§5最大生命值");
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss.desc_3", "§d【罪罚两断之刃】");
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss.desc_4", "§5攻击触发§e(7%+赤痕层数×1%)§5概率召唤寒冰棘刺");
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss.desc_5", "§5棘刺领域持续§78秒§5，每秒造成§c30%§5武器白值冰冻伤害");
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss.desc_6", "§5对领域内敌人施加§e11级缓慢§5（移动速度降低90%）");
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss.desc_7", "§5击杀目标后棘刺领域将§d扩散§5至半径3格范围");
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss.desc_8", "§5扩散后的领域持续时间递减§c30%§5，可连锁扩散§e3次");
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss.desc_9", "§d【枯萎的白花】");
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss.desc_10", "§5生命≤§440%§5时获得§c20%§5伤害加成");
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss.desc_11", "§5当§e天秤崩落§5激活时，额外获得§c25%§5伤害加成");
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss.desc_12", "§5攻击§b冻结目标§5时伤害提升§c30%");
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss.desc_13", "§5§e天秤崩落§5状态下冻结加成提升至§c45%");
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss.desc_14", "§d【终焉噩梦】");
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss.desc_15", "§5每次攻击叠加§8噩梦层数§5（上限6层）");
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss.desc_16", "§5每层提供§c15%§5伤害加成，持续§718秒");
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss.desc_17", "§5当§8噩梦§5叠加至§46层§5时：");
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss.desc_18", "§d• 消耗所有层数释放§c冰渊爆发§d");
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss.desc_19", "§d• 造成§c(200%白值 + 200%已损生命值)§5的冰冻伤害");
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss.desc_20", "§d• 对§74格§5范围内敌人施加§b绝对冻结§5（移动禁止3秒）");
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss.desc_21", "§8※所有效果可同时生效，触发优先级：赤痕→罪罚→白花→噩梦");
        REGISTRATE.addRawLang("se.srelic.nightmare_abyss.desc_22", "§8※如果崩端，概不负责");

// 特殊效果
        REGISTRATE.addRawLang("se.srelic.mugen_ice", "§b无我·极冰演算");
        REGISTRATE.addRawLang("se.srelic.mugen_ice.desc", "§b【无我之境】");
        REGISTRATE.addRawLang("se.srelic.mugen_ice.desc_1", "§3获得抗性提升III，持续积累变奏值");
        REGISTRATE.addRawLang("se.srelic.mugen_ice.desc_2", "§b【时滞演算】");
        REGISTRATE.addRawLang("se.srelic.mugen_ice.desc_3", "§335格范围施加缓慢255，持续120秒");
        REGISTRATE.addRawLang("se.srelic.mugen_ice.desc_4", "§b【覆冰增幅】");
        REGISTRATE.addRawLang("se.srelic.mugen_ice.desc_5", "§3基础伤害+120%，副手拔刀剑时额外+20%");
        REGISTRATE.addRawLang("se.srelic.mugen_ice.desc_6", "§b【极冰穿刺】");
        REGISTRATE.addRawLang("se.srelic.mugen_ice.desc_7", "§3极冰层≥20时，15格范围持续造成50%白值伤害");
        REGISTRATE.addRawLang("se.srelic.mugen_ice.desc_8", "§b◇变奏系统◇");
        REGISTRATE.addRawLang("se.srelic.mugen_ice.desc_9", "§d击杀消耗100耀魂叠加，50层时岚啸伤害+100%");
        REGISTRATE.addRawLang("se.srelic.mugen_ice.desc_10", "§d25层时岚啸伤害+50%");
        REGISTRATE.addRawLang("se.srelic.mugen_ice.desc_11", "§b◆极冰机制◆");
        REGISTRATE.addRawLang("se.srelic.mugen_ice.desc_12", "§5每击杀5敌消耗150耀魂叠加，10层激活覆冰");
        REGISTRATE.addRawLang("se.srelic.mugen_ice.desc_13", "§520层激活冰刺领域");
        REGISTRATE.addRawLang("se.srelic.mugen_ice.desc_14", "§8※耀魂值不足时无法叠加层数");

        // 誓约矩阵
        REGISTRATE.addRawLang("se.srelic.pledge_matrix", "§6誓约矩阵");
        REGISTRATE.addRawLang("se.srelic.pledge_matrix.desc", "§e【光耀节点】");
        REGISTRATE.addRawLang("se.srelic.pledge_matrix.desc_1", "§625%攻击概率生成持续脉冲节点");
        REGISTRATE.addRawLang("se.srelic.pledge_matrix.desc_2", "§e【荣光领域】");
        REGISTRATE.addRawLang("se.srelic.pledge_matrix.desc_3", "§6形成三角区域时：");
        REGISTRATE.addRawLang("se.srelic.pledge_matrix.desc_4", "§b- 友军伤害+40% §7| §c- 敌人每秒灼烧伤害");
        REGISTRATE.addRawLang("se.srelic.pledge_matrix.desc_5", "§e【脉冲网络】");
        REGISTRATE.addRawLang("se.srelic.pledge_matrix.desc_6", "§6节点间自动连接形成能量光束");
        REGISTRATE.addRawLang("se.srelic.pledge_matrix.desc_7", "§c【赤焰共鸣】");
        REGISTRATE.addRawLang("se.srelic.pledge_matrix.desc_8", "§e友军攻击燃烧目标时：");
        REGISTRATE.addRawLang("se.srelic.pledge_matrix.desc_9", "§b- 每层+2%伤害 §7(§d上限50层§7)");
        REGISTRATE.addRawLang("se.srelic.pledge_matrix.desc_10", "§c【协同作战】");
        REGISTRATE.addRawLang("se.srelic.pledge_matrix.desc_11", "§e领域内共鸣效率提升30%");

        REGISTRATE.addRawLang("se.srelic.white_rose", "§d白夜蔷薇");
        REGISTRATE.addRawLang("se.srelic.white_rose.desc", "§5【蔷薇绽放】");
        REGISTRATE.addRawLang("se.srelic.white_rose.desc_1", "§dSA命中+1层 §7| §5友方攻燃+2层(2秒CD)");
        REGISTRATE.addRawLang("se.srelic.white_rose.desc_2", "§5Ⅰ-Ⅴ层: §c粉色幻影刃§7(90%伤害)并额外增伤30%");
        REGISTRATE.addRawLang("se.srelic.white_rose.desc_3", "§5Ⅵ-Ⅹ层: §b「心火」§735%减伤");
        REGISTRATE.addRawLang("se.srelic.white_rose.desc_4", "§5Ⅺ-ⅩⅤ层: §c每层+10%伤害");
        REGISTRATE.addRawLang("se.srelic.white_rose.desc_5", "§d【永炎领域】");
        REGISTRATE.addRawLang("se.srelic.white_rose.desc_6", "§5攻击燃敌时治疗35%伤害值");
        REGISTRATE.addRawLang("se.srelic.white_rose.desc_7", "§5领域内每友方提供:");
        REGISTRATE.addRawLang("se.srelic.white_rose.desc_8", "§c- 火焰伤害+10% §7| §b- 对亡灵+5%");
        REGISTRATE.addRawLang("se.srelic.white_rose.desc_9", "§d【灼烧连锁】");
        REGISTRATE.addRawLang("se.srelic.white_rose.desc_10", "§5友方攻击燃敌追加80%火焰伤害");
        REGISTRATE.addRawLang("se.srelic.white_rose.desc_11", "§5层数每45秒下降1层");

        // 岚身
        REGISTRATE.addRawLang("se.srelic.storm_body", "§b帝弓天将·巡猎天击");
        REGISTRATE.addRawLang("se.srelic.storm_body.desc", "§3【岚身】");
        REGISTRATE.addRawLang("se.srelic.storm_body.desc_1", "§b每10次攻击+1层,每次挥刀都会聚集生物并加一层");
        REGISTRATE.addRawLang("se.srelic.storm_body.desc_2", "§b使用SA每消耗层数提供§c20%§b增伤 §7(上限30层)");
        REGISTRATE.addRawLang("se.srelic.storm_body.desc_3", "§3【惟首正丘】");
        REGISTRATE.addRawLang("se.srelic.storm_body.desc_4", "§b挥刀生成两道追踪幻影剑");
        REGISTRATE.addRawLang("se.srelic.storm_body.desc_5", "§3【流风回雪】");
        REGISTRATE.addRawLang("se.srelic.storm_body.desc_6", "§b幻影剑自动追踪12格内敌人");

// 特殊效果
        REGISTRATE.addRawLang("se.srelic.ice_rhythm", "§b凛华引奏");
        REGISTRATE.addRawLang("se.srelic.ice_rhythm.desc", "§3【极冰旋律】");
        REGISTRATE.addRawLang("se.srelic.ice_rhythm.desc_1", "§b每次攻击积累§3冰华层数§b（每5击+1层，上限15层）");
        REGISTRATE.addRawLang("se.srelic.ice_rhythm.desc_2", "§3【凛冬庇护】");
        REGISTRATE.addRawLang("se.srelic.ice_rhythm.desc_3", "§b每层提供§310%§b减伤，满层时获得§3抗性提升II");
        REGISTRATE.addRawLang("se.srelic.ice_rhythm.desc_4", "§3【极冰狂奏】");
        REGISTRATE.addRawLang("se.srelic.ice_rhythm.desc_5", "§b锻造≥200时，附加目标§c5%§b当前生命真实伤害");

        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.FIRE_FLY), "次级燃烧");
        REGISTRATE.addRawLang(CreateSeLangDescs(SRSpecialEffectsRegistry.FIRE_FLY), "每次攻击叠加30层过热值");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.FIRE_FLY, 1), "过热值大于300时清空并开启完全燃烧状态");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.FIRE_FLY, 2), "完全燃烧状态下你可以飞行");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.FIRE_FLY, 3), "攻击获得30%穿甲，伤害提高150%");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.FIRE_FLY, 4), "获得迅捷2力量3和抗性提升3");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.FIRE_FLY, 5), "并会对周围生物造成伤害");

        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.RED_SCAR), "梦中如一");
        REGISTRATE.addRawLang(CreateSeLangDescs(SRSpecialEffectsRegistry.RED_SCAR), "攻击叠加一层「赤痕」");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.RED_SCAR, 1), "每两层获得一级的抗性与力量");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.RED_SCAR, 2), "且每层增伤10%持续15秒最高8层");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.RED_SCAR, 3), "叠满8层后，每再获得一层会恢复2点生命值");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.RED_SCAR, 4), "叠满8层后每一刀伤害提高50%");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.RED_SCAR, 5), "在手持时不再受生命恢复的影响");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.RED_SCAR, 6), "每次攻击有10%的概率触发量子崩塌(吸引生物并进行一次小范围爆炸)");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.RED_SCAR, 7), "拥有「赤痕」时，周围玩家获得生命恢复Ⅰ，伤害吸收Ⅰ");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.RED_SCAR, 8), "每一层的「赤痕」都会额外附加2点魔法伤害，并造成缓慢效果");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.RED_SCAR, 9), "每次攻击给怪增加10点异常值，异常值满后使怪物处于2s的破防状态，无视敌方40%的护甲值");

        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.Elemental_Explosion), "空刃·元素爆发");
        REGISTRATE.addRawLang(CreateSeLangDescs(SRSpecialEffectsRegistry.Elemental_Explosion), "§3【元素爆发】");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.Elemental_Explosion, 1), "剑技·天空系列sa释放后会增加一层[空刃]");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.Elemental_Explosion, 2), "每层提供一级速度与一级抗性提升(抗性提升最高四级)");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.Elemental_Explosion, 3), "造成伤害时触发额外魔法伤害");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.Elemental_Explosion, 4), "每30秒减少一层[空刃]");

        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.VACUUM_BLADE), "空刃·真空刃");
        REGISTRATE.addRawLang(CreateSeLangDescs(SRSpecialEffectsRegistry.VACUUM_BLADE), "§3【真空刃】");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.VACUUM_BLADE, 1), "剑技·天空系列sa释放后会增加一层[空刃]");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.VACUUM_BLADE, 2), "持有[空刃]后可打出倍率80%的幻影刃");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.VACUUM_BLADE, 3), "每额外有一层[空刃]倍率就+20%");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.VACUUM_BLADE, 4), "每30秒减少一层[空刃]");

        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.CELESTIAL_STRIKE), "天宇贯星击");
        REGISTRATE.addRawLang(CreateSeLangDescs(SRSpecialEffectsRegistry.CELESTIAL_STRIKE), "【剑盾模式】");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.CELESTIAL_STRIKE, 1), "每次攻击为敌人叠加1层「星蚀」标记（上限20层）");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.CELESTIAL_STRIKE, 2), "每层星蚀标记会对敌人造成0.75%当前生命值的伤害");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.CELESTIAL_STRIKE, 3), "释放sa【决胜轰解】时，如果半径10格有敌人持有星蚀则会被直接引爆");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.CELESTIAL_STRIKE, 4), "此外，不管敌人是否有20层星蚀在释放sa后都会切换为【盾斧模式】");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.CELESTIAL_STRIKE, 5), "【盾斧模式】");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.CELESTIAL_STRIKE, 6), "你的所有非火焰伤害类型攻击都会被转为4段火焰伤害并为你叠加2层解放值");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.CELESTIAL_STRIKE, 7), "同时，会根据你的hp的2%来增加额外的伤害");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.CELESTIAL_STRIKE, 8), "解放值满100时，攻击概率触发极解");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.CELESTIAL_STRIKE, 9), "释放sa后如果解放值=100则对半径十格范围的生物造成等同于玩家面板的8段火焰伤害");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.CELESTIAL_STRIKE, 10), "同时解放值=100的情况下释放sa后会切换为【剑盾模式】");


        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.INFERNO_THOUSAND_TRIALS), "炎烬千劫");
        REGISTRATE.addRawLang(CreateSeLangDescs(SRSpecialEffectsRegistry.INFERNO_THOUSAND_TRIALS), "【炎烬千劫】");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.INFERNO_THOUSAND_TRIALS, 1), "你造成的伤害提高200%");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.INFERNO_THOUSAND_TRIALS, 2), "同时会对自身造成最大生命值2%的虚空属性伤害，这被扣除的部分会按照1颗心+10%的比例进行额外伤害增幅");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.INFERNO_THOUSAND_TRIALS, 3), "每攻击十次会展开一次火焰领域对敌人造成一次等于玩家50%攻击力的火焰伤害");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.INFERNO_THOUSAND_TRIALS, 4), "玩家受到的伤害减半");

        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.ICE_BLADE), "天降寒天之钉");
        REGISTRATE.addRawLang(CreateSeLangDescs(SRSpecialEffectsRegistry.ICE_BLADE), "【天降寒天之钉】");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.ICE_BLADE, 1), "你造成的伤害提高200%");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.ICE_BLADE, 2), "攻击有30%的概率召唤冰锥");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.ICE_BLADE, 3), "冰锥的伤害倍率为360%");

        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.HOLY_ENERGY_OVERFLOW), "3rd圣能充溢");
        REGISTRATE.addRawLang(CreateSeLangDescs(SRSpecialEffectsRegistry.HOLY_ENERGY_OVERFLOW), "【3rd圣能充溢】");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.HOLY_ENERGY_OVERFLOW, 1), "你造成的伤害提高30%");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.HOLY_ENERGY_OVERFLOW, 2), "你的攻击伤害变为两段");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.HOLY_ENERGY_OVERFLOW, 3), "当你锻造数>100时：");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.HOLY_ENERGY_OVERFLOW, 4), "你造成的伤害再次提高60%");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.HOLY_ENERGY_OVERFLOW, 5), "你的攻击伤害变为三段");

        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.DREAM_COMPANION), "与梦的同行");
        REGISTRATE.addRawLang(CreateSeLangDescs(SRSpecialEffectsRegistry.DREAM_COMPANION), "【与梦的同行】");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.DREAM_COMPANION, 1), "你造成的伤害提高100%");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.DREAM_COMPANION, 2), "攻击会叠加霜冻值，满层后缓慢敌人，这种方式冻结的敌人受到的伤害增加25%");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.DREAM_COMPANION, 3), "你周围对你没仇恨的生物获得力量3");

        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.BITTER_COLD_HELL), "苦寒地狱");
        REGISTRATE.addRawLang(CreateSeLangDescs(SRSpecialEffectsRegistry.BITTER_COLD_HELL), "【苦寒地狱】");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.BITTER_COLD_HELL, 1), "你造成的伤害提高50%");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.BITTER_COLD_HELL, 2), "每3秒自动叠加「勿忘」层数");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.BITTER_COLD_HELL, 3), "攻击时消耗层数造成额外伤害");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.BITTER_COLD_HELL, 4), "攻击造成霜冻效果");

        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.BITTER_COLD_HELL_EX), "超限·苦寒地狱");
        REGISTRATE.addRawLang(CreateSeLangDescs(SRSpecialEffectsRegistry.BITTER_COLD_HELL_EX), "【超限·苦寒地狱】");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.BITTER_COLD_HELL_EX, 1), "你造成的伤害提高100%");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.BITTER_COLD_HELL_EX, 2), "每2秒自动叠加「勿忘」层数");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.BITTER_COLD_HELL_EX, 3), "攻击时消耗层数造成额外伤害");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.BITTER_COLD_HELL_EX, 4), "攻击造成霜冻效果");

        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.ICE_BLOOM), "转魄");
        REGISTRATE.addRawLang(CreateSeLangDescs(SRSpecialEffectsRegistry.ICE_BLOOM), "【转魄】");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.ICE_BLOOM, 1), "每次攻击有概率累计「朔望」效果");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.ICE_BLOOM, 2), "当「朔望」效果达到4层：");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.ICE_BLOOM, 3), "进入「转魄」效果：");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.ICE_BLOOM, 4), "在「转魄」效果期间，伤害提高100%，超过4层的「朔望」可额外提高10%的伤害，最高可叠加9层。");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.ICE_BLOOM, 5), "「转魄」下获得「幻刃风暴」和力量效果");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.ICE_BLOOM, 6), "此外，不管是否转魄，你的所有伤害都会被转为靠生命值为基础的冰属性伤害");

        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.AFFLORDITE), "银莲花");
        REGISTRATE.addRawLang(CreateSeLangDescs(SRSpecialEffectsRegistry.AFFLORDITE), "【银莲花】");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.AFFLORDITE, 1), "你的伤害增加25%");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.AFFLORDITE, 2), "攻击概率造成范围伤害");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.AFFLORDITE, 3), "范围伤害倍率为1.5");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.AFFLORDITE, 4), "如果敌人为boss时，倍率为3");

        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.FROST_FLAME), "风花霜月·寒炎");
        REGISTRATE.addRawLang(CreateSeLangDescs(SRSpecialEffectsRegistry.FROST_FLAME), "【风花霜月·寒炎】");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.FROST_FLAME, 1), "伤害提高60%");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.FROST_FLAME, 2), "伤害变为火焰伤害和寒冷伤害混伤");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.FROST_FLAME, 3), "攻击概率给目标叠加「霜灼」效果（着火+缓慢3）");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.FROST_FLAME, 4), "攻击带有「霜灼」的目标时，伤害提高30%");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.FROST_FLAME, 5), "每五段斩击获得一层「落霜」，上限6层，持续6秒");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.FROST_FLAME, 6), "每层「落霜」提供15%增伤，并无视30%火焰/霜冻抗性");

        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.ICE_SOUL_FROST_SKY), "冰魄·寒月霜天");
        REGISTRATE.addRawLang(CreateSeLangDescs(SRSpecialEffectsRegistry.ICE_SOUL_FROST_SKY), "【冰魄·寒月霜天】");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.ICE_SOUL_FROST_SKY, 1), "造成伤害提高45%");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.ICE_SOUL_FROST_SKY, 2), "面对boss时额外提高15%");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.ICE_SOUL_FROST_SKY, 3), "击中敌人时+1层冰蚀层数（最高15层）");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.ICE_SOUL_FROST_SKY, 4), "满15层会自动触发一次SA");

        REGISTRATE.addRawLang(CreateSeLang(SRSpecialEffectsRegistry.HUNTER), "【追猎者】");
        REGISTRATE.addRawLang(CreateSeLangDescs(SRSpecialEffectsRegistry.HUNTER), "【追猎者】");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.HUNTER, 1), "移动速度提升25%");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.HUNTER, 2), "根据移速加成提升你造成的伤害");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.HUNTER, 3), "每次攻击叠加毒素层数");
        REGISTRATE.addRawLang(CreateSeLangDescsCounts(SRSpecialEffectsRegistry.HUNTER, 4), "每0.5秒造成毒素伤害（伤害值为玩家基础攻击力的20%×层数）");


        REGISTRATE.addRawLang("srelic.sword_type.other_world_on", "幻");
        REGISTRATE.addRawLang("srelic.sword_type.other_world", "解");
        REGISTRATE.addRawLang("srelic.sword_type.other_world_off", "封");


        REGISTRATE.addRawLang("message.srelic.grammer_alloy_obtained", "你获得了格拉默合金！");
        REGISTRATE.addRawLang("message.srelic.grammer_progress", "蠹虫击杀进度: %d/%d");
        REGISTRATE.addRawLang("tooltip.srelic.grammer_progress", "蠹虫击杀进度: %d/%d");


        REGISTRATE.addRawLang("item_group." + MODID + "." + MODID + "_slashblade", "§6游戏扩增·异界之刃");
        REGISTRATE.addRawLang("item_group." + MODID + "." + MODID + "_se", "§6游戏扩增·命途轨迹");
        REGISTRATE.addRawLang("item_group." + MODID + "." + MODID + "_se2", "§6游戏扩增·测试用SE遍历");
        REGISTRATE.addRawLang("item_group." + MODID + "." + MODID + "_item", "§6游戏扩增·利刃素材");
    }


    public static String CreateItemLang(ResourceKey<SlashBladeDefinition> a) {
        return "item." + MODID + "." + a.location().getPath();
    }

    public static String CreateSeLang(RegistryObject<SpecialEffect> a) {
        return "se." + MODID + "." + a.getId().getPath();
    }

    public static String CreateSeLangDescs(RegistryObject<SpecialEffect> a) {
        return "se." + MODID + "." + a.getId().getPath() + ".desc";
    }

    public static String CreateSeLangDescsCounts(RegistryObject<SpecialEffect> a, int i) {
        return "se." + MODID + "." + a.getId().getPath() + ".desc_" + i;
    }

    public static String CreateSaLang(RegistryObject<SlashArts> a) {
        return "slash_art." + MODID + "." + a.getId().getPath();
    }


}
