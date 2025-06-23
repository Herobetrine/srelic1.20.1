package com.dinzeer.srelic.specialeffects.superSe2;

import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import com.dinzeer.srelic.registry.SRStacksReg;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * 与梦的同行特效
 * 
 * 功能说明:
 * 1. 攻击伤害提高100%
 * 2. 攻击叠加霜冻值(满100点触发缓慢效果)
 * 3. 被缓慢的敌人受到伤害增加25%
 * 4. 给周围无仇恨生物施加力量III效果
 */
@Mod.EventBusSubscriber
public class DreamCompanion extends SpecialEffect {
    
    // 霜冻值状态存储
    private static final Map<LivingEntity, Integer> frostStacks = new WeakHashMap<>();
    // 递归抑制标记
    private static final WeakHashMap<LivingEntity, Boolean> processingEntities = new WeakHashMap<>();
    
    // 常量配置
    private static final int FROST_PER_HIT = 5;                 // 每次攻击叠加的霜冻值
    private static final int FROST_THRESHOLD = 100;             // 触发缓慢的霜冻阈值
    private static final float DAMAGE_BOOST = 1.0f;             // 伤害提高100%
    private static final float FROST_DAMAGE_MULTIPLIER = 1.25f; // 霜冻目标伤害加成
    private static final int SLOW_DURATION = 100;               // 缓慢效果持续时间(ticks)
    private static final int STRENGTH_DURATION = 100;           // 力量效果持续时间
    private static final int STRENGTH_LEVEL = 2;                // 力量III效果
    private static final double RANGE = 8.0;                    // 效果作用范围
    
    public DreamCompanion() {
        super(90); // 特效ID
    }
    
    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        // 检查攻击者是否为持有特效的玩家
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.DREAM_COMPANION.get())) return;
        
        LivingEntity target = event.getEntity();
        
        // 递归抑制检查
        if (processingEntities.containsKey(target)) return;
        processingEntities.put(target, true);
        
        try {
            // 伤害提高100%
            float damage = event.getAmount() * (1 + DAMAGE_BOOST);
            
            // 如果目标处于霜冻状态，额外增加25%伤害
            if (target.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                damage *= FROST_DAMAGE_MULTIPLIER;
            }
            
            // 更新霜冻值
            int currentFrost = frostStacks.getOrDefault(target, 0) + FROST_PER_HIT;
            frostStacks.put(target, currentFrost);
            
            // 达到阈值时触发缓慢效果
            if (currentFrost >= FROST_THRESHOLD) {
                target.addEffect(new MobEffectInstance(
                    MobEffects.MOVEMENT_SLOWDOWN, 
                    SLOW_DURATION, 
                    0 // 缓慢I效果
                ));
                frostStacks.put(target, 0); // 重置霜冻值
            }
            
            // 设置新的伤害值
            event.setAmount(damage);
            
            // 给周围无仇恨生物施加力量III效果
            if (target.level() instanceof ServerLevel serverLevel) {
                AABB area = new AABB(
                    player.getX() - RANGE, 
                    player.getY() - RANGE, 
                    player.getZ() - RANGE,
                    player.getX() + RANGE, 
                    player.getY() + RANGE, 
                    player.getZ() + RANGE
                );
                
                serverLevel.getEntitiesOfClass(Mob.class, area).forEach(mob -> {
                    // 检查生物是否没有对玩家产生仇恨
                    if (mob.getTarget() != player) {
                        mob.addEffect(new MobEffectInstance(
                            MobEffects.DAMAGE_BOOST, 
                            STRENGTH_DURATION, 
                            STRENGTH_LEVEL // 力量III效果
                        ));
                    }
                });
            }
        } finally {
            // 清除处理标记
            processingEntities.remove(target);
        }
    }
}