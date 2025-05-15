package com.dinzeer.srelic.specialattacks;

import com.dinzeer.srelic.entity.EntityDriveFire;
import com.dinzeer.srelic.registry.SRStacksReg;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.capability.concentrationrank.ConcentrationRankCapabilityProvider;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.util.VectorHelper;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class ClosingSong {
    public static void doslash(LivingEntity playerIn) {
        Vec3 centerOffset = Vec3.ZERO;
        if (playerIn.level().isClientSide()) return;
        if (!(playerIn instanceof Player player))return;
        Vec3 pos = playerIn.position()
                .add(0.0D, (double) playerIn.getEyeHeight() * 0.75D, 0.0D)
                .add(playerIn.getLookAngle().scale(0.3f));

        pos = pos.add(VectorHelper.getVectorForRotation(-90.0F, playerIn.getViewYRot(0)).scale(centerOffset.y))
                .add(VectorHelper.getVectorForRotation(0, playerIn.getViewYRot(0) + 90).scale(centerOffset.z))
                .add(playerIn.getLookAngle().scale(centerOffset.z));

        shootDrive(playerIn, pos, 0);     // 正前方
        shootDrive(playerIn, pos, -20);   // 左侧45度
        shootDrive(playerIn, pos, 20);    // 右侧45度
        if (SRStacksReg.BLAZING_VALOR_STACKS.getCurrentStacks(player)>10){
            shootDrive(playerIn, pos, -10);
            shootDrive(playerIn, pos, 10);
        }
    }

    private static void shootDrive(LivingEntity playerIn, Vec3 pos, float angleOffset) {
        EntityDriveFire drive = new EntityDriveFire(SlashBlade.RegistryEvents.Drive, playerIn.level());
        playerIn.level().addFreshEntity(drive);

        float speed = Mth.randomBetween(drive.level().getRandom(), 2F, 5F);
        drive.setPos(pos.x, pos.y, pos.z);
        drive.setDamage(7.5F);
        drive.setSpeed(speed);

        float yaw = playerIn.getViewYRot(1.0F) + angleOffset; // 使用更平滑的插值Yaw
        Vec3 direction = VectorHelper.getVectorForRotation(0, yaw); // 水平旋转

        drive.shoot(direction.x, direction.y, direction.z, drive.getSpeed(), 0);

        drive.setOwner(playerIn);
        drive.setRotationRoll(90);

        int colorcode = playerIn.getMainHandItem()
                .getCapability(ItemSlashBlade.BLADESTATE)
                .map(state -> state.getColorCode()).orElse(0xFF3333FF);
        drive.setColor(colorcode);
        drive.setIsCritical(true);
        drive.setLifetime(30);

        if (playerIn != null)
            playerIn.getCapability(ConcentrationRankCapabilityProvider.RANK_POINT)
                    .ifPresent(rank -> drive.setRank(rank.getRankLevel(playerIn.level().getGameTime())));
    }

}
