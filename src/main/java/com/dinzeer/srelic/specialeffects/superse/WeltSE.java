package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
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
public class WeltSE extends SpecialEffect {
    // 核心参数
    private static final int GRAVITY_DURATION = 200;   // 重力场持续时间
    private static final double BLACKHOLE_RADIUS = 5.0; // 黑洞作用半径
    private static final int TIME_LOCK_COOLDOWN = 300; // 时空锁定冷却
    
    // 虚数坍缩系统
    private static final int COLLAPSE_STACK_MAX = 7;   // 最大坍缩层数
    private static final float DAMAGE_PER_STACK = 0.15f;// 每层增伤

    public WeltSE() {
        super(95, false, false);
    }

    @SubscribeEvent
    public static void onAttack(SlashBladeEvent.HitEvent event) {
        ISlashBladeState state = event.getSlashBladeState();
        if(state.hasSpecialEffect(SRSpecialEffectsRegistry.WELT_SE.getId())) {
            Player player = (Player) event.getUser();
            
           event.getTarget().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 3));
            // 概率触发时空裂隙
            if (Math.random() < 0.3) {
                createTimeRift(player);
            }
        }
    }

    @SubscribeEvent
    public static void onEntityTick(TickEvent.PlayerTickEvent event) {
        LivingEntity entity = event.player;
        if (entity.getPersistentData().contains("GravityMark")) {
            
            // 持续重力牵引效果
            Vec3 center = new Vec3(
                entity.getPersistentData().getDouble("GravityX"),
                entity.getPersistentData().getDouble("GravityY"),
                entity.getPersistentData().getDouble("GravityZ")
            );
            
            Vec3 pullDirection = center.subtract(entity.position()).normalize();
            entity.setDeltaMovement(pullDirection.scale(0.15));
            
            if (entity.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.REVERSE_PORTAL,
                    entity.getX(), entity.getY()+0.5, entity.getZ(),
                    3, 0.3, 0.3, 0.3, 0.02);
            }
        }
    }

    private static void applyGravityMark(Player player, LivingEntity target) {
        target.getPersistentData().putDouble("GravityX", player.getX());
        target.getPersistentData().putDouble("GravityY", player.getY());
        target.getPersistentData().putDouble("GravityZ", player.getZ());
        target.getPersistentData().putInt("GravityTimer", 100);
    }

    private static void createTimeRift(Player player) {
        long lastTimeLock = player.getPersistentData().getLong("LastTimeLock");
        if (player.level().getGameTime() - lastTimeLock < TIME_LOCK_COOLDOWN) return;

        // 生成时空停滞领域
        player.level().getEntitiesOfClass(LivingEntity.class, 
            player.getBoundingBox().inflate(BLACKHOLE_RADIUS)).forEach(e -> {
                if (e != player) {
                    e.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 80, 5));
                    e.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100));
                    
                    // 向中心牵引
                    Vec3 center = player.position();
                    Vec3 dir = center.subtract(e.position()).normalize();
                    e.setDeltaMovement(dir.scale(0.5));
                }
            });
        
        // 黑洞视觉效果
        SeEX.spawnParticleRing(player, ParticleTypes.PORTAL, BLACKHOLE_RADIUS, 64);
        if (!player.level().isClientSide) {
            player.sendSystemMessage(Component.literal("§5[虚数奇点] §d时空结构崩坏开始！"));
        }
        
        player.getPersistentData().putLong("LastTimeLock", player.level().getGameTime());
    }

    @SubscribeEvent
    public static void onCollapseTrigger(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "welt_se", player.experienceLevel)) return;

        // 虚数坍缩层数机制
        int collapseStack = event.getEntity().getPersistentData().getInt("CollapseStack");
        event.setAmount(event.getAmount() * (1 + collapseStack * DAMAGE_PER_STACK));
        
        if (collapseStack >= COLLAPSE_STACK_MAX) {
            triggerQuantumCollapse(event.getEntity());
            event.getEntity().getPersistentData().putInt("CollapseStack", 0);
        } else {
            event.getEntity().getPersistentData().putInt("CollapseStack", collapseStack + 1);
        }
    }

    private static void triggerQuantumCollapse(LivingEntity target) {
        // 引发范围坍缩爆炸
        target.level().getEntitiesOfClass(LivingEntity.class, 
            target.getBoundingBox().inflate(7.0)).forEach(e -> {
                e.hurt(target.damageSources().magic(), 12.0f);
                if (!(e instanceof Player)){
                e.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60));
                }
            });
        
        if (target.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                target.getX(), target.getY()+1, target.getZ(),
                50, 2, 2, 2, 0.5);
        }
    }
}
