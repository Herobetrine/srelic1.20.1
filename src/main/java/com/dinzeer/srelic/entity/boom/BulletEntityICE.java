package com.dinzeer.srelic.entity.boom;

import com.dinzeer.srelic.entity.BigDriveEnity;
import com.dinzeer.srelic.registry.SREntiteRegristrys;
import mods.flammpfeil.slashblade.ability.StunManager;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.entity.Projectile;
import mods.flammpfeil.slashblade.util.KnockBacks;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.PlayMessages;

public class BulletEntityICE extends BigDriveEnity {
    public BulletEntityICE(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }
    public static BulletEntityICE createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new BulletEntityICE(SREntiteRegristrys.Bullet, worldIn);
    }



    protected void onHitEntity(EntityHitResult result) {
        Entity targetEntity = result.getEntity();
        if (targetEntity instanceof LivingEntity)
        {
            KnockBacks.cancel.action.accept((LivingEntity) targetEntity);
            StunManager.setStun((LivingEntity) targetEntity);
        }
        if (targetEntity instanceof LivingEntity lv) {
            lv.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,60,3));
        }
        super.onHitEntity(result);
    }
    @Override
    public int getColor() {
        return 16895;
    }
}
