package com.dinzeer.srelic.specialattacks;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import twilightforest.network.ParticlePacket;

public class WhiteNight {
    public static void dois(LivingEntity playerIn) {
        if (playerIn.level().isClientSide) return;

        // 获取标准化视线方向（确保向量长度准确）
        Vec3 lookVec = playerIn.getLookAngle().normalize();
        double dashPower = 2.5; // 提升冲刺力度

        // 服务端设置运动参数
        Vec3 motion = lookVec.scale(dashPower);
        playerIn.setDeltaMovement(motion);
        playerIn.hurtMarked = true; // 强制同步运动数据到客户端
        playerIn.setOnGround(false); // 解除地面限制

        // 运动学增强配置
        playerIn.fallDistance = 0; // 防止坠落伤害
        playerIn.hasImpulse = true; // 确保物理引擎处理该运动

        // 动态计算碰撞区域（根据实际速度调整）
        double predictDistance = dashPower * 3; // 动态预测距离
        AABB dashArea = playerIn.getBoundingBox()
                .expandTowards(lookVec.scale(predictDistance))
                .inflate(1.0); // 周边1格范围检测

        // 增强型粒子效果（客户端执行）
        if (!playerIn.level().isClientSide) {
            if (playerIn.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        ParticleTypes.CLOUD,
                        playerIn.getX(),
                        playerIn.getY()+1,
                        playerIn.getZ(),
                        15,  // 数量
                        lookVec.x * 0.5,
                        0.2,
                        lookVec.z * 0.5,
                        0.1  // 扩散速度
                );
            }
        }

        // 伤害逻辑（保持原有）
        playerIn.level().getEntitiesOfClass(LivingEntity.class, dashArea, e -> e != playerIn)
                .forEach(entity -> {
                    entity.hurt(entity.damageSources().mobAttack(playerIn),
                            (float) (playerIn.getAttributeValue(Attributes.ATTACK_DAMAGE) * 6.8f));
                    entity.setSecondsOnFire(8);
                });
        createCrossExplosion(playerIn);
    }
    private static void createCrossExplosion(LivingEntity player) {
        if (player.level().isClientSide) return;

        double centerX = player.getX();
        double centerY = player.getY();
        double centerZ = player.getZ();
        int range = 4; // 十字臂长

        // 创建十字检测区域
        AABB detectionArea = new AABB(
                centerX - range, centerY - 1, centerZ - range,
                centerX + range, centerY + 2, centerZ + range
        );

        // 获取范围内所有实体（排除玩家）
        player.level().getEntitiesOfClass(LivingEntity.class, detectionArea, e -> e != player)
                .forEach(entity -> {
                    // 判断是否在十字线上
                    boolean onXAxis = Math.abs(entity.getZ() - centerZ) < 0.5
                            && Math.abs(entity.getX() - centerX) <= range;
                    boolean onZAxis = Math.abs(entity.getX() - centerX) < 0.5
                            && Math.abs(entity.getZ() - centerZ) <= range;

                    if (onXAxis || onZAxis) {
                        entity.hurt(entity.damageSources().explosion(player, player),
                                (float) (player.getAttributeValue(Attributes.ATTACK_DAMAGE) * 2.5f));
                        entity.setSecondsOnFire(3);
                    }
                });

        // 生成十字粒子效果
        for (int i = -range; i <= range; i++) {
            if (i == 0) continue; // 跳过中心点

            // X轴粒子线
            spawnExplosionParticle(player.level(),
                    centerX + i, centerY + 0.5, centerZ);

            // Z轴粒子线
            spawnExplosionParticle(player.level(),
                    centerX, centerY + 0.5, centerZ + i);
        }
    }

    private static void spawnExplosionParticle(Level level, double x, double y, double z) {
        if (!level.isClientSide) {
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                        ParticleTypes.EXPLOSION,
                        x,
                        y,
                        z,
                        10,  // 数量
                        0,
                        0,
                        0,0.1
            );
        }
        }
    }

}
