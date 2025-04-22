package com.dinzeer.srelic.specialattacks;

import com.dinzeer.srelic.Utils.GetNumUtil;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import mods.flammpfeil.slashblade.util.KnockBacks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class Thunderous {
    public static void doSlash(LivingEntity playerIn) {
        BlockPos playerPos = playerIn.blockPosition();
        Level level = playerIn.level();

        // 在8个方向上生成雷电
        for (int i = 0; i < 8; i++) {
            // 计算每个方向的角度（45度间隔）
            double angle = Math.toRadians(i * 45);
            // 计算坐标偏移（2格距离）
            double xOffset = 5 * Math.cos(angle);
            double zOffset = 5 * Math.sin(angle);

            // 计算最终坐标（保持Y轴与玩家相同）
            BlockPos strikePos = playerPos.offset(
                    (int) Math.round(xOffset),
                    0,
                    (int) Math.round(zOffset)
            );

            // 创建闪电实体
            LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
            if (lightning != null) {
                lightning.moveTo(Vec3.atBottomCenterOf(strikePos));
                if (playerIn instanceof ServerPlayer serverPlayer) {
                    lightning.setCause(serverPlayer);
                    lightning.setDamage((float) (GetNumUtil.getdamage(playerIn)*3));
                }
                level.addFreshEntity(lightning);
            }
        }



        // 新增扩展雷电（10-25道）
        int extraStrikes = 10 + level.random.nextInt(16); // 随机生成10-25次
        for (int i = 0; i < extraStrikes; i++) {
            // 随机角度（0-360度）
            double angle = Math.toRadians(level.random.nextDouble() * 360);
            // 随机距离（10-15格）
            double distance = 10 + level.random.nextDouble() * 5;

            // 极坐标转笛卡尔坐标
            double xOffset = distance * Math.cos(angle);
            double zOffset = distance * Math.sin(angle);

            BlockPos strikePos = playerPos.offset(
                    (int) Math.round(xOffset),
                    0,
                    (int) Math.round(zOffset)
            );

            LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
            if (lightning != null) {
                lightning.moveTo(Vec3.atBottomCenterOf(strikePos));
                if (playerIn instanceof ServerPlayer serverPlayer) {
                    lightning.setCause(serverPlayer);
                    lightning.setDamage((float) (GetNumUtil.getdamage(playerIn)*3));
                }
                // 添加粒子特效差异
                lightning.setVisualOnly(level.random.nextBoolean());
                level.addFreshEntity(lightning);
            }
        }





        // 播放音效（只需要在服务端执行一次）
        if (!level.isClientSide) {
            playerIn.playSound(SoundEvents.TRIDENT_THUNDER, 5.0F, 1.0F);
        }
    }


}
