package com.dinzeer.srelic.data;

import cn.mmf.slashblade_addon.data.SlashBladeAddonBuiltInRegistry;
import mods.flammpfeil.slashblade.data.builtin.SlashBladeBuiltInRegistry;
import mods.flammpfeil.slashblade.init.SBItems;
import mods.flammpfeil.slashblade.recipe.RequestDefinition;
import mods.flammpfeil.slashblade.recipe.SlashBladeIngredient;
import mods.flammpfeil.slashblade.recipe.SlashBladeShapedRecipeBuilder;
import mods.flammpfeil.slashblade.registry.slashblade.EnchantmentDefinition;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import twilightforest.init.TFItems;

import java.util.function.Consumer;

import static com.dinzeer.srelic.data.SRelicBuiltInRegsitry.getEnchantmentID;

public class SrelicBladeRecipeProvioder extends RecipeProvider implements IConditionBuilder {
    public SrelicBladeRecipeProvioder(PackOutput output) {
        super(output);
    }

    protected void buildRecipes(Consumer<FinishedRecipe> consumer){
        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.BLADE.location())
                .pattern("NZN")
                .pattern("ZBZ")
                .pattern("NZN")
                .define('Z', SBItems.proudsoul_tiny)
                .define('N', Items.PAPER)
                .define('B',SBItems.slashblade)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.RAIDIAN.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("CHI")
                .define('A', SBItems.proudsoul_trapezohedron)
                .define('B', getItem(TFItems.SNOW_QUEEN_TROPHY.getId()))
                .define('C',Items.NETHER_STAR)
                .define('D', getItem(TFItems.NAGA_TROPHY.getId()))
                .define('E', SlashBladeIngredient.of(
                                RequestDefinition.Builder.newInstance()
                                        .name(SRelicBuiltInRegsitry.BLADE.location())
                                        .killCount(10000)
                                        .refineCount(50)
                                        .build()))
                .define('F',getItem(TFItems.HYDRA_TROPHY.getId()))
                .define('H' ,SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.TUKUMO.location())
                                .build()))
                .define('I',SBItems.proudsoul_trapezohedron)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.FIRE.location())
                .pattern("NZN")
                .pattern("ZBZ")
                .pattern("NXN")
                .define('Z', Items.NETHERITE_INGOT)
                .define('N', Items.BLAZE_ROD)
                .define('B',SlashBladeIngredient.of(
                                RequestDefinition.Builder.newInstance()
                                        .name(SRelicBuiltInRegsitry.BLADE.location())
                                        .killCount(3000)
                                        .refineCount(20)
                                        .build()))
                .define('X',SBItems.slashblade_white)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.ARK_FIRE.location())
                .pattern("ABA")
                .pattern("CDE")
                .pattern("AFA")
                .define('A', Items.NETHERITE_INGOT)
                .define('B', Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                .define('D',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.FIRE.location())
                                .killCount(6000)
                                .refineCount(30)
                                .build()))
                .define('C',Items.MAGMA_CREAM)
                .define('E',Items.BLAZE_POWDER)
                .define('F',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.END_FIRE.location())
                .pattern("ABA")
                .pattern("DEF")
                .pattern("ABA")
                .define('A', SBItems.proudsoul_crystal)
                .define('B', SBItems.proudsoul_trapezohedron)
                .define('D',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeAddonBuiltInRegistry.FROSTY_CHERRY.location())
                                .build()))
                .define('E',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.ARK_FIRE.location())
                                .killCount(12000)
                                .refineCount(50)
                                .build()))
                .define('F',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeAddonBuiltInRegistry.KAMUY_FIRE.location())
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.ETERNAL_VOWS.location())
                .pattern("AAA")
                .pattern("BCB")
                .pattern("BDB")
                .define('A', Items.DIAMOND_BLOCK)
                .define('B', SBItems.proudsoul_sphere)
                .define('C',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .killCount(2000)
                                .refineCount(10)
                                .build()))
                .define('D',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeAddonBuiltInRegistry.KAMUY_WATER.location())
                                .killCount(2000)
                                .refineCount(10)
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.FIRE_KNIGHT.location())
                .pattern("ABA")
                .pattern("ACA")
                .pattern("ADA")
                .define('A', SBItems.proudsoul_sphere)
                .define('B', Items.MAGMA_BLOCK)
                .define('C',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .killCount(4000)
                                .refineCount(20)
                                .addEnchantment(new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT),1))
                                .build()))
                .define('D',Items.TRIDENT)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.ICECRY.location())
                .pattern("ABA")
                .pattern("ACA")
                .pattern("ADA")
                .define('A', SBItems.proudsoul_sphere)
                .define('B', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.KOSEKI.location())

                                .build()))
                .define('C',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .killCount(3000)
                                .refineCount(30)
                                .addEnchantment(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS),5))
                                .build()))
                .define('D',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.SANGE.location())
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.KAFUKA.location())
                .pattern("ABA")
                .pattern("ACA")
                .pattern("ADA")
                .define('A', SBItems.proudsoul_crystal)
                .define('B', SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeAddonBuiltInRegistry.KATANA.location())
                                .addEnchantment(new EnchantmentDefinition(getEnchantmentID(Enchantments.POWER_ARROWS),5))
                                .build()))
                .define('C',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .killCount(2000)
                                .refineCount(10)
                                .addEnchantment(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS),5))
                                .build()))
                .define('D',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeAddonBuiltInRegistry.TACHI.location())
                                .addEnchantment(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING),1))
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.START.location())
                .pattern("NAN")
                .pattern("ZBZ")
                .pattern("NAN")
                .define('Z',  Items.ENDER_EYE)
                .define('N',SBItems.proudsoul_sphere)
                .define('A', Items.NETHERITE_INGOT)
                .define('B',SlashBladeIngredient.of(
                                RequestDefinition.Builder.newInstance()
                                        .name(SRelicBuiltInRegsitry.BLADE.location())
                                        .killCount(3000)
                                        .refineCount(20)
                                        .addEnchantment(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS),5))
                                        .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.NONE.location())
                .pattern("NZN")
                .pattern("ZBZ")
                .pattern("SAS")
                .define('N',  Items.ENDER_EYE)
                .define('S',Items.NETHER_STAR)
                .define('Z', Items.NETHERITE_INGOT)
                .define('B',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.START.location())
                                .killCount(9000)
                                .refineCount(25)
                                .addEnchantment(new EnchantmentDefinition(getEnchantmentID(Enchantments.SHARPNESS),5))
                                .build()))
                .define('A',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeAddonBuiltInRegistry.NIHILBX.location())
                                .refineCount(25)
                                .addEnchantment(new EnchantmentDefinition(getEnchantmentID(Enchantments.MOB_LOOTING),2))
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.RAPPA.location())
                .pattern("ABC")
                .pattern("DED")
                .pattern("FBG")
                .define('A',  Items.GOLD_BLOCK)
                .define('B',  Items.AMETHYST_SHARD)
                .define('C',  Items.DIAMOND_BLOCK)
                .define('D',  Items.REDSTONE_BLOCK)
                .define('E',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .refineCount(25)
                                .addEnchantment(new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE),3))
                                .build()))
                .define('F',  Items.IRON_BLOCK)
                .define('G',  Items.LAPIS_BLOCK)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.STAR.location())
                .pattern("ABA")
                .pattern("CDC")
                .pattern("ABA")
                .define('A',  Items.DIAMOND_BLOCK)
                .define('B',  Items.NETHER_STAR)
                .define('C',  SBItems.proudsoul_trapezohedron)
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .killCount(1000)
                                .refineCount(25)
                                .addEnchantment(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING),3))
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.RED_QUEEN.location())
                .pattern("ABA")
                .pattern("BDB")
                .pattern("ABA")
                .define('A',  SBItems.proudsoul_trapezohedron)
                .define('B',  SBItems.proudsoul_crystal)
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .killCount(3000)
                                .refineCount(25)
                                .addEnchantment(new EnchantmentDefinition(getEnchantmentID(Enchantments.SMITE),5))
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.WATER.location())
                .pattern("ABA")
                .pattern("BDB")
                .pattern("ABA")
                .define('A',  Items.WATER_BUCKET)
                .define('B',  SBItems.proudsoul_sphere)
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .killCount(3000)
                                .refineCount(30)

                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.WOLF.location())
                .pattern("ABA")
                .pattern("CDC")
                .pattern("ECF")
                .define('A',   SBItems.proudsoul_sphere)
                .define('B',  Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                .define('C',  Items.DIAMOND)
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .killCount(5000)
                                .refineCount(30)

                                .build()))
                .define('E',  Items.NETHERITE_BLOCK)
                .define('F',  SlashBladeIngredient.of(
                                RequestDefinition.Builder.newInstance()
                                        .name(SlashBladeBuiltInRegistry.RODAI_NETHERITE.location())
                                        .addEnchantment(new EnchantmentDefinition(getEnchantmentID(Enchantments.UNBREAKING),3))
                                        .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.YE_TACHI.location())
                .pattern("ABA")
                .pattern("BDB")
                .pattern("ABA")
                .define('B',  Items.REDSTONE_BLOCK)
                .define('A',  SBItems.proudsoul_ingot)
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .killCount(3000)
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.YE_TACHI_FIRE.location())
                .pattern("ABA")
                .pattern("BDB")
                .pattern("AEA")
                .define('B',  Items.REDSTONE_BLOCK)
                .define('A',  SBItems.proudsoul_ingot)
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.YE_TACHI.location())
                                .killCount(3000)
                                .build()))
                .define('E',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeAddonBuiltInRegistry.KAMUY_FIRE.location())
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.YE_FIRE.location())
                .pattern("ABA")
                .pattern("BDB")
                .pattern("ABA")
                .define('B',  Items.BLAZE_ROD)
                .define('A',  SBItems.proudsoul_crystal)
                .define('D',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.YE_TACHI.location())
                                .killCount(3000)
                                .refineCount(20)
                                .addEnchantment(new EnchantmentDefinition(getEnchantmentID(Enchantments.FIRE_ASPECT),1))
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.YE_STAR.location())
                .pattern("ABA")
                .pattern("ACA")
                .pattern("DEF")
                .define('B',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeAddonBuiltInRegistry.BLUE.location())
                                .build()))
                .define('A',  SBItems.proudsoul_trapezohedron)
                .define('C',  SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.YE_TACHI.location())
                                .killCount(3000)
                                .refineCount(20)
                                .build()))
                .define('D',  Items.LAPIS_BLOCK)
                .define('E',  Items.DIAMOND_BLOCK)
                .define('F',  Items.QUARTZ_BLOCK)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
    }
    public Item getItem(ResourceLocation item) {
        return ForgeRegistries.ITEMS.getValue(item);
    }
}
