package com.dinzeer.srelic.specialattacks;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class ButterFlyDance {
    public static void doslash(LivingEntity playerIn) {
        // 获取玩家当前视角方向向量（已自动标准化）
        Vec3 lookVec = playerIn.getLookAngle();

        // 计算30格距离的冲刺向量（保持Y轴原有运动状态）
        Vec3 motion = lookVec.scale(30)
                .add(0, playerIn.getDeltaMovement().y, 0);

        // 应用运动向量
        playerIn.setDeltaMovement(motion);

        // 强制同步运动更新（重要！否则客户端不会立即更新位置）
        playerIn.hurtMarked = true;
    }
}
