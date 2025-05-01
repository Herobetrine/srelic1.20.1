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
public class CompressedAlloyHandler {
    private static final int BASE_RADIUS = 1;
    private static final Block BASE_BLOCK = Blocks.NETHER_BRICKS;
    private static final Block TARGET_BLOCK = Blocks.NETHERITE_BLOCK;
    private static final int TARGET_RADIUS = 1;

    @SubscribeEvent
    public static void onRitualTrigger(PlayerInteractEvent.RightClickBlock event) {
        // 检查基础条件：下界维度 + 右键点击的是下界合金块
        if (!RitualUtils.basicRitualCheck(event, Items.NETHER_STAR, Level.NETHER) || 
            !event.getLevel().getBlockState(event.getPos()).is(TARGET_BLOCK)) {
            return;
        }

        BlockPos center = event.getPos();
        
        // 验证结构：3x3下界砖基座 + 3x3下界合金层
        boolean isValid = RitualUtils.validatePlatform(
                event.getLevel(), center, 
                BASE_RADIUS, -1, BASE_BLOCK
            ) && RitualUtils.validatePlatform(
                event.getLevel(), center, 
                TARGET_RADIUS, 0, TARGET_BLOCK
            );

        if (isValid) {
            event.getItemStack().shrink(1);
            // 销毁全部9个下界合金块
            for(int x = -1; x <= 1; x++) {
                for(int z = -1; z <= 1; z++) {
                    event.getLevel().destroyBlock(center.offset(x, 0, z), false);
                }
            }

            // 生成压缩合金
            RitualUtils.spawnProtectedItem(
                event.getLevel(),
                center,
                new ItemStack(SRItemRegsitry.compressed_alloy.get())
            );


        }
    }
}
