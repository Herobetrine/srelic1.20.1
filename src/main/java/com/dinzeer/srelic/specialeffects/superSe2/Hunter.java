package com.dinzeer.srelic.specialeffects.superSe2;

import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.registry.SRStacksReg;
import mods.flammpfeil.slashblade.capability.slashblade.ISlashBladeState;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.WeakHashMap;

@Mod.EventBusSubscriber
public class Hunter extends SpecialEffect {
    
    // 常量定义
    private static final float SPEED_BOOST = 0.2f; // 20%移动速度加成
    private static final float DAMAGE_BONUS_PER_10_SPEED = 0.05f; // 每10%移速提供5%伤害加成
    private static final float TOXIN_DAMAGE_FACTOR = 0.2f; // 毒素伤害系数 (20%基础攻击)
    private static final int TOXIN_DAMAGE_INTERVAL = 10; // 毒素伤害间隔 (10 ticks = 0.5秒)
    private static final int TOXIN_DECAY_INTERVAL = 200; // 毒素衰减间隔 (200 ticks = 10秒)
    
    // 状态存储 (使用WeakHashMap防止内存泄漏)
    private static final Map<LivingEntity, Player> toxinSources = new WeakHashMap<>();
    private static final Map<LivingEntity, Integer> toxinDamageTimers = new WeakHashMap<>();
    private static final Map<LivingEntity, Integer> toxinDecayTimers = new WeakHashMap<>();
    
    // 移动速度加成修饰符UUID
    private static final UUID SPEED_BOOST_UUID = UUID.fromString("1a1b1c1d-2e2f-3a3b-4c4d-5e5f6a6b7c7d");

    public Hunter() {
        super(80); // 特效ID
    }

    /**
     * 处理玩家tick事件 - 应用移动速度加成和毒素逻辑
     */
    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        
        // 玩家移动速度加成
        if (entity instanceof Player player) {
            applySpeedBoost(player);
        }
        
        // 毒素伤害逻辑
        processToxinDamage(entity);
        
        // 毒素衰减逻辑
        processToxinDecay(entity);
    }

    /**
     * 应用移动速度加成
     */
    private static void applySpeedBoost(Player player) {
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.HUNTER.get())) return;
        
        // 获取属性实例并添加修饰符
        var speedAttribute = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if (speedAttribute != null) {
            // 移除旧修饰符并添加新修饰符
            speedAttribute.removeModifier(SPEED_BOOST_UUID);
            speedAttribute.addTransientModifier(
                new AttributeModifier(SPEED_BOOST_UUID, "Hunter speed boost", SPEED_BOOST, AttributeModifier.Operation.MULTIPLY_TOTAL)
            );
        }
    }

    /**
     * 处理攻击事件 - 计算伤害加成并叠加毒素
     */
    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        // 新增: 跳过毒伤害来源，防止递归
        if (event.getSource().is(DamageTypes.MAGIC)) {
            return;
        }
        
        // 验证攻击者是否为持有特效的玩家
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.HUNTER.get())) return;
        
        // 计算伤害加成
        float damageBonus = calculateDamageBonus(player);
        event.setAmount(event.getAmount() * (1 + damageBonus));
        
        // 叠加毒素层数
        addToxinStack(player, event.getEntity());
    }

    /**
     * 计算伤害加成 (基于移动速度)
     */
    private static float calculateDamageBonus(Player player) {
        // 获取当前移动速度加成百分比
        float speedMultiplier = player.getSpeed() - 1.0f; // 减去基础速度
        // 计算伤害加成 (每10%移速提供5%伤害)
        return speedMultiplier * (DAMAGE_BONUS_PER_10_SPEED / 0.1f);
    }

    /**
     * 添加毒素层数
     */
    private static void addToxinStack(Player source, LivingEntity target) {
        // 使用堆栈管理器添加层数
        SRStacksReg.HUNTER_TOXIN_STACKS.addStacks(source, 1);
        
        // 记录毒素来源
        toxinSources.put(target, source);
        
        // 初始化计时器
        toxinDamageTimers.putIfAbsent(target, TOXIN_DAMAGE_INTERVAL);
        toxinDecayTimers.putIfAbsent(target, TOXIN_DECAY_INTERVAL);
    }

    /**
     * 处理毒素伤害
     */
    private static void processToxinDamage(LivingEntity entity) {
        if (!toxinDamageTimers.containsKey(entity)) return;
        
        // 更新计时器
        int timer = toxinDamageTimers.get(entity) - 1;
        toxinDamageTimers.put(entity, timer);
        
        // 计时器归零时触发伤害
        if (timer <= 0) {
            Player source = toxinSources.get(entity);
            if (source != null) {
                // 修复: 增加空值检查避免崩溃
                Optional<ISlashBladeState> state = source.getMainHandItem().getCapability(SlashBladeUtil.state).resolve();
                if (!state.isPresent()) {
                    toxinDamageTimers.remove(entity);
                    return;
                }
                
                // 计算伤害值 (基础攻击的20% * 层数)
                int stacks = SRStacksReg.HUNTER_TOXIN_STACKS.getCurrentStacks(source);
                float baseAttack = state.get().getBaseAttackModifier();
                float damage = baseAttack * TOXIN_DAMAGE_FACTOR * stacks;
                
                // 应用伤害 (改为毒伤害类型)
                entity.invulnerableTime = 0;
                entity.hurt(
                    SlashBladeUtil.DamageSourceToCreat(source, DamageTypes.MAGIC), // 改为毒伤害类型
                    damage
                );
                
                // 重置计时器
                toxinDamageTimers.put(entity, TOXIN_DAMAGE_INTERVAL);
                
                // 生成粒子效果 (服务端)
                if (entity.level() instanceof ServerLevel serverLevel) {
                    Vec3 pos = entity.position().add(0, entity.getBbHeight() * 0.5, 0);
                    serverLevel.sendParticles(
                        net.minecraft.core.particles.ParticleTypes.ENTITY_EFFECT,
                        pos.x, pos.y, pos.z,
                        stacks * 2, 0.3, 0.5, 0.3, 0.1
                    );
                }
            }
        }
    }

    /**
     * 处理毒素衰减
     */
    private static void processToxinDecay(LivingEntity entity) {
        if (!toxinDecayTimers.containsKey(entity)) return;
        
        // 更新计时器
        int timer = toxinDecayTimers.get(entity) - 1;
        toxinDecayTimers.put(entity, timer);
        
        // 计时器归零时衰减层数
        if (timer <= 0) {
            Player source = toxinSources.get(entity);
            if (source != null) {
                // 减少一层毒素
                int currentStacks = SRStacksReg.HUNTER_TOXIN_STACKS.getCurrentStacks(source);
                if (currentStacks > 0) {
                    SRStacksReg.HUNTER_TOXIN_STACKS.addStacks(source, -1);
                }
                
                // 重置计时器
                toxinDecayTimers.put(entity, TOXIN_DECAY_INTERVAL);
            }
        }
        
        // 清理死亡实体
        if (!entity.isAlive()) {
            toxinSources.remove(entity);
            toxinDamageTimers.remove(entity);
            toxinDecayTimers.remove(entity);
        }
    }
}