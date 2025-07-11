package com.dinzeer.srelic.event.handler;

import com.dinzeer.srelic.Srelic;
import com.dinzeer.srelic.registry.SRItemRegsitry;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class FireHandle {

    // 击杀烈焰人掉落誓炎绯丝 (30%概率)
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Blaze) {
            if (event.getSource().getEntity() instanceof Player player) {
                if (player.getRandom().nextFloat() < 0.3) {
                    ItemStack strand = new ItemStack(SRItemRegsitry.PledgeflameVermilionStrand.get());
                    event.getEntity().spawnAtLocation(strand);
                }
            }
        }
    }

    // 用火焰附加剑破坏蜘蛛网获取誓炎绯丝
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getState().getBlock() == Blocks.COBWEB) {
            Player player = event.getPlayer();
            ItemStack heldItem = player.getMainHandItem();
            
            // 检查主手物品是否有火焰附加附魔
            if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_ASPECT, heldItem) > 0) {
                ItemStack strand = new ItemStack(SRItemRegsitry.PledgeflameVermilionStrand.get());
                Block.popResource(player.level(), event.getPos(), strand);
            }
        }
    }
}