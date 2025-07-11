package com.dinzeer.srelic.specialeffects.superse;

import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.specialeffects.SeEX;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;
import java.util.WeakHashMap;

@Mod.EventBusSubscriber
public class KafkaStrings extends SpecialEffect {
    // 核心参数
    private static final int MAX_STACKS = 5;           // 最大神经毒素层数
    private static final int DETONATION_DAMAGE = 12;   // 毒素引爆伤害
    private static final int WEB_RANGE = 7;            // 蛛网范围
    
    // 雷暴系统
    private static final int CHAIN_LIMIT = 3;          // 最大连锁次数
    private static final float CHAIN_DAMAGE_RATIO = 0.7f; // 连锁伤害衰减
    
    // 傀儡操控
    private static final int CONTROL_DURATION = 200;   // 控制持续时间
    private static final int CONTROL_COOLDOWN = 400;   // 控制冷却时间

    private static final Map<UUID, Integer> toxinStacks = new HashMap<>();
    // 添加递归抑制标记
    private static final WeakHashMap<LivingEntity, Boolean> processingEntities = new WeakHashMap<>();

    public KafkaStrings() {
        super(90, false, false);
    }

    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        
        // 替换老旧方法
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.KAFKA_STRINGS.get())) return;
        
        // 检查是否正在处理中
        if (processingEntities.containsKey(event.getEntity())) return;
        
        processingEntities.put(event.getEntity(), true);
        try {
            applyNeurotoxin(event.getEntity());
            triggerLightningWeb(player, event.getEntity());
        } finally {
            processingEntities.remove(event.getEntity());
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            handleToxinDamage(event.player);
            updateControlledMobs(event.player);
        }
    }

    private static void applyNeurotoxin(LivingEntity target) {
        int stacks = toxinStacks.getOrDefault(target.getUUID(), 0);
        stacks = Math.min(stacks + 1, MAX_STACKS);
        toxinStacks.put(target.getUUID(), stacks);

        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 80, stacks-1));
        SeEX.spawnParticleRing(target, ParticleTypes.SPORE_BLOSSOM_AIR, 0.8f, 16);
    }

    private static void triggerLightningWeb(Player player, LivingEntity primaryTarget) {
        List<LivingEntity> chained = new ArrayList<>();
        LivingEntity current = primaryTarget;
        
        for (int i = 0; i < CHAIN_LIMIT; i++) {
            executeLightningStrike(player, current, DETONATION_DAMAGE * (1 - i*CHAIN_DAMAGE_RATIO));
            chained.add(current);
            
            Optional<LivingEntity> next = findNextTarget(current, chained);
            if (next.isEmpty()) break;
            current = next.get();
        }
        
        if (toxinStacks.getOrDefault(primaryTarget.getUUID(), 0) >= MAX_STACKS) {
            toxinStacks.remove(primaryTarget.getUUID());
            primaryTarget.hurt(player.damageSources().magic(), DETONATION_DAMAGE * 2);
            SeEX.spawnParticleRing(primaryTarget, ParticleTypes.ELECTRIC_SPARK, 2.5f, 32);
        }
    }

    private static void executeLightningStrike(Player player, LivingEntity target, float damage) {
        if (player.level() instanceof ServerLevel serverLevel) {
            // 应用玩家伤害加成（基础伤害 + 攻击强度系数）
            float finalDamage = damage * (1.0f + player.getAttackStrengthScale(1F));

            // 使用特定伤害类型
            target.hurt(SlashBladeUtil.DamageSourceToCreat(player, DamageTypes.LIGHTNING_BOLT), finalDamage);

            // 添加闪电特效（保持原有视觉效果）
            serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                    target.getX(), target.getY() + 1, target.getZ(),
                    30, 0.5, 1.0, 0.5, 0.2);

            // 添加音效
            serverLevel.playSound(null, target.getX(), target.getY(), target.getZ(),
                    SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.WEATHER, 1.0F, 0.8F);

            attemptMindControl(target);
        }
    }

    private static void attemptMindControl(LivingEntity target) {
        if (target.getRandom().nextFloat() < 0.3f) {
            target.getPersistentData().putLong("ControlledTime", 
                target.level().getGameTime() + CONTROL_DURATION);
            target.addEffect(new MobEffectInstance(MobEffects.GLOWING, CONTROL_DURATION));
        }
    }

    private static void handleToxinDamage(Player player) {
        toxinStacks.entrySet().removeIf(entry -> {
            if (player.level() instanceof ServerLevel serverLevel) {
                LivingEntity entity = (LivingEntity) serverLevel.getEntity(entry.getKey());
                if (entity != null && entity.isAlive()) {
                    // 使用特定伤害类型
                    entity.hurt(SlashBladeUtil.DamageSourceToCreat(player, DamageTypes.LIGHTNING_BOLT), entry.getValue() * 0.5f);
                    return false;
                }
            }
            return true;
        });
    }

    // 修改 updateControlledMobs 方法
    private static void updateControlledMobs(Player player) {
        toxinStacks.keySet().removeIf(uuid -> {
            if (player.level() instanceof ServerLevel serverLevel) {
                LivingEntity entity = (LivingEntity) serverLevel.getEntity(uuid);
                if (entity != null) {
                    long controlledTime = entity.getPersistentData().getLong("ControlledTime");
                    if (controlledTime > entity.level().getGameTime()) {
                        entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 40, 2));
                        return false;
                    }
                }
            }
            return true;
        });
    }

    private static Optional<LivingEntity> findNextTarget(LivingEntity current, List<LivingEntity> excluded) {
        return current.level().getEntitiesOfClass(LivingEntity.class,
            current.getBoundingBox().inflate(WEB_RANGE)).stream()
            .filter(e -> !excluded.contains(e))
            .findFirst();
    }
}
