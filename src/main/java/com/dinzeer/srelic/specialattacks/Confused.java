package com.dinzeer.srelic.specialattacks;

import com.dinzeer.srelic.entity.RappaEnity;
import com.dinzeer.srelic.registry.SREntiteRegristrys;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class Confused {
    public static void doSlash(LivingEntity playerIn, int lifetime, double damage, float speed, int y) {
        if (playerIn.level().isClientSide()) return;


        int colorCode = (Integer) playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE)
                .map(state -> state.getColorCode()).orElse(-13421569);

        if (playerIn.level().isClientSide()) return;

        // 获取玩家视线方向向量（单位向量）
        Vec3 lookVec = playerIn.getLookAngle().normalize();
        // 获取玩家基础位置（保持原有逻辑）
        Vec3 playerPos = playerIn.position()
                .add(0.0, playerIn.getEyeHeight() * 0.75, 0.0)
                .add(lookVec.scale(1.2));

        // 散射参数配置
        final float horizontalSpread = 30.0f; // 水平散射角度 ±30°
        final float verticalSpread = 15.0f;   // 垂直散射角度 ±15°

        for (int i = 0; i <= 20; i++) {
            // 生成随机偏移角度（基于玩家视线方向）
            float yawOffset = (playerIn.level().random.nextFloat() - 0.5f) * horizontalSpread;
            float pitchOffset = (playerIn.level().random.nextFloat() - 0.5f) * verticalSpread;

            // 根据偏移量计算新方向
            Vec3 direction = calculateScatterDirection(lookVec, yawOffset, pitchOffset);

            // 添加随机位置偏移（保持原有立体感）
            Vec3 spawnPos = playerPos.add(
                    (Math.random() - 0.5) * 1.5 * direction.x,
                    y + (Math.random() - 0.5) * 0.8,
                    (Math.random() - 0.5) * 1.5 * direction.z
            );

            // 创建并设置实体（保持原有逻辑）
            RappaEnity drive = new RappaEnity(SREntiteRegristrys.Rappa, playerIn.level());
            drive.setPos(spawnPos.x, spawnPos.y, spawnPos.z);
            drive.shoot(direction.x, direction.y, direction.z, speed * 1.2f, 12.0f);

            // 属性设置（保持不变）
            drive.setDamage(damage);
            drive.setSpeed(speed);
            drive.setOwner(playerIn);
            drive.setColor(colorCode);
            drive.setLifetime(lifetime);
            playerIn.level().addFreshEntity(drive);
        }
    }
    // 辅助方法：基于视线方向的散射向量计算
    private static Vec3 calculateScatterDirection(Vec3 baseDirection, float yawOffset, float pitchOffset) {
        // 将基础方向转换为旋转角度
        double yaw = Math.atan2(baseDirection.z, baseDirection.x);
        double pitch = Math.asin(baseDirection.y);

        // 应用随机偏移
        yaw += Math.toRadians(yawOffset);
        pitch += Math.toRadians(pitchOffset);

        // 重新计算方向向量
        double xzLen = Math.cos(pitch);
        return new Vec3(
                xzLen * Math.cos(yaw),
                Math.sin(pitch),
                xzLen * Math.sin(yaw)
        ).normalize();
    }
}
