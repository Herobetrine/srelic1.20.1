package com.dinzeer.srelic.specialeffects.superSe2;

// ... existing imports ...

// 新增导入
import com.dinzeer.legendreliclib.lib.util.impl.IStackManager;
import com.dinzeer.legendreliclib.lib.util.impl.RegisteredStackManager;
import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.srelic.registry.imp.SRRegisteredStackManager;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

@Mod.EventBusSubscriber
public class RedScar extends SpecialEffect {
    // 新增常量
    private static final int TRIGGER_LAYER_THRESHOLD = 4;
    private static final int HEAL_PER_LAYER = 4;
    private static final float HEAL_PERCENTAGE = 0.1f;
    private static final int BUFF_DURATION = 160; // 8秒(160tick)
    private static final int COOLDOWN_DURATION = 2400; // 120秒(2400tick)
    private static final float BUFF_DAMAGE_MULTIPLIER = 1.5f;
    private static final float BUFF_RADIUS_MULTIPLIER = 1.5f;
    
    // 新增状态存储
    private static final Map<Player, Integer> buffTimers = new WeakHashMap<>();
    private static final Map<Player, Integer> cooldownTimers = new WeakHashMap<>();
    private static final Map<Player, Integer> layerTimers = new WeakHashMap<>();

    private static final int FULL_LAYER_THRESHOLD = 8;
    private static final int LAYER_TIMER_DURATION = 300; // 15秒计时器(300tick)
    private static final float HEAL_AMOUNT = 2.0f;
    private static final float BASE_DAMAGE_PER_LAYER = 2.0f;
    private static final float FULL_LAYER_DAMAGE_MULTIPLIER = 1.5f;
    
    // 量子坍缩系统常量
    private static final double QUANTUM_TRIGGER_CHANCE = 0.1;
    private static final float QUANTUM_DAMAGE = 5.0f;
    private static final double QUANTUM_RADIUS = 8.0;
    
    // 异常值系统常量
    private static final int ANOMALY_INCREMENT = 10;
    private static final int ANOMALY_THRESHOLD = 100;
    private static final float ANOMALY_DAMAGE_MULTIPLIER = 0.4f;
    private static final long ARMOR_REDUCE_DURATION = 40; // 破防持续时间
    
    // 新增: IStackManager实例
    private static final IStackManager stackManager = SRStacksReg.RED_SCAR;
    // 效果存储Map
    private static final Map<LivingEntity, Integer> anomalyValues = new WeakHashMap<>();

    public RedScar() {
        super(120);
    }

