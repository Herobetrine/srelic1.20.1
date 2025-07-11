package com.dinzeer.srelic.event.handler;

import com.dinzeer.srelic.registry.SRItemRegsitry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class WindyCoreGet {
    
    @SubscribeEvent
    public static void onPlayerRightClick(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack heldItem = event.getItemStack();
        
        // 检查玩家位置是否在300格以上
        if (player.getY() < 300) return;
        
        // 检查是否为紫水晶碎片
        if (heldItem.getItem() == Items.AMETHYST_SHARD) {
            // 消耗一个物品
            heldItem.shrink(1);
            
            // 创建windy_core物品
            ItemStack windyCore = new ItemStack(SRItemRegsitry.windy_core.get());
            
            // 添加到玩家背包
            if (!player.addItem(windyCore)) {
                // 如果背包已满，则掉落物品
                player.drop(windyCore, false);
            }
            
            event.setCanceled(true); // 阻止原右键行为
        }
    }
}