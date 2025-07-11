package com.dinzeer.srelic.data;

import cn.mmf.slashblade_addon.data.SlashBladeAddonBuiltInRegistry;
import com.dinzeer.srelic.blade.SRItem;
import com.dinzeer.srelic.blade.re2.SrelicSlashBladeShapedRecipeBuilder;
import com.dinzeer.srelic.registry.SRItemRegsitry;
import mods.flammpfeil.slashblade.data.builtin.SlashBladeBuiltInRegistry;
import mods.flammpfeil.slashblade.init.SBItems;
import mods.flammpfeil.slashblade.recipe.RequestDefinition;
import mods.flammpfeil.slashblade.recipe.SlashBladeIngredient;
import mods.flammpfeil.slashblade.registry.slashblade.EnchantmentDefinition;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Consumer;

import static com.dinzeer.srelic.data.SRelicBuiltInRegsitry.getEnchantmentID;

public class SrelicBladeRecipeProvioder extends RecipeProvider implements IConditionBuilder {
    public SrelicBladeRecipeProvioder(PackOutput output) {
        super(output);
    }

    protected void buildRecipes(Consumer<FinishedRecipe> consumer){
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.BLADE.location())
                .pattern("NZN")
                .pattern("ZBZ")
                .pattern("NZN")
                .define('Z', SBItems.proudsoul_tiny)
                .define('N', Items.PAPER)
                .define('B', SBItems.slashblade_white)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.WOLF.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("AGA")
                .define('A', SBItems.proudsoul_trapezohedron)
                .define('B',Tags.Items.INGOTS_NETHERITE)
                .define('C', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.RODAI_NETHERITE.location())
                                .killCount(1000)
                                .build())
                )
                .define('D', SRItemRegsitry.compressed_alloy)
                .define('E', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .define('F', Tags.Items.GEMS_DIAMOND)
                .define('G', SRItemRegsitry.fel_metal)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);


        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.ICE_BLUE.location())
                .pattern("ABC")
                .pattern("BDB")
                .pattern("EBF")
                .define('A', Items.SNOW_BLOCK)
                .define('B',SRItemRegsitry.soul_metal)
                .define('C', Items.BLUE_ICE
                )
                .define('D', Items.NETHER_STAR)
                .define('E', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                        .proudSoul(30000)
                                        .refineCount(30)
                                .build()))
                .define('F', Items.CHERRY_LEAVES)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.THIRD_RELIC.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A', SBItems.proudsoul_ingot)
                .define('B',SBItems.proudsoul_crystal)
                .define('C', SRItemRegsitry.sakura_steel_ingot
                )
                .define('D',  SBItems.proudsoul_trapezohedron)
                .define('E', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(3000)
                                .refineCount(30)
                                .build()))
                .define('F', SBItems.proudsoul_sphere)
                .define('G', SBItems.proudsoul)
                .define('H', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.MURAMASA.location())
                                .build()))
                .define('I',SBItems.proudsoul_tiny)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);








    }
    public Item getItem(ResourceLocation item) {
        return ForgeRegistries.ITEMS.getValue(item);
    }
}
