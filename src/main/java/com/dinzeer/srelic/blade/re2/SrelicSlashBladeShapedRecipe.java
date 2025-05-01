package com.dinzeer.srelic.blade.re2;


import com.dinzeer.srelic.blade.ISrelicblade;
import com.dinzeer.srelic.blade.SRItem;
import mods.flammpfeil.slashblade.recipe.SlashBladeShapedRecipe;
import mods.flammpfeil.slashblade.registry.slashblade.SlashBladeDefinition;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;

public class SrelicSlashBladeShapedRecipe extends SlashBladeShapedRecipe {

    public static final RecipeSerializer<SrelicSlashBladeShapedRecipe>
            SERIALIZER =
            new SrelicSlashBladeShapedRecipeSerializer<>(
            RecipeSerializer.SHAPED_RECIPE,
                    SrelicSlashBladeShapedRecipe::new);

    private final ResourceLocation outputBlade;

    public SrelicSlashBladeShapedRecipe(ShapedRecipe compose, ResourceLocation outputBlade) {
        super(compose, outputBlade);
        this.outputBlade = outputBlade;
    }


    private static ItemStack getResultBlade(ResourceLocation outputBlade) {
        Item bladeItem = ForgeRegistries.ITEMS.containsKey(outputBlade)
                ? ForgeRegistries.ITEMS.getValue(outputBlade)
                : SRItem.getItem(SRItem.SRELIC_BLADE_ID); // 这里已修改

        return bladeItem.getDefaultInstance();
    }


    public ResourceLocation getOutputBlade() {
        return outputBlade;
    }

    public ItemStack getResultItem(RegistryAccess access) {
        ItemStack result = getResultBlade(this.getOutputBlade());
        if (!ForgeRegistries.ITEMS.getKey(result.getItem()).equals(this.getOutputBlade())) {
            result = access.registryOrThrow(SlashBladeDefinition.REGISTRY_KEY).get(this.getOutputBlade()).getBlade(result.getItem());
        }

        return result;
    }
    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess access) {
        var result = this.getResultItem(access);
        if (!(result.getItem() instanceof ISrelicblade)) {

            result = new ItemStack(SRItem.getItem(SRItem.SRELIC_BLADE_ID));
        }
        var resultState = result.getCapability(ISrelicblade.BLADESTATE).orElseThrow(NullPointerException::new);
        for (var stack : container.getItems()) {
            if (!(stack.getItem() instanceof ISrelicblade))
                continue;
            var ingredientState = stack.getCapability(ISrelicblade.BLADESTATE).orElseThrow(NullPointerException::new);

            resultState.setProudSoulCount(resultState.getProudSoulCount() + ingredientState.getProudSoulCount());
            resultState.setKillCount(resultState.getKillCount() + ingredientState.getKillCount());
            resultState.setRefine(resultState.getRefine() + ingredientState.getRefine());
            resultState.setMaxDamage(resultState.getMaxDamage() + ingredientState.getMaxDamage());
            updateEnchantment(result, stack);
        }

        return result;
    }

    private void updateEnchantment(ItemStack result, ItemStack ingredient) {
        Map<Enchantment, Integer> newItemEnchants = result.getAllEnchantments();
        Map<Enchantment, Integer> oldItemEnchants = ingredient.getAllEnchantments();

        // 直接合并所有附魔（无任何限制）
        for (Map.Entry<Enchantment, Integer> entry : oldItemEnchants.entrySet()) {
            Enchantment enchantment = entry.getKey();
            int oldLevel = entry.getValue();
            int newLevel = Math.max(oldLevel, newItemEnchants.getOrDefault(enchantment, 0));


            newItemEnchants.put(enchantment, newLevel);
        }

        EnchantmentHelper.setEnchantments(newItemEnchants, result);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

}
