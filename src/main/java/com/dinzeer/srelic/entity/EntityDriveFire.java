package com.dinzeer.srelic.entity;

import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class EntityDriveFire extends EntityDrive {
    public EntityDriveFire(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level() instanceof ServerLevel serverLevel){
            serverLevel.sendParticles(ParticleTypes.FLAME,
                    this.getX(),this.getY(),this.getZ(),15,0.2,0.2,0.2,0.2);
        }

    }

}
