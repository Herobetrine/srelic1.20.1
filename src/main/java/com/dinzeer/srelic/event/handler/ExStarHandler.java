package com.dinzeer.srelic.event.handler;

import com.dinzeer.srelic.registry.SRItemRegsitry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.stream.IntStream;
@Mod.EventBusSubscriber
public class ExStarHandler {
    private static final Block BASE_BLOCK = Blocks.CRYING_OBSIDIAN;
    private static final Block MID_BLOCK = Blocks.ICE;
    private static final Block TOP_BLOCK = Blocks.MAGMA_BLOCK;

    @SubscribeEvent
    public static void onRitualTrigger(PlayerInteractEvent.RightClickBlock event) {
        if (!RitualUtils.basicRitualCheck(event, Items.NETHER_STAR, Level.END) ||
            !event.getLevel().getBlockState(event.getPos()).is(TOP_BLOCK)) return;

        BlockPos center = event.getPos();
        
        // 验证双层结构
        boolean isValid = RitualUtils.validatePlatform(event.getLevel(), center, 2, -2, BASE_BLOCK)
            && RitualUtils.validatePlatform(event.getLevel(), center, 1, -1, MID_BLOCK)
                && RitualUtils.validateCorners(event.getLevel(), center, 2, Blocks.GOLD_BLOCK);

        if (isValid) {
            // 消耗材料
            event.getItemStack().shrink(1);
            IntStream.of(-2, 0, 2).forEach(x ->
                IntStream.of(-2, 0, 2).forEach(z ->
                    event.getLevel().destroyBlock(center.offset(x, -2, z), false)
                )
            );

            // 生成EX之星
            RitualUtils.spawnProtectedItem(
                event.getLevel(),
                center,
                new ItemStack(SRItemRegsitry.ex_star.get(), 1)
            );
            event.getEntity().setHealth(event.getEntity().getHealth()-20);
            // 触发星辉特效
            event.getLevel().explode(null,
                center.getX(), center.getY(), center.getZ(),
                8.0f, true, Level.ExplosionInteraction.NONE
            );
        }
    }
}
