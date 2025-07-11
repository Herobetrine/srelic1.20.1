package com.dinzeer.srelic.event.handler;

// ... existing imports ...
import com.dinzeer.srelic.registry.SRItemRegsitry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class AbyssalHandler {

    @SubscribeEvent
    public static void onRightClickSea(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        
        // 只处理服务端逻辑
        if (level.isClientSide) return;
        
        // 检查点击的是否是海晶灯
        if (level.getBlockState(pos).getBlock() == Blocks.SEA_LANTERN) {
            BlockPos abovePos = pos.above();
            
            // 获取海晶灯上方区域的物品实体
            List<ItemEntity> items = level.getEntitiesOfClass(ItemEntity.class,
                new AABB(abovePos).inflate(0.5));
            
            // 检查所需物品是否存在
            boolean hasHeart = false, hasOceanic = false, hasFrozen = false;
            for (ItemEntity itemEntity : items) {
                ItemStack stack = itemEntity.getItem();
                if (stack.getItem() == Items.HEART_OF_THE_SEA) hasHeart = true;
                else if (stack.getItem() == SRItemRegsitry.oceanic_netherite_alloy.get()) hasOceanic = true;
                else if (stack.getItem() == SRItemRegsitry.frozen_netherite_alloy.get()) hasFrozen = true;
            }
            
            // 如果所有材料都存在
            if (hasHeart && hasOceanic && hasFrozen) {
                // 生成粒子效果
                for (int i = 0; i < 20; i++) {
                    double x = pos.getX() + 0.5 + level.random.nextGaussian() * 0.5;
                    double y = pos.getY() + 1.0 + level.random.nextGaussian() * 0.5;
                    double z = pos.getZ() + 0.5 + level.random.nextGaussian() * 0.5;
                    level.addParticle(ParticleTypes.BUBBLE, x, y, z, 0, 0.1, 0);
                }
                
                // 消耗材料
                for (ItemEntity itemEntity : new ArrayList<>(items)) {
                    ItemStack stack = itemEntity.getItem();
                    if (stack.getItem() == Items.HEART_OF_THE_SEA || 
                        stack.getItem() == SRItemRegsitry.oceanic_netherite_alloy.get() || 
                        stack.getItem() == SRItemRegsitry.frozen_netherite_alloy.get()) {
                        
                        if (stack.getCount() > 1) {
                            stack.shrink(1);
                            itemEntity.setItem(stack);
                        } else {
                            itemEntity.discard();
                        }
                    }
                }
                
                // 移除海晶灯
                level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                
                // 生成渊海流辉锭
                ItemStack result = new ItemStack(SRItemRegsitry.AbyssalLuminanceIngot.get());
                ItemEntity itemEntity = new ItemEntity(level, 
                    pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 
                    result);
                level.addFreshEntity(itemEntity);
                
                event.setCanceled(true);
            }
        }
    }
}