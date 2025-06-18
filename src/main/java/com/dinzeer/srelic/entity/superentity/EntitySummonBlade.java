package com.dinzeer.srelic.entity.superentity;

import com.dinzeer.srelic.registry.SREntiteRegristrys;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

import javax.annotation.Nullable;

public class EntitySummonBlade extends Projectile {

    public static EntitySummonBlade createInstance(PlayMessages.SpawnEntity packet, Level worldIn) {
        return new EntitySummonBlade(SREntiteRegristrys.SummonBlade, worldIn);
    }

    private static final EntityDataAccessor<Integer> SURVIVAL_TIME = SynchedEntityData.defineId(EntitySummonBlade.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TICKS_EXISTED = SynchedEntityData.defineId(EntitySummonBlade.class, EntityDataSerializers.INT);

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SURVIVAL_TIME, 0);
        this.entityData.define(TICKS_EXISTED, 0);
        this.entityData.define(ROTATIONSPEED, 0f);
    }
    private static final EntityDataAccessor<Float> ROTATIONSPEED = SynchedEntityData.defineId(EntitySummonBlade.class, EntityDataSerializers.FLOAT);


    
    protected EntitySummonBlade(EntityType<? extends Projectile> p_37248_, Level p_37249_, boolean spawnLeft) {
        super(p_37248_, p_37249_);
        this.spawnLeft = spawnLeft;
    }

    public EntitySummonBlade(EntityType<EntitySummonBlade> entitySummonBladeEntityType, Level level) {
        super(SREntiteRegristrys.SummonBlade, level);
    }

    @Override
    public @Nullable Entity getOwner() {
        return super.getOwner();
    }

    @Override
    public void setOwner(@Nullable Entity p_37263_) {
        super.setOwner(p_37263_);
    }

    // 方向选择（左/右）和攻击目标
    private boolean spawnLeft; // true=左侧 false=右侧
    private LivingEntity target;
    
    // 构造函数增加spawnLeft参数
    public EntitySummonBlade(EntityType<? extends Projectile> p_37248_, Level p_37249_, int survivalTime, boolean spawnLeft, boolean spawnLeft1) {
        this(p_37248_, p_37249_, spawnLeft1);
        this.spawnLeft = spawnLeft;
        // 设置存活时间到实体数据
        this.getEntityData().set(SURVIVAL_TIME, survivalTime);
    }

    @Override
    public void tick() {
        super.tick();
        
        // 获取实体数据中的旋转速度值
        float rotationSpeed = this.getEntityData().get(ROTATIONSPEED);
        if (rotationSpeed != 0) {
            this.setYRot(this.getYRot() + rotationSpeed);
            // 保持角度在0-360范围内
            if (this.getYRot() >= 360) this.setYRot(this.getYRot() - 360);
            if (this.getYRot() < 0) this.setYRot(this.getYRot() + 360);
        }
        
        // 获取实体数据中的已存在时间值
        int ticksExisted = this.getEntityData().get(TICKS_EXISTED);
        // 初始移动阶段（前3tick）
        if (ticksExisted < 3) {
            moveToOwnerSide();
            // 设置旋转速度到实体数据（10度/每tick）
            this.getEntityData().set(ROTATIONSPEED, 10.0F); 
        } 
        // 锁定目标阶段
        else if (target == null) {
            findTarget();
            // 停止旋转，准备攻击
            rotationSpeed = 0;
        } 
        // 攻击阶段
        else {
            attackTarget();
            // 攻击时保持当前朝向
            rotationSpeed = 0;
        }
        
        // 更新已存在时间值
        ticksExisted++;
        this.getEntityData().set(TICKS_EXISTED, ticksExisted);
        
        // 获取实体数据中的存活时间值
        int survivalTime = this.getEntityData().get(SURVIVAL_TIME);
        if (this.getLifetime() == 1 && ticksExisted >= survivalTime) {
            this.discard(); // 存活时间结束，销毁实体
        }
    }

    // 向owner侧边移动两格
    private void moveToOwnerSide() {
        Entity owner = getOwner();
        if (owner != null) {
            Vec3 look = owner.getLookAngle();
            Vec3 right = new Vec3(-look.z, 0, look.x).normalize();
            Vec3 offset = right.scale(spawnLeft ? -2 : 2);
            
            setPos(owner.getX() + offset.x, 
                   owner.getY() + 1, 
                   owner.getZ() + offset.z);
        }
    }

    // 锁定owner视线内的目标
    private void findTarget() {
        Entity owner = getOwner();
        if (owner instanceof LivingEntity livingOwner) {
            Vec3 start = livingOwner.getEyePosition();
            Vec3 end = start.add(livingOwner.getLookAngle().scale(20));
            
            // 在视线范围内寻找目标
            this.level().getEntities(owner, new AABB(start, end), entity ->
                entity instanceof LivingEntity && entity != owner
            ).stream().findFirst().ifPresent(entity -> 
                target = (LivingEntity) entity
            );
        }
    }

    // 攻击目标并造成魔法伤害
    private void attackTarget() {
        if (target != null && target.isAlive()) {
            // 向目标移动
            Vec3 motion = new Vec3(
                target.getX() - getX(),
                target.getY() - getY(),
                target.getZ() - getZ()
            ).normalize().scale(0.5);
            
            setDeltaMovement(motion);
            
            // 检查是否击中目标
            if (distanceToSqr(target) < 4.0) {
                // 造成魔法伤害并清除自身
                target.hurt(target.damageSources().magic(), 5.0F);
                discard();
            }
        } else {
            // 目标消失则清除自身
            discard();
        }
    }





    public int getLifetime() {
        return this.getEntityData().get(SURVIVAL_TIME);
    }

    public void setLifetime(int value) {
        this.getEntityData().set(SURVIVAL_TIME, value);
    }


    public boolean isSpawnLeft() {
        return spawnLeft;
    }

    public void setSpawnLeft(boolean spawnLeft) {
        this.spawnLeft = spawnLeft;
    }

    public LivingEntity getTarget() {
        return target;
    }

    public void setTarget(LivingEntity target) {
        this.target = target;
    }
}