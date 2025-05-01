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

    }
    public static <T> T unlock(RegistrateRecipeProvider pvd, BiFunction<String, InventoryChangeTrigger.TriggerInstance, T> func, Item item) {
        return func.apply("has_" + pvd.safeName(item), DataIngredient.items(item).getCritereon(pvd));
    }
}
