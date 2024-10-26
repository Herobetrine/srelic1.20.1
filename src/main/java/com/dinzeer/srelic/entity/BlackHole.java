package com.dinzeer.srelic.entity;

import com.dinzeer.srelic.registry.ParticleHelper;
import com.dinzeer.srelic.registry.SREntiteRegristrys;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class BlackHole extends Projectile{
    private static final EntityDataAccessor<Float> DATA_RADIUS = SynchedEntityData.defineId(BlackHole.class, EntityDataSerializers.FLOAT);

    public BlackHole(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public BlackHole(Level pLevel, LivingEntity owner) {
        this(SREntiteRegristrys.BLACK_HOLE.get(), pLevel);

    }

    List<Entity> trackingEntities = new ArrayList<>();



    public void refreshDimensions() {
        double d0 = this.getX();
        double d1 = this.getY();
        double d2 = this.getZ();
        super.refreshDimensions();
        this.setPos(d0, d1, d2);
    }


    private int soundTick;
    private float damage;

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getDamage() {
        return damage;
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        return EntityDimensions.scalable(this.getRadius() * 2.0F, this.getRadius() * 2.0F);
    }

    public void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_RADIUS, 5F);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (DATA_RADIUS.equals(pKey)) {
            this.refreshDimensions();
            if (getRadius() < .1f)
                this.discard();
        }

        super.onSyncedDataUpdated(pKey);
    }

    public void setRadius(float pRadius) {
        if (!this.level().isClientSide) {
            this.getEntityData().set(DATA_RADIUS, Math.min(pRadius, 48));
        }
    }

    public float getRadius() {
        return this.getEntityData().get(DATA_RADIUS);
    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putFloat("Radius", this.getRadius());
        pCompound.putInt("Age", this.tickCount);
        pCompound.putFloat("Damage", this.getDamage());

        super.addAdditionalSaveData(pCompound);
    }
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        this.tickCount = pCompound.getInt("Age");
        this.damage = pCompound.getFloat("Damage");
        if (damage == 0)
            damage = 1;
        if (pCompound.getInt("Radius") > 0)
            this.setRadius(pCompound.getFloat("Radius"));

        super.readAdditionalSaveData(pCompound);

    }
    public static boolean isFriendlyFireBetween(Entity attacker, Entity target) {
        if (attacker == null || target == null)
            return false;
        if (attacker.isPassengerOfSameVehicle(target)) {
            return true;
        }
        var team = attacker.getTeam();
        if (team != null) {
            return team.isAlliedTo(target.getTeam()) && !team.isAllowFriendlyFire();
        }
        //We already manually checked for teams, so this will only return true for any overrides (such as summons)
        return attacker.isAlliedTo(target);
    }

    @Override
    public void tick() {
        super.tick();
        int update = Math.max((int) (getRadius() / 2), 2);
        //prevent lag from giagantic black holes
        if (tickCount % update == 0) {
            updateTrackingEntities();
        }
        var bb = this.getBoundingBox();
        float radius = (float) (bb.getXsize());
        boolean hitTick = this.tickCount % 10 == 0;
        for (Entity entity : trackingEntities) {
            if (entity != getOwner() && !isFriendlyFireBetween(getOwner(), entity)) {
                Vec3 center = bb.getCenter();
                float distance = (float) center.distanceTo(entity.position());
                if (distance > radius) {
                    continue;
                }
                float f = 1 - distance / radius;
                float scale = f * f * f * f * .25f;

                Vec3 diff = center.subtract(entity.position()).scale(scale);
                entity.push(diff.x, diff.y, diff.z);
                if (hitTick && distance < 9 && canHitEntity(entity)) {
                    entity.invulnerableTime = 0;
                    if (entity.level() instanceof ServerLevel serverLevel) {
                        entity.hurt(new DamageSource(serverLevel.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC), getOwner()), 3);
                    }
                }
                entity.fallDistance = 0;
            }
        }
        if (!level().isClientSide) {
            if (tickCount > 20 * 16 * 2) {
                this.discard();
                this.level().playSound(null, BlockPos.containing(this.getX(), this.getY(), this.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.ender_dragon.growl")), SoundSource.NEUTRAL, 1, 1);
                ;

           //     this.playSound(SoundRegistry.BLACK_HOLE_CAST.get(), getRadius() / 2f, 1);
               spawnParticles(level(), ParticleHelper.UNSTABLE_ENDER, getX(), getY() + getRadius(), getZ(), 200, 1, 1, 1, 1, true);
            } else if ((tickCount - 1) % loopSoundDurationInTicks == 0) {
                spawnParticles(level(), ParticleHelper.UNSTABLE_ENDER, getX(), getY() + getRadius(), getZ(), 200, 1, 1, 1, 1, true);
            }
        }
    }
    public static void spawnParticles(Level level, ParticleOptions particle, double x, double y, double z, int count, double deltaX, double deltaY, double deltaZ, double speed, boolean force) {
        level.getServer().getPlayerList().getPlayers().forEach(player -> ((ServerLevel) level).sendParticles(player, particle, force, x, y, z, count, deltaX, deltaY, deltaZ, speed));
    }
    private void updateTrackingEntities() {
        trackingEntities = level().getEntities(this, this.getBoundingBox().inflate(1));
    }

    private static final int loopSoundDurationInTicks = 320;

    @Override
    public boolean displayFireAnimation() {
        return false;
    }

}