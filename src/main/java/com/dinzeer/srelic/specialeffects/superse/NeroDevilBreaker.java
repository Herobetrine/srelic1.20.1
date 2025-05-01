package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
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

import java.util.UUID;

@Mod.EventBusSubscriber
public class NeroDevilBreaker extends SpecialEffect {
    // 核心参数
    private static final int MAX_CHARGE = 3;          // 最大充能层数
    private static final int CHARGE_DECAY = 100;      // 充能衰减时间(5秒)
    private static final float CHARGE_BONUS = 0.5f;   // 每层充能伤害加成
    
    // 恶魔投掷
    private static final int GRAB_RANGE = 4;          // 抓取范围
    private static final int THROW_FORCE = 2;         // 投掷力量
    private static final int GRAB_COOLDOWN = 80;      // 抓取冷却
    
    // 红刀超载
    private static final int OVERLOAD_DURATION = 60;  // 超载持续时间
    private static final float OVERLOAD_DAMAGE = 3.0f;// 超载额外伤害

    public NeroDevilBreaker() {
        super(90, false, true);
    }

    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "nero_devil_breaker", player.experienceLevel)) return;

        handleChargeSystem(player);
        attemptGrabAttack(player, event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            if (SeEX.hasSpecialEffect(player.getMainHandItem(), "nero_devil_breaker", player.experienceLevel)) {
                updateChargeDecay(player);
                handleOverload(player);
            }
        }
    }

    private static void handleChargeSystem(Player player) {
        int charge = player.getPersistentData().getInt("NeroCharge");
        charge = Math.min(charge + 1, MAX_CHARGE);
        player.getPersistentData().putInt("NeroCharge", charge);
        player.getPersistentData().putLong("LastChargeTime", player.level().getGameTime());
        
        SeEX.spawnParticleRing(player, ParticleTypes.LAVA, 1.0f + charge*0.3f, 12);
    }

    private static void attemptGrabAttack(Player player, LivingEntity target) {
        long lastGrab = player.getPersistentData().getLong("LastGrabTime");
        if (player.level().getGameTime() - lastGrab < GRAB_COOLDOWN) return;

        if (player.distanceTo(target) <= GRAB_RANGE) {
            performDevilThrow(player, target);
            player.getPersistentData().putLong("LastGrabTime", player.level().getGameTime());
        }
    }

    private static void performDevilThrow(Player player, LivingEntity target) {
        // 抓取动作
        Vec3 throwDirection = player.getLookAngle().scale(THROW_FORCE);
        target.setDeltaMovement(throwDirection.x, 0.5, throwDirection.z);
        target.hurtMarked = true;
        
        // 地面冲击波
        if (target.onGround()) {
            player.level().getEntitiesOfClass(LivingEntity.class, 
                target.getBoundingBox().inflate(2)).forEach(e -> {
                    e.hurt(player.damageSources().magic(), 5.0f);
                });
        }

        SeEX.spawnParticleRing(target, ParticleTypes.SOUL_FIRE_FLAME, 2.0f, 24);
        player.level().playSound(null, target.getX(), target.getY(), target.getZ(),
            SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 1.0F, 1.2F);
    }

    private static void updateChargeDecay(Player player) {
        long lastCharge = player.getPersistentData().getLong("LastChargeTime");
        if (player.level().getGameTime() - lastCharge > CHARGE_DECAY) {
            player.getPersistentData().putInt("NeroCharge", 0);
        }
    }

    private static void handleOverload(Player player) {
        int charge = player.getPersistentData().getInt("NeroCharge");
        if (charge >= MAX_CHARGE && !player.getPersistentData().getBoolean("OverloadMode")) {
            activateOverload(player);
        }
    }

    private static void activateOverload(Player player) {
        player.getPersistentData().putBoolean("OverloadMode", true);
        player.getPersistentData().putLong("OverloadStart", player.level().getGameTime());
        player.getPersistentData().putInt("NeroCharge", 0);
        
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, OVERLOAD_DURATION, 2));
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, OVERLOAD_DURATION, 1));
        
        SeEX.spawnParticleRing(player, ParticleTypes.FLASH, 2.5f, 36);
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
            SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.PLAYERS, 2.0F, 0.7F);
    }

    // 在攻击时应用超载伤害
    public static void applyOverloadDamage(LivingEntity target, Player player) {
        if (player.getPersistentData().getBoolean("OverloadMode")) {
            target.hurt(player.damageSources().onFire(), OVERLOAD_DAMAGE);
            SeEX.spawnParticleRing(target, ParticleTypes.FLAME, 1.0f, 8);
        }
    }
}
