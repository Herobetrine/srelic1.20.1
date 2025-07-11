package com.dinzeer.srelic.entity;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class SwordRianEntity extends SwordRainEntity{
    public SwordRianEntity(EntityType<? extends Projectile> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }


    public static SwordRainEntity createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new SwordRainEntity(SlashBlade.RegistryEvents.HeavyRainSwords, worldIn);
    }


    @Override
    public void rideTick() {
        if (this.itFired() && this.fireTime <= (long)this.tickCount) {
            this.faceEntityStandby();
            this.stopRiding();
            this.tickCount = 0;
            if (this.entityData.get(Forword)){
                Entity sender = getOwner();
                if (sender!=null) {
                    this.entityData.set(Vis,true);
                    Vec3 dir = this.getViewVector(0);
                    Level worldIn = sender.level();


                    final Vec3 _center = new Vec3(this.getX(), this.getY(), this.getZ());
                    List<Entity> _entfound = this.level().getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(60 / 2d), a -> true)
                            .stream()
                            .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
                            .toList();

                    Optional<Entity> entityiterator = _entfound.stream()
                            .filter(e -> e instanceof LivingEntity && ((LivingEntity) e).getHealth() > 0 && e != getOwner() && e != this)
                            .findFirst();
                    Vec3 targetPos = entityiterator.map((e) -> new Vec3(e.getX(), e.getY() + e.getEyeHeight() * 0.5, e.getZ()))
                            .orElseGet(() ->
                            {
                                Vec3 start = sender.getEyePosition(1.0f);
                                Vec3 end = start.add(sender.getLookAngle().scale(40));
                                HitResult result = worldIn.clip(new ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, sender));
                                return result.getLocation();
                            });
                    Vec3 pos = this.getPosition(0.0f);
                    dir = targetPos.subtract(pos).normalize();
                    this.shoot(dir.x, dir.y, dir.z, 2f, 1f);
                }
            }else {

                Vec3 dir = new Vec3(0.0, -1.0, 0.0);
                this.shoot(dir.x, dir.y, dir.z, 4.0F, 2.0F);
            }


        } else {
            this.setDeltaMovement(Vec3.ZERO);
            if (this.canUpdate()) {
                this.baseTick();
            }

            this.faceEntityStandby();
            if (!this.itFired()) {
                int basedelay = 10;
                this.fireTime = (long)(this.tickCount + basedelay + this.getDelay());
                this.doFire();
            }

        }
    }
    private void faceEntityStandby() {
        this.setPos(this.position());
        Random random1 = new Random();
        float floater=0;
        if (random1.nextInt(2)==1){
            floater=45.0f;
        }else {
            floater=-45.0f;
        }
        this.setRot(this.getYRot(), -90.0F+floater);
    }
}
