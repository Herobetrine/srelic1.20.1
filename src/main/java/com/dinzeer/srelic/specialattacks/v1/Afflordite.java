package com.dinzeer.srelic.specialattacks.v1;

import com.dinzeer.srelic.Utils.SlashBladeUtil;
import mods.flammpfeil.slashblade.util.AttackManager;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import java.util.Comparator;
import java.util.List;
import net.minecraft.world.phys.AABB;

public class Afflordite {
    public static void doSlash(LivingEntity playerIn) {


       int a= SlashBladeUtil.getRefine((Player) playerIn)/100;


        for (int i = 0; i < a+1; i++)
        {
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
                    if (!playerIn.level().isClientSide) {
                        // 计算传送位置（目标实体身后2格）
                        Vec3 lookVec = target.getLookAngle();
                        Vec3 teleportPos = target.position()
                                .subtract(lookVec.x * 2.0, lookVec.y * 2.0, lookVec.z * 2.0);

                        // 执行传送并同步到客户端
                        playerIn.teleportTo(teleportPos.x, teleportPos.y, teleportPos.z);
                    }
                    else
                    {
                        playerIn.lookAt(EntityAnchorArgument.Anchor.EYES,
                                target.position().add(0, target.getEyeHeight() / 2.0, 0));
                        Vec3 lookVec = target.getLookAngle();
                        Vec3 teleportPos = target.position()
                                .subtract(lookVec.x * 2.0, lookVec.y * 2.0, lookVec.z * 2.0);
                        // 计算玩家到目标的视线方向
                        Vec3 lookDirection = target.position().subtract(teleportPos).normalize();
                        // 计算偏航角(yRot)和俯仰角(xRot)
                        double dx = lookDirection.x;
                        double dz = lookDirection.z;
                        float yRot = (float)(Math.atan2(dz, dx) * (180.0 / Math.PI)) - 90.0F;
                        float xRot = (float)(-Math.asin(lookDirection.y) * (180.0 / Math.PI));

                        // 角度规范化处理（保持在0-360度范围内）
                        yRot = yRot % 360.0F;
                        if (yRot < 0.0F) {
                            yRot += 360.0F;
                        }

                        // 设置玩家的视线方向
                        playerIn.setYRot(yRot);
                        playerIn.setXRot(xRot);

                        // 同步设置身体和头部旋转角度
                        playerIn.setYBodyRot(yRot);
                        playerIn.setYHeadRot(yRot);
                    }
                });
        AttackManager.doSlash(playerIn, 90F, Vec3.ZERO, false, false, 8F);
        }
    }
}