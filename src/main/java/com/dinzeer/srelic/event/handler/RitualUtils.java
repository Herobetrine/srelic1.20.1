package com.dinzeer.srelic.event.handler;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import java.util.stream.Stream;

public class RitualUtils {
    // 通用结构验证
    public static boolean validatePlatform(Level level, BlockPos center,
                                           int radius, int yOffset,
                                           Block targetBlock) {
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                BlockPos pos = center.offset(x, yOffset, z);
                if (!level.getBlockState(pos).is(targetBlock)) {
                    return false;
                }
            }
        }
        return true;
    }

    // 四角方块验证
    public static boolean validateCorners(Level level, BlockPos center,
                                         int distance, Block cornerBlock) {
        return Stream.of(
            center.offset(distance, 0, distance),
            center.offset(distance, 0, -distance),
            center.offset(-distance, 0, distance),
            center.offset(-distance, 0, -distance)
        ).allMatch(pos -> level.getBlockState(pos).is(cornerBlock));
    }

    // 安全生成物品实体
    public static void spawnProtectedItem(Level level, BlockPos pos, ItemStack stack) {
        ItemEntity item = new ItemEntity(level,
            pos.getX() + 0.5, 
            pos.getY() + 1.0, 
            pos.getZ() + 0.5, 
            stack
        );
        item.setInvulnerable(true);
        item.setUnlimitedLifetime();
        level.addFreshEntity(item);
    }

    // 通用仪式触发检查
    public static boolean basicRitualCheck(PlayerInteractEvent event,
                                           Item requiredItem,
                                           ResourceKey<Level> destination) {
        return event.getItemStack().getItem() == requiredItem &&
               event.getLevel().dimension().equals(destination) ;
    }
}
