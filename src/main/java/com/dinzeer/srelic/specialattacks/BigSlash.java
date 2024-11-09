package com.dinzeer.srelic.specialattacks;

import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.capability.concentrationrank.ConcentrationRankCapabilityProvider;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.util.KnockBacks;
import mods.flammpfeil.slashblade.util.VectorHelper;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingGetProjectileEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Comparator;
import java.util.List;

import static com.dinzeer.srelic.Srelic.queueServerWork;

import static com.dinzeer.srelic.specialattacks.YunLi.sendParticleCircle;
public class BigSlash {

    public static void doSlash(LivingEntity playerIn, float roll, int lifetime, Vec3 centerOffset,
                               boolean critical, double damage, float minSpeed, float maxSpeed, int count, int cc) {
        doSlash(playerIn, roll, lifetime, centerOffset, critical, damage, KnockBacks.cancel, minSpeed, maxSpeed, count,cc);
    }

    public static void doSlash(LivingEntity playerIn, float roll, int lifetime, Vec3 centerOffset,
                               boolean critical, double damage, KnockBacks knockback, float minSpeed, float maxSpeed, int count,int cc) {
        int colorCode = cc;
        doSlash(playerIn, roll, lifetime, colorCode, centerOffset, critical, damage, knockback, minSpeed, maxSpeed, count);
    }

    public static void doSlash(LivingEntity playerIn, float roll, int lifetime, int colorCode, Vec3 centerOffset,
                               boolean critical, double damage, KnockBacks knockback, float minSpeed, float maxSpeed, int count) {

        if (playerIn.level().isClientSide()) return;



        {
            playerIn.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 45, 1));
            CommandSourceStack commandSourceStack;
            if (playerIn.level() instanceof  ServerLevel serverLevel){
                commandSourceStack =  new CommandSourceStack(
                        CommandSource.NULL,
                        playerIn.position(),
                        playerIn.getRotationVector(),
                        serverLevel,
                        4,
                        playerIn.getName().getString(),
                        playerIn.getDisplayName(),
                        serverLevel.getServer(),
                        playerIn
                );
            } else {
                commandSourceStack = null;
            }
            playerIn.getServer().getCommands().performPrefixedCommand(commandSourceStack, "teleport @s ~ ~1 ~ ~ ~");
            sendParticleCircle((ServerLevel) playerIn.level(), playerIn, ParticleTypes.CLOUD, 4, 25);
            sendParticleCircle((ServerLevel) playerIn.level(), playerIn, ParticleTypes.CLOUD, 3, 10);
            sendParticleCircle((ServerLevel) playerIn.level(), playerIn, ParticleTypes.CLOUD, 2, 6);
            sendParticleCircle((ServerLevel) playerIn.level(), playerIn, ParticleTypes.CLOUD, 1, 3);
                Vec3 pos = playerIn.position().add(0.0D, (double) playerIn.getEyeHeight() * 0.75D, 0.0D)
                        .add(playerIn.getLookAngle().scale(0.3f));

                pos = pos.add(VectorHelper.getVectorForRotation(-90.0F, playerIn.getViewYRot(0)).scale(centerOffset.y))
                        .add(VectorHelper.getVectorForRotation(0, playerIn.getViewYRot(0) + 90).scale(centerOffset.z))
                        .add(playerIn.getLookAngle().scale(centerOffset.z));
                EntityDrive drive = new EntityDrive(SlashBlade.RegistryEvents.Drive, playerIn.level());

                playerIn.level().addFreshEntity(drive);

            drive.setBaseSize(30);
            drive.getDimensions(Pose.STANDING).scale(12,12);



            drive.setPos(pos.x, pos.y, pos.z);
            drive.setDamage(damage*0.02f);
            drive.setSpeed(0.2f);
            drive.shoot(playerIn.getLookAngle().x, playerIn.getLookAngle().y, playerIn.getLookAngle().z, drive.getSpeed(), 0);
            drive.setColor(16514816);
            drive.setOwner(playerIn);


           // drive.setPierce((byte) 10);
            drive.getPersistentData().putBoolean("shenjun",true);
            // 设置 xRot 为 0，使实体水平
            // drive.setIsCritical(critical);
            drive.setKnockBack(knockback);
            drive.setLifetime(lifetime+90);
                if (playerIn != null)
                    playerIn.getCapability(ConcentrationRankCapabilityProvider.RANK_POINT)
                            .ifPresent(rank -> drive.setRank(rank.getRankLevel(playerIn.level().getGameTime())));

            final Vec3 _center = playerIn.position();

            List<Entity> _entfound = playerIn.level().getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(12 / 2d), a -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
            for (Entity entityiterator : _entfound) {
                    if (entityiterator != playerIn) {
                        if (entityiterator instanceof LivingEntity) {
                            ((LivingEntity) entityiterator).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 255, (false), (false)));
                            entityiterator.hurt(new DamageSource(playerIn.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC), playerIn), (float) (damage*0.2));


                        }
                    }


            }





        }

    }
}
