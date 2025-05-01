package com.dinzeer.srelic.event.handler;


import com.dinzeer.srelic.registry.SRItemRegsitry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.stream.IntStream;

@Mod.EventBusSubscriber
public class BlackHoleRitualHandler {
    private static final int PLATFORM_RADIUS = 2;
    private static final Block PLATFORM_BLOCK = Blocks.OBSIDIAN;
    private static final Block CORNER_BLOCK = Blocks.NETHERITE_BLOCK;
    private static final int CORNER_DISTANCE = 2;

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (!RitualUtils.basicRitualCheck(event, SRItemRegsitry.compressed_alloy.asItem(), Level.END)) return;

        BlockPos center = event.getPos().above();
        boolean validStructure = RitualUtils.validatePlatform(
                event.getLevel(), center,
                PLATFORM_RADIUS, -1, PLATFORM_BLOCK
        ) && RitualUtils.validateCorners(
                event.getLevel(), center,
                CORNER_DISTANCE, CORNER_BLOCK
        );

        if (validStructure) {
            event.getItemStack().shrink(1);
            // 消耗四角方块
            IntStream.of(CORNER_DISTANCE, -CORNER_DISTANCE).forEach(x ->
                    IntStream.of(CORNER_DISTANCE, -CORNER_DISTANCE).forEach(z ->
                            event.getLevel().destroyBlock(center.offset(x, 0, z), false)
                    )
            );

            // 生成物品
            RitualUtils.spawnProtectedItem(
                    event.getLevel(),
                    center,
                    new ItemStack(SRItemRegsitry.black_hole_metal.get())
            );


        }
    }
}
