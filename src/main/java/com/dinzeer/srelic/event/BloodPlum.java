package com.dinzeer.srelic.event;

import com.dinzeer.srelic.registry.SRBlockRegsitry;
import com.dinzeer.srelic.registry.SRItemRegsitry;
import com.dinzeer.srelic.registry.item.SrelicItem;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.init.SBItems;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.WeakHashMap;

@Mod.EventBusSubscriber
public class BloodPlum {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        BlockState state = event.getState();
        // 仅处理血梅树叶方块
        if (state.getBlock() == SRBlockRegsitry.plum_leaves.get()) {
            ItemStack tool = event.getPlayer().getMainHandItem();
            boolean isShear = tool.getItem() instanceof ShearsItem;
            if (event.getPlayer().isCreative())return;

            

            if (event.getLevel().getRandom().nextFloat() < 0.1f) {
                event.getPlayer().getCommandSenderWorld().addFreshEntity(
                    new ItemEntity(
                        event.getPlayer().getCommandSenderWorld(),
                        event.getPos().getX() + 0.5,
                        event.getPos().getY() + 0.5,
                        event.getPos().getZ() + 0.5,
                        new ItemStack(SRBlockRegsitry.blood_plum_sampling.get())
                    )
                );
            }
            if (event.getLevel().getRandom().nextFloat() < 0.02f&&isShear) {
                event.getPlayer().getCommandSenderWorld().addFreshEntity(
                        new ItemEntity(
                                event.getPlayer().getCommandSenderWorld(),
                                event.getPos().getX() + 0.5,
                                event.getPos().getY() + 0.5,
                                event.getPos().getZ() + 0.5,
                                new ItemStack(SRItemRegsitry.eternal_plum.get())
                        )
                );
            }
            if (event.getLevel().getRandom().nextFloat() < 0.03f) {
                event.getPlayer().getCommandSenderWorld().addFreshEntity(
                        new ItemEntity(
                                event.getPlayer().getCommandSenderWorld(),
                                event.getPos().getX() + 0.5,
                                event.getPos().getY() + 0.5,
                                event.getPos().getZ() + 0.5,
                                new ItemStack(SBItems.proudsoul_tiny)
                        )
                );
            }
            if (event.getLevel().getRandom().nextFloat() < 0.05f) {
                event.getPlayer().getCommandSenderWorld().addFreshEntity(
                        new ItemEntity(
                                event.getPlayer().getCommandSenderWorld(),
                                event.getPos().getX() + 0.5,
                                event.getPos().getY() + 0.5,
                                event.getPos().getZ() + 0.5,
                                new ItemStack(SRItemRegsitry.PuleApple.get())
                        )
                );
            }
        }
    }

    @SubscribeEvent
    public static void handleBladeStandAttack(SlashBladeEvent.BladeStandAttackEvent event) {
        
        if (event.getDamageSource().getEntity() instanceof Player player) {

            ISlashBladeState bladeState = event.getSlashBladeState();
            ItemStack mainHand = player.getMainHandItem();

            // 防御性编程：空值检查
            if (bladeState == null || mainHand.isEmpty()) {
                return;
            }
            if (mainHand.getItem() == SRItemRegsitry.PuleApple.get()) {
            event.setCanceled(true);
                if (mainHand.getCount() > 0) {
                    bladeState.setKillCount(bladeState.getKillCount() + 10);
                    mainHand.shrink(1);
                }
            }
        }
    }
}
