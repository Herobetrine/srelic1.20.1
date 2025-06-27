package com.dinzeer.srelic.specialattacks.v1;

import mods.flammpfeil.slashblade.util.AttackManager;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import java.util.Comparator;
import java.util.List;
import net.minecraft.world.phys.AABB;

public class Afflordite {
    public static void doSlash(LivingEntity playerIn) {
        // 仅在服务器端执行传送逻辑
        if (!playerIn.level().isClientSide) {
            // 获取玩家周围20格内的实体
            AABB area = playerIn.getBoundingBox().inflate(20.0D);
            List<Entity> entities = playerIn.level().getEntities(playerIn, area);
            
            // 过滤并排序实体
            entities.stream()
                .filter(e -> e instanceof LivingEntity)  // 仅生物实体
                .filter(e -> e != playerIn)              // 排除玩家自身
                .filter(playerIn::hasLineOfSight)        // 可见实体
                .min(Comparator.comparingDouble(playerIn::distanceToSqr)) // 取最近实体
                .ifPresent(target -> {
                    // 计算传送位置（目标实体身后2格）
                    Vec3 lookVec = target.getLookAngle();
                    Vec3 teleportPos = target.position()
                        .subtract(lookVec.x * 2.0, lookVec.y * 2.0, lookVec.z * 2.0);
                    
                    // 计算朝向（面向目标实体背部）
                    float yRot = target.getYRot() + 180.0F;
                    if (yRot > 360.0F) yRot -= 360.0F;
                    
                    // 执行传送并同步到客户端
                    playerIn.teleportTo(teleportPos.x, teleportPos.y, teleportPos.z);
                    playerIn.setYRot(yRot);
                    playerIn.setYBodyRot(yRot);
                });
            AttackManager.doSlash(playerIn, 90F, Vec3.ZERO, false, false, 8F);
        }
    }
}