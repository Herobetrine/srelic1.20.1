package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber
public class ThunderstormSlash extends SpecialEffect {
    // ===== 平衡配置 =====
    private static final int CHAIN_RANGE = 5;          // 连锁闪电范围
    private static final int MAX_CHAIN = 4;            // 最大连锁次数
    private static final float BASE_CHAIN_DAMAGE = 0.4f; // 基础连锁伤害比例
    private static final float CHAIN_DAMAGE_DECAY = 0.6f; // 连锁伤害衰减

    // 感电系统
    private static final int CHARGE_MAX = 8;           // 最大感电层数
    private static final int CHARGE_DECAY = 40;        // 感电衰减时间(2秒)
    private static final float CHARGE_DAMAGE_PER = 0.15f; // 每层感电伤害加成
    private static final float CHARGE_DAMAGE_CAP = 10.0f; // 感电最大额外伤害

    // 超载模式
    private static final int OVERLOAD_DURATION = 120;  // 超载持续时间(6秒)
    private static final float OVERLOAD_BOOST = 1.3f;  // 超载伤害加成
    private static final int OVERLOAD_COOLDOWN = 300;  // 超载冷却时间(15秒)

    public ThunderstormSlash() {
        super(90, false, false);
    }

    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.THUNDERSTORM_SLASH.get())) return;
        if (player.level().isClientSide()) return; // 仅服务端执行

        applyChainLightning(player, event.getEntity());
        updateChargeStack(player, event.getEntity());
    }

    @SubscribeEvent
    public static void onDamageDealt(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.THUNDERSTORM_SLASH.get())) return;

        // 感电爆发计算
        LivingEntity target = event.getEntity();
        int charge = getChargeLevel(target);
        if (charge > 0) {
            float extraDamage = calculateChargeDamage(player, charge);
            event.setAmount(event.getAmount() + extraDamage);
            resetChargeLevel(target);
            spawnLightningPillar(target);
        }

        // 超载模式加成
        if (isOverloadActive(player)) {
            event.setAmount(event.getAmount() * OVERLOAD_BOOST);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (event.phase != TickEvent.Phase.END) return;

        // 处理超载模式
        if (isOverloadActive(player)) {
            handleOverloadMode(player);
        }

        // 处理超载冷却
        handleOverloadCooldown(player);
    }

    @SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();

        // 处理感电衰减
        handleChargeDecay(entity);
    }

    // ===== 核心功能实现 =====
    private static void applyChainLightning(Player player, LivingEntity primaryTarget) {
        List<LivingEntity> chainedEntities = new ArrayList<>();
        LivingEntity currentTarget = primaryTarget;
        chainedEntities.add(currentTarget);

        float baseDamage = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE) * BASE_CHAIN_DAMAGE;
        float currentDamage = baseDamage;

        for (int i = 0; i < MAX_CHAIN; i++) {
            List<LivingEntity> nearbyEntities = currentTarget.level()
                    .getEntitiesOfClass(LivingEntity.class, currentTarget.getBoundingBox().inflate(CHAIN_RANGE));

            Optional<LivingEntity> nextTarget = nearbyEntities.stream()
                    .filter(e -> !chainedEntities.contains(e) && e != player)
                    .findFirst();

            if (nextTarget.isEmpty()) break;

            LivingEntity next = nextTarget.get();
            next.hurt(player.damageSources().lightningBolt(), currentDamage);
            chainedEntities.add(next);

            // 连锁视觉效果
            if (player.level() instanceof ServerLevel serverLevel) {
                SeEX.spawnLightningBeam(serverLevel,
                        currentTarget.position().add(0, 1, 0),
                        next.position().add(0, 1, 0),
                        ParticleTypes.ELECTRIC_SPARK, 6
                );
            }

            // 伤害衰减
            currentDamage *= CHAIN_DAMAGE_DECAY;
            currentTarget = next;
        }
    }

    private static void updateChargeStack(Player player, LivingEntity target) {
        int currentCharge = getChargeLevel(target);
        if (currentCharge < CHARGE_MAX) {
            setChargeLevel(target, currentCharge + 1);
            target.getPersistentData().putLong("LastChargeTime", player.level().getGameTime());
        }

        // 超载模式触发
        if (currentCharge >= CHARGE_MAX - 1 &&
                !isOverloadActive(player) &&
                !isOverloadCooldown(player)) {
            activateOverload(player);
        }
    }

    private static void activateOverload(Player player) {
        player.getPersistentData().putBoolean("OverloadMode", true);
        player.getPersistentData().putLong("OverloadStart", player.level().getGameTime());

        // 增益效果
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, OVERLOAD_DURATION, 1));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, OVERLOAD_DURATION, 0));

        // 视觉特效
        SeEX.spawnParticleRing(player, ParticleTypes.END_ROD, 2.0f, 24);
        SeEX.spawnParticleRing(player, ParticleTypes.ELECTRIC_SPARK, 1.5f, 32);
    }

    // ===== 辅助方法 =====
    private static int getChargeLevel(LivingEntity entity) {
        return entity.getPersistentData().getInt("ThunderCharge");
    }

    private static void setChargeLevel(LivingEntity entity, int level) {
        entity.getPersistentData().putInt("ThunderCharge", Math.min(level, CHARGE_MAX));
    }

    private static void resetChargeLevel(LivingEntity entity) {
        entity.getPersistentData().putInt("ThunderCharge", 0);
    }

    private static boolean isOverloadActive(Player player) {
        return player.getPersistentData().getBoolean("OverloadMode");
    }

    private static boolean isOverloadCooldown(Player player) {
        long lastOverload = player.getPersistentData().getLong("LastOverload");
        return player.level().getGameTime() - lastOverload < OVERLOAD_COOLDOWN;
    }

    private static float calculateChargeDamage(Player player, int charge) {
        float baseDamage = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
        return Math.min(charge * CHARGE_DAMAGE_PER * baseDamage, CHARGE_DAMAGE_CAP);
    }

    private static void handleOverloadMode(Player player) {
        long startTime = player.getPersistentData().getLong("OverloadStart");
        long currentTime = player.level().getGameTime();

        // 粒子效果
        if (currentTime % 4 == 0) {
            float size = 1.5f + (float) Math.sin(currentTime * 0.1) * 0.3f;
            SeEX.spawnParticleRing(player, ParticleTypes.ELECTRIC_SPARK, size, 12);
        }

        // 结束检测
        if (currentTime - startTime > OVERLOAD_DURATION) {
            player.getPersistentData().putBoolean("OverloadMode", false);
            player.getPersistentData().putLong("LastOverload", currentTime);

            // 结束特效
            SeEX.spawnParticleRing(player, ParticleTypes.SMOKE, 2.5f, 20);
        }
    }

    private static void handleOverloadCooldown(Player player) {
        if (!isOverloadCooldown(player)) return;

        long lastOverload = player.getPersistentData().getLong("LastOverload");
        long cooldownLeft = OVERLOAD_COOLDOWN - (player.level().getGameTime() - lastOverload);

        // 冷却结束提示
        if (cooldownLeft == 100) {
            player.displayClientMessage(Component.literal("§b超载冷却即将结束..."), true);
        }
    }

    private static void handleChargeDecay(LivingEntity entity) {
        if (!entity.getPersistentData().contains("LastChargeTime")) return;

        long lastCharge = entity.getPersistentData().getLong("LastChargeTime");
        if (entity.level().getGameTime() - lastCharge > CHARGE_DECAY) {
            int charge = getChargeLevel(entity);
            if (charge > 0) {
                setChargeLevel(entity, charge - 1);
                entity.getPersistentData().putLong("LastChargeTime", entity.level().getGameTime());
            }
        }
    }

    private static void spawnLightningPillar(LivingEntity target) {
        if (!(target.level() instanceof ServerLevel serverLevel)) return;

        // 粒子效果
        serverLevel.sendParticles(ParticleTypes.REVERSE_PORTAL,
                target.getX(), target.getY() + 1, target.getZ(),
                25, 0.5, 1.5, 0.5, 0.15
        );

        // 范围伤害（限制伤害）
        target.level().getEntitiesOfClass(LivingEntity.class,
                target.getBoundingBox().inflate(2.5)).forEach(e -> {
                    if (e != target) {
                        e.hurt(target.damageSources().magic(), 4.0f);
                    }
                }
        );
    }
}