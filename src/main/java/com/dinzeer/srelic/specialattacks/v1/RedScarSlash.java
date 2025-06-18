package com.dinzeer.srelic.specialattacks.v1;

import com.dinzeer.srelic.entity.BigDriveEnity;
import com.dinzeer.srelic.registry.SRStacksReg;
import mods.flammpfeil.slashblade.util.AttackManager;
import mods.flammpfeil.slashblade.util.KnockBacks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

public class RedScarSlash {
    public static void doSlash(LivingEntity playerIn) {
        // 计算视角正前方第二格位置（Y轴与脚部同高）
        Vec3 lookVec = playerIn.getLookAngle().normalize();
        Vec3 targetPos = playerIn.position()
                .add(0, playerIn.getEyeHeight() - 1.0, 0) // 调整到脚部高度
                .add(lookVec.scale(2)); // 前方两格
        
        // 获取周围5格内生物
        AABB area = new AABB(playerIn.blockPosition()).inflate(5);
        playerIn.level().getEntitiesOfClass(LivingEntity.class, area)
                .stream()
                .filter(e -> !e.equals(playerIn))
                .forEach(entity -> {
                    // 设置向目标点移动
                    Vec3 moveVec = targetPos.subtract(entity.position()).normalize();
                    entity.setDeltaMovement(moveVec.scale(0.5));

                    // 粒子效果
                    if (playerIn.level() instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(ParticleTypes.SWEEP_ATTACK,
                                entity.getX(), entity.getY() + 0.5, entity.getZ(),
                                5, 0.2, 0.2, 0.2, 0.02);
                    }
                });
        
        // 播放音效
        playerIn.level().playSound(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(),
                SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 0.8f, 1.2f);
        for (int i = 0; i < 10; i++){
        AttackManager.doSlash(playerIn, -80F+(i*5), Vec3.ZERO, false, false, 1F+( SRStacksReg.RED_SCAR.getCurrentStacks((Player) playerIn)*0.2));
           if (SRStacksReg.RED_SCAR.getCurrentStacks((Player) playerIn)>0) {
               SRStacksReg.RED_SCAR.addStacks((Player) playerIn, -1);
           }
        }
    }
}