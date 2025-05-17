package com.dinzeer.srelic.event.handler;

import com.dinzeer.srelic.registry.SRItemRegsitry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Mod.EventBusSubscriber
public class ItemRightClickHandler {



    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        ItemStack stack = player.getMainHandItem();
        BlockState state = event.getLevel().getBlockState(event.getPos());
        if (player.level().isClientSide)return;
        // 检查物品和方块
        if (stack.getItem() == Items.NETHERITE_INGOT
            && state.getBlock() == Blocks.MAGMA_BLOCK
            &&event.getLevel().dimension().equals( Level.NETHER)
        ) {
            
            // 消耗材料
            stack.shrink(1);
            
            // 给予新物品
            ItemStack result = new ItemStack(SRItemRegsitry.flame_netherite_alloy.get());
            if (!player.addItem(result)) {
                player.drop(result, false);
            }
        }
        if (stack.getItem() == Items.IRON_INGOT
                && state.getBlock() == Blocks.CHERRY_LOG
                && isSurroundedByCherryLeaves(event.getLevel(), event.getPos())) {

            processConversion(player, stack, SRItemRegsitry.sakura_steel_ingot.get());

            // 樱花特效
            if (player.level().isClientSide) {
                spawnCherryBlossomParticles(player.level(), event.getPos());
                player.playSound(SoundEvents.BAMBOO_BREAK, 1.0F, 0.8F);
            }
        }

        // 极寒合金仪式
        if (stack.getItem() == Items.NETHERITE_INGOT
                && state.getBlock() == Blocks.BLUE_ICE
                && player.isInWaterRainOrBubble()) { // 需要水中/雨中环境
            processConversion(player, stack, SRItemRegsitry.frozen_netherite_alloy.get());
        }

        // 雷霆合金仪式
        if (stack.getItem() == Items.NETHERITE_INGOT
                && state.getBlock() == Blocks.LIGHTNING_ROD
                && event.getLevel().isThundering()) { // 需要雷暴天气
            processConversion(player, stack, SRItemRegsitry.thunder_netherite_alloy.get());
        }
        if (stack.getItem() == Items.NETHERITE_INGOT
                && state.getBlock() == Blocks.PRISMARINE
                && player.isUnderWater()
               ) {
            processConversion(player, stack, SRItemRegsitry.oceanic_netherite_alloy.get());

            // 添加海洋特效
            if (player.level().isClientSide) {
                player.level().addParticle(ParticleTypes.BUBBLE_COLUMN_UP,
                        player.getX(), player.getY(), player.getZ(), 0, 0.5, 0);
            }
        }










    }




    private static boolean isSurroundedByCherryLeaves(Level level, BlockPos center) {
        return BlockPos.betweenClosedStream(center.offset(-3, -1, -3), center.offset(3, 3, 3))
                .anyMatch(pos -> level.getBlockState(pos).getBlock() == Blocks.CHERRY_LEAVES);
    }

    // 新增粒子生成方法
    private static void spawnCherryBlossomParticles(Level level, BlockPos pos) {
        for(int i = 0; i < 15; ++i) {
            double x = pos.getX() + 0.5D + level.random.nextGaussian() * 0.5D;
            double y = pos.getY() + 1.2D;
            double z = pos.getZ() + 0.5D + level.random.nextGaussian() * 0.5D;
            level.addParticle(ParticleTypes.CHERRY_LEAVES, x, y, z, 0.0D, -0.2D, 0.0D);
        }
    }












    private static void processConversion(Player player, ItemStack stack, Item resultItem) {
        if (!player.level().isClientSide) {
            stack.shrink(1);
            ItemStack result = new ItemStack(resultItem);
            if (!player.addItem(result)) {
                player.drop(result, false);
            }
        }
    }
}
