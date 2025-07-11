package com.dinzeer.srelic.event.handler;

import com.dinzeer.srelic.Srelic;
import com.dinzeer.srelic.registry.SRItemRegsitry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Srelic.MODID)
public class GrammerAlloyHandler {
    // 存储玩家击杀蠹虫计数的Map
    private static final Map<UUID, Integer> killCountMap = new HashMap<>();
    
    // 所需的击杀数
    private static final int REQUIRED_KILLS = 20;

    @SubscribeEvent
    public static void onSilverfishKill(LivingDeathEvent event) {
        // 检查死亡实体是否为蠹虫
        if (!(event.getEntity() instanceof Silverfish)) return;
        
        // 检查伤害来源是否为玩家
        if (event.getSource().getEntity() instanceof ServerPlayer player) {
            // 检查玩家主手是否持有烈焰下界合金锭
            ItemStack mainHandItem = player.getMainHandItem();
            if (!mainHandItem.is(SRItemRegsitry.flame_netherite_alloy.get())) return;
            
            // 更新击杀计数
            UUID playerId = player.getUUID();
            int newCount = killCountMap.getOrDefault(playerId, 0) + 1;
            killCountMap.put(playerId, newCount);
            
            // 检查是否达到要求
            if (newCount >= REQUIRED_KILLS) {
                // 给予格拉默合金
                player.addItem(new ItemStack(SRItemRegsitry.GrammerAlloy.get()));
                player.getMainHandItem().shrink(1);
                // 重置计数
                killCountMap.put(playerId, 0);
                // 发送提示消息
                player.sendSystemMessage(Component.translatable("message.srelic.grammer_alloy_obtained"));
            } else {
                // 发送进度消息
                player.sendSystemMessage(Component.translatable("message.srelic.grammer_progress", 
                    newCount, REQUIRED_KILLS));
            }
        }
    }
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onItemTooltip(ItemTooltipEvent event) {
        // 新增：添加ItemStack空值检查
        ItemStack stack = event.getItemStack();
        if (stack == null || stack.isEmpty()) {
            return; // 堆栈为空时直接返回
        }
        
        // 修复：添加空值检查防止渲染线程崩溃
        if (event.getEntity() == null) {
            return; // 实体不存在时直接返回
        }
        
        UUID playerId = event.getEntity().getUUID();
        
        // 修改：添加stack有效性检查
        if (stack.is(SRItemRegsitry.flame_netherite_alloy.get())) {
            // 修复：使用getOrDefault避免空指针
            int killCount = killCountMap.getOrDefault(playerId, 0);
            
            if (killCount > 0) {
                event.getToolTip().add(
                    Component.translatable("tooltip.srelic.grammer_progress",
                        killCount, REQUIRED_KILLS)
                        .withStyle(ChatFormatting.GRAY)
                );
            }
        }
    }
}