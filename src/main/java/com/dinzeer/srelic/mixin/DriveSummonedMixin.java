package com.dinzeer.srelic.mixin;

import com.dinzeer.srelic.Utils.GetNumUtil;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.entity.Projectile;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.checkerframework.checker.units.qual.C;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.List;

import static com.dinzeer.srelic.specialattacks.YunLi.sendParticleCircle;

@Mixin(EntityDrive.class)
public abstract class DriveSummonedMixin {


    @Inject(method = "tick" , at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        EntityDrive entity = (EntityDrive)(Object)this;
        if (entity.getPersistentData().getBoolean("shenjun")){
            final Vec3 _center = entity.position();
            sendParticleCircle((ServerLevel) entity.level(), entity, ParticleTypes.END_ROD, 3, 3);

            List<Entity> _entfound = entity.level().getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(18 / 2d), a -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
            for (Entity entityiterator : _entfound) {
                if (entityiterator != entity.getShooter()) {
                if (entityiterator != entity.getShooter()) {
                    if (entityiterator instanceof LivingEntity) {
                        sendParticleCircle((ServerLevel) entityiterator.level(), entityiterator, ParticleTypes.TOTEM_OF_UNDYING, 1, 4);

                        ((LivingEntity) entityiterator).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 10, 4, (false), (false)));
                        entityiterator.hurt(new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC), entity.getShooter()), (float) ((GetNumUtil.getdamage(entity.getShooter())+5) ));


                    }
                }
                }


            }
        }

    }
}
