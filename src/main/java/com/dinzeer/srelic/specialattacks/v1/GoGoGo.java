package com.dinzeer.srelic.specialattacks.v1;

import com.dinzeer.srelic.entity.SwordRainEntityFeiXiao;
import com.dinzeer.srelic.entity.SwordRianEntity;
import com.dinzeer.srelic.registry.SREntiteRegristrys;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class GoGoGo {
    public static void dois(LivingEntity playerIn) {
        // 获取玩家视角方向
        Vec3 lookVec = playerIn.getLookAngle();
        // 定义检测范围，比如前方 20 格，半径 3 格的范围
        AABB detectionBox = playerIn.getBoundingBox().expandTowards(lookVec.scale(20)).inflate(3);

        // 获取范围内所有实体并筛选出敌人（LivingEntity）
        List<LivingEntity> entities = playerIn.level().getEntitiesOfClass(LivingEntity.class, detectionBox);
        entities.remove(playerIn); // 排除自己

        if (!entities.isEmpty()) {
            LivingEntity target = entities.get(0); // 取第一个敌人为目标

            // 给敌人添加缓慢效果（持续时间：200 ticks，等级：1）
            MobEffectInstance slowEffect = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1);
            target.addEffect(slowEffect);
        }

//        // 让玩家向前移动 10 格
//        Vec3 moveDirection = lookVec.normalize().scale(10);
//        playerIn.setPos(playerIn.getX() + moveDirection.x, playerIn.getY(), playerIn.getZ() + moveDirection.z);
//
        Vec3 moveDirection = lookVec.normalize().scale(2F); // 调整系数控制速度
        playerIn.setDeltaMovement(
                moveDirection.x * 2,
                playerIn.getDeltaMovement().y, // 保持原有垂直速度
                moveDirection.z * 2
        );
        // 关闭垂直运动（可选）
        playerIn.fallDistance = 0; // 防止掉落伤害
        playerIn.setOnGround(true); // 模拟持续着地状态
    }
    public static void dois2(LivingEntity playerIn) {
        // 获取玩家视角方向
        Vec3 lookVec = playerIn.getLookAngle();
        // 定义检测范围，比如前方 20 格，半径 3 格的范围
        AABB detectionBox = playerIn.getBoundingBox().expandTowards(lookVec.scale(20)).inflate(3);

        // 获取范围内所有实体并筛选出敌人（LivingEntity）
        List<LivingEntity> entities = playerIn.level().getEntitiesOfClass(LivingEntity.class, detectionBox);
        entities.remove(playerIn); // 排除自己

        if (!entities.isEmpty()) {
            LivingEntity target = entities.get(0); // 取第一个敌人为目标

            // 给敌人添加缓慢效果（持续时间：200 ticks，等级：1）
            MobEffectInstance slowEffect = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1);
            target.addEffect(slowEffect);
        }

//        // 让玩家向前移动 10 格
//        Vec3 moveDirection = lookVec.normalize().scale(10);
//        playerIn.setPos(playerIn.getX() + moveDirection.x, playerIn.getY(), playerIn.getZ() + moveDirection.z);
//
        Vec3 moveDirection = lookVec.normalize().scale(-2F); // 调整系数控制速度
        playerIn.setDeltaMovement(
                moveDirection.x * 2,
                playerIn.getDeltaMovement().y, // 保持原有垂直速度
                moveDirection.z * 2
        );
        // 关闭垂直运动（可选）
        playerIn.fallDistance = 0; // 防止掉落伤害
        playerIn.setOnGround(true); // 模拟持续着地状态
    }










    public static void doSlashs(LivingEntity playerIn) {
        Level worldIn = playerIn.level();

        int count = 2;
        for (int i=0;i<count;i++){
        SwordRianEntity ss = new SwordRianEntity(SlashBlade.RegistryEvents.HeavyRainSwords, worldIn);

        worldIn.addFreshEntity(ss);


        ss.setIsCritical(false);
        ss.setOwner(playerIn);
        ss.setColor(playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE).map(ISlashBladeState::getColorCode).get());
        ss.setRoll(0);
        ss.setDamage(10);
        // force riding
        ss.startRiding(playerIn, true);
        ss.setForward(true);
        ss.setDelay(5);
        ss.doFire();

        boolean isRight = ss.getDelay() % 2 == 0;
        RandomSource random = worldIn.getRandom();

        double xOffset = 5 * (isRight ? 1 : -1);
        double yOffset = 8;
        double zOffset = random.nextFloat() * 3;

        ss.setPos(playerIn.position().add(xOffset, yOffset, zOffset));


        playerIn.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);
        }
    }
}
