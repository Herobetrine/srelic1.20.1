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

import java.util.*;

@Mod.EventBusSubscriber
public class JingyuanThunder extends SpecialEffect {
    // 核心参数
    private static final int MAX_STACKS = 40;          // 最大神君层数
    private static final int STACK_DECAY = 100;        // 层数衰减时间(5秒)
    private static final float DAMAGE_PER_STACK = 0.2f;// 每层伤害加成
    
    // 雷殛领域
    private static final int FIELD_RADIUS = 5;         // 领域半径
    private static final int FIELD_DAMAGE_INTERVAL = 20;// 领域伤害间隔
    
    // 天霆断空斩
    private static final int ULT_COST = 10;            // 终结技消耗层数
    private static final float ULT_DAMAGE = 25.0f;     // 终结技基础伤害
    private static final int ULT_COOLDOWN = 200;       // 终结技冷却

    public JingyuanThunder() {
        super(90, false, false);
    }

    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "jingyuan_thunder", player.experienceLevel)) return;

        updateStacks(player);
        applyFieldEffect(player);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            if (SeEX.hasSpecialEffect(player.getMainHandItem(), "jingyuan_thunder", player.experienceLevel)) {
                handleStackDecay(player);
                handleUltimate(player);
            }
        }
    }

    private static void updateStacks(Player player) {
        int stacks = player.getPersistentData().getInt("JingyuanStacks");
        stacks = Math.min(stacks + 1, MAX_STACKS);
        player.getPersistentData().putInt("JingyuanStacks", stacks);
        player.getPersistentData().putLong("LastStackTime", player.level().getGameTime());
        
        SeEX.spawnParticleRing(player, ParticleTypes.ELECTRIC_SPARK, 1.0f + stacks*0.15f, 8 + stacks);
    }

    private static void applyFieldEffect(Player player) {
        if (player.tickCount % FIELD_DAMAGE_INTERVAL != 0) return;
        
        int stacks = player.getPersistentData().getInt("JingyuanStacks");
        player.level().getEntitiesOfClass(LivingEntity.class, 
            player.getBoundingBox().inflate(FIELD_RADIUS)).forEach(e -> {
                if (e != player) {
                    e.hurt(player.damageSources().lightningBolt(), stacks * 0.5f);

                }
            });
    }

    private static void handleStackDecay(Player player) {
        long lastStack = player.getPersistentData().getLong("LastStackTime");
        if (player.level().getGameTime() - lastStack > STACK_DECAY) {
            player.getPersistentData().putInt("JingyuanStacks", 0);
        }
    }

    private static void handleUltimate(Player player) {
        int stacks = player.getPersistentData().getInt("JingyuanStacks");
        long lastUlt = player.getPersistentData().getLong("LastUltTime");
        
        if (stacks >= ULT_COST && 
            player.level().getGameTime() - lastUlt > ULT_COOLDOWN) {
            executeUltimate(player);
        }
    }

    private static void executeUltimate(Player player) {
        player.getPersistentData().putInt("JingyuanStacks", 
            player.getPersistentData().getInt("JingyuanStacks") - ULT_COST);
        player.getPersistentData().putLong("LastUltTime", player.level().getGameTime());
        int stacks = player.getPersistentData().getInt("JingyuanStacks");
        // 扇形范围斩击
        Vec3 look = player.getLookAngle();
        player.level().getEntitiesOfClass(LivingEntity.class, 
            player.getBoundingBox().inflate(8)).forEach(e -> {
                if (isInFront(player, e, 90)) {
                    e.hurt(player.damageSources().magic(), ULT_DAMAGE*(stacks *DAMAGE_PER_STACK));
                    // 连环闪电
                    chainLightning(e, 3);
                }
            });
        
        // 特效与音效
        SeEX.spawnParticleRing(player, ParticleTypes.REVERSE_PORTAL, 3.0f, 48);
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
            SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.PLAYERS, 2.0F, 0.8F);
    }

    private static void chainLightning(LivingEntity origin, int remaining) {
        if (remaining <= 0) return;
        int stacks = origin.getPersistentData().getInt("JingyuanStacks");
        origin.level().getEntitiesOfClass(LivingEntity.class, 
            origin.getBoundingBox().inflate(4)).stream()
            .filter(e -> e != origin)
            .findFirst().ifPresent(e -> {
                e.hurt(origin.damageSources().lightningBolt(), ULT_DAMAGE * 0.6f*(stacks *DAMAGE_PER_STACK));
                SeEX.spawnLightningBeam(origin.level(), origin.position(), e.position(),ParticleTypes.END_ROD,8);
                chainLightning(e, remaining - 1);
            });
    }

    private static boolean isInFront(Player player, LivingEntity target, float angle) {
        Vec3 look = player.getLookAngle();
        Vec3 toTarget = target.position().subtract(player.position()).normalize();
        return look.dot(toTarget) > Math.cos(Math.toRadians(angle/2));
    }
}
