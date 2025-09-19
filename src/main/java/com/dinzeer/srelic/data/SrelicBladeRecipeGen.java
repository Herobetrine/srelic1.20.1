package com.dinzeer.srelic.data;

import com.dinzeer.srelic.Srelic;
import com.dinzeer.srelic.registry.SRBlockRegsitry;
import com.dinzeer.srelic.registry.SRItemRegsitry;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import vazkii.patchouli.common.item.PatchouliItems;

import java.util.function.BiFunction;
import java.util.function.Consumer;

import static com.dinzeer.srelic.Srelic.MODID;

public class SrelicBladeRecipeGen {
    public static void onRecipeGen(RegistrateRecipeProvider pvd) {
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SRItemRegsitry.ender_metal, 1)::unlockedBy, Items.IRON_INGOT)
                .pattern("AAA")
                .pattern("BCB")
                .pattern("AAA")
                .define('A', Items.ENDER_EYE)
                .define('B',Items.IRON_INGOT)
                .define('C',Items.NETHERITE_SCRAP)
                .save(pvd);
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.soul_metal, 1)::unlockedBy, Items.IRON_INGOT)
                .pattern("ABC")
                .pattern("DCD")
                .pattern("ABC")
                .define('A',Items.GLOWSTONE)
                .define('B',Items.REDSTONE)
                .define('C',Items.GOLD_INGOT)
                .define('D',Items.DIAMOND)
                .save(pvd);
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.demon_ingot, 1)::unlockedBy, Items.IRON_INGOT)
                .pattern(" A ")
                .pattern("BEC")
                .pattern(" D ")
                .define('A',SRItemRegsitry.flame_netherite_alloy)
                .define('B',SRItemRegsitry.frozen_netherite_alloy)
                .define('C',SRItemRegsitry.thunder_netherite_alloy)
                .define('D',SRItemRegsitry.oceanic_netherite_alloy)
                .define('E',Items.NETHER_STAR)
                .save(pvd);

        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.max_ingot, 1)::unlockedBy, Items.IRON_INGOT)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A',SRItemRegsitry.soul_metal)
                .define('B',SRItemRegsitry.flame_netherite_alloy)
                .define('C',SRItemRegsitry.sakura_steel_ingot)
                .save(pvd);
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.windy_core_ingot, 1)::unlockedBy, Items.IRON_INGOT)
                .pattern(" A ")
                .pattern(" B ")
                .pattern("   ")
                .define('A',SRItemRegsitry.windy_core)
                .define('B',Items.NETHERITE_INGOT)
                .save(pvd);


        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.diamond_star, 1)::unlockedBy, Items.IRON_INGOT)
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Blocks.DIAMOND_BLOCK)
                .define('B',Items.NETHER_STAR)
                .save(pvd);




        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.spirit_butterfly, 1)::unlockedBy, Items.IRON_INGOT)
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A', Blocks.DANDELION)       // 蒲公英
                .define('B', Blocks.POPPY)          // 虞美人
                .define('C', Blocks.BLUE_ORCHID)    // 兰花
                .define('D', Blocks.ALLIUM)         // 绒球葱
                .define('E', Blocks.RED_TULIP)      // 红色郁金香
                .define('F', Blocks.OXEYE_DAISY)    // 雏菊
                .define('G', Blocks.LILY_OF_THE_VALLEY) // 铃兰
                .define('H', Blocks.CORNFLOWER)     // 矢车菊
                .define('I', Blocks.WITHER_ROSE)    // 凋零玫瑰
                .save(pvd);

        // 添加炎翎合成配方
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.Emberquill.get(), 1)::unlockedBy, Items.IRON_INGOT)
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A', Items.FEATHER)        // 羽毛
                .define('B', Items.BLAZE_ROD)      // 烈焰棒
                .define('C', Items.PINK_WOOL)      // 粉红羊毛
                .define('D', Items.GOLD_INGOT)     // 金锭
                .define('E', Blocks.DIAMOND_BLOCK) // 钻石块
                .define('F', Blocks.QUARTZ_BLOCK)  // 石英块
                .define('G', Items.FIRE_CHARGE)    // 火焰弹
                .define('H', Items.FEATHER)        // 羽毛
                .define('I', SRItemRegsitry.ScarletflameIngot.get()) // 绯炎锭
                .save(pvd);

        // 添加绯炎锭合成配方
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.ScarletflameIngot.get(), 1)::unlockedBy, Items.IRON_INGOT)
                .pattern("ABA")
                .pattern("CDC")
                .pattern("EFE")
                .define('A', Items.NETHERITE_INGOT)   // 下界合金锭
                .define('B', Items.BLAZE_ROD)         // 烈焰棒
                .define('C', Blocks.PINK_STAINED_GLASS_PANE) // 粉红玻璃板
                .define('D', SRItemRegsitry.PledgeflameVermilionStrand.get()) // 誓炎绯丝
                .define('E', Items.GOLD_INGOT)        // 金锭
                .define('F', Items.NETHER_STAR)       // 下界之星
                .save(pvd);

        // 添加「誓炎熔芯」合成配方
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.PledgeflameHeartforge.get(), 1)::unlockedBy, Items.IRON_INGOT)
                .pattern(" A ")
                .pattern("BCB")
                .pattern(" A ")
                .define('A', SRItemRegsitry.ScarletflameIngot.get()) // 绯炎锭
                .define('B', SRItemRegsitry.Emberquill.get())        // 炎翎
                .define('C', Items.NETHERITE_INGOT)                  // 下界合金锭
                .save(pvd);

        // 添加流涌涡心合成配方
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.SurgeheartVortex.get(), 1)::unlockedBy, Items.CONDUIT)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', SRItemRegsitry.AbyssalLuminanceIngot.get()) // 渊海流辉锭
                .define('B', Items.NAUTILUS_SHELL) // 鹦鹉螺壳
                .define('C', Items.CONDUIT) // 潮涌核心
                .save(pvd);
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.NereidSecretBrew.get(), 1)::unlockedBy, Items.CONDUIT)
                .pattern("ABC")
                .pattern(" D ")
                .pattern(" E ")
                .define('A', PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER_BREATHING).getItem())
                .define('B', PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER).getItem())
                .define('C', PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.SLOWNESS).getItem())
                .define('D', Items.HONEY_BOTTLE)
                .define('E', Items.SUGAR)
                .save(pvd);






        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.crimson_paper.get(), 1)::unlockedBy, Items.CONDUIT)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', Items.REDSTONE)
                .define('B', Blocks.BLACKSTONE)
                .define('C', Items.PAPER)
                .save(pvd);


        
        // 添加火焰能量立方合成配方
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, 
                SRItemRegsitry.ignis_cube.get(), 1)::unlockedBy, SRItemRegsitry.flame_netherite_alloy.get())
                .pattern("ABA")
                .pattern("BCB")
                .pattern("DEE")
                .define('A', SRItemRegsitry.flame_netherite_alloy)
                .define('D', Blocks.SOUL_CAMPFIRE)
                .define('C', SRItemRegsitry.ex_star)
                .define('B', SRItemRegsitry.max_ingot)
                .define('E', Items.FIRE_CHARGE)
                .save(pvd);

        // 添加织梦锭合成配方
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.DreamweaveIngot.get(), 1)::unlockedBy, Items.IRON_INGOT)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', Items.NETHERITE_INGOT)
                .define('B', Items.GOLD_INGOT)
                .define('C', SRItemRegsitry.PhantomirageButterfly.get())
                .save(pvd);

        // 添加幻痕锭合成配方
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.PhantomTraceIngot.get(), 1)::unlockedBy, Items.IRON_INGOT)
                .pattern("AAA")
                .pattern("BCB")
                .pattern("DAD")
                .define('A', Items.NETHERITE_INGOT)
                .define('B', Items.GOLD_INGOT)
                .define('C', SRItemRegsitry.PhantomirageButterfly.get())
                .define('D', Items.DIAMOND)
                .save(pvd);

        // 添加夜狩锭合成配方
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.NightstalkerIngot.get(), 1)::unlockedBy, Items.IRON_INGOT)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', Items.NETHERITE_INGOT)
                .define('B', Items.GOLD_INGOT)
                .define('C', SRItemRegsitry.NightterrorBloodwing.get())
                .save(pvd);

        // 添加梦魇锭合成配方
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.NightmareCoreIngot.get(), 1)::unlockedBy, Items.IRON_INGOT)
                .pattern("AAA")
                .pattern("BCB")
                .pattern("DAD")
                .define('A', Items.NETHERITE_INGOT)
                .define('B', Items.GOLD_INGOT)
                .define('C', SRItemRegsitry.NightterrorBloodwing.get())
                .define('D', Items.DIAMOND)
                .save(pvd);


        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.GrammerCore.get(), 1)::unlockedBy, SRItemRegsitry.GrammerAlloy.get())
                .pattern(" A ")
                .pattern("BCB")
                .pattern("DED")
                .define('A', Items.NETHER_STAR) // 下界之星
                .define('B', SRItemRegsitry.GrammerAlloy.get()) // 格拉默合金 (4个)
                .define('C', Blocks.SOUL_CAMPFIRE) // 灵魂篝火
                .define('D', Items.SUGAR) // 糖
                .define('E', Blocks.CAMPFIRE) // 普通篝火
                .save(pvd);
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.GrammerAlloyEx.get(), 1)::unlockedBy, SRItemRegsitry.GrammerAlloy.get())
                .pattern(" A ")
                .pattern(" B ")
                .pattern("   ")
                .define('A', Items.NETHER_STAR) // 下界之星
                .define('B', SRItemRegsitry.GrammerAlloy.get()) // 格拉默合金
                .save(pvd);

        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRBlockRegsitry.blood_plum_sampling_ex.get(), 1)::unlockedBy, SRBlockRegsitry.blood_plum_sampling_ex.get().asItem())
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" A ")
                .define('A',SRBlockRegsitry.blood_plum_sampling.get())
                .save(pvd);


        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRBlockRegsitry.blood_plum_planks.get(), 4)::unlockedBy, SRBlockRegsitry.blood_plum_planks.get().asItem())
                .pattern(" A ")
                .pattern("   ")
                .pattern("   ")
                .define('A',SRBlockRegsitry.blood_plum_log.get())
                .save(pvd);
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRBlockRegsitry.blood_plum_planks.get(), 4)::unlockedBy, SRBlockRegsitry.blood_plum_planks.get().asItem())
                .pattern(" A ")
                .pattern("   ")
                .pattern("   ")
                .define('A',SRBlockRegsitry.stripped_blood_plum_log.get())
                .save(pvd,"srelic:blood_plum_planks2");

        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                Items.ANCIENT_DEBRIS, 2)::unlockedBy, SRBlockRegsitry.blood_plum_planks.get().asItem())
                .pattern(" A ")
                .pattern(" B ")
                .pattern("   ")
                .define('A',Items.GOLD_BLOCK)
                .define('B',SRItemRegsitry.ex_star.get())
                .save(pvd,"srelic:ex_star_make_nether_ore");
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                Items.NETHER_STAR, 2)::unlockedBy, SRBlockRegsitry.blood_plum_planks.get().asItem())
                .pattern("ACA")
                .pattern("CBC")
                .pattern("ACA")
                .define('A',Items.WITHER_SKELETON_SKULL)
                .define('B',SRItemRegsitry.ex_star.get())
                .define('C',Items.SOUL_SAND)
                .save(pvd,"srelic:ex_star_make_nether_star");
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                Items.NETHER_STAR, 1)::unlockedBy, SRBlockRegsitry.blood_plum_planks.get().asItem())
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A',SRItemRegsitry.rainbow_star.get())
                .define('B',SRItemRegsitry.ex_star.get())
                .save(pvd,"srelic:ex_star_make_rainbow_star");

        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(SRItemRegsitry.rough_crimson_shadow_ore.get()),
            RecipeCategory.MISC,
            SRItemRegsitry.crimson_shadow_ingot.get(),
            0.7f,
            200
        ).unlockedBy(
            "has_rough_crimson_shadow_ore", 
            RegistrateRecipeProvider.has(SRItemRegsitry.rough_crimson_shadow_ore.get())
        ).save(pvd, new ResourceLocation(MODID, "crimson_shadow_ingot_smelting"));


        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SRItemRegsitry.crimson_shadow_particle.get(), 9)::unlockedBy, Items.IRON_INGOT)
                .pattern("   ")
                .pattern(" A ")
                .pattern("   ")
            .define('A', SRItemRegsitry.crimson_shadow_ingot.get())
            .save(pvd);


        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SRItemRegsitry.crimson_shadow_ingot.get(), 1)::unlockedBy, Items.IRON_INGOT)
            .pattern("AAA")
            .pattern("AAA")
            .pattern("AAA")
            .define('A', SRItemRegsitry.crimson_shadow_particle.get())
            .save(pvd);

        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SRItemRegsitry.red_tide_core.get(), 1)::unlockedBy, Items.IRON_INGOT)
            .pattern("ABC")
            .pattern("CDC")
            .pattern("CBA")
            .define('A', Blocks.REDSTONE_BLOCK)
            .define('B', Items.LAVA_BUCKET)
            .define('C', SRItemRegsitry.crimson_shadow_ingot.get())
            .define('D', SRItemRegsitry.diamond_star.get())
            .save(pvd);


        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC, SRItemRegsitry.crimson_core.get(), 1)::unlockedBy, Items.IRON_INGOT)
            .pattern("ABA")
            .pattern("BDB")
            .pattern("CBC")
            .define('A', SRItemRegsitry.rough_crimson_shadow_ore.get())
            .define('B', Blocks.CRYING_OBSIDIAN)
            .define('C', SRBlockRegsitry.crimson_shadow_ore.get().asItem())
            .define('D', SRItemRegsitry.diamond_star.get())
            .save(pvd);


        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.void_core.get(), 1)::unlockedBy, SRItemRegsitry.void_ingot.get())
                .pattern(" A ")
                .pattern("BCB")
                .pattern(" D ")
                .define('A', SRItemRegsitry.ender_metal.get())
                .define('B', SRItemRegsitry.void_ingot.get())
                .define('C', SRItemRegsitry.PuleApple.get())
                .define('D', Items.ENDER_EYE)
                .save(pvd);


        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.void_ingot.get(), 1)::unlockedBy, Items.NETHERITE_INGOT)
                .pattern("ABA")
                .pattern("CDC")
                .pattern("EBE")
                .define('A', Items.NETHERITE_SCRAP)
                .define('B', Blocks.PURPUR_BLOCK)
                .define('C', SRItemRegsitry.ex_star.get())
                .define('D', Items.ENDER_EYE)
                .define('E', Blocks.DRAGON_EGG)
                .save(pvd);

        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.max_smithing_template.get(), 1)::unlockedBy, Items.NETHERITE_INGOT)
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ADA")
                .define('A', SRItemRegsitry.max_ingot.get())
                .define('B', SRItemRegsitry.demon_ingot.get())
                .define('C', SRItemRegsitry.fire_smithing_template.get())
                .define('D', SRItemRegsitry.ignis_cube)

                .save(pvd);




        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.max_smithing_template.get(), 2)::unlockedBy, Items.NETHERITE_INGOT)
                .pattern("ACA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', SRItemRegsitry.max_ingot.get())
                .define('B', SRItemRegsitry.fel_metal.get())
                .define('C', SRItemRegsitry.max_smithing_template.get())
                .save(pvd,"srelic:max_smithing_template_copy");
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.fire_smithing_template.get(), 2)::unlockedBy, Items.NETHERITE_INGOT)
                .pattern("ACA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', SRItemRegsitry.flame_netherite_alloy.get())
                .define('B', SRItemRegsitry.fel_metal.get())
                .define('C', SRItemRegsitry.fire_smithing_template.get())
                .save(pvd,"srelic:fire_smithing_template_copy");






        smithing(pvd,
                Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE,
                SRItemRegsitry.flame_netherite_alloy.get(),
                SRItemRegsitry.fire_smithing_template.get(),
                Items.MAGMA_BLOCK);

        smithing(pvd,
                Items.NETHERITE_SWORD,
                SRItemRegsitry.flame_netherite_alloy.get(),
                SRItemRegsitry.fire_netherite_sword.get(),
                SRItemRegsitry.fire_smithing_template.get());
        smithing(pvd,
                Items.NETHERITE_PICKAXE,
                SRItemRegsitry.flame_netherite_alloy.get(),
                SRItemRegsitry.fire_netherite_pickaxe.get(),
                SRItemRegsitry.fire_smithing_template.get());



        smithing(pvd,
                SRItemRegsitry.fire_netherite_sword.get(),
                SRItemRegsitry.max_ingot.get(),
                SRItemRegsitry.max_sword.get(),
                SRItemRegsitry.max_smithing_template.get());
        smithing(pvd,
                SRItemRegsitry.fire_netherite_pickaxe.get(),
                SRItemRegsitry.max_ingot.get(),
                SRItemRegsitry.max_pickaxe.get(),
                SRItemRegsitry.max_smithing_template.get());





        smithing(pvd,
                Items.NETHERITE_HELMET,
                SRItemRegsitry.max_ingot.get(),
                SRItemRegsitry.max_helmet.get(),
                SRItemRegsitry.max_smithing_template.get());
        smithing(pvd,
                Items.NETHERITE_CHESTPLATE,
                SRItemRegsitry.max_ingot.get(),
                SRItemRegsitry.max_chestplate.get(),
                SRItemRegsitry.max_smithing_template.get());
        smithing(pvd,
                Items.NETHERITE_LEGGINGS,
                SRItemRegsitry.max_ingot.get(),
                SRItemRegsitry.max_leggings.get(),
                SRItemRegsitry.max_smithing_template.get());
        smithing(pvd,
                Items.NETHERITE_BOOTS,
                SRItemRegsitry.max_ingot.get(),
                SRItemRegsitry.max_boots.get(),
                SRItemRegsitry.max_smithing_template.get());

        smithing(pvd,
                SRItemRegsitry.diamond_block_helmet.get(),
                SRItemRegsitry.windy_core_ingot.get(),
                SRItemRegsitry.sky_helmet.get(),
                Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE);
        smithing(pvd,
                SRItemRegsitry.diamond_block_chestplate.get(),
                SRItemRegsitry.windy_core_ingot.get(),
                SRItemRegsitry.sky_chestplate.get(),
                Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE);
        smithing(pvd,
                SRItemRegsitry.diamond_block_leggings.get(),
                SRItemRegsitry.windy_core_ingot.get(),
                SRItemRegsitry.sky_leggings.get(),
                Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE);
        smithing(pvd,
                SRItemRegsitry.diamond_block_boots.get(),
                SRItemRegsitry.windy_core_ingot.get(),
                SRItemRegsitry.sky_boots.get(),
                Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE);

        smithing(pvd,
                SRItemRegsitry.diamond_block_helmet.get(),
                SRItemRegsitry.oceanic_netherite_alloy.get(),
                SRItemRegsitry.ocean_helmet.get(),
                Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE);
        smithing(pvd,
                SRItemRegsitry.diamond_block_chestplate.get(),
                SRItemRegsitry.oceanic_netherite_alloy.get(),
                SRItemRegsitry.ocean_chestplate.get(),
                Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE);
        smithing(pvd,
                SRItemRegsitry.diamond_block_leggings.get(),
                SRItemRegsitry.oceanic_netherite_alloy.get(),
                SRItemRegsitry.ocean_leggings.get(),
                Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE);
        smithing(pvd,
                SRItemRegsitry.diamond_block_boots.get(),
                SRItemRegsitry.oceanic_netherite_alloy.get(),
                SRItemRegsitry.ocean_boots.get(),
                Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE);

        smithing(pvd,
                Items.DIAMOND_HELMET,
                SRItemRegsitry.diamond_star.get(),
                SRItemRegsitry.diamond_block_helmet.get(),
                Items.DIAMOND_BLOCK);
        smithing(pvd,
                Items.DIAMOND_CHESTPLATE,
                SRItemRegsitry.diamond_star.get(),
                SRItemRegsitry.diamond_block_chestplate.get(),
                Items.DIAMOND_BLOCK);
        smithing(pvd,
                Items.DIAMOND_LEGGINGS,
                SRItemRegsitry.diamond_star.get(),
                SRItemRegsitry.diamond_block_leggings.get(),
                Items.DIAMOND_BLOCK);
        smithing(pvd,
                Items.DIAMOND_BOOTS,
                SRItemRegsitry.diamond_star.get(),
                SRItemRegsitry.diamond_block_boots.get(),
                Items.DIAMOND_BLOCK);

    }
    public static <T> T unlock(RegistrateRecipeProvider pvd, BiFunction<String, InventoryChangeTrigger.TriggerInstance, T> func, Item item) {
        return func.apply("has_" + pvd.safeName(item), DataIngredient.items(item).getCritereon(pvd));
    }
    public static void smithing(RegistrateRecipeProvider pvd, Item in, Item mat, Item out, Item template) {
        smithing(pvd, in, mat, out, pvd,template);
    }

    public static void smithing(RegistrateRecipeProvider pvd, Item in, Item mat, Item out, Consumer<FinishedRecipe> cons, Item template) {
        unlock(pvd, SmithingTransformRecipeBuilder.smithing(Ingredient.of(template), Ingredient.of(in), Ingredient.of(mat), RecipeCategory.MISC, out)::unlocks, mat).save(cons, getID(out));
    }
    private static ResourceLocation getID(Item item) {
        return new ResourceLocation(MODID,  ForgeRegistries.ITEMS.getKey(item).getPath());
    }
}
