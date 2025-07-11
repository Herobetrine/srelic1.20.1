package com.dinzeer.srelic.event;

import com.dinzeer.srelic.registry.SRBlockRegsitry;
import com.dinzeer.srelic.registry.SRItemRegsitry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CrimsonPaper {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level world = event.getLevel();
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();
        
        // 防御性检查：确保关键参数有效
        if (world.isClientSide() || player == null || stack.isEmpty()) {
            return;
        }
        
        // 检查是否为CrimsonPaper物品
        if (stack.getItem() != SRItemRegsitry.crimson_paper.get()) {
            return;
        }
        
        BlockPos pos = event.getPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        
        // 排除不可转换的方块：绯影原矿或基岩级硬度
        if (block == SRBlockRegsitry.crimson_shadow_ore.get() || 
            block.defaultDestroyTime() == Blocks.BEDROCK.defaultDestroyTime()) {
            return;
        }
        
        // 转换方块为绯影原矿
        world.setBlockAndUpdate(pos, SRBlockRegsitry.crimson_shadow_ore.get().defaultBlockState());
        
        // 消耗物品（非创造模式）
        if (!player.isCreative()) {
            stack.shrink(1);
        }
        
        // 取消事件防止其他操作
        event.setCanceled(true);
    }
}