package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class InfernoBlazeSE extends SpecialEffect {
    // 核心参数
    private static final int MAX_HEAT = 36;               // 最大热能层数
    private static final float HEAT_DAMAGE_BONUS = 0.3f;  // 每层伤害增幅
    private static final int MELTDOWN_THRESHOLD = 8;      // 熔核触发层数
    
    // 烈焰风暴
    private static final int STORM_DURATION = 300;        // 风暴持续时间
    private static final float STORM_RADIUS = 10.0f;       // 风暴作用半径

    public InfernoBlazeSE() {
        super(98, true, true);
    }

    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "inferno_blaze", player.experienceLevel)) return;

        accumulateHeat(player);
        applyMoltenStrike(event.getEntity());
        tryTriggerMeltdown(player);
    }

    @SubscribeEvent
    public static void onPlayerMove(LivingEvent.LivingTickEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "inferno_blaze", player.experienceLevel)) return;

        generateFireTrail(player);
    }

    private static void accumulateHeat(Player player) {
        int heat = player.getPersistentData().getInt("InfernoHeat");
        heat = Math.min(heat + 1, MAX_HEAT);
        player.getPersistentData().putInt("InfernoHeat", heat);
        
        // 热能粒子特效
        SeEX.spawnRotatingParticles(
            player.level(),
            player.position().add(0, 1.5, 0),
            ParticleTypes.LAVA,
            1.0f + heat*0.15f,
            16 + heat*2,
            180.0f
        );
    }

    private static void applyMoltenStrike(LivingEntity target) {
        target.setSecondsOnFire(5);
        target.addEffect(new MobEffectInstance(
            MobEffects.WEAKNESS, 100, 1));
        
        // 熔岩喷溅特效
        if (target.level() instanceof ServerLevel server) {
            SeEX.spawnParticleExplosion(
                server,
                target.position(),
                ParticleTypes.FLAME,
                20
            );
            server.playSound(null, target.getX(), target.getY(), target.getZ(),
                SoundEvents.LAVA_AMBIENT, SoundSource.HOSTILE, 0.8F, 0.5F);
        }
    }

    private static void tryTriggerMeltdown(Player player) {
        int heat = player.getPersistentData().getInt("InfernoHeat");
        if (heat >= MELTDOWN_THRESHOLD) {
            executeMeltdown(player);
            player.getPersistentData().putInt("InfernoHeat", 0);
        }
    }

    private static void generateFireTrail(Player player) {
        if (player.getPersistentData().getInt("InfernoHeat") > 0 && 
            player.tickCount % 5 == 0) {
            
            SeEX.spawnGroundFire(
                player.level(),
                player.position(),
                ParticleTypes.SMALL_FLAME,
                3
            );
        }
    }

    private static void executeMeltdown(Player player) {
        // 熔核冲击波
        player.level().getEntitiesOfClass(LivingEntity.class, 
            player.getBoundingBox().inflate(STORM_RADIUS)).forEach(e -> {
                if (e != player) {
                    e.hurt(player.damageSources().onFire(), 
                        player.getAttackStrengthScale(1.0f) * (1 + player.getPersistentData().getInt("InfernoHeat") * HEAT_DAMAGE_BONUS));
                    
                    // 冲击波特效
                    if (e.level() instanceof ServerLevel server) {
                        SeEX.spawnFireVortex(
                            server,
                            e.position(),
                            ParticleTypes.SOUL_FIRE_FLAME,
                            3.0f,
                            12
                        );
                    }
                }
            });
        
        // 烈焰风暴持续效果
        player.addEffect(new MobEffectInstance(
            MobEffects.FIRE_RESISTANCE, STORM_DURATION));
        player.addEffect(new MobEffectInstance(
            MobEffects.MOVEMENT_SPEED, STORM_DURATION, 1));
        
        // 环境点燃
        SeEX.igniteNearbyBlocks(player.level(), player.position(), STORM_RADIUS, 0.3f);
        
        player.displayClientMessage(Component.literal("§6「炎狱劫火·天烬！」"), true);
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
            SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 2.0F, 0.3F);
    }
}
