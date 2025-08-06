package com.dinzeer.srelic.event;

import com.dinzeer.srelic.registry.SRBlockRegsitry;
import com.dinzeer.srelic.registry.SRItemRegsitry;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class MaximEvent {
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        BlockState state = event.getState();
        if (state.getBlock() == Blocks.GRASS) {


            if (event.getLevel().getRandom().nextFloat() < 0.01f) {
                event.getPlayer().getCommandSenderWorld().addFreshEntity(
                        new ItemEntity(
                                event.getPlayer().getCommandSenderWorld(),
                                event.getPos().getX() + 0.5,
                                event.getPos().getY() + 0.5,
                                event.getPos().getZ() + 0.5,
                                new ItemStack(SRItemRegsitry.maxim.get())
                        )
                );
            }
        }
    }
}
