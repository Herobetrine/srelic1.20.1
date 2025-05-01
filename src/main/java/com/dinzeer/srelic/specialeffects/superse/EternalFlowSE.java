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
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EternalFlowSE extends SpecialEffect {
    // 核心参数
    private static final int MAX_TIDE = 7;            // 最大潮汐层数
    private static final float HEAL_PER_TIDE = 0.03f; // 每层治疗加成
    private static final int FIELD_RADIUS = 6;        // 领域半径
    
    // 潮汐护盾
    private static final int SHIELD_COOLDOWN = 400;   // 护盾冷却(20秒)
    private static final float SHIELD_ABSORB = 0.7f;  // 伤害吸收率
    private static final int TIDE_RELEASE = 3;        // 释放护盾需要的层数
    
    // 激流爆发
    private static final int SURGE_COST = 7;          // 激流消耗层数
    private static final float SURGE_DAMAGE = 12.0f;  // 基础冲击伤害

    public EternalFlowSE() {
        super(95, true, true);
    }

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "eternal_flow", player.experienceLevel)) return;

        updateTideStacks(player);
        tryReleaseSurge(player, event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            if (SeEX.hasSpecialEffect(player.getMainHandItem(), "eternal_flow", player.experienceLevel)) {
                handleTideEffects(player);
                checkShield(player);
            }
        }
    }

    private static void updateTideStacks(Player player) {
        int tide = player.getPersistentData().getInt("EternalTide");
        tide = Math.min(tide + 1, MAX_TIDE);
        player.getPersistentData().putInt("EternalTide", tide);
        
        // 水流视觉反馈
        SeEX.spawnParticleRing(player, ParticleTypes.BUBBLE, 
            1.2f + tide*0.15f, 12 + tide*2);
    }

    private static void handleTideEffects(Player player) {
        if (player.tickCount % 40 != 0) return;
        
        // 治疗波纹
        int tide = player.getPersistentData().getInt("EternalTide");
        float healAmount = player.getMaxHealth() * HEAL_PER_TIDE * (1 + tide*0.1f);
        
        player.level().getEntitiesOfClass(LivingEntity.class, 
            player.getBoundingBox().inflate(FIELD_RADIUS)).forEach(e -> {
                if (e instanceof Player teammate) {
                    teammate.heal(healAmount);
                    SeEX.spawnParticleRing(teammate, ParticleTypes.GLOW, 0.8f, 8);
                }
            });
    }

    private static void checkShield(Player player) {
        long lastShield = player.getPersistentData().getLong("LastShieldTime");
        int tide = player.getPersistentData().getInt("EternalTide");
        
        if (player.getHealth() / player.getMaxHealth() < 0.3f && 
            tide >= TIDE_RELEASE &&
            player.level().getGameTime() - lastShield > SHIELD_COOLDOWN) {
                
            activateTideShield(player);
        }
    }

    private static void activateTideShield(Player player) {
        player.getPersistentData().putInt("EternalTide", 
            player.getPersistentData().getInt("EternalTide") - TIDE_RELEASE);
        player.getPersistentData().putLong("LastShieldTime", player.level().getGameTime());
        
        // 护盾特效
        SeEX.spawnParticleRing(player, ParticleTypes.FALLING_DRIPSTONE_WATER, 2.5f, 24);
        player.addEffect(new MobEffectInstance(
            MobEffects.DAMAGE_RESISTANCE, 100, 2, false, true));
            
        // 音效反馈
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
            SoundEvents.BUBBLE_COLUMN_UPWARDS_INSIDE, SoundSource.PLAYERS, 1.0F, 0.8F);
    }

    private static void tryReleaseSurge(Player player, LivingEntity target) {
        int tide = player.getPersistentData().getInt("EternalTide");
        if (tide < SURGE_COST) return;

        // 释放激流冲击
        Vec3 direction = target.position().subtract(player.position()).normalize();
        player.level().getEntitiesOfClass(LivingEntity.class, 
            player.getBoundingBox().inflate(8)).forEach(e -> {
                if (e != player && isInCone(player, e, 60.0f)) {
                    e.hurt(player.damageSources().drown(), 
                        SURGE_DAMAGE * (1 + tide*0.15f));
                    e.setDeltaMovement(direction.scale(1.2f));
                    
                    // 水流冲击特效
                    if (e.level() instanceof ServerLevel server) {
                        SeEX.spawnLightningBeam(server, player.position().add(0,1,0), 
                            e.position(), ParticleTypes.BUBBLE_COLUMN_UP, 12);
                    }
                }
            });
        
        player.getPersistentData().putInt("EternalTide", tide - SURGE_COST);
        player.displayClientMessage(Component.literal("§b[千古洑流] 激流释放！"), true);
    }

    private static boolean isInCone(Player player, LivingEntity target, float angle) {
        Vec3 look = player.getLookAngle();
        Vec3 toTarget = target.position().subtract(player.position()).normalize();
        return look.dot(toTarget) > Math.cos(Math.toRadians(angle/2));
    }
}
