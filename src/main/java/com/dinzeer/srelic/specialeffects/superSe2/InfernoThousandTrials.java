package com.dinzeer.srelic.specialeffects.superSe2;

import com.dinzeer.srelic.Utils.SlashBladeUtil;
import com.dinzeer.srelic.registry.SRSpecialEffectsRegistry;
import mods.flammpfeil.slashblade.registry.specialeffects.SpecialEffect;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.WeakHashMap;

@Mod.EventBusSubscriber
public class InfernoThousandTrials extends SpecialEffect {
    
    // 新增: 攻击计数器 (记录玩家攻击次数)
    private static final Map<Player, Integer> attackCountMap = new WeakHashMap<>();
    private static final int ATTACKS_REQUIRED = 10; // 需要10次攻击才能触发火焰领域
    
    private static final float DAMAGE_BONUS = 2.0f; // 200%伤害提升
    private static final float SELF_DAMAGE_PERCENT = 0.02f; // 自身伤害百分比
    private static final float DAMAGE_REDUCTION = 0.5f; // 伤害减半
    private static final float MAX_HEART_BONUS = 1.5f; // 150%上限
    
    public InfernoThousandTrials() {
        super(130);
    }
    
    // 玩家攻击事件处理
    @SubscribeEvent
    public static void onAttack(LivingHurtEvent event) {
        if (!(event.getSource().getEntity() instanceof Player player)) return;
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.INFERNO_THOUSAND_TRIALS.get())) {
            // 新增: 移除无特效玩家的攻击计数
            attackCountMap.remove(player);
            return;
        }
        
        float selfDamage = player.getMaxHealth() * SELF_DAMAGE_PERCENT;
        player.hurt(SlashBladeUtil.DamageSourceToCreat(player, DamageTypes.FELL_OUT_OF_WORLD), selfDamage);
        
        // 计算额外伤害增幅（1颗心=10%）
        float heartBonus = selfDamage * 0.1f;
        // 新增：限制最大150%
        if (heartBonus > player.getMaxHealth() * MAX_HEART_BONUS) {
            heartBonus = player.getMaxHealth() * MAX_HEART_BONUS;
            // 上限提示粒子
            if (player.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.FLASH,
                    player.getX(), player.getY() + 2, player.getZ(),
                    5, 0, 0.5, 0, 0.1);
            }
        }
        
        // 应用伤害增益：200%基础提升 + 生命扣除转换的额外伤害
        float originalDamage = event.getAmount();
        float boostedDamage = originalDamage * (1 + DAMAGE_BONUS) + heartBonus;
        event.setAmount(boostedDamage);
        
        // 新增: 攻击计数逻辑
        int currentCount = attackCountMap.getOrDefault(player, 0) + 1;
        attackCountMap.put(player, currentCount);
        
        // 当达到10次攻击时触发火焰领域
        if (currentCount >= ATTACKS_REQUIRED&&!event.getSource().is(DamageTypes.IN_FIRE)) {
            triggerFlameField(player);
            attackCountMap.put(player, 0); // 重置计数器
        }
    }
    
    // 玩家受伤事件处理（伤害减半）
    @SubscribeEvent
    public static void onPlayerHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!SlashBladeUtil.hasSpecialEffect(player, SRSpecialEffectsRegistry.INFERNO_THOUSAND_TRIALS.get())) return;
        
        // 伤害减半
        event.setAmount(event.getAmount() * DAMAGE_REDUCTION);
    }
    
    // 火焰领域效果实现
    private static void triggerFlameField(Player player) {
        // 获取玩家50%攻击力
        float damageValue = (float)(player.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.5f);
        
        // 对周围敌人造成伤害
        player.level().getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(8.0D))
            .stream()
            .filter(e -> e != player && !e.isAlliedTo(player))
            .forEach(target -> {
                target.invulnerableTime = 0;
                target.hurt(SlashBladeUtil.DamageSourceToCreat(player, DamageTypes.IN_FIRE), damageValue);
                
                // 粒子效果
                if (player.level() instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(ParticleTypes.FLAME,
                        target.getX(),
                        target.getY() + target.getBbHeight() * 0.5,
                        target.getZ(),
                        10, 0.2, 0.2, 0.2, 0.05);
                }
            });
    }
}