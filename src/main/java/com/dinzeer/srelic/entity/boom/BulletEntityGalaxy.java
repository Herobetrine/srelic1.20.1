package com.dinzeer.srelic.entity.boom;

import com.dinzeer.srelic.entity.BigDriveEnity;
import com.dinzeer.srelic.registry.SREntiteRegristrys;
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

public class BulletEntityGalaxy extends BigDriveEnity {
    public BulletEntityGalaxy(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }
    public static BulletEntityGalaxy createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new BulletEntityGalaxy(SREntiteRegristrys.Bullet, worldIn);
    }


    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();
        Level level = entity.level();
        if (!level.isClientSide()) {
            BlockPos blockpos = entity.blockPosition();
            if (this.level().canSeeSky(blockpos)) {
                LightningBolt lightningbolt = (LightningBolt)EntityType.LIGHTNING_BOLT.create(this.level());
                if (lightningbolt != null) {
                    lightningbolt.moveTo(Vec3.atBottomCenterOf(blockpos));
                    lightningbolt.setCause(this.getOwner() instanceof ServerPlayer ? (ServerPlayer)this.getOwner() : null);
                    this.level().addFreshEntity(lightningbolt);
                    this.playSound(SoundEvents.TRIDENT_THUNDER, 5.0F, 1.0F);
                }
            }
        }

        super.onHitEntity(result);
    }
    @Override
    public int getColor() {
        return 4522239;
    }


}
