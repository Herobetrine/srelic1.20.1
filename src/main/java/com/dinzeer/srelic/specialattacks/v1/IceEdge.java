package com.dinzeer.srelic.specialattacks.v1;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
// 新增导入
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.phys.AABB;
import java.util.List;

public class IceEdge {

    public static void doSlash(LivingEntity playerIn) {
        // 原方法体为空，现在实现以下功能：
        // 1. 半径5格范围伤害
        // 2. 粒子圆环特效
        
        if (playerIn == null || playerIn.level() == null) return;
        
        // 计算伤害值：玩家攻击伤害*1.2
        double baseDamage = playerIn.getAttributeValue(Attributes.ATTACK_DAMAGE);
        double finalDamage = baseDamage * 1.2;
        
        // 定义伤害范围（半径5格）
        double radius = 5.0;
        AABB area = new AABB(
            playerIn.getX() - radius, playerIn.getY() - radius, playerIn.getZ() - radius,
            playerIn.getX() + radius, playerIn.getY() + radius, playerIn.getZ() + radius
        );
        
        // 获取范围内的生物实体（排除玩家自身）
        List<LivingEntity> entities = playerIn.level().getEntitiesOfClass(
            LivingEntity.class, area, e -> e != playerIn
        );
        
        // 对每个实体造成魔法伤害
        for (LivingEntity entity : entities) {
            entity.hurt(playerIn.damageSources().magic(), (float)finalDamage);
        }
        
        // 在客户端生成粒子圆环（避免在服务端执行）
        if (playerIn.level().isClientSide) {
            // 粒子生成中心点（玩家位置上方0.5格）
            Vec3 center = playerIn.position().add(0, 0.5, 0);
            int particleCount = 128; // 圆环粒子数量
            
            // 生成圆环粒子
            for (int i = 0; i < particleCount; i++) {
                double angle = (2 * Math.PI * i) / particleCount;
                double xOffset = radius * Math.cos(angle);
                double zOffset = radius * Math.sin(angle);
                
                // 在圆环位置生成雪花粒子
                playerIn.level().addParticle(
                    ParticleTypes.SNOWFLAKE,
                    center.x + xOffset,
                    center.y,
                    center.z + zOffset,
                    0, 0, 0 // 无速度
                );
            }
        }
    }
}