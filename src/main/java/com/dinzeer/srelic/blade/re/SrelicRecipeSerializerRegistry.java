package com.dinzeer.srelic.blade.re;

import com.dinzeer.srelic.blade.re2.SrelicSlashBladeShapedRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.dinzeer.srelic.Srelic.MODID;

public class SrelicRecipeSerializerRegistry {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister
            .create(ForgeRegistries.RECIPE_SERIALIZERS, MODID);

    public static final RegistryObject<RecipeSerializer<?>> SRelic_SHAPED = RECIPE_SERIALIZER
            .register("srelic_blade", () -> SrelicSlashBladeShapedRecipe.SERIALIZER);
}
