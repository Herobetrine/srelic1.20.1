package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.legendreliclib.lib.util.FastMakeEntityUtil;
import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ChaosBreaker extends SpecialEffect {
    // ===== 平衡配置 =====
    private static final int MAX_COMBO = 10;          // 最大连击数
    private static final int COMBO_DECAY = 30;        // 连击衰减时间(1.5秒)
    private static final float COMBO_DAMAGE_PER = 0.07f; // 每层连击伤害加成(7%)

    // 雷核充能
    private static final int CHARGE_NEED = 4;         // 充能所需连击次数
    private static final int BURST_DURATION = 80;     // 爆发持续时间(4秒)
    private static final float BURST_DAMAGE_MULTI = 1.8f; // 爆发伤害倍率

    // 星裂斩
    private static final float SLASH_RANGE = 4.0f;     // 斩击范围
    private static final float SLASH_ANGLE = 100.0f;  // 扇形角度
    private static final int MAX_TARGETS = 5;         // 最大连锁目标数

    // 闪电链
    private static final float CHAIN_DAMAGE_DECAY = 0.65f; // 每次连锁伤害衰减
    private static final int MAX_CHAIN_DEPTH = 4;      // 最大连锁深度
    private static final float MAX_CHAIN_DAMAGE = 50.0f; // 单次连锁最大伤害

    public ChaosBreaker() {
        super(90, false, false);
    }

    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.CHAOS_BREAKER.get())) return;
        if (player.level().isClientSide) return;
        if (event.getSource().is(DamageTypes.LIGHTNING_BOLT))return;
        updateComboSystem(player);
        triggerLightningChain(player, event.getEntity());
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Player player = event.player;
        if (player.getPersistentData().getBoolean("StarBurstMode")) {
            maintainBurstEffects(player);
        }

        // 连击衰减检测
        long lastHit = player.getPersistentData().getLong("ChaosLastHit");
        if (player.level().getGameTime() - lastHit > COMBO_DECAY &&
                SRStacksReg.CHAOS_BREAKER_STACKS.getCurrentStacks(player) > 0) {
            SRStacksReg.CHAOS_BREAKER_STACKS.resetStacks(player);
            if (!player.level().isClientSide) {
                player.sendSystemMessage(Component.literal("§c连击中断！"));
            }
        }
    }

    private static void updateComboSystem(Player player) {
        int combo = SRStacksReg.CHAOS_BREAKER_STACKS.getCurrentStacks(player);

        // 更新连击数（不超过最大值）
        if (combo < MAX_COMBO) {
            SRStacksReg.CHAOS_BREAKER_STACKS.addStacks(player, 1);
            combo++;
        }

        player.getPersistentData().putLong("ChaosLastHit", player.level().getGameTime());

        // 充能检测（每CHARGE_NEED次连击充能一次）
        if (combo % CHARGE_NEED == 0) {
            int chargeCount = player.getPersistentData().getInt("ChargeCount") + 1;
            player.getPersistentData().putInt("ChargeCount", chargeCount);

            // 充能视觉反馈
            if (!player.level().isClientSide) {
                SeEX.spawnParticleRing(player, ParticleTypes.ELECTRIC_SPARK,
                        1.5f + chargeCount * 0.3f, 12 + chargeCount * 4);
            }
        }
    }

    private static void triggerLightningChain(Player player, LivingEntity primaryTarget) {
        int combo = SRStacksReg.CHAOS_BREAKER_STACKS.getCurrentStacks(player);
        float damageBoost = 1 + combo * COMBO_DAMAGE_PER;

        // 爆发模式伤害加成
        if (player.getPersistentData().getBoolean("StarBurstMode")) {
            damageBoost *= BURST_DAMAGE_MULTI;
        }

        // 扇形范围攻击（限制最大目标数）
        int targetCount = 0;
        for (LivingEntity entity : player.level().getEntitiesOfClass(LivingEntity.class,
                player.getBoundingBox().inflate(SLASH_RANGE))) {
            if (targetCount >= MAX_TARGETS) break;
            if (entity == player || !isInSector(player, entity, SLASH_ANGLE)) continue;

            // 生成闪电实体（视觉效果）
            LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(player.level());
            if (lightning != null) {
                lightning.moveTo(entity.getX(), entity.getY(), entity.getZ());
                player.level().addFreshEntity(lightning);
            }

            // 连锁闪电机制
            chainLightning(player, entity, MAX_CHAIN_DEPTH, damageBoost);
            targetCount++;

            // 音效
            player.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                    SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.WEATHER,
                    0.8F, 0.9F + player.getRandom().nextFloat() * 0.2F);
        }

        // 充能爆发检测
        if (player.getPersistentData().getInt("ChargeCount") >= 3) {
            activateStarBurst(player);
        }
    }

    private static void activateStarBurst(Player player) {
        player.getPersistentData().putBoolean("StarBurstMode", true);
        player.getPersistentData().putLong("BurstStartTime", player.level().getGameTime());
        player.getPersistentData().putInt("ChargeCount", 0); // 消耗所有充能

        // 爆发增益效果（有限时间）
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, BURST_DURATION, 1));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, BURST_DURATION, 0));

        if (!player.level().isClientSide) {
            player.sendSystemMessage(Component.literal("§6★星裂模式激活★ §e最大出力！"));
        }
        SeEX.spawnParticleRing(player, ParticleTypes.REVERSE_PORTAL, 3.0f, 48);
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ILLUSIONER_CAST_SPELL, SoundSource.PLAYERS, 1.0F, 0.7F);
    }

    private static void maintainBurstEffects(Player player) {
        long burstTime = player.level().getGameTime() -
                player.getPersistentData().getLong("BurstStartTime");

        // 动态特效
        if (burstTime % 4 == 0) {
            float size = 1.8f + (float)Math.sin(burstTime * 0.15) * 0.4f;
            SeEX.spawnParticleRing(player, ParticleTypes.FIREWORK, size, 12);
        }

        // 结束检测
        if (burstTime > BURST_DURATION) {
            player.getPersistentData().putBoolean("StarBurstMode", false);
            if (!player.level().isClientSide) {
                player.sendSystemMessage(Component.literal("§7[系统] 能量核心过载冷却..."));
            }
            SeEX.spawnParticleRing(player, ParticleTypes.SMOKE, 3.5f, 32);
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.LIGHTNING_BOLT_IMPACT, SoundSource.PLAYERS, 0.8F, 1.2F);
        }
    }

    private static boolean isInSector(Player player, LivingEntity target, float angle) {
        Vec3 look = player.getLookAngle();
        Vec3 toTarget = target.position().subtract(player.position()).normalize();
        return look.dot(toTarget) > Math.cos(Math.toRadians(angle/2));
    }

    // 平衡的连锁闪电实现
    private static void chainLightning(Player player, LivingEntity origin, int remainingChain, float baseBoost) {
        if (remainingChain <= 0) return;

        // 获取玩家基础攻击力（更安全的获取方式）
        float baseDamage = 4.0f;
        AttributeInstance attackAttr = player.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackAttr != null) {
            baseDamage = (float) attackAttr.getValue();
        }

        // 伤害计算（含衰减和上限）
        float damageMulti = baseBoost * (float) Math.pow(CHAIN_DAMAGE_DECAY, MAX_CHAIN_DEPTH - remainingChain);
        float damage = Math.min(baseDamage * damageMulti, MAX_CHAIN_DAMAGE);
        origin.invulnerableTime=0;
        // 应用伤害
        origin.hurt(FastMakeEntityUtil.DamageSourceToCreat( player, DamageTypes.LIGHTNING_BOLT), damage);

        // 粒子效果
        if (player.level() instanceof ServerLevel serverLevel) {
            Vec3 start = player.position().add(0, 1.5, 0);
            Vec3 end = origin.position().add(0, 1, 0);
            SeEX.spawnLightningBeam(serverLevel, start, end, ParticleTypes.ELECTRIC_SPARK, 6);
        }

        // 递归连锁（限制目标数和深度）
        int chainCount = 0;
        double searchRadius = 5.0 - (MAX_CHAIN_DEPTH - remainingChain) * 0.8;

        for (LivingEntity nextTarget : origin.level().getEntitiesOfClass(LivingEntity.class,
                origin.getBoundingBox().inflate(searchRadius))) {
            if (chainCount >= 2) break; // 每次最多连锁2个目标
            if (nextTarget == origin || nextTarget == player || nextTarget.isAlliedTo(origin)) continue;

            chainLightning(player, nextTarget, remainingChain - 1, baseBoost);
            chainCount++;
        }
    }
}