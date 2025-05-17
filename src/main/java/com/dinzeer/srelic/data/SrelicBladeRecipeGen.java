package com.dinzeer.srelic.data;

import com.dinzeer.srelic.registry.SRItemRegsitry;
import com.dinzeer.srelic.registry.SrelicItem;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.function.BiFunction;

public class SrelicBladeRecipeGen {
    public static void onRecipeGen(RegistrateRecipeProvider pvd) {
        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.ender_metal, 1)::unlockedBy, Items.IRON_INGOT)
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
                .pattern("CCC")
                .pattern("ABC")
                .define('A',Items.GLOWSTONE)
                .define('B',Items.REDSTONE)
                .define('C',Items.GOLD_INGOT)
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
                SRItemRegsitry.diamond_star, 1)::unlockedBy, Items.IRON_INGOT)
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Blocks.DIAMOND_BLOCK)
                .define('B',Items.NETHER_STAR)
                .save(pvd);




        unlock(pvd, ShapedRecipeBuilder.shaped(RecipeCategory.MISC,
                SRItemRegsitry.eternal_plum, 1)::unlockedBy, Items.IRON_INGOT)
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A',  Blocks.OAK_LEAVES)
                .define('B',Blocks.DARK_OAK_LEAVES)
                .define('C', Blocks.BIRCH_LEAVES)
                .define('D', Blocks.JUNGLE_LEAVES)
                .define('E', Blocks.SPRUCE_LEAVES)
                .define('F',Blocks.FLOWERING_AZALEA_LEAVES)
                .define('G', Blocks.AZALEA_LEAVES)
                .define('H', Blocks.CHERRY_LEAVES)
                .define('I',  Blocks.ACACIA_LEAVES)
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

    }
    public static <T> T unlock(RegistrateRecipeProvider pvd, BiFunction<String, InventoryChangeTrigger.TriggerInstance, T> func, Item item) {
        return func.apply("has_" + pvd.safeName(item), DataIngredient.items(item).getCritereon(pvd));
    }
}
