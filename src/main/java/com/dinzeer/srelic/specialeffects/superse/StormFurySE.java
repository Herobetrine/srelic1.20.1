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
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class StormFurySE extends SpecialEffect {
    // 核心参数
    private static final int MAX_WIND_CHARGE = 5;       // 最大风能层数
    private static final float CHARGE_CHANCE = 0.35f;   // 攻击充能概率
    private static final int TORNADO_COST = 3;          // 释放龙卷风消耗层数
    
    // 风暴领域
    private static final int AURA_RADIUS = 5;           // 领域半径
    private static final int AURA_INTERVAL = 20;        // 领域作用间隔(1秒)
    
    // 龙卷风参数
    private static final int TORNADO_DURATION = 100;    // 持续时间(5秒)
    private static final float TORNADO_DAMAGE = 3.0f;   // 每秒基础伤害

    public StormFurySE() {
        super(95, false, false);
    }

    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "storm_fury", player.experienceLevel)) return;

        accumulateWindCharge(player);
        tryLaunchTornado(player, event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (event.phase == TickEvent.Phase.END && 
            SeEX.hasSpecialEffect(player.getMainHandItem(), "storm_fury", player.experienceLevel)) {
            
            applyAuraEffects(player);
            handleMovementBoost(player);
        }
    }

    private static void accumulateWindCharge(Player player) {
        if (player.getRandom().nextFloat() < CHARGE_CHANCE) {
            int charges = Math.min(
                player.getPersistentData().getInt("WindCharges") + 1, 
                MAX_WIND_CHARGE
            );
            player.getPersistentData().putInt("WindCharges", charges);
            
            // 风能粒子特效
            SeEX.spawnRotatingParticles(
                player.level(),
                player.position().add(0, 1, 0),
                ParticleTypes.CLOUD,
                1.2f,
                8 + charges*2,
                360.0f
            );
        }
    }

    private static void tryLaunchTornado(Player player, LivingEntity target) {
        int charges = player.getPersistentData().getInt("WindCharges");
        if (charges >= TORNADO_COST) {
            player.getPersistentData().putInt("WindCharges", charges - TORNADO_COST);
            activateTornado(player, target);
        }
    }

    private static void activateTornado(Player player, LivingEntity center) {
        player.getPersistentData().putLong("TornadoEndTime", 
            player.level().getGameTime() + TORNADO_DURATION);
        
        // 龙卷风实体生成
        player.level().getEntitiesOfClass(LivingEntity.class, 
            center.getBoundingBox().inflate(AURA_RADIUS)).forEach(e -> {
                if (e != player) {
                    Vec3 direction = e.position()
                        .subtract(center.position())
                        .normalize()
                        .scale(0.5);
                    e.push(direction.x, 0.3, direction.z);
                }
            });

        // 持续伤害机制
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 
            TORNADO_DURATION, 2));
        
        SeEX.spawnParticleVortex(
            player.level(),
            center.position(),
            ParticleTypes.CLOUD,
            3.0f,
            24,
            0.2f
        );

    }

    private static void applyAuraEffects(Player player) {
        if (player.tickCount % AURA_INTERVAL != 0) return;

        player.level().getEntitiesOfClass(LivingEntity.class, 
            player.getBoundingBox().inflate(AURA_RADIUS)).forEach(e -> {
                if (e != player) {
                    // 持续风压伤害
                    e.hurt(player.damageSources().magic(), TORNADO_DAMAGE);
                    // 击退效果
                    e.knockback(0.5f, 
                        player.getX() - e.getX(), 
                        player.getZ() - e.getZ());
                }
                // 友方加速
                if (e instanceof Player teammate) {
                    teammate.addEffect(new MobEffectInstance(
                        MobEffects.MOVEMENT_SPEED, 60, 1));
                }
            });
    }

    private static void handleMovementBoost(Player player) {
        // 根据移动速度增加攻击速度
        float speed = (float)player.getDeltaMovement().length();
        int amp = (int)(speed * 3);
        if (amp > 0) {
            player.addEffect(new MobEffectInstance(
                MobEffects.DIG_SPEED, 40, Math.min(amp, 3)));
        }
        
        // 移动轨迹特效
        if (player.level().getGameTime() % 3 == 0) {
            SeEX.spawnParticleTrail(
                player.level(),
                player.position().add(0, 0.2, 0),
                ParticleTypes.CLOUD,
                0.3f
            );
        }
    }
}
