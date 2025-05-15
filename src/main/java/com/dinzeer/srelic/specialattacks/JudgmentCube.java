package com.dinzeer.srelic.specialattacks;

import com.dinzeer.srelic.Utils.EntityPointer;
import com.dinzeer.srelic.entity.SwordRainEntityShirin;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class JudgmentCube {
    public static void cleantodo(LivingEntity playerIn)
    {
        LivingEntity target = EntityPointer.raycastForEntityTo(playerIn.level(), playerIn, 32, true);
        if (target == null) {
            Optional<LivingEntity> targetedEntity = EntityPointer.findTargetedEntity(playerIn, 100);
            if (targetedEntity.isEmpty()) return;
            target = targetedEntity.get();
        }


        target.setPos(target.getX(), target.getY() + 10, target.getZ());

        // 生成3x3x3龙息粒子立方体
        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                for (int zOffset = -1; zOffset <= 1; zOffset++) {
                    // 计算粒子生成位置
                    double px = target.getX() + xOffset;
                    double py = target.getY() + yOffset;
                    double pz = target.getZ() + zOffset;

                    // 生成龙息粒子（服务端需通过Packet发送）
                    playerIn.level().addParticle(
                            ParticleTypes.DRAGON_BREATH,
                            px, py, pz,
                            0, 0, 0 // 无速度
                    );
                }
            }







          }



        doslash(playerIn, target);






       }





    public static void doslash(LivingEntity livingEntity, LivingEntity target){

        double baseY = target.position().y +( 5.0 )+ 3.5; // 总高度提升8.5格

// 圆周参数
        double radius = 5.0; // 半径5*i1格
        int count = 20; // 总数20个
        double angleStep = 360.0 / count; // 每18度一个实体


        for (int i = 0; i < count; i++) {
            Level worldIn = livingEntity.level();
            SwordRainEntityShirin ss = new SwordRainEntityShirin(SlashBlade.RegistryEvents.BlisteringSwords, worldIn);

            worldIn.addFreshEntity(ss);


            ss.setIsCritical(false);
            ss.setOwner(livingEntity);
            ss.setColor(livingEntity.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE)
                    .map(ISlashBladeState::getColorCode).get());
            ss.setRoll(0);
            ss.setForward(true);
            ss.setDamage(2);
            // force riding
            ss.startRiding(livingEntity, true);

            ss.doFire();

            // 计算圆周坐标
            double angle = Math.toRadians(angleStep * i);
            double xOffset = radius * Math.cos(angle);
            double zOffset = radius * Math.sin(angle);

            // 设置位置
            ss.setPos(
                    target.position().x + xOffset,
                    baseY, // 统一高度
                    target.position().z + zOffset
            );
            livingEntity.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 0.2F, 1.45F);




        }
    }
}
