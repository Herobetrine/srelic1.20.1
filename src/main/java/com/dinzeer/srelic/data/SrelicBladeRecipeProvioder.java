package com.dinzeer.srelic.data;

import cn.mmf.slashblade_addon.SlashBladeAddon;
import cn.mmf.slashblade_addon.data.SlashBladeAddonBuiltInRegistry;
import com.dinzeer.srelic.Srelic;
import com.dinzeer.srelic.blade.SRItem;
import com.dinzeer.srelic.blade.re2.SrelicSlashBladeShapedRecipeBuilder;
import com.dinzeer.srelic.registry.SRBlockRegsitry;
import com.dinzeer.srelic.registry.SRItemRegsitry;
import mods.flammpfeil.slashblade.data.builtin.SlashBladeBuiltInRegistry;
import mods.flammpfeil.slashblade.init.SBItems;
import mods.flammpfeil.slashblade.item.SwordType;
import mods.flammpfeil.slashblade.recipe.RequestDefinition;
import mods.flammpfeil.slashblade.recipe.SlashBladeIngredient;
import mods.flammpfeil.slashblade.recipe.SlashBladeShapedRecipeBuilder;
import mods.flammpfeil.slashblade.registry.slashblade.EnchantmentDefinition;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
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


        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.dawn_flame_feather.location())
                .pattern(" AB")
                .pattern("DCD")
                .pattern("EF ")
                .define('A', SRItemRegsitry.red_tide_core)
                .define('B', SRItemRegsitry.ignis_cube)
                .define('D', SRItemRegsitry.Emberquill)
                .define('C', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(3000)
                                .build()))
                .define('E', SRItemRegsitry.PledgeflameHeartforge)
                .define('F', SRItemRegsitry.flame_netherite_alloy)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.radiance.location())
                .pattern("HAB")
                .pattern("DCD")
                .pattern("EFI")
                .define('A', SRItemRegsitry.red_tide_core)
                .define('B', SRItemRegsitry.ignis_cube)
                .define('D', SRItemRegsitry.Emberquill)
                .define('C', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(3000)
                                .build()))
                .define('E', SRItemRegsitry.PledgeflameHeartforge)
                .define('F', SRItemRegsitry.flame_netherite_alloy)
                .define('H', Items.CHERRY_LEAVES)
                .define('I', Items.PEONY)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.conspicuous.location())
                .pattern("HAB")
                .pattern("DCD")
                .pattern("EFI")
                .define('A', SRItemRegsitry.red_tide_core)
                .define('B', SRItemRegsitry.ignis_cube)
                .define('D', SRItemRegsitry.Emberquill)
                .define('C', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(3000)
                                .build()))
                .define('E', SRItemRegsitry.PledgeflameHeartforge)
                .define('F', SRItemRegsitry.flame_netherite_alloy)
                .define('H', Items.GOLDEN_SWORD)
                .define('I', Items.GLISTERING_MELON_SLICE)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);


        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.samu.location())
                .pattern("ABB")
                .pattern("DEB")
                .pattern("FGH")
                .define('A', SRItemRegsitry.GrammerAlloy)
                .define('B', SRItemRegsitry.GrammerAlloyEx)
                .define('D', Blocks.MAGMA_BLOCK)
                .define('E', SRItemRegsitry.GrammerCore)
                .define('F', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(3000)
                                .build()))
                .define('H',  SRItemRegsitry.flame_netherite_alloy)
                .define('G', Items.LILY_OF_THE_VALLEY)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.SEVEN_1.location())
                .pattern("ABC")
                .pattern("BDB")
                .pattern("EBF")
                .define('A', Items.DIAMOND_SWORD)
                .define('B', SRItemRegsitry.thunder_netherite_alloy)
                .define('C', SRItemRegsitry.red_tide_core)
                .define('D', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(3000)
                                .build()))
                .define('E', SRItemRegsitry.diamond_star)
                .define('F',  Items.SHIELD)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.SEVEN_2.location())
                .pattern("ABC")
                .pattern("BDB")
                .pattern("EBF")
                .define('A', Items.DIAMOND_AXE)
                .define('B', SRItemRegsitry.thunder_netherite_alloy)
                .define('C', SRItemRegsitry.red_tide_core)
                .define('D', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(3000)
                                .build()))
                .define('E', SRItemRegsitry.diamond_star)
                .define('F', Items.DIAMOND_AXE)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.The_radiance_of_stagnant_water_flow.location())
                .pattern(" AA")
                .pattern("BCD")
                .pattern("ED ")
                .define('A',SRItemRegsitry.oceanic_netherite_alloy)
                .define('B', SRItemRegsitry.NereidSecretBrew)
                .define('E', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(3000)
                                .build()))
                .define('D', SRItemRegsitry.AbyssalLuminanceIngot)
                .define('C', SRItemRegsitry.SurgeheartVortex)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.dash.location())
                .pattern("ABC")
                .pattern("DEB")
                .pattern("FGA")
                .define('A',Items.NETHERITE_AXE)
                .define('B', SRItemRegsitry.windy_core_ingot)
                .define('C',Items.DIAMOND_SWORD)
                .define('D',SBItems.proudsoul_trapezohedron)
                .define('E', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(3000)
                                .build()))
                .define('F',SRItemRegsitry.windy_core)
                .define('G',SBItems.proudsoul_crystal)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.THIRD_BLUE_1.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A', SRItemRegsitry.frozen_netherite_alloy)
                .define('B',Items.SEA_LANTERN)
                .define('C',Items.DIAMOND_SWORD)
                .define('D', SRBlockRegsitry.plum_leaves)
                .define('E', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(3000)
                                .build()))
                .define('F',Items.LAPIS_BLOCK)
                .define('G',Items.SNOW_BLOCK)
                .define('H',Items.BLUE_ICE)
                .define('I',SRItemRegsitry.demon_ingot)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.rose_spear.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A', SRBlockRegsitry.plum_leaves)
                .define('B',Items.REDSTONE_BLOCK)
                .define('C',SRItemRegsitry.max_ingot)
                .define('D', Items.POPPY)
                .define('E', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(3000)
                                .build()))
                .define('F',Items.RED_DYE)
                .define('G',Items.TRIDENT)
                .define('H',SRItemRegsitry.sakura_steel_ingot)
                .define('I',Items.WHITE_DYE)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.STAR.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A', Items.NETHER_STAR)
                .define('B',Items.IRON_INGOT)
                .define('C',SRItemRegsitry.demon_ingot)
                .define('D', Items.IRON_INGOT)
                .define('E', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(3000)
                                .build()))
                .define('F',Items.PAPER)
                .define('G',SRItemRegsitry.demon_ingot)
                .define('H',Items.NETHER_STAR)
                .define('I',SRItemRegsitry.PuleApple)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.NO_END.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A', Items.BLUE_ICE)
                .define('B',SRItemRegsitry.frozen_netherite_alloy)
                .define('C',SRItemRegsitry.compressed_alloy)
                .define('D', SRItemRegsitry.fel_metal)
                .define('E', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(3000)
                                .build()))
                .define('F',SRItemRegsitry.flame_netherite_alloy)
                .define('G',SRItemRegsitry.compressed_alloy)
                .define('H',SRItemRegsitry.demon_ingot)
                .define('I',Items.MAGMA_BLOCK)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.YE_TACHI.location())
                .pattern("  A")
                .pattern("BA ")
                .pattern("CD ")
                .define('A', SRItemRegsitry.crimson_shadow_ingot)
                .define('B',SRItemRegsitry.thunder_netherite_alloy)
                .define('D', SRItemRegsitry.crimson_core)
                .define('C', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(3000)
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.YE_STAR.location())
                .pattern(" AB")
                .pattern("CDE")
                .pattern("BC ")
                .define('A', SRItemRegsitry.crimson_core)
                .define('B',SRItemRegsitry.thunder_netherite_alloy)
                .define('C', SRItemRegsitry.ender_metal)
                .define('D', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.YE_TACHI.location())
                                .proudSoul(3000)
                                .build()))
                .define('E', Items.ENDER_EYE)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.YE_FIRE.location())
                .pattern(" AB")
                .pattern("CDE")
                .pattern("BF ")
                .define('A', SRItemRegsitry.crimson_shadow_ingot)
                .define('B',SRItemRegsitry.thunder_netherite_alloy)
                .define('C', Items.SOUL_CAMPFIRE)
                .define('D', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.YE_TACHI.location())
                                .proudSoul(3000)
                                .build()))
                .define('E',SRItemRegsitry.crimson_core)
                .define('F', Items.WITHER_SKELETON_SKULL)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.YE_TACHI_FIRE.location())
                .pattern(" AB")
                .pattern("CDE")
                .pattern("AF ")
                .define('A', SRItemRegsitry.flame_netherite_alloy)
                .define('B',SRItemRegsitry.thunder_netherite_alloy)
                .define('C', Items.FIRE_CHARGE)
                .define('D', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.YE_TACHI.location())
                                .proudSoul(3000)
                                .build()))
                .define('E',SRItemRegsitry.crimson_core)
                .define('F', Items.BLAZE_ROD)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.ETERNAL_VOWS.location())
                .pattern("ABC")
                .pattern("BDB")
                .pattern("CBE")
                .define('A', Items.HEART_OF_THE_SEA)
                .define('B',SRItemRegsitry.oceanic_netherite_alloy)
                .define('C', SRItemRegsitry.diamond_star)
                .define('D', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(3000)
                                .build()))
                .define('E',Items.ENCHANTED_GOLDEN_APPLE)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.el.location())
                .pattern("ABC")
                .pattern("BAB")
                .pattern("CBD")
                .define('A', SRItemRegsitry.sakura_steel_ingot)
                .define('B',Items.PINK_DYE)
                .define('C', Items.PINK_WOOL)
                .define('D', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(3000)
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.spring.location())
                .pattern("  A")
                .pattern("BCD")
                .pattern("ED ")
                .define('A', Items.TNT)
                .define('B', Items.WEEPING_VINES)
                .define('C', Items.NETHER_STAR)
                .define('D', SRItemRegsitry.sakura_steel_ingot)
                .define('E', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(3000)
                                .build()))

                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.Aphrodite_Lyre.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A', SRItemRegsitry.frozen_netherite_alloy)
                .define('B',SRItemRegsitry.demon_ingot)
                .define('C', SRItemRegsitry.frozen_netherite_alloy)
                .define('D',SRItemRegsitry.demon_ingot)
                .define('E', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(3000)
                                .build()))
                .define('F',SRItemRegsitry.maxim)
                .define('G',SRItemRegsitry.frozen_netherite_alloy)
                .define('H',Items.NOTE_BLOCK)
                .define('I',SRItemRegsitry.red_tide_core)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.FIRE_KNIGHT.location())
                .pattern("A B")
                .pattern("BCD")
                .pattern("EFG")
                .define('A', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(3000)
                                .build()))
                .define('B',Items.FIRE_CORAL)
                .define('C',Items.FIRE_CHARGE)
                .define('D',Items.AMETHYST_SHARD)
                .define('E',Items.FLINT)
                .define('F',Items.NETHER_STAR)
                .define('G',Items.DIAMOND)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.heita.location())
                .pattern("ABC")
                .pattern("HDE")
                .pattern(" FG")
                .define('H', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(3000)
                                .build()))
                .define('A',Items.AMETHYST_SHARD)
                .define('B',Items.DIAMOND_PICKAXE)
                .define('C',Items.PURPLE_DYE)
                .define('D',Items.ICE)
                .define('E',SRItemRegsitry.diamond_star)
                .define('F',Items.DIAMOND_BLOCK)
                .define('G',SRItemRegsitry.frozen_netherite_alloy)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.ice_sword.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A',Items.SNOW_BLOCK)
                .define('B',SRItemRegsitry.diamond_star)
                .define('C',Items.ICE)
                .define('D',SRItemRegsitry.frozen_netherite_alloy)
                .define('E', SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(3000)
                                .build()))
                .define('F',Items.NETHERITE_SCRAP)
                .define('G',Items.SEA_LANTERN)
                .define('H',Items.POINTED_DRIPSTONE)
                .define('I',Items.DIAMOND_SWORD)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.JINYUAN.location())
                .pattern("AB ")
                .pattern("C D")
                .pattern(" EF")
                .define('A',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(3000)
                                .build()))
                .define('B',SRItemRegsitry.thunder_netherite_alloy)
                .define('C',Items.LIGHTNING_ROD)
                .define('D',Items.NETHER_STAR)
                .define('E',Items.ARROW)
                .define('F',Items.TRIDENT)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.RAIDIAN.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A',SRItemRegsitry.thunder_netherite_alloy)
                .define('B',Items.DIAMOND_SWORD)
                .define('C',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(3000)
                                .build()))
                .define('D',Items.SMALL_AMETHYST_BUD)
                .define('E',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.RODAI_NETHERITE.location())
                                .proudSoul(3000)
                                .build()))
                .define('F',Items.CHERRY_SAPLING)
                .define('G',Items.LIGHTNING_ROD)
                .define('H',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.SANGE.location())
                                .proudSoul(3000)
                                .build()))
                .define('I',Items.NETHER_STAR)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.staff_of_homa.location())
                .pattern("ABC")
                .pattern("DEA")
                .pattern("FGA")
                .define('A',SRItemRegsitry.compressed_alloy)
                .define('B',SRItemRegsitry.spirit_butterfly)
                .define('C',SRItemRegsitry.eternal_plum)
                .define('D',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(30000)
                                .killCount(5000)
                                .build()))
                .define('E',SRItemRegsitry.ignis_cube)
                .define('F',SRItemRegsitry.PuleApple)
                .define('G',Items.TRIDENT)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.ButterflyLament.location())
                .pattern("AB ")
                .pattern("CDE")
                .pattern(" F ")
                .define('A',Items.ENCHANTED_GOLDEN_APPLE)
                .define('B',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .proudSoul(30000)
                                .build()))
                .define('C',Items.PURPLE_DYE)
                .define('D',SRItemRegsitry.compressed_alloy)
                .define('E',Items.NETHERRACK)
                .define('F',SRItemRegsitry.spirit_butterfly)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.Abyss_Eye.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A',SRItemRegsitry.NightstalkerIngot)
                .define('B',SRItemRegsitry.NightmareCoreIngot)
                .define('C',SRItemRegsitry.NightterrorBloodwing)
                .define('D',SRItemRegsitry.compressed_alloy)
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                         .proudSoul(10180)
                                        .killCount(2021)
                                        .refineCount(14)
                                .build()))
                .define('F',Items.PINK_TULIP)
                .define('G',SRItemRegsitry.PhantomirageButterfly)
                .define('H',SRItemRegsitry.DreamweaveIngot)
                .define('I',SRItemRegsitry.PhantomTraceIngot)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.idanstar.location())
                .pattern("ABC")
                .pattern("ADA")
                .pattern("EBA")
                .define('A',SRItemRegsitry.soul_metal)
                .define('B',SRItemRegsitry.demon_ingot)
                .define('C',SRItemRegsitry.black_hole_metal)
                .define('D',Items.NETHER_STAR)
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.GENE_HARVESTER.location())
                .pattern("ABC")
                .pattern("DED")
                .pattern("CFA")
                .define('A',
                        Items.PUFFERFISH
                )
                .define('B',Items.NETHER_STAR)
                .define('C',SRItemRegsitry.diamond_star)
                .define('D',SRItemRegsitry.soul_metal)
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .define('F',SRItemRegsitry.demon_ingot)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.redmoon.location())
                .pattern(" AB")
                .pattern("CDA")
                .pattern("EC ")
                .define('A',
                        Items.DIAMOND
                )
                .define('B',Items.NETHERITE_SCRAP)
                .define('D',SRItemRegsitry.diamond_star)
                .define('C',SRItemRegsitry.fel_metal)
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.FROST_SNIPER.location())
                .pattern("ABC")
                .pattern("DEB")
                .pattern("A  ")
                .define('A',
                        SRItemRegsitry.soul_metal
                )
                .define('B',Items.POTION)
                .define('C',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .define('D',SBItems.proudsoul_crystal)
                .define('E',Items.DIAMOND)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.redsakura.location())
                .pattern("ABC")
                .pattern("DED")
                .pattern("CDF")
                .define('A',
                        SRItemRegsitry.max_ingot
                )

                .define('B',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .define('C',SRItemRegsitry.soul_metal)
                .define('D',SRBlockRegsitry.plum_leaves)
                .define('E',Items.ENDER_EYE)
                .define('F',SRItemRegsitry.demon_ingot)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.clound.location())
                .pattern("ABA")
                .pattern("CDC")
                .pattern("EAE")
                .define('A',
                        Items.ROTTEN_FLESH
                )
                .define('B',Items.ENDER_EYE)
                .define('C',Items.SPIDER_EYE)
                .define('D',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .define('E',SBItems.proudsoul_sphere)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.AIR_NONE.location())
                .pattern("ABA")
                .pattern("CDE")
                .pattern("AFA")
                .define('A',SRItemRegsitry.void_ingot
                )
                .define('B',SRItemRegsitry.void_core)
                .define('C',Items.ENDER_EYE)
                .define('D',SRItemRegsitry.shirin_fish)
                .define('E',Items.DRAGON_EGG)
                .define('F',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.AFFLORDITE.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("  G")
                .define('A',SRItemRegsitry.diamond_star
                )
                .define('B',SBItems.proudsoul_crystal)
                .define('C',SBItems.proudsoul_sphere)
                .define('D',Items.RED_TULIP)
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .define('F',Items.REDSTONE)
                .define('G',Items.DIAMOND_HOE)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.FIRE.location())
                .pattern(" AB")
                .pattern("CDE")
                .pattern("FGH")
                .define('A',Items.COPPER_INGOT)
                .define('B',Items.LAVA_BUCKET)
                .define('C',Items.MAGMA_CREAM)
                .define('D',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .define('E',Items.MAGMA_BLOCK)
                .define('F',Items.BLAZE_ROD)
                .define('G',Items.FIRE_CHARGE)
                .define('H',SBItems.proudsoul_tiny)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.the_fire_pink2.location())
                .pattern("ABC")
                .pattern("DED")
                .pattern("CBA")
                .define('A',PotionUtils.setPotion(new ItemStack(Items.TIPPED_ARROW), Potions.HEALING).copy().getItem())
                .define('B',SRItemRegsitry.sakura_steel_ingot)
                .define('C',PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.HEALING).copy().getItem())
                .define('D',Items.CHERRY_LEAVES)
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.FIRE.location())
                                .build()))

                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.the_fire_gray.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A',Items.CLOCK)
                .define('B',PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.HEALING).copy().getItem())
                .define('C',SRItemRegsitry.flame_netherite_alloy)
                .define('D',SRItemRegsitry.rainbow_star)
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.FIRE.location())
                                .build()))
                .define('F',Items.NETHER_STAR)
                .define('G',Items.COAL)
                .define('H',Items.TOTEM_OF_UNDYING)
                .define('I',Items.HEART_OF_THE_SEA)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.the_fire_green2.location())
                .pattern("ABC")
                .pattern("BFB")
                .pattern("DBE")
                .define('A',Items.COMPASS)
                .define('B',SRItemRegsitry.windy_core)
                .define('F',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.FIRE.location())
                                .build()))
                .define('E',Items.ENDER_EYE)
                .define('D',Items.NAME_TAG)
                .define('C',Items.SPYGLASS)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.FIREBlue2.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A',Items.BLUE_ICE)
                .define('B',Items.SNOWBALL)
                .define('C',Items.SNOW)
                .define('D',SRItemRegsitry.soul_metal)
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.FIRE.location())
                                .build()))
                .define('F',Items.STRING)
                .define('G',Items.POWDER_SNOW_BUCKET)
                .define('H',Items.ICE)
                .define('I',Items.PACKED_ICE)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.ARK_FIRE.location())
                .pattern("ABC")
                .pattern("DED")
                .pattern("FBC")
                .define('A',Items.LAVA_BUCKET)
                .define('B',SRItemRegsitry.soul_metal)
                .define('C',SBItems.proudsoul_trapezohedron)
                .define('D',Items.NETHER_STAR)
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.FIRE.location())
                                .build()))
                .define('F',Items.FEATHER)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.END_FIRE.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("CGH")
                .define('A',SRItemRegsitry.ignis_cube)
                .define('B',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.FIREBlue2.location())
                                .build()))
                .define('C',SRItemRegsitry.compressed_alloy)
                .define('D',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.the_fire_gray.location())
                                .build()))
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.ARK_FIRE.location())
                                .build()))
                .define('F',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.the_fire_green2.location())
                                .build()))
                .define('G',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.the_fire_pink2.location())
                                .build()))
                .define('H',SRItemRegsitry.ex_star)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.kill.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("CGH")
                .define('A',SRItemRegsitry.ignis_cube)
                .define('B',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.FIREBlue2.location())
                                .build()))
                .define('C',SRItemRegsitry.compressed_alloy)
                .define('D',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.the_fire_green2.location())
                                .build()))
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.ARK_FIRE.location())
                                .build()))
                .define('F',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.the_fire_gray.location())
                                .build()))
                .define('G',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.the_fire_pink2.location())
                                .build()))
                .define('H',SRItemRegsitry.ex_star)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.galaxy.location())
                .pattern("ABC")
                .pattern("DE ")
                .pattern("FGH")
                .define('A',Items.CHAIN)
                .define('B',Items.LIGHTNING_ROD)
                .define('C',Items.DIAMOND_SWORD)
                .define('D',Items.EGG)
                .define('E',Items.END_CRYSTAL)
                .define('F',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .define('G',Items.BELL)
                .define('H',Items.CHAIN)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.ICECRY.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A',Items.WITHER_ROSE)
                .define('B',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.KOSEKI.location())
                                .build()))
                .define('C',SBItems.proudsoul_trapezohedron)
                .define('D',Items.BLUE_ICE)
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .define('F',Items.ENDER_EYE)
                .define('G',Items.GHAST_TEAR)
                .define('H',Items.NETHERITE_SWORD)
                .define('I',SRItemRegsitry.fel_metal)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.RED_QUEEN.location())
                .pattern("ABC")
                .pattern("DED")
                .pattern("FGH")
                .define('A',Items.SOUL_LANTERN)
                .define('B',SRItemRegsitry.max_ingot)
                .define('C',Items.NETHER_STAR)
                .define('D',Items.ANCIENT_DEBRIS)
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .define('F',Items.LAVA_BUCKET)
                .define('G',Items.BLAST_FURNACE)
                .define('H',Items.DRAGON_BREATH)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.LAOTIE.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A',Items.WAXED_WEATHERED_CUT_COPPER)
                .define('B',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.RODAI_NETHERITE.location())
                                .build()))
                .define('C',SRItemRegsitry.diamond_star)
                .define('D',SRItemRegsitry.demon_ingot)
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .define('F',SRItemRegsitry.max_ingot)
                .define('G',SBItems.proudsoul_trapezohedron)
                .define('H',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.SABIGATANA_BROKEN.location())
                                .build()))
                .define('I',Items.SHIELD)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.START.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.YAMATO.location())
                                .addSwordType(SwordType.BROKEN)
                                .build()))
                .define('B',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.RODAI_STONE.location())
                                .addSwordType(SwordType.BROKEN)
                                .build()))
                .define('C',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.RODAI_WOODEN.location())
                                .addSwordType(SwordType.BROKEN)
                                .build()))
                .define('D',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.RODAI_GOLDEN.location())
                                .addSwordType(SwordType.BROKEN)
                                .build()))
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .addSwordType(SwordType.BROKEN)
                                .build()))
                .define('F',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.RODAI_IRON.location())
                                .addSwordType(SwordType.BROKEN)
                                .build()))
                .define('G',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.RODAI_NETHERITE.location())
                                .addSwordType(SwordType.BROKEN)
                                .build()))
                .define('H',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.RODAI_DIAMOND.location())
                                .addSwordType(SwordType.BROKEN)
                                .build()))
                .define('I',SlashBladeIngredient.of(
                        RequestDefinition.Builder.newInstance()
                                .name(SlashBladeBuiltInRegistry.MURAMASA.location())
                                .addSwordType(SwordType.BROKEN)
                                .build()))
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.NONE.location())
                .pattern("ABC")
                .pattern("AEF")
                .pattern("GAH")
                .define('A',SRItemRegsitry.compressed_alloy)
                .define('B',SRItemRegsitry.black_hole_metal)
                .define('C',SRItemRegsitry.ex_star)
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.START.location())
                                        .addSwordType(SwordType.BROKEN)
                                .build()))
                .define('F',Items.TOTEM_OF_UNDYING)
                .define('G',SRItemRegsitry.rainbow_star)
                .define('H',Items.ECHO_SHARD)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.santa_caterina.location())
                .pattern("A B")
                .pattern("DEF")
                .pattern("GHI")
                .define('A',Items.HONEY_BLOCK)
                .define('B',Items.GOAT_HORN)
                .define('D',Items.DIAMOND_CHESTPLATE)
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .define('F',Items.MAGMA_CREAM)
                .define('G',Items.NETHERITE_SWORD)
                .define('H',Items.TOTEM_OF_UNDYING)
                .define('I',Items.CHAIN)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.KAFUKA.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A',Items.COPPER_INGOT)
                .define('B',Items.LIGHTNING_ROD)
                .define('C',Items.NETHER_STAR)
                .define('D',SRItemRegsitry.ender_metal)
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .define('F',Items.NETHERITE_SCRAP)
                .define('G',Items.STRING)
                .define('H',Items.SCULK_CATALYST)
                .define('I',Items.STRIPPED_WARPED_HYPHAE)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.DOUBLE_FISH.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("G H")
                .define('A',SRItemRegsitry.diamond_star)
                .define('B',SBItems.proudsoul_sphere)
                .define('C',SBItems.proudsoul_crystal)
                .define('D',SRItemRegsitry.ender_metal)
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .define('F',SRItemRegsitry.ender_metal)
                .define('G',Items.AMETHYST_SHARD)
                .define('H',Items.NETHERITE_BOOTS)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);

        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.THE_STAR_OF_THE_FROST.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A',Items.NETHER_STAR)
                .define('B',SBItems.proudsoul_sphere)
                .define('C',SBItems.proudsoul_crystal)
                .define('D',Items.LAPIS_BLOCK)
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .define('F',Items.SNOWBALL)
                .define('G',Items.BLUE_ICE)
                .define('H',SRItemRegsitry.ender_metal)
                .define('I',Items.DIAMOND_BOOTS)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.sky_sword.location())
                .pattern("ABC")
                .pattern("DED")
                .pattern("FGH")
                .define('A',SBItems.proudsoul_sphere)
                .define('B',Items.DIAMOND_SWORD)
                .define('C',Items.CORNFLOWER)
                .define('D',SRItemRegsitry.windy_core)
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .define('F',SBItems.proudsoul_trapezohedron)
                .define('G',SRItemRegsitry.windy_core_ingot)
                .define('H',SBItems.proudsoul_crystal)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.sky_sword_ascent.location())
                .pattern("ABC")
                .pattern("ADA")
                .pattern("EAA")
                .define('A',SRItemRegsitry.windy_core_ingot)
                .define('B',Items.DANDELION)
                .define('C',Items.DIAMOND_SWORD)
                .define('D',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .define('E',SBItems.proudsoul_trapezohedron)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.WATER.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A',Items.HONEY_BOTTLE)
                .define('B',Items.CONDUIT)
                .define('C',Items.GOLDEN_SWORD)
                .define('D',Items.SEA_LANTERN)
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .define('F',SRItemRegsitry.oceanic_netherite_alloy)
                .define('G',Items.ENCHANTED_GOLDEN_APPLE)
                .define('H',Items.BEACON)
                .define('I',SRItemRegsitry.NereidSecretBrew)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.SEELE.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A',SRItemRegsitry.fel_metal)
                .define('B',SRItemRegsitry.compressed_alloy)
                .define('C',SRItemRegsitry.PhantomTraceIngot)
                .define('D',SRItemRegsitry.demon_ingot)
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .define('F',SRItemRegsitry.ender_metal)
                .define('G',SRItemRegsitry.diamond_star)
                .define('H',SRItemRegsitry.soul_metal)
                .define('I',SRItemRegsitry.DreamweaveIngot)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.lightning.location())
                .pattern("ABC")
                .pattern("DEC")
                .pattern("FGH")
                .define('A',SRItemRegsitry.sakura_steel_ingot)
                .define('B',SRItemRegsitry.demon_ingot)
                .define('C',Items.DRAGON_BREATH)
                .define('D',SRItemRegsitry.ex_star)
                .define('E',SRItemRegsitry.compressed_alloy)
                .define('F',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .define('G',SRItemRegsitry.diamond_star)
                .define('H',SRItemRegsitry.soul_metal)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.RAPPA.location())
                .pattern("ABC")
                .pattern("CDE")
                .pattern("FCA")
                .define('A',SRItemRegsitry.compressed_alloy)
                .define('B',SRItemRegsitry.max_ingot)
                .define('C',Items.NETHER_STAR)
                .define('D',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .define('E',SRItemRegsitry.demon_ingot)
                .define('F',SRItemRegsitry.diamond_star)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.sika_gun.location())
                .pattern("ABC")
                .pattern("BDE")
                .pattern("FGH")
                .define('A',Items.BOW)
                .define('B',SRItemRegsitry.demon_ingot)
                .define('C',Items.TIPPED_ARROW)
                .define('D',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.BLADE.location())
                                .build()))
                .define('E',Items.SNOWBALL)
                .define('F',Items.TRIDENT)
                .define('G',Items.EGG)
                .define('H',Items.CROSSBOW)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);
        SrelicSlashBladeShapedRecipeBuilder.shaped(SRelicBuiltInRegsitry.ICE_BLUE_EX.location())
                .pattern("ABC")
                .pattern("DEF")
                .pattern("GHI")
                .define('A',Items.BLUE_ICE)
                .define('B',SRItemRegsitry.frozen_netherite_alloy)
                .define('C',SRItemRegsitry.demon_ingot)
                .define('D',Items.POTION)
                .define('E',SlashBladeIngredient.of(
                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                .name(SRelicBuiltInRegsitry.ICE_BLUE.location())
                                .build()))
                .define('F',SRItemRegsitry.frozen_netherite_alloy)
                .define('G',SRItemRegsitry.ex_star)
                .define('H',SRItemRegsitry.diamond_star)
                .define('I',SRItemRegsitry.max_ingot)
                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(consumer);


        ConditionalRecipe.builder()
                .addCondition(this.modLoaded("energyblade"))
                .addRecipe(recipeConsumer ->
                        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltlnRegsitryHF.samu_hf.location())
                                .pattern(" A ")
                                .pattern(" B ")
                                .pattern(" C ")
                                .define('A', SRItemRegsitry.thunder_netherite_alloy)
                                .define('B', SlashBladeIngredient.of(
                                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),
                                        RequestDefinition.Builder.newInstance()
                                                .name(SRelicBuiltInRegsitry.samu.location())
                                                .build()))
                                .define('C', Items.REDSTONE)
                                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(recipeConsumer)
                )
                .build(consumer, Srelic.prefix("samu_hf_ver"));
        ConditionalRecipe.builder()
                .addCondition(this.modLoaded("energyblade"))
                .addRecipe((r) ->
                        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltlnRegsitryHF.FIRE_HF.location())
                                .pattern(" A ")
                                .pattern(" B ")
                                .pattern("   ")
                                .define('A',SRItemRegsitry.thunder_netherite_alloy)
                                .define('B',SlashBladeIngredient.of(
                                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                                .name(SRelicBuiltInRegsitry.FIRE.location())
                                                .build()))
                                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(r)
                )
                .build(consumer,  Srelic.prefix("fire_hf_ver"));
        ConditionalRecipe.builder()
                .addCondition(this.modLoaded("energyblade"))
                .addRecipe((r) ->
                        SlashBladeShapedRecipeBuilder.shaped(SRelicBuiltlnRegsitryHF.RED_QUEEN_HF.location())
                                .pattern(" A ")
                                .pattern(" B ")
                                .pattern("   ")
                                .define('A',SRItemRegsitry.thunder_netherite_alloy)
                                .define('B',SlashBladeIngredient.of(
                                        SRItem.getItem(SRItem.SRELIC_BLADE_ID),RequestDefinition.Builder.newInstance()
                                                .name(SRelicBuiltInRegsitry.RED_QUEEN.location())
                                                .build()))
                                .unlockedBy(getHasName(SBItems.slashblade), has(SBItems.slashblade)).save(r)
                )
                .build(consumer, Srelic.prefix("red_queen_ver"));
    }
    public Item getItem(ResourceLocation item) {
        return ForgeRegistries.ITEMS.getValue(item);
    }
}
