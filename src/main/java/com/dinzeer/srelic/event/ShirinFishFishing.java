package com.dinzeer.srelic.event;

import com.dinzeer.srelic.registry.SRStacksReg;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import com.dinzeer.srelic.registry.SRItemRegsitry;

@Mod.EventBusSubscriber
public class ShirinFishFishing {
    
    @SubscribeEvent
    public static void onFishingLoot(net.minecraftforge.event.entity.player.ItemFishedEvent event) {
        ItemStack rod = event.getEntity().getMainHandItem();
        if (rod.isEmpty()) return;
        
        // 检查钓竿是否同时拥有海之眷顾III和钓饵III附魔
        boolean hasLure = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FISHING_SPEED, rod) >= 3;
        boolean hasLuck = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FISHING_LUCK, rod) >= 3;
        
        if (hasLure && hasLuck) {
            SRStacksReg.SHRIN_FISH_STACKS.addStacks(event.getEntity(), 1);
            if (SRStacksReg.SHRIN_FISH_STACKS.getCurrentStacks(event.getEntity())==SRStacksReg.SHRIN_FISH_STACKS.getMaxStacks()){
                SRStacksReg.SHRIN_FISH_STACKS.addStacks(event.getEntity(), -SRStacksReg.SHRIN_FISH_STACKS.getMaxStacks());
                event.getEntity().addItem(new ItemStack(SRItemRegsitry.shirin_fish.get()));
            }
            if (event.getEntity().getRandom().nextDouble() < 0.0623){
            event.getDrops().add(new ItemStack(SRItemRegsitry.shirin_fish.get()));
            }
        }
    }
}