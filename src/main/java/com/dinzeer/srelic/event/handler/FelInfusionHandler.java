package com.dinzeer.srelic.event.handler;

import com.dinzeer.srelic.registry.SRItemRegsitry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class FelInfusionHandler {
    private static final int PLATFORM_RADIUS = 1;
    private static final Block PLATFORM_BLOCK = Blocks.NETHER_BRICKS;
    private static final Block CORNER_BLOCK = Blocks.SCULK_CATALYST;
    private static final int CORNER_DISTANCE = 2;

    @SubscribeEvent
    public static void onCauldronUse(PlayerInteractEvent.RightClickBlock event) {
        if (!RitualUtils.basicRitualCheck(event, Items.NETHERITE_SCRAP, Level.NETHER) ||
                event.getLevel().getBlockState(event.getPos()).getBlock() != Blocks.CAULDRON) {
            return;
        }

        boolean validStructure = RitualUtils.validatePlatform(
                event.getLevel(), event.getPos(),
                PLATFORM_RADIUS, -1, PLATFORM_BLOCK
        ) && RitualUtils.validateCorners(
                event.getLevel(), event.getPos(),
                CORNER_DISTANCE, CORNER_BLOCK
        );

        if (validStructure) {
            event.getItemStack().shrink(1);
            RitualUtils.spawnProtectedItem(
                    event.getLevel(),
                    event.getPos(),
                    new ItemStack(SRItemRegsitry.fel_metal.get())
            );


        }
    }
}