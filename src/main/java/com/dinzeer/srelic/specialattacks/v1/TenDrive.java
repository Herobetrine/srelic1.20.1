package com.dinzeer.srelic.specialattacks.v1;

import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.slasharts.Drive;
import mods.flammpfeil.slashblade.util.AttackManager;
import mods.flammpfeil.slashblade.util.KnockBacks;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class TenDrive {
    public static void doSlash(LivingEntity playerIn, float roll, int lifetime, Vec3 centerOffset,
                               boolean critical, double damage, float minSpeed, float maxSpeed, int count) {
        doSlash(playerIn, roll, lifetime, centerOffset, critical, damage, KnockBacks.cancel, minSpeed, maxSpeed, count);
    }

    public static void doSlash(LivingEntity playerIn, float roll, int lifetime, Vec3 centerOffset,
                               boolean critical, double damage, KnockBacks knockback, float minSpeed, float maxSpeed, int count) {

        int colorCode = playerIn.getMainHandItem().getCapability(ItemSlashBlade.BLADESTATE)
                .map(state -> state.getColorCode()).orElse(0xFF3333FF);

        doSlash(playerIn, roll, lifetime, colorCode, centerOffset, critical, damage, knockback, minSpeed, maxSpeed, count);
    }

    public static void doSlash(LivingEntity playerIn, float roll, int lifetime, int colorCode, Vec3 centerOffset,
                               boolean critical, double damage, KnockBacks knockback, float minSpeed, float maxSpeed, int count) {
                AttackManager.doSlash(playerIn, 0.0f, Vec3.ZERO, false, false, 2F);
                AttackManager.doSlash(playerIn, -90F, Vec3.ZERO, false, false, 2F);
                Drive.doSlash(playerIn,roll,lifetime,colorCode,centerOffset,critical,damage,knockback,minSpeed);
                Drive.doSlash(playerIn,roll-90F,lifetime,colorCode,centerOffset,critical,damage,knockback,minSpeed);





    }
}
