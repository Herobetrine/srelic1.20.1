package com.dinzeer.srelic.specialeffects.superSe2;

import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.registry.SRStacksReg;
import com.dinzeer.srelic.registry.imp.IStackManager;
import mods.flammpfeil.slashblade.event.SlashBladeEvent;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * 风花霜月·寒炎特效
 * 
 * 功能说明:
 * 1. 伤害提高60%
 * 2. 伤害变为火焰伤害和寒冷伤害混伤（在60%增伤后触发）
 * 3. 攻击概率给目标叠加「霜灼」效果（着火+缓慢3）
 * 4. 攻击带有「霜灼」的目标时，伤害提高30%
 * 5. 每五段斩击获得一层「落霜」，上限6层，持续6秒
 * 6. 每层「落霜」提供15%增伤，并无视30%火焰/霜冻抗性
 */
@Mod.EventBusSubscriber
public class FrostFlame extends SpecialEffect {
    
    // 常量配置
    private static final float BASE_DAMAGE_BOOST = 0.6f;          // 基础伤害提升60%
    private static final float FROSTBURN_BONUS = 0.3f;            // 攻击霜灼目标额外30%伤害
    private static final float STACK_DAMAGE_PER_LEVEL = 0.15f;    // 每层落霜提供15%增伤
    private static final float RESISTANCE_IGNORE = 0.3f;          // 无视30%抗性
    private static final int MAX_SLASH_COUNT = 5;                 // 5段斩击获得一层
    private static final float FROSTBURN_CHANCE = 0.3f;           // 30%概率触发霜灼
    
    // 堆栈管理器
    public static final IStackManager FROST_FLAME_STACKS = SRStacksReg.FROST_FLAME_STACKS;
    
    // 玩家状态存储 (使用WeakHashMap防止内存泄漏)
    private static final Map<Player, Integer> slashCounters = new WeakHashMap<>();
    private static final Map<LivingEntity, Long> frostburnTimers = new WeakHashMap<>();
    
    // 递归抑制标记 (防止混伤导致的递归调用)
    private static final ThreadLocal<Boolean> processingMixedDamage = ThreadLocal.withInitial(() -> false);
    
    public FrostFlame() {
        super(80); // 特效ID
    }
    
    /**
     * 攻击事件处理 - 实现风花霜月·寒炎效果
     * 
     * @param event 生物受伤事件
     */
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onAttack(LivingHurtEvent event) {
        // 验证攻击者是否为持有特效的玩家
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.FROST_FLAME.get())) return;
        
        // 检查递归抑制标记
        if (processingMixedDamage.get()) return;
        
        LivingEntity target = event.getEntity();
        float originalDamage = event.getAmount();
        
        // 基础伤害提升60%
        float boostedDamage = originalDamage * (1.0f + BASE_DAMAGE_BOOST);
        
        // 检查目标是否有霜灼效果
        boolean hasFrostburn = hasFrostburn(target);
        if (hasFrostburn) {
            boostedDamage *= (1.0f + FROSTBURN_BONUS); // 额外30%伤害
        }
        
        // 应用落霜层数增伤
        int stacks = FROST_FLAME_STACKS.getCurrentStacks(player);
        if (stacks > 0) {
            boostedDamage *= (1.0f + stacks * STACK_DAMAGE_PER_LEVEL);
        }
        
        // 取消原始伤害事件
        event.setCanceled(true);
        
        // 设置递归抑制标记
        processingMixedDamage.set(true);
        
        try {
            // 造成混合伤害 (火焰+寒冷)
            applyMixedDamage(player, target, boostedDamage);
            
            // 概率附加霜灼效果
            if (player.getRandom().nextFloat() < FROSTBURN_CHANCE) {
                applyFrostburn(target);
            }
        } finally {
            // 清除递归抑制标记
            processingMixedDamage.set(false);
        }
    }
    
    /**
     * 斩击事件处理 - 计数斩击并叠加落霜层数
     * 
     * @param event 斩击事件
     */
    @SubscribeEvent
    public static void onSlash(SlashBladeEvent.DoSlashEvent event) {
        if (!(event.getUser() instanceof Player player)) return;
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.FROST_FLAME.get())) return;
        
        // 更新斩击计数器
        int count = slashCounters.getOrDefault(player, 0) + 1;
        slashCounters.put(player, count);
        
        // 每5段斩击获得一层落霜
        if (count >= MAX_SLASH_COUNT) {
            FROST_FLAME_STACKS.addStacks(player, 1);
            slashCounters.put(player, 0); // 重置计数器
            
            // 刷新落霜层数持续时间
            player.getPersistentData().putLong("FrostFlame_StackTime", player.level().getGameTime());
        }
    }
    
    /**
     * 应用混合伤害 (火焰+寒冷)
     * 
     * @param player 玩家实体
     * @param target 目标实体
     * @param totalDamage 总伤害值
     */
    private static void applyMixedDamage(Player player, LivingEntity target, float totalDamage) {
        // 计算每种伤害的数值 (各占一半)
        float halfDamage = totalDamage / 2.0f;
        
        // 无视抗性比例 (基于落霜层数)
        int stacks = FROST_FLAME_STACKS.getCurrentStacks(player);
        float ignoreRatio = stacks > 0 ? RESISTANCE_IGNORE : 0.0f;
        
        // 造成火焰伤害
        DamageSource fireSource = SlashBladeUtil.DamageSourceToCreat(player, DamageTypes.ON_FIRE);
        applyDamageWithResistanceIgnore(target, fireSource, halfDamage, ignoreRatio);
        
        // 造成寒冷伤害
        DamageSource coldSource = SlashBladeUtil.DamageSourceToCreat(player, DamageTypes.FREEZE);
        applyDamageWithResistanceIgnore(target, coldSource, halfDamage, ignoreRatio);
    }
    
    /**
     * 应用伤害并忽略部分抗性
     * 
     * @param target 目标实体
     * @param source 伤害来源
     * @param damage 伤害值
     * @param ignoreRatio 抗性忽略比例
     */
    private static void applyDamageWithResistanceIgnore(LivingEntity target, DamageSource source, float damage, float ignoreRatio) {
        // 计算实际伤害 (考虑抗性忽略)
        float actualDamage = damage * (1.0f + ignoreRatio);
        
        // 重置无敌时间并造成伤害
        target.invulnerableTime = 0;
        target.hurt(source, actualDamage);
    }
    
    /**
     * 应用霜灼效果 (着火+缓慢3)
     * 
     * @param target 目标实体
     */
    private static void applyFrostburn(LivingEntity target) {
        // 着火效果 (10秒)
        target.setSecondsOnFire(10);
        
        // 缓慢3效果 (10秒)
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 2));
        
        // 记录效果时间
        frostburnTimers.put(target, target.level().getGameTime());
    }
    
    /**
     * 检查目标是否有霜灼效果
     * 
     * @param target 目标实体
     * @return 是否有效果
     */
    private static boolean hasFrostburn(LivingEntity target) {
        Long startTime = frostburnTimers.get(target);
        if (startTime == null) return false;
        
        // 检查效果是否在有效期内 (10秒)
        long currentTime = target.level().getGameTime();
        return (currentTime - startTime) < 200; // 200 ticks = 10秒
    }
}