package com.dinzeer.srelic.entity.boom;

import com.dinzeer.srelic.entity.BigDriveEnity;
import com.dinzeer.srelic.registry.SREntiteRegristrys;
import mods.flammpfeil.slashblade.entity.EntityAbstractSummonedSword;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

public class BulletEntityFire extends BigDriveEnity {
    public BulletEntityFire(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }
    public static BulletEntityFire createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new BulletEntityFire(SREntiteRegristrys.Bullet, worldIn);
    }


    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        Level level = entity.level();
        if (!level.isClientSide()) {
            BlockPos blockpos = entity.blockPosition();
            entity.setSecondsOnFire(8);
            this.playSound(SoundEvents.FIRECHARGE_USE, 5.0F, 1.0F);

        }
    }

    @Override
    public int getColor() {
        return 16711680;
    }
}
