package com.dinzeer.srelic;

import com.dinzeer.srelic.registry.SRItemRegsitry;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

import java.util.Random;

public class ItemNBTHelper {
    // 创建带有指定特效的测试物品
    public static ItemStack createTestItem(RegistryObject<SpecialEffect> effect) {
        ItemStack stack = new ItemStack(SRItemRegsitry.universal_test.get());
        CompoundTag tag = stack.getOrCreateTag();
        tag.putString("SEffect", effect.getId().toString());
        return stack;
    }



    public static ItemStack createTestItem(SpecialEffect effect) {
        ItemStack stack = new ItemStack(SRItemRegsitry.universal_test.get());
        CompoundTag tag = stack.getOrCreateTag();
        tag.putString("SEffect", effect.toString());
        return stack;
    }








    // 随机生成测试物品
    public static ItemStack createRandomTestItem() {
        int index = new Random().nextInt(SRSpecialEffectsRegistry.PATH_SE_POOL.size());
        return createTestItem(SRSpecialEffectsRegistry.PATH_SE_POOL.get(index));
    }
}
