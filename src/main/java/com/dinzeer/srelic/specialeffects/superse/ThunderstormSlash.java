package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.capability.slashblade.SlashBladeState;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber
public class ThunderstormSlash extends SpecialEffect {
    // 核心参数
    private static final int CHAIN_RANGE = 6;          // 连锁闪电范围
    private static final int MAX_CHAIN = 5;            // 最大连锁次数
    private static final float CHAIN_DAMAGE_RATIO = 0.6f; // 连锁伤害衰减

    // 感电系统
    private static final int CHARGE_MAX = 10;          // 最大感电层数
    private static final int CHARGE_DECAY = 40;        // 感电衰减时间(2秒)
    private static final float CHARGE_DAMAGE = 2.5f;   // 每层感电伤害

    // 超载模式
    private static final int OVERLOAD_DURATION = 200;  // 超载持续时间(10秒)
    private static final float OVERLOAD_BOOST = 1.5f;  // 超载伤害加成

    public ThunderstormSlash() {
        super(90, false, true);
    }

    @SubscribeEvent
    public static void onAttack(LivingAttackEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "thunderstorm_slash", player.experienceLevel)) return;

        // 基础电磁伤害
        applyChainLightning(player, event.getEntity());
        updateChargeStack(player, event.getEntity());
    }

    @SubscribeEvent
    public static void onDamageDealt(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SeEX.hasSpecialEffect(player.getMainHandItem(), "thunderstorm_slash", player.experienceLevel)) return;

        // 感电爆发计算
        LivingEntity target = event.getEntity();
        int charge = target.getPersistentData().getInt("ThunderCharge");
        if (charge > 0) {
            event.setAmount(event.getAmount() + charge * CHARGE_DAMAGE);
            target.getPersistentData().putInt("ThunderCharge", 0);
            spawnLightningPillar(target);
        }

        // 超载模式检测
        if (player.getPersistentData().getBoolean("OverloadMode")) {
            event.setAmount(event.getAmount() * OVERLOAD_BOOST);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (event.phase == TickEvent.Phase.END &&
                player.getPersistentData().getBoolean("OverloadMode")) {

            // 超载模式粒子效果
            if (player.tickCount % 5 == 0) {
                SeEX.spawnParticleRing(player, ParticleTypes.ELECTRIC_SPARK,
                        1.2f + player.getRandom().nextFloat(), 16);
            }
        }
    }

    private static void applyChainLightning(Player player, LivingEntity primaryTarget) {
        List<LivingEntity> chainedTargets = new ArrayList<>();
        LivingEntity currentTarget = primaryTarget;
         if (!(player.getMainHandItem().getItem() instanceof ItemSlashBlade slashBlade))return;
        for (int i = 0; i < MAX_CHAIN; i++) {
            List<LivingEntity> nearbyEntities = currentTarget.level()
                    .getEntitiesOfClass(LivingEntity.class, currentTarget.getBoundingBox().inflate(CHAIN_RANGE));

            LivingEntity finalCurrentTarget = currentTarget;
            Optional<LivingEntity> nextTarget = nearbyEntities.stream()
                    .filter(e -> e != finalCurrentTarget && !chainedTargets.contains(e))
                    .findFirst();

            if (nextTarget.isPresent()) {
                LivingEntity next = nextTarget.get();
                next.hurt(player.damageSources().lightningBolt(),
                        (float) (player.getAttributeValue(Attributes.ATTACK_DAMAGE)* CHAIN_DAMAGE_RATIO));

                // 生成闪电链视觉效果
                if (player.level() instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                            currentTarget.getX(), currentTarget.getEyeY(), currentTarget.getZ(),
                            10, 0.5, 0.5, 0.5, 0.1);
                }

                chainedTargets.add(next);
                currentTarget = next;
            } else {
                break;
            }
        }
    }

    private static void updateChargeStack(Player player, LivingEntity target) {
        int currentCharge = target.getPersistentData().getInt("ThunderCharge");
        if (currentCharge < CHARGE_MAX) {
            target.getPersistentData().putInt("ThunderCharge", currentCharge + 1);
            target.getPersistentData().putLong("LastChargeTime", player.level().getGameTime());

            // 启动感电衰减计时器
            if (!target.getPersistentData().contains("ChargeDecayScheduled")) {
                target.getPersistentData().putBoolean("ChargeDecayScheduled", true);
                target.level().getServer().execute(() -> {
                    if (target.isAlive()) {
                        int charge = target.getPersistentData().getInt("ThunderCharge");
                        long lastTime = target.getPersistentData().getLong("LastChargeTime");
                        if (player.level().getGameTime() - lastTime > CHARGE_DECAY) {
                            target.getPersistentData().putInt("ThunderCharge", Math.max(charge - 1, 0));
                        }
                    }
                });
            }
        }

        // 超载模式触发
        if (currentCharge >= CHARGE_MAX - 1 && !player.getPersistentData().getBoolean("OverloadMode")) {
            activateOverload(player);
        }
    }

    private static void activateOverload(Player player) {
        player.getPersistentData().putBoolean("OverloadMode", true);
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, OVERLOAD_DURATION, 1));
        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, OVERLOAD_DURATION, 2));

        // 设置超载结束时间
        player.getPersistentData().putLong("OverloadEndTime",
                player.level().getGameTime() + OVERLOAD_DURATION);

        // 超载特效
        SeEX.spawnParticleRing(player, ParticleTypes.END_ROD, 2.5f, 32);
    }

    private static void spawnLightningPillar(LivingEntity target) {
        if (target.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.REVERSE_PORTAL,
                    target.getX(), target.getY() + 1, target.getZ(),
                    20, 0.5, 2.0, 0.5, 0.2);

            // 范围伤害
            target.level().getEntitiesOfClass(LivingEntity.class,
                    target.getBoundingBox().inflate(3)).forEach(e -> {
                e.hurt(target.damageSources().magic(), 8.0f);
                e.setSecondsOnFire(3);
            });
        }
    }
}