    /**
     * 检查玩家是否拥有此特殊效果
     * @param player 待检查的玩家
     * @return 是否拥有效果
     */
    public static Boolean hasSE(Player player){
        return SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.RED_SCAR.get());
    }

    /**
     * 攻击事件处理主逻辑
     * 1. 处理层数积累
     * 2. 应用层数效果
     * 3. 处理异常值系统
     * 4. 尝试触发量子坍缩
     */
    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player) || !hasSE(player)) return;
        if (event.getSource().is(DamageTypes.MAGIC)) return;
        
        handleScarLayers(player);
        applyLayerEffects(event, player);
        handleAnomalySystem(event.getEntity(), player);
        tryQuantumCollapse(player);
    }

    /**
     * 处理红疤层数逻辑
     * - 每次攻击增加1层
     * - 达到满层时治疗玩家
     * - 重置层数计时器
     */
    private static void handleScarLayers(Player player) {
        stackManager.addStacks(player, 1);
        int newStacks = stackManager.getCurrentStacks(player);
        
        // 满层治疗效果
        if (newStacks > FULL_LAYER_THRESHOLD) {
            player.heal(HEAL_AMOUNT);
        }

    }

    /**
     * 应用层数效果到伤害事件
     * 1. 基础层数增伤
     * 2. 附加缓慢效果
     * 3. 满层额外增伤
     */
    private static void applyLayerEffects(LivingHurtEvent event, Player player) {
        // 获取当前层数
        int layers = stackManager.getCurrentStacks(player);
        
        // 基础层数增伤
        event.setAmount(event.getAmount() + layers * BASE_DAMAGE_PER_LAYER);
        // 附加缓慢效果
        event.getEntity().addEffect(new MobEffectInstance(
            MobEffects.MOVEMENT_SLOWDOWN,
            100,  // 5秒持续时间 
            0     // 等级0
        ));
        
        // 满层增伤
        if (layers >= FULL_LAYER_THRESHOLD) {
            event.setAmount(event.getAmount() * FULL_LAYER_DAMAGE_MULTIPLIER);
        }
    }

    /**
     * 尝试触发量子坍缩效果
     * 10%概率触发 或 增益状态下100%触发
     */
    private static void tryQuantumCollapse(Player player) {
        boolean isBuffed = buffTimers.containsKey(player);
        boolean shouldTrigger = isBuffed || (Math.random() < QUANTUM_TRIGGER_CHANCE);
        
        if (shouldTrigger) {
            // 计算增强后的数值
            float damage = isBuffed ? 
                QUANTUM_DAMAGE * BUFF_DAMAGE_MULTIPLIER : 
                QUANTUM_DAMAGE;
                
            double radius = isBuffed ? 
                QUANTUM_RADIUS * BUFF_RADIUS_MULTIPLIER : 
                QUANTUM_RADIUS;
            
            // 获取周围实体
            List<LivingEntity> entities = player.level().getEntitiesOfClass(
                LivingEntity.class, 
                new AABB(player.blockPosition()).inflate(QUANTUM_RADIUS)
            );
            
            // 对每个实体应用效果
            for (LivingEntity e : entities) {
                if (e.equals(player)) continue;
                
                // 吸引实体
                Vec3 direction = player.position().subtract(e.position()).normalize();
                e.setDeltaMovement(direction);
                
                // 造成伤害
                e.hurt(
                    SlashBladeUtil.DamageSourceToCreat(player, DamageTypes.MAGIC), 
                    QUANTUM_DAMAGE
                );
            }
            
            // 播放粒子效果
            if (player.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(
                    ParticleTypes.SWEEP_ATTACK,
                    player.getX(), player.getY(), player.getZ(), 
                    10,     // 粒子数量
                    0.5, 0.5, 0.5, // 偏移量
                    0.2    // 速度
                );
            }
        }
    }

    /**
     * 处理异常值系统
     * 1. 每次攻击增加目标异常值
     * 2. 异常值满时触发破防和额外伤害
     * 3. 重置异常值
     */
    private static void handleAnomalySystem(LivingEntity target, Player player) {
        int anomaly = anomalyValues.getOrDefault(target, 0) + ANOMALY_INCREMENT;
        
        if (anomaly >= ANOMALY_THRESHOLD) {
            // 设置破防状态结束时间
            long endTime = target.level().getGameTime() + ARMOR_REDUCE_DURATION;
            target.getPersistentData().putLong("ArmorReduceEndTime", endTime);
            
            // 造成百分比生命值伤害
            float damage = target.getHealth() * ANOMALY_DAMAGE_MULTIPLIER;
            target.hurt(
                SlashBladeUtil.DamageSourceToCreat(player, DamageTypes.MAGIC),
                damage
            );
        }
        
        // 重置异常值(模100保留余数)
        anomalyValues.put(target, anomaly % ANOMALY_THRESHOLD);
    }

    /**
     * 玩家tick事件处理
     * 1. 层数衰减
     * 2. 禁止生命恢复
     * 3. 提供群体增益
     * 4. 新增：特殊玩家触发条件检测
     */
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        
        // 层数衰减处理
        if (layerTimers.containsKey(player)) {
            int timer = layerTimers.get(player) - 1;
            if (timer <= 0) {
                // 层数衰减
                int current = stackManager.getCurrentStacks(player);
                if (current > 0) {
                    stackManager.addStacks(player, -1);
                }
                layerTimers.put(player, LAYER_TIMER_DURATION);
            } else {
                layerTimers.put(player, timer);
            }
        }
        
        // 新增：处理增益状态递减
        if (buffTimers.containsKey(player)) {
            int timer = buffTimers.get(player) - 1;
            if (timer <= 0) {
                buffTimers.remove(player);
            } else {
                buffTimers.put(player, timer);
            }
        }
        
        // 新增：处理冷却时间递减
        if (cooldownTimers.containsKey(player)) {
            int timer = cooldownTimers.get(player) - 1;
            if (timer <= 0) {
                cooldownTimers.remove(player);
            } else {
                cooldownTimers.put(player, timer);
            }
        }
        
        // 特殊玩家触发检测
        String playerName = player.getName().getString();
        if ((playerName.equals("biantwin") || playerName.equals("Dev") || 
             playerName.equals("dinzeer_dzr") || playerName.equals("steve")) &&
            !cooldownTimers.containsKey(player) &&
            player.getHealth() < player.getMaxHealth() * 0.25f) {
            
            // 获取当前层数
            int layers = stackManager.getCurrentStacks(player);
            if (layers >= TRIGGER_LAYER_THRESHOLD) {
                triggerSpecialEffect(player, layers);
            }
        }
        
        // 禁止生命恢复效果
        if (hasSE(player)) {
            player.removeEffect(MobEffects.REGENERATION);
        }
        
        // 提供群体增益
        if (stackManager.getCurrentStacks(player) > 0) {
            player.level().getEntitiesOfClass(Player.class, player.getBoundingBox().inflate(8))
                .forEach(p -> {
                    p.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40, 0));
                    p.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 40, 0));
                });
        }
    }
    
    /**
     * 新增：触发特殊效果
     * 1. 消耗所有层数
     * 2. 恢复生命值
     * 3. 设置增益状态
     * 4. 设置冷却时间
     */
    private static void triggerSpecialEffect(Player player, int layers) {
        // 消耗所有层数
        stackManager.resetStacks(player);
        
        // 计算并恢复生命值
        float maxHealth = player.getMaxHealth();
        float missingHealth = maxHealth - player.getHealth();
        float healAmount = layers * HEAL_PER_LAYER + missingHealth * HEAL_PERCENTAGE;
        player.heal(healAmount);
        
        // 设置增益状态
        buffTimers.put(player, BUFF_DURATION);
        
        // 设置冷却时间
        cooldownTimers.put(player, COOLDOWN_DURATION);
        
        // 播放粒子效果（可选）
        if (player.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(
                ParticleTypes.TOTEM_OF_UNDYING,
                player.getX(), player.getY() + 1.0, player.getZ(),
                50, 0.5, 0.5, 0.5, 0.2
            );
        }
    }

    /**
     * 实体tick事件处理
     * 检测并应用破防状态
     */
    @SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        CompoundTag data = entity.getPersistentData();
        
        // 检查破防状态是否生效
        long armorReduceEnd = data.getLong("ArmorReduceEndTime");
        long currentTime = entity.level().getGameTime();
        
        if (armorReduceEnd > currentTime) {
            // 应用护甲削弱效果
            AttributeInstance armorAttr = entity.getAttribute(Attributes.ARMOR);
            if (armorAttr != null) {
                double baseValue = armorAttr.getBaseValue();
                armorAttr.setBaseValue(baseValue * 0.6f);
            }
        }
    }
}